package cn.gukeer.common.shiro;

import cn.gukeer.platform.modelView.MenuView;
import cn.gukeer.platform.modelView.RoleView;
import cn.gukeer.platform.persistence.entity.User;
import cn.gukeer.platform.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by conn on 2016/7/29.
 */
public class ShiroAuthRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;


    public void clearCached(){
        PrincipalCollection principal = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principal);
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        User user = (User) super.getAvailablePrincipal(principalCollection);

        SimpleAuthorizationInfo simpleAuthorInfo = null;
        if (null != user) {

            simpleAuthorInfo = new SimpleAuthorizationInfo();

            List<String> permissions = new ArrayList<String>();

            List<RoleView> roleViews = userService.selectRoleViewByUserId(user.getId());

            boolean flag = userService.isAreaAdmin(user.getSchoolId());//判断是否是区级人员
            if (flag)
                simpleAuthorInfo.addRole("area");//区平台人员
            else
                simpleAuthorInfo.addRole("common");//普通校级人员


            for (RoleView roleView : roleViews) {
                String role = roleView.getRoleIdentify();
                if (!StringUtils.isEmpty(role)) {
                    simpleAuthorInfo.addRole(role);

                    List<MenuView> menuViews = userService.selectMenusByRoleId(roleView.getId());

                    for (MenuView menuView : menuViews) {
                        String permission = menuView.getPermission();
                        if (!StringUtils.isEmpty(permission)) {
                            permissions.add(permission);
                        }
                    }
                }
            }
            simpleAuthorInfo.addStringPermissions(permissions);
        } else {
            throw new AuthorizationException();
        }

        return simpleAuthorInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        String pwd = String.valueOf(usernamePasswordToken.getPassword());
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        //User user = userService.getByAccount(username, pwd);
        User user = userService.getUserByAccount(username);
        if (user != null) {
            if ( !user.getPassword().equals(pwd)) {
                throw new IncorrectCredentialsException();
            }
            if (null != user && user.getDelFlag() == 1) {
                throw new LockedAccountException();
            }
            return new SimpleAuthenticationInfo(user,
                    user.getPassword(), getName());
        }
        return null;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
