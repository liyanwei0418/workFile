package cn.gukeer.platform.service;

import cn.gukeer.platform.persistence.entity.Menu;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by conn on 2016/8/8.
 */
public interface MenuService {

    PageInfo<Menu> findAllList(int pageNum, int pageSize);

    List<Menu> findAllList();

    Menu findMenuById(String id);

    int updateMenu(Menu menu);

    int insertMenu(Menu menu);

    List<Menu> selectMenuByBelong(String belong);
}
