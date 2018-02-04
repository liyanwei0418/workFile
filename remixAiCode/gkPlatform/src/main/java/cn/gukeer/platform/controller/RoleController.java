package cn.gukeer.platform.controller;

import cn.gukeer.common.controller.BasicController;
import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.common.utils.PrimaryKey;
import cn.gukeer.platform.persistence.entity.Menu;
import cn.gukeer.platform.persistence.entity.Role;
import cn.gukeer.platform.persistence.entity.RoleMenu;
import cn.gukeer.platform.persistence.entity.User;
import cn.gukeer.platform.service.MenuService;
import cn.gukeer.platform.service.RoleService;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by conn on 2016/8/1.
 * role
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController extends BasicController {

    @Autowired
    RoleService roleService;

    @Autowired
    MenuService menuService;

    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, Model model) {

        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);

        PageInfo<Role> pageInfo = roleService.findAllList(pageNum, pageSize);
        List<Role> roleList = pageInfo.getList();

        model.addAttribute("roleList", roleList);

        return "role/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Model model) {
        return "role/edit";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit")
    public String edit(HttpServletRequest request, Model model) {

        String id = getParamVal(request, "id");
        Role role = roleService.findRoleById(id);
        model.addAttribute("role", role);

        return "role/edit";
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultEntity update(HttpServletRequest request, Model model) {

        String id = getParamVal(request, "id");
        String name = getParamVal(request, "name");
        String remarks = getParamVal(request, "remarks");

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        Role role = new Role();
        role.setId(id);
        role.setName(name);
        role.setRemarks(remarks);

        if (StringUtil.isEmpty(id)) {
            role.setId(PrimaryKey.get());
            role.setCreateBy(user.getId());
            role.setCreateDate(System.currentTimeMillis());
            roleService.insertRole(role);
        } else {
            role.setUpdateBy(user.getId());
            role.setUpdateDate(System.currentTimeMillis());
            roleService.updateRole(role);
        }

        return ResultEntity.newResultEntity();
    }

    @RequestMapping(value = "/{roleId}/assign")
    public String assignMenu(HttpServletRequest request, Model model, @PathVariable String roleId) {

        Role role = roleService.findRoleById(roleId);

        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);

        PageInfo<Menu> pageInfo = menuService.findAllList(pageNum, pageSize);
        List<Menu> menuList = pageInfo.getList();

        List<RoleMenu> roleMenuList = roleService.findRoleMenuList(roleId);

        model.addAttribute("role", role);
        model.addAttribute("menuList", menuList);
        model.addAttribute("roleMenuList", roleMenuList);

        return "role/assignMenu";
    }

    @ResponseBody
    @RequestMapping(value = "/doAssign", method = RequestMethod.POST)
    public ResultEntity doAssign(HttpServletRequest request, Model model) {

        String roleId = getParamVal(request, "roleId");
        String addIds = getParamVal(request, "addIds");
        String delIds = getParamVal(request, "delIds");

        if (!StringUtils.isEmpty(addIds)) {
            String menuIdArr[] = addIds.split(",");
            for (String menuId : menuIdArr) {
                String _rid = roleId;
                String _mid = menuId;
                if (_rid != "" && _mid != "") {
                    roleService.assignRoleMenu(_rid, _mid);
                }

            }
        }

        if (!StringUtils.isEmpty(delIds)) {
            String menuIdArr[] = delIds.split(",");
            for (String menuId : menuIdArr) {
                String _rid = roleId;
                String _mid = menuId;
                if (_rid != "" && _mid != "") {
                    roleService.deleteRoleMenu(_rid, _mid);
                }
            }
        }

        return ResultEntity.newResultEntity();
    }

}
