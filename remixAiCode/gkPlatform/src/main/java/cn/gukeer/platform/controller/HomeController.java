package cn.gukeer.platform.controller;

import cn.gukeer.common.controller.BasicController;
import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.common.security.MD5Utils;
import cn.gukeer.common.utils.AES;
import cn.gukeer.common.utils.GsonUtil;
import cn.gukeer.common.utils.SnsUtil;
import cn.gukeer.platform.common.ConstantUtil;
import cn.gukeer.platform.modelView.*;
import cn.gukeer.platform.modelView.weather.HeWeather;
import cn.gukeer.platform.persistence.entity.*;
import cn.gukeer.platform.service.*;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by zjy on 2016/8/8.
 * teacher
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController extends BasicController {

    @Autowired
    TeacherService teacherService;

    @Autowired
    NotifyService notifyService;

    @Autowired
    AppService appService;

    @Autowired
    SchoolService schoolService;

    @Autowired
    StudentService studentService;

    @Autowired
    ClassService classService;

    @Autowired
    UserService userService;


    /**
     * 首页ren
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, Model model) {

        User user = getLoginUser();

        Map<Object, Object> paramMap = new HashMap<Object, Object>();
        paramMap.put("userId", user.getRefId());
        paramMap.put("isPublish", System.currentTimeMillis());//判断公告是否已经发布的字段。比对发布时间和当前日期
        paramMap.put("schoolId", user.getSchoolId());
        PageInfo<Map<Object, Object>> pageInfo = notifyService.findNotifyView(paramMap,getPageSize(request),getPageNum(request));
        //判断公告未读消息的条数：
        Integer publicCount = notifyService.selectRemindNotifyCount(user.getRefId());

        int permissionFlag = userService.isAreaAdmin(user.getSchoolId()) ? 1 : 2;//是 区级1，校级2, 3 公共

        //默认应用
        List<App> defaultAppList = appService.findAppByIdDefaultByTargetUser(user.getUserType().toString(), permissionFlag);//默认应用

        //我的应用
        List<App> myAppList = appService.selectAppByUserIdAndUserType(user);
        defaultAppList.addAll(myAppList);

        List<Map<String, Object>> res = appDivide(defaultAppList);
        //常用应用
        List<App> commonAppList = appService.findCommonUsedAppList(user.getId());

        Map<String,Object> commonList = new HashedMap();
        commonList.put("appTypeName","常用应用");
        commonList.put("appViewList",commonAppList);
        res.add(1,commonList);//类别排序  系统应用>常用应用>其他应用

        HttpSession session = request.getSession();

        session.setAttribute("publicCount", publicCount);//未读通知公告
        if (getLoginUser().getUserType() != 3) {
            session.setAttribute("userName", getLoginUser().getName());
        }
        session.setAttribute("bottomName", getBottomName());
        session.setAttribute("userType", getLoginUser().getUserType());
        model.addAttribute("notifyList", pageInfo.getList());
        model.addAttribute("defaultAppList", defaultAppList);
        model.addAttribute("viewAppList", res);

        return "home/index";
//        return "home/index_all";
    }


    //sns对接
    @ResponseBody
    @RequestMapping(value = "/sns/{id}", method = RequestMethod.POST)
    public ResultEntity sns(HttpServletRequest request, @PathVariable("id") String id) throws UnsupportedEncodingException {

        String snsMac = MD5Utils.md5(SnsUtil.SNS_KEY + getLoginUser().getUsername());

        List res = new ArrayList();
        switch (id) {
            case "1":
                res = SnsUtil.getSns("/index.php/Api/Index/weibo_topic_hot?username=" + getLoginUser().getUsername(), HotTopicView.class, 8, 0);//话题
                break;
            case "2":
                res = SnsUtil.getSns("/index.php/Api/Index/weibo_all?username=" + getLoginUser().getUsername(), WeiBoView.class, 5, 0);//微博
                break;
            case "3":
                res = SnsUtil.getSns("/index.php/Api/Index/plate_hot?username=" + getLoginUser().getUsername(), HotSpotView.class, 5, 0);//板块
                break;
            case "4":
                res = SnsUtil.getSns("/index.php/Api/Index/forum_all?username=" + getLoginUser().getUsername(), AllForumView.class, 5, 0);//帖子
                break;
            case "5":
//                List<Object> rep = SnsUtil.getSns("/cloud/index.php/Api/Index/fans?username=" + getLoginUser().getUsername() + "=" + snsMac, FansView.class, 1, 1);//关注，粉丝，动态
                List rep = new ArrayList();//关注粉丝动态接口暂时关闭
                if (rep.size() > 0) {
                    res.add(rep.get(0));
                } else {
                    FansView fansView = new FansView();
                    fansView.setFans(String.valueOf(0));
                    fansView.setFollow(String.valueOf(0));
                    fansView.setWb_num(String.valueOf(0));
                    res.add(fansView);
                }
                break;
            default:
                break;
        }

        Map resMap = new HashMap();
        String fullPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        resMap.put("list", res);
        resMap.put("snsUrl", SnsUtil.SNS_URL);
        resMap.put("snsMac", snsMac);
        resMap.put("loginUser", getLoginUser());

        String url = AES.AES_Encrypt(SnsUtil.SNS_KEY,getLoginUser().getUsername()+","+fullPath+"/file/pic/show?picPath="+getLoginUser().getPhotoUrl());

//        resMap.put("param", URLEncoder.encode(url.replaceAll("\\+","%2B"),"UTF-8"));
        resMap.put("param", url.replaceAll("\\+","%2B"));

        return ResultEntity.newResultEntity(resMap);
    }


    //天气
    @ResponseBody
    @RequestMapping(value = "/weather", method = RequestMethod.POST)
    public ResultEntity getWeather(HttpServletRequest request) {
        String weather = schoolService.getWeather(getLoginUser().getSchoolId());

        Gson gson = GsonUtil.noneIntDouble();
        HeWeather heWeather = gson.fromJson(weather,
                new TypeToken<HeWeather>() {
                }.getType());

        if (heWeather.getDaily_forecast().size() > 0)
            return ResultEntity.newResultEntity(heWeather.getDaily_forecast().get(0));

        return ResultEntity.newResultEntity(null);
    }


    @RequestMapping(value = "/messagecenter")
    public String msgCenter(HttpServletRequest request, Model model) {
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);

        User user = getLoginUser();

        Map<Object, Object> paramMap = new HashMap<Object, Object>();
        paramMap.put("userId", user.getRefId());
        paramMap.put("isPublish", System.currentTimeMillis());//判断公告是否已经发布的字段。比对发布时间和当前日期
        paramMap.put("schoolId", user.getSchoolId());
        PageInfo<Map<Object, Object>> pageInfo = notifyService.findNotifyView(paramMap,pageSize,pageNum);

        Integer publicCount = notifyService.selectRemindNotifyCount(user.getRefId());

        HttpSession session = request.getSession();
        session.setAttribute("publicCount", publicCount);//未读通知公告
        model.addAttribute("notifyList", pageInfo.getList());
        model.addAttribute("pageInfo", pageInfo);
        return "home/message";
    }

    //查询未读取的通知
    @ResponseBody
    @RequestMapping(value = "/remind/notify")
    public ResultEntity getRemindNotify(){
        String refId = getLoginUser().getRefId();
        int count = notifyService.selectRemindNotifyCount(refId);
        return ResultEntity.newResultEntity(count);
    }


    public String getBottomName() {
        User user = getLoginUser();
        Integer userType = user.getUserType();
        if (userType == 1) {
            School school = schoolService.selectSchoolById(user.getSchoolId());
            if (school != null) return school.getName();
        }
        if (userType == 2) {
            String refId = user.getRefId();
            String classId = studentService.selectStudentById(refId).getClassId();
            if (classId != null) {
                GradeClass gradeClass = classService.selectClassById(classId);
                if (gradeClass != null) {
                    String njName = ConstantUtil.getValueByKeyAndFlag(gradeClass.getNj(), "nj");
                    String bjName = gradeClass.getName();
                    return njName + " " + bjName;
                }
            }
        }
        if (userType == 3) {
            String refId = user.getRefId();
            Patriarch par = (Patriarch) userService.getLoginUserDetail(refId, new Patriarch());
            Student student = studentService.selectStudentById(par.getStudentId());
            if (par != null) return student.getXsxm() + "的家长";
        }
        return "";
    }


    //分类别展示应用
    public static List<Map<String, Object>> appDivide(List<App> defaultAppList) {
        Map<String, List<App>> res = new LinkedHashMap<>();
        //首页要求显示多个tap切换
        for (App app : defaultAppList) {
            String key = app.getCategory();
            if ("2".equals(key))
                key = "互动空间";
            else if ("1".equals(key))
                key = "教学教务";
            else if ("0".equals(key))
                key = "系统应用";
            else if ("3".equals(key))
                key = "校务管理";

            if (res.containsKey(key)) {
                res.get(key).add(app);
            } else {
                List<App> list = new ArrayList<>();
                list.add(app);
                res.put(key, list);
            }
        }

        List<Map<String,Object>> resView = new ArrayList<>();
        for (Map.Entry entry : res.entrySet()) {
            Map<String,Object> map = new HashedMap();
            map.put("appTypeName",entry.getKey());
            map.put("appViewList",entry.getValue());
            resView.add(map);
        }

        return resView;
    }

}
