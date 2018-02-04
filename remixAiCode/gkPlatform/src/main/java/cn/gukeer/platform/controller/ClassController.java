package cn.gukeer.platform.controller;

import cn.gukeer.common.controller.BasicController;
import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.common.exception.ErrcodeException;
import cn.gukeer.common.security.AESencryptor;
import cn.gukeer.common.tld.GukeerStringUtil;
import cn.gukeer.common.utils.*;
import cn.gukeer.common.utils.excel.ExportExcel;
import cn.gukeer.common.utils.excel.ImportExcel;
import cn.gukeer.platform.common.ConstantUtil;
import cn.gukeer.platform.common.EnumeratedConstant;
import cn.gukeer.platform.modelView.*;
import cn.gukeer.platform.modelView.importExport.*;
import cn.gukeer.platform.persistence.entity.*;
import cn.gukeer.platform.service.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by conn on 2016/8/19.
 */
@Controller
@RequestMapping("/class")
public class ClassController extends BasicController {

    @Autowired
    ClassService classService;

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    PatriarchService patriarchService;

    @Autowired
    UserService userService;

    @Autowired
    SchoolService schoolService;

    @Autowired
    RoleService roleService;

    @Autowired
    AppService appService;


    //    学籍管理-家长信息页面
    @RequestMapping(value = "/parent/info/index", method = RequestMethod.GET)
    public String parentInfo(Model model) {
        String schoolId = getLoginUser().getSchoolId();
        String[] judge = {"", "", "", ""};
        SchoolView menuTree = classService.selectAndMakeTree(schoolId, judge,false);

        Map param = new HashMap();
        param.put("pageSize", 10);
        param.put("pageNum", 0);
        param.put("schoolId", schoolId);

        PageInfo<Map> pageInfo = classService.parentInfoList(param);

        model.addAttribute("schoolview", menuTree);
        model.addAttribute("focusNode", "school_" + schoolId);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("schoolId", schoolId);
        return "class/parentInfo";
    }

    //    学籍管理-家长信息页面操作
    @RequestMapping(value = "/parent/info/manage", method = RequestMethod.POST)
    public String parentInfoManage(HttpServletRequest request, Model model, String nodeList) {
        String classId = getParamVal(request, "classId");
        String stuName = getParamVal(request, "stname");
        String xd = getParamVal(request, "xd");
        String xq = getParamVal(request, "xq");
        String nj = getParamVal(request, "nj");
        String focusNode = getParamVal(request, "focusNode");
        String schoolId = getLoginUser().getSchoolId();
        int pageSize = getPageSize(request);
        int pageNum = getPageNum(request);

        classId = !StringUtils.isEmpty(classId) ? classId : "";
        xd = !StringUtils.isEmpty(xd) ? xd : "";
        xq = !StringUtils.isEmpty(xq) ? xq : "";
        nj = !StringUtils.isEmpty(nj) ? nj : "";

        SchoolView schoolview = new SchoolView();
        String[] judge = {classId, xd, String.valueOf(xq), String.valueOf(nj)};

        Map param = new HashMap();
        param.put("pageSize", pageSize);
        param.put("pageNum", pageNum);

        if (!"0".equals(schoolId))
            param.put("schoolId", schoolId);

        if (!"0".equals(xd))
            param.put("xd", xd);

        if (!StringUtils.isEmpty(stuName))
            param.put("stuName", "%" + stuName + "%");

        if (!"0".equals(xq))
            param.put("xq", xq);

        if (!"0".equals(nj))
            param.put("nj", nj);

        if (!"0".equals(classId))
            param.put("classId", classId);

        PageInfo<Map> pageInfo = classService.parentInfoList(param);

        if (nodeList == null || nodeList == "") {
            schoolview = classService.selectAndMakeTree(schoolId, judge,false);
            model.addAttribute("schoolview", schoolview);
        } else {
            model.addAttribute("nodeList", nodeList);
        }
        model.addAttribute("focusNode", focusNode);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("stuName", stuName);
        model.addAttribute("classId", classId);
        model.addAttribute("schoolId", schoolId);

        model.addAttribute("xd", xd);
        model.addAttribute("xq", xq);
        model.addAttribute("nj", nj);
        return "class/parentInfo";
    }

    //    学籍管理-家长信息 添加/详情 页面
    @RequestMapping(value = "/parent/info/add/index", method = RequestMethod.GET)
    public String parentInfoAdd(HttpServletRequest request, Model model) {
        String prim = getParamVal(request, "prim");
        if (StringUtils.isEmpty(prim)) {
            //新增
        } else {
            //修改，查看
            Map map = classService.selectParentByPrim(prim);
            model.addAttribute("parentInfo", map);
        }
        return "/class/parentAdd";
    }

