package cn.gukeer.platform.controller;

import cn.gukeer.common.controller.BasicController;
import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.common.utils.PrimaryKey;
import cn.gukeer.platform.persistence.dao.MenuMapper;
import cn.gukeer.platform.persistence.entity.App;
import cn.gukeer.platform.persistence.entity.Menu;
import cn.gukeer.platform.persistence.entity.MenuExample;
import cn.gukeer.platform.persistence.entity.User;
import cn.gukeer.platform.service.AppService;
import cn.gukeer.platform.service.MenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by conn on 2016/8/1.
 * menu
 */
@Controller
@RequestMapping(value = "/menu")
public class MenuController extends BasicController{

    @Autowired
    MenuService menuService;
    @Autowired
    MenuMapper menuMapper;

    @Autowired
    AppService appService;

    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, Model model){
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        PageHelper.startPage(pageNum,pageSize);
        String menuName=getParamVal(request,"menuName");
        MenuExample example=new MenuExample();
        MenuExample.Criteria criteria=example.createCriteria();
        example.setOrderByClause("create_date desc");
        criteria.andDelFlagEqualTo(0);

        try {
            menuName=java.net.URLDecoder.decode(menuName, "UTF-8");//解决非post访问的中文乱码问题。
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if(!StringUtils.isEmpty(menuName)){
            criteria.andNameLike("%"+menuName+"%");
        }

        List<Menu> menuList=menuMapper.selectByExample(example);
        PageInfo<Menu> pageInfo=new PageInfo<Menu>(menuList);
        model.addAttribute("menuList", menuList);
        model.addAttribute("pageInfo", pageInfo);

        return "menu/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Model model) {

        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);

        PageInfo<Menu> pageInfo = menuService.findAllList(pageNum, pageSize);
        List<Menu> menuList = pageInfo.getList();

        PageInfo<App> appPageInfo = appService.findAllList(0, 0);

        model.addAttribute("menuList", menuList);
        model.addAttribute("appList", appPageInfo.getList());

        return "menu/edit";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit")
    public String edit(HttpServletRequest request, Model model) {

        String id =getParamVal(request, "id");

        PageInfo<App> appPageInfo = appService.findAllList(0, 0);

        Menu menu = menuService.findMenuById(id);
        model.addAttribute("menu", menu);
        model.addAttribute("appList", appPageInfo.getList());

        return "menu/edit";
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultEntity update(HttpServletRequest request, Model model) {

        String id =getParamVal(request, "id");
        String name =getParamVal(request, "name");
        String href =getParamVal(request, "href");
        String permission =getParamVal(request, "permission");
        String remarks =getParamVal(request, "remarks");
        String belong = getParamVal(request, "belong");

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        Menu menu = new Menu();
        menu.setId(id);
        menu.setName(name);
        menu.setHref(href);
        menu.setPermission(permission);
        menu.setRemarks(remarks);
        menu.setBelong(belong);
        if (StringUtil.isEmpty(id)) {
            menu.setId(PrimaryKey.get());
            menu.setCreateBy(user.getId());
            menu.setCreateDate(System.currentTimeMillis());
            menuService.insertMenu(menu);
        } else {
            menu.setUpdateBy(user.getId());
            menu.setUpdateDate(System.currentTimeMillis());
            menuService.updateMenu(menu);
        }

        return ResultEntity.newResultEntity();
    }

    @RequestMapping(value = "/addSub")
    public String addSub(HttpServletRequest request, Model model) {

        String parentId =getParamVal(request, "parentId");
        String subId =getParamVal(request, "subId");

        String _pid = parentId;
        Menu parent = menuService.findMenuById(_pid);

        String _sid = subId;
        Menu sub = menuService.findMenuById(_sid);

        model.addAttribute("parent", parent);
        model.addAttribute("sub", sub);

        return "menu/addSub";
    }

    @ResponseBody
    @RequestMapping(value = "/saveSub", method = RequestMethod.POST)
    public ResultEntity addSubMenu(HttpServletRequest request) {

        // 父菜单 ID
        String menuId =getParamVal(request, "menuId");
        // 子菜单 ID
        String subId =getParamVal(request, "subId");
        String name =getParamVal(request, "name");
        String href =getParamVal(request, "href");
        String permission =getParamVal(request, "permission");
        String remarks =getParamVal(request, "remarks");

        String _menuId = menuId;
        Menu parent = menuService.findMenuById(_menuId);

        String _subId = subId;
        Menu sub = new Menu();
        sub.setName(name);
        sub.setHref(href);
        sub.setPermission(permission);
        sub.setRemarks(remarks);

        if (StringUtil.isNotEmpty(parent.getBelong()))
            sub.setBelong(parent.getBelong());


        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        if (_subId == null || _subId == "") { // insert
            sub.setId(PrimaryKey.get());
            sub.setParentId(parent.getId());
            sub.setCreateBy(user.getId());
            sub.setCreateDate(System.currentTimeMillis());
            menuService.insertMenu(sub);
        } else { // update
            sub.setParentId(parent.getId());
            sub.setId(_subId);
            sub.setUpdateBy(user.getId());
            sub.setUpdateDate(System.currentTimeMillis());
            menuService.updateMenu(sub);
        }

        return ResultEntity.newResultEntity();
    }
}
