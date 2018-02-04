package cn.gukeer.platform.controller;

import cn.gukeer.common.controller.BasicController;
import cn.gukeer.common.tld.GukeerStringUtil;
import cn.gukeer.common.utils.NumberConvertUtil;
import cn.gukeer.platform.common.ConstantUtil;
import cn.gukeer.platform.modelView.AreaSchoolView;
import cn.gukeer.platform.persistence.entity.*;
import cn.gukeer.platform.service.*;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

//区级学籍管理
@Controller
@RequestMapping(value = "/area/class")
public class ClassAreaController extends BasicController {

    @Autowired
    TeacherService teacherService;

    @Autowired
    SchoolService schoolService;

    @Autowired
    UserService userService;

    @Autowired
    StudentService studentService;

    @Autowired
    RoleService roleService;

    @Autowired
    ClassService classService;


    //学生信息
    @RequiresPermissions("class:area:stuinfo")
    @RequestMapping(value = "/stuinfo/index")
    public String stuInfo(HttpServletRequest request, Model model) throws UnsupportedEncodingException {

        String chooseSchoolId = getParamVal(request, "choose");
        String stuName = URLDecoder.decode(getParamVal(request, "stuName"), "UTF-8");//解决非post访问的中文乱码问题。;
        String xd = getParamVal(request, "xdId");
        String nj = getParamVal(request, "njId");
        String bj = getParamVal(request, "bjId");
        int pageSize = getPageSize(request);
        int pageNum = getPageNum(request);

        User user = getLoginUser();

        Map<String, Object> res = getTreeMenu(user.getSchoolId(), chooseSchoolId);
        if (!GukeerStringUtil.isNullOrEmpty(res.get("chooseSchoolId")))
            chooseSchoolId = res.get("chooseSchoolId").toString();


        //------------------------------------------begin-----------------------
        Map<String, Object> select = getXdNjBjSelect(chooseSchoolId, xd, nj);
        if (!GukeerStringUtil.isNullOrEmpty(select)) {
            if (StringUtil.isEmpty(bj)) {
                List<GradeClass> bjList = (List<GradeClass>) select.get("bjList");
                if (!GukeerStringUtil.isNullOrEmpty(bjList))
                    bj = bjList.get(0).getId();
            }
            select.put("bj", bj);
        }
        //------------------------------------------end-----------------------------

        Map param = new HashMap();
        param.put("pageSize", pageSize);
        param.put("pageNum", pageNum);

        if (StringUtil.isNotEmpty(stuName) && stuName.indexOf("%") < 0)
            param.put("stuName", "%" + stuName + "%");

        if (!GukeerStringUtil.isNullOrEmpty(select)) {
            param.put("xd", select.get("xdChoose"));
            param.put("nj", select.get("njChoose"));
        }

        param.put("classId", bj);

        PageInfo<Map> pageInfo = classService.getAreaStuList(param);

        List<SchoolType> schoolTypeList = schoolService.selectSchoolTypeBySchoolId(chooseSchoolId);//已设置校区
        List<ClassSection> sectionList = classService.getAllClassSectionBySchoolId(chooseSchoolId);//已启用学段

        model.addAttribute("schoolTypeList", schoolTypeList);
        model.addAttribute("sectionList", sectionList);
        model.addAttribute("select", select);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("stuList", pageInfo.getList());
        model.addAttribute("schoolList", res.get("ret"));//菜单tree
        model.addAttribute("chooseSchool", res.get("chooseSchool"));//当前选择的学校信息
        model.addAttribute("currentSchool", res.get("loginSchool"));//当前登录用户所在的机构信息
        model.addAttribute("stuName", stuName);//搜索的stuName;

        if (StringUtil.isNotEmpty(getParamVal(request, "appId")))
            request.getSession().setAttribute("xueJId", getParamVal(request, "appId"));

        return "classArea/stuInfo";
    }

    //家长信息
    @RequiresPermissions("class:area:parinfo")
    @RequestMapping(value = "/parinfo/index")
    public String parInfo(HttpServletRequest request, Model model) throws UnsupportedEncodingException {

        String chooseSchoolId = getParamVal(request, "choose");
        String stuName = URLDecoder.decode(getParamVal(request, "stuName"), "UTF-8");//解决非post访问的中文乱码问题。;
        String xd = getParamVal(request, "xdId");
        String nj = getParamVal(request, "njId");
        String bj = getParamVal(request, "bjId");
        int pageSize = getPageSize(request);
        int pageNum = getPageNum(request);

        User user = getLoginUser();

        Map<String, Object> res = getTreeMenu(user.getSchoolId(), chooseSchoolId);
        if (!GukeerStringUtil.isNullOrEmpty(res.get("chooseSchoolId")))
            chooseSchoolId = res.get("chooseSchoolId").toString();

        //------------------------------------------begin-----------------------
        Map<String, Object> select = getXdNjBjSelect(chooseSchoolId, xd, nj);
        if (!GukeerStringUtil.isNullOrEmpty(select)) {
            if (StringUtil.isEmpty(bj)) {
                List<GradeClass> bjList = (List<GradeClass>) select.get("bjList");
                if (!GukeerStringUtil.isNullOrEmpty(bjList))
                    bj = bjList.get(0).getId();
            }
            select.put("bj", bj);
        }
        //------------------------------------------end-----------------------------
        Map param = new HashMap();
        if (StringUtil.isNotEmpty(stuName))
            param.put("stuName", "%" + stuName + "%");

        param.put("schoolId", chooseSchoolId);
        param.put("xd", select.get("xdChoose"));
        param.put("nj", select.get("njChoose"));
        param.put("classId", bj);
        param.put("pageSize", pageSize);
        param.put("pageNum", pageNum);

        PageInfo<Map> pageInfo = classService.parentInfoList(param);

        model.addAttribute("select", select);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("schoolList", res.get("ret"));//菜单tree
        model.addAttribute("chooseSchool", res.get("chooseSchool"));//当前选择的学校信息
        model.addAttribute("currentSchool", res.get("loginSchool"));//当前登录用户所在的机构信息
        model.addAttribute("stuName", stuName);//搜索的stuName;

        return "classArea/parInfo";
    }


