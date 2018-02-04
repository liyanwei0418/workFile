package cn.gukeer.platform.service.impl;

import cn.gukeer.common.service.BasicService;
import cn.gukeer.platform.persistence.dao.A_MenuExtensionMapper;
import cn.gukeer.platform.persistence.dao.MenuMapper;
import cn.gukeer.platform.persistence.entity.Menu;
import cn.gukeer.platform.persistence.entity.MenuExample;
import cn.gukeer.platform.service.MenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by conn on 2016/8/8.
 */
@Service
public class MenuServiceImpl extends BasicService implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    A_MenuExtensionMapper a_menuExtensionMapper;

    @Override
    public PageInfo<Menu> findAllList(int pageNum, int pageSize) {

        MenuExample example = new MenuExample();
        example.createCriteria().andDelFlagEqualTo(0);
        example.setOrderByClause("create_date desc");

        PageHelper.startPage(pageNum, pageSize);
        List<Menu> list = menuMapper.selectByExample(example);

        PageInfo<Menu> pageInfo = new PageInfo<Menu>(list);

        return pageInfo;
    }

    @Override
    public List<Menu> findAllList() {
        MenuExample example = new MenuExample();
        example.createCriteria().andDelFlagEqualTo(0);
        example.setOrderByClause("create_date desc");
        List<Menu> list = menuMapper.selectByExample(example);

        return list;
    }

    @Override
    public Menu findMenuById(String id) {

        Menu menu = menuMapper.selectByPrimaryKey(id);
        return menu;
    }

    @Override
    public int updateMenu(Menu menu) {
        MenuExample example = new MenuExample();
        example.createCriteria().andIdEqualTo(menu.getId());
        int count = menuMapper.updateByExampleSelective(menu, example);
        return count;
    }

    @Override
    public int insertMenu(Menu menu) {
        int count = menuMapper.insertSelective(menu);
        return count;
    }

    @Override
    public List<Menu> selectMenuByBelong(String belong) {
        MenuExample example = new MenuExample();
        example.createCriteria().andBelongEqualTo(belong);

        List<Menu> res = menuMapper.selectByExample(example);
        return res;
    }
}
