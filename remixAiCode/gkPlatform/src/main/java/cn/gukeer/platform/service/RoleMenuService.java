package cn.gukeer.platform.service;

import cn.gukeer.platform.persistence.entity.RoleMenu;

public interface RoleMenuService {

	 int deleteByRoleId(String roleId);

	 int insert(RoleMenu roleMenu);	 
}
