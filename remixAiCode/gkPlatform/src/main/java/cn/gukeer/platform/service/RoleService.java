package cn.gukeer.platform.service;

import cn.gukeer.platform.persistence.entity.Role;
import cn.gukeer.platform.persistence.entity.RoleMenu;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by conn on 2016/8/8.
 */
public interface RoleService {

    List<Role> findRoleByAppId(String appId);

    List<Role> findNotHaveList(String appId);

    List<Role> findAllList();

    List<Role> findRoleListByName(String name);

    PageInfo<Role> findAllList(int pageNum, int pageSize);

    Role findRoleById(String id);

    int updateRole(Role role);

    int insertRole(Role role);

    List<RoleMenu> findRoleMenuList(String roleId);

    int assignRoleMenu(String roleId, String menuId);

    int deleteRoleMenu(String roleId, String menuId);

    int deleteRoleMenuByRoleId(String roleId);

    int saveRoleMenu(RoleMenu roleMenu);

    String insertRoleBackId(Role role);

}
