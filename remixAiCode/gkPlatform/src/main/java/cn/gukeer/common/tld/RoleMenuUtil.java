package cn.gukeer.common.tld;

import cn.gukeer.common.utils.NumberConvertUtil;
import cn.gukeer.platform.persistence.entity.RoleMenu;

import java.util.List;

/**
 * Created by conn on 2016/8/8.
 */
public class RoleMenuUtil {

    public static Boolean roleMenuContains(String roleId, String menuId, List<RoleMenu> list) {
        if (null == list || list.size() == 0) return false;

        String _rid = roleId;
        String _mid = menuId;
        for (RoleMenu rm : list) {
            if (rm.getRoleId() == _rid && rm.getMenuId() == _mid) {
                return true;
            }
        }
        return false;
    }
}