    //    学籍管理-家长信息  新增,修改保存
    @ResponseBody
    @RequestMapping(value = "/parent/info/add/save", method = RequestMethod.POST)
    public ResponseEntity parentInfoSave(HttpServletRequest request) {
        String prim = getParamVal(request, "prim");
        String stuNum = getParamVal(request, "stuNum");
        String parentName = getParamVal(request, "parentName");
        String aaaa = getParamVal(request, "stuName");
        String relation = getParamVal(request, "relation");
        String work = getParamVal(request, "work");
        String workAt = getParamVal(request, "workAt");
        String gender = getParamVal(request, "gender");
        String guardian = getParamVal(request, "guardian");
        String parentPhone = getParamVal(request, "parentPhone");
        String lifeTogether = getParamVal(request, "lifeTogether");
        User user = getLoginUser();

        Map map = studentService.findStudentByStuNum(user.getSchoolId(), stuNum);
        String stuId = null;
        String schoolId = null;
        if (!GukeerStringUtil.isNullOrEmpty(map)) {
            stuId = map.get("id").toString();//关联studentId
            schoolId = map.get("school_id").toString();
        }

        Patriarch parent = new Patriarch();
        parent.setName(parentName);
        parent.setPinyin(CnToPyUtils.getPingYin(parentName));
        parent.setStudentSchoolId(schoolId);
        parent.setStudentId(stuId);
        parent.setWork(work);
        parent.setWorkAt(workAt);
        parent.setPhone(parentPhone);
        parent.setSfjhr(NumberConvertUtil.convertS2I(guardian));
        parent.setSfyqsh(NumberConvertUtil.convertS2I(lifeTogether));
        parent.setPatriarchFlag(NumberConvertUtil.convertS2I(relation));
        parent.setGender(NumberConvertUtil.convertS2I(gender));

        Map res = new HashMap();
        try {
            if (StringUtils.isEmpty(prim)) {
                parent.setId(PrimaryKey.get());
                parent.setCreateBy(user.getId());
                parent.setCreateDate(System.currentTimeMillis());
                patriarchService.insertPatriarch(parent);
            } else {
                parent.setId(prim);
                parent.setUpdateDate(System.currentTimeMillis());
                parent.setUpdateBy(user.getId());
                patriarchService.updatePatriarch(parent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.put("code", 1);
            res.put("msg", "操作失败");
            return new ResponseEntity(res, HttpStatus.EXPECTATION_FAILED);
        }

        res.put("code", 0);
        res.put("msg", parentName + " 操作成功");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    //    根据学号查询学生信息
    @RequestMapping(value = "/student/info/{num}", method = RequestMethod.POST)
    public ResponseEntity getStuInfo(@PathVariable String num) {

        Map res = studentService.findStudentByStuNum(getLoginUser().getSchoolId(), num);

        return new ResponseEntity(res, HttpStatus.OK);
    }

    //    学籍管理-删除家长信息
    @Transactional
    @RequestMapping(value = "/parent/delete", method = RequestMethod.POST)
    public ResponseEntity parentInfoDelete(String prims) {
        Map res = new HashMap();
        List<String> primList = ConstantUtil.splitWithOutNull(prims);
        try {
            for (String prim : primList) {
                classService.deleteParent(prim);//update del_flag to 1
            }

        } catch (Exception e) {
            res.put("code", 1);
            res.put("msg", "删除失败");
            return new ResponseEntity(res, HttpStatus.EXPECTATION_FAILED);
        }
        res.put("code", 0);
        res.put("msg", "删除" + primList.size() + "条数据成功");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    //    学籍管理-下载导入模板
    @RequestMapping(value = "/parent/template/download")
    public void parentMobanDownload(HttpServletResponse response) {
        try {
            String fileName = "家长信息导入模板.xlsx";
            List<IOParentView> list = Lists.newArrayList();
            String anno = "注释：橙色字段为必填项\n" +
                    "           1.关系：父亲，母亲，其他\n" +
                    "           2.家长性别：男  女\n" +
                    "           3.是否监护人：是  否\n" +
                    "           4.是否生活在一起：是 否\n";
            new ExportExcel(false,"家长信息", IOParentView.class, 2, anno, 1).setDataList(list).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    学籍管理-导入家长信息
    @ResponseBody
    @RequestMapping(value = "/parent/import", method = RequestMethod.POST)
    public ResponseEntity parentImport(@RequestParam(value = "file") MultipartFile file) throws IOException, InvalidFormatException, IllegalAccessException, InstantiationException {
        Long begin = System.currentTimeMillis();
        List<Patriarch> parentList = new ArrayList<Patriarch>();
        List<IOParentView> errorParentList = new ArrayList<>();
        IOParentView errorParent = new IOParentView();

        User user = getLoginUser();
        List<Student> studentList = studentService.selectStudentByclassId(user.getSchoolId(), null);//查询当前机构所有的学生

        Map<String, Student> studentMap = new HashMap<>();
        for (Student student : studentList) {
            if (!StringUtils.isEmpty(student.getXh())) {
                studentMap.put(student.getXh(), student);
            }
        }

        ImportExcel importExcel = new ImportExcel(file, 2, 0);
        List<IOParentView> list = importExcel.getDataList(IOParentView.class, 1);
        for (IOParentView parentView : list) {
            try {
                errorParent = parentView;

                Student student = studentMap.get(parentView.getStuNum());//学号：key  学生 value
                if (GukeerStringUtil.isNullOrEmpty(student))
                    throw new ErrcodeException("学号有误");

                if (!parentView.getStuName().equals(student.getXsxm()))
                    throw new ErrcodeException("姓名学籍号不匹配");

                Patriarch patriarch = new Patriarch();
                patriarch.setId(PrimaryKey.get());
                patriarch.setName(parentView.getParName());
                patriarch.setPinyin(CnToPyUtils.getPingYin(parentView.getParName()));
                if (!GukeerStringUtil.isNullOrEmpty(student)) {
                    patriarch.setStudentSchoolId(student.getSchoolId());
                    patriarch.setStudentId(student.getId());
                }
                patriarch.setWork(parentView.getWork());
                patriarch.setWorkAt(parentView.getWorkAt());
                patriarch.setPhone(parentView.getParentPhone());

                int gender = ConstantUtil.getKeyByValueAndFlag(parentView.getGender(), "xb");
                if (gender == 0)
                    throw new ErrcodeException("性别格式错误");
                patriarch.setGender(gender);

                int guardian = ConstantUtil.getKeyByValueAndFlag(parentView.getGuardian(), "yorn");
                if (guardian == 0)
                    throw new ErrcodeException("是否监护人格式错误");
                patriarch.setSfjhr(guardian);

                int lifeTogether = ConstantUtil.getKeyByValueAndFlag(parentView.getLifeTogether(), "yorn");
                if (lifeTogether == 0)
                    throw new ErrcodeException("是否一起生活格式错误");
                patriarch.setSfyqsh(lifeTogether);

                int parentFlag = ConstantUtil.getKeyByValueAndFlag(parentView.getRelation(), "par");
                if (parentFlag == 0)
                    throw new ErrcodeException("关系格式错误");
                patriarch.setPatriarchFlag(parentFlag);

                patriarch.setCreateBy(user.getId());
                patriarch.setCreateDate(System.currentTimeMillis());
                patriarch.setDelFlag(0);
                parentList.add(patriarch);
            } catch (Exception e) {
                errorParentList.add(errorParent);
                e.printStackTrace();
                continue;
            }
        }
        if (parentList.size() > 0)
            patriarchService.batchInsetPatriarch(parentList);
        Long end = System.currentTimeMillis();
        Map res = new HashMap();
        res.put("msg", "导入完成，共" + parentList.size() + "条成功，" + errorParentList.size() + "条失败,耗时" + (end - begin) / 1000 + "秒");
        res.put("errorList", errorParentList);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    //    学籍管理-下载导入错误的家长信息
    @RequestMapping(value = "/parent/error/export", method = RequestMethod.POST)
    public void errorParent(HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileName = "错误信息列表.xlsx";
            String anno = "注释：橙色字段为必填项\n" +
                    "           1.关系：父亲，母亲，其他\n" +
                    "           2.家长性别：男  女\n" +
                    "           3.是否监护人：是  否\n" +
                    "           4.是否生活在一起：是 否";

            String msg = getParamVal(request, "msg");
            JsonArray jsonArray = new JsonParser().parse(msg).getAsJsonArray();
            List<IOParentView> exportFile = new ArrayList<IOParentView>();
            for (JsonElement jsonElement : jsonArray) {
                IOParentView importBundling = GsonUtil.fromJson(jsonElement.getAsJsonObject(), IOParentView.class);
                exportFile.add(importBundling);
            }
            new ExportExcel(false,"家长信息", IOParentView.class, 2, anno, 1).setDataList(exportFile).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    学籍管理-导出家长学生关系
    @RequestMapping(value = "/parent/info/export", method = RequestMethod.POST)
    public void exportParent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String classId = getParamVal(request, "classId");
        String stuName = getParamVal(request, "stname");
        String xd = getParamVal(request, "xd");
        String xq = getParamVal(request, "xq");
        String nj = getParamVal(request, "nj");
        String prims = getParamVal(request, "choose");
        String schoolId = getLoginUser().getSchoolId();

        classId = !StringUtils.isEmpty(classId) ? classId : "";
        xd = !StringUtils.isEmpty(xd) ? xd : "";
        xq = !StringUtils.isEmpty(xq) ? xq : "";
        nj = !StringUtils.isEmpty(nj) ? nj : "";

        Map param = new HashMap();
        if (!"0".equals(schoolId))
            param.put("schoolId", schoolId);

        if (!"0".equals(xd))
            param.put("xd", xd);

        if (!StringUtils.isEmpty(stuName))
            param.put("stuName", "%" + stuName + "%");

        if (!"0".equals(xq))
            param.put("xq", xq);

        if (!"0".equals(nj))
            param.put("nj", nj);

        if (!"0".equals(classId))
            param.put("classId", classId);

        List<String> primList = ConstantUtil.splitWithOutNull(prims);
        if (primList.size() > 0)
            param.put("parId", primList);

        PageInfo<Map> pageInfo = classService.parentInfoList(param);
        List<Map> resMap = pageInfo.getList();

        List<OutputParentRelationView> exportFile = new ArrayList<>();
        for (Map map : resMap) {
            try {
                OutputParentRelationView view = new OutputParentRelationView();
                view.setStuNum(map.get("xh").toString());
                view.setStuName(map.get("xsxm").toString());
                view.setParName(map.get("parentName").toString());

                view.setRelation(ConstantUtil.getValueByKeyAndFlag(NumberConvertUtil.convertS2I(map.get("patriarch_flag").toString()), "par"));

                view.setWork(map.get("work").toString());
                view.setWorkAt(map.get("work_at").toString());
                view.setParentPhone(map.get("parentPhone").toString());

                view.setGender(ConstantUtil.getValueByKeyAndFlag(NumberConvertUtil.convertS2I(map.get("parentGender").toString()), "xb"));
                view.setGuardian(ConstantUtil.getValueByKeyAndFlag(NumberConvertUtil.convertS2I(map.get("sfjhr").toString()), "yorn"));
                view.setLifeTogether(ConstantUtil.getValueByKeyAndFlag(NumberConvertUtil.convertS2I(map.get("sfyqsh").toString()), "yorn"));

                view.setXq(map.get("schoolName").toString());
                view.setXd(map.get("sectionName").toString());
                view.setNj(ConstantUtil.getValueByKeyAndFlag(NumberConvertUtil.convertS2I(map.get("nj").toString()), "nj"));
                view.setClassName(map.get("className").toString());
                exportFile.add(view);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
        String fileName = "家长信息表格.xlsx";
        new ExportExcel(false,"家长信息", OutputParentRelationView.class, 1, null, 1).setDataList(exportFile).write(response, fileName).dispose();

    }

    //    学籍管理-学生管理页面
    @RequiresPermissions("class:student:view")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {
        String schoolId = getLoginUser().getSchoolId();
        String appid = getParamVal(request, "appId");
        SchoolView schoolview = new SchoolView();
        String[] judge = {"", "", "", ""};
        schoolview = classService.selectAndMakeTree(schoolId, judge,false);
        PageInfo<StudentView> pageInfo = studentService.selectStudentByChoose(schoolId, "", "", 0, 0, -1, "", 0, 10);


        if (null != pageInfo) {
            model.addAttribute("pageInfo", pageInfo);
        } else model.addAttribute("pageInfo", null);
        model.addAttribute("appId", appid);
        model.addAttribute("schoolview", schoolview);
        model.addAttribute("focusNode", "school_" + schoolId);
        model.addAttribute("schoolId", schoolId);

        List searchParam = new ArrayList();
        searchParam.add(schoolId);
        searchParam.add("");
        searchParam.add("");
        searchParam.add(0);
        searchParam.add(0);
        searchParam.add(-1);
        searchParam.add("");
        model.addAttribute("searchParam", searchParam);

        if (StringUtil.isNotEmpty(getParamVal(request, "appId")))
            request.getSession().setAttribute("xueJId", appid);

        return "class/studentManage";
    }

    //    学籍管理-学生管理页面
    @RequiresPermissions(value = {"class", "class:student:view"}, logical = Logical.OR)
    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String postIndex(HttpServletRequest request, Model model, String nodeList) {

        String _cId = getParamVal(request, "classId");
        String _sId = getParamVal(request, "schoolId");
        String name = getParamVal(request, "stname");
        String _status = getParamVal(request, "status");
        String _xd = getParamVal(request, "xd");
        String _xq = getParamVal(request, "xq");
        String _nj = getParamVal(request, "nj");
        String nowfocus = getParamVal(request, "focusNode");
        String schoolId = getLoginUser().getSchoolId();

        String xd;
        int xq, nj, status;
        String sid;
        String cid;
        if (_cId != null && _cId != "")
            cid = _cId;
        else cid = "";
        if (_sId != null && _sId != "")
            sid = _sId;
        else sid = "";
        if (_xd != null && _xd != "")
            xd = _xd;
        else xd = "";
        if (_xq != null && _xq != "")
            xq = NumberConvertUtil.convertS2I(_xq);
        else xq = 0;
        if (_nj != null && _nj != "")
            nj = NumberConvertUtil.convertS2I(_nj);
        else nj = 0;
        if (_status != null && _status != "")
            status = NumberConvertUtil.convertS2I(_status);
        else status = -1;
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);

        SchoolView schoolview = new SchoolView();
        String[] judge = {cid, xd, String.valueOf(xq), String.valueOf(nj)};
        if (nodeList == null || nodeList == "") {                 //可能性为0~
            schoolview = classService.selectAndMakeTree(schoolId, judge,false);
            model.addAttribute("schoolview", schoolview);
        } else {
            model.addAttribute("nodeList", nodeList);
        }

        PageInfo<StudentView> pageInfo = studentService.selectStudentByChoose(sid, cid, xd, nj, xq, status, name, pageNum, pageSize);

        if (null != pageInfo) {
            model.addAttribute("pageInfo", pageInfo);
        }
        model.addAttribute("classId", cid);
        model.addAttribute("schoolId", sid);
        model.addAttribute("stname", name);
        model.addAttribute("status", status);
        model.addAttribute("focusNode", nowfocus);

        model.addAttribute("xd", xd);
        model.addAttribute("xq", xq);
        model.addAttribute("nj", nj);

        List searchParam = new ArrayList();
        searchParam.add(sid);
        searchParam.add(cid);
        searchParam.add(xd);
        searchParam.add(nj);
        searchParam.add(xq);
        searchParam.add(status);
        searchParam.add(name);
        model.addAttribute("searchParam", searchParam);

        return "class/studentManage";
    }

    /**
     * 学籍管理-班级管理页面
     *
     * @param request
     * @param model
     * @return
     */
    @RequiresPermissions("class:banji:view")
    @RequestMapping(value = "/banji/index", method = RequestMethod.GET)
    public String banjiManage(HttpServletRequest request, Model model) {

        String schoolId = getLoginUser().getSchoolId();
        String[] judge = {"-1", "-1", "-1"};
        SchoolView schoolview = classService.makeClassTree(schoolId, judge);
        String focusNode = "";
        if (schoolview.getSections() != null) {
            for (ClassSectionView sectionView : schoolview.getSections()) {
                sectionView.setOpen(true);
                for (SchoolTypeView schoolTypeView : sectionView.getSchoolTypeView()) {
                    schoolTypeView.setOpen(true);
                    for (GradeClassView gradeClassView : schoolTypeView.getNjview()) {
                        {
                            focusNode = gradeClassView.getTid();
                            break;
                        }
                    }
                    break;
                }
                break;
            }
        }
//        school_28section_20xq_30nianji1
        if (focusNode.indexOf("xq_") >= 0 && focusNode.indexOf("nianji") >= 0 && focusNode.indexOf("section_") >= 0) {
            String _xq = focusNode.substring(focusNode.indexOf("xq_") + 3, focusNode.indexOf("nianji"));
            Integer nj = NumberConvertUtil.convertS2I(focusNode.substring(focusNode.indexOf("nianji") + 6));
            String xd = focusNode.substring(focusNode.indexOf("section_") + 8, focusNode.indexOf("xq_"));
            int pageNum = getPageNum(request);
            int pageSize = getPageSize(request);
            PageInfo<GradeClass> pageInfo = classService.selectClassBySchoolIdAndNj(schoolId, _xq, nj, pageNum, pageSize, xd);
            model.addAttribute("xd", xd);
            model.addAttribute("xq", _xq);
            model.addAttribute("nj", nj);
            model.addAttribute("pageInfo", pageInfo);
        } else {
            PageInfo<GradeClass> pageInfo = new PageInfo<>();
            model.addAttribute("pageInfo", pageInfo);
        }


        List<SchoolType> schoolTypes = schoolService.selectSchoolTypeBySchoolId(schoolId);
        List<ClassSection> classSections = classService.getAllClassSectionBySchoolId(schoolId);
        model.addAttribute("classSections", classSections);
        model.addAttribute("schoolTypes", schoolTypes);
        model.addAttribute("schoolview", schoolview);
        model.addAttribute("schoolId", schoolId);
        model.addAttribute("focusNode", focusNode);
        return "class/banJiManage";
    }

    /**
     * 修改班级 页面
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/banji/edit", method = RequestMethod.GET)
    public String banjiEdit(HttpServletRequest request, Model model) {

        String _id = getParamVal(request, "id");
        String focusNode = getParamVal(request, "focusNode");

        String id = _id;
        String focusNj = "";
        String focusXd = "";
        String focusXq = "";
        User user = getLoginUser();
        GradeClass gradeClass = classService.selectClassById(id);

        List<ClassSection> xdList = classService.getAllClassSectionBySchoolId(user.getSchoolId());
        List<SchoolType> schoolTypes = schoolService.selectSchoolTypeBySchoolId(user.getSchoolId());
        List<Integer> rxndList = new ArrayList<Integer>();

        Calendar cal = Calendar.getInstance();
        int nowYear = cal.get(Calendar.YEAR);
        rxndList.add(nowYear);
        for (int i = 0; i < 6; i++) {       //入学年度list
            nowYear--;
            rxndList.add(nowYear);
        }

        int year = 0;       //学制

        if (focusNode != "" && focusNode != null) {
            int xdStart = focusNode.indexOf("section_");
            int xqStart = focusNode.indexOf("xq_");
            int njStart = focusNode.indexOf("nianji");
            focusXd = focusNode.substring(xdStart + "section_".length(), xqStart);
            focusNj = focusNode.substring(njStart + "nianji".length());
            focusXq = focusNode.substring(xqStart + "xq_".length(), njStart);
        }
        if (!id.equals("")) {
            focusXd = gradeClass.getXd().toString();
            focusNj = gradeClass.getNj().toString();
            focusXq = gradeClass.getXq().toString();
        }
        for (int i = 0; i < xdList.size(); i++) {
            if (xdList.get(i).getId().equals(focusXd)) {
                year = xdList.get(i).getSectionYear();
            }
        }

        SchoolView schoolview = new SchoolView();
        String[] judge = {"-1", "-1", "-1"};
        schoolview = classService.makeClassTree(user.getSchoolId(), judge);
        model.addAttribute("schoolview", schoolview);
        model.addAttribute("focusXq", focusXq);
        model.addAttribute("focusXd", focusXd);
        model.addAttribute("focusNj", focusNj);
        model.addAttribute("sectionYear", year);
        model.addAttribute("rxndList", rxndList);
        model.addAttribute("gradeClass", gradeClass);
        model.addAttribute("xdList", xdList);
        model.addAttribute("schoolTypes", schoolTypes);


        return "class/edit";
    }

    /**
     * 学籍管理-班级管理页面
     *
     * @param request
     * @param model
     * @return
     */
    @RequiresPermissions("class:banji:view")
    @RequestMapping(value = "/banji/index", method = RequestMethod.POST)
    public String banjiManagePost(HttpServletRequest request, Model model, String nodeList) {

        String _sid = getParamVal(request, "schoolId");
        String _nj = getParamVal(request, "nj");
        String xd = getParamVal(request, "xd");
        String _xq = getParamVal(request, "xq");
        String focusNode = getParamVal(request, "focusNode");
//        JSONArray jsonArray = JSONArray.fromObject(nodeList);

        String sid = _sid;
        int nj = NumberConvertUtil.convertS2I(_nj);
        int xq = NumberConvertUtil.convertS2I(_xq);

        String schoolId = getLoginUser().getSchoolId();
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        PageInfo<GradeClass> pageInfo = classService.selectClassBySchoolIdAndNj(sid, _xq, nj, pageNum, pageSize, xd);
        List<SchoolType> schoolTypes = schoolService.selectSchoolTypeBySchoolId(schoolId);
        SchoolView schoolview = new SchoolView();
        if (nodeList == null || nodeList == "") {
            String[] judge = {xd, String.valueOf(xq), String.valueOf(nj)};
            schoolview = classService.makeClassTree(schoolId, judge);
            model.addAttribute("schoolview", schoolview);
        } else {
            model.addAttribute("nodeList", nodeList);
        }
        List<ClassSection> classSections = classService.getAllClassSectionBySchoolId(schoolId);
        model.addAttribute("nodeList", nodeList);
        model.addAttribute("classSections", classSections);
        model.addAttribute("schoolTypes", schoolTypes);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("schoolId", sid);
        model.addAttribute("xd", xd);
        model.addAttribute("xq", xq);
        model.addAttribute("nj", nj);
        model.addAttribute("focusNode", focusNode);
        return "class/banJiManage";
    }

    /**
     * 班级管理 删除班级
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/banji/delete", method = RequestMethod.POST)
    public ResultEntity banjidelete(HttpServletRequest request) {
        String _classId = getParamVal(request, "classid");
        String[] classIds = _classId.split(",");
        String failed = null;   //记录不能删除的班级名字
        List<GradeClass> finalList = new ArrayList<>();     //能够删除的班级
        for (int i = 0; i < classIds.length; i++) {
            String classId = classIds[i];
            String schoolId = getLoginUser().getSchoolId();
            List<Student> studentList = studentService.selectStudentByclassId(schoolId, classId);
            GradeClass gradeClass = classService.selectClassById(classId);
            if (gradeClass == null) return ResultEntity.newErrEntity("未找到班级");
            if (studentList.size() > 0) {
                if (failed == null) {
                    failed = gradeClass.getName();
                } else {
                    failed = failed + "," + gradeClass.getName();
                }
            } else {
                finalList.add(gradeClass);
            }
        }
        if (failed == null) {   //不存在不能删除班级时
            if (finalList.size() > 0) {
                for (GradeClass gradeClass : finalList) {
                    classService.changeDelFlag(gradeClass.getId());
                }
            }
            return ResultEntity.newResultEntity("删除成功", "");
        } else {        //存在任何不能删除的班级
            return ResultEntity.newErrEntity("删除失败，" + failed + "存在未删除学生");
        }
    }

    /**
     * 班级管理 新增/保存班级信息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/banji/save", method = RequestMethod.POST)
    public ResultEntity banjisave(HttpServletRequest request) {
        String bjlx = getParamVal(request, "bjlx");
        String _id = getParamVal(request, "id");
        String name = getParamVal(request, "name");
        String shortName = getParamVal(request, "shortName");
        String bh = getParamVal(request, "bh");
        String rxnd = getParamVal(request, "rxnd");
        String xd = getParamVal(request, "xd");
        String nj = getParamVal(request, "nj");
        String xq = getParamVal(request, "xq");
        GradeClass newclass = new GradeClass();
        Student student = new Student();
        User user = getLoginUser();
        //班级名称，在同校，同学段，同年级情况下，不能重复
        List<GradeClass> gradeClasses = classService.selectClassBySchoolIdXdNj(user.getSchoolId(), xd, NumberConvertUtil.convertS2I(nj));
        for (GradeClass eachClass : gradeClasses) {
            if (eachClass.getId().equals(_id))
                continue;
            if (eachClass.getName().equals(name)) {
                return ResultEntity.newErrEntity("新增失败，班级名称重复");
            }
        }
        String id = _id;
        if (!StringUtils.isEmpty(id)) {
            newclass.setId(id);
            newclass.setUpdateBy(user.getId());
            newclass.setUpdateDate(System.currentTimeMillis());
        } else {
            newclass.setDelFlag(0);
            newclass.setCreateBy(user.getId());
            newclass.setCreateDate(System.currentTimeMillis());
        }
        newclass.setSchoolId(user.getSchoolId());
        if (bh != null && bh != "")
            newclass.setBh(NumberConvertUtil.convertS2I(bh));
        if (rxnd != "" && rxnd != null)
            newclass.setRxnd(Long.parseLong(rxnd));
        if (bjlx != "" && bjlx != null)
            newclass.setBjlx(NumberConvertUtil.convertS2I(bjlx));
        newclass.setShortName(shortName);
        newclass.setName(name);
        newclass.setXq(xq);
        newclass.setNj(NumberConvertUtil.convertS2I(nj));
        newclass.setXd(xd);
        int count = classService.saveGradeClass(newclass);
        classService.saveClassAndStudent(id, xd, Integer.valueOf(nj));
        return ResultEntity.newResultEntity("保存成功", "");
    }

    @RequestMapping(value = "/teacherarrangement/index", method = RequestMethod.GET)
    public String arrangeTeacherGet(HttpServletRequest request, Model model) {
        String pageSizeMaster = getParamVal(request, "pageSizeMaster");
        String pageSizeCourse = getParamVal(request, "pageSizeCourse");
        String pageNumMaster = getParamVal(request, "pageNumMaster");
        String pageNumCourse = getParamVal(request, "pageNumCourse");
        String schoolId = getLoginUser().getSchoolId();
        SchoolView schoolview = new SchoolView();
        String[] judge = {"-1", "-1", "-1", "-1"};
        String focusNode = "";
        schoolview = classService.selectAndMakeTree(schoolId, judge,false);
        if (schoolview.getSections() != null) {
            for (ClassSectionView sectionView : schoolview.getSections()) {
                for (SchoolTypeView schoolTypeView : sectionView.getSchoolTypeView()) {
                    for (GradeClassView gradeClassView : schoolTypeView.getNjview()) {
                        {
                            if (gradeClassView.getBanjiview() != null) {
                                for (BanjiView banjiView : gradeClassView.getBanjiview()) {
                                    sectionView.setOpen(true);
                                    schoolTypeView.setOpen(true);
                                    gradeClassView.setOpen(true);
                                    banjiView.setOpen(true);
                                    focusNode = banjiView.getId();
                                    break;
                                }
                                break;
                            }
                        }
                    }
                    break;
                }
                break;
            }
        }
        List<Teacher> master = new ArrayList<Teacher>();
        List<Teacher> course = new ArrayList<Teacher>();

        PageInfo<Teacher> masterInfo = null;
        PageInfo<Teacher> courseInfo = null;
        if (focusNode != "") {
            String _focusNode = focusNode.substring("banji".length());
            if (_focusNode != null) {
                int pageSize = NumberConvertUtil.convertS2I(pageSizeMaster) == 0 ? 10 : NumberConvertUtil.convertS2I(pageSizeMaster);
                int pageNum = NumberConvertUtil.convertS2I(pageNumMaster);
                PageHelper.startPage(pageNum, pageSize);
                Page<Teacher> masterPage = (Page<Teacher>) classService.getTeacherByClassId(_focusNode, 1);
                masterInfo = new PageInfo<Teacher>(masterPage);

                pageSize = NumberConvertUtil.convertS2I(pageSizeCourse) == 0 ? 10 : NumberConvertUtil.convertS2I(pageSizeCourse);
                pageNum = NumberConvertUtil.convertS2I(pageNumCourse);
                PageHelper.startPage(pageNum, pageSize);
                Page<Teacher> coursePage = (Page<Teacher>) classService.getTeacherByClassId(_focusNode, 0);
                courseInfo = new PageInfo<Teacher>(coursePage);
            }
        }
        model.addAttribute("which", getParamVal(request, "which"));
        model.addAttribute("courseInfo", courseInfo);
        model.addAttribute("masterInfo", masterInfo);
        model.addAttribute("focusNode", focusNode);
        model.addAttribute("schoolview", schoolview);
        return "class/teacherarrangement";
    }

    @RequestMapping(value = "/teacherarrangement/index", method = RequestMethod.POST)
    public String arrangeTeacher(HttpServletRequest request, Model model) {
        String focusNode = getParamVal(request, "focusNode");
        String nodeList = getParamVal(request, "nodeList");
        String schoolId = getLoginUser().getSchoolId();
        SchoolView schoolview = new SchoolView();
        String[] judge = {"-1", "-1", "-1", "-1"};
        if (nodeList == null || nodeList == "") {
            schoolview = classService.selectAndMakeTree(schoolId, judge,false);
        }
        List<Teacher> master = new ArrayList<Teacher>();
        List<Teacher> course = new ArrayList<Teacher>();

        PageInfo<Teacher> masterInfo = null;
        PageInfo<Teacher> courseInfo = null;
        if (focusNode != "") {
            String _focusNode = focusNode.substring("banji".length());
            if (_focusNode != null) {
//                int pageSize = getPageSize(request);
//                int pageNum = getPageNum(request);
                String pageSizeMaster = getParamVal(request, "pageSizeMaster");
                String pageSizeCourse = getParamVal(request, "pageSizeCourse");
                String pageNumMaster = getParamVal(request, "pageNumMaster");
                String pageNumCourse = getParamVal(request, "pageNumCourse");
                int pageSize = NumberConvertUtil.convertS2I(pageSizeMaster) == 0 ? 10 : NumberConvertUtil.convertS2I(pageSizeMaster);
                int pageNum = NumberConvertUtil.convertS2I(pageNumMaster);

                PageHelper.startPage(pageNum, pageSize);
                Page<Teacher> masterPage = (Page<Teacher>) classService.getTeacherByClassId(_focusNode, 1);
                masterInfo = new PageInfo<Teacher>(masterPage);

                int _pageSize = NumberConvertUtil.convertS2I(pageSizeCourse) == 0 ? 10 : NumberConvertUtil.convertS2I(pageSizeCourse);
                int _pageNum = NumberConvertUtil.convertS2I(pageNumCourse);

                PageHelper.startPage(_pageNum, _pageSize);
                Page<Teacher> coursePage = (Page<Teacher>) classService.getTeacherByClassId(_focusNode, 0);
                courseInfo = new PageInfo<Teacher>(coursePage);
            }
        }
        model.addAttribute("which", getParamVal(request, "which"));
        model.addAttribute("nodeList", nodeList);
        model.addAttribute("courseInfo", courseInfo);
        model.addAttribute("masterInfo", masterInfo);
        model.addAttribute("focusNode", focusNode);
        model.addAttribute("schoolview", schoolview);
        return "class/teacherarrangement";
    }

    @RequestMapping(value = "/teacherarrangement/add")
    public String addTeacher(HttpServletRequest request, Model model) {
        model.addAttribute("which", getParamVal(request, "which"));
        model.addAttribute("focusNode", getParamVal(request, "focusNode"));
        return "class/addteacher";
    }

    @ResponseBody
    @RequestMapping(value = "/teacherarrangement/save", method = RequestMethod.POST)
    public ResultEntity saveTeacher(HttpServletRequest request) {
        String teacherIds = getParamVal(request, "teacherId");
        String _classId = getParamVal(request, "classId");
        String _type = getParamVal(request, "which");
        String classId = _classId;
        int type = NumberConvertUtil.convertS2I(_type);
        int num = 0;
        String[] teacherId = teacherIds.split(",");
        for (String _id : teacherId) {
            String id = _id;
            num += classService.saveTeacherClass(id, classId, type);
        }
        if (num > 0) {
            return ResultEntity.newResultEntity();
        } else {
            return ResultEntity.newErrEntity();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/teacherarrangement/delete", method = RequestMethod.POST)
    public ResultEntity deleteTeacher(HttpServletRequest request) {
        String teacherIds = getParamVal(request, "teacherId");
        String _classId = getParamVal(request, "classId");
        String _type = getParamVal(request, "which");
        String classId = _classId;
        int type = NumberConvertUtil.convertS2I(_type);
        int num = 0;
        String[] teacherId = teacherIds.split(",");
        for (String _id : teacherId) {
            String id = _id;
            num += classService.delTeacherClass(id, classId, type);
        }
        if (num > 0) {
            return ResultEntity.newResultEntity();
        } else {
            return ResultEntity.newErrEntity();
        }
    }

    /**
     * 学籍管理-学校设置页面
     *
     * @param request
     * @param model
     * @return
     */
    @RequiresPermissions("class:schoolSetting:view")
    @RequestMapping(value = "/schoolsetting/index", method = RequestMethod.GET)
    public String schoolSetting(HttpServletRequest request, Model model) {
        String which = getParamVal(request, "which");
        String schoolId = getLoginUser().getSchoolId();

        School school = schoolService.selectSchoolById(schoolId);

        List<SchoolType> schoolType = schoolService.selectSchoolTypeBySchoolId(schoolId);

        model.addAttribute("which", which);
        model.addAttribute("school", school);
        model.addAttribute("schoolType", schoolType);

        return "class/schoolSetting";
    }

    /**
     * 学校设置-校区信息保存
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/schoolType/save", method = RequestMethod.POST)
    public ResultEntity schoolTypeSave(HttpServletRequest request) {
        int count = 0;
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        String _id = getParamVal(request, "id");
        String _stname = getParamVal(request, "stname");
        String _stsort = getParamVal(request, "stsort");
        String[] stname = _stname.split(",");       //判断是否为空
        String[] stsort = _stsort.split(",");       //判断是否为空
        String[] id = _id.split(",");              //判断是否为空
        for (int i = 0; i < stname.length; i++) {       //字段为空不进入！
            String name = stname[i];
            int sort = NumberConvertUtil.convertS2I(stsort[i]);
            if (i < id.length && id[0] != "") {
                SchoolType schoolType = schoolService.selectSchoolTypeById(id[i]);
                schoolType.setName(name);
                schoolType.setSort(sort);
                schoolType.setUpdateBy(user.getId());
                schoolType.setUpdateDate(System.currentTimeMillis());
                count += schoolService.saveSchoolType(schoolType);
            } else {
                SchoolType schoolType = new SchoolType();
                schoolType.setSort(sort);
                schoolType.setSchoolId(schoolId);
                schoolType.setName(name);
                schoolType.setCreateBy(user.getId());
                schoolType.setCreateDate(System.currentTimeMillis());
                schoolType.setDelFlag(0);
                count += schoolService.saveSchoolType(schoolType);
            }
        }
        return ResultEntity.newResultEntity(count);
    }

    /**
     * 校区删除
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/schoolType/delete", method = RequestMethod.POST)
    public ResultEntity schoolTypeDelete(HttpServletRequest request) {

        String schoolId = getLoginUser().getSchoolId();
        String _id = getParamVal(request, "id");
        String id = _id;
        List<GradeClass> gradeClassList = classService.getClassBySchoolType(schoolId, _id);
        if (gradeClassList.size() > 0) {
            return ResultEntity.newErrEntity("删除失败  该校区下有存在未删除班级");
        } else {
            SchoolType schoolType = schoolService.selectSchoolTypeById(id);
            schoolType.setDelFlag(1);
            schoolService.saveSchoolType(schoolType);
            return ResultEntity.newResultEntity("删除成功", "");
        }
    }

    /**
     * 学籍管理-学校 save
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/school/save", method = RequestMethod.POST)
    public ResultEntity schoolSave(HttpServletRequest request) {

        String _id = getParamVal(request, "id");
        String name = getParamVal(request, "name", null);
        String fax = getParamVal(request, "fax", null);
        String ename = getParamVal(request, "ename", null);
        String email = getParamVal(request, "email", null);
       /* String deployUrl = getParamVal(request, "deployUrl", null);*/
        String zipCode = getParamVal(request, "zipCode", null);
        String master = getParamVal(request, "master", null);
        String phone = getParamVal(request, "phone", null);
        String address = getParamVal(request, "address", null);
        User user = getLoginUser();
        String id = _id;
        School school = new School();
        if (id != "") {
            school.setId(id);
            school.setUpdateBy(user.getId());
            school.setUpdateDate(System.currentTimeMillis());
        } else {
            school.setId(null);
            school.setCreateBy(user.getId());
            school.setCreateDate(System.currentTimeMillis());
        }
        school.setName(name);
        school.setFax(fax);
        school.setEname(ename);
        school.setEmail(email);
       /* school.setDeployUrl(deployUrl);*/
        school.setZipCode(zipCode);
        school.setMaster(master);
        school.setPhone(phone);
        school.setAddress(address);

        int count = schoolService.saveSchoolSettingInfo(school);

        return ResultEntity.newResultEntity(count);
    }


    /**
     * 学校设置-显示设置保存
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/school/displaySetting", method = RequestMethod.POST)
    public ResultEntity displaySetting(HttpServletRequest request) {
        String logoUrl = getParamVal(request, "headUrl");
        String _id = getParamVal(request, "id");
        String bgPicture = getParamVal(request, "headUrl2");
        String id = _id;
        if (id != "") {
            School school = schoolService.selectSchoolById(id);
            school.setLogo(logoUrl);
            school.setBgPicture(bgPicture);
            school.setUpdateBy(getLoginUser().getId());
            school.setUpdateDate(System.currentTimeMillis());
            if (school.getDeployUrl() != null && school.getDeployUrl() != "") {
                schoolService.saveAndClearSchoolcache(school, school.getDeployUrl());
            } else {
                schoolService.saveSchoolSettingInfo(school);
            }
            return ResultEntity.newResultEntity();
        } else return ResultEntity.newErrEntity();
    }

    /**
     * 学籍管理-学段管理页面
     *
     * @param request
     * @param model
     * @return
     */
    @RequiresPermissions("class:xueDuan:view")
    @RequestMapping(value = "/xueduan/index", method = RequestMethod.GET)
    public String xueDuanManage(HttpServletRequest request, Model model) {

        String schoolId = getLoginUser().getSchoolId();

        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);

        PageInfo<ClassSection> pageInfo = classService.getSectionContainDefault(schoolId, pageNum, pageSize);

        model.addAttribute("pageInfo", pageInfo);
        return "class/xueDuanManage";
    }

    /**
     * 删除学段
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/xueduan/del", method = RequestMethod.POST)
    public ResultEntity xueDuanDel(HttpServletRequest request) {

        String schoolId = getLoginUser().getSchoolId();
        String _id = getParamVal(request, "id");
        String id = _id;
        ClassSection classSection = classService.selectClassSectionById(id);
        String classId = classSection.getId();
        List<GradeClass> gradeClasses = classService.selectClassByXdAndSchoolId(classId, schoolId);
        if (gradeClasses.size() == 0) {
            if (classSection.getDelFlag() == 0) {
                classSection.setDelFlag(1);
            } else classSection.setDelFlag(0);

            int count = classService.saveClassSection(classSection);
            return ResultEntity.newResultEntity("设置成功", "");
        } else {
            return ResultEntity.newErrEntity("设置失败  请确定该学段无班级信息");
        }
    }

    /**
     * 学籍管理-编辑页面
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/xueDuan/edit", method = RequestMethod.GET)
    public String xueDuanEdit(HttpServletRequest request, Model model) {

        String _sectionId = getParamVal(request, "id");

        String id = _sectionId;

        ClassSection section = classService.selectClassSectionById(id);

        model.addAttribute("classSection", section);

        return "class/xueDuanEdit";
    }

    /**
     * 修改/新增学生学籍信息-保存
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/xueDuan/save", method = RequestMethod.POST)
    public ResultEntity xueDuanSave(HttpServletRequest request) {

        String _id = getParamVal(request, "id");
        String name = getParamVal(request, "name");
        String shortName = getParamVal(request, "shortName");
        String limitAge = getParamVal(request, "limitAge");
        String sectionYear = getParamVal(request, "sectionYear");
        String remarks = getParamVal(request, "remarks");
        User user = getLoginUser();

        List<ClassSection> classSectionList = classService.getAllClassSectionBySchoolId(user.getSchoolId());
        ClassSection section = new ClassSection();
        String id = _id;
        for (ClassSection classSection : classSectionList) {
            if (classSection.getName().equals(name) && !classSection.getId().equals(id)) {
                return ResultEntity.newErrEntity("学段名称重复！");
            }
        }
        if (id != "") {
            section.setId(id);
            section.setUpdateBy(user.getId());
            section.setUpdateDate(System.currentTimeMillis());
        } else {
            section.setCreateBy(user.getId());
            section.setCreateDate(System.currentTimeMillis());
        }
        section.setSchoolId(user.getSchoolId());
        section.setName(name);
        section.setShortName(shortName);
        section.setLimitAge(NumberConvertUtil.convertS2I(limitAge));
        section.setSectionYear(NumberConvertUtil.convertS2I(sectionYear));
        section.setRemarks(remarks);

        int count = classService.saveClassSection(section);

        return ResultEntity.newResultEntity(count);
    }

    /**
     * 学籍管理-学生账号管理页面
     *
     * @param request
     * @param model
     * @return
     */
    @RequiresPermissions("class:stuAccount:view")
    @RequestMapping(value = "/stuaccount/index", method = RequestMethod.GET)
    public String stuAccountMan(HttpServletRequest request, Model model) {
        String studentname = getParamVal(request, "studentname");
        try {
            studentname = java.net.URLDecoder.decode(studentname, "UTF-8");//解决非post访问的中文乱码问题。
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String schoolId = getLoginUser().getSchoolId();
        Map<String, String> param = new HashMap<String, String>();
        param.put("studentname", getParamVal(request, "studentname"));
        param.put("pageSizeHave", getParamVal(request, "pageSizeHave"));
        param.put("pageNumHave", getParamVal(request, "pageNumHave"));
        param.put("pageSizeNo", getParamVal(request, "pageSizeNo"));
        param.put("pageNumNo", getParamVal(request, "pageNumNo"));


        String password = "000000";
        List<Config> configs = schoolService.selectConfigByType("defaultPassword");
        if (configs.size() > 0) {
            for (Config config : configs) {
                if (config.getParamKey().equals("student")) {
                    password = config.getParamValue();//默认密码
                    break;
                }
            }
        }

        Map haveAccountMap = studentService.getStudentList(param, true, schoolId, 1);       //sf 1:学生，2:家长
        model.addAttribute("studentListHave", haveAccountMap.get("studentlist"));
        model.addAttribute("pageInfoHave", haveAccountMap.get("pageInfo"));

        Map noAccountMap = studentService.getStudentList(param, false, schoolId, 1);        //sf 1:学生，2:家长
        model.addAttribute("studentListNo", noAccountMap.get("studentlist"));
        model.addAttribute("pageInfoNo", noAccountMap.get("pageInfo"));

        model.addAttribute("password", password);
        model.addAttribute("studentname", studentname);
        model.addAttribute("whichPage", request.getParameter("whichPage"));//点击未生成翻页，停留在未生成的页面

        return "class/stuAccountManage";
    }

    /**
     * 生成学生账号
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/generator/stu", method = RequestMethod.POST)
    public ResultEntity generatorStudentAccount(HttpServletRequest request) {
        String password = getParamVal(request, "password");
        User userLogin = getLoginUser();
        /*查找未生成账号的学生*/
        List<Student> studentList = studentService.findNoAccountStudent(userLogin.getSchoolId());

        int count = 0; //记录生成的账号数
        int flag = 0; //记录拼音异常人数

        //获取插入之后的userList
        List<String> refIds = new ArrayList<String>();

        //遍历未生成账号的人员并生成账号
        for (Student student : studentList) {
            if (student.getXmpy() == null || student.equals("")) {
                flag += 1;
                continue;
            }
            User user = new User();
            user.setUserType(2);/*用户类型[0:root, 1:教师, 2:学生, 3:家长]*/
            user.setRefId(student.getId());/*引用id*/
            user.setSchoolId(student.getSchoolId());/*机构id*/
            user.setName(student.getXsxm());/*姓名*/
            user.setCreateBy(userLogin.getId());/*创建人*/
            user.setCreateDate(System.currentTimeMillis());/*创建时间*/
            user.setPhotoUrl(FileUtils.DEFAULT_HEAD_PHOTO);
            user.setPassword(AESencryptor.encryptCBCPKCS5Padding(password));
            String username = userService.findSameUserNameMax(student.getXmpy(), userLogin);
            user.setUsername(username);
            student.setAccount(username);
            userService.saveUser(user);
            studentService.saveExtension(student);
            refIds.add(student.getId());
            count += 1;
        }

        if (studentList.size() > 0) {
            //3:更新关联的role_user关联
            List<UserRole> userRoles = new ArrayList<UserRole>();
            List<User> users = userService.selectUserByCriteria(refIds, 2);
            for (User user : users) {
                UserRole userRole = new UserRole();
                userRole.setSchoolId(user.getSchoolId());
                userRole.setRoleId(EnumeratedConstant.DEFAULT_COMMON_STUDENT);
                userRole.setUserId(user.getId());
                userRoles.add(userRole);
            }
            userService.saveUserRoleBatch(userRoles);
        }
        if (flag > 0){
            return ResultEntity.newErrEntity("拼音为空！未导入" + flag + "位同学");
        }
        return ResultEntity.newResultEntity("成功生成" + count + "位学生账号", "");
    }

    /**
     * 学籍管理-家长账号管理页面
     *
     * @param request
     * @param model
     * @return
     */
    @RequiresPermissions("class:parAccount:view")
    @RequestMapping(value = "/paraccount/index", method = RequestMethod.GET)
    public String parAccountMan(HttpServletRequest request, Model model) {
        String studentname = getParamVal(request, "studentname");
        try {
            studentname = java.net.URLDecoder.decode(studentname, "UTF-8");//解决非post访问的中文乱码问题。
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String schoolId = getLoginUser().getSchoolId();
        Map<String, String> param = new HashMap<String, String>();
        param.put("name", getParamVal(request, "studentname"));
        param.put("pageSizeHave", getParamVal(request, "pageSizeHave"));
        param.put("pageNumHave", getParamVal(request, "pageNumHave"));
        param.put("pageSizeNo", getParamVal(request, "pageSizeNo"));
        param.put("pageNumNo", getParamVal(request, "pageNumNo"));


        String password = EnumeratedConstant.DEFAULT_MANAGER_PASSWORD;
        List<Config> configs = schoolService.selectConfigByType("defaultPassword");
        if (configs.size() > 0) {
            for (Config config : configs) {
                if (config.getParamKey().equals("parent")) {
                    password = config.getParamValue();//默认密码
                    break;
                }
            }
        }
        model.addAttribute("password", password);

//        Map haveAccountMap = getStudentList(request, true, 2);
        Map haveAccountMap = patriarchService.getParList(param, true, schoolId); //sf 1:学生，2:家长
        model.addAttribute("studentListHave", haveAccountMap.get("parlist"));
        model.addAttribute("pageInfoHave", haveAccountMap.get("pageInfo"));


//        Map noAccountMap = getStudentList(request, false, 2);
        Map noAccountMap = patriarchService.getParList(param, false, schoolId); //sf 1:学生，2:家长
        model.addAttribute("studentListNo", noAccountMap.get("parlist"));
        model.addAttribute("pageInfoNo", noAccountMap.get("pageInfo"));


        model.addAttribute("studentname", studentname);
        model.addAttribute("whichPage", request.getParameter("whichPage"));//点击未生成翻页，停留在未生成的页面

        return "class/parAccountManage";
    }

    /**
     * 生成家长账号
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/generator/patriarch", method = RequestMethod.POST)
    public ResultEntity generatorPatriarchAccount(HttpServletRequest request) {
        String password = getParamVal(request, "password");
        User loginUser = getLoginUser();
//        查找未生成账号的家长
        List<Patriarch> parentList = studentService.findNoAccountParent(loginUser.getSchoolId());
        int count = 0; //记录生成的账号数
        int flag = 0;
        //获取插入之后的userList
        List<String> refIds = new ArrayList<String>();

        //遍历未生成账号的人员并生成账号
        for (Patriarch par : parentList) {
            if (null == par.getPinyin() || par.equals("")) {
                flag += 1;
                continue;
            }
            User user = new User();
            user.setUserType(3);/*用户类型[0:root, 1:教师, 2:学生, 3:家长]*/
            user.setRefId(par.getId());/*引用id*/
            user.setSchoolId(par.getStudentSchoolId());/*机构id*/
            user.setName(par.getName());
            user.setCreateBy(loginUser.getId());/*创建人*/
            user.setCreateDate(System.currentTimeMillis());/*创建时间*/
            user.setPhotoUrl(FileUtils.DEFAULT_HEAD_PHOTO);
            user.setPassword(AESencryptor.encryptCBCPKCS5Padding(password));
            String username = userService.findSameUserNameMax(par.getPinyin(), loginUser);
            user.setUsername(username);
            par.setAccount(username);
            userService.saveUser(user);
            patriarchService.updatePatriarch(par);
            refIds.add(par.getId());
            count += 1;
        }
        if (parentList.size() > 0) {

            //3:更新关联的role_user关联
            List<UserRole> userRoles = new ArrayList<UserRole>();
            List<User> users = new ArrayList<>();
            if (refIds.size() > 0) {
                users = userService.selectUserByCriteria(refIds, 3);
            }
            for (User user : users) {
                UserRole userRole = new UserRole();
                userRole.setSchoolId(user.getSchoolId());
                userRole.setRoleId(EnumeratedConstant.DEFAULT_COMMON_PATRIARCH);
                userRole.setUserId(user.getId());
                userRoles.add(userRole);
            }
            userService.saveUserRoleBatch(userRoles);
        }
        if (flag > 0)
            return ResultEntity.newErrEntity("拼音为空！未导入" + flag + "位同学家长");
        return ResultEntity.newResultEntity("成功生成" + count + "位学生家长账号", "");
    }

    /**
     * 重置student，parent密码
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public void resetPassword(HttpServletRequest request) {
        String _id = getParamVal(request, "id");
        String _sf = getParamVal(request, "sf");
        String password = getParamVal(request, "password");
        String id = _id;
        int sf = NumberConvertUtil.convertS2I(_sf);
        User user = new User();
        user.setPassword(AESencryptor.encryptCBCPKCS5Padding(password));
        userService.updateUserByRefId(user, id, getLoginUser().getSchoolId(), sf);
    }

    /**
     * 学籍管理-角色管理页面
     *
     * @param request
     * @param model
     * @return
     */
    @RequiresPermissions("class:role:view")
    @RequestMapping(value = "/rolemanage/index", method = RequestMethod.GET)
    public String roleManage(HttpServletRequest request, Model model) {
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
        return "class/roleManage";
    }

    /**
     * 角色分配用户页面
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/roleUser/add")
    public String addUser(HttpServletRequest request, Model model) {
        String roleId = getParamVal(request, "roleId");
        List<Teacher> userList = teacherService.findHaveAccountTeacher(getLoginUser().getSchoolId());
        model.addAttribute("userList", new Gson().toJson(userList));
        model.addAttribute("roleId", roleId);
        return "class/addrole";
    }

    /**
     * 保存用户的学籍角色
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/roleUser/save", method = RequestMethod.POST)
    public ResultEntity addUserSave(HttpServletRequest request) {
        String roleId = getParamVal(request, "roleId");
        String userId = getParamVal(request, "userId");

        List<User> userList = userService.getTeacherByRefId(userId);
        User userLogin = getLoginUser();
        List<UserRole> userRoleList = new ArrayList<>();
        for (User user : userList) {
            List<UserRole> res = userService.findUserRoleByUserIdAndRoleId(user.getId(),roleId);
            if (res.size()>0)
                return ResultEntity.newErrEntity("该人员已经是管理员");

            UserRole userRole = new UserRole();
            userRole.setSchoolId(userLogin.getSchoolId());
            userRole.setUserId(user.getId());
            userRole.setRoleId(roleId);
            userRoleList.add(userRole);
        }
        try {
            userService.saveUserRoleBatch(userRoleList);
        }catch (Exception e){
            return ResultEntity.newErrEntity("分配失败");
        }
       return ResultEntity.newResultEntity("分配成功",null);
    }

    /**
     * 学籍角色分配 删除用户
     *
     * @param request
     */
    @ResponseBody
    @RequestMapping(value = "roleUser/delete", method = RequestMethod.POST)
    public void deleteRoleUser(HttpServletRequest request) {
        String userRefId = getParamVal(request, "userId");
        String roled = getParamVal(request, "roleId");
        String userId = userService.getTeacherByRefId(userRefId).get(0).getId();

        UserRole userRole = new UserRole();
        userRole.setSchoolId(getLoginUser().getSchoolId());
        userRole.setRoleId(roled);
        userRole.setUserId(userId);
        userService.deleteUserRole(userRole);
    }

    /**
     * 班级管理-导入班级页面
     *
     * @return
     */
    @RequestMapping(value = "/classImport", method = RequestMethod.GET)
    public String moBanImport() {

        return "class/classImport";
    }

    /**
     * 班级管理-导入班级
     *
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/bj/import", method = RequestMethod.POST)
    public ResponseEntity classImport(@RequestParam(value = "file") MultipartFile file) throws IOException, InvalidFormatException, IllegalAccessException, InstantiationException {
        Long begin = System.currentTimeMillis();
        List<GradeClass> gradeClassList = new ArrayList<GradeClass>();
        List<IOBJClassView> errorClassList = new ArrayList<>();
        IOBJClassView errorClass = new IOBJClassView();

        User user = getLoginUser();
        ImportExcel importExcel = new ImportExcel(file, 2, 0); //从2开始
        List<IOBJClassView> list = importExcel.getDataList(IOBJClassView.class, 1);

        List<SchoolType> schoolTypeList = schoolService.selectSchoolTypeBySchoolId(user.getSchoolId());
        List<ClassSection> classSections = classService.getAllClassSectionBySchoolId(user.getSchoolId());
        List<GradeClass> alreadyClass = classService.getAllClassBySchoolId(user.getSchoolId());

        Map<String, List<GradeClass>> gradeClassMap = new TreeMap<>();
        List<GradeClass> groupClass = new ArrayList<>();
        for (GradeClass gradeClass : alreadyClass) {
            String index = gradeClass.getXd() + gradeClass.getNj();
            if (gradeClassMap.containsKey(index)) {
                gradeClassMap.get(index).add(gradeClass);
            } else {
                List groupList = new ArrayList();
                groupList.add(gradeClass);
                gradeClassMap.put(index, groupList);
            }
        }

        for (IOBJClassView classView : list) {
            try {
                errorClass = classView;
                if (null == classView.getName() || classView.getName() == "") {
                    throw new ErrcodeException("班级名称为空");
                    //return ResultEntity.newErrEntity("导入失败，班级名称为空");
                }

                if (null == classView.getXd() || classView.getXd() == "") {
                    throw new ErrcodeException("学段为空");
                    //return ResultEntity.newErrEntity("导入失败，学段为空");
                }

                if (null == classView.getNj() || classView.getNj() == "") {
                    throw new ErrcodeException("年级为空");
                    //return ResultEntity.newErrEntity("导入失败，年级为空");
                }
                if (null == classView.getXq()) {
                    throw new ErrcodeException("校区为空");
                    // return ResultEntity.newErrEntity("导入失败，校区为空");
                }

                int schoolTypeFlag = 0;

                GradeClass gradeClass = new GradeClass();
                if (classView.getBh() != null)
                    gradeClass.setBh(NumberConvertUtil.convertS2I(classView.getBh()));
                if (classView.getBjlx() != null) {
                    gradeClass.setBjlx(ConstantUtil.getKeyByValueAndFlag(classView.getBjlx(), "bjlx"));
                } else {
                    gradeClass.setBjlx(ConstantUtil.getKeyByValueAndFlag("普通班级", "bjlx"));
                }
                if (classView.getRxnd() != null) {
                    gradeClass.setRxnd(Long.parseLong(classView.getRxnd()));
                } else {
                    Calendar cal = Calendar.getInstance();
                    int nowYear = cal.get(Calendar.YEAR);
                    gradeClass.setRxnd((long) (nowYear));
                }

                if (classView.getXq() != null) {
                    for (SchoolType schoolType : schoolTypeList) {
                        if (schoolType.getName().equals(classView.getXq())) {
                            if (schoolTypeFlag == 0) {
                                schoolTypeFlag = 1;
                                gradeClass.setXq(schoolType.getId().toString());
                            } else {
                                throw new ErrcodeException("校区名称设置重复");
                                //return ResultEntity.newErrEntity("导入失败，校区名称设置重复");
                            }
                        }
                    }
                    if (schoolTypeFlag == 0) {
                        throw new ErrcodeException("校区信息没有找到");
                        //return ResultEntity.newErrEntity("导入失败，校区信息没有找到：" + classView.getXq());
                    }
                }

                gradeClass.setShortName(classView.getShortName());
                gradeClass.setCreateBy(user.getId());
                gradeClass.setSchoolId(user.getSchoolId());
                gradeClass.setDelFlag(0);
                gradeClass.setCreateDate(System.currentTimeMillis());

                int classFlag = 0;
                for (ClassSection tempSection : classSections) {
                    if (tempSection.getName().equals(classView.getXd())) {
                        if (classFlag == 0) {
                            gradeClass.setXd(tempSection.getId());
                            classFlag = 1;
                            if (tempSection.getSectionYear() < ConstantUtil.getKeyByValueAndFlag(classView.getNj(), "nj")) {
                                throw new ErrcodeException("年级设置超出学制");
                                // return ResultEntity.newErrEntity("导入失败，年级设置超出学制");
                            }
                        } else {
                            throw new ErrcodeException("学段设置有误");
                            // return ResultEntity.newErrEntity("导入失败，学段设置有误");//重复学段名
                        }
                    }
                }
                if (classFlag == 0) {
                    throw new ErrcodeException("学段信息没有找到");
                    //return ResultEntity.newErrEntity("导入失败，学段信息没有找到：" + classView.getXd());//没有找到相应的学段
                }

                gradeClass.setNj(ConstantUtil.getKeyByValueAndFlag(classView.getNj(), "nj"));

                if (gradeClass.getNj()==0)
                    throw new ErrcodeException("年级格式有误");

                gradeClass.setName(classView.getName());

                List<GradeClass> gradeClasses = gradeClassMap.get(gradeClass.getXd() + gradeClass.getNj());
                if (!GukeerStringUtil.isNullOrEmpty(gradeClasses)) {
                    for (GradeClass eachClass : gradeClasses) {
                        if (eachClass.getName().equals(gradeClass.getName())) {
                            throw new ErrcodeException("已存在同名称班级");
                            //return ResultEntity.newErrEntity("导入失败，已存在同名称班级");
                        }
                    }
                }

                gradeClass.setId(PrimaryKey.get());
                gradeClass.setDelFlag(0);
                gradeClassList.add(gradeClass);
            } catch (Exception e) {
                errorClassList.add(errorClass);
                e.printStackTrace();
                continue;
            }

        }
        if (gradeClassList.size() > 0) {
            classService.batchInsertGradeClass(gradeClassList);
        }

        Long end = System.currentTimeMillis();
        Map res = new HashMap();
        res.put("msg", "导入完成，共" + gradeClassList.size() + "条成功，" + errorClassList.size() + "条失败,耗时" + (end - begin) / 1000 + "秒");
        res.put("errorList", errorClassList);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    //下载导入失败的班级列表
    @RequestMapping(value = "/class/error/export", method = RequestMethod.POST)
    public void errorClass(HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileName = "错误信息列表.xlsx";
            String anno = "注释：注释：橙色字段为必填项 \n" +
                    "           未填写班级类型，默认为普通班级；未填写入学年度，默认为本年度\n" +
                    "          1.入学年度格式：2016\n" +
                    "          2.年级用大写汉字，如：一年级\n" +
                    "          3.班级类型：普通班级、民族班、体育班级、外语班级、其他特殊班\n";

            String msg = getParamVal(request, "msg");
            JsonArray jsonArray = new JsonParser().parse(msg).getAsJsonArray();
            List<IOBJClassView> exportFile = new ArrayList<IOBJClassView>();
            for (JsonElement jsonElement : jsonArray) {
                IOBJClassView importBundling = GsonUtil.fromJson(jsonElement.getAsJsonObject(), IOBJClassView.class);
                exportFile.add(importBundling);
            }
            new ExportExcel(false,"班级数据", IOBJClassView.class, 2, anno, 1).setDataList(exportFile).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 导入页面
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/fileImport", method = RequestMethod.GET)
    public String moBan2Import(HttpServletRequest request, Model model) {
        String submitUrl = getParamVal(request, "url");
        model.addAttribute("url", submitUrl);
        return "class/studentImport";
    }

    /**
     * 学生管理-批量导入学生
     *
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/student/import", method = RequestMethod.POST)
    public ResponseEntity studentImport(@RequestParam(value = "file") MultipartFile file) throws IOException, InvalidFormatException, IllegalAccessException, InstantiationException {
        Long begin = System.currentTimeMillis();
        List<Student> studentList = new ArrayList<Student>();
        List<IOStudentView> errorStudentList = new ArrayList<>();
        IOStudentView errorStudent = new IOStudentView();

        User user = getLoginUser();
        //获得学段
        List<ClassSection> classSections = classService.getAllClassSectionBySchoolId(user.getSchoolId());
        List<GradeClass> gradeClasses = classService.getAllClassBySchoolId(user.getSchoolId());

        ImportExcel importExcel = new ImportExcel(file, 2, 0); //从2开始
        List<IOStudentView> list = importExcel.getDataList(IOStudentView.class, 1);
        for (IOStudentView studentView : list) {
            try {
                errorStudent = studentView;
                if (StringUtils.isEmpty(studentView.getName())) {
                    throw new ErrcodeException("学生名称为空");
                }
                if (StringUtils.isEmpty(studentView.getPinyin())) {
                    CnToPyUtils.getPingYin(studentView.getName());
                }
                if (StringUtils.isEmpty(studentView.getXh())) {
                    throw new ErrcodeException("学号为空");
                }
                if (StringUtils.isEmpty(studentView.getBj())) {
                    throw new ErrcodeException("班级名称为空");
                }
                if (StringUtils.isEmpty(studentView.getXd())) {
                    throw new ErrcodeException("学段为空");
                }
                if (StringUtil.isEmpty(studentView.getNj())) {
                    throw new ErrcodeException("年级为空");
                }


                Student student = new Student();
                int classFlag = 0;
                for (ClassSection tempSection : classSections) {
                    if (tempSection.getName().equals(studentView.getXd())) {
                        if (classFlag == 0) {
                            student.setXd(tempSection.getId());
                            classFlag = 1;
                        } else {
                            throw new ErrcodeException("学段设置有误");
                        }
                    }
                }
                if (classFlag == 0)
                    throw new ErrcodeException("学段信息没有找到");

                String xmpy = studentView.getPinyin();              //学生拼音设置，为空时系统判断
                if (xmpy == "" || xmpy == null) {
                    xmpy = CnToPyUtils.getPingYin(studentView.getName());
                }
                student.setXmpy(xmpy);
                student.setNj(ConstantUtil.getKeyByValueAndFlag(studentView.getNj(), "nj"));
                student.setCreateBy(user.getId());
                student.setCreateDate(System.currentTimeMillis());
                student.setSchoolId(user.getSchoolId());
                student.setXsxm(studentView.getName());
                if (studentView.getGender() != null && studentView.getGender() != "") {
                    int temp = ConstantUtil.getKeyByValueAndFlag(studentView.getGender(), "xb");
                    student.setXsxb(temp);
                }
                student.setYxzjh(studentView.getYxzjh());
                student.setXh(studentView.getXh());
                student.setJyid(studentView.getJyid());
                student.setGb(studentView.getGb());
                student.setMz(studentView.getMz());
                student.setJg(studentView.getJg());
                student.setHkszd(studentView.getHkszdXX());
                student.setXjzd(studentView.getXzz());
                if (StringUtil.isNotEmpty(studentView.getYxzjlx()))
                    student.setYxzjlx(ConstantUtil.getKeyByValueAndFlag(studentView.getYxzjlx(), "cardTyp"));
                if (StringUtil.isNotEmpty(studentView.getRxnd()))
                    student.setRxrq(DateUtils.yyyyMMddToMillis(studentView.getRxnd()));
                if (StringUtil.isNotEmpty(studentView.getXslb()))
                    student.setXslb(ConstantUtil.getKeyByValueAndFlag(studentView.getXslb(), "xslb"));
                if (StringUtil.isNotEmpty(studentView.getZzmm()))
                    student.setZzmm(ConstantUtil.getKeyByValueAndFlag(studentView.getZzmm(), "zzmm"));
                if (StringUtil.isNotEmpty(studentView.getStatus()))
                    student.setStatus(ConstantUtil.getKeyByValueAndFlag(studentView.getStatus(), "zxzt"));
                else student.setStatus(ConstantUtil.getKeyByValueAndFlag("0", "zxzt"));
                if (StringUtil.isNotEmpty(studentView.getHkxz()))
                    student.setHkxz(ConstantUtil.getKeyByValueAndFlag(studentView.getHkxz(), "hkxz").toString());
                if (StringUtil.isNotEmpty(studentView.getLydq()))
                    student.setLydq(ConstantUtil.getKeyByValueAndFlag(studentView.getLydq(), "lydq").toString());
                if (StringUtil.isNotEmpty(studentView.getSfbshk()))
                    student.setSfbshk(ConstantUtil.getKeyByValueAndFlag(studentView.getSfbshk(), "yorn"));
                if (StringUtil.isNotEmpty(studentView.getCsrq()))
                    student.setZslb(ConstantUtil.getKeyByValueAndFlag(studentView.getZslb(), "zslb"));
                if (StringUtil.isNotEmpty(studentView.getCsrq()))
                    student.setCsrq(DateUtils.yyyyMMddToMillis(studentView.getCsrq().replace("-", "")));

                //获取classId
                String xd = student.getXd();
                int nj = student.getNj();
                String className = studentView.getBj();
                int flag = 0;

                for (GradeClass eachClass : gradeClasses) {
                    if (eachClass.getXd().equals(xd) && eachClass.getName().equals(className) && eachClass.getNj() == nj) {       //要不要去空格呢~
                        if (flag == 0) {
                            student.setClassId(eachClass.getId());
                            flag = 1;
                        } else {          //如果出现同学校，同学段，同年级，班级名称相同情况，return 0；
                            throw new ErrcodeException("班级设置有误");
                            // return ResultEntity.newErrEntity("导入失败，班级设置有误");//出现重名班级
                        }
                    }
                }
                if (flag == 0) {
                    throw new ErrcodeException("班级信息没有找到");
                    //return ResultEntity.newErrEntity("导入失败，班级信息没有找到：" + studentView.getBj());//没有找到对应班级
                }
                student.setDelFlag(0);
                student.setId(PrimaryKey.get());
                studentList.add(student);
            } catch (Exception e) {
                errorStudentList.add(errorStudent);
                e.printStackTrace();
                continue;
            }
        }

        /*for (int i = 0; i < studentList.size(); i++)
            studentService.save(studentList.get(i));*/
        if (studentList.size() > 0) {
            studentService.batchInsertStudent(studentList);
        }

        Long end = System.currentTimeMillis();
        Map res = new HashMap();
        res.put("msg", "导入完成，共" + studentList.size() + "条成功，" + errorStudentList.size() + "条失败,耗时" + (end - begin) / 1000 + "秒");
        res.put("errorList", errorStudentList);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    //下载导入失败的student列表
    @RequestMapping(value = "/student/error/export", method = RequestMethod.POST)
    public void errorTeacher(HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileName = "错误信息列表.xlsx";
            String anno = "注释：注释：橙色字段为必填项 \n" +
                    "           不填写在校状态默认为在籍在校\n" +
                    "          1：性别：男、女                                                                                                    2：政治面貌：群众、团员、党员\n" +
                    "          3：来源地区: 区县内、省市内、省市外                                                                     4：本市对待： 是、否\n" +
                    "          5：学生类别：普通学生、随班就读生、残障学生、其他                                             6：有效证件类别：身份证 护照\n" +
                    "          7：年级：一年级、二年级、三年级、四年级、五年级、六年级                                   8：日期格式：yyyy/mm/dd,例如：2016/9/1\n" +
                    "          9：招生类别：普通入学、民族班、体育特招、外校转入、恢复入学资格、外籍、其他           10:在校状态:在籍在校、在籍离校、在校不在籍、不在籍不在校\n" +
                    "          11:入学年度：2017";

            String msg = getParamVal(request, "msg");
            JsonArray jsonArray = new JsonParser().parse(msg).getAsJsonArray();
            List<IOStudentView> exportFile = new ArrayList<IOStudentView>();
            for (JsonElement jsonElement : jsonArray) {
                IOStudentView importBundling = GsonUtil.fromJson(jsonElement.getAsJsonObject(), IOStudentView.class);
                exportFile.add(importBundling);
            }
            new ExportExcel(false,"学生信息", IOStudentView.class, 2, anno, 1).setDataList(exportFile).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 班级导入模板下载
     *
     * @param response
     * @return
     */
    @RequestMapping(value = "/bjTemplate")
    public String bjTemplate(HttpServletResponse response) {
        try {
            String fileName = "班级导入模板.xlsx";
            List<GradeClass> list = Lists.newArrayList();
            String anno = "注释：注释：橙色字段为必填项 \n" +
                    "           未填写班级类型，默认为普通班级；未填写入学年度，默认为本年度\n" +
                    "          1.入学年度格式：2016\n" +
                    "          2.年级用大写汉字，如：一年级\n" +
                    "          3.班级类型：普通班级、民族班、体育班级、外语班级、其他特殊班\n";
            new ExportExcel(false,"班级数据", IOBJClassView.class, 2, anno, 1).setDataList(list).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 学生导入模板下载
     *
     * @param response
     * @return
     */
    @RequestMapping(value = "/studentTemplate")
    public String studentTemplate(HttpServletResponse response) {
        try {
            String fileName = "学生导入模板.xlsx";
            List<Student> list = Lists.newArrayList();
            String anno = "注释：注释：橙色字段为必填项 \n" +
                    "           不填写在校状态默认为在籍在校\n" +
                    "          1：性别：男、女                                                                                                    2：政治面貌：群众、团员、党员\n" +
                    "          3：来源地区: 区县内、省市内、省市外                                                                     4：本市对待： 是、否\n" +
                    "          5：学生类别：普通学生、随班就读生、残障学生、其他                                             6：有效证件类别：身份证 护照\n" +
                    "          7：年级：一年级、二年级、三年级、四年级、五年级、六年级                                   8：日期格式：yyyyMMdd,例如：20160901\n" +
                    "          9：招生类别：普通入学、民族班、体育特招、外校转入、恢复入学资格、外籍、其他           10:在校状态:在籍在校、在籍离校、在校不在籍、不在籍不在校";
            new ExportExcel(false,"学生数据", IOStudentView.class, 2, anno, 1).setDataList(list).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 学生导出-选择到处字段弹出框
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/stuexport")
    public String studentExport(HttpServletRequest request, Model model) {
        String students = getParamVal(request, "students");
        if (students != "") {
            try {
                students = URLDecoder.decode(students, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("students", students);
        return "class/studentExport";
    }

    /**
     * 学生导出功能
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public void exportFile(HttpServletRequest request, HttpServletResponse response) {
        try {
            String students = URLDecoder.decode(getParamVal(request, "students"), "UTF-8");
            String _header = getParamVal(request, "header");
            String header = "";

            try {
                header = java.net.URLDecoder.decode(_header, "UTF-8");//解决非post访问的中文乱码问题。
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            List<StudentView> dataList = new ArrayList<>();
            String fileName = "学生信息.xlsx";
            List<OutputStudentView> stuList = new ArrayList<OutputStudentView>();
            if (students.indexOf("]") > 0) {
                List<String> searchParam = ConstantUtil.searchParam(students);
                if (searchParam.size() >= 7) {
//                    sid, cid, xd, nj, xq, status, name, pageNum, pageSize);
                    //  (schoolId, "", "", 0, 0, -1, "", 0, 10);
                    PageInfo<StudentView> studentSear = studentService.selectStudentByChoose(searchParam.get(0).trim(), searchParam.get(1).trim(), searchParam.get(2).trim(),
                            NumberConvertUtil.convertS2I(searchParam.get(3).trim()), NumberConvertUtil.convertS2I(searchParam.get(4).trim()),
                            NumberConvertUtil.convertS2I(searchParam.get(5).trim()), searchParam.get(6).trim(), 0, -1);
                    dataList = studentSear.getList();
                }
            } else {
                dataList = studentService.selectBatchStudents(ConstantUtil.splitWithOutNull(students), getLoginUser().getSchoolId());
            }

            //获取数据
            if (dataList.size() > 0) {
                for (Student student : dataList) {
                    //学生信息
                    String stuName = student.getXsxm();
                    String yxzjh = student.getYxzjh();
//                    String xjh = student.getXjh();
                    String xh = student.getXh();
                    String jyid = student.getJyid();//教育id
                    String qgid = student.getQgxjh();
                    String pinyin = student.getXmpy();
                    String gb = student.getGb();
                    String mz = student.getMz();
                    String jg = student.getJg();
                    String xzz = student.getXjzd();
                    String hkszdxx = student.getHkszd();
                    String rxnd = "", xslb = "", zzmm = "", status = "", hkxz = "", sfbshk = "", csrq = "", zslb = "", xqName = "", yxzjlx = "", gender = "", lydq = "";
                    String sectionName = "";
                    String njName = "";
                    String xq = "";
                    String bjName = "";
                    //家长信息
                    String faname = "";
                    String fawork = "";
                    String faworkat = "";
                    String faphone = "";
                    String maname = "";
                    String mawork = "";
                    String maworkat = "";
                    String maphone = "";

                    ClassSection classSection = classService.selectClassSectionById(student.getXd());
                    if (classSection != null) {
                        sectionName = classSection.getName();
                    }

                    GradeClass gradeClass = classService.selectClassById(student.getClassId());
                    if (gradeClass != null) {
                        xq = gradeClass.getXq();
                        bjName = gradeClass.getName();
                    }
                    if (xq != null && xq != "") {
                        xqName = schoolService.selectSchoolTypeById(xq).getName();//校区
                    }

                    if (student.getNj() != null && student.getNj() > 0 && student.getNj() < 10) {
                        njName = ConstantUtil.getValueByKeyAndFlag(student.getNj(), "nj");//年级
                    }

                    if (student.getXsxb() != null && student.getXsxb() != 0) {
                        gender = ConstantUtil.getValueByKeyAndFlag(student.getXsxb(), "xb");//性别
                    }

                    if (student.getYxzjlx() != null && student.getYxzjlx() != 0) {
                        yxzjlx = ConstantUtil.getValueByKeyAndFlag(student.getYxzjlx(), "cardTyp");//有效证件类型
                    }

                    if (student.getRxrq() != null && student.getRxrq() != 0) {
                        rxnd = DateUtils.millsToyyyyMMdd(student.getRxrq()).toString();//入学时间
                    }
                    if (student.getXslb() != null && student.getXslb() != -1) {
                        xslb = ConstantUtil.getValueByKeyAndFlag(student.getXslb(), "xslb");//学生类别
                    }
                    if (student.getZzmm() != null && student.getZzmm() != -1) {
                        zzmm = ConstantUtil.getValueByKeyAndFlag(student.getZzmm(), "zzmm");//政治面貌
                    }
                    if (student.getStatus() != null && student.getStatus() != -1) {
                        status = ConstantUtil.getValueByKeyAndFlag(student.getStatus(), "zxzt");//在校状态
                    }
                    if (student.getHkxz() != null && student.getHkxz() != "") {
                        hkxz = ConstantUtil.getValueByKeyAndFlag(NumberConvertUtil.convertS2I(student.getHkxz()), "hkxz");//户口性质
                    }
                    if (student.getSfbshk() != null && student.getSfbshk() != 0) {
                        sfbshk = ConstantUtil.getValueByKeyAndFlag(student.getSfbshk(), "yorn");//是否本市户口
                    }
                    if (student.getCsrq() != null && student.getCsrq() != 0) {
                        csrq = DateUtils.millsToyyyyMMdd(student.getCsrq()).toString();//出生日期
                    }
                    if (student.getZslb() != null && student.getZslb() != -1) {
                        zslb = ConstantUtil.getValueByKeyAndFlag(student.getZslb(), "zslb");//招生类别
                    }
                    if (student.getLydq() != null && student.getLydq() != "-1") {
                        lydq = ConstantUtil.getValueByKeyAndFlag(NumberConvertUtil.convertS2I(student.getLydq()), "lydq");//来源地区
                    }
                    Patriarch fa = patriarchService.findPatriarchByStudentId(student.getId(), 1);//第二个参数为家长标识:patriarch_flag
                    Patriarch ma = patriarchService.findPatriarchByStudentId(student.getId(), 2);

                    if (fa != null) {
                        faname = fa.getName();
                        faphone = fa.getPhone();
                        fawork = fa.getWork();
                        faworkat = fa.getWorkAt();
                    }
                    if (ma != null) {
                        maname = ma.getName();
                        maphone = ma.getPhone();
                        mawork = ma.getWork();
                        maworkat = ma.getWorkAt();
                    }

                    OutputStudentView studentView = new OutputStudentView();
                    studentView.setXd(sectionName);
                    studentView.setXq(xqName);
                    studentView.setNj(njName);
                    studentView.setBj(bjName);
                    studentView.setName(stuName);
                    studentView.setGender(gender);
                    studentView.setYxzjlx(yxzjlx);
                    studentView.setYxzjh(yxzjh);
//                    studentView.setXjh(xjh);
                    studentView.setXh(xh);
                    studentView.setJyid(jyid);
                    studentView.setPinyin(pinyin);
                    studentView.setRxnd(rxnd);
                    studentView.setXslb(xslb);
                    studentView.setZzmm(zzmm);
                    studentView.setStatus(status);
                    studentView.setHkxz(hkxz);
                    studentView.setSfbshk(sfbshk);
                    studentView.setCsrq(csrq);
                    studentView.setZslb(zslb);
                    studentView.setGb(gb);
                    studentView.setMz(mz);
                    studentView.setJg(jg);
                    studentView.setXzz(xzz);
                    studentView.setLydq(lydq);
                    studentView.setHkszdXX(hkszdxx);

                    studentView.setFaname(faname);
                    studentView.setFaphone(faphone);
                    studentView.setFawork(fawork);
                    studentView.setFaworkat(faworkat);
                    studentView.setManame(maname);
                    studentView.setMaphone(maphone);
                    studentView.setMawork(mawork);
                    studentView.setMaworkat(maworkat);

                    stuList.add(studentView);
                }
            }

            //获取家长数据


            //获取表头数据
            String[] head = header.split(",");
            List<String> headerList = new ArrayList<String>();
            for (int i = 0; i < head.length; i++) {
                headerList.add(head[i]);
            }
            new ExportExcel("学生信息", headerList, "", 1).setDataListByHeader(stuList, headerList)
                    .write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
