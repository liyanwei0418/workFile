package cn.gukeer.platform.controller;

import cn.gukeer.common.controller.BasicController;
import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.common.security.AESencryptor;
import cn.gukeer.common.tld.GukeerStringUtil;
import cn.gukeer.common.utils.AES;
import cn.gukeer.common.utils.DateUtils;
import cn.gukeer.common.utils.PropertiesUtil;
import cn.gukeer.common.utils.SnsUtil;
import cn.gukeer.common.utils.syncdata.MD5Util;
import cn.gukeer.platform.common.UserRoleType;
import cn.gukeer.platform.persistence.entity.*;
import cn.gukeer.platform.service.AppService;
import cn.gukeer.platform.service.MenuService;
import cn.gukeer.platform.service.SchoolService;
import cn.gukeer.platform.service.UserService;
import cn.gukeer.sync.service.pull.QuerySchedule;
import com.github.pagehelper.StringUtil;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Properties;


@Controller
public class LoginController extends BasicController {

    @Autowired
    UserService userService;

    @Autowired
    SchoolService schoolService;

    @Autowired
    AppService appService;

    @Autowired
    QuerySchedule querySchedule;

    @Autowired
    MenuService menuService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin(HttpServletRequest request,Model model) {
        StringBuffer tempPathString = request.getRequestURL();
        Integer start = "http://".length();
        String _end = "";
        try {
            Properties p = PropertiesUtil.getProperties("/db.properties");
            _end = p.getProperty("develop_path");
        } catch (Exception e1) {
            _end = "";
        }
        if (StringUtil.isEmpty(_end)) {
            _end = ".gukeyun.cn";
        }

        Integer end = tempPathString.indexOf(_end);
        String schoolUrl = "";
        if (end > 0 && end > start) {
            schoolUrl = tempPathString.substring(start, end);
            School school = schoolService.selectSchoolByWholeUrl(schoolUrl);
            if (school != null) {
                model.addAttribute("logo", school.getLogo());
                model.addAttribute("bgPicture", school.getBgPicture());
                model.addAttribute("shortFlag", school.getShortFlag());
            }
        }

        List<Config> config = schoolService.selectConfigByType("bottomText");
        if (config.size() > 0) {
            model.addAttribute("bottomText", config.get(0).getParamValue());
        }
        return "login/login";
    }

    @ResponseBody
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public ResultEntity login(HttpServletRequest request, HttpServletResponse response, Model model) {
        String username = getParamVal(request, "username");
        String password = getParamVal(request, "password");
        String remember = getParamVal(request, "remember");
        String shortFlag = getParamVal(request, "shortFlag");

        if (!username.equalsIgnoreCase("root") && !shortFlag.equals("@")) {
            username = username + shortFlag;
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username.toLowerCase(), AESencryptor.encryptCBCPKCS5Padding(password));
        Subject loginUser = SecurityUtils.getSubject();
        String errmsg = null;
        if ("1".equals(remember)) {
            token.setRememberMe(true);
        }
        try {
            loginUser.login(token);

        } catch (UnknownAccountException uae) {
            errmsg = "账户不存在";
        } catch (IncorrectCredentialsException ice) {
            errmsg = "用户名或者密码错误";
        } catch (LockedAccountException lae) {
            errmsg = "账户已锁定,暂时不能登录";
        } catch (ExcessiveAttemptsException eae) {
            errmsg = "错误次数过多,暂时不能登录";
        } catch (AuthenticationException ae) {
            ae.printStackTrace();
        }
        if (!loginUser.isAuthenticated()) {
            token.clear();
            if (StringUtils.isEmpty(errmsg)) {
                errmsg = "验证出错,请联系管理员";
            }
            return ResultEntity.newErrEntity(errmsg);
        }

        //WebUtils.getSavedRequest(request);
        String url = "/";
        if (loginUser.isAuthenticated()) {
            if (loginUser.hasRole(UserRoleType.ROLE_ROOT)) {
                url = "school/index";
            } else if (loginUser.hasRole(UserRoleType.ROLE_ADMIN)) {
                url = "admin/index";
            } else {
                url = "home/index";
            }
        }

        // 登录成功标识
        HttpSession session = request.getSession();
        session.setAttribute("loginUser", getLoginUser());
        return ResultEntity.newResultEntity(url);
    }



