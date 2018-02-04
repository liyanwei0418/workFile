package cn.gukeer.common.controller;

import cn.gukeer.common.utils.LoggerWrapper;
import cn.gukeer.common.utils.NumberConvertUtil;
import cn.gukeer.platform.common.ProjectConfig;
import cn.gukeer.platform.persistence.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by conn on 2016/8/5.
 */
@Controller
public abstract class BasicController extends LoggerWrapper {

    protected int getPageNum(HttpServletRequest request) {

        int _pageNum = 0;
        if (null == request) return _pageNum;

        String pageNum = getParamVal(request, "pageNum");
        if (StringUtils.isEmpty(pageNum)) return _pageNum;

        _pageNum = NumberConvertUtil.convertS2I(pageNum);

        return _pageNum;
    }

    protected int getPageSize(HttpServletRequest request) {

        int _pageSize = ProjectConfig.DEFAULT_PAGE_SIZE;
        if (null == request) return _pageSize;

        String pageSize = getParamVal(request, "pageSize");
        if (StringUtils.isEmpty(pageSize)) return _pageSize;

        _pageSize = NumberConvertUtil.convertS2I(pageSize);
        if (_pageSize == 0) {
            _pageSize = ProjectConfig.DEFAULT_PAGE_SIZE;
        }

        return _pageSize;
    }


    protected String getParamVal(HttpServletRequest request, String key) {
        return getParamVal(request, key, "");
    }

    protected String getParamVal(HttpServletRequest request, String key, String defaultVal) {

        String val = defaultVal;
        if (null == request) return val;

        String value = request.getParameter(key);
        if (StringUtils.isEmpty(value)) return val;

        return value;
    }

    protected String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_FORWARDED");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_VIA");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("REMOTE_ADDR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    protected User getLoginUser() {
        Subject subject = getSubject();
        //SecurityUtils.getSubject().getSession().setTimeout(1800000);//用户操作间隔大于180000ms才会超时
        User user = (User) subject.getPrincipal();
        if (null == user || StringUtils.isEmpty(user.getId())) {
            throw new UnauthenticatedException("登录超时，请重新登录");
        }
        return user;
    }

    protected Subject getSubject() {
        Subject subject = SecurityUtils.getSubject();
        //SecurityUtils.getSubject().getSession().setTimeout(1800000);//用户操作间隔大于180000ms才会超时
        if ( null == subject ) {
            throw new UnauthenticatedException("登录超时，请重新登录");
        }
        return subject;
    }

    protected Map transToMAP(Map parameterMap){
        // 返回值Map
        Map returnMap = new HashMap();
        Iterator entries = parameterMap.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if(null == valueObj){
                value = "";
            }else if(valueObj instanceof String[]){
                String[] values = (String[])valueObj;
                for(int i=0;i<values.length;i++){
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length()-1);
            }else{
                value = valueObj.toString();
            }
            try {
                value = new String(value.getBytes("iso8859-1"),"utf-8");
            } catch ( Exception e){
                throw new RuntimeException( e);
            }

            returnMap.put(name, value);
        }
        return  returnMap;
    }
}
