package cn.gukeer.platform.modelView;

import cn.gukeer.platform.persistence.entity.App;
import cn.gukeer.platform.persistence.entity.Menu;
import cn.gukeer.platform.persistence.entity.User;
import cn.gukeer.platform.persistence.entity.UserRole;

import java.util.List;

/**
 * Created by conn on 2016/8/19.
 */
public class UserView extends User {

    private UserRole userRole;

    private List<Menu> menus;

    private List<App> apps;

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public List<App> getApps() {
        return apps;
    }

    public void setApps(List<App> apps) {
        this.apps = apps;
    }
}
