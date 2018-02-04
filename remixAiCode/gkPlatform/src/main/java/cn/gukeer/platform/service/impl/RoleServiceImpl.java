package cn.gukeer.platform.service.impl;

import cn.gukeer.common.service.BasicService;
import cn.gukeer.platform.persistence.dao.A_RoleExtensionMapper;

import cn.gukeer.platform.persistence.dao.RoleMapper;
import cn.gukeer.platform.persistence.dao.RoleMenuMapper;
import cn.gukeer.platform.persistence.entity.Role;
import cn.gukeer.platform.persistence.entity.RoleExample;
import cn.gukeer.platform.persistence.entity.RoleMenu;
import cn.gukeer.platform.persistence.entity.RoleMenuExample;
import cn.gukeer.platform.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;

/**
 * Created by conn on 2016/8/8.
 */
@Service
public class RoleServiceImpl extends BasicService implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RoleMenuMapper roleMenuMapper;

    @Autowired
    A_RoleExtensionMapper roleExtensionMapper;

    @Override
    public List<Role> findRoleByAppId(String appId) {

        return roleExtensionMapper.findRoleByApp(appId);
    }

    @Override
    public List<Role> findNotHaveList(String appId) {
        return null;
    }


    @Override
    public List<Role> findAllList() {
        RoleExample example = new RoleExample();
        example.createCriteria().andDelFlagEqualTo(0);
        example.setOrderByClause("create_date desc");
        return roleMapper.selectByExample(example);
    }

    @Override
    public List<Role> findRoleListByName(String name) {
        RoleExample example=new RoleExample();
        RoleExample.Criteria criteria=example.createCriteria();
        criteria.andDelFlagEqualTo(0);

        if(!StringUtils.isEmpty(name)){
            criteria.andNameLike("%"+name+"%");
        }
        return roleMapper.selectByExample(example);
    }

    @Override
    public PageInfo<Role> findAllList(int pageNum, int pageSize) {

        RoleExample example = new RoleExample();
        example.createCriteria().andDelFlagEqualTo(0);
        example.setOrderByClause("create_date desc");

        PageHelper.startPage(pageNum, pageSize);
        List<Role> list = roleMapper.selectByExample(example);
        PageInfo<Role> pageInfo = new PageInfo<Role>(list);

        return pageInfo;
    }

    @Override
    public Role findRoleById(String id) {

        Role role = roleMapper.selectByPrimaryKey(id);
        return role;
    }

    @Override
    public int updateRole(Role role) {
        RoleExample example = new RoleExample();
        example.createCriteria().andIdEqualTo(role.getId());
        int count = roleMapper.updateByExampleSelective(role, example);
        return count;
    }

    @Override
    public int insertRole(Role role) {
        int count = roleMapper.insertSelective(role);
        return count;
    }

    @Override
    public List<RoleMenu> findRoleMenuList(String roleId) {

        RoleMenuExample example = new RoleMenuExample();
        example.createCriteria().andRoleIdEqualTo(roleId);

        List<RoleMenu> roleMenuList = roleMenuMapper.selectByExample(example);

        return roleMenuList;
    }


    public RoleMenu findRoleMenu(String roleId, String menuId) {

        RoleMenuExample example = new RoleMenuExample();
        example.createCriteria().andRoleIdEqualTo(roleId).andMenuIdEqualTo(menuId);
        List<RoleMenu> roleMenus = roleMenuMapper.selectByExample(example);

        RoleMenu roleMenu = null;
        if (null != roleMenus && roleMenus.size() > 0) {
            roleMenu = roleMenus.get(0);
        }
        return roleMenu;
    }

    /**
     * 添加角色对于的menu
     *
     * @param roleId
     * @param menuId
     * @return
     */
    @Override
    public int assignRoleMenu(String roleId, String menuId) {

        RoleMenu roleMenu = findRoleMenu(roleId, menuId);
        if (null != roleMenu) {
            return 0;
        }
        roleMenu = new RoleMenu();
        roleMenu.setRoleId(roleId);
        roleMenu.setMenuId(menuId);
        int count = roleMenuMapper.insert(roleMenu);
        return count;
    }

    /**
     * 删除角色对应的menu
     *
     * @param roleId
     * @param menuId
     * @return
     */
    @Override
    public int deleteRoleMenu(String roleId, String menuId) {
        RoleMenuExample example = new RoleMenuExample();
        example.createCriteria().andRoleIdEqualTo(roleId).andMenuIdEqualTo(menuId);
        int count = roleMenuMapper.deleteByExample(example);
        return count;
    }

    @Override
    public int deleteRoleMenuByRoleId(String roleId) {
        RoleMenuExample example = new RoleMenuExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        int count = roleMenuMapper.deleteByExample(example);
        return count;
    }

    @Override
	public int saveRoleMenu(RoleMenu roleMenu) {
		return roleMenuMapper.insertSelective(roleMenu);
	}

    @Override
    public String insertRoleBackId(Role role) {
        roleMapper.insertSelective(role);
        return role.getId();
    }

}
