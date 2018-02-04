package cn.gukeer.platform.controller;

import cn.gukeer.common.controller.BasicController;
import cn.gukeer.common.tld.GukeerStringUtil;
import cn.gukeer.platform.modelView.AreaSchoolView;
import cn.gukeer.platform.modelView.TeacherView;
import cn.gukeer.platform.persistence.entity.*;
import cn.gukeer.platform.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by zjy on 2016/8/8.
 * teacher
 */
@Controller
@RequestMapping(value = "/area")
public class RenShiAreaController extends BasicController {

    @Autowired
    TeacherService teacherService;

    @Autowired
    SchoolService schoolService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    UserService userService;

    @Autowired
    AppService appService;

    @Autowired
    RoleService roleService;

    //区级人事管理-人员管理页面
    @RequiresPermissions("renShi:area:quji")
    @RequestMapping(value = "/renyuan/index")
    public String rsIndex(HttpServletRequest request, Model model) {
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        String teacherName = getParamVal(request, "teacherName");
        try {
            teacherName = java.net.URLDecoder.decode(teacherName, "UTF-8");//解决非post访问的中文乱码问题。
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        PageHelper.startPage(pageNum, pageSize);
        Map<Object, Object> paramMap = new HashMap<Object, Object>();
        paramMap.put("schoolId", getLoginUser().getSchoolId());
        paramMap.put("teacherId", -1);//-1为查询所有人员
        paramMap.put("teacherName", "%" + teacherName + "%");
        List<Map<String, Object>> teacherList = teacherService.findTeacherViewByParams(paramMap);

        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(teacherList);

        model.addAttribute("teacherName", teacherName);
        model.addAttribute("teacherList", teacherList);
        model.addAttribute("pageInfo", pageInfo);

        if (StringUtil.isNotEmpty(getParamVal(request, "appId")))
            request.getSession().setAttribute("renshiId", getParamVal(request, "appId"));

        return "renShiArea/rsRenYuanManage";
    }

    //区级人事管理-账号管理页面
    @RequestMapping(value = "/account/index")
    public String rsAccountIndex(HttpServletRequest request, Model model) {
        String schoolId = getLoginUser().getSchoolId();

        Map<String, String> param = new HashMap<String, String>();
        param.put("teacherName", getParamVal(request, "teacherName"));
        param.put("pageSizeHave", getParamVal(request, "pageSizeHave"));
        param.put("pageNumHave", getParamVal(request, "pageNumHave"));
        param.put("pageSizeNo", getParamVal(request, "pageSizeNo"));
        param.put("pageNumNo", getParamVal(request, "pageNumNo"));


        String password = "000000";
        List<Config> configs = schoolService.selectConfigByType("defaultPassword");
        if (configs.size() > 0) {
            for (Config config : configs) {
                if (config.getParamKey().equals("teacher")) {
                    password = config.getParamValue();//默认密码
                    break;
                }
            }
        }
        model.addAttribute("password", password);

        Map haveAccountMap = teacherService.getTeacherList(param, true, schoolId);
        model.addAttribute("teacherListHave", haveAccountMap.get("teacherList"));
        model.addAttribute("pageInfoHave", haveAccountMap.get("pageInfo"));

        Map noAccountMap = teacherService.getTeacherList(param, false, schoolId);
        model.addAttribute("teacherListNo", noAccountMap.get("teacherList"));
        model.addAttribute("pageInfoNo", noAccountMap.get("pageInfo"));

        model.addAttribute("teacherName", haveAccountMap.get("teacherName"));
        model.addAttribute("whichPage", request.getParameter("whichPage"));//点击未生成翻页，停留在未生成的页面
        return "renShiArea/rsAccountManage";
    }

    //区级人事管理-部门管理页面
    @RequestMapping(value = "/bumen/index")
    public String rsBuMenIndex(HttpServletRequest request, Model model) {
        String departmentId = getParamVal(request, "departmentId");
        int pageSize = getPageSize(request);
        int pageNum = getPageNum(request);
        User user = getLoginUser();
        String teacherName = null;
        try {
            teacherName = java.net.URLDecoder.decode(getParamVal(request, "teacherName"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Department currentDepart = departmentService.findDepartmentById(departmentId);//当前页面显示的部门
        List<Department> departmentList = departmentService.findAllDepartment(user.getSchoolId());
        StringBuffer builder = new StringBuffer();
        for (Department depart : departmentList) {
            builder.append(depart.getParentId().toString() + ",");
        }

        Teacher _teacher = new Teacher();
        _teacher.setName(teacherName);
        _teacher.setSchoolId(user.getSchoolId());

        PageInfo<Teacher> pageInfo = teacherService.findTeacherByDepartmentId(departmentId, _teacher, pageNum, pageSize);
        List<Teacher> teacherList = pageInfo.getList();
        List<TeacherView> teacherViewList = new ArrayList<TeacherView>();
        for (Teacher teacher : teacherList) {
            TeacherView teacherView = new TeacherView();
            teacherView.setId(teacher.getId());
            teacherView.setName(teacher.getName());
            teacherView.setGender(teacher.getGender());
            teacherView.setNo(teacher.getNo());
            for (int i = 0; i < departmentList.size(); i++) {
                if (teacher.getDepartmentId().equals(departmentList.get(i).getId())) {
                    teacher.setDepartmentId(teacher.getDepartmentId());
                    teacherView.setDepartName(departmentList.get(i).getName());
                    break;
                }
            }
            teacherViewList.add(teacherView);
        }
        model.addAttribute("departmentList", departmentList);
        model.addAttribute("parentList", builder);
        model.addAttribute("teacherName", teacherName);
        model.addAttribute("currentDepart", currentDepart);
        model.addAttribute("teacherList", teacherList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("teacherViewList", teacherViewList);
        return "renShiArea/rsBuMenManage";
    }

    //角色分配
    @RequiresPermissions("renShi:area:role")
    @RequestMapping(value = "/rolefp/index")
    public String rsRoleFpIndex(HttpServletRequest request, Model model) {
        int pageSize = getPageSize(request);
        int pageNum = getPageNum(request);
        String roleId = getParamVal(request, "roleId");

        HttpSession session = request.getSession();
        Object appId = session.getAttribute("renshiId");

        User loginUser = getLoginUser();

        List<Role> roleList = new ArrayList();
        if (!GukeerStringUtil.isNullOrEmpty(appId))
            roleList = roleService.findRoleByAppId(appId.toString());//。。。。人事管理

        List<String> idList = new ArrayList<String>();
        for (Role role : roleList) {
            idList.add(role.getId());
        }

        if (roleId.equals("") && roleList.size() > 0) {
            roleId = roleList.get(0).getId().toString();
        }

        if (roleList.size() > 0) {
                PageInfo<Teacher> pageInfo = userService.findRoleTeacherList(roleId,loginUser.getSchoolId(),pageSize,pageNum);

                model.addAttribute("teacherList", pageInfo.getList());
                model.addAttribute("pageInfo", pageInfo);
        }

        model.addAttribute("roleList", roleList);
        model.addAttribute("currentRole", roleId);
        return "renShiArea/rsRoleFpManage";
    }

    //校级管理-机构管理
    @RequestMapping("/school/index")
    public String schoolManage(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        Map map = new HashMap();
        List schoolIdList = new ArrayList();
        schoolIdList.add(user.getSchoolId());
        map.put("parentId", schoolIdList);

        List<Map<String, Object>> res = schoolService.selectTeacherByParam(map);
        return "校级管理 机构管理页面";
    }

    //校级管理-机构管理-机构详情
    @RequestMapping(value = "/school/detail/{id}", method = RequestMethod.GET)
    public String getSchoolDetail(@PathVariable("id") String id, Model model) {
        return "renShiArea/test";
    }

    //校级管理-人员管理
    @RequiresPermissions("renShi:area:xiao")
    @RequestMapping(value = "/school/person/index")
    public String schoolPerson(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        int pageSize = getPageSize(request);
        int pageNum = getPageNum(request);
        String teacherName = java.net.URLDecoder.decode(getParamVal(request, "teacherName"), "UTF-8");
        String chooseSchoolId = getParamVal(request, "choose");
        User user = getLoginUser();

        Map<String, Object> res = getTreeMenu(user.getSchoolId(), chooseSchoolId);
        if (!GukeerStringUtil.isNullOrEmpty(res.get("chooseSchoolId")))
            chooseSchoolId = res.get("chooseSchoolId").toString();

        PageInfo<Map> pageInfo = teacherService.teacherListView(pageNum, pageSize, user.getSchoolId(), chooseSchoolId, teacherName);//teacherService.findAllList(pageNum,pageSize,chooseSchoolId,teacherName);

        model.addAttribute("schoolList", res.get("ret"));
        model.addAttribute("currentSchool", res.get("loginSchool"));
        model.addAttribute("choose", chooseSchoolId);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("teacherList", pageInfo.getList());
        model.addAttribute("teacherName", teacherName);

        return "renShiArea/schoolPerson";
    }

    //校级管理-账号管理
    @RequestMapping(value = "/school/person/account/index")
    public String schoolPersonAccount(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        int pageSize = getPageSize(request);
        int pageNum = getPageNum(request);
        String teacherName = java.net.URLDecoder.decode(getParamVal(request, "teacherName"), "UTF-8");
        String chooseSchoolId = getParamVal(request, "choose");
        User user = getLoginUser();

        Map<String, Object> res = getTreeMenu(user.getSchoolId(), chooseSchoolId);
        if (!GukeerStringUtil.isNullOrEmpty(res.get("chooseSchoolId")))
            chooseSchoolId = res.get("chooseSchoolId").toString();

        PageInfo<Map> pageInfo = teacherService.teacherListView(pageNum, pageSize, user.getSchoolId(), chooseSchoolId, teacherName);//teacherService.findAllList(pageNum,pageSize,chooseSchoolId,teacherName);

        model.addAttribute("schoolList", res.get("ret"));
        model.addAttribute("currentSchool", res.get("loginSchool"));
        model.addAttribute("choose", chooseSchoolId);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("teacherList", pageInfo.getList());
        model.addAttribute("teacherName", teacherName);

        return "renShiArea/schoolPersonAccount";
    }

    //校级管理-部门管理
    @RequestMapping(value = "/school/department/index")
    public String schoolDepart(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        int pageSize = getPageSize(request);
        int pageNum = getPageNum(request);
        String teacherName = java.net.URLDecoder.decode(getParamVal(request, "teacherName"), "UTF-8");
        String departId = getParamVal(request, "departId");
        String chooseSchoolId = getParamVal(request, "choose");
        User user = getLoginUser();

        Map<String, Object> res = getTreeMenu(user.getSchoolId(), chooseSchoolId);
        if (!GukeerStringUtil.isNullOrEmpty(res.get("chooseSchoolId")))
            chooseSchoolId = res.get("chooseSchoolId").toString();

        List<Department> departList = departmentService.findAllDepartment(chooseSchoolId);//当前选中机构的部门列表

        Teacher teacher = new Teacher();
        teacher.setName(teacherName);
        teacher.setSchoolId(chooseSchoolId);

//        PageInfo<Teacher> pageInfo = teacherService.findTeacherByDepartmentId(departId, teacher, pageNum, pageSize);
        PageInfo<Map> pageInfo = teacherService.findTeacherViewList(departId, teacher, pageNum, pageSize);

        Department chooseDepart = departmentService.findDepartmentById(departId);

        model.addAttribute("pageInfo", pageInfo);//部门人员列表
        model.addAttribute("teacherList", pageInfo.getList());//部门人员列表
        model.addAttribute("schoolList", res.get("ret"));//菜单tree
        model.addAttribute("currentSchool", res.get("loginSchool"));//当前登录的区平台机构
        model.addAttribute("choose", chooseSchoolId);//菜单选择的机构
        model.addAttribute("teacherName", teacherName);//搜索的teacherName;
        model.addAttribute("departList", departList);//下拉部门列表
        model.addAttribute("currentDepart", chooseDepart);//选中部门的信息

        return "renShiArea/schoolBuMenManage";

    }

    //校级管理-职务管理
    @RequestMapping(value = "/school/zhiwu/index")
    public String zwManage(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        int pageSize = getPageSize(request);
        int pageNum = getPageNum(request);
        String teacherName = java.net.URLDecoder.decode(getParamVal(request, "teacherName"), "UTF-8");
        String zhiwuId = getParamVal(request, "titleId");
        String chooseSchoolId = getParamVal(request, "choose");
        User user = getLoginUser();

        Map<String, Object> res = getTreeMenu(user.getSchoolId(), chooseSchoolId);
        if (!GukeerStringUtil.isNullOrEmpty(res.get("chooseSchoolId")))
            chooseSchoolId = res.get("chooseSchoolId").toString();

        List<Title> zhiwuList = teacherService.selectTitleBySchoolId(chooseSchoolId);//当前选中机构的职务列表

        /*PageHelper.startPage(pageNum, pageSize);
        List<Teacher> teacherList = teacherService.findTeacherByTitleId(zhiwuId, chooseSchoolId, user.getSchoolId(), teacherName);
        PageInfo<Teacher> pageInfo = new PageInfo<Teacher>(teacherList);*/
        PageInfo<Map> pageInfo = teacherService.findTeacherViewByTitleId(zhiwuId, chooseSchoolId, user.getSchoolId(),
                                                                           teacherName, pageNum, pageSize);

        Title chooseTitle = teacherService.selectTitleById(zhiwuId);

        model.addAttribute("pageInfo", pageInfo);//部门人员列表
        model.addAttribute("teacherList", pageInfo.getList());//部门人员列表
        model.addAttribute("schoolList", res.get("ret"));//菜单tree
        model.addAttribute("currentSchool", res.get("loginSchool"));//当前登录的区平台机构
        model.addAttribute("choose", chooseSchoolId);//菜单选择的机构
        model.addAttribute("teacherName", teacherName);//搜索的teacherName;
        model.addAttribute("zhiwuList", zhiwuList);//下拉部门列表
        model.addAttribute("currentTitle", chooseTitle);//选中部门的信息

        return "renShiArea/schoolZhiWuManage";
    }

    //统计报表
    @RequiresPermissions("renShi:area:birt")
    @RequestMapping(value = "/birt/index")
    public String birtIndex(HttpServletRequest request, Model model) {
        User user = getLoginUser();

        School loginSchool = schoolService.selectSchoolById(user.getSchoolId());

        List<School> sonSchoolList = schoolService.getSonSchoolList(user.getSchoolId());
        sonSchoolList.add(loginSchool);

        Map birt = teacherService.teacherReport(user.getSchoolId(), sonSchoolList);

        model.addAttribute("birt", birt);
        return "renShiArea/birt";
    }

    //校级管理左侧菜单  机构>学校类型>学校
    public Map<String, Object> getTreeMenu(String schoolId, String chooseSchoolId) {

        School loginSchool = schoolService.selectSchoolById(schoolId);//当前登录用户所在机构

        List<School> schoolList = schoolService.getSonSchoolList(schoolId);//当前机构下的所有子机构

        if (StringUtil.isEmpty(chooseSchoolId))
            if (schoolList.size() > 0)
                chooseSchoolId = schoolList.get(0).getId();//若未选择菜单，默认选择菜单第一项

        List<AreaSchoolView> ret = formatMenu(schoolList);
        Map res = new HashMap();
        res.put("ret", ret);
        res.put("chooseSchoolId", chooseSchoolId);
        res.put("loginSchool", loginSchool);
        return res;
    }

    //schoollist  -->根据grade  划分 小学：school1---schooln   初中：shcooln--schoolx
    public List<AreaSchoolView> formatMenu(List<School> schoolList) {
        User user = getLoginUser();
        List<AreaSchoolView> ret = new ArrayList<>();
        Map<Object, List> map = new TreeMap<>();
        for (School school : schoolList) {
            Object grade = school.getGrade();
            if (map.containsKey(grade)) {
                map.get(grade).add(school);
            } else {
                List<School> list = new ArrayList<>();
                list.add(school);
                map.put(school.getGrade(), list);
            }
        }
        Set set = map.keySet();
        for (Object key : set) {
            AreaSchoolView view = new AreaSchoolView();
            view.setId("nosearch" + key.toString());
            view.setPid(user.getSchoolId());
            view.setList(map.get(key));
            String name = null;
            if (key.toString().equals("1"))
                name = "小学";
            if (key.toString().equals("2"))
                name = "初中";
            if (key.toString().equals("3"))
                name = "高中";
            if (key.toString().equals("4"))
                name = "一贯制";
            view.setName(name);
            ret.add(view);
        }
        return ret;
    }

}
