package cn.gukeer.platform.controller;

import cn.gukeer.common.config.Global;
import cn.gukeer.common.controller.BasicController;
import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.common.security.MD5Utils;
import cn.gukeer.common.tld.GukeerStringUtil;
import cn.gukeer.common.utils.*;
import cn.gukeer.common.utils.syncdata.LdapUtils;
import cn.gukeer.common.utils.syncdata.MD5Util;
import cn.gukeer.platform.common.ConstantUtil;
import cn.gukeer.platform.modelView.AppView;
import cn.gukeer.platform.persistence.entity.*;
import cn.gukeer.platform.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.google.gson.JsonObject;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

/**
 * Created by conn on 2016/8/8.
 */
@Controller
@RequestMapping(value = "/app")
public class AppController extends BasicController {
    private final String VFS_ROOT_PATH = VFSUtil.getVFSRootPath();
    private final String UPLOAD_PATH = VFSUtil.getVFSRootPath() + "/app/detail/";

    public static Properties prop = LdapUtils.getProperties("/syncData.properties");
    public static Properties apiProp = LdapUtils.getProperties("/api.properties");
    public static final String PASSWORD = prop.getProperty("password");

    @Autowired
    AppService appService;

    @Autowired
    SchoolService schoolService;

    @Autowired
    SchoolAppService schoolAppService;

    @Autowired
    AppRoleService appRoleService;

    @Autowired
    RoleService roleService;

    @Autowired
    MyAppService myAppService;

    @Autowired
    RoleMenuService roleMenuService;

    @Autowired
    MonitorService monitorService;

    @Autowired
    MenuService menuService;

