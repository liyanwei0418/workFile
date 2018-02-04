package cn.gukeer.platform.common;

import cn.gukeer.common.exception.ErrcodeException;
import cn.gukeer.common.utils.CacheUtils;
import cn.gukeer.common.utils.Exceptions;
import cn.gukeer.common.utils.SpringContextHolder;
import cn.gukeer.common.utils.StringUtils;
import cn.gukeer.platform.persistence.dao.LogMapper;
import cn.gukeer.platform.persistence.dao.MenuMapper;
import cn.gukeer.platform.persistence.entity.LogWithBLOBs;
import cn.gukeer.platform.persistence.entity.Menu;
import cn.gukeer.platform.persistence.entity.MenuExample;
import cn.gukeer.platform.persistence.entity.User;
import com.google.common.collect.Maps;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 日志工具类
 */
public class LogUtils {

    public static final String CACHE_MENU_NAME_PATH_MAP = "menuNamePathMap";

    private static LogMapper logMapper = SpringContextHolder.getBean(LogMapper.class);
    private static MenuMapper menuMapper = SpringContextHolder.getBean(MenuMapper.class);

    /**
     * 保存日志
     */
    public static void saveLog(HttpServletRequest request, String title) {
        saveLog(request, null, null, title);
    }

    /**
     * 保存日志
     */
    public static void saveLog(HttpServletRequest request, Object handler, Exception ex, String title) {
        User user = getLoginUser();
        if (user != null && user.getId() != null) {
            LogWithBLOBs log = new LogWithBLOBs();
            log.setTitle(title);
            log.setType(ex == null ? "1" : "2");
            log.setRemoteAddr(StringUtils.getRemoteAddr(request));
            log.setUserAgent(request.getHeader("user-agent"));
            log.setRequestUri(request.getRequestURI());
            log.setMethod(request.getMethod());
            log.setParams(LogUtils.setParams(request.getParameterMap()));
            // 异步保存日志
            new SaveLogThread(log, handler, ex).start();
        }
    }

    /**
     * 保存日志线程
     */
    public static class SaveLogThread extends Thread {

        private LogWithBLOBs log;
        private Object handler;
        private Exception ex;

        public SaveLogThread(LogWithBLOBs log, Object handler, Exception ex) {
            super(SaveLogThread.class.getSimpleName());
            this.log = log;
            this.handler = handler;
            this.ex = ex;
        }

        @Override
        public void run() {
            // 获取日志标题
            if (StringUtils.isBlank(log.getTitle())) {
                String permission = "";
                if (handler instanceof HandlerMethod) {
                    Method m = ((HandlerMethod) handler).getMethod();
                    RequiresPermissions rp = m.getAnnotation(RequiresPermissions.class);
                    permission = (rp != null ? StringUtils.join(rp.value(), ",") : "");
                }
                log.setTitle(getMenuNamePath(log.getRequestUri(), permission));
            }
            // 如果有异常，设置异常信息
            log.setException(Exceptions.getStackTraceAsString(ex));
            // 如果无标题并无异常日志，则不保存信息
            if (StringUtils.isBlank(log.getTitle()) && StringUtils.isBlank(log.getException())) {
                return;
            }
            if (log.getId() == null || log.getId() == "") {
                log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            }
            User user = getLoginUser();
            if (org.apache.commons.lang3.StringUtils.isNotBlank(user.getId().toString())) {
                log.setCreateBy(user.getId());
            }
            log.setCreateDate(System.currentTimeMillis());
            // 保存日志信息
            logMapper.insert(log);
        }
    }

    /**
     * 获取菜单名称路径
     */
    public static String getMenuNamePath(String requestUri, String permission) {
        ServletContext context = SpringContextHolder
                .getBean(ServletContext.class);
        String href = StringUtils.substringAfter(requestUri, context.getContextPath());// 去掉了adminPath
        @SuppressWarnings("unchecked")
        Map<String, String> menuMap = (Map<String, String>) CacheUtils.get(CACHE_MENU_NAME_PATH_MAP);
        if (menuMap == null) {
            menuMap = Maps.newHashMap();
            MenuExample example= new MenuExample();
            example.createCriteria().andDelFlagEqualTo(0);
            List<Menu> menuList = menuMapper.selectByExample(example);
            for (Menu menu : menuList) {
                // 获取菜单名称路径
                String namePath = "";
                if (menu.getParentId() != null && menu.getParentId() != "") {
                    for (Menu m : menuList) {
                        if (menu.getParentId() == m.getId()) {
                            namePath = m.getName() + "-";
                        }
                    }
                    namePath += menu.getName();
                } else namePath = menu.getName();
                // 设置菜单名称路径
                if (StringUtils.isNotBlank(menu.getHref())) {
                    menuMap.put(menu.getHref(), namePath);
                } else if (StringUtils.isNotBlank(menu.getPermission())) {
                    for (String p : StringUtils.split(menu.getPermission())) {
                        menuMap.put(p, namePath);
                    }
                }

            }
            CacheUtils.put(CACHE_MENU_NAME_PATH_MAP, menuMap);
        }
        String menuNamePath = menuMap.get(href);
        if (menuNamePath == null) {
            for (String p : permission.split(",")) {
                menuNamePath = menuMap.get(p);
                if (StringUtils.isNotBlank(menuNamePath)) {
                    break;
                }
            }
            if (menuNamePath == null) {
                return "";
            }
        }
        return menuNamePath;
    }

    /**
     * 设置请求参数
     *
     * @param paramMap
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static String setParams(Map paramMap) {
        if (paramMap == null) {
            return "";
        }
        StringBuilder params = new StringBuilder();
        for (Map.Entry<String, String[]> param : ((Map<String, String[]>) paramMap).entrySet()) {
            params.append(("".equals(params.toString()) ? "" : "&") + param.getKey() + "=");
            String paramValue = (param.getValue() != null && param.getValue().length > 0 ? param.getValue()[0] : "");
            params.append(StringUtils.abbr(StringUtils.endsWithIgnoreCase(param.getKey(), "password") ? "" : paramValue, 100));
        }
        return params.toString();
    }

    protected static User getLoginUser() {
        Subject subject = getSubject();
        User user = (User) subject.getPrincipal();
        if (null == user || org.springframework.util.StringUtils.isEmpty(user.getId())) {
            return null;
        }
        return user;
    }

    protected static Subject getSubject() {
        Subject subject = SecurityUtils.getSubject();
       if (null == subject) {
           return null;
        }
        return subject;
    }


}
