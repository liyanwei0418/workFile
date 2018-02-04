package cn.gukeer.common.utils;

import cn.gukeer.common.security.AESencryptor;
import cn.gukeer.platform.persistence.entity.User;
import cn.gukeer.platform.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by connli on 16/4/8.
 */
public class LoginUtil {
    private static Logger logger = LoggerFactory.getLogger(LoginUtil.class);

    private final static String SESSION_KEY_USER = "session_login_user";

    public static void handleLoginSession(HttpServletRequest request, HttpServletResponse response, User user) {
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(60 * 10); //单位是秒 秒x分钟x小时
        session.setAttribute(SESSION_KEY_USER, user);

        Cookie cookie;
        if (user != null) {
            cookie = new Cookie("yoyomvploginuser", user.getUsername());
            cookie.setMaxAge(24 * 60 * 60 * 365);
        } else {
            cookie = new Cookie("yoyomvploginuser", "");
            cookie.setMaxAge(0);//不设置时间的话，无法存入本地COOKIE
        }
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static void handleLogoutSession(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        Cookie cookie = new Cookie("yoyomvploginuser", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static User getLoginUser(HttpServletRequest request) {
        return (User) (request.getSession(false) != null ? request.getSession().getAttribute(SESSION_KEY_USER) : null);
    }

    public static boolean isLoginUser(HttpServletRequest request) {
        return getLoginUser(request) != null;
    }

    public static void updateSessionUser(HttpServletRequest request, User user) {
        if (isLoginUser(request)) {
            HttpSession session = request.getSession(true);
            session.setAttribute(SESSION_KEY_USER, user);
        }
    }

    private static <T> T getSessionObject(HttpServletRequest request, String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        return request.getSession(false) != null ? (T) request.getSession(false).getAttribute(key) : null;
    }

    // login verify
    public static String parsePwd1(String origin, String timestamp) {
        byte[] bytes = Base64Utils.decode(origin.getBytes());
        String ori = new String(bytes);
        if (StringUtils.isEmpty(ori)) {
            return null;
        } else {
            if (ori.contains("_")) {
                String t = ori.split("_")[1];
                String p = ori.split("_")[0];
                if (t.equals(timestamp)) {
                    return p;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
    }

    // pwd_timestamp base decode
    public static Map<String, String> parseApiRequest(HttpServletRequest request, UserService dao) {

        String password = null;
        String account = null;
        Map<String, String> map = null;
        try {
            String token = request.getHeader("token");
            account = request.getHeader("a");
            String timestamp = request.getHeader("t");

            if (StringUtils.isEmpty(token) || StringUtils.isEmpty(account) ||
                    StringUtils.isEmpty(timestamp)) {
                return map;
            }

            try {
                String oriStr = new String(Base64Utils.decodeFromString(token));
                String base64Str = AESencryptor.decryptCBCPKCS5Padding(oriStr);
                String oriToken = new String(Base64Utils.decodeFromString(base64Str));

                String infos[] = oriToken.split("-");

                account = infos[0];
                password = infos[1];

                logger.info("token: {}", token);

                if (null == dao.getByAccountAndPwd(account, password)) {
                    return map;
                }
            } catch (Exception e) {
                return map;
            }

            map = new HashMap<String, String>();
            map.put("account", account);
            map.put("pwd", password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

}
