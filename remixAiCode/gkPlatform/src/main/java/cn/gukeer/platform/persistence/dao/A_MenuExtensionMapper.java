package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.modelView.MenuView;

import java.util.List;

/**
 * Created by conn on 2016/8/21.
 */
public interface A_MenuExtensionMapper {
    List<MenuView> selectMenusByRoleId(String roleId);
}
