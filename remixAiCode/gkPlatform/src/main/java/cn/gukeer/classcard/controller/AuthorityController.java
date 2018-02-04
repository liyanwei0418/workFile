package cn.gukeer.classcard.controller;

import cn.gukeer.classcard.service.*;
import cn.gukeer.common.controller.BasicController;
import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.common.tld.GukeerStringUtil;
import cn.gukeer.platform.persistence.dao.A_GradeClassMapper;
import cn.gukeer.platform.persistence.dao.ClassRoomMapper;
import cn.gukeer.platform.persistence.dao.RefClassRoomMapper;
import cn.gukeer.platform.persistence.entity.Role;
import cn.gukeer.platform.persistence.entity.Teacher;
import cn.gukeer.platform.persistence.entity.User;
import cn.gukeer.platform.persistence.entity.UserRole;
import cn.gukeer.platform.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alpha on 18-1-16.
 */
@Controller
@RequestMapping(value = "/classcard")
public class AuthorityController extends BasicController {

    @Autowired
    TeacherService teacherService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    //角色分配
    @RequiresPermissions("classCard:role:view")
    @RequestMapping(value = "/role/index")
    public String RoleIndex(HttpServletRequest request, Model model) {
        int pageSize = getPageSize(request);
        int pageNum = getPageNum(request);
        String roleId = getParamVal(request, "roleId");
        User loginUser = getLoginUser();
        HttpSession session = request.getSession();
        Object appId = session.getAttribute("appId");

        List<Role> roleList = new ArrayList();
        if (!GukeerStringUtil.isNullOrEmpty(appId))
            roleList = roleService.findRoleByAppId(appId.toString());

        List<String> idList = new ArrayList<String>();
        for (Role role : roleList) {
            idList.add(role.getId());
        }

        List<UserRole> userRoleList = new ArrayList<UserRole>();
        if (idList.size() > 0) {
            Role role = new Role();
            role.setId(roleId);
            userRoleList = userService.findUserRoleByCriteria(idList, role, loginUser.getSchoolId());
        }
        if (userRoleList.size() > 0) {
            List<String> userIds = new ArrayList<String>();
            for (UserRole userRole : userRoleList) {
                userIds.add(userRole.getUserId());
            }
            List<User> userList = userService.selectUsersInIds(userIds);
            List<Teacher> teacherList = new ArrayList<Teacher>();
            List<String> teacherIdList = new ArrayList<>();
            for (User user : userList) {
                teacherIdList.add(user.getRefId());
            }
            PageHelper.startPage(pageNum, pageSize);
            teacherList = teacherService.selectBatchTeachers(teacherIdList, loginUser.getSchoolId());
            PageInfo<Teacher> pageInfo = new PageInfo<Teacher>(teacherList);
            model.addAttribute("teacherList", teacherList);
            model.addAttribute("pageInfo", pageInfo);
        }
        if ("".equals(roleId) && roleList.size() > 0) {
            roleId = roleList.get(0).getId().toString();
        }
        model.addAttribute("roleList", roleList);
        model.addAttribute("currentRole", roleId);
        return "classCard/role/index";
    }

    //角色分配-添加用户-弹出页面
    @RequestMapping(value = "/roleuser/add")
    public String addUser(HttpServletRequest request, Model model) {
        String roleId = getParamVal(request, "roleId");
        List<Teacher> teacherList = teacherService.findHaveAccountTeacher(getLoginUser().getSchoolId());
        model.addAttribute("teacherList", new Gson().toJson(teacherList));
        model.addAttribute("roleId", roleId);
        return "classCard/role/roleUserAdd";
    }

    //角色分配-添加用户保存
    @ResponseBody
    @RequestMapping(value = "/roleuser/save")
    public ResultEntity addUserSave(HttpServletRequest request) {
        String teacherId = getParamVal(request, "teacherId");
        String roleId = getParamVal(request, "roleId");

        List<User> userList = userService.getTeacherByRefId(teacherId);
        List<UserRole> userRoleList = new ArrayList<>();
        String schoolId = getLoginUser().getSchoolId();
        for (User user : userList) {
            List<UserRole> ur = userService.findUserRoleByUserIdAndRoleId(user.getId(), roleId);
            if (ur != null && ur.size() > 0) {
                return ResultEntity.newErrEntity("该用户已是管理员");
            }
            UserRole userRole = new UserRole();
            userRole.setSchoolId(schoolId);
            userRole.setUserId(user.getId());
            userRole.setRoleId(roleId);
            userRoleList.add(userRole);
        }
        ResultEntity entity = ResultEntity.newResultEntity();
        if (userRoleList.size() <= 0) {
            entity.setMsg("分配失败");
        }
        try {
            userService.saveUserRoleBatch(userRoleList);
            entity.setMsg("分配成功");
            entity.setCode(200);
        } catch (Exception e) {
            e.printStackTrace();
            entity.setMsg("分配失败");
        }
        return entity;
    }

    /**
     * 班牌角色分配 删除用户
     *
     * @param request
     */
    @ResponseBody
    @RequestMapping(value = "/roleUser/delete", method = RequestMethod.POST)
    public ResultEntity deleteRoleUser(HttpServletRequest request) {
        String userRefId = getParamVal(request, "userId");
        String roled = getParamVal(request, "roleId");
        String userId = userService.getTeacherByRefId(userRefId).get(0).getId();

        UserRole userRole = new UserRole();
        userRole.setSchoolId(getLoginUser().getSchoolId());
        userRole.setRoleId(roled);
        userRole.setUserId(userId);
        userService.deleteUserRole(userRole);
        return  ResultEntity.newResultEntity();
    }

}
