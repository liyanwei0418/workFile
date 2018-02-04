
package cn.gukeer.platform.controller;

import cn.gukeer.common.controller.BasicController;
import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.common.security.AESencryptor;
import cn.gukeer.common.tld.GukeerStringUtil;
import cn.gukeer.common.utils.FileUtils;
import cn.gukeer.common.utils.NumberConvertUtil;
import cn.gukeer.common.utils.PrimaryKey;
import cn.gukeer.platform.common.ConstantUtil;
import cn.gukeer.platform.common.EnumeratedConstant;
import cn.gukeer.platform.modelView.MenuView;
import cn.gukeer.platform.persistence.entity.*;
import cn.gukeer.platform.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.google.gson.Gson;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by conn on 2016/8/1.
 * school[机构管理]
 */
@Controller
@RequestMapping(value = "/school")
public class SchoolController extends BasicController {

    @Autowired
    SchoolService schoolService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    MenuService menuService;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    AppService appService;
    @Autowired
    SchoolAppService schoolAppService;
    @Autowired
    ClassService classService;
    @Autowired
    AppRoleService appRoleService;

    @RequiresPermissions("school:index:view")
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, Model model) {
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        PageHelper.startPage(pageNum, pageSize);
        String name = getParamVal(request, "name");
        try {
            name = java.net.URLDecoder.decode(name, "UTF-8");//解决非post访问的中文乱码问题。
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<Object, Object> paramMap = new HashMap<Object, Object>();
        paramMap.put("schoolId", null);
        if (!StringUtils.isEmpty(name)) {
            paramMap.put("name", "%" + name + "%");
        }
        List<Map<String, Object>> schoolList = schoolService.selectTeacherByParam(paramMap);

        //翻译school中的parentId字段
        List<School> schools = schoolService.selectAllList();
        for (Map<String, Object> school : schoolList) {
            for (int i = 0; i < schools.size(); i++) {
                if (school.get("parentId").equals(schools.get(i).getId())) {
                    school.put("parentId", schools.get(i).getName());
                }
                if (GukeerStringUtil.isNullOrEmpty(school.get("parentId")) || school.get("parentId").equals("0"))
                    school.put("parentId", "无");
            }
        }
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(schoolList);

        model.addAttribute("schoolList", schoolList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("name", name);
        return "school/index";
    }

    @RequiresPermissions("school:role:view")
    @RequestMapping(value = "/permissionMan")
    public String permissionMan(HttpServletRequest request, Model model) {
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        String roleName = getParamVal(request, "roleName");
        PageHelper.startPage(pageNum, pageSize);
        try {
            roleName = java.net.URLDecoder.decode(roleName, "UTF-8");//解决非post访问的中文乱码问题。
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<Role> roleList = roleService.findRoleListByName(roleName);
        PageInfo<Role> pageInfo = new PageInfo<Role>(roleList);

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("roleList", roleList);
        return "school/permissionMan";
    }


    //分配应用角色页面
    @RequestMapping(value = "/app/distribution")
    public String distribution(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        PageHelper.startPage(pageNum, pageSize);
        String id = getParamVal(request, "id");
        String name_gkb = request.getParameter("name");
        String name = java.net.URLDecoder.decode(name_gkb, "UTF-8");
        List<Role> appRole = roleService.findRoleByAppId(id);
        PageInfo<Role> pageInfo = new PageInfo<Role>(appRole);

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("appRole", appRole);
        model.addAttribute("appId", id);
        model.addAttribute("appName", name);
        return "app/admin/appDistribution";
    }

    //为应用选择角色，显示角色列表
    @RequestMapping(value = "/role/list")
    public String roleList(HttpServletRequest request, Model model) {
        String appId = getParamVal(request, "appId");

        List<Role> appRoleList = roleService.findRoleByAppId(appId);//该应用对应的角色
        List<String> appRoleIdList = new ArrayList<>();
        for (Role role : appRoleList) {
            appRoleIdList.add(role.getId());
        }

        List<Role> roleList = roleService.findAllList();//所有角色列表

        model.addAttribute("appRoleIdList", appRoleIdList);
        model.addAttribute("roleList", roleList);
        model.addAttribute("appId", appId);

        return "app/admin/chooseRole";
    }

    @Transactional
    @ResponseBody
    @RequestMapping(value = "/app/role/add")
    public void addAppRoleRef(HttpServletRequest request) {
        String appId = getParamVal(request, "appId");
        String roleIds = getParamVal(request, "roleIds");

        appRoleService.deleteAppRoleByAppId(appId);//删除该应用角色关联

        List<String> roleIdList = ConstantUtil.splitWithOutNull(roleIds);
        for (String roleId : roleIdList) {
            AppRole appRole = new AppRole();
            appRole.setAppId(appId);
            appRole.setRoleId(roleId);

            appRoleService.insertAppRole(appRole);
        }

    }

    /*重置机构管理员密码*/
    @ResponseBody
    @RequestMapping(value = "/admin/update")
    public ResultEntity resetPassWord(HttpServletRequest request) {
        String schoolId = getParamVal(request, "id");
        School school = schoolService.selectSchoolById(schoolId);
        List<User> userList = userService.getTeacherByRefId(school.getmId());
        for (User user : userList) {
            user.setPassword(AESencryptor.encryptCBCPKCS5Padding("000000"));
            user.setUpdateBy(getLoginUser().getId());
            user.setUpdateDate(System.currentTimeMillis());
            userService.saveUser(user);
        }
        return ResultEntity.newErrEntity();
    }

    /*机构管理编辑/新增弹出页面*/
    @RequestMapping(value = "/edit")
    public String edit(HttpServletRequest request, Model model) {
        String id = getParamVal(request, "id");
        if (!id.equals("")) {
            Map<Object, Object> paramMap = new HashMap<Object, Object>();
            paramMap.put("schoolId", id);
            Map<String, Object> school = schoolService.selectTeacherByParam(paramMap).get(0);//根据id查询得到的是长度为1的list结果集
            model.addAttribute("school", school);
            List<String> xz = ConstantUtil.splitWithOutNull(school.get("xz").toString());
            model.addAttribute("province", xz.get(0));
            model.addAttribute("city", xz.get(1));
            model.addAttribute("county", xz.get(2));
        }

        model.addAttribute("schoolList", schoolService.selectAllList());
        return "school/addSchool";
    }

    /*机构管理编辑(新增)-保存*/
    @Transactional
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultEntity update(HttpServletRequest request) {
        String id = getParamVal(request, "id");
        String name = getParamVal(request, "name");
        String shortFlag = getParamVal(request, "shortFlag");
        int type = NumberConvertUtil.convertS2I(getParamVal(request, "type"));
        String address = getParamVal(request, "address");
        String deployUrl = getParamVal(request, "deployUrl");
        String parentId = getParamVal(request, "parentId");
        String email = getParamVal(request, "email");
        String chooseAddress = getParamVal(request, "s_province") + "," + getParamVal(request, "s_city") + "," + getParamVal(request, "s_county");
        String logoUrl = getParamVal(request, "headUrl");
        int grade = NumberConvertUtil.convertS2I(getParamVal(request, "grade"));

        ResultEntity resultEntity = null;
        int rstNum = 0;
        School school = new School();
        school.setName(name);
        school.setLogo(logoUrl);
        school.setShortFlag(shortFlag);
        school.setType(type);
        school.setAddress(address);//地址输入框，联系地址
        school.setXz(chooseAddress);//级联选择，所属地区
        school.setDeployUrl(deployUrl);
        school.setParentId(parentId);
        school.setEmail(email);

        if (EnumeratedConstant.Platform.getIndex() == type)
            grade = 0;

        school.setGrade(grade);

        if (StringUtil.isEmpty(id)) {
            String primary = PrimaryKey.get();
            school.setId(primary);

            school.setCreateBy(getLoginUser().getId());
            school.setCreateDate(System.currentTimeMillis());
            school.setDelFlag(0);
            schoolService.saveSchoolBackId(school);

            //分配管理员，创建机构的时候分配管理员账号admin+标识为登录名，密码初始为6个0.
            String manId = "";
            Teacher teacher = new Teacher();
            String teacherPri = PrimaryKey.get();
            teacher.setId(teacherPri);
            teacher.setSchoolId(primary);
            teacher.setIsManage(EnumeratedConstant.IS_MANAGER);
            teacher.setName(EnumeratedConstant.DEFAULT_MANAGER_NAME);
            teacher.setGender(-1);
            teacher.setDelFlag(0);
            teacher.setAccount("admin@" + shortFlag);//生成账号管理员账号：admin+标识
            teacher.setCreateDate(System.currentTimeMillis());
            teacher.setCreateBy(getLoginUser().getId());

            int flag = teacherService.saveTeacherBackId(teacher); //保存user_teacher

            if (flag > 0) {
                manId = teacherPri;
            }

            //添加sys_user表
            User user = new User();
            String prim = PrimaryKey.get();
            user.setId(prim);
            user.setSchoolId(primary);
            user.setUsername(teacher.getAccount());
            user.setPassword(AESencryptor.encryptCBCPKCS5Padding(EnumeratedConstant.DEFAULT_MANAGER_PASSWORD));
            user.setCreateBy(getLoginUser().getId());
            user.setCreateDate(System.currentTimeMillis());
            user.setRefId(manId);
            user.setName(EnumeratedConstant.DEFAULT_MANAGER_NAME);
            user.setUserType(EnumeratedConstant.TEACHER.getIndex());
            user.setPhotoUrl(FileUtils.DEFAULT_HEAD_PHOTO);

            userService.insertUserBackId(user);//保存 sys_user

            // 添加ref_user_role表
            UserRole userRole = new UserRole();
            userRole.setSchoolId(primary);
            userRole.setRoleId(EnumeratedConstant.DEFAULT_MANAGER_SCHOOL_ADMIN);
            userRole.setUserId(prim);
            userService.saveUserRole(userRole);

            //更新机构表管理员id字段
            school.setId(primary);
            school.setmId(manId);
            schoolService.saveSchoolSettingInfo(school); //更新学校设置

            //创建的机构是学校类型 创建校区
            if (type == EnumeratedConstant.School.getIndex()) {
                //学校生成后，创建主校区
                SchoolType schoolType = new SchoolType();
                schoolType.setSort(1);
                schoolType.setSchoolId(primary);
                schoolType.setName(EnumeratedConstant.MAIN_SCHOOL);
                schoolType.setCreateBy(getLoginUser().getId());
                schoolType.setCreateDate(System.currentTimeMillis());
                schoolType.setDelFlag(0);
                schoolService.saveSchoolType(schoolType);

                //学校生成后，创建默认学段
                ClassSection elementary = new ClassSection();
                elementary.setName(EnumeratedConstant.PrimarySchool.getName());

                if (grade == EnumeratedConstant.PrimarySchool.getIndex() || grade == EnumeratedConstant.MixSchool.getIndex())
                    elementary.setDelFlag(0);
                else
                    elementary.setDelFlag(1);

                elementary.setCreateBy(getLoginUser().getId());
                elementary.setCreateDate(System.currentTimeMillis());
                elementary.setSchoolId(primary);
                elementary.setSectionYear(6);
                elementary.setLimitAge(7);
                elementary.setShortName(EnumeratedConstant.PrimarySchool.getName());
                classService.saveClassSection(elementary);

                ClassSection junior = new ClassSection();
                junior.setName(EnumeratedConstant.JuniorSchool.getName());

                if (grade == EnumeratedConstant.JuniorSchool.getIndex() || grade == EnumeratedConstant.MixSchool.getIndex()) {
                    junior.setDelFlag(0);
                } else {
                    junior.setDelFlag(1);
                }

                junior.setCreateBy(getLoginUser().getId());
                junior.setCreateDate(System.currentTimeMillis());
                junior.setSchoolId(primary);
                junior.setSectionYear(3);
                junior.setLimitAge(13);
                junior.setShortName(EnumeratedConstant.JuniorSchool.getName());
                classService.saveClassSection(junior);

                ClassSection senior = new ClassSection();
                senior.setName(EnumeratedConstant.HighSchool.getName());

                if (grade == EnumeratedConstant.HighSchool.getIndex() || grade == EnumeratedConstant.MixSchool.getIndex()) {
                    senior.setDelFlag(0);
                } else {
                    senior.setDelFlag(1);
                }

                senior.setCreateBy(getLoginUser().getId());
                senior.setCreateDate(System.currentTimeMillis());
                senior.setSchoolId(primary);
                senior.setSectionYear(3);
                senior.setLimitAge(16);
                senior.setShortName(EnumeratedConstant.HighSchool.getName());
                classService.saveClassSection(senior);
            }

        } else {
            try {
                school.setId(id);
                school.setUpdateBy(getLoginUser().getId());
                school.setUpdateDate(System.currentTimeMillis());
                if (school.getDeployUrl() != null && school.getDeployUrl() != "") {
                    rstNum = schoolService.saveAndClearSchoolcache(school, school.getDeployUrl());
                } else {
                    rstNum = schoolService.saveSchoolSettingInfo(school);
                }
            } catch (Exception e) {
                logger.error("params error:{}, cause:{}", e.getMessage(), e.getCause());
                resultEntity = ResultEntity.newErrEntity(e.getMessage());
            }
        }
        if (rstNum > 0) {
            resultEntity = ResultEntity.newResultEntity();
        }
        return resultEntity;
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResultEntity apiBusinessUpdate(HttpServletRequest request) {
        String id = getParamVal(request, "id");
        try {
            schoolService.delete(id);
        } catch (Exception e) {
            logger.error("delete error :{}, cause:{}", e.getMessage(), e.getCause());
            ResultEntity.newErrEntity(e.getMessage());
        }
        return ResultEntity.newResultEntity();
    }


    /*机构详情*/
    @RequestMapping(value = "/details", method = RequestMethod.POST)
    public
    @ResponseBody
    String schoolDetails(HttpServletRequest request, Model model) {
        String id = getParamVal(request, "id");
        School school = schoolService.selectSchoolById(id);

        model.addAttribute("school", school);
        return new Gson().toJson(school);
    }

    @RequestMapping(value = "/permission/view")
    public String getPermissions(HttpServletRequest request, Model model) {
        String roleId = getParamVal(request, "roleId");
        List<Menu> menuList = menuService.findAllList();
        List<String> currentRolePermission = getRolePermission(roleId);

        model.addAttribute("menuList", menuList);
        model.addAttribute("roleId", roleId);
        model.addAttribute("currentRolePermission", currentRolePermission);
        return "school/menuList";
    }

    /*权限管理--给角色分配菜单权限*/
    @ResponseBody
    @RequestMapping(value = "/permission/save")
    public void testPermissions(HttpServletRequest request) {
        String menuList = getParamVal(request, "menuList");
        String roleId = getParamVal(request, "roleId");
        //清空角色菜单关联
        roleService.deleteRoleMenuByRoleId(roleId);

        String[] menu = menuList.split(",");
        for (int i = 0; i < menu.length; i++) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenuId(menu[i]);
            roleMenu.setRoleId(roleId);
            roleMenu.setSchoolId(getLoginUser().getSchoolId());

            roleService.saveRoleMenu(roleMenu);
        }
    }

    //（云平台给学校）推送应用-弹出页面
    @RequestMapping(value = "/app/authorization")
    public String getAppList(HttpServletRequest request, Model model) {
        String schoolId = getParamVal(request, "schoolId");
        String appStatus = getParamVal(request, "appStatus");//判断 1 已推送, 2 已上线, 3 已下线 4 其他
        //查询当前应用表里面的所有应用
        List<App> appList = appService.findAppByCriteria(-1);//查询所有可用app

        //查询当前机构已经推送/已经上线的应用列表，
        List<App> schoolAppList = appService.findAppBySchoolId(schoolId, Integer.valueOf(appStatus));

        List<String> appIds = new ArrayList<String>();
        for (App app : schoolAppList) {
            appIds.add(app.getId());
        }

        model.addAttribute("appList", appList);
        model.addAttribute("schoolId", schoolId);
        model.addAttribute("appIds", appIds);
        model.addAttribute("appStatus", appStatus);
        return "school/appList";
    }

    //推送/上线应用-
    @Transactional
    @ResponseBody
    @RequestMapping(value = "/app/authorization/save")
    public void appAuthorization(HttpServletRequest request) {
        String schoolId = getParamVal(request, "schoolId");
        String appStatus = getParamVal(request, "appStatus");
        String id = getParamVal(request, "apps");

        SchoolApp schoolAppDelete = new SchoolApp();
        schoolAppDelete.setSchoolId(schoolId);
        schoolAppDelete.setAppStatus(Integer.valueOf(appStatus));
        schoolAppService.deleteSchoolApp(schoolAppDelete);

        //改了应用上线，需要将用户添加的记录也更改（应用a下线，之前添加了应用a的用户记录得删除）
        schoolAppService.deleteAddedApp(ConstantUtil.splitWithOutNull(id));

        if (StringUtils.isEmpty(id)) {
            return;
        }

        String[] idArr = id.split(",");
        for (int i = 0; i < idArr.length; i++) {
            SchoolApp schoolApp = new SchoolApp();
            schoolApp.setAppId(idArr[i]);
            schoolApp.setSchoolId(schoolId);
            schoolApp.setAppStatus(Integer.valueOf(appStatus));
            schoolAppService.insertSchoolApp(schoolApp);
        }

    }

    //   权限管理--删除角色
    @ResponseBody
    @RequestMapping(value = "/role/delete")
    public String roleDelete(HttpServletRequest request) {
        String roleId = getParamVal(request, "roleId");
        Role role = new Role();
        role.setId(roleId);
        role.setDelFlag(1);
        role.setUpdateBy(getLoginUser().getId());
        role.setUpdateDate(System.currentTimeMillis());

        roleService.updateRole(role);//更改角色为不可用
        roleService.deleteRoleMenuByRoleId(roleId);//删除角色菜单关联表
        return "";
    }

    //权限管理-查看角色信息
    @ResponseBody
    @RequestMapping(value = "/role/view")
    public String getRoleInfo(HttpServletRequest request) {
        String roleId = getParamVal(request, "roleId");
        Role role = roleService.findRoleById(roleId);
        List<String> currentRolePermission = getRolePermission(roleId);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("role", role);
        resultMap.put("permissions", currentRolePermission);
        return new Gson().toJson(resultMap);
    }

    private List<String> getRolePermission(String roleId) {
        List<MenuView> menuViews = userService.selectMenusByRoleId(roleId);
        List<String> currentRolePermission = new ArrayList<String>();
        for (MenuView menuView : menuViews) {
            String permission = menuView.getPermission();
            if (!StringUtils.isEmpty(permission)) {
                currentRolePermission.add(permission);
            }
        }
        return currentRolePermission;
    }

    //    权限管理-编辑角色弹出页面
    @RequestMapping(value = "/role/edit")
    public String roleEdit(HttpServletRequest request, Model model) {

        String id = getParamVal(request, "id");
        Role role = roleService.findRoleById(id);

        model.addAttribute("role", role);
        return "school/editRole";
    }

    //权限管理-编辑(新增)角色保存
    @ResponseBody
    @RequestMapping(value = "/role/save", method = RequestMethod.POST)
    public ResultEntity roleSave(HttpServletRequest request) {

        String id = getParamVal(request, "id");
        String name = getParamVal(request, "name");
        String remarks = getParamVal(request, "remarks");
        String identify = getParamVal(request, "identify");

        User user = getLoginUser();
        Role role = new Role();
        role.setId(id);
        role.setName(name);
        role.setRemarks(remarks);
        role.setRoleIdentify(identify);

        if (StringUtil.isEmpty(id)) {
            role.setId(PrimaryKey.get());
            role.setCreateBy(user.getId());
            role.setCreateDate(System.currentTimeMillis());
            roleService.insertRole(role);
        } else {
            role.setUpdateDate(System.currentTimeMillis());
            role.setUpdateBy(user.getId());
            roleService.updateRole(role);
        }
        return ResultEntity.newResultEntity();
    }

    /**
     * 管理日志
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/log")
    public String logShow(HttpServletRequest request, Model model) {
        Integer pageNum = getPageNum(request);
        Integer pageSize = getPageSize(request);

        PageInfo<LogWithBLOBs> logList = schoolService.selectLog(pageNum, pageSize);
        model.addAttribute("pageInfo", logList);
        return "school/showLog";
    }

    /**
     * 日志详情
     *
     * @param request
     * @param model
     * @return
     */
    @RequiresPermissions("school:index:view")
    @RequestMapping(value = "/log/detail")
    public String logDetail(HttpServletRequest request, Model model) {
        String logId = getParamVal(request, "id");
        String type = getParamVal(request, "type");

        LogWithBLOBs logWithBLOBs = schoolService.selectLogById(logId);

        String detail = "";
        if (type.equals("params")) {
            detail = logWithBLOBs.getParams();
        }
        if (type.equals("exception")) {
            detail = logWithBLOBs.getException();
        }
        model.addAttribute("log", detail);
        return "school/logDetail";
    }

    @RequiresPermissions("school:index:view")
    @RequestMapping(value = "/config")
    public String sysconfig(HttpServletRequest request, Model model) {
        Integer pageNum = getPageNum(request);
        Integer pageSize = getPageSize(request);

        PageInfo<Config> configList = schoolService.selectConfig(pageNum, pageSize);
        model.addAttribute("pageInfo", configList);
        return "school/config";
    }

    @RequestMapping(value = "/config/delete")
    public ResultEntity configDelete(HttpServletRequest request, Model model) {
        String _id = getParamVal(request, "id");

        Config config = schoolService.selectConfigById(_id);
        config.setDelFlag(1);
        schoolService.saveConfig(config);
        return ResultEntity.newResultEntity();
    }

    @RequestMapping(value = "/config/add", method = RequestMethod.GET)
    public String configAdd(HttpServletRequest request, Model model) {
        String _id = getParamVal(request, "id");

        Config config = schoolService.selectConfigById(_id);
        model.addAttribute("config", config);

        return "school/addConfig";
    }

    @RequestMapping(value = "/config/save", method = RequestMethod.POST)
    public String configAdd(Config config) {
        schoolService.saveConfig(config);
        return "school/config";
    }
}
