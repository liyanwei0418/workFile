package cn.gukeer.platform.service.impl;

import cn.gukeer.common.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;

import cn.gukeer.common.utils.NumberConvertUtil;
import cn.gukeer.platform.persistence.dao.RoleMenuMapper;
import cn.gukeer.platform.persistence.entity.Role;
import cn.gukeer.platform.persistence.entity.RoleMenu;
import cn.gukeer.platform.persistence.entity.RoleMenuExample;
import cn.gukeer.platform.service.RoleMenuService;
import org.springframework.stereotype.Service;

@Service
public class RoleMenuServiceImpl  extends BasicService implements RoleMenuService {
    @Autowired
    RoleMenuMapper roleMenuMapper;
    
	@Override
	public int deleteByRoleId(String roleId) {
		RoleMenuExample example=new RoleMenuExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        try {
        	 roleMenuMapper.deleteByExample(example);
      		 return 1;
		} catch (Exception e) {
			 return 0;
		}
      
	}

	@Override
	public int insert(RoleMenu roleMenu) {
		roleMenuMapper.insert(roleMenu);
		return 0;
	}



}