    //外链单点登录到云平台 username,from,sign
    @RequestMapping(value = "/third/index", method = RequestMethod.GET)
    public String banPaiIndex(HttpServletRequest request, HttpServletResponse response) {
        try {
            String username = getParamVal(request, "username");
            String from = getParamVal(request,"from");
            String sign = getParamVal(request,"sign");

            Properties p = PropertiesUtil.getProperties("/api.properties");
            String key = p.getProperty("platform.login.key");

            if (!MD5Util.go(username+from+key).equals(sign)){
                return "500";//加密认证失败，单点登录失败
            }

            User user = userService.getUserByAccount(username);
            if (GukeerStringUtil.isNullOrEmpty(user)){
                return "500";//用户不存在，单点登录失败
            }

            UsernamePasswordToken token = new UsernamePasswordToken(username.toLowerCase(), user.getPassword());
            Subject loginUser = SecurityUtils.getSubject();
            try {
                loginUser.login(token);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 登录成功标识
        HttpSession session = request.getSession();
        session.setAttribute("loginUser", getLoginUser());

        //重定向的方式才会去重新加载资源，直接跳转index.jsp页面无数据
        return "redirect:/home/index";
    }


    //根据开发者来判断(登录到各个应用)
    @ResponseBody
    @RequestMapping(value = "/third/party/page")
    public ResultEntity index(HttpServletRequest request) throws IOException {
        String appId = getParamVal(request, "appId");
        String fullPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        String url = "";
        User user = getLoginUser();
        Subject subject = getSubject();

        Properties prop = PropertiesUtil.getProperties("api.properties");
        App app = appService.findAppById(appId);
        if (!GukeerStringUtil.isNullOrEmpty(app)) {


            if (StringUtil.isEmpty(app.getWebUrl()))
                return ResultEntity.newErrEntity("无权限");

            String developers = app.getDevelopers();
            if ("教育软件有限公司".equals(developers)) {
                String key = prop.getProperty("lezhix.app.key");
                String pKey = MD5Util.go(user.getRefId() + DateUtils.getCurrentTime("yyyy-MM-dd") + key);
                url += app.getWebUrl() + "&puid=" + user.getRefId() + "&pkey=" + pKey;

                return ResultEntity.newResultEntity(url);
            }

            if ("sns".equals(developers)) {
                String param = AES.AES_Encrypt(SnsUtil.SNS_KEY, getLoginUser().getUsername() + "," + fullPath + "/file/pic/show?picPath=" + getLoginUser().getPhotoUrl());
                url += app.getWebUrl() + "?param=" + param;
                url = url.replaceAll("\\+", "%2B");

                return ResultEntity.newResultEntity(url);
            }

            if ("ring".equals(developers)) {
                String from = "platform";
                String username = user.getUsername();
                int r = RandomUtils.nextInt(6);
                Long timeStamp = System.currentTimeMillis();
                String key = prop.getProperty("smartRing.key");
                String sign = MD5Util.go(from + username + timeStamp + r + key);
                String param = "?from=" + from + "&user=" + user.getUsername() + "&r=" + r + "&timestamp=" + timeStamp + "&sign=" + sign;
                url += app.getWebUrl() + param;

                return ResultEntity.newResultEntity(url);
            }


            //判断当前用户是否有该应用的访问权限
            List<Menu> menuList = menuService.selectMenuByBelong(appId);
            boolean flag = false;
            for (Menu menu : menuList) {
                if (subject.isPermitted(menu.getPermission()))
                    flag = true;
            }

            if ("教育云平台".equals(developers)) {
                if (flag) {
                    url = fullPath + "/" + app.getWebUrl();
                    return ResultEntity.newResultEntity(url + "?appId=" + appId);
                } else {
                    return ResultEntity.newErrEntity("无权限");
                }
            }

        }

        return ResultEntity.newResultEntity(url);
    }




}