    //统计报表
    @RequiresPermissions("class:area:birt")
    @RequestMapping(value = "/birt/index")
    public String birtIndex(HttpServletRequest request, Model model) {
        User user = getLoginUser();

        School loginSchool = schoolService.selectSchoolById(user.getSchoolId());

        List<School> sonSchoolList = schoolService.getSonSchoolList(user.getSchoolId());
        sonSchoolList.add(loginSchool);

        List<Map> birtGender = studentService.genderReport(sonSchoolList);//性别人数柱状图
        Map birtLydq = studentService.lydqReport(sonSchoolList);//来源地区饼状图
        List<Map> birtLine = studentService.personCountReport(sonSchoolList);//班级人数情况折线图

        //区域学生统计
        Map personCount = new HashMap();
        int count = 0;
        for (Map map : birtGender) {
            Object countAll = map.get("countAll");
            count += Integer.valueOf(countAll.toString());
            personCount.put(map.get("sectionName"), countAll);
        }
        personCount.put("allStu", count);

        //折线图横坐标小学1转为小学1年级
        for (int i = 0; i < birtLine.size(); i++) {
            birtLine.get(i).put("indexName", ConstantUtil.translate(birtLine.get(i).get("indexName").toString()));
        }

        model.addAttribute("personCount", personCount);
        model.addAttribute("birtGender", birtGender);
        model.addAttribute("birtLydq", birtLydq);
        model.addAttribute("birtLine", birtLine);
        return "classArea/birt";
    }

    //角色分配
    @RequiresPermissions("class:area:role")
    @RequestMapping(value = "/rolefp/index")
    public String rsRoleFpIndex(HttpServletRequest request, Model model) {
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        String roleId = getParamVal(request, "roleId");
        User loginUser = getLoginUser();
        HttpSession session = request.getSession();
        Object appId = session.getAttribute("xueJId");

        List<Role> roleList = new ArrayList();
        if (!GukeerStringUtil.isNullOrEmpty(appId))
            roleList = roleService.findRoleByAppId(appId.toString());

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
        return "classArea/roleFenPei";
    }

    //校级管理左侧菜单  机构>学校类型>学校
    public Map<String, Object> getTreeMenu(String schoolId, String chooseSchoolId) {

        School loginSchool = schoolService.selectSchoolById(schoolId);//当前登录用户所在机构

        List<School> schoolList = schoolService.getSonSchoolList(schoolId);//当前机构下的所有子机构

        if (StringUtil.isEmpty(chooseSchoolId))
            if (schoolList.size() > 0)
                chooseSchoolId = schoolList.get(0).getId();//若未选择菜单，默认选择菜单第一项

        School chooseSchool = schoolService.selectSchoolById(chooseSchoolId);//当前登录用户所在机构

        List<AreaSchoolView> ret = formatMenu(schoolList);
        Map res = new HashMap();
        res.put("ret", ret);
        res.put("chooseSchoolId", chooseSchoolId);
        res.put("chooseSchool", chooseSchool);
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

    //获取学段，年级，班级级联菜单
    public Map<String, Object> getXdNjBjSelect(String chooseSchoolId, String chooseXdId, String chooseNjId) {
        //xd list  小学，初中，高中
        List<ClassSection> sectionList = classService.getAllClassSectionBySchoolId(chooseSchoolId); //学段list

        if (sectionList.size() == 0)
            return new HashedMap();

        ClassSection chooseXd = new ClassSection();//根据选择的学段，得到学制，得到年级列表
        for (ClassSection section : sectionList) {
            if (section.getId().equals(chooseXdId))
                chooseXd = section;
        }

        if (StringUtil.isEmpty(chooseXdId) && sectionList.size() > 0)//点击机构菜单，学段默认选择第一个
            chooseXd = sectionList.get(0);

        if (GukeerStringUtil.isNullOrEmpty(chooseXd.getSectionYear()))
            return new HashedMap();

        //nj list
        List<Map> njList = new ArrayList<>();//根据选择的学段生成的年级列表
        for (int i = 1; i <= chooseXd.getSectionYear(); i++) {
            Map njMap = new TreeMap();
            njMap.put("njId", i);
            njMap.put("njName", ConstantUtil.getValueByKeyAndFlag(i, "nj"));
            njList.add(njMap);
        }

        if (StringUtil.isEmpty(chooseNjId))
            chooseNjId = njList.get(0).get("njId").toString();
        //bj list
        List<GradeClass> bjList = classService.selectClassBySchoolIdXdNj(chooseSchoolId, chooseXd.getId(), NumberConvertUtil.convertS2I(chooseNjId));

        Map res = new HashMap();

        res.put("xdList", sectionList);//学段下拉
        res.put("njList", njList);//年级下拉

        if (bjList.size() > 0)
            res.put("bjList", bjList);//班级下拉

        res.put("xdChoose", chooseXd.getId());
        res.put("njChoose", chooseNjId);

        return res;
    }

}