    @Autowired
    UserService userService;

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("appEntity")
    public void initBinderAppEntity(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("appEntity.");
    }

    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, Model model) {
        try {
            int pageNum = getPageNum(request);
            int pageSize = getPageSize(request);

            PageInfo<App> pageInfo = appService.findAllList(pageNum, pageSize);

            model.addAttribute("pageInfo", pageInfo);
        } catch (Exception e) {
            logger.error("AppController.index()---error", e);
        }
        return "app/admin/index";
    }

    @RequestMapping(value = "/selectbyname")
    public String selectByName(HttpServletRequest request, Model model) {
        try {
            int pageNum = getPageNum(request);
            int pageSize = getPageSize(request);
            PageHelper.startPage(pageNum, pageSize);
            String name = java.net.URLDecoder.decode(getParamVal(request, "name"), "UTF-8");//解决非post访问的中文乱码问题。
            List<App> appList = appService.findByName(name);
            PageInfo<App> pageInfo = new PageInfo<>(appList);
            model.addAttribute("pageInfo", pageInfo);
        } catch (Exception e) {
            logger.error("AppController.index()---error", e);
        }
        return "app/admin/index";
    }


    /**
     * 应用商店推介页面
     *
     * @param request
     */
    @RequestMapping(value = "/add")
    public String add(HttpServletRequest request) {
        return "app/admin/addApp";
    }

    /**
     * 应用商店推介页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/appstore/index")
    public String appStoreIndex(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        String userId = user.getId();

        String name = getParamVal(request, "name");
        String category = getParamVal(request, "category");
        String targetUser = getParamVal(request, "targetUser");
        String area = getParamVal(request, "area");
        try {
            name = java.net.URLDecoder.decode(name, "UTF-8");//解决非post访问的中文乱码问题。
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        int permissionFlag = userService.isAreaAdmin(user.getSchoolId()) ? 1 : 2;//是 区级1，校级2, 3 公共
        //应用首页tap------------------------------------------------------------tap1-------------

        PageHelper.startPage(0, 8);
        List<App> appAllList = appService.findAppByCriteria(permissionFlag);//系统全部应用

        //全部应用tap-----------------------------------------------------------tap2--------------
        List<App> appList = appService.findAppByNameAndCategoryAndTargetUser(permissionFlag, name, category, targetUser, area, schoolId);

        //我的应用tap-----------------------------------------------------------tap3--------------
        List<App> myAppList = appService.selectAppByUserIdAndUserType(user);//我的个人已经添加应用
        List<App> defaultAppList = appService.findDefaultAppList(permissionFlag);//系统默认应用
        defaultAppList.addAll(myAppList);//个人已经添加+系统默认应用 组成个人应用列表

        List<App> commonlyUsedAppList = appService.findCommonUsedAppList(userId);//常用应用列表

        Map<String, List<App>> res = appDivide(defaultAppList);//下面的应用分类list
        List<Map<String, Object>> resView = new ArrayList<>();
        for (Map.Entry entry : res.entrySet()) {
            Map<String, Object> map = new HashedMap();

            String key = "";
            if ("1".equals(entry.getKey()))
                key = "教学教务";
            else if ("2".equals(entry.getKey()))
                key = "互动空间";
            else if ("0".equals(entry.getKey()))
                key = "系统应用";
            else if ("3".equals(key))
                key = "校务管理";

            map.put("appTypeName", key);

            map.put("appViewList", entry.getValue());
            resView.add(map);
        }

        model.addAttribute("res", resView);
        model.addAttribute("commonlyUsedAppList", commonlyUsedAppList);

        model.addAttribute("name", name);
        model.addAttribute("category", category);
        model.addAttribute("targetUser", targetUser);
        model.addAttribute("area", area);
        model.addAttribute("appList", appList);
        model.addAttribute("size", appList.size());
        model.addAttribute("appAllList", appAllList);
        model.addAttribute("chooseTap", getParamVal(request, "choose"));
        return "app/index";
    }

    /**
     * 应用商店推介页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/manage")
    public String manage(HttpServletRequest request, Model model) {
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        PageInfo<School> pageInfo = schoolService.selectAllList(pageNum, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        return "app/admin/manage";
    }

    /**
     * 应用商店推介页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/manage/add")
    public String manageEdit(HttpServletRequest request, Model model) {
        String schoolId = request.getParameter("id");
        if (!StringUtil.isEmpty(schoolId)) {
            List<App> appList = appService.findAppBySchool(schoolId);
            List<App> allList = appService.findNotHaveList(schoolId);

            model.addAttribute("appList", appList);
            model.addAttribute("allList", allList);
            model.addAttribute("sid", schoolId);
        }
        return "app/admin/manage/add";
    }

    /**
     * 添加应用
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add/save")
    public SchoolApp addAppToSchool(HttpServletRequest request) {
        User user = getLoginUser();
        try {
            String appId = request.getParameter("id");
            String schoolId = request.getParameter("sid");
            if (!StringUtil.isEmpty(appId) && !StringUtil.isEmpty(schoolId) && null != user && (user.getSchoolId().equals(schoolId) || user.getUserType() == 0)) {
                SchoolApp schoolApp = new SchoolApp();
                schoolApp.setAppId(appId);
                schoolApp.setSchoolId(schoolId);
                schoolAppService.insertSchoolApp(schoolApp);
                return schoolApp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 应用商店推介页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/manage/delete")
    public String manageDel(HttpServletRequest request, Model model) {
        String schoolId = request.getParameter("id");
        if (!StringUtil.isEmpty(schoolId)) {
            List<App> appList = appService.findAppBySchool(schoolId);
            model.addAttribute("appList", appList);
            List<App> allList = appService.findNotHaveList(schoolId);
            model.addAttribute("allList", allList);
            model.addAttribute("sid", schoolId);
        }
        return "app/admin/manage/delete";
    }

    /**
     * 删除应用
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete/save")
    public SchoolApp delAppFromSchool(HttpServletRequest request) {
        User user = getLoginUser();
        try {
            String appId = request.getParameter("id");
            String schoolId = request.getParameter("sid");
            if (!StringUtil.isEmpty(appId) && !StringUtil.isEmpty(schoolId) && null != user && (user.getSchoolId().equals(schoolId) || user.getUserType() == 0)) {
                SchoolApp schoolApp = new SchoolApp();
                schoolApp.setAppId(appId);
                schoolApp.setSchoolId(schoolId);
                schoolAppService.deleteSchoolApp(schoolApp);
                return schoolApp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加应用
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/approle/add")
    public String addAppRole(HttpServletRequest request, Model model) {
        String appId = request.getParameter("id");
        if (!StringUtil.isEmpty(appId)) {
            List<Role> roleList = roleService.findRoleByAppId(appId);
            List<Role> allRoleList = roleService.findNotHaveList(appId);
            model.addAttribute("allRoleList", allRoleList);
            model.addAttribute("appId", appId);
            model.addAttribute("roleList", roleList);
        }
        return "app/admin/addRole";
    }

    /**
     * 添加角色至应用
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/approle/add/save")
    public AppRole addRoleToApp(HttpServletRequest request) {
        User user = getLoginUser();
        try {
            String roleId = request.getParameter("roleId");
            String appId = request.getParameter("appId");
            if (!StringUtil.isEmpty(roleId) && !StringUtil.isEmpty(appId) && null != user && user.getUserType() == 0) {
                AppRole appRole = new AppRole();
                appRole.setAppId(appId);
                appRole.setRoleId(roleId);
                appRoleService.insertAppRole(appRole);
                return appRole;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加角色并将角色加到应用
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addroleaddtoapp")
    public ResultEntity addRoleaddToApp(HttpServletRequest request) {
        String appId = getParamVal(request, "appid");
        String name = getParamVal(request, "name");
        String remarks = getParamVal(request, "remarks");
        String identify = request.getParameter("identify");
        Role role = new Role();
        role.setRoleIdentify(identify);
        role.setName(name);
        role.setRemarks(remarks);
        role.setCreateBy(getLoginUser().getId());
        role.setCreateDate(System.currentTimeMillis());
        role.setDelFlag(0);
        String pri = PrimaryKey.get();
        role.setId(pri);

        roleService.insertRoleBackId(role);
        AppRole appRole = new AppRole();
        appRole.setAppId(appId);
        appRole.setRoleId(pri);
        appRoleService.insertAppRole(appRole);
        return ResultEntity.newResultEntity();
    }

    /**
     * 添加应用
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/approle/delete")
    public String delAppRole(HttpServletRequest request, Model model) {
        String appId = request.getParameter("id");
        if (!StringUtil.isEmpty(appId)) {
            List<Role> roleList = roleService.findRoleByAppId(appId);
            model.addAttribute("roleList", roleList);
            List<Role> allRoleList = roleService.findNotHaveList(appId);
            model.addAttribute("allRoleList", allRoleList);
            model.addAttribute("appId", appId);
        }
        return "app/admin/delRole";
    }

    /**
     * 将角色从应用中删除
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delrolefromapp")
    public AppRole delRoleFromApp(HttpServletRequest request) {
        User user = getLoginUser();
        try {
            String roleId = request.getParameter("roleId");
            String appId = request.getParameter("appId");
            if (!StringUtil.isEmpty(roleId) && !StringUtil.isEmpty(appId) && null != user && user.getUserType() == 0) {
                AppRole appRole = new AppRole();
                appRole.setAppId(appId);
                appRole.setRoleId(roleId);
                appRoleService.deleteAppRole(appRole);
                return appRole;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 编辑
     */
    @RequestMapping(value = "/edit")
    public String edit_new(HttpServletRequest request, Model model) {
        try {
            String _id = getParamVal(request, "id");

            App app = appService.findAppById(_id);
            if (!GukeerStringUtil.isNullOrEmpty(app)) {
                String[] picUrls = app.getPicUrl().split(",");
                int num = picUrls.length;
                String[] result = new String[num];
                for (int i = num - 1; i >= 0; i--) {
                    result[i] = picUrls[num - 1 - i];
                }
                model.addAttribute("picUrls", result);
            }
            if (!GukeerStringUtil.isNullOrEmpty(app)) {
                String[] targetUsers = app.getTargetUser().split(",");
                model.addAttribute("targetUsers", targetUsers);
            }
            model.addAttribute("app", app);

        } catch (Exception e) {
            logger.error("AppController.edit()---error", e);
        }
        return "app/admin/edit";
    }

    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping(value = "/del")
    public String del(HttpServletRequest request) {
        String _id = getParamVal(request, "id");
        App app = appService.findAppById(_id);
        app.setDelFlag(Global.YES);
        appService.updateApp(app);
        return "ok";
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping(value = "/save")
    public void save(@ModelAttribute("appEntity") App appEntity, HttpServletRequest request) {
        try {
            String icon = getParamVal(request, "iconNew");
            String pic = getParamVal(request, "picNew");
            Subject subject = SecurityUtils.getSubject();
            User user = (User) subject.getPrincipal();
            if (!icon.equals("")) {
                appEntity.setIconUrl(icon);
            }
            appEntity.setCreateBy(user.getId());
            appEntity.setCreateDate(System.currentTimeMillis());


            if (StringUtil.isEmpty(appEntity.getId())) {
                appEntity.setId(PrimaryKey.get());
                appService.insertApp(appEntity);
            } else appService.updateApp(appEntity);
        } catch (Exception e) {
            logger.error("AppController.update()---error", e);
        }

    }


    /**
     * 应用管理弹出选择角色框
     */
    @RequestMapping(value = "/role/add")
    public String roleadd(HttpServletRequest request, Model model) {
        String appid = request.getParameter("appid");
        model.addAttribute("appid", appid);
        return "app/admin/appRoleTree";
    }

    /**
     * 文件上传
     *
     * @param file
     * @param response
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/uploads")
    public void uploads(@Param("file") MultipartFile file, HttpServletResponse response, @Param("imgName") String imgName) throws Exception {
        FileOutputStream fos = null;
        InputStream fis = null;
        try {
            String suffix = imgName.substring(imgName.lastIndexOf("."));
            String fileName = System.currentTimeMillis() + suffix;
            String absPath = UPLOAD_PATH + fileName;
            fis = file.getInputStream();
            File f = new File(absPath);
            fos = new FileOutputStream(f);
            byte[] b = new byte[1024];
            int nRead = 0;
            while ((nRead = fis.read(b)) != -1) {
                fos.write(b, 0, nRead);
            }
            fos.flush();

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("url", "/app/showpicture?picPath=" + absPath);
            response.getWriter().print(jsonObject);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (Exception e) {
            logger.error("上传文件失败", e);
        } finally {
            fos.close();
            fis.close();
        }
    }

    /**
     * 读取图片文件
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping("/showpicture")
    public void showPicture(HttpServletResponse response, String picPath) throws Exception {
        File file = new File(VFS_ROOT_PATH + picPath);
        if (!file.exists()) {
            logger.error("找不到文件[" + VFS_ROOT_PATH + picPath + "]");
            return;
        }
        response.setContentType("multipart/form-data");
        InputStream reader = null;
        /*try {*/
        reader = VFSUtil.getInputStream(file, true);
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = reader.read(buf)) != -1) {
        }
        OutputStream stream = response.getOutputStream();
        stream.write(buf);
        stream.flush();
        stream.close();
    }

    /**
     * 我的应用页面
     *
     * @param request
     * @return
     */
   /* @RequestMapping(value = "/myapp")
    public String myApp(HttpServletRequest request, Model model) {
        User loginUser = getLoginUser();
        String userId = loginUser.getId();
        int userType = loginUser.getUserType();
        String schoolId = loginUser.getSchoolId();

        User user = new User();
        user.setId(userId);
        user.setUserType(userType);
        user.setSchoolId(schoolId);
        List<App> myAppList = appService.selectAppByUserIdAndUserType(user);//我的个人已经添加应用
        List<App> defaultAppList = appService.findDefaultAppList();//系统默认应用

        defaultAppList.addAll(myAppList);//个人已经添加+系统默认应用 组成个人应用列表

        List<App> commonlyUsedAppList = appService.findCommonUsedAppList(userId);

        Map<String, List<App>> res = appDivide(defaultAppList);

        int size = defaultAppList.size();
        model.addAttribute("appList", defaultAppList);
        model.addAttribute("res", res);
        model.addAttribute("commonlyUsedAppList", commonlyUsedAppList);
        model.addAttribute("size", size);

        return "app/myApp";
    }*/

    /**
     * 我的应用页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/showalertapps")
    public String showAlertApps(HttpServletRequest request, Model model) {
        String type = getParamVal(request, "type");

        User loginUser = getLoginUser();
        String userId = loginUser.getId();
        int userType = loginUser.getUserType();
        String schoolId = loginUser.getSchoolId();

        User user = new User();
        user.setId(userId);
        user.setUserType(userType);
        user.setSchoolId(schoolId);
        List<App> otherAppList = new ArrayList<>();

        int permissionFlag = userService.isAreaAdmin(user.getSchoolId()) ? 1 : 2;//是 区级1，校级2, 3 公共

        if (type.equals("my"))
            otherAppList = appService.findOtherAppByUser(user);
        if (type.equals("common")) {
            //查看个人所有已添加的应用列表，再排除已经添加为常用应用的列表
            List<App> defaultAppList = appService.findDefaultAppList(permissionFlag);
            List<App> myAlreadyList = appService.selectAppByUserIdAndUserType(user);//我的个人已经添加的应用
            defaultAppList.addAll(myAlreadyList);

            //查看已经添加的常用应用列表
            List<App> commonAppList = appService.findCommonUsedAppList(user.getId());
            defaultAppList.removeAll(commonAppList);

            otherAppList.addAll(defaultAppList);
        }


        Map<String, List<App>> res = appDivide(otherAppList);
        List<Map<String, Object>> resView = new ArrayList<>();
        for (Map.Entry entry : res.entrySet()) {
            Map<String, Object> map = new HashedMap();

            String key = "";
            if ("1".equals(entry.getKey()))
                key = "教学教务";
            else if ("2".equals(entry.getKey()))
                key = "互动空间";
            else if ("0".equals(entry.getKey()))
                key = "系统应用";
            else if ("3".equals(key))
                key = "校务管理";

            map.put("appTypeName", key);

            map.put("appViewList", entry.getValue());
            resView.add(map);
        }


        int size = otherAppList.size();

        model.addAttribute("res", resView);
        model.addAttribute("otherAppList", otherAppList);
        model.addAttribute("size", size);
        model.addAttribute("type", type);
        return "app/alertApps";
    }

    /**
     * 添加我的应用
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/savemyapp")
    public ResultEntity saveMyApp(HttpServletRequest request) {
        try {
            String type = getParamVal(request, "type");

            User user = getLoginUser();
            String appIds = request.getParameter("ids");
            String[] split = appIds.split(",");
            if ("my".equals(type)) {
                for (int i = 0; i < split.length; i++) {
                    String appId = split[i];
                    String userId = user.getId();
                    int userType = user.getUserType();
                    if (null != user) {
                        MyApp myApp = new MyApp();
                        myApp.setUserId(userId);
                        myApp.setAppId(appId);
                        myApp.setUserType(userType);
                        myAppService.insertMyApp(myApp);
                    }
                }
            } else if ("common".equals(type)) {
                List<CommonlyUsedApp> commonlyUsedAppList = new ArrayList<>();
                for (int i = 0; i < split.length; i++) {
                    String appId = split[i];
                    CommonlyUsedApp commonlyUsedApp = new CommonlyUsedApp();
                    commonlyUsedApp.setAppId(appId);
                    commonlyUsedApp.setUserId(user.getId());

                    commonlyUsedAppList.add(commonlyUsedApp);
                }
                appService.insertBatchCommonUsedApp(commonlyUsedAppList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultEntity.newResultEntity("操作成功");
    }

    /*
    * 添加常用应用(验证应用id必须要是他能添加的范围内)
    * */
    @ResponseBody
    @RequestMapping(value = "/common/add", method = RequestMethod.POST)
    public ResultEntity addCommonlyUsedApp(HttpServletRequest request) {
        String appId = getParamVal(request, "appId");
        User user = getLoginUser();

        List<App> otherAppList = appService.findOtherAppByUser(user);//查询该用户有权限添加的应用列表

        List<String> appIdList = new ArrayList<>();
        for (App app : otherAppList) {
            appIdList.add(app.getId());
        }
        if (!appIdList.contains(appId))
            return ResultEntity.newErrEntity("您无权限添加该应用");//所要添加的应用不在个人所能添加的范围内，不允许添加

        List<App> alreadyAdd = appService.findCommonUsedAppList(user.getId());
        if (alreadyAdd.size() >= 15)
            return ResultEntity.newErrEntity("您的常用应用个数超过15个");

        CommonlyUsedApp commonUsed = new CommonlyUsedApp();
        commonUsed.setUserId(user.getId());
        commonUsed.setAppId(appId);

        int res = appService.addCommonUsedApp(commonUsed);
        if (res > 0)
            return ResultEntity.newResultEntity("添加成功", res);
        else
            return ResultEntity.newResultEntity("添加失败", null);
    }

    /*
    * 删除常用应用
    * */
    @ResponseBody
    @RequestMapping(value = "/common/delete", method = RequestMethod.POST)
    public ResultEntity deleteCommonlyUsedApp(HttpServletRequest request) {
        String appId = getParamVal(request, "appId");
        String userId = getLoginUser().getId();

        if (StringUtil.isNotEmpty(appId)) {

            CommonlyUsedApp commonUsed = new CommonlyUsedApp();
            commonUsed.setUserId(userId);
            commonUsed.setAppId(appId);

            int res = appService.deleteCommonUsedApp(commonUsed);

            if (res > 0)
                return ResultEntity.newResultEntity("删除成功", res);
            else
                return ResultEntity.newResultEntity("删除失败", null);
        } else
            return ResultEntity.newResultEntity("删除失败", null);
    }


    /**
     * 删除我的应用
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delmyapp")
    public ResultEntity delMyApp(HttpServletRequest request) {
        try {
            User user = getLoginUser();
            String appId = request.getParameter("appId");
            String userId = user.getId();
            int userType = user.getUserType();
            if (!StringUtil.isEmpty(appId) && null != user) {
                MyApp myApp = new MyApp();
                myApp.setUserId(userId);
                myApp.setUserType(userType);
                myApp.setAppId(appId);
                myAppService.deleteMyApp(myApp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.newResultEntity("删除失败", e);
        }
        return ResultEntity.newResultEntity("删除成功", null);
    }

    /**
     * 我的应用详情
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/showappdetails")
    public String showAppDetails(HttpServletRequest request, Model model) {
        User user = getLoginUser();

        List<MyApp> myAppList = appService.selectAppByUser(user);//查询当前用户添加了哪些应用
        List<String> appIdList = new ArrayList<>();
        for (MyApp myApp : myAppList) {
            appIdList.add(myApp.getAppId());
        }

        String app_id = getParamVal(request, "id");
        String _id = app_id;
        App app = appService.findAppById(_id);
        if (!StringUtil.isEmpty(app.getPicUrl())) {
            String[] picUrls = app.getPicUrl().split(",");
            int num = picUrls.length;
            String[] result = new String[num];
            for (int i = num - 1; i >= 0; i--) {
                result[i] = picUrls[num - 1 - i];
            }

            model.addAttribute("picUrls", result);
            model.addAttribute("size", result.length);
        }
        if (!StringUtil.isEmpty(app.getTargetUser())) {
            String[] targetUsers = app.getTargetUser().split(",");
            model.addAttribute("targetUsers", targetUsers);
        }
        model.addAttribute("appIdList", appIdList);
        model.addAttribute("app", app);
        return "app/appDetails";
    }

    /**
     * 删除权限以及权限和应用的关系
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/del_role_and_app")
    public ResultEntity del_role_and_app(HttpServletRequest request) {
        try {
            User user = getLoginUser();
            String roleId = request.getParameter("roleId");
            String appId = request.getParameter("appId");
            if (!StringUtil.isEmpty(roleId) && !StringUtil.isEmpty(appId) && null != user && user.getUserType() == 0) {
                AppRole appRole = new AppRole();
                appRole.setAppId(appId);
                appRole.setRoleId(roleId);
                appRoleService.deleteAppRole(appRole);
            }
            Role role = roleService.findRoleById(roleId);
            role.setDelFlag(Global.YES);
            roleService.updateRole(role);
            return null;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultEntity.newResultEntity();
    }

    /**
     * 弹出授权应用树
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getmenutree")
    public String getAppList(HttpServletRequest request, Model model) {
        String belong = getParamVal(request, "appId");
        String roleId = getParamVal(request, "roleId");
        List<Menu> menu_list = menuService.selectMenuByBelong(belong);
        model.addAttribute("menuList", menu_list);
        model.addAttribute("appId", belong);
        model.addAttribute("roleId", roleId);
        return "app/admin/appRoleTree";
    }

    /**
     * 给角色分配菜单
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/ref_menu_role")
    public void testPermissions(HttpServletRequest request) {
        String menuList = getParamVal(request, "menuList");
        String roleId = getParamVal(request, "roleId");
        String[] menu = menuList.split(",");
        roleMenuService.deleteByRoleId(roleId);
        for (int i = 0; i < menu.length; i++) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenuId(menu[i]);
            roleMenu.setRoleId(roleId);
            roleMenu.setSchoolId("0");
            roleMenuService.insert(roleMenu);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/save/fromopen", method = RequestMethod.POST)
    public ResultEntity appSaveFromOpen(HttpServletRequest request) {
        try {
            Map params = transToMAP(request.getParameterMap());
            String paramString = params.get("security").toString();

            String time = params.get("time").toString();
            String random = params.get("random").toString();

            List<String> list = new ArrayList<>();
            List<Monitor> monitorList = monitorService.selectMonitor();
            for (Monitor monitor : monitorList) {
                StringBuffer buffer = new StringBuffer();

                buffer.append(monitor.getClientId()).append(time).append(random).append(PASSWORD);
                String token = MD5Utils.md5(buffer.toString());
                list.add(token);
            }
            if (!list.contains(paramString)) {
                return ResultEntity.newErrEntity("假数据");
            }
            net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(params.get("app").toString());
            App app = new App();
            app.setId(jsonObject.getString("id"));
            app.setName(jsonObject.getString("name"));
            app.setIconUrl(jsonObject.getString("logo"));
            app.setWebUrl(jsonObject.getString("appUrl"));
            app.setPicUrl(jsonObject.getString("appScreenshot"));
//            app.setAppStatus( 1);//已上线
            app.setIsDefault(Integer.valueOf(jsonObject.getString("isFree")));
            app.setCreateDate(Long.valueOf(jsonObject.getString("createDate")));
            app.setRemarks(jsonObject.getString("appAbstruct"));
            app.setAppStatus(Integer.parseInt(jsonObject.getString("checkStatus")));
            app.setDelFlag(0);//显示
            app.setSfczxmz(0);//推送过来的应用静态资源文件不存在项目中
            app.setSource(1);//应用来源（0:系统应用 1:接入的第三方应用 2：其他应用）
            String[] targetUsers = jsonObject.getString("targetUser").split(",");
            String targetUser = "";
            if (targetUsers.length > 0) {
                for (int i = 0; i < targetUsers.length; i++) {
                    if (targetUsers[i].equals("教师")) {
                        if (i == 0) {
                            targetUser = "1";
                        } else {
                            targetUser = targetUser + "," + "1";
                        }
                    } else if (targetUsers[i].equals("学生")) {
                        if (i == 0) {
                            targetUser = "2";
                        } else {
                            targetUser = targetUser + "," + "2";
                        }
                    } else if (targetUsers[i].equals("家长")) {
                        if (i == 0) {
                            targetUser = "3";
                        } else {
                            targetUser = targetUser + "," + "3";
                        }
                    }
                }
            }
            app.setTargetUser(targetUser);
            //应用类别
            if (jsonObject.getString("category").equals("0")) {
                app.setCategory("1");
            } else if (jsonObject.getString("category").equals("1")) {
                app.setCategory("2");
            }
            app.setDevelopers(params.get("companName").toString());
            app.setCreateBy(params.get("developer").toString());
            int count = appService.saveApp(app);
            if (count == 1) {
                return ResultEntity.newResultEntity("操作成功");
            } else {
                return ResultEntity.newErrEntity();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.newErrEntity("");
        }
    }


    //上线管理（应用管理页面选择学校上线）
    @RequestMapping(value = "/online/index")
    public String getSchoolList(HttpServletRequest request, Model model) {
        String appId = getParamVal(request, "appId");
        //当前应用已经推送的学校列表
        List<School> schoolAlreadyPushList = schoolAppService.findSchoolListByAppId(appId);
        List<String> schoolIdList = new ArrayList<>();
        for (School school : schoolAlreadyPushList) {
            schoolIdList.add(school.getId());
        }
        //所有应用列表
        List<School> schoolList = schoolService.selectAllList();

        model.addAttribute("appId", appId);
        model.addAttribute("schoolList", schoolList);
        model.addAttribute("schoolAlreadyPushList", schoolIdList);
        return "app/admin/schoolList";
    }

    //保存
    @Transactional
    @ResponseBody
    @RequestMapping(value = "/online/save")
    public ResultEntity saveSchoolApp(HttpServletRequest request, Model model) {
        String appId = getParamVal(request, "appId");
        String schools = getParamVal(request, "schools");

        try {
            schoolAppService.deleteSchoolAppByAppId(appId);//根据应用id，删除学校应用关联。

            //因为学校数量不多，循环上线保存学校机构关联
            for (String schoolId : ConstantUtil.splitWithOutNull(schools)) {
                SchoolApp schoolApp = new SchoolApp();
                schoolApp.setAppStatus(2);
                schoolApp.setSchoolId(schoolId);
                schoolApp.setAppId(appId);
                schoolAppService.insertSchoolApp(schoolApp);
            }
        } catch (Exception e) {
            return ResultEntity.newErrEntity("操作异常");
        }
        return ResultEntity.newResultEntity("操作成功");
    }

    //给校训提供的 应用商店的接口----------------------------begin------------------------------------------------------

    //显示应用列表
    @ResponseBody
    @RequestMapping(value = "/api/list", method = RequestMethod.POST)
    public ResultEntity apiAppList(HttpServletRequest request) {

        String username = getParamVal(request, "username");
        String token = getParamVal(request, "token");

       /* if (!tokenCheck(token))
            return ResultEntity.newErrEntity("token无效");*/

        User user = appService.findUserByUsername(username);

        if (GukeerStringUtil.isNullOrEmpty(user))
            return ResultEntity.newErrEntity("无此用户");

        int permissionFlag = userService.isAreaAdmin(user.getSchoolId()) ? 1 : 2;//是 区级1，校级2, 3 公共
        //查询应用列表
        List<App> myAppList = appService.selectAppByUserIdAndUserType(user);//我的个人已经添加应用
        List<App> schoolAppList = appService.findAppBySchoolId(user.getSchoolId(), 2);//当前学校可以使用的应用列表
        List<App> defaultAppList = appService.findDefaultAppList(permissionFlag);//系统默认应用
        List<App> commonlyUsedAppList = appService.findCommonUsedAppList(user.getId());//常用应用

        defaultAppList.addAll(myAppList);
        defaultAppList.addAll(schoolAppList);

        //去重
        Iterator<App> it = defaultAppList.iterator();
        List<App> resList = new ArrayList<>();
        while (it.hasNext()) {
            App app = it.next();
            if (resList.contains(app)) {
                it.remove();
            } else {
                resList.add(app);
            }

        }

        List<AppView> viewList = formatApiAppRes(myAppList, defaultAppList, request);

        Map<String, List<AppView>> res = new TreeMap<>();
        //首页要求显示多个tap切换
        for (AppView appView : viewList) {
            String key = appView.getAppType();
            if ("1".equals(key)) {

                key = "教学教务";
            } else if ("2".equals(key)) {
                key = "互动空间";
            } else if ("0".equals(key)) {

                key = "系统应用";
            } else if ("3".equals(key)) {

                key = "校务管理";
            }

            appView.setAppType(key);

            if (res.containsKey(key)) {
                res.get(key).add(appView);
            } else {
                List<AppView> list = new ArrayList<>();
                list.add(appView);
                res.put(key, list);
            }
        }

        Map allRes = new HashMap();
        allRes.put("appList", appDivideFormat(res));
        allRes.put("commonUsedAppList", formatApiAppRes(myAppList, commonlyUsedAppList, request));
        return ResultEntity.newResultEntity(allRes);
    }

    //查询可添加应用列表
    @ResponseBody
    @RequestMapping(value = "/api/available/list", method = RequestMethod.POST)
    public ResultEntity appAvailable(HttpServletRequest request) {
        String username = getParamVal(request, "username");
        String token = getParamVal(request, "token");

       /* if (!tokenCheck(token))
            return ResultEntity.newErrEntity("token无效");*/

        User user = appService.findUserByUsername(username);

        if (GukeerStringUtil.isNullOrEmpty(user))
            return ResultEntity.newErrEntity("无此用户");

        List<App> appList = appService.findOtherAppByUser(user);

        List<AppView> resView = formatApiAppRes(null, appList, request);

        Map<String, List<AppView>> res = new TreeMap<>();
        //首页要求显示多个tap切换
        for (AppView appView : resView) {
            String key = appView.getAppType();
            if ("1".equals(key))
                key = "教学教务";
            else if ("2".equals(key))
                key = "互动空间";
            else if ("0".equals(key))
                key = "系统应用";
            else if ("3".equals(key))
                key = "校务管理";

            appView.setAppType(key);

            if (res.containsKey(key)) {
                res.get(key).add(appView);
            } else {
                List<AppView> list = new ArrayList<>();
                list.add(appView);
                res.put(key, list);
            }
        }

        return ResultEntity.newResultEntity(appDivideFormat(res));
    }

    //添加应用（支持批量添加）
    @Transactional
    @ResponseBody
    @RequestMapping(value = "/api/add", method = RequestMethod.POST)
    public ResultEntity appAdd(HttpServletRequest request) {
        String appIds = getParamVal(request, "appId");
        String username = getParamVal(request, "username");
        String token = getParamVal(request, "token");
        String type = getParamVal(request, "type");

      /*  if (!tokenCheck(token))
            return ResultEntity.newErrEntity("token无效");*/

        User user = appService.findUserByUsername(username);

        if (GukeerStringUtil.isNullOrEmpty(user))
            return ResultEntity.newErrEntity("无此用户");

        List<App> otherAppList = appService.findOtherAppByUser(user);//查询该用户有权限添加的应用列表

        //查询可以添加为个人应用的应用列表，判断传入的参数是否可添加为个人应用
        List<String> appIdCanAddAsMyList = new ArrayList<>();
        for (App app : otherAppList) {
            appIdCanAddAsMyList.add(app.getId());
        }

        int permissionFlag = userService.isAreaAdmin(user.getSchoolId()) ? 1 : 2;//是 区级1，校级2, 3 公共
        //查看个人所有已添加的应用列表，判断添加的常用应用是否在这个范围
        List<App> defaultAppList = appService.findDefaultAppList(permissionFlag);
        List<App> myAlreadyList = appService.selectAppByUserIdAndUserType(user);//我的个人已经添加的应用
        defaultAppList.addAll(myAlreadyList);
        List<String> appIdCanAddAsCommon = new ArrayList<>();
        for (App app : defaultAppList) {
            appIdCanAddAsCommon.add(app.getId());
        }

        //查看该应用是否已经在常用列表，若已经添加为常用应用了，不允许再添加
        List<App> commonAppList = appService.findCommonUsedAppList(user.getId());
        List<String> commonList = new ArrayList<>();
        for (App app : commonAppList) {
            commonList.add(app.getId());
        }

        List<String> appIdList = ConstantUtil.splitWithOutNull(appIds);

        List<MyApp> myAppList = new ArrayList<>();
        List<CommonlyUsedApp> commonlyUsedAppList = new ArrayList<>();
        for (String appId : appIdList) {

            if (type.equals("common")) {
                //常用应用必须是个人应用范围的
                if (!appIdCanAddAsCommon.contains(appId)) {
                    return ResultEntity.newErrEntity("请先将该应用添加为个人应用");//所要添加的应用不在个人所能添加的范围内，不允许添加
                }

                if (commonList.contains(appId)) {
                    return ResultEntity.newErrEntity("该应用已经是常用应用了");//所要添加的应用不在个人所能添加的范围内，不允许添加
                }


                CommonlyUsedApp commonlyUsedApp = new CommonlyUsedApp();
                commonlyUsedApp.setAppId(appId);
                commonlyUsedApp.setUserId(user.getId());

                commonlyUsedAppList.add(commonlyUsedApp);
            } else if (type.equals("my")) {

                if (!appIdCanAddAsMyList.contains(appId)) {
                    return ResultEntity.newErrEntity("您无权限添加该应用或该应用已经添加");//所要添加的应用不在个人所能添加的范围内，不允许添加
                }

                MyApp myApp = new MyApp();
                myApp.setUserId(user.getId());
                myApp.setUserType(user.getUserType());
                myApp.setAppId(appId);

                myAppList.add(myApp);
            } else {
                return ResultEntity.newErrEntity("type参数异常");
            }

        }

        try {
            if (type.equals("common")) {

                appService.insertBatchCommonUsedApp(commonlyUsedAppList);

            } else if (type.equals("my")) {

                myAppService.insertBatchMyApp(myAppList);

            } else {
                return ResultEntity.newErrEntity("type参数异常");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.newErrEntity("添加失败");
        }
        return ResultEntity.newResultEntity("添加成功", null);
    }

    //删除应用
    @ResponseBody
    @RequestMapping(value = "/api/delete", method = RequestMethod.POST)
    public ResultEntity appDelete(HttpServletRequest request) {
        String appId = getParamVal(request, "appId");
        String username = getParamVal(request, "username");
        String token = getParamVal(request, "token");
        String type = getParamVal(request, "type");

     /*   if (!tokenCheck(token))
            return ResultEntity.newErrEntity("token无效");*/

        User user = appService.findUserByUsername(username);

        if (GukeerStringUtil.isNullOrEmpty(user)) {
            return ResultEntity.newErrEntity("无此用户");
        }


        try {
            if (type.equals("common")) {
                CommonlyUsedApp commonlyUsedApp = new CommonlyUsedApp();
                commonlyUsedApp.setAppId(appId);
                commonlyUsedApp.setUserId(user.getId());

                appService.deleteCommonUsedApp(commonlyUsedApp);
            } else if (type.equals("my")) {
                MyApp myApp = new MyApp();
                myApp.setUserId(user.getId());
                myApp.setUserType(user.getUserType());
                myApp.setAppId(appId);

                myAppService.deleteMyApp(myApp);
            } else {
                return ResultEntity.newErrEntity("type参数异常");
            }
        } catch (Exception e) {
            return ResultEntity.newErrEntity("删除失败");
        }


        return ResultEntity.newResultEntity("删除成功", null);
    }


    //校训端点击应用直接跳转到应用首页
    @RequestMapping(value = "/api/url", method = RequestMethod.GET)
    public String appJump(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = getParamVal(request, "username");
        String token = getParamVal(request, "token");
        String appId = getParamVal(request, "appId");

        /*  if(!tokenCheck(token)){
            return "noLogin";//token无效跳转到未登录页面
        }*/

        User user = userService.getUserByAccount(username);

        App app = appService.findAppById(appId);
        if (!GukeerStringUtil.isNullOrEmpty(app)) {
            String url = "";
            String fullPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

            if (StringUtil.isEmpty(app.getWebUrl())) {
                url = "noPermission";
            }

            String developers = app.getDevelopers();
            if ("教育软件有限公司".equals(developers)) {
                String key = apiProp.getProperty("lezhix.app.key");
                String pKey = MD5Util.go(user.getRefId() + DateUtils.getCurrentTime("yyyy-MM-dd") + key);
                url += app.getWebUrl() + "&puid=" + user.getRefId() + "&pkey=" + pKey;
            }

            if ("sns".equals(developers)) {
                String param = AES.AES_Encrypt(SnsUtil.SNS_KEY, username + "," + fullPath + "/file/pic/show?picPath=" + user.getPhotoUrl());
                url += app.getWebUrl() + "?param=" + param;
                url = url.replaceAll("\\+", "%2B");
            }

            if ("ring".equals(developers)) {
                String from = "platform";
                int r = RandomUtils.nextInt(6);
                Long timeStamp = System.currentTimeMillis();
                String key = apiProp.getProperty("smartRing.key");
                String sign = MD5Util.go(from + username + timeStamp + r + key);
                String param = "?from=" + from + "&user=" + username + "&r=" + r + "&timestamp=" + timeStamp + "&sign=" + sign;
                url += app.getWebUrl() + param;
            }

            //登陆态
            UsernamePasswordToken loginToken = new UsernamePasswordToken(username, user.getPassword());
            Subject subject = SecurityUtils.getSubject();
            subject.login(loginToken);

            HttpSession session = request.getSession();
            session.setAttribute("loginUser", subject.getPrincipal());

            //判断当前用户是否有该应用的访问权限
            List<Menu> menuList = menuService.selectMenuByBelong(appId);
            boolean flag = false;
            for (Menu menu : menuList) {
                if (subject.isPermitted(menu.getPermission())) {
                    flag = true;
                }
            }

            if ("教育云平台".equals(developers)) {
                if (flag) {
                    url = "/" + app.getWebUrl() + "?appId=" + appId;
                    return "redirect:" + url;
                } else {
                    url = "noPermission";
                }
            }

            if (url.contains("http://")) {
                response.sendRedirect(url);
                return null;
            }
            return "/" + url;
        } else {
            //找不到这个应用404
            return "404";
        }
    }

    //格式化返回参数，并且将已经添加的应用比对，添加字段：已添加，未添加
    public static List<AppView> formatApiAppRes(List<App> myAppList, List<App> appList, HttpServletRequest request) {

        //判断该应用是否已经添加第一步
        List<String> myAppIdList = new ArrayList<>();

        if (!GukeerStringUtil.isNullOrEmpty(myAppList)) {
            for (App app : myAppList) {
                myAppIdList.add(app.getId());
            }
        }

        String username = request.getParameter("username");
        String token = request.getParameter("token");

        //将应用图标的显示图片路径更改为全url，数据库中存的是相对url
        String fullPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        List<AppView> viewList = new ArrayList<>();
        for (App app : appList) {

            AppView appView = new AppView();
            appView.setAppType(app.getCategory());
            appView.setId(app.getId());
            appView.setName(app.getName());
            appView.setWebUrl(fullPath + "/app/api/url?username=" + username + "&token=" + token + "&appId=" + app.getId());

            if (myAppIdList.contains(appView.getId()) || app.getIsDefault() == 0) {
                appView.setAppStatus(1);
            } else {
                appView.setAppStatus(0);
            }

            String picPath = null;
            if (app.getSfczxmz() == 0) {
                if (app.getIconUrl().contains("http")) {
                    picPath = app.getIconUrl();
                } else {
                    picPath = fullPath + "/file/pic/show?picPath=" + app.getIconUrl();
                }
            } else if (app.getSfczxmz() == 1) {
                picPath = fullPath + "/assetsNew/old_resourse/image/appDetails/" + app.getIconUrl();
            }
            appView.setIconUrl(picPath);

            viewList.add(appView);
        }
        return viewList;

    }

    public static Map<String, List<App>> appDivide(List<App> defaultAppList) {
        Map<String, List<App>> res = new TreeMap<>();
        //首页要求显示多个tap切换
        for (App app : defaultAppList) {
            String key = app.getCategory();

            if (res.containsKey(key)) {
                res.get(key).add(app);
            } else {
                List<App> list = new ArrayList<>();
                list.add(app);
                res.put(key, list);
            }
        }
        return res;
    }

    //将 map<String,List<appView>> 格式为 list<Map<String,Object>>
    public static List<Map<String, Object>> appDivideFormat(Map<String, List<AppView>> res) {
        List<Map<String, Object>> resView = new ArrayList<>();
        for (Map.Entry entry : res.entrySet()) {
            Map<String, Object> map = new HashedMap();
            map.put("appTypeName", entry.getKey());
            map.put("appViewList", entry.getValue());
            resView.add(map);
        }
        return resView;
    }

    public static boolean tokenCheck(String token) {
        Map param = new HashMap();
        param.put("accessToken", token);

        String url = PropertiesUtil.getProperties("/api.properties").get("open.url").toString();

        try {
            String res = HttpRequestUtil.get(url + "login/check", null, param);
            JSONObject jsonObject = JSONObject.fromObject(res).getJSONObject("data");

            Boolean flag = Boolean.valueOf(jsonObject.get("login").toString());

            return flag;
        } catch (Exception e) {
            return false;
        }
    }
    //给校训提供的 应用商店的接口-------------------------end--------------------------------------------------------
}
