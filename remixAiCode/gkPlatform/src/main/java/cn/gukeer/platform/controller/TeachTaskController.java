package cn.gukeer.platform.controller;

import cn.gukeer.classcard.service.ClassCardService;
import cn.gukeer.common.controller.BasicController;
import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.common.exception.ErrcodeException;
import cn.gukeer.common.tld.GukeerStringUtil;
import cn.gukeer.common.utils.GsonUtil;
import cn.gukeer.common.utils.NumberConvertUtil;
import cn.gukeer.common.utils.PrimaryKey;
import cn.gukeer.common.utils.excel.ExportExcel;
import cn.gukeer.common.utils.excel.ImportExcel;
import cn.gukeer.platform.common.ConstantUtil;
import cn.gukeer.platform.modelView.*;
import cn.gukeer.platform.modelView.importExport.*;
import cn.gukeer.platform.persistence.entity.*;
import cn.gukeer.platform.service.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedHashTreeMap;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/teach/task")
public class TeachTaskController extends BasicController {
    @Autowired
    TeachTaskService teachTaskService;

    @Autowired
    SchoolService schoolService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    ClassService classService;

    @Autowired
    ClassCardService classCardService;

    /**
     * 教务管理-课程管理
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/course/index")
    public String courseIndex(HttpServletRequest request, Model model) {

        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        User user = getLoginUser();
        String schoolId = user.getSchoolId();

        String cycleYear = getParamVal(request, "cycleYear");
        String cycleSemester = getParamVal(request, "cycleSemester");


        //根据学年学期获得周期
        TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), user.getSchoolId());
        //获得周期列表，补全两个下拉框
        List<TeachCycle> cycleList = teachTaskService.getCycleList(user.getSchoolId());
        if (GukeerStringUtil.isNullOrEmpty(teachCycle.getId()))
            if (cycleList.size() > 0)
                teachCycle = cycleList.get(0);
        List<TeachCycle> semesterList = new ArrayList<>();
        if (StringUtil.isEmpty(cycleYear))
            cycleYear = teachCycle.getCycleYear();
        Map yearMap = new LinkedHashMap();
        for (TeachCycle cycle : cycleList) {
            if (cycleYear.equals(cycle.getCycleYear()))
                semesterList.add(cycle);
            yearMap.put(cycle.getCycleYear(), cycle);
        }

        Map param = new HashMap();
        param.put("pageNum", pageNum);
        param.put("pageSize", pageSize);
        param.put("schoolId", schoolId);
        param.put("cycleYear", cycleYear);
        param.put("cycleId", teachCycle.getId());
        //查询所有的课程
        PageInfo<CourseView> coursePageInfo = new PageInfo<>();
        if (StringUtils.isNotEmpty(teachCycle.getId())) {
            coursePageInfo = teachTaskService.getAllCourseListByParam(param);
        }

        model.addAttribute("teachCycle", teachCycle);
        model.addAttribute("cycleYear", cycleYear);
        model.addAttribute("cycleSemester", cycleSemester);
        model.addAttribute("semesterList", semesterList);
        model.addAttribute("yearList", yearMap.keySet());
        model.addAttribute("coursePageInfo", coursePageInfo);
        return "teachTask/course";
    }

    //课程编辑、增加的弹窗
    @RequestMapping(value = "/course/pop")
    public String courseAddPop(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        String courseId = getParamVal(request, "id");
        String cycleId = getParamVal(request, "cycleId");

        //查询所有的标准课程类型
        List<StandardCourse> standardCourseList = teachTaskService.findAllStandardCourseBySchoolId(schoolId);

        //查询所有的教室类型
        List<RoomType> roomTypeList = teachTaskService.getAllRoomTypeBySchoolId(schoolId);
        //根据学校查询所有的教学周期
        List<TeachCycle> teachCycleList = teachTaskService.getCycleList(schoolId);

        //获得周期列表，补全两个下拉框
        List<TeachCycle> cycleList = teachTaskService.getCycleList(user.getSchoolId());
        TeachCycle teachCycle = new TeachCycle();
        if (GukeerStringUtil.isNullOrEmpty(cycleId)) {
            if (cycleList.size() > 0)
                teachCycle = cycleList.get(0);
        } else {
            teachCycle = teachTaskService.selectByKey(cycleId);
        }

        List<TeachCycle> semesterList = new ArrayList<>();
        String cycleYear = "";
        if (!GukeerStringUtil.isNullOrEmpty(teachCycle))
            cycleYear = teachCycle.getCycleYear();

        Map yearMap = new LinkedHashMap();
        for (TeachCycle cycle : cycleList) {
            if (cycleYear.equals(cycle.getCycleYear()))
                semesterList.add(cycle);
            yearMap.put(cycle.getCycleYear(), cycle);
        }

        Course course = null;
        if (courseId != "") {
            course = teacherService.findCourseById(courseId);
        }

        model.addAttribute("standardCourseList", standardCourseList);
        model.addAttribute("roomTypeList", roomTypeList);
        model.addAttribute("teachCycleList", teachCycleList);

        model.addAttribute("cycle", teachCycle);
        model.addAttribute("cycleYear", cycleYear);
        model.addAttribute("cycleSemester", teachCycle.getCycleSemester());
        model.addAttribute("course", course);
        model.addAttribute("yearmap", yearMap.keySet());
        return "teachTask/pop/courseAddPop";
    }

    // 增加授课班级的弹窗
    @RequestMapping(value = "/course/class/pop")
    public String addCourseClassPop(HttpServletRequest request, Model model) {
        String courseId = request.getParameter("id");
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        if (courseId != "" && courseId != null) {
            model.addAttribute("courseId", courseId);
        }
        //查询所有的班级
        //首先查询所有的学段信息
        List<ClassSection> sectionList = classService.getAllClassSectionBySchoolId(schoolId);
        List<GradeClass> njList = classService.getAllClassBySchoolId(schoolId);

        for (int k = 0; k < njList.size() - 1; k++) {
            for (int l = njList.size() - 1; l > k; l--) {
                if (njList.get(l).getNj().equals(njList.get(k).getNj()) && njList.get(l).getXd().equals(njList.get(k).getXd())) {
                    njList.remove(l);
                }
            }
        }
        List<GradeClass> gradeClassList = classService.getAllClassBySchoolId(schoolId);

        //根据courseId,查询course_classs表，作为页面checkbox默认选中的判断条件
        List<CourseClass> courseClassList = teachTaskService.findClassIdByCourseId(courseId);
        List<String> list = new ArrayList<>();
        for (CourseClass courseClass : courseClassList) {
            list.add(courseClass.getClassId());
        }

        model.addAttribute("list", list.toString().replace(" ", ""));
        model.addAttribute("sectionList", sectionList);
        model.addAttribute("sectionListSize", sectionList.size());
        model.addAttribute("njList", njList);
        model.addAttribute("gradeClassList", gradeClassList);
        return "teachTask/pop/courseClassPop";
    }

    // 增加授课班级
    @RequestMapping(value = "/course/class/add", method = RequestMethod.POST)
    public String courseClass(HttpServletRequest request) {
        String courseId = request.getParameter("courseId");
        String classIds = getParamVal(request, "classIds");
        String listOld = getParamVal(request, "listOld");

        //查询所有的课程班级信息
        Map courseClassMap = new HashedMap();
        List<CourseClass> courseClasseList = teachTaskService.findAllCourseClassByCourseId(courseId);
        for (CourseClass courseClass : courseClasseList) {
            courseClassMap.put(courseClass.getCourseId() + courseClass.getClassId(), courseClass);
        }

        String _listOld = "";
        List<String> oldBanji = new ArrayList<>();
        List<String> newBanji = new ArrayList<>();
        List<CourseClass> courseClasseNew = new ArrayList<>();

        if (StringUtils.isNotBlank(listOld) && !listOld.equals("[]")) {
            _listOld = listOld.substring(1, listOld.length() - 1);
            String[] oldBanjiArr = _listOld.split(",");
            if (oldBanjiArr.length > 0) {
                for (int i = 0; i < oldBanjiArr.length; i++) {
                    oldBanji.add(oldBanjiArr[i]);
                }
            }
        }

        if (StringUtils.isNotEmpty(classIds)) {
            String[] idsArray = classIds.split(",");
            if (idsArray.length > 0) {
                for (int i = 0; i < idsArray.length; i++) {
                    newBanji.add(idsArray[i]);
                }
            }
        }

//        Collection c = CollectionUtils.intersection(newBanji,oldBanji);
        //取出原来的课程班级中现在选择的要删除的班级删除  新的授课班级添加
        List<String> newtemp = new ArrayList<>(newBanji);
        List<String> oldtemp = new ArrayList<>(oldBanji);
        if (oldBanji.size() > 0) {
            if (newBanji.size() > 0) {
                oldBanji.removeAll(newtemp);
                newBanji.removeAll(oldtemp);
            }
        }

        if (oldBanji.size() > 0) {
            for (String oldOne : oldBanji) {
                int succ = teachTaskService.delCourseClassByCourseIdAndClassId(courseId, oldOne);
            }
        }

        if (newBanji.size() > 0) {
            for (String newNeedInsert : newBanji) {
                CourseClass courseClass = new CourseClass();
                courseClass.setId(PrimaryKey.get());
                courseClass.setClassId(newNeedInsert);
                courseClass.setCourseId(courseId);
                courseClasseNew.add(courseClass);
            }
        }
        //先根据courseId查询所有的数据，做一个删除操作，再插入
        if (courseClasseNew != null && courseClasseNew.size() > 0) {
            teachTaskService.batchInsertCourseClass(courseClasseNew);
        }

        return "teachTask/course";
    }

    //课程的增删改
    @ResponseBody
    @RequestMapping(value = "/course/update", method = RequestMethod.POST)
    public String updateCourse(HttpServletRequest request, Model model, Course course) {
        String type = getParamVal(request, "type");
        User user = getLoginUser();
        String id = getParamVal(request, "id");
        String cycleYear = getParamVal(request, "cycleYear");
        String _semester = request.getParameter("semester");
        course.setCreateDate(System.currentTimeMillis());
        TeachCycle teachCycle = null;
        //根据schoolId和学期学年数据查询cycleId
        if (cycleYear != "" && _semester != "") {
            teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(_semester), user.getSchoolId());
        } else {
            teachCycle = getLatestTeachCycle(user.getSchoolId());
        }

        if (id != "") {
            course.setId(id);
            if (ConstantUtil.DELETE.equalsIgnoreCase(type.trim())) {
                course.setDelFlag(1);
            }
            teachTaskService.saveCourse(course, user);
        } else if (ConstantUtil.INSERT.equalsIgnoreCase(type.trim())) {
            //根据cycleId name schoolId查询该课程是否已存在\
            String cycleId = "";
            if (teachCycle.getId() != null) {
                course.setCycleId(teachCycle.getId());
            }

            teachTaskService.saveCourse(course, user);
        }
        return "";
    }

    //标准课程增加、编辑的弹窗
    @RequestMapping(value = "/course/standard/pop", method = RequestMethod.GET)
    public String addStandardCoursePop(HttpServletRequest request, Model model) {
        //查询所有的课程类型
        String id = request.getParameter("id");

        String type = getParamVal(request, "type");
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        Map param = new HashedMap();
        param.put("schoolId", schoolId);
        PageInfo<CourseType> courseTypePageInfo = teachTaskService.findAllCourseType(param);

        model.addAttribute("courseTypePageInfo", courseTypePageInfo);
        Course course = null;
        if (id != null) {
            StandardCourse standardCourse = teachTaskService.findStandardCourseById(id);
            model.addAttribute("standardCourse", standardCourse);
        }
        return "teachTask/pop/standardCoursePop";
    }


    //标准课程增加
    @RequestMapping(value = "/course/standard/add", method = RequestMethod.POST)
    public String addStandardCourse(HttpServletRequest request, Model model, StandardCourse standardCourse) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        String id = standardCourse.getId();
        standardCourse.setSchoolId(schoolId);
        String courseTypeId = standardCourse.getCourseTypeId();
        if (StringUtils.isEmpty(courseTypeId) || courseTypeId.equals("无")) {
            standardCourse.setIsDictionary(1);//1表示自定义学科
        } else {
            standardCourse.setIsDictionary(0);//0表示字典学科
        }
        teachTaskService.saveStandardCourse(standardCourse);
        return "teachTask/course";
    }

    //标准课程删除
    @RequestMapping(value = "/course/standard/del", method = RequestMethod.POST)
    public String delStandardCourse(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        //直接删掉，不做逻辑删除
        teachTaskService.delStandardCourseById(id);
        return "teachTask/course";
    }

    //标准课程首页
    @RequestMapping(value = "/course/standard/index", method = RequestMethod.GET)
    public String standardCourseIndex(HttpServletRequest request, Model model, StandardCourse standardCourse) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        //根据标准的课程名称查询的是否已经存在
        //根据schoolId查询所有的标准课程
        PageInfo<StandardCourse> pageInfo = teachTaskService.findAllStandardCourseBySchoolIdAndPageNum(schoolId, pageNum, pageSize);
        List<StandardCourse> standardCourseList = pageInfo.getList();

        model.addAttribute("pageInfo", pageInfo);
        return "teachTask/standardCourse";
    }


    /**
     * 教务管理-课程类型管理
     *
     * @param request
     * @param model
     * @return
     */
    //   课程类型管理的弹窗
    @RequestMapping(value = "/course/type/pop", method = RequestMethod.GET)
    public String courseCategory(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();

        Map param = new HashMap();
        param.put("schoolId", schoolId);

        PageInfo<CourseType> pageInfo = teachTaskService.findAllCourseType(param);

        model.addAttribute("pageInfo", pageInfo);
        return "teachTask/pop/courseCategoryPop";
    }

    @ResponseBody
    @RequestMapping(value = "/course/type/update/one", method = RequestMethod.POST)
    public ResultEntity courseCategoryAdd(HttpServletRequest request) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        String oneCourse = request.getParameter("oneCourse");
        String typeId = request.getParameter("typeId");
        //根据名字是否已经存在
        if (typeId != null) {
            CourseType courseType = new CourseType();
            courseType.setId(typeId);
            courseType.setUpdateDate(new Date().getTime());
            courseType.setUpdateBy(user.getId());
            courseType.setName(oneCourse);
            courseType.setDelFlag(0);
            int succ = teachTaskService.saveCourseType(courseType);
            return ResultEntity.newResultEntity("修改成功", "teachTask/pop/courseCategoryPop");
        } else {
            if (oneCourse != null) {
                CourseType courseTypeFromDB = teachTaskService.findCourseTypeByName(oneCourse, schoolId);
                if (courseTypeFromDB.getId() != null) {
                    return ResultEntity.newResultEntity("课程已经存在,已经执行了更新操作", "teachTask/pop/courseCategoryPop");
                } else {
                    CourseType courseType = new CourseType();
                    courseType.setCreateBy(user.getId());
                    courseType.setDelFlag(0);
                    courseType.setName(oneCourse);
                    courseType.setSchoolId(schoolId);
                    int succ = teachTaskService.saveCourseType(courseType);
                    return ResultEntity.newResultEntity("创建成功", "teachTask/pop/courseCategoryPop");
                }
            }
        }
        return ResultEntity.newResultEntity();
    }

    @RequestMapping(value = "/course/type/update", method = RequestMethod.POST)
    public String courseCategoryUpdate(@ModelAttribute CourseType courseType, HttpServletRequest request, Model model) {
        String type = request.getParameter("type");
        String id = request.getParameter("id");
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        CourseType courseTypeDB = null;
        if (id != "" && id != null) {
            courseTypeDB = teachTaskService.selectCourseTypeByKey(id);
        }
        if (courseType != null) {
            if (ConstantUtil.UPDATE.equalsIgnoreCase(type.trim())) {
                courseType.setDelFlag(0);
                courseType.setCreateBy(user.getId());
                courseType.setUpdateDate(System.currentTimeMillis());
                int succ = teachTaskService.saveCourseType(courseType);
            } else if (ConstantUtil.DELETE.equalsIgnoreCase(type.trim())) {
                courseTypeDB.setDelFlag(1);
                teachTaskService.saveCourseType(courseTypeDB);
            }
        }
        return "teachTask/courseCategoryPop";
    }


    /**
     * 教务管理-教学周期
     *
     * @param request
     * @param model
     * @return
     */

    //   周期管理首页
    @RequiresPermissions("teach:task:base")
    @RequestMapping(value = "/cycle/index")
    public String cycleIndex(HttpServletRequest request, Model model) {
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        User user = getLoginUser();

        Map param = new HashMap();
        param.put("pageNum", pageNum);
        param.put("pageSize", pageSize);
        param.put("schoolId", user.getSchoolId());

        PageInfo<TeachCycle> pageInfo = teachTaskService.getTeachCycle(param);
        List<TeachCycle> list = pageInfo.getList();
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("list", list);

        if (StringUtil.isNotEmpty(getParamVal(request, "appId")))
            request.getSession().setAttribute("teachTaskId", getParamVal(request, "appId"));

        return "teachTask/cycleIndex";
    }


    //教学周期 - 增加的弹窗
    @RequestMapping(value = "/cycle/add/pop")
    public String cycleAddPop(HttpServletRequest request, Model model) {
        return "teachTask/pop/cycleAddPop";
    }

    //教学周期 - 编辑的弹窗
    @RequestMapping(value = "/cycle/edit/pop")
    public String cycleEditPop(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        String type = request.getParameter("type");
        TeachCycle teachCycle = null;
        if (id != null && id != "") {
            teachCycle = teachTaskService.selectByKey(id);
        }
        model.addAttribute("update", 1);
        model.addAttribute("type", type);
        model.addAttribute("teachCycle", teachCycle);
        return "teachTask/pop/cycleAddPop";
    }

    //教学周期 - 增加教学周期
    @ResponseBody
    @RequestMapping(value = "/cycle/do", method = RequestMethod.POST)
    public ResultEntity cycleAdd(HttpServletRequest request, Model model) {
        String cycleId = request.getParameter("id");
        String type = request.getParameter("type");
        String cycleYear = request.getParameter("cycleYear");
        String cycleSemester = request.getParameter("cycleSemester");
        String _endDate = request.getParameter("endDate");
        String _beginDate = request.getParameter("beginDate");
        String weekCount = request.getParameter("weekCount");
        String _termsStart = getParamVal(request, "termBeginTime");
        User user = getLoginUser();
        Long termsStart = 0L;
        TeachCycle teachCycleDB = null;
        String schoolId = user.getSchoolId();
        try {

            termsStart = stringToLong(_termsStart, "yyyy-MM-dd");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (StringUtils.isNotBlank(cycleId)) {
            teachCycleDB = teachTaskService.selectByKey(cycleId);
            if (ConstantUtil.DELETE.equalsIgnoreCase(type.trim())) {
                teachCycleDB.setDelFlag(1);
                teachTaskService.saveTeachCycle(teachCycleDB);
                return ResultEntity.newResultEntity("删除成功", "teachTask/cycleIndex");
            } else if (ConstantUtil.UPDATE.equalsIgnoreCase(type.trim())) {
                teachCycleDB.setCreateBy(user.getId());
                teachCycleDB.setDelFlag(0);
                teachCycleDB.setCreateDate(System.currentTimeMillis());
                teachCycleDB.setBeginDate(Long.parseLong(_beginDate));
                teachCycleDB.setEndDate(Long.parseLong(_endDate));
                teachCycleDB.setSchoolId(schoolId);
                teachCycleDB.setTermBeginTime(termsStart);
                teachCycleDB.setWeekCount(NumberConvertUtil.convertS2I(weekCount));
                teachTaskService.saveTeachCycle(teachCycleDB);
                return ResultEntity.newResultEntity("修改成功", "teachTask/cycleIndex");
            }
        } else {
            //根据学年 学期  学校Id判断该学期是否已经存在
            TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), schoolId);
            if (teachCycle.getId() != null) {
                return ResultEntity.newResultEntity("该学期已经存在，无需再创建", "teachTask/cycleIndex");
            } else {
                TeachCycle teachCycleNew = new TeachCycle();
                teachCycleNew.setCreateBy(user.getId());
                teachCycleNew.setDelFlag(0);
                teachCycleNew.setCreateDate(System.currentTimeMillis());
                teachCycleNew.setCycleYear(cycleYear);
                teachCycleNew.setCycleSemester(NumberConvertUtil.convertS2I(cycleSemester));
                teachCycleNew.setBeginDate(Long.parseLong(_beginDate));
                teachCycleNew.setEndDate(Long.parseLong(_endDate));
                teachCycleNew.setSchoolId(schoolId);
                teachCycleNew.setTermBeginTime(termsStart);

                teachCycleNew.setWeekCount(NumberConvertUtil.convertS2I(weekCount));
                teachTaskService.saveTeachCycle(teachCycleNew);
                //插入之后紧接着查询cycleId
                TeachCycle teachCycleThis = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), schoolId);
                String thisCycleId = teachCycleThis.getId();
                Map param = new HashMap<>();
                String preCycleId = null;
                TeachCycle teachCycleBefore = null;
                List<TeachCycle> cycleListAll = teachTaskService.getCycleList(schoolId);
                if (cycleListAll.size() > 1) {
                    teachCycleBefore = cycleListAll.get(1);
                    preCycleId = teachCycleBefore.getId();
                }

                //获取上一学期课程
                List<Course> courseList = null;

                //获取上一学期教室信息
                List<ClassRoom> classRoomList = null;

                //获取上一学期班主任信息
                List<TeacherClass> teacherClassList = null;

                //获取上一学期任课教师信息
                List<BZRView> courseClassList = null;
                List<CourseClass> courseClassList1 = new ArrayList<>();
                //获取要同步的信息
                String synInfo = request.getParameter("synInfo");
                String[] infoArray = synInfo.split(",");
                if (infoArray.length > 0) {
                    for (int i = 0; i < infoArray.length; i++) {
                        if (preCycleId != null) {
                            String oneInfo = infoArray[i];
                            if (oneInfo.equals("教室管理")) {
                                classRoomList = teachTaskService.findAllClassRoomByCycleId(preCycleId);
                                List<RefRoomCycle> cycleList = new ArrayList<>();
                                if (classRoomList != null) {
                                    for (ClassRoom classRoom : classRoomList) {
                                        String roomId = PrimaryKey.get();
                                        classRoom.setId(roomId);

                                        //教室周期关联
                                        RefRoomCycle cycle = new RefRoomCycle();
                                        cycle.setCycleId(thisCycleId);
                                        cycle.setRoomId(roomId);
                                        cycle.setId(PrimaryKey.get());
                                        cycleList.add(cycle);
                                    }
                                }
                                if (classRoomList != null && classRoomList.size() > 0) {
                                    teachTaskService.insertClassRoomBatch(classRoomList);
                                    teachTaskService.batchSaveRefRoomCycle(cycleList);
                                }
                            } else if (oneInfo.equals("班主任安排")) {

                                teacherClassList = teachTaskService.findLastMasterByPreCycleId(preCycleId);
                                for (TeacherClass teacherClass : teacherClassList) {
                                    teacherClass.setCycleId(thisCycleId);
                                    //aaaaaaaaaaaaaaaaaaa teacherClass.setId(PrimaryKey.get());
                                }
                                if (teacherClassList != null && teacherClassList.size() > 0)
                                    teachTaskService.insertBatchTeacherClass(teacherClassList);
                            } else if (oneInfo.equals("课程安排")) {

                                courseList = teachTaskService.findAllCourseBySchoolIdAndCycleId(schoolId, preCycleId);
                                //根据courseList  查询所有的授课班级
                                List<CourseClass> courseClassListForClass = teachTaskService.findAllCourseClassByCourseList(courseList);

                                if (courseList != null && courseList.size() > 0) {
                                    for (Course course : courseList) {
                                        course.setCycleId(thisCycleId);
                                    }

                                    for (CourseClass courseClass : courseClassListForClass) {
                                        for (Course course : courseList) {
                                            if (courseClass.getCourseId().equals(course.getId())) {
                                                course.setId(PrimaryKey.get());
                                                courseClass.setCourseId(course.getId());

                                            } else {
                                                course.setId(PrimaryKey.get());
                                            }
                                            courseClass.setId(PrimaryKey.get());
                                        }
                                    }

                                    if (!synInfo.contains("任课教师安排")) {
                                        if (null != courseClassListForClass && courseClassListForClass.size() > 0) {
                                            for (CourseClass courseClass : courseClassListForClass) {
                                                courseClass.setTeacherId(null);
                                            }
                                        }
                                    }

                                    if (!synInfo.contains("科目课时安排")) {
                                        if (null != courseClassListForClass && courseClassListForClass.size() > 0) {
                                            for (CourseClass courseClass : courseClassListForClass) {
                                                courseClass.setCourseHour(0);
                                            }
                                        }
                                    }


                                    if (courseList.size() > 0 || courseClassListForClass.size() > 0)
                                        teachTaskService.batchInsertCourse(courseList, courseClassListForClass);
                                }
                            } else if (oneInfo.equals("班级教室安排")) {
                                //查询上一学期班级教室安排
                                List<RefClassRoom> refClassRoomList = teachTaskService.findRefClassRoomByCycleId(preCycleId);
                                List<RefClassRoom> refClassRoomListNew = new ArrayList<>();
                                if (refClassRoomList.size() > 0) {
                                    for (RefClassRoom refClassRoom : refClassRoomList) {
                                        RefClassRoom refClassRoomNew = new RefClassRoom();
                                        refClassRoomNew.setId(PrimaryKey.get());
                                        refClassRoomNew.setRoomId(refClassRoom.getRoomId());
                                        refClassRoomNew.setSchoolTypeId(refClassRoom.getSchoolTypeId());
                                        refClassRoomNew.setGradeClass(refClassRoom.getGradeClass());
                                        refClassRoomNew.setCycleId(thisCycleId);
                                        refClassRoomListNew.add(refClassRoomNew);
                                    }
                                }
                                //批量插入班级教室
                                if (refClassRoomListNew != null && refClassRoomListNew.size() > 0)
                                    teachTaskService.batchInsertRefClassRoom(refClassRoomListNew);
                            } else if (oneInfo.equals("课节设置")) {
                                ///根据cycleid查询所有的课节init表  node表
                                List<CourseNodeInit> courseNodeInitList = teachTaskService.findCourseNodeInitByCycleId(preCycleId);

                                List<String> initIdsList = new ArrayList<>();
                                if (null != courseNodeInitList && courseNodeInitList.size() > 0) {

                                    List<CourseNode> courseNodeList = teachTaskService.findCourseNodeByCourseNodeInitIdList(initIdsList);

                                    for (CourseNodeInit courseNodeInit : courseNodeInitList) {
                                        courseNodeInit.setCycleId(thisCycleId);
                                        initIdsList.add(courseNodeInit.getId());
                                    }

                                    if (null != courseNodeList && courseNodeList.size() > 0) {
                                        for (CourseNodeInit courseNodeInit : courseNodeInitList) {
                                            String key = PrimaryKey.get();
                                            for (CourseNode courseNode : courseNodeList) {
                                                courseNode.setCourseNodeInitId(key);
                                                courseNodeInit.setId(key);
                                                courseNode.setId(PrimaryKey.get());
                                                courseNode.setCycleId(thisCycleId);
                                            }
                                        }
                                        if (courseNodeInitList != null && courseNodeInitList.size() > 0 && courseNodeList != null && courseNodeList.size() > 0)
                                            teachTaskService.batchInsertCourseNodeInit(courseNodeInitList, courseNodeList);
                                    }
                                }
                            }
                        }
                    }
                }
                return ResultEntity.newResultEntity("成功创建教学周期", "teachTask/cycleIndex");
            }
        }
        return null;
    }

    @RequestMapping(value = "/cycle/syn/pop")
    public String synPop(HttpServletRequest request, Model model) {
        String cycleId = request.getParameter("cycleId");
        model.addAttribute("cycleId", cycleId);
        return "teachTask/pop/syn";
    }

    //教学周期 - 同步数据
    @RequestMapping(value = "/cycle/syn")
    public String syn(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        //获取教学周期id
        String cycleId = request.getParameter("cycleId");
        TeachCycle teachCycle = null;
        if (cycleId != null) {
            teachCycle = teachTaskService.selectByKey(cycleId);
        }
        int cycleSemester = 0;
        String cycleYear = null;
        if (teachCycle != null) {
            cycleSemester = teachCycle.getCycleSemester();
            cycleYear = teachCycle.getCycleYear();
        }
        Map param = new HashMap<>();
        String preCycleId = null;
        TeachCycle teachCycle1 = null;
        if (cycleSemester == 2) {
            cycleSemester = 1;
            param.put("schoolId", schoolId);
            param.put("cycleYear", cycleYear);
            param.put("cycleSemester", cycleSemester);
            teachCycle1 = teachTaskService.getCycleByYearSemester(cycleYear, cycleSemester, schoolId);
            preCycleId = teachCycle1.getId();
        } else if (cycleSemester == 1) {
            String[] arr = cycleYear.split("-");
            Integer before = Integer.parseInt(arr[0]) - 1;
            Integer after = Integer.parseInt(arr[1]) - 1;
            String preCycleYear = before.toString() + "-" + after.toString();
            teachCycle1 = teachTaskService.getCycleByYearSemester(cycleYear, cycleSemester, schoolId);
            preCycleId = teachCycle1.getId();
        }
        //根据preCycleId获取上一学期数据

        //获取上一学期课程
        List<Course> courseList = null;

        //获取上一学期教室信息
        List<ClassRoom> classRoomList = null;

        //获取上一学期班主任信息
        List<TeacherClass> teacherClassList = null;

        //获取上一学期任课教师信息
        List<BZRView> courseClassList = null;
        List<CourseClass> courseClassList1 = new ArrayList<>();
        //获取要同步的信息
        String synInfo = request.getParameter("synInfo");
        String[] infoArray = synInfo.split(",");
        if (infoArray.length > 0) {
            for (int i = 0; i < infoArray.length; i++) {
                String oneInfo = infoArray[i];
                if (oneInfo.equals("教室管理")) {
                    List<ClassRoom> classRoomList1 = teachTaskService.findAllClassRoomByCycleId(cycleId);
                    classRoomList = teachTaskService.findAllClassRoomByCycleId(preCycleId);

                    List<RefRoomCycle> cycleList = new ArrayList<>();
                    for (ClassRoom classRoom : classRoomList) {
                        String roomId = PrimaryKey.get();
                        classRoom.setId(roomId);

                        //教室，周期关联
                        RefRoomCycle cycle = new RefRoomCycle();
                        cycle.setId(PrimaryKey.get());
                        cycle.setCycleId(cycleId);
                        cycle.setRoomId(roomId);

                        cycleList.add(cycle);
                    }
                    if (classRoomList != null && classRoomList.size() > 0) {
                        teachTaskService.insertClassRoomBatch(classRoomList);
                        teachTaskService.batchSaveRefRoomCycle(cycleList);//教室周期关联
                    }

                } else if (oneInfo.equals("班主任安排")) {

                    teacherClassList = teachTaskService.findLastMasterByPreCycleId(preCycleId);
                    for (TeacherClass teacherClass : teacherClassList) {
                        teacherClass.setCycleId(cycleId);
                    }
                    if (null != teacherClassList && teacherClassList.size() > 0)
                        teachTaskService.insertBatchTeacherClass(teacherClassList);
                } else if (oneInfo.equals("任课教师安排")) {
                    //先去查一下这些
                    courseClassList = teachTaskService.findLastCourseClassByPreCycleId(preCycleId);
                    for (BZRView bzrView : courseClassList) {
                        CourseClass courseClass = new CourseClass();
                        courseClass.setId(PrimaryKey.get());
                        courseClass.setTeacherId(bzrView.getTeacherId());
                        courseClass.setClassId(bzrView.getClassId());
                        courseClass.setCourseId(bzrView.getCourseId());
                        courseClassList1.add(courseClass);
                    }
                    if (null != courseClassList1 && courseClassList1.size() > 0)
                        teachTaskService.batchInsertCourseClass(courseClassList1);
                } else if (oneInfo.equals("课程安排")) {
                    courseList = teachTaskService.findAllCourseBySchoolIdAndCycleId(schoolId, preCycleId);
                    for (Course course : courseList) {
                        course.setId(PrimaryKey.get());
                        course.setCycleId(cycleId);
                    }
                    if (courseList != null && courseList.size() > 0)
                        teachTaskService.batchInsertCourse(courseList, null);
                }
            }
        }
        return "teachTask/cycleIndex";
    }

    //---------------------------------------------------begin-----------------------------------------------------


    //创建课程select的change

    @ResponseBody
    @RequestMapping(value = "/course/add/select/change")
    public ResultEntity courseAddSelectchange(HttpServletRequest request, Model model) {
        String cycleYear = getParamVal(request, "cycleYear");

        List<TeachCycle> teachCycleList = teachTaskService.findTeachCycleByCycleYearAndSchoolId(getLoginUser().getSchoolId(), cycleYear);

        return ResultEntity.newResultEntity(teachCycleList);
    }

    //教室管理，主页
    @RequestMapping(value = "/room/index")
    public String classRoomManage(HttpServletRequest request, Model model) {
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        User user = getLoginUser();

        String cycleYear = getParamVal(request, "cycleYear");
        String semester = getParamVal(request, "cycleSemester");
        //根据学年学期获得周期
        TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(semester), user.getSchoolId());
        //获得周期列表，补全两个下拉框
        List<TeachCycle> cycleList = teachTaskService.getCycleList(user.getSchoolId());
        if (GukeerStringUtil.isNullOrEmpty(teachCycle.getId()))
            if (cycleList.size() > 0)
                teachCycle = cycleList.get(0);

        List<TeachCycle> semesterList = new ArrayList<>();
        if (StringUtil.isEmpty(cycleYear))
            cycleYear = teachCycle.getCycleYear();

        Map yearMap = new LinkedHashMap();
        for (TeachCycle cycle : cycleList) {
            if (cycleYear.equals(cycle.getCycleYear()))
                semesterList.add(cycle);
            yearMap.put(cycle.getCycleYear(), cycle);
        }

        Map param = new HashMap();
        param.put("pageNum", pageNum);
        param.put("pageSize", pageSize);
        param.put("schoolId", user.getSchoolId());
        param.put("cycleId", teachCycle.getId());

        PageInfo<ClassRoom> pageInfo = new PageInfo<>();
        if (teachCycle != null || StringUtils.isNotEmpty(teachCycle.getId())) {
            pageInfo = teachTaskService.getClassRoomList(param);
        }
        List<ClassRoom> list = pageInfo.getList();

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("yearList", yearMap.keySet());
        model.addAttribute("semesterList", semesterList);
        model.addAttribute("cycleYear", cycleYear);
        model.addAttribute("cycleSemester", semester);

        if (StringUtil.isNotEmpty(getParamVal(request, "appId")))
            request.getSession().setAttribute("teachTaskId", getParamVal(request, "appId"));

        return "teachTask/teachTaskRoom";
    }


    //修改，新增，页面
    @RequestMapping(value = "/room/update/index")
    public String roomIndex(HttpServletRequest request, Model model) {
        String roomId = getParamVal(request, "roomId");
        User user = getLoginUser();

        List<SchoolType> schoolTypeList = schoolService.selectSchoolTypeBySchoolId(user.getSchoolId());
        List<RoomType> roomTypeList = teachTaskService.roomTypeList(user.getSchoolId());
        ClassRoom room = teachTaskService.getRoomByPri(roomId);
        model.addAttribute("room", room);
        model.addAttribute("roomTypeList", roomTypeList);
        model.addAttribute("schoolTypeList", schoolTypeList);

        return "teachTask/pop/classRoomAdd";
    }

    //教室 修改，新增
    @RequestMapping(value = "/room/update", method = RequestMethod.POST)
    public String updateRoom(@ModelAttribute("inputForm") ClassRoom room, HttpServletRequest request) {

        User user = getLoginUser();

        TeachCycle cycleLatest = getLatestTeachCycle(user.getSchoolId());

        String pri = PrimaryKey.get();
        room.setSchoolId(user.getSchoolId());
        room.setDelFlag(0);
        if (StringUtil.isNotEmpty(room.getId())) {

            room.setUpdateBy(user.getId());
            room.setUpdateDate(System.currentTimeMillis());

        } else if (StringUtil.isEmpty(room.getId())) {

            room.setCreateBy(user.getId());
            room.setCreateDate(System.currentTimeMillis());

            //周期教室关联
            RefRoomCycle cycle = new RefRoomCycle();
            cycle.setRoomId(pri);
            cycle.setCycleId(cycleLatest.getId());
            cycle.setId(PrimaryKey.get());
            teachTaskService.saveRefRoomCycle(cycle);
        }
        teachTaskService.saveClassRoom(room, pri);
        return "redirect:/teach/task/room/update/index?roomId=" + room.getId();
    }

    //教室，删除，批量删除
    @RequestMapping(value = "/room/delete", method = RequestMethod.POST)
    public void deleteRoom(HttpServletRequest request) {
        String roomIds = getParamVal(request, "roomId");
        List<String> roomIdList = ConstantUtil.splitWithOutNull(roomIds);

        if (roomIdList.size() > 0) {
            for (String roomId : roomIdList) {
                ClassRoom room = new ClassRoom();
                room.setDelFlag(1);
                room.setId(roomId);
                teachTaskService.saveClassRoom(room, null);
            }
        }
    }

    //教室导入模板
    @RequestMapping(value = "/room/download")
    public void exportRoomTemplate(HttpServletResponse response) {
        try {
            String fileName = "教室导入模板.xlsx";
            String anno = "注释：注释：橙色字段为必填项 \n" +
                    "          1.校区名称需和学籍管理中统一，按此规则填写;教室类型一栏请填写存在的类型\n" +
                    "          2.楼层、容纳人数、有效座位数、考试座位数、房间号请填写整数\n" +
                    "          3.是否用于选课请填写是或者否 \n";
            new ExportExcel(false, "教室导入模板", IOClassRoomView.class, 2, anno, 1).setDataList(new ArrayList()).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //导入教室
    @ResponseBody
    @RequestMapping(value = "/room/import")
    public ResponseEntity importRoom(@RequestParam(value = "file") MultipartFile file) throws Exception {
        User user = getLoginUser();
        Long begin = System.currentTimeMillis();
        List<IOClassRoomView> errorRoomList = new ArrayList<IOClassRoomView>();
        List<ClassRoom> correctRoomList = new ArrayList<ClassRoom>();
        IOClassRoomView errorRoom = new IOClassRoomView();

        //获取最新的教学周期
        TeachCycle cycleLatest = getLatestTeachCycle(user.getSchoolId());

        //翻译教室类型
        List<RoomType> roomTypeList = teachTaskService.roomTypeList(user.getSchoolId());
        Map roomTypeMap = new HashMap();
        for (RoomType roomType : roomTypeList) {
            roomTypeMap.put(roomType.getName(), roomType.getId());//config_key冗余
        }

        List<SchoolType> schoolTypeList = schoolService.selectSchoolTypeBySchoolId(user.getSchoolId());
        Map schoolTypeMap = new HashMap();
        for (SchoolType schoolType : schoolTypeList) {
            schoolTypeMap.put(schoolType.getName(), schoolType.getId());
        }

        ImportExcel importExcel = new ImportExcel(file, 2, 0);
        List<IOClassRoomView> list = importExcel.getDataList(IOClassRoomView.class);
        List<RefRoomCycle> refRoomCycleList = new ArrayList<>();
        Long time = 0L;
        for (IOClassRoomView roomView : list) {
            time++;
            try {
                errorRoom = roomView;
                ClassRoom room = new ClassRoom();
                String roomId = PrimaryKey.get();
                room.setId(roomId);
                //room.setRoomName(roomView.getRoomName());
                room.setTeachBuilding(roomView.getTeachBuilding());
                room.setRoomTypeName(roomView.getRoomTypeName());
                if (GukeerStringUtil.isNullOrEmpty(roomTypeMap.get(roomView.getRoomTypeName()))) {
                    throw new ErrcodeException("教室类型不存在");
                }
                room.setRoomType(roomTypeMap.get(roomView.getRoomTypeName()).toString());

                room.setSchoolTypeName(roomView.getSchoolTypeName());
                if (GukeerStringUtil.isNullOrEmpty(schoolTypeMap.get(roomView.getSchoolTypeName()))) {
                    throw new ErrcodeException("校区不存在");
                }
                room.setSchoolType(schoolTypeMap.get(roomView.getSchoolTypeName()).toString());
                room.setRoomNum(roomView.getRoomNum());
                room.setSchoolId(user.getSchoolId());
                room.setFloor(roomView.getFloor());
                room.setCount(roomView.getCount());
                room.setAvailableSeat(roomView.getAvailableSeat());
                room.setExamSeat(roomView.getExamSeat());
                room.setCourseSelect(ConstantUtil.getKeyByValueAndFlag(roomView.getCourseSelect(), "yorn"));
                room.setRemarks(roomView.getRemarks());
                room.setCreateBy(user.getId());
                room.setCreateDate(System.currentTimeMillis() + time);

                //关联教室，周期
                RefRoomCycle cycle = new RefRoomCycle();
                cycle.setId(PrimaryKey.get());
                cycle.setRoomId(roomId);
                cycle.setCycleId(cycleLatest.getId());
                refRoomCycleList.add(cycle);

                correctRoomList.add(room);
            } catch (Exception e) {
                errorRoomList.add(errorRoom);
                continue;
            }

        }
        if (correctRoomList.size() > 0) {
            teachTaskService.insertClassRoomBatch(correctRoomList);
            //同时也要在ref_room_cycle表中插入这些数据
            teachTaskService.batchInsertRefRoomCycle(refRoomCycleList);
        }


        Long end = System.currentTimeMillis();
        Map res = new HashMap();
        res.put("msg", "导入完成，共" + correctRoomList.size() + "条成功，" + errorRoomList.size() + "条失败,耗时" + (end - begin) / 1000 + "秒");
        res.put("errorList", errorRoomList);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    //下载导入失败的classRoom列表
    @RequestMapping(value = "/room/error/export", method = RequestMethod.POST)
    public void errorRoom(HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileName = "错误信息列表.xlsx";
            String anno = "注释：橙色字段为必填项\n" +
                    "          1.校区一栏填写:主校区、西校区，按此规则填写;教室类型一栏请填写存在的类型\n" +
                    "          2.楼层、容纳人数、有效座位数、考试座位数、房间号请填写整数\n" +
                    "          3.是否用于选课请填写是或者否 \n";
            String msg = getParamVal(request, "msg");
            JsonArray jsonArray = new JsonParser().parse(msg).getAsJsonArray();
            List<IOClassRoomView> exportFile = new ArrayList<IOClassRoomView>();
            for (JsonElement jsonElement : jsonArray) {
                IOClassRoomView importBundling = GsonUtil.fromJson(jsonElement.getAsJsonObject(), IOClassRoomView.class);
                exportFile.add(importBundling);
            }
            new ExportExcel(false, "教室信息", IOClassRoomView.class, 2, anno, 1).setDataList(exportFile).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresPermissions("renShi:role:view")
    @RequestMapping(value = "/rolefp/index")
    public String rsRoleFpIndex(HttpServletRequest request, Model model) {
        int pageSize = getPageSize(request);
        int pageNum = getPageNum(request);
        String roleId = getParamVal(request, "roleId");
        User loginUser = getLoginUser();

        HttpSession session = request.getSession();
        Object appId = session.getAttribute("teachTaskId");

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
            PageInfo<Teacher> pageInfo = userService.findRoleTeacherList(roleId, loginUser.getSchoolId(), pageSize, pageNum);

            model.addAttribute("teacherList", pageInfo.getList());

            model.addAttribute("pageInfo", pageInfo);
        }

        model.addAttribute("roleList", roleList);
        model.addAttribute("currentRole", roleId);
        return "teachTask/roleFp";
    }
    //---------------------------------------------------end-----------------------------------------------------


    public TeachCycle getLatestTeachCycle(String schoolId) {
        List<TeachCycle> cycleList = teachTaskService.getCycleList(schoolId);
        TeachCycle cycleLatest = new TeachCycle();
        if (cycleList.size() > 0)
            cycleLatest = cycleList.get(0);
        return cycleLatest;
    }

    /**
     * 教务管理-班主任管理
     *
     * @param request
     * @param model
     * @return
     */

    @RequestMapping(value = "/master/index")
    public String masterTeacherIndex(HttpServletRequest request, Model model) {

        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        User user = getLoginUser();
        String schoolId = user.getSchoolId();

        String cycleYear = getParamVal(request, "cycleYear");
        String cycleSemester = getParamVal(request, "cycleSemester");
        String xdId = getParamVal(request, "sectionId");
        String nj = getParamVal(request, "nj");

        //根据学年学期获得周期
        TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), user.getSchoolId());
        //获得周期列表，补全两个下拉框
        List<TeachCycle> cycleList = teachTaskService.getCycleList(user.getSchoolId());
        if (GukeerStringUtil.isNullOrEmpty(teachCycle.getId()))
            if (cycleList.size() > 0)
                teachCycle = cycleList.get(0);
        List<TeachCycle> semesterList = new ArrayList<>();
        if (StringUtil.isEmpty(cycleYear))
            cycleYear = teachCycle.getCycleYear();
        Map yearMap = new LinkedHashMap();

        if (cycleList.size() > 0) {
            for (TeachCycle cycle : cycleList) {
                if (cycleYear.equals(cycle.getCycleYear()))
                    semesterList.add(cycle);
                yearMap.put(cycle.getCycleYear(), cycle);
            }
        }


        String cycleId = teachCycle.getId();
        //根据学校id查询学段的名字
        List<ClassSection> classSectionList = classService.getAllClassSectionBySchoolId(schoolId);
        if (xdId == "") {
            if (classSectionList.size() > 0) {
                xdId = classSectionList.get(0).getId();
            }
        }

        if (StringUtils.isEmpty(nj)) {
            nj = "1";
        }

        //班主任分页用
        PageInfo<BZRView> pageInfo = new PageInfo<>();
        if (StringUtils.isNotEmpty(cycleId)) {
            pageInfo = teachTaskService.getAllMasterByGradeClassIds(pageNum, pageSize, xdId, NumberConvertUtil.convertS2I(nj), cycleId, 1);
        }
        model.addAttribute("xdId", xdId);
        model.addAttribute("yearList", yearMap.keySet());
        model.addAttribute("classSectionList", classSectionList);
        model.addAttribute("semesterList", semesterList);
        model.addAttribute("nj", nj);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("cycleYear", cycleYear);
        model.addAttribute("cycleSemester", cycleSemester);

        return "teachTask/masterIndex";
    }


    //班主任编辑
    @RequestMapping(value = "/master/edit", method = RequestMethod.POST)
    public String masterEdit(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        String teacherIdFromHouTai = request.getParameter("teacherIdFromHouTai");
        String teacherId = request.getParameter("teacherId");
        String deputyIds = request.getParameter("chooseIds");
        String classId = request.getParameter("classId");
        String cycleId = request.getParameter("cycleId");
        String deputyIdsFromHoutai = request.getParameter("deputyIds");
        List<TeacherClass> TeacherClassList = new ArrayList<>();
        //全部根据classId进行批量更新
        if (classId != null) {
            //首先把原来的数据根据classId全部物理删除 再燃后直接批量插入
            int deleteSucc = teachTaskService.deleteTeacherClassByClassId(classId, cycleId);
        }

        TeacherClass teacherClass = new TeacherClass();
        teacherClass.setType(1);
        teacherClass.setClassId(classId);
        teacherClass.setCycleId(cycleId);
        if (StringUtils.isEmpty(teacherId)) {
            teacherClass.setTeacherId(teacherIdFromHouTai);
        } else {
            teacherClass.setTeacherId(teacherId);
        }
        TeacherClassList.add(teacherClass);
        if (StringUtils.isEmpty(deputyIds)) {
            deputyIds = deputyIdsFromHoutai;
        }
        if (StringUtils.isNotEmpty(deputyIds)) {
            String[] deputyIdArray = deputyIds.split(",");
            for (int i = 0; i < deputyIdArray.length; i++) {
                String oneDeputyId = deputyIdArray[i];
                TeacherClass teacherClass1 = new TeacherClass();
                teacherClass1.setClassId(classId);
                teacherClass1.setTeacherId(oneDeputyId);
                teacherClass1.setType(2);
                teacherClass1.setCycleId(cycleId);
                TeacherClassList.add(teacherClass1);
            }
        }
        //批量插入
        if (null != TeacherClassList && TeacherClassList.size() > 0)
            teachTaskService.insertBatchTeacherClass(TeacherClassList);
        return "teachTask/masterIndex";
    }


    //班主任删除
    @RequestMapping(value = "/master/del", method = RequestMethod.POST)
    public String masterDel(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();

        String classId = request.getParameter("classId");
        String cycleYear = request.getParameter("cycleYear");
        String cycleSemester = request.getParameter("cycleSemester");
        TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), schoolId);
        //全部根据classId cycleId删除一个班的班主任
        if (teachCycle != null) {
            String cycleId = teachCycle.getId();
            //首先把原来的数据根据classId全部物理删除 再燃后直接批量插入
            int deleteSucc = teachTaskService.deleteTeacherClassByClassIdAndCycleId(classId, cycleId);
        }

        return "teachTask/masterIndex";
    }

    //任课教师删除
    @RequestMapping(value = "/course/teacher/del", method = RequestMethod.POST)
    public String courseTeacherDel(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        String refId = request.getParameter("id");

        if (StringUtils.isNotEmpty(refId)) {
            //首先把原来的数据根据classId全部物理删除 再燃后直接批量插入
            int deleteSucc = teachTaskService.delCourseTeacherByPrimarykey(refId);
        }


        return "teachTask/courrseTeacher";
    }


    //班级教室删除
    @RequestMapping(value = "/ref/class/room/del", method = RequestMethod.POST)
    public String refClassRoomDel(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        String refId = request.getParameter("id");

        if (StringUtils.isNotEmpty(refId)) {
            //首先把原来的数据根据classId全部物理删除 再燃后直接批量插入
            int deleteSucc = teachTaskService.delRefClassRoomByPrimarykey(refId);
        }

        return "teachTask/refClassRoom";
    }

    //班主任编辑的弹窗
    @RequestMapping(value = "/master/edit/pop", method = RequestMethod.GET)
    public String masterEditPop(HttpServletRequest request, Model model) {

        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        String classId = getParamVal(request, "classId");
        String cycleId = getParamVal(request, "cycleId");

        List<BZRView> bzrViewList = teachTaskService.findAllTeacherClassByClassIdAndCycleId(classId, cycleId);
        List<BZRView> bzrViewNew = new ArrayList<>();
        List<BZRView> deputyBzrViewNew = new ArrayList<>();
        for (BZRView bzrView : bzrViewList) {
            if (bzrView.getType() == 1) {
                bzrViewNew.add(bzrView);
            } else {
                deputyBzrViewNew.add(bzrView);
            }
        }

        //循环两个list合并 放到最后的list中
        BZRView bzrView2 = new BZRView();
        if (bzrViewNew.size() > 0) {
            for (BZRView bzrView : bzrViewNew) {
                bzrView2.setTeacherId(bzrView.getTeacherId());
                bzrView2.setSectionId(bzrView.getSectionId());
                bzrView2.setXdName(bzrView.getXdName());
                bzrView2.setClassId(bzrView.getClassId());
                bzrView2.setClassName(bzrView.getClassName());
                bzrView2.setCycleId(bzrView.getCycleId());
                bzrView2.setNj(bzrView.getNj());
                bzrView2.setMasterName(bzrView.getTeacherName());
                String deputyIds = "";
                String deputyNames = "";
                if (deputyBzrViewNew.size() > 0) {
                    for (BZRView bzrView1 : deputyBzrViewNew) {
                        if (bzrView.getClassId().equals(bzrView1.getClassId())) {
                            String oneDeputyId = bzrView1.getTeacherId();
                            deputyIds += "," + oneDeputyId;
                            String oneDuputyName = bzrView1.getTeacherName();
                            deputyNames += "," + oneDuputyName;
                        }
                    }
                }

                bzrView2.setDeputyIds(deputyIds);
                if (deputyNames.length() > 1) {
                    bzrView2.setDeputymasterName(deputyNames.substring(1, deputyNames.length()));
                }
            }
        }

        List<Teacher> _teacherList = teacherService.findAllTeacher(schoolId);
        JSON teacherList = (JSON) JSON.toJSON(_teacherList);
        model.addAttribute("classId", classId);
        model.addAttribute("teacherList", teacherList);
        model.addAttribute("bzrView", bzrView2);
        model.addAttribute("cycleId", cycleId);
        return "teachTask/pop/masterEditPop";
    }


    //班主任搜索
    @RequestMapping(value = "/master/search")
    public String rsIndex(HttpServletRequest request, Model model) {
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        String cycleYear = request.getParameter("cycleYear");
        String cycleSemester = request.getParameter("cycleSemester");
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        String teacherName = request.getParameter("name");

        //根据学年学期获得周期
        TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), user.getSchoolId());
        //获得周期列表，补全两个下拉框
        List<TeachCycle> cycleList = teachTaskService.getCycleList(user.getSchoolId());
        if (GukeerStringUtil.isNullOrEmpty(teachCycle.getId()))
            if (cycleList.size() > 0)
                teachCycle = cycleList.get(0);
        List<TeachCycle> semesterList = new ArrayList<>();
        if (StringUtil.isEmpty(cycleYear))
            cycleYear = teachCycle.getCycleYear();
        Map yearMap = new LinkedHashMap();

        if (cycleList.size() > 0) {
            for (TeachCycle cycle : cycleList) {
                if (cycleYear.equals(cycle.getCycleYear()))
                    semesterList.add(cycle);
                yearMap.put(cycle.getCycleYear(), cycle);
            }
        }

        String cycleId = teachCycle.getId();
        try {
            if (teacherName != null) {
                teacherName = java.net.URLDecoder.decode(teacherName, "UTF-8");//解决非post访问的中文乱码问题。
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        teacherName = "%" + teacherName + "%";
        PageInfo<BZRView> pageInfo = teachTaskService.findteacherByNameAndSchoolICycleId(cycleId, schoolId, teacherName, pageNum, pageSize);
        List<BZRView> bzrViewList = pageInfo.getList();
        HttpSession session = request.getSession();
        if (StringUtil.isNotEmpty(getParamVal(request, "appId")))
            session.setAttribute("teachTask", getParamVal(request, "appId"));//将当前应用id存到session中
        model.addAttribute("cycleYear", cycleYear);
        model.addAttribute("cycleSemester", cycleSemester);
        model.addAttribute("bzrViewList", bzrViewList);
        model.addAttribute("yearList", yearMap.keySet());
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("semesterList", semesterList);
        return "teachTask/masterSearch";
    }

    //班主任导入
    @ResponseBody
    @RequestMapping(value = "/master/import")
    public ResponseEntity importMaster(@RequestParam(value = "file") MultipartFile file) throws Exception {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        Long begin = System.currentTimeMillis();
        List<IOCMasterView> errorIOCMasterView = new ArrayList<IOCMasterView>();
        List<TeacherClass> correctTeacherClassList = new ArrayList<TeacherClass>();
        List<IOCMasterView> importItem = new ArrayList<IOCMasterView>();
        List<ClassSection> classSectionList = classService.getAllClassSectionBySchoolId(user.getSchoolId());
        Map classSectionMap = new HashedMap();
        for (ClassSection classSection : classSectionList) {
            classSectionMap.put(classSection.getName() + schoolId, classSection.getId());
        }

        List<GradeClass> classList = classService.getAllClassBySchoolId(user.getSchoolId());
        Map classMap = new HashedMap();
        for (GradeClass banji : classList) {
            classMap.put(banji.getName() + banji.getNj() + banji.getXd(), banji);
        }

        List<Teacher> teacherLlist = teacherService.findAllTeacher(schoolId);
        Map<String, Teacher> teacherMap = new HashedMap();
        Map<String, Teacher> teacherMapName = new HashedMap();
        for (Teacher teacher : teacherLlist) {
            teacherMapName.put(teacher.getName() + schoolId, teacher);
            teacherMap.put(teacher.getNo() + schoolId, teacher);
        }

        TeachCycle cycleLatest = getLatestTeachCycle(user.getSchoolId());
        ImportExcel importExcel = new ImportExcel(file, 2, 0);
        List<IOCMasterView> list = importExcel.getDataList(IOCMasterView.class);
        Map res = new HashMap();

        //去除名字一样的
        Integer count = 0;
        if (list.size() > 0) {
            for (int i = 0; i < list.size() - 1; i++) {
                for (int j = list.size() - 1; j > i; j--) {
                    if (list.get(i).getMasterName().equals(list.get(j).getMasterName())) {
                        errorIOCMasterView.add(list.get(j));
                        list.remove(j);
                        count++;
                    }
                }
            }
        }

        for (IOCMasterView iocMasterView : list) {
            try {

                TeacherClass ref1 = new TeacherClass();
                if (ConstantUtil.getKeyByValueAndFlag(iocMasterView.getNj(), "nj") == 0) {
                    errorIOCMasterView.add(iocMasterView);
                    continue;
                }
                String xdId = (String) classSectionMap.get(iocMasterView.getXdName() + schoolId);
                GradeClass gradeClass = (GradeClass) classMap.get(iocMasterView.getBj() + ConstantUtil.getKeyByValueAndFlag(iocMasterView.getNj(), "nj") + xdId);
                String classId = "";
                if (gradeClass != null) {
                    classId = gradeClass.getId();
                } else {
                    continue;
                }

                //根据学期和学年导入数据时候首先获取cycleId
                String cycleYear = iocMasterView.getCycleYear();
                int cycleSemester = iocMasterView.getCycleSemester();
                //获取cycleId
                TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, cycleSemester, schoolId);
                String cycleId = null;
                if (teachCycle.getId() != null) {
                    cycleId = teachCycle.getId();
                }
                if (gradeClass.getNj().equals(ConstantUtil.getKeyByValueAndFlag(iocMasterView.getNj(), "nj")))
                    if (gradeClass.getXd().equals(xdId))
                        ref1.setClassId(gradeClass.getId());
                ref1.setCycleId(cycleId);

                //班主任
                //中英文符号的替换
                String _masterName = iocMasterView.getMasterName();
                String amasterName = _masterName.replace("（", "(");
                String masterName = amasterName.replace("）", ")").replace(" ", "");
                String[] masterNoArray = masterName.split("\\(");
                String teacherId = "";

                //查询导入的班主任的id
                if (masterNoArray.length >= 2) {
                    String masterNo = masterNoArray[1].substring(0, masterNoArray[1].length() - 1);
                    if (masterNo.equals(teacherMap.get(masterNo + schoolId).getNo())) {
                        Teacher teacher = teacherMap.get(masterNo + schoolId);
                        teacherId = teacher.getId();
                        ref1.setTeacherId(teacherMap.get(masterNo + schoolId).getId());
                    }
                } else if (masterName.equals(teacherMapName.get((masterName + schoolId).replace(" ", "")).getName())) {
                    Teacher teacher = teacherMapName.get(masterName + schoolId);
                    teacherId = teacher.getId();
                    ref1.setTeacherId(teacherId);
                    if (StringUtils.isEmpty(teacher.getAccount())) {
                        throw new ErrcodeException("班主任账号不存在，请先生成账号");
                    }
                } else {
                    throw new ErrcodeException("该教师信息不存在");
                }


                ref1.setType(1);
                TeacherClass teacherClass = teachTaskService.findTeacherClassByClassIdCycleIdTeacherId(classId, cycleId, teacherId, 1);
                if (teacherClass.getClassId() == null)
                    correctTeacherClassList.add(ref1);

                //副班主任
                //中英文符号替换
                String _deputyNoStr = iocMasterView.getDeputymasterName();
                if (_deputyNoStr != null) {
                    String douhaoDeputyNoStr = _deputyNoStr.replace("；", ";");
                    String aDeputyNoStr = douhaoDeputyNoStr.replace("（", "(");
                    String deputyNoStr = aDeputyNoStr.replace("）", ")");
                    if (deputyNoStr != null) {
                        String[] deputyNoArray = deputyNoStr.split(";");
                        int index = 0;
                        for (int i = 0; i < deputyNoArray.length; i++) {

                            String _deputyNo = deputyNoArray[i];
                            String[] _deputyArray = _deputyNo.split("\\(");
                            TeacherClass ref2 = new TeacherClass();
                            ref2.setClassId(gradeClass.getId());

                            if (_deputyArray.length >= 2) {
                                String deputyNo = _deputyArray[1].substring(0, _deputyArray[1].length() - 1);
                                if (deputyNo.equals(teacherMap.get(deputyNo + schoolId).getNo())) {
                                    Teacher teacher = teacherMap.get(deputyNo + schoolId);
                                    teacherId = teacher.getId();
                                    ref2.setTeacherId(teacherId);
                                }
                            } else if (_deputyNo.equals(teacherMapName.get(_deputyNo + schoolId).getName())) {
                                Teacher teacher = teacherMapName.get((_deputyNo + schoolId).replace(" ", ""));
                                teacherId = teacher.getId();
                                ref2.setTeacherId(teacherId);
                            } else {
                                throw new ErrcodeException("该教师信息不存在");
//                                break;
                            }
                            ref2.setType(2);
                            ref2.setCycleId(cycleId);

                            TeacherClass teacherClass1 = teachTaskService.findTeacherClassByClassIdCycleIdTeacherId(classId, cycleId, teacherId, 2);
                            if (teacherClass1.getTeacherId() == null) {
                                correctTeacherClassList.add(ref2);
                            }
                            index++;
                            if (index == deputyNoArray.length) {
                                importItem.add(iocMasterView);
                            }

                        }
                    }
                } else {
                    importItem.add(iocMasterView);
                }
            } catch (Exception e) {
                e.printStackTrace();
                errorIOCMasterView.add(iocMasterView);
                continue;
            }
        }
        if (correctTeacherClassList.size() > 0)
            teachTaskService.insertBatchTeacherClass(correctTeacherClassList);
        Long end = System.currentTimeMillis();

        res.put("msg", "导入完成，共" + importItem.size() + "条成功，" + errorIOCMasterView.size() + "条失败,耗时" + (end - begin) / 1000 + "秒");
        res.put("errorList", errorIOCMasterView);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    /**
     * 教务管理-任课教师管理
     *
     * @param request
     * @param model
     * @return
     */

//任课教师首页
    @RequestMapping(value = "/course/teacher/index")
    public String courseTeacherIndex(HttpServletRequest request, Model model) {
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        String courseId = getParamVal(request, "courseId");
        String cycleYear = getParamVal(request, "cycleYear");
        String cycleSemester = getParamVal(request, "cycleSemester");
        String nj = getParamVal(request, "nj");
        String xdId = getParamVal(request, "xdId");

        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        //查询所有的学段信息
        List<ClassSection> classSections = classService.getAllClassSectionBySchoolId(schoolId);
        if (xdId == "") {
            if (classSections.size() > 0) {
                xdId = classSections.get(0).getId();
            }
        }
        //根据学段查询所有的年级
        List<GradeClass> njList = classService.selectClassByXdAndSchoolId(xdId, schoolId);
        for (int k = 0; k < njList.size() - 1; k++) {
            for (int l = njList.size() - 1; l > k; l--) {
                if (njList.get(l).getNj().equals(njList.get(k).getNj()) && njList.get(l).getXd().equals(njList.get(k).getXd())) {
                    njList.remove(l);
                }
            }
        }
        if (StringUtils.isEmpty(nj) || nj.equals("null")) {
            if (njList.size() > 0) {
                nj = njList.get(0).getNj() + "";
            } else {
                nj = "1";
            }
        }
        //根据学年学期获得周期
        TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), user.getSchoolId());

        //获得周期列表，补全两个下拉框
        List<TeachCycle> cycleList = teachTaskService.getCycleList(user.getSchoolId());

        Map yearMap = new LinkedHashMap();
        List<TeachCycle> semesterList = new ArrayList<>();
        if (GukeerStringUtil.isNullOrEmpty(teachCycle.getId())) {
            if (cycleList.size() > 0) {
                teachCycle = cycleList.get(0);
            }
        }
        if (StringUtil.isEmpty(cycleYear))
            if (!GukeerStringUtil.isNullOrEmpty(teachCycle.getId()))
                cycleYear = teachCycle.getCycleYear();

        if (cycleList.size() > 0) {
            for (TeachCycle cycle : cycleList) {
                if (cycleYear.equals(cycle.getCycleYear()))
                    semesterList.add(cycle);
                yearMap.put(cycle.getCycleYear(), cycle);
            }
        }


        //根据cycleId、schoolId查询存在的课程
        String cycleId = "";
        if (!GukeerStringUtil.isNullOrEmpty(teachCycle.getId()))
            cycleId = teachCycle.getId();

        List<Course> courseList = teachTaskService.findAllCourseBySchoolIdAndCycleId(schoolId, cycleId);

        String courseName = null;
        if (StringUtil.isEmpty(courseId) || courseId.equals("undefined") || courseId.equals("null")) {
            if (courseList != null && courseList.size() > 0) {
                courseId = courseList.get(0).getId();
                courseName = courseList.get(0).getName();
            }
        }

        //根据courseId查询课程班级关联表
        List<CourseClass> courseClassList = teachTaskService.findAllCourseClassByCourseId(courseId);
        model.addAttribute("cycleList", cycleList);
        model.addAttribute("cycleYear", cycleYear);
        model.addAttribute("cycleSemester", cycleSemester);
        model.addAttribute("yearList", yearMap.keySet());
        model.addAttribute("semesterList", semesterList);
        //根据classIdList和teacherIdList查询所有的任课老师
        model.addAttribute("courseName", courseName);
        model.addAttribute("courseList", courseList);
        model.addAttribute("courseId", courseId);
        model.addAttribute("xd", classSections);
        model.addAttribute("cycleId", cycleId);
        model.addAttribute("xdId", xdId);
        model.addAttribute("nj", nj);
        model.addAttribute("njList", njList);
        if (courseClassList.size() == 0) {
            return "teachTask/courseTeacher";
        }

        PageInfo<BZRView> pageInfo = new PageInfo<>();
        if (StringUtils.isNotEmpty(cycleId)) {
            pageInfo = teachTaskService.findAllCourseTeacherByCourseIdAndXdAndNjAndCycleId(courseId, pageNum, pageSize, teachCycle.getId(), NumberConvertUtil.convertS2I(nj), xdId);
        }
        List<BZRView> list = pageInfo.getList();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (StringUtils.isEmpty(list.get(i).getTeacherId())) {
                    list.remove(i);
                }
            }
        }
        model.addAttribute("pageInfo", pageInfo);
        return "teachTask/courseTeacher";
    }

    //任课教师编辑的弹窗
    @RequestMapping(value = "/course/teacher/edit/pop")
    public String courseTeacherEditPop(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        String courseClassId = request.getParameter("refId");
        String cycleId = request.getParameter("cycleId");
        String classId = request.getParameter("classId");
        String teacherId = request.getParameter("teacherId");
        String courseId = request.getParameter("courseId");

        GradeClass gradeClass =null;
        Teacher teacher = null;
        Course course =null;
        ClassSection classSection = null;

        if (StringUtils.isNotEmpty(classId)){
            gradeClass = classService.selectClassById(classId);
            if (gradeClass != null){
                String xdId = gradeClass.getXd();
                classSection = teachTaskService.findClassSectionById(xdId);
            }
        }
        if (StringUtils.isNotEmpty(courseId)){
            course = teachTaskService.findCourseById(courseId);
        }
        if (StringUtils.isNotEmpty(teacherId)){
            teacher = teacherService.findTeacherById(teacherId.trim());
        }

//        String nj = request.getParameter("nj");
//        String bj = request.getParameter("bj");
//        String xd = request.getParameter("xd");
//        String className = request.getParameter("className");
//        String courseName = request.getParameter("courseName");
//        String teacherName = request.getParameter("teacherName");
        //根据schoolId查询所有的老师
        List<Teacher> _teacherList = teacherService.findAllTeacher(schoolId);
//        try {
//            bj = new String(bj.getBytes("iso8859-1"), "utf-8");
//            xd = new String(xd.getBytes("iso8859-1"), "utf-8");
//            teacherName = new String(teacherName.getBytes("iso8859-1"), "utf-8");
//            courseName = new String(courseName.getBytes("iso8859-1"), "utf-8");
//            className = new String(className.getBytes("iso8859-1"), "utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        JSON teacherList = (JSON) JSON.toJSON(_teacherList);
        model.addAttribute("cycleId", cycleId);
        model.addAttribute("teacherList", teacherList);
        model.addAttribute("classSection", classSection);
        model.addAttribute("course", course);
        model.addAttribute("teacher", teacher);
        model.addAttribute("gradeClass", gradeClass);
        model.addAttribute("courseClassId", courseClassId);
        return "teachTask/pop/courseTeacherEdit";
    }


    @RequestMapping(value = "/course/teacher/edit")
    public String courseTeacherEdit(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String courseClassId = request.getParameter("courseClassId");
        String classId = request.getParameter("classId");
        String cycleId = request.getParameter("cycleId");
        String teacherIdFromHouTai = request.getParameter("teacherIdFromHouTai");
        String teacherId = request.getParameter("teacherId");
        if (courseClassId != null) {
            CourseClass courseClass = teachTaskService.selectCourseClassByKey(courseClassId);
            if (courseClass != null) {
                if (StringUtils.isNotEmpty(teacherId)) {
                    courseClass.setTeacherId(teacherId);
                    TeacherClass teacherClass = new TeacherClass();
                    teacherClass.setCycleId(cycleId);
                    teacherClass.setClassId(classId);
                    teacherClass.setType(3);
                    teacherClass.setTeacherId(teacherId);
                    teachTaskService.delTeacherClassByClassIdAndTeacherIdAndType(teacherIdFromHouTai,classId,3,cycleId,teacherClass);
                } else {
                    courseClass.setTeacherId(teacherIdFromHouTai);
                }

                int succ = teachTaskService.updateCourseClassByPrimareyKey(courseClass);
            }
        }
        return "teachTask/courseTeacher";
    }


    //导入任课教师
    @ResponseBody
    @RequestMapping(value = "/course/teacher/import")
    public ResponseEntity importExcel(@RequestParam(value = "file") MultipartFile file) throws Exception {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        Long begin = System.currentTimeMillis();
        List<IOCCourseTeacherViewError> errorIOCCourseTeacherViewList = new ArrayList<IOCCourseTeacherViewError>();
        List<CourseClass> correcCourseClassList = new ArrayList<CourseClass>();
        IOCCourseTeacherViewError errCourse = new IOCCourseTeacherViewError();
        ImportExcel importExcel = new ImportExcel(file, 2, 0);
        List<IOCCourseTeacherView> list = importExcel.getDataList(IOCCourseTeacherView.class, 1);

        List<ClassSection> classSectionList = classService.getAllClassSectionBySchoolId(user.getSchoolId());
        Map classSectionMap = new HashedMap();
        for (ClassSection classSection : classSectionList) {
            classSectionMap.put(classSection.getName() + schoolId, classSection.getId());
        }

        List<GradeClass> classList = classService.getAllClassBySchoolId(user.getSchoolId());
        Map classMap = new HashedMap();
        for (GradeClass banji : classList) {
            classMap.put(banji.getName() + banji.getNj() + banji.getXd(), banji);
        }

        List<Teacher> teacherLlist = teacherService.findAllTeacher(schoolId);
        Map<String, Teacher> teacherMap = new HashedMap();
        Map<String, Teacher> teacherMapName = new HashedMap();
        for (Teacher teacher : teacherLlist) {
            teacherMapName.put(teacher.getName() + schoolId, teacher);
            teacherMap.put(teacher.getNo() + schoolId, teacher);
        }


        //查询所有课程的名字
        Map courseNameMap = new HashMap();
        List<Course> courseList = teachTaskService.findAllCourseBySchoolIdAndCycleId(schoolId, null);
        for (Course course : courseList) {
            courseNameMap.put(course.getCycleId() + course.getName(), course.getId());
        }

        //获取最新的教学周期
        TeachCycle teachCycle = getLatestTeachCycle(schoolId);
        String cycleId = teachCycle.getId();


        //查询所有的任课教师
        Map courseTeacherMap = new HashMap();

        //用来判断这个学期的课程班级信息能不能对应的上是不是
        Map courseClassMap = new HashedMap();
        Map courseClassMapForId = new HashedMap();
        List<CourseClass> courseTeacherList = teachTaskService.findAllCourseClassByCourseList(courseList);
        for (CourseClass courseClass : courseTeacherList) {
            courseTeacherMap.put(courseClass.getCourseId() + courseClass.getClassId() + courseClass.getTeacherId(), courseClass.getId());
            courseClassMapForId.put(courseClass.getClassId() + courseClass.getCourseId(), courseClass.getId());
        }

        List<TeacherClass> teacherClasses = new ArrayList<>();
        int i = 0;
        for (IOCCourseTeacherView iocCourseTeacherView : list) {
            errCourse = new IOCCourseTeacherViewError();
            try {
                //gradeclassId、courseId
                if (iocCourseTeacherView != null) {
                    errCourse.setNj(iocCourseTeacherView.getNj());
                    errCourse.setXdName(iocCourseTeacherView.getXdName());
                    errCourse.setBj(iocCourseTeacherView.getBj());
                    errCourse.setCourse(iocCourseTeacherView.getCourse());
                    errCourse.setCourseTeacher(iocCourseTeacherView.getCourseTeacher());
                }
                String gradeClassId = "";
                String xdId = (String) classSectionMap.get(iocCourseTeacherView.getXdName() + schoolId);
                GradeClass gradeClass = (GradeClass) classMap.get(iocCourseTeacherView.getBj() + ConstantUtil.getKeyByValueAndFlag(iocCourseTeacherView.getNj(), "nj") + xdId);

                //拿到对应的classId
                if (gradeClass != null) {
                    if (gradeClass.getXd().equals(xdId)) {
                        if (gradeClass.getNj() == ConstantUtil.getKeyByValueAndFlag(iocCourseTeacherView.getNj(), "nj")) {
                            if (gradeClass.getName().equals(iocCourseTeacherView.getBj())) {
                                gradeClassId = gradeClass.getId();
                            }
                        }
                    }
                } else {
                    errCourse.setFailedReason("班级信息不存在");
                    errorIOCCourseTeacherViewList.add(errCourse);
                    continue;
                }


                String courseId = (String) courseNameMap.get((cycleId + iocCourseTeacherView.getCourse()).replace(" ", ""));
                //????????????????????????????????????老师姓名+（编号）

                String _courseTeacher = iocCourseTeacherView.getCourseTeacher();
                String acourseTeacher = _courseTeacher.replace("（", "(");
                String courseTeacher = acourseTeacher.replace("）", ")");
                String[] courseteachArray = courseTeacher.split("\\(");
                String teacherId = "";

                CourseClass courseClass = new CourseClass();
                courseClass.setClassId(gradeClassId);
                if (courseteachArray.length >= 2) {
                    String courseTeacherNo = courseteachArray[1].substring(0, courseteachArray[1].length() - 1);
                    if (courseTeacherNo.equals(teacherMap.get(courseTeacherNo + schoolId).getNo())) {
                        Teacher teacher = teacherMap.get(courseTeacherNo + schoolId);
                        teacherId = teacher.getId();
                    }
                } else if (null != teacherMapName.get(courseTeacher + schoolId) && courseTeacher.equals(teacherMapName.get(courseTeacher + schoolId).getName())) {
                    Teacher teacher = teacherMapName.get(courseTeacher + schoolId);
                    teacherId = teacher.getId();
                } else {
//                    logger.info(teacherMapName.get(courseTeacher + schoolId).getName() + "----该教师信息不存在--------");
                    if (null == teacherMapName.get(courseTeacher + schoolId)) {
                        errCourse.setFailedReason("该教师信息不存在");
                        errorIOCCourseTeacherViewList.add(errCourse);
                        continue;
                    }
                }
                courseClass.setTeacherId(teacherId);

                //这个代码是用来判断是不是已经存在这样的任课教师信息。如果存在 则不再在导入
                String courseTeacherId = (String) courseTeacherMap.get(courseId + gradeClassId + teacherId);
                if (courseTeacherId != null) {
                    errCourse.setFailedReason("该任课教师信息已经存在");
                    errorIOCCourseTeacherViewList.add(errCourse);
                    continue;
                }

                if (courseId == null) {
                    errCourse.setFailedReason("该课程信息信息不存在");
                    throw new ErrcodeException(iocCourseTeacherView.getCourse() + "课程不存在，请先到课程管理目录创建该课程");
                }

                courseClass.setCourseId(courseId);
                //这个用来判断 课程和=班级的对应关系 =，如果没有 就不能导入成功
                if (courseClassMapForId.get(gradeClassId + courseId) == null) {
                    errCourse.setFailedReason(errCourse.getCourse() + "没有设置上课班级");
                    errorIOCCourseTeacherViewList.add(errCourse);
                    continue;
                }
                //此时classId 、courseId、 teacherId都有了 插入中间表 导入成功
                correcCourseClassList.add(courseClass);
                TeacherClass teacherClass = new TeacherClass();
                teacherClass.setTeacherId(teacherId);
                teacherClass.setType(3);
                teacherClass.setClassId(gradeClassId);
                teacherClass.setCycleId(cycleId);
                teacherClasses.add(teacherClass);
            } catch (Exception e) {
                errorIOCCourseTeacherViewList.add(errCourse);
                e.printStackTrace();
                continue;
            }
        }
        if (correcCourseClassList.size() > 0) {
            //执行批量更新，因为course和class已经有这条数据，只能是更新
            for (CourseClass courseClass : correcCourseClassList) {
                String courseClassId = (String) courseClassMapForId.get(courseClass.getClassId() + courseClass.getCourseId());
                courseClass.setId(courseClassId);
                teachTaskService.updateCourseClassByCourseIdAndClassId(courseClass,teacherClasses);
            }
        }

        Long end = System.currentTimeMillis();
        Map res = new HashMap();
        res.put("msg", "共" + correcCourseClassList.size() + "条成功，" + errorIOCCourseTeacherViewList.size() + "条失败,耗时" + (end - begin) / 1000 + "秒");
        res.put("errorList", errorIOCCourseTeacherViewList);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    //任课教师下载导入模板
    @ResponseBody
    @RequestMapping(value = "/course/teacher/moban/download")
    public void exportCourseTeacher(HttpServletResponse response) {
        try {
            String fileName = "任课教师导入模板.xlsx";
            String anno = "请确保您的学段、年级、班级等字段的对应";
            new ExportExcel(false, "任课教师导入模板", IOCCourseTeacherView.class, 2, anno, 1).setDataList(new ArrayList()).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //查看所有任课教师安排
    @RequestMapping(value = "/course/teacher/all")
    public String courseTeacherAll(HttpServletRequest request, Model model) {

        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        //查询所有的任课教师安排
        List<CourseClassView> coureClassViewList = teachTaskService.findAllCourseTeacherBySchoolId(schoolId, null);
        List<IOCCourseTeacherView> iocCourseTeacherViews = new ArrayList<>();
        if (coureClassViewList != null) {
            for (CourseClassView courseClassView : coureClassViewList) {
                IOCCourseTeacherView iocCourseTeacherView = new IOCCourseTeacherView();
                iocCourseTeacherView.setBj(courseClassView.getClassName());
                iocCourseTeacherView.setCourse(courseClassView.getCourseName());
                iocCourseTeacherView.setCourseTeacher(courseClassView.getTeacherName());
                iocCourseTeacherView.setNj(courseClassView.getNj().toString());
                iocCourseTeacherView.setXdName(courseClassView.getClassSection());
                iocCourseTeacherViews.add(iocCourseTeacherView);
            }
        }
        JSON json = (JSON) JSON.toJSON(iocCourseTeacherViews);
        model.addAttribute("coureClassViewList", coureClassViewList);
        model.addAttribute("json", json);
        return "teachTask/pop/allCourseTeacher";
    }


    //任课教师搜索
    @RequestMapping(value = "/course/teacher/search")
    public String courseTeacherSearch(HttpServletRequest request, Model model) {
        //在页面获取所有的查询条件
        String cycleYear = getParamVal(request, "cycleYear");
        String cycleSemester = getParamVal(request, "cycleSemester");
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        String teacherName = request.getParameter("name");

        ////解决teacherName乱码问题。
        try {
            if (teacherName != null)
                teacherName = java.net.URLDecoder.decode(teacherName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //根据学年学期获得周期
        TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), user.getSchoolId());

        //获得周期列表，补全两个下拉框
        List<TeachCycle> cycleList = teachTaskService.getCycleList(user.getSchoolId());
        if (GukeerStringUtil.isNullOrEmpty(teachCycle))
            if (cycleList.size() > 0)
                teachCycle = cycleList.get(0);

        List<TeachCycle> semesterList = new ArrayList<>();
        if (StringUtil.isEmpty(cycleYear))
            cycleYear = teachCycle.getCycleYear();

        Map yearMap = new LinkedHashMap();
        for (TeachCycle cycle : cycleList) {
            if (cycleYear.equals(cycle.getCycleYear()))
                semesterList.add(cycle);
            yearMap.put(cycle.getCycleYear(), cycle);
        }

        String cycleId = teachCycle.getId();
        teacherName = "%" + teacherName + "%";
        //根据cycleId和schoolId查询courseIdList
        List<BZRView> courseTeacherList = teachTaskService.findCourseTeacherByCycleIdAndSchoolIdAndName(schoolId, cycleId, teacherName);

        HttpSession session = request.getSession();
        if (StringUtil.isNotEmpty(getParamVal(request, "appId")))
            session.setAttribute("teachTask", getParamVal(request, "appId"));//将当前应用id存到session中

        model.addAttribute("cycleYear", cycleYear);
        model.addAttribute("cycleSemester", cycleSemester);
        model.addAttribute("yearList", yearMap.keySet());
        model.addAttribute("semesterList", semesterList);
        model.addAttribute("courseTeacherList", courseTeacherList);

        return "teachTask/courseTeacherSearch";
    }

    //班主任 导入模板下载
    @ResponseBody
    @RequestMapping(value = "/master/moban/download")
    public void exportMaster(HttpServletResponse response) {
        try {
            String fileName = "班主任导入模板.xlsx";
            String anno = "注释：除副班主任外所有字段为必填项\n" +
                    "          1.学年、学期、学段、年级、班级信息都需与教务管理和学籍管理中的信息相符才能导入成功\n" +
                    "          2.班主任和副班主任若有重名请按如下格式填写：姓名（教师编号）；姓名（教师编号）\n";
            new ExportExcel(false, "班主任导入模板", IOCMasterView.class, 2, anno, 1).setDataList(new ArrayList()).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //下载导入失败的CourseClass表
    @RequestMapping(value = "/master/error/export", method = RequestMethod.POST)
    public void errorMaster(HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileName = "错误信息列表.xlsx";
            String anno = "注释：橙色字段为必填项\n" +
                    "          1.学年、学期、学段、年级、班级信息都需与教务管理和学籍管理中的信息相符才能导入成功\n" +
                    "          2.班主任和副班主任若有重名请按如下格式填写：姓名（教师编号）；姓名（教师编号）\n";
            String msg = getParamVal(request, "msg");
            JsonArray jsonArray = new JsonParser().parse(msg).getAsJsonArray();
            List<IOCMasterView> exportFile = new ArrayList<IOCMasterView>();
            for (JsonElement jsonElement : jsonArray) {
                IOCMasterView importBundling = GsonUtil.fromJson(jsonElement.getAsJsonObject(), IOCMasterView.class);
                exportFile.add(importBundling);
            }
            new ExportExcel(false, "班主任错误信息列表", IOCMasterView.class, 2, anno, 1).setDataList(exportFile).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //下载所有任课教师的安排列表
    @RequestMapping(value = "/course/teacher/export", method = RequestMethod.POST)
    public void courseClassExport(HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileName = "所有任课教师安排列表.xlsx";
            String anno = "注释：橙色字段为必填项\n" +
                    "          学期字段填写1或者2，1表示第一学期\n";
            String courseClassViews = getParamVal(request, "courseClassViews");
            JsonArray jsonArray = new JsonParser().parse(courseClassViews).getAsJsonArray();
            List<IOCCourseTeacherView> exportFile = new ArrayList<IOCCourseTeacherView>();
            for (JsonElement jsonElement : jsonArray) {
                IOCCourseTeacherView importBundling = GsonUtil.fromJson(jsonElement.getAsJsonObject(), IOCCourseTeacherView.class);
                exportFile.add(importBundling);
            }
            new ExportExcel(false, "任课教师安排信息", IOCCourseTeacherView.class, 2, anno, 1).setDataList(exportFile).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //下载导入失败的CourseClass表
    @RequestMapping(value = "/course/teacher/error/export", method = RequestMethod.POST)
    public void errorCourseClass(HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileName = "错误信息列表.xlsx";
            String anno = "请确保您的学段、年级、班级等字段的对应";
            String msg = getParamVal(request, "msg");
            JsonArray jsonArray = new JsonParser().parse(msg).getAsJsonArray();
            List<IOCCourseTeacherViewError> exportFile = new ArrayList<IOCCourseTeacherViewError>();
            for (JsonElement jsonElement : jsonArray) {
                IOCCourseTeacherViewError importBundling = GsonUtil.fromJson(jsonElement.getAsJsonObject(), IOCCourseTeacherViewError.class);
                exportFile.add(importBundling);
            }
            new ExportExcel(false, "任课教师错误信息列表", IOCCourseTeacherViewError.class, 2, anno, 1).setDataList(exportFile).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // string类型转换为long类型
// strTime要转换的String类型的时间
// formatType时间格式
// strTime的时间格式和formatType的时间格式必须相同
    public static long stringToLong(String strTime, String formatType)
            throws ParseException {
        Date date = stringToDate(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

    // string类型转换为date类型
// strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
// HH时mm分ss秒，
// strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    // date类型转换为long类型
// date要转换的date类型的时间
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    // long类型转换为String类型
// currentTime要转换的long类型的时间
// formatType要转换的string类型的时间格式
    public static String longToString(long currentTime, String formatType)
            throws ParseException {
        Date date = longToDate(currentTime, formatType); // long类型转成Date类型
        String strTime = dateToString(date, formatType); // date类型转成String
        return strTime;
    }

    // long转换为Date类型
// currentTime要转换的long类型的时间
// formatType要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    public static Date longToDate(long currentTime, String formatType)
            throws ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    // date类型转换为String类型
// formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
// data Date类型的时间
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }


//综合管理

    /**
     * 教务管理-班级教室管理
     *
     * @param request
     * @param model
     * @return
     */

//班级教室首页
    @RequestMapping(value = "/ref/class/room/index", method = RequestMethod.GET)
    public String refClassRoomIndex(HttpServletRequest request, Model model) {
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        String cycleYear = getParamVal(request, "cycleYear");
        String cycleSemester = getParamVal(request, "cycleSemester");
        String xdId = getParamVal(request, "xdId");
        String nj = getParamVal(request, "nj");
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        //根据学年学期获得周期
        TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), user.getSchoolId());

        //获得周期列表，补全两个下拉框
        List<TeachCycle> cycleList = teachTaskService.getCycleList(user.getSchoolId());
        if (GukeerStringUtil.isNullOrEmpty(teachCycle.getId()))
            if (cycleList.size() > 0)
                teachCycle = cycleList.get(0);

        List<TeachCycle> semesterList = new ArrayList<>();
        if (StringUtil.isEmpty(cycleYear))
            cycleYear = teachCycle.getCycleYear();

        Map yearMap = new LinkedHashMap();
        for (TeachCycle cycle : cycleList) {
            if (cycleYear.equals(cycle.getCycleYear()))
                semesterList.add(cycle);
            yearMap.put(cycle.getCycleYear(), cycle);
        }
        Map param = new HashMap();
        param.put("pageNum", pageNum);
        param.put("pageSize", pageSize);
        param.put("schoolId", user.getSchoolId());
        param.put("cycleId", teachCycle.getId());
        String cycleId = teachCycle.getId();
        //根据学校id查询学段的名字
        List<ClassSection> classSectionList = classService.getAllClassSectionBySchoolId(schoolId);
        if (xdId == "") {
            if (classSectionList.size() > 0) {
                xdId = classSectionList.get(0).getId();
            }
        }

        if (nj == "") {
            nj = "1";
        }

        PageInfo<RefClassRoomView> pageInfo = new PageInfo<>();
        if (StringUtils.isNotEmpty(cycleId)) {
            pageInfo = teachTaskService.getRefClassRoomList(pageNum, pageSize, schoolId, cycleId, NumberConvertUtil.convertS2I(nj), xdId);
        }

        model.addAttribute("yearList", yearMap.keySet());
        model.addAttribute("semesterList", semesterList);
        model.addAttribute("classSectionList", classSectionList);
        model.addAttribute("cycleYear", cycleYear);
        model.addAttribute("nj", nj);
        model.addAttribute("xdId", xdId);
        model.addAttribute("cycleSemester", cycleSemester);
        model.addAttribute("pageInfo", pageInfo);
        return "teachTask/refClassRoom";

    }


    //下载教室班级模板
    @ResponseBody
    @RequestMapping(value = "/ref/class/room/download")
    public void exportRefClassRoom(HttpServletResponse response) {
        try {
            String fileName = "班级教室导入模板.xlsx";
            String anno = "注释：橙色字段为必填项\n" +
                    "          1.校区一栏填写:主校区、西校区，按此规则填写;教室类型一栏请填写存在的类型\n" +
                    "          2.楼层、容纳人数、有效座位数、考试座位数、房间号请填写整数\n" +
                    "          3.是否用于选课请填写是或者否 \n";

            new ExportExcel(false, "班级教室导入模板", IOCRefClassRoomView.class, 2, anno, 1).setDataList(new ArrayList()).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //班级教室导入
    @ResponseBody
    @RequestMapping(value = "/ref/class/room/import")
    public ResponseEntity importRefClassRoom(@RequestParam(value = "file") MultipartFile file) throws Exception {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        Long begin = System.currentTimeMillis();


        List<ClassSection> classSectionList = classService.getAllClassSectionBySchoolId(user.getSchoolId());
        Map classSectionMap = new HashedMap();
        for (ClassSection classSection : classSectionList) {
            classSectionMap.put(classSection.getName() + schoolId, classSection.getId());
        }

        List<GradeClass> classList = classService.getAllClassBySchoolId(user.getSchoolId());
        Map classMap = new HashedMap();
        for (GradeClass banji : classList) {
            classMap.put(banji.getName() + banji.getNj() + banji.getXd() + schoolId, banji);
        }

        List<IOCRefClassRoomViewError> errorRefClassRoomList = new ArrayList<IOCRefClassRoomViewError>();
        List<RefClassRoom> correctRefClassRoomList = new ArrayList<RefClassRoom>();
        IOCRefClassRoomViewError errRefClassRoom = null;

        ImportExcel importExcel = new ImportExcel(file, 2, 0);
        List<IOCRefClassRoomView> list = importExcel.getDataList(IOCRefClassRoomView.class, 1);


        List<SchoolType> schoolTypeList = schoolService.selectSchoolTypeBySchoolId(user.getSchoolId());
        Map schoolTypeMap = new HashMap();
        for (SchoolType schoolType : schoolTypeList) {
            schoolTypeMap.put(schoolType.getName() + schoolId, schoolType.getId());
        }

        //首先根据schoolId查询出所有的classRoomId
        List<ClassRoom> classRoomListByschoolId = teachTaskService.findAllClassRoomBySchoolId(schoolId);
        //查询所有的班级教室信息
        List<RefClassRoom> allRefClassRoomList = null;
//        if (classRoomListByschoolId != null) {
        if (classRoomListByschoolId.size() > 0) {
            allRefClassRoomList = teachTaskService.findAllRefClassRoomByClassRoomId(classRoomListByschoolId);
        }
        Map refClassRoomMap = new HashMap();
        if (allRefClassRoomList != null && allRefClassRoomList.size() > 0) {
            for (RefClassRoom refClassRoom : allRefClassRoomList) {
                refClassRoomMap.put(refClassRoom.getCycleId() + refClassRoom.getGradeClass() + refClassRoom.getRoomId(), refClassRoom);
            }
        }

        //查询所有教室信息
        Map classRoomMap = new HashMap();
        for (ClassRoom classRoom : classRoomListByschoolId) {
            classRoomMap.put(classRoom.getSchoolType() + classRoom.getTeachBuilding() + classRoom.getFloor() + classRoom.getRoomNum(), classRoom);
        }
        //查询所有的教学周期
        //查询所有的教学周期
        List<TeachCycle> cycleList = teachTaskService.getCycleList(user.getSchoolId());

        Map cycleMap = new HashMap();
        for (TeachCycle teachCycle : cycleList) {
            cycleMap.put(teachCycle.getCycleYear() + teachCycle.getCycleSemester(), teachCycle.getId());
        }
        //查询所有的班级教室信息
        for (IOCRefClassRoomView iocRefClassRoomView : list) {
            try {
                //gradeclassId、courseId
                RefClassRoom refClassRoom = new RefClassRoom();
                errRefClassRoom = new IOCRefClassRoomViewError();
                if (iocRefClassRoomView != null) {
                    errRefClassRoom.setFloor(iocRefClassRoomView.getFloor());
                    errRefClassRoom.setBj(iocRefClassRoomView.getBj());
                    errRefClassRoom.setTeachBuilding(iocRefClassRoomView.getTeachBuilding());
                    errRefClassRoom.setXiaoQu(iocRefClassRoomView.getXiaoQu());
                    errRefClassRoom.setXdName(iocRefClassRoomView.getXdName());
                    errRefClassRoom.setNj(iocRefClassRoomView.getNj());
                    errRefClassRoom.setCycleYear(iocRefClassRoomView.getCycleYear());
                    errRefClassRoom.setClassRoomNum(iocRefClassRoomView.getClassRoomNum());
                    errRefClassRoom.setCycleSemester(iocRefClassRoomView.getCycleSemester());
                }
                ///////////////////////id
                String gradeClassId = "";
                String cycleId = "";
                //根据表格拿到学年和学期
                String cycleYear = iocRefClassRoomView.getCycleYear();
                Integer cycleSemester = iocRefClassRoomView.getCycleSemester();
                if (null != cycleMap.get(cycleYear + cycleSemester)) {
                    cycleId = cycleMap.get(cycleYear + cycleSemester).toString();
                }
                ////////////////////////////cycleId
                if (StringUtils.isEmpty(cycleId)) {
                    errRefClassRoom.setFailReason("所填写的教学周期不存在");
                    errorRefClassRoomList.add(errRefClassRoom);
                    continue;
                } else {
                    refClassRoom.setCycleId(cycleId);
                }

                ////////////////gradeClassId
                String xdId = (String) classSectionMap.get(iocRefClassRoomView.getXdName() + schoolId);
                if (StringUtils.isEmpty(xdId)) {
                    errRefClassRoom.setFailReason("学段信息不存在");
                    errorRefClassRoomList.add(errRefClassRoom);
                    continue;
                }
                GradeClass gradeClass = (GradeClass) classMap.get(iocRefClassRoomView.getBj() + ConstantUtil.getKeyByValueAndFlag(iocRefClassRoomView.getNj(), "nj") + xdId + schoolId);
                //拿到对应的classId
                if (gradeClass == null) {
                    errRefClassRoom.setFailReason("班级信息不存在");
                    errorRefClassRoomList.add(errRefClassRoom);
                    continue;
                }
                if (gradeClass.getXd().equals(xdId)) {
                    if (gradeClass.getNj() == ConstantUtil.getKeyByValueAndFlag(iocRefClassRoomView.getNj(), "nj")) {
                        if (gradeClass.getName().equals(iocRefClassRoomView.getBj())) {
                            gradeClassId = gradeClass.getId();
                        }
                    }
                }

                if (StringUtils.isEmpty(gradeClassId)) {
                    errRefClassRoom.setFailReason("班级信息不存在");
                    errorRefClassRoomList.add(errRefClassRoom);
                    continue;
                }
                refClassRoom.setGradeClass(gradeClassId);

                //根据校区的名字和schoolId可以拿到
                /////////////////////////schoolTypeId
                String schoolTypeName = iocRefClassRoomView.getXiaoQu();
                String schoolTypeId = null;
                if (null != schoolTypeMap.get(schoolTypeName + schoolId)) {
                    schoolTypeId = schoolTypeMap.get(schoolTypeName + schoolId).toString();
                }

                if (StringUtils.isEmpty(schoolTypeId)) {
                    errRefClassRoom.setFailReason("校区信息不存在");
                    errorRefClassRoomList.add(errRefClassRoom);
                    continue;
                }
                refClassRoom.setSchoolTypeId(schoolTypeId);
                //根据schoolId、校区名称、所在教学楼楼号、教室号可以唯一确定roomId
                String classRoomNum = iocRefClassRoomView.getClassRoomNum();
                String teachBuilding = iocRefClassRoomView.getTeachBuilding();

                /////////////////////roomId
                String roomId = "";
                ClassRoom classRoom = (ClassRoom) classRoomMap.get((schoolTypeId + teachBuilding + iocRefClassRoomView.getFloor() + classRoomNum).replace(" ", ""));

                if (classRoom != null) {
                    roomId = classRoom.getId();
                }
                if (StringUtils.isEmpty(roomId)) {
                    errRefClassRoom.setClassRoomNum(classRoomNum);
                    errRefClassRoom.setFailReason("该教学周内教室信息不存在");
                    errorRefClassRoomList.add(errRefClassRoom);
                    continue;
                } else {
                    refClassRoom.setRoomId(roomId);
                }

                //查看是否已经导入
                if (null != refClassRoomMap.get(cycleId + gradeClassId + roomId)) {
                    errRefClassRoom.setFailReason("信息已经存在,请勿重复导入");
                    errorRefClassRoomList.add(errRefClassRoom);
                    continue;
                } else {
                    refClassRoom.setId(PrimaryKey.get());
                    correctRefClassRoomList.add(refClassRoom);
                }
            } catch (Exception e) {
                errorRefClassRoomList.add(errRefClassRoom);
                e.printStackTrace();
                continue;
            }
        }
        if (correctRefClassRoomList.size() > 0)
            teachTaskService.batchInsertRefClassRoom(correctRefClassRoomList);
        Long end = System.currentTimeMillis();
        Map res = new HashMap();
        res.put("msg", "导入完成，共" + correctRefClassRoomList.size() + "条成功，" + errorRefClassRoomList.size() + "条失败,耗时" + (end - begin) / 1000 + "秒");
        res.put("errorList", errorRefClassRoomList);
        return new ResponseEntity(res, HttpStatus.OK);
    }


    //班级教室编辑的弹窗
    @RequestMapping(value = "/ref/class/room/edit/pop", method = RequestMethod.GET)
    public String refClassRoomEditPop(HttpServletRequest request, Model model) {
        String refId = getParamVal(request, "refId");
        //根据refId 查询出schoolTypeId roomId
        RefClassRoom refClassRoom = teachTaskService.findRefClassRoomByPrimarykey(refId);
        GradeClass gradeClass = new GradeClass();
        ClassSection classSection = new ClassSection();

        if (refClassRoom != null) {
            String classId = refClassRoom.getGradeClass();
            gradeClass = classService.selectClassById(classId);
            if (gradeClass != null) {
                String sectionId = gradeClass.getXd();
                if (sectionId != null) {
                    classSection = teachTaskService.findGradeClassBySecctionByPrimarykey(sectionId);
                }
            }
            //根据refId 拿到roomId  那单roomId
            ClassRoom classRoom = teachTaskService.findClassRoomByPrimarykey(refClassRoom.getRoomId());
            model.addAttribute("classRoom", classRoom);

            String schoolTypeId = refClassRoom.getSchoolTypeId();
            //查询所有的校区
            List<SchoolType> schoolTypeList = schoolService.selectSchoolTypeBySchoolId(getLoginUser().getSchoolId());
            model.addAttribute("schoolTypeList", schoolTypeList);
            //查询所有的校区
            //根据schoolTypeId查询所有的教学楼 并根据教学楼分组
//            List<ClassRoom> buildingList = teachTaskService.findBuildingByschoolTypeId(schoolTypeId);
            //根据

            //根据schoolTypeId building查询教室号
//            List<ClassRoom> rooms = teachTaskService.findRooomsBySchoolTypeIdAndBuilding(schoolTypeId, classRoom.getTeachBuilding());
        }

        model.addAttribute("option", "edit");
        model.addAttribute("classSection", classSection);
        model.addAttribute("gradeClass", gradeClass);
        model.addAttribute("refClassRoom", refClassRoom);
        return "teachTask/pop/refClassRoomEditPop";
    }

    @ResponseBody
    @RequestMapping(value = "/ref/class/room/edit/selce/change", method = RequestMethod.GET)
    public Map refClassRoomEditSelectChange(HttpServletRequest request) throws UnsupportedEncodingException {
        String refId = getParamVal(request, "refId");
        String schoolTypeId = getParamVal(request, "schoolTypeId");
        String building = URLDecoder.decode(getParamVal(request, "teachBuilding"), "UTF-8");
        String roomId = getParamVal(request, "roomId");
        String floor = getParamVal(request, "floor");
        String flag = getParamVal(request, "flag");
        //根据refId查询
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        Map<String, Object> map = new HashedMap();
        List<ClassRoom> classRooms = teachTaskService.findCascadeClassRoom(flag, schoolTypeId, building, floor, schoolId);
        map.put("flag", flag);
        map.put("classRooms", classRooms);
        return map;
    }


    //班级教室编辑
    @RequestMapping(value = "/ref/class/room/edit", method = RequestMethod.POST)
    public String refClassRoomEdit(HttpServletRequest request, Model model) {
        String refId = getParamVal(request, "refId");
        String schoolTypeId = getParamVal(request, "schoolTypeId");
        String roomId = getParamVal(request, "roomId");
        //根据refId查询
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        //根据refId查询该班级教室信息
        RefClassRoom refClassRoom = new RefClassRoom();
        refClassRoom.setRoomId(roomId);
        refClassRoom.setId(refId);
        refClassRoom.setSchoolTypeId(schoolTypeId);
        int succ = teachTaskService.updateRefClassRoomByKey(refClassRoom);
        return "teachTask/refClassRoom";

    }

    //下载导入失败的classRoom列表
    @RequestMapping(value = "/ref/class/room/error/export", method = RequestMethod.POST)
    public void errorRefClassRoom(HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileName = "班级教室错误信息列表.xlsx";
            String anno = "注释：橙色字段为必填项\n" +
                    "          1.校区一栏填写:主校区、西校区，按此规则填写;教室类型一栏请填写存在的类型\n" +
                    "          2.楼层、容纳人数、有效座位数、考试座位数、房间号请填写整数\n" +
                    "          3.是否用于选课请填写是或者否 \n";
            String msg = getParamVal(request, "msg");
            JsonArray jsonArray = new JsonParser().parse(msg).getAsJsonArray();
            List<IOCRefClassRoomViewError> exportFile = new ArrayList<IOCRefClassRoomViewError>();
            for (JsonElement jsonElement : jsonArray) {
                IOCRefClassRoomViewError importBundling = GsonUtil.fromJson(jsonElement.getAsJsonObject(), IOCRefClassRoomViewError.class);
                exportFile.add(importBundling);
            }
            new ExportExcel(false, "班级教室错误信息列表", IOCRefClassRoomViewError.class, 2, anno, 1).setDataList(exportFile).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 教务管理-科目课时管理
     *
     * @param request
     * @param model
     * @return
     */

    @RequestMapping(value = "/course/hour", method = RequestMethod.GET)
    public String courseHour(HttpServletRequest request, Model model) {
        String cycleYear = getParamVal(request, "cycleYear");
        String cycleSemester = getParamVal(request, "cycleSemester");
        String courseId = getParamVal(request, "courseId");
        String courseLL = getParamVal(request, "courseLL");
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        //根据学年学期获得周期
        TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), user.getSchoolId());

        //获得周期列表，补全两个下拉框
        List<TeachCycle> cycleList = teachTaskService.getCycleList(user.getSchoolId());
        if (GukeerStringUtil.isNullOrEmpty(teachCycle.getId()))
            if (cycleList.size() > 0)
                teachCycle = cycleList.get(0);

        List<TeachCycle> semesterList = new ArrayList<>();
        if (StringUtil.isEmpty(cycleYear))
            cycleYear = teachCycle.getCycleYear();

        Map yearMap = new LinkedHashMap();
        for (TeachCycle cycle : cycleList) {
            if (cycleYear.equals(cycle.getCycleYear()))
                semesterList.add(cycle);
            yearMap.put(cycle.getCycleYear(), cycle);
        }

        String cycleId = teachCycle.getId();
        //根据cycleId查询所有课程
        List<Course> courseList = teachTaskService.findAllCourseBySchoolIdAndCycleId(schoolId, cycleId);
        if (StringUtils.isEmpty(courseId)) {
            if (courseList.size() > 0)
                courseId = courseList.get(0).getId();
        }

        //cycleId、courseId都有了 查询
        List<CourseClassView> courseClassViewList = new ArrayList<>();
        if (StringUtils.isNotEmpty(courseId))
            courseClassViewList = teachTaskService.findRefCourseClassByCycleIdCourseId(cycleId, courseId);
        List<Integer> hours = new ArrayList<>();
        if (courseClassViewList.size() > 0) {
            for (CourseClassView courseClassView : courseClassViewList) {
                if (courseClassView.getCourseHour() != null) {
                    hours.add(courseClassView.getCourseHour());
                }
            }

        }
        if (hours.size() > 0) {
            model.addAttribute("save", 1);
        } else {
            model.addAttribute("save", 0);
        }

        model.addAttribute("courseLL", courseLL);
        model.addAttribute("yearList", yearMap.keySet());
        model.addAttribute("semesterList", semesterList);
        model.addAttribute("cycleYear", cycleYear);
        model.addAttribute("cycleSemester", cycleSemester);
        model.addAttribute("courseList", courseList);
        model.addAttribute("courseId", courseId);
        model.addAttribute("courseClassViewList", courseClassViewList);

        return "teachTask/courseHour";

    }


    @RequestMapping(value = "/course/hour/edit", method = RequestMethod.POST)
    public String courseHourEdit(HttpServletRequest request, Model model) {
        String sectionIdAndCourseHour = getParamVal(request, "sectionIdAndCourseHour");
        String cycleYear = getParamVal(request, "cycleYear");
        String cycleSemester = getParamVal(request, "cycleSemester");
        String courseId = getParamVal(request, "courseId");
        String courseLL = getParamVal(request, "courseLL");
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        //根据学年学期获得周期
        TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), user.getSchoolId());
        String cycleId = "";
        if (teachCycle != null) {
            cycleId = teachCycle.getId();
        }
        String[] sectionIdArray = sectionIdAndCourseHour.split(",");
        List<String> sectionIdList = new ArrayList<>();
        List<CourseClassHourView> a_courseClassHourArrayList = new ArrayList<>();
        Map map = new HashedMap();
        if (sectionIdArray.length > 0) {
            for (int i = 1; i < sectionIdArray.length; i++) {

                //该数组中的第一个元素代表sectionId,第二个元素代表nj,第三个元素代表课时数
                String[] arr = sectionIdArray[i].split(":");
                for (int j = 0; j < arr.length; j++) {
                    CourseClassHourView a_courseClassHour = new CourseClassHourView();
                    a_courseClassHour.setSectionId(arr[0]);
                    a_courseClassHour.setNj(NumberConvertUtil.convertS2I(arr[1]));
                    a_courseClassHour.setCourseHour(NumberConvertUtil.convertS2I(arr[2]));
                    a_courseClassHourArrayList.add(a_courseClassHour);
                }
            }
        }

        //根据courseId获取所有的classId
        List<CourseClass> courseClasses = teachTaskService.findAllCourseClassByCourseId(courseId);

        List<CourseClass> updateForCourseClass = new ArrayList<>();
        //根据a_courseClassHourArrayList里面的sectionId和nj去查所有的班级
        List<GradeClass> gradeClassList = teachTaskService.findGradeClassBySectionIdAndNj(a_courseClassHourArrayList);
        for (GradeClass gradeClass : gradeClassList) {
            for (CourseClassHourView a_courseClassHour : a_courseClassHourArrayList) {
                if (a_courseClassHour.getSectionId().equals(gradeClass.getXd()) && a_courseClassHour.getNj() == gradeClass.getNj()) {
                    for (CourseClass courseClass : courseClasses) {
                        if (courseClass.getClassId().equals(gradeClass.getId())) {
                            //这会就可以根据classId courseId唯一确定CourseClass对象了
                            CourseClass courseClassDB = teachTaskService.findCourseClassByClassIdAndCourseId(courseId, gradeClass.getId());
                            courseClassDB.setCourseHour(a_courseClassHour.getCourseHour());
                            updateForCourseClass.add(courseClassDB);
                        }
                    }
                }
            }
        }
        if (updateForCourseClass.size() > 0) {
            for (CourseClass courseClass : updateForCourseClass) {
                teachTaskService.updateCourseClassByPrimareyKey(courseClass);
            }
        }

//        teachTaskService.updateCourseClassByList(updateForCourseClass);
        return "redirect:/teach/task/course/hour";
    }


    /**
     * 班级日常课时管理
     */

//首页
    @RequestMapping(value = "/daily/hour", method = RequestMethod.GET)
    public String dailyHour(HttpServletRequest request, Model model) {
        String cycleYear = getParamVal(request, "cycleYear");
        String cycleSemester = getParamVal(request, "cycleSemester");
        String xdId = getParamVal(request, "sectionId");
        String nj = getParamVal(request, "nj");
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        User user = getLoginUser();
        String schoolId = user.getSchoolId();

        //根据学年学期获得周期
        TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), user.getSchoolId());

        //查询所有的教学周期
        List<TeachCycle> cycleList = teachTaskService.getCycleList(user.getSchoolId());

        List<TeachCycle> semesterList = new ArrayList<>();
        if (GukeerStringUtil.isNullOrEmpty(teachCycle.getId())) {
            if (cycleList.size() > 0) {
                teachCycle = cycleList.get(0);
            }
        }
        Map yearMap = new LinkedHashMap();
        for (TeachCycle cycle : cycleList) {
            if (teachCycle.getCycleYear().equals(cycle.getCycleYear()))
                semesterList.add(cycle);
            yearMap.put(cycle.getCycleYear(), cycle);
        }

        //根据学校id查询学段的名字
        List<ClassSection> classSectionList = classService.getAllClassSectionBySchoolId(schoolId);
        if (xdId == "") {
            if (classSectionList.size() > 0) {
                xdId = classSectionList.get(0).getId();
            }
        }
        String cycleId = teachCycle.getId();
        if (StringUtil.isEmpty(cycleYear)) {
            cycleYear = teachCycle.getCycleYear();
        }
        if (StringUtils.isEmpty(nj) || nj.equals("null")) {
            nj = "1";
        }

        //根据xd、cycleId、nj查询所有的班级日常课时信息
        PageInfo<DailyHourView> pageInfo = new PageInfo<>();
        if (StringUtils.isNotEmpty(cycleId))
            pageInfo = teachTaskService.findDailyHourByXdAndCycleIdAndNj(schoolId, xdId, cycleId, nj, pageNum, pageSize);
        model.addAttribute("xdId", xdId);
        model.addAttribute("cycleList", cycleList);
        model.addAttribute("yearList", yearMap.keySet());
        model.addAttribute("classSectionList", classSectionList);
        model.addAttribute("cycleSemester", cycleSemester);
        model.addAttribute("semesterList", semesterList);
        model.addAttribute("nj", nj);
        model.addAttribute("cycleYear", cycleYear);
        model.addAttribute("pageInfo", pageInfo);

        model.addAttribute("cycle", teachCycle);
        return "teachTask/dailyHour";

    }

    //班级日常课时的弹窗
    @RequestMapping(value = "/daily/hour/add/pop", method = RequestMethod.GET)
    public String dailyHourAddPop(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        String dailyId = getParamVal(request, "dailyId");
        String nj = getParamVal(request, "nj");
        String xdId = getParamVal(request, "xdId");

        String cycleYear = getParamVal(request, "cycleYear");
        String cycleSemester = getParamVal(request, "cycleSemester");
        //根据上述条件查询cycleId的值
        TeachCycle teachCycle = null;
        List<TeachCycle> cycleList = teachTaskService.getCycleList(user.getSchoolId());
        if (cycleList.size() > 0)
            teachCycle = cycleList.get(0);
        List<TeachCycle> semesterList = new ArrayList<>();
        if (StringUtil.isEmpty(cycleYear))
            cycleYear = teachCycle.getCycleYear();
        Map yearMap = new LinkedHashMap();
        for (TeachCycle cycle : cycleList) {
            if (cycleYear.equals(cycle.getCycleYear()))
                semesterList.add(cycle);
            yearMap.put(cycle.getCycleYear(), cycle);
        }
        //查询所有的班级
        List<GradeClass> gradeClassList = teachTaskService.getAllClassBySchoolIdAndNj(schoolId, nj, xdId);
        DailyHour dailyHour = null;
        GradeClass gradeClass=null;
        if (StringUtils.isNotEmpty(dailyId)) {
            dailyHour = teachTaskService.findDailyHourById(dailyId);
            String gradeClassId = dailyHour.getGradeClassId();
            gradeClass = classService.selectClassById(gradeClassId);
        }

        model.addAttribute("cycle", teachCycle);
        model.addAttribute("cycleYear", cycleYear);
        model.addAttribute("cycleSemester", cycleSemester);
        model.addAttribute("gradeClassOne", gradeClass);
        model.addAttribute("gradeClassList", gradeClassList);
        model.addAttribute("yearList", yearMap.keySet());
        model.addAttribute("dailyHour", dailyHour);
        return "teachTask//pop/dailyHourAdd";
    }


    //班级日常课时的增加
    @ResponseBody
    @RequestMapping(value = "/daily/hour/add", method = RequestMethod.POST)
    public ResultEntity dailyHourAdd(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();


        String cycleYear = getParamVal(request, "cycleYear");
        String cycleSemester = getParamVal(request, "cycleSemester");
        TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), schoolId);
        String cycleId = null;

        if (teachCycle.getId() != null) {
            cycleId = teachCycle.getId();
        } else {
            teachCycle = getLatestTeachCycle(schoolId);
            cycleId = teachCycle.getId();
        }

        String skts = getParamVal(request, "skts");
        String swks = getParamVal(request, "swks");
        String xwks = getParamVal(request, "xwks");
        String kjc = getParamVal(request, "kjc");
        String classIds = getParamVal(request, "classIds");
        if (skts == "" || kjc == "" || swks == "" || xwks == "" || classIds == "" || cycleSemester == "") {
            return ResultEntity.newErrEntity("所有项均为必填项");
        }
        String reg = "^[0-9]*[1-9][0-9]*$";
        if (!skts.matches(reg) || !kjc.matches(reg) || !swks.matches(reg) || !xwks.matches(reg)) {
            return ResultEntity.newErrEntity("数据格式不正确");
        }
        List<DailyHour> dailyHourList = new ArrayList<>();
        String[] classIdArr = classIds.split(",");
        if (classIdArr.length > 0) {
            for (int i = 1; i < classIdArr.length; i++) {
                DailyHour dailyHour = new DailyHour();
                dailyHour.setId(PrimaryKey.get());
                dailyHour.setSkts(NumberConvertUtil.convertS2I(skts));
                dailyHour.setSwks(NumberConvertUtil.convertS2I(swks));
                dailyHour.setXwks(NumberConvertUtil.convertS2I(xwks));
                dailyHour.setKjc(NumberConvertUtil.convertS2I(kjc));
                dailyHour.setCycleId(cycleId);
                dailyHour.setCreateTime(System.currentTimeMillis());
                dailyHour.setUpdateTime(System.currentTimeMillis());
                dailyHour.setUpdateBy(user.getId());
                dailyHour.setGradeClassId(classIdArr[i]);
                dailyHour.setDelFlag(0);
                dailyHour.setXn(cycleYear);
                dailyHour.setXq(cycleSemester);
                dailyHour.setSchoolId(schoolId);
                dailyHourList.add(dailyHour);
            }
        }
        //查询所有的dailyHour,看是否已经创建过一次了
        if (dailyHourList != null && dailyHourList.size() > 0)
            teachTaskService.batchInsertDailyHour(dailyHourList);
        return ResultEntity.newResultEntity("");
    }


    //班级日常课时编辑
    @ResponseBody
    @RequestMapping(value = "/daily/hour/edit", method = RequestMethod.POST)
    public ResultEntity dailyHourEdit(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        String dailyId = getParamVal(request, "dailyId");
        String cycleYear = getParamVal(request, "cycleYear");
        String cycleSemester = getParamVal(request, "cycleSemester");
        TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), schoolId);
        String cycleId = null;
        if (teachCycle != null) {
            cycleId = teachCycle.getId();
        }
        DailyHour dailyHour = new DailyHour();
        if (dailyId != "") {
            dailyHour.setId(dailyId);
        }
        String skts = getParamVal(request, "skts");
        String swks = getParamVal(request, "swks");
        String xwks = getParamVal(request, "xwks");
        String kjc = getParamVal(request, "kjc");
        dailyHour.setXq(cycleSemester);
        if (skts == "" || kjc == "" || swks == "" || xwks == "" || cycleSemester == "") {
            return ResultEntity.newErrEntity("所有项均为必填项");
        }
        dailyHour.setSkts(NumberConvertUtil.convertS2I(skts));
        dailyHour.setSwks(NumberConvertUtil.convertS2I(swks));
        dailyHour.setXwks(NumberConvertUtil.convertS2I(xwks));
        dailyHour.setKjc(NumberConvertUtil.convertS2I(kjc));
        dailyHour.setCycleId(cycleId);
        dailyHour.setUpdateTime(System.currentTimeMillis());
        dailyHour.setUpdateBy(user.getId());
        dailyHour.setDelFlag(0);
        dailyHour.setXn(cycleYear);
        dailyHour.setXq(cycleSemester);
        teachTaskService.saveDailyHour(dailyHour);
        return ResultEntity.newResultEntity("");
    }

    @ResponseBody
    @RequestMapping(value = "/daily/hour/del", method = RequestMethod.POST)
    public ResultEntity dailyHourDel(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        String dailyId = getParamVal(request, "dailyId");
        teachTaskService.delDailyHourById(dailyId);
        return ResultEntity.newResultEntity("");
    }

    /**
     * 课节设置
     */
//首页
    @RequestMapping(value = "/node", method = RequestMethod.GET)
    public String nodeIndex(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        Integer pageNum = getPageNum(request);
        Integer pageSize = getPageSize(request);
        String cycleYear = getParamVal(request, "cycleYear");
        String cycleSemester = getParamVal(request, "cycleSemester");

        //根据学年学期获得周期
        TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), user.getSchoolId());

        //查询所有的教学周期
        List<TeachCycle> cycleList = teachTaskService.getCycleList(user.getSchoolId());

        List<TeachCycle> semesterList = new ArrayList<>();
        if (teachCycle.getId() == null) {
            teachCycle = cycleList.get(0);
        }
        if (StringUtil.isEmpty(cycleYear)) {
            cycleYear = teachCycle.getCycleYear();
        }

        Map yearMap = new LinkedHashMap();
        for (TeachCycle cycle : cycleList) {
            if (teachCycle.getCycleYear().equals(cycle.getCycleYear()))
                semesterList.add(cycle);
            yearMap.put(cycle.getCycleYear(), cycle);
        }
        String cycleId = teachCycle.getId();
        //根据schoolId查询时间表
        PageInfo<CourseNodeInit> pageInfo = new PageInfo<>();
        if (StringUtils.isNotEmpty(cycleId))
            pageInfo = teachTaskService.findCourseNodeInitBySchoolId(schoolId, pageNum, pageSize, cycleId);
        model.addAttribute("cycleYear", cycleYear);
        model.addAttribute("cycleId", cycleId);
        model.addAttribute("cycleSemester", cycleSemester);
        model.addAttribute("yearList", yearMap.keySet());
        model.addAttribute("semesterList", semesterList);
        model.addAttribute("pageInfo", pageInfo);
        return "teachTask/node";
    }


    @RequestMapping(value = "/node/detail/pop")
    public String nodeDetail(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        String nodeInitId = getParamVal(request, "nodeId");
        //根据nodeId查询所有的courseNode

        List<CourseNode> courseNodeList = teachTaskService.findCourseNodeByNodeId(nodeInitId);
        List<CourseNodeView> courseNodeViewList = new ArrayList<>();
        if (courseNodeList.size() > 0) {
            for (CourseNode courseNode : courseNodeList) {
                CourseNodeView courseNodeView = new CourseNodeView();
                courseNodeView.setCourseNode(courseNode);
                Long start = courseNode.getStartTime();
                Long end = courseNode.getEndTime();
                String _start = null;
                String _end = null;
                try {
                    _start = longToString(start, "HH:mm");
                    _end = longToString(end, "HH:mm");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                courseNodeView.setStartTime(_start);
                courseNodeView.setEndTime(_end);
                courseNodeViewList.add(courseNodeView);
            }
        }
        model.addAttribute("courseNodeViewList", courseNodeViewList);
        return "teachTask/pop/nodeDetail";
    }

    @RequestMapping(value = "/node/del")
    public String nodeDel(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        String nodeId = getParamVal(request, "nodeId");
        //根据nodeId查询所有的courseNode
        //根据id删除 不做逻辑删除，直接干掉了
        teachTaskService.delCourseNodeInit(nodeId);
        //再根据nodeId，删除courseNode表中的所有数据，也是直接干掉，不做逻辑删除
//        teachTaskService.delCourseNodeByNodeInitId(nodeId);
        return "teachTask/node";
    }


    public CourseNode courseNode(Integer node, Long start, Long end, String schoolId, User user, String courseInitId, String nodeName, String morningAfternoon) {
        CourseNode courseNode = new CourseNode();
        courseNode.setNode(node);//node
        courseNode.setStartTime(start);
        courseNode.setEndTime(end);
        courseNode.setSchoolId(schoolId);
        courseNode.setCreateTime(System.currentTimeMillis());
        courseNode.setUpdateTime(System.currentTimeMillis());
        courseNode.setUpdateBy(user.getId());
        courseNode.setId(PrimaryKey.get());
        courseNode.setDelFlag(0);
        courseNode.setCourseNodeInitId(courseInitId);
        courseNode.setMorningAfternoon(morningAfternoon);
        courseNode.setNodeName(nodeName);
        return courseNode;
    }

    /**
     * 教室类型管理
     */
//   教室类型管理的弹窗
    @RequestMapping(value = "/room/type/pop", method = RequestMethod.GET)
    public String roomTypePop(HttpServletRequest request, Model model) {
        User user = getLoginUser();

        List<RoomType> roomTypeList = teachTaskService.roomTypeList(user.getSchoolId());

        model.addAttribute("roomTypeList", roomTypeList);
        return "teachTask/pop/roomType";
    }

    //教室类型  添加 删除  更新
    @RequestMapping(value = "/room/type/add", method = RequestMethod.POST)
    public String roomTypeAdd(HttpServletRequest request, Model model, RoomType roomType) {
        User user = getLoginUser();
        String roomTypeId = getParamVal(request, "id");
        String doType = getParamVal(request, "type");
        if (StringUtils.isNotEmpty(doType) && doType.equals("delete")) {
            roomType.setName("");
            roomType.setDelFlag(1);//隐藏
        }
        int succ = teachTaskService.saveRoomType(roomType, user);
//        return "teachTask/pop/roomType";
        return "redirect:/teach/task/room/type/pop";
    }

    //新增课节 管理
    @RequestMapping(value = "/node/new", method = RequestMethod.GET)
    public String nodeIndexNew(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        Integer pageNum = getPageNum(request);
        Integer pageSize = getPageSize(request);
        String cycleYear = getParamVal(request, "cycleYear");
        String cycleSemester = getParamVal(request, "cycleSemester");

        //根据学年学期获得周期
        TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), user.getSchoolId());

        //查询所有的教学周期
        List<TeachCycle> cycleList = teachTaskService.getCycleList(user.getSchoolId());

        List<TeachCycle> semesterList = new ArrayList<>();
        if (GukeerStringUtil.isNullOrEmpty(teachCycle.getId())) {
            if (cycleList.size() > 0) {
                teachCycle = cycleList.get(0);
            }
        }
        if (StringUtil.isEmpty(cycleYear)) {
            cycleYear = teachCycle.getCycleYear();
        }


        Map yearMap = new LinkedHashMap();
        for (TeachCycle cycle : cycleList) {
            if (teachCycle.getCycleYear().equals(cycle.getCycleYear()))
                semesterList.add(cycle);
            yearMap.put(cycle.getCycleYear(), cycle);
        }

        String cycleId = "";
        if (teachCycle != null) {
            cycleId = teachCycle.getId();
        }

        //根据schoolId查询时间表
        PageInfo<CourseNodeInit> pageInfo = new PageInfo<>();
        if (StringUtils.isNotEmpty(cycleId)) {
            pageInfo = teachTaskService.findCourseNodeInitBySchoolId(schoolId, pageNum, pageSize, cycleId);
        }
        model.addAttribute("cycleId", cycleId);
        model.addAttribute("cycleYear", cycleYear);
        model.addAttribute("cycleSemester", cycleSemester);
        model.addAttribute("yearList", yearMap.keySet());
        model.addAttribute("semesterList", semesterList);
        model.addAttribute("pageInfo", pageInfo);
        return "teachTask/nodeNew";
    }

    //新增课节的增加的弹窗
    @RequestMapping(value = "/node/add/new/pop", method = RequestMethod.GET)
    public String newCourseNodeAddPop(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String cycleId = getParamVal(request, "cycleId");
        TeachCycle teachCycle = new TeachCycle();
        if (StringUtils.isEmpty(cycleId)) {
            teachCycle = getLatestTeachCycle(user.getSchoolId());
        } else {
            teachCycle = teachTaskService.findTeachCycleByPrimarykey(cycleId);
        }

        String nodeInitId = getParamVal(request, "nodeInitId");
        CourseNodeInit courseNodeInit = new CourseNodeInit();
        if (StringUtils.isNotEmpty(nodeInitId)) {
            courseNodeInit = teachTaskService.findCourseNodeInitById(nodeInitId);
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("courseNodeInit", courseNodeInit);

        //根据courseNodeInitId查询所有的courseNode
        List<CourseNode> courseNodeList = new ArrayList<>();
        if (courseNodeInit.getId() != null) {
            courseNodeList = teachTaskService.findCourseNodeByNodeId(courseNodeInit.getId());
        }

        List<CourseNodeExtention> courseNodeExtentions = new ArrayList<>();
        if (courseNodeList.size() > 0) {
            for (CourseNode courseNode : courseNodeList) {
                CourseNodeExtention courseNodeExtention = new CourseNodeExtention();
                courseNodeExtention.setId(courseNode.getId());
                courseNodeExtention.setNodeName(courseNode.getNodeName());
                courseNodeExtention.setCycleId(courseNode.getCycleId());
                try {
                    courseNodeExtention.setStartTime(longToString(courseNode.getStartTime(), "HH:mm"));
                    courseNodeExtention.setEndTime(longToString(courseNode.getEndTime(), "HH:mm"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                courseNodeExtentions.add(courseNodeExtention);
            }
        }


        Long endweek = 0L;
        if (teachCycle.getId() != null) {
            endweek = teachCycle.getEndDate();
            cycleId = teachCycle.getId();
            //根据cycleId查询node_init表 做时间比较
            List<CourseNodeInit> courseNodeInitList = teachTaskService.findCourseNodeInitByCycleId(cycleId);
            if (courseNodeInitList.size() > 1) {
                model.addAttribute("courseNodeInitEndWeek", courseNodeInitList.get(1).getEndWeek());
            } else {
                model.addAttribute("courseNodeInitEndWeek", 0);
            }
        }

        model.addAttribute("teachCycle", teachCycle);
        model.addAttribute("courseNodeExtentions", courseNodeExtentions);
        model.addAttribute("courseNodeListSize", courseNodeList.size());
        model.addAttribute("cycleId", cycleId);
        model.addAttribute("endweek", endweek);
        return "teachTask/pop/newNodeAddPop";
    }

    //新增课节 增加的弹窗
    @RequestMapping(value = "/node/add/new", method = RequestMethod.POST)
    public String newCourseNodeAdd(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String arr = getParamVal(request, "arr");
        String nodeInitId = getParamVal(request, "nodeInitId");

        Gson gson = new Gson();
        List<CourseNodeExtention> courseNodeExtentions = gson.fromJson(arr, new TypeToken<List<CourseNodeExtention>>() {
        }.getType());


        String cycleId = getParamVal(request, "cycleId");
        String startweek = getParamVal(request, "startweek");
        String endweek = getParamVal(request, "endweek");
        String initName = getParamVal(request, "initName");

        CourseNodeInit courseNodeInit = new CourseNodeInit();
        if (nodeInitId != "" && !nodeInitId.equals("null")) {
            courseNodeInit.setId(nodeInitId);
        } else {
            courseNodeInit.setId(PrimaryKey.get());
        }
        courseNodeInit.setCycleId(cycleId);
        courseNodeInit.setName(initName);
        courseNodeInit.setStartWeek(NumberConvertUtil.convertS2I(startweek));
        courseNodeInit.setEndWeek(NumberConvertUtil.convertS2I(endweek));
        courseNodeInit.setSchoolId(user.getSchoolId());
        courseNodeInit.setUpdateBy(user.getId());

        List<CourseNode> courseNodeList = new ArrayList<>();
        if (courseNodeExtentions != null && courseNodeExtentions.size() > 0) {
            Long time = 0L;
            for (CourseNodeExtention courseNodeExtention : courseNodeExtentions) {
                time++;
                try {
                    Long start = stringToLong(courseNodeExtention.getStartTime(), "HH:mm");
                    Long end = stringToLong(courseNodeExtention.getEndTime(), "HH:mm");

                    CourseNode courseNode = new CourseNode();
                    courseNode.setId(PrimaryKey.get());
                    courseNode.setCourseNodeInitId(courseNodeInit.getId());
                    courseNode.setCycleId(cycleId);
                    courseNode.setUpdateBy(user.getId());
                    courseNode.setSchoolId(user.getSchoolId());
                    courseNode.setCreateTime(System.currentTimeMillis() + time);
                    courseNode.setDelFlag(0);
                    courseNode.setNode(courseNodeExtention.getNode());
                    courseNode.setEndTime(end);
                    courseNode.setStartTime(start);
                    courseNode.setNodeName(courseNodeExtention.getNodeName());

                    courseNodeList.add(courseNode);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        if (courseNodeInit != null && courseNodeList != null && courseNodeList.size() > 0) {
            teachTaskService.saveCourseNodeInit(courseNodeInit, courseNodeList);
        }

        return "teachTask/nodeNew";
    }

    @RequestMapping(value = "/choose/deputy/show")
    public String choosePerson(HttpServletRequest request, Model model) {
        String choosesId = getParamVal(request, "chooseIds");
        model.addAttribute("choosesId", choosesId);
        return "teachTask/chooseDeputy";
    }


    @RequestMapping(value = "/table", method = RequestMethod.GET)
    public String table(HttpServletRequest request, Model model) throws ParseException, UnsupportedEncodingException {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        String cycleYear = getParamVal(request, "cycleYear");
        String cycleSemester = getParamVal(request, "cycleSemester");
        String nj = getParamVal(request, "nj");
        String gradeClassId = getParamVal(request, "gradeClass");
        String sectionName = URLDecoder.decode(getParamVal(request, "sectionName"), "UTF-8");
        String sectionId = getParamVal(request, "sectionId");
        String week = getParamVal(request, "week");

        List<ClassSection> sectionList = classService.getAllClassSectionBySchoolId(schoolId);
        //根据学年学期获得周期
        TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), user.getSchoolId());
        //获得周期列表，补全两个下拉框
        List<TeachCycle> cycleList = teachTaskService.getCycleList(user.getSchoolId());
        if (GukeerStringUtil.isNullOrEmpty(teachCycle.getId()))
            if (cycleList.size() > 0)
                teachCycle = cycleList.get(0);
        List<TeachCycle> semesterList = new ArrayList<>();
        if (StringUtil.isEmpty(cycleYear))
            cycleYear = teachCycle.getCycleYear();
        Map yearMap = new LinkedHashMap();

        if (cycleList.size() > 0) {
            for (TeachCycle cycle : cycleList) {
                if (cycleYear.equals(cycle.getCycleYear()))
                    semesterList.add(cycle);
                yearMap.put(cycle.getCycleYear(), cycle);
            }
        }

        List<String> njList = new ArrayList<>();
        if (sectionName != "") {
            if (sectionName.equals("小学")) {
                njList.add("一年级");
                njList.add("二年级");
                njList.add("三年级");
                njList.add("四年级");
                njList.add("五年级");
                njList.add("六年级");
            } else {
                njList.add("一年级");
                njList.add("二年级");
                njList.add("三年级");
            }
        }

        List<GradeClass> gradeClassList = new ArrayList<>();
        if (StringUtils.isNotEmpty(sectionId) && StringUtils.isNotEmpty(nj)) {
            //根据年级和学段来查询班级集合
            gradeClassList = teachTaskService.findAllGradeClassByXdAndNj(sectionId, nj);
        }


        Integer totalHour = 0;
        Integer skts = 0;
        DailyHour dailyHour = new DailyHour();
        List<Integer> integers = new ArrayList<>();
        if (StringUtils.isNotEmpty(gradeClassId)) {
            dailyHour = teachTaskService.fingDailyHourByCycleIdAndClassId(teachCycle.getId(), gradeClassId);
            if (dailyHour != null) {
                skts = dailyHour.getSkts();
                totalHour = dailyHour.getSwks() + dailyHour.getXwks();
                for (int i = 0; i < totalHour; i++) {
                    integers.add(i);
                }
            }
        }

        //得到当前系统时间是当年的第几周
        Calendar c = Calendar.getInstance();

        System.out.println(c.get(Calendar.DAY_OF_WEEK) - 1);

        Long termBeginTime = 0L;
        Long currentWeek = 1L;
        if (teachCycle.getId() != null) {
            termBeginTime = teachCycle.getTermBeginTime();
            if (termBeginTime != null) {
                Long currenttime = new Date().getTime();
                if (currenttime > termBeginTime) {
                    //获取今天是星期几
                    Integer weekofoneday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
                    if (weekofoneday == 0) {
                        weekofoneday = 7;
                    }

                    //获取开学日期是星期几
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(termBeginTime);
                    int startweekofoneday = calendar.get(Calendar.DAY_OF_WEEK) - 1;
                    if (startweekofoneday == 0) {
                        startweekofoneday = 7;
                    }

                    Long zhengshu = (currenttime - termBeginTime) / 1000 / 60 / 60 / 24 - (7 - weekofoneday) + 1;
                    System.out.println(zhengshu);
                    if (zhengshu % 7 > 0) {
                        currentWeek = zhengshu / 7 + 2;
                        if (StringUtils.isEmpty(week) || week.equals("null"))
                            week = String.valueOf(currentWeek);
                    } else {
                        currentWeek = zhengshu / 7 + 2;
                        if (StringUtils.isEmpty(week) || week.equals("null"))
                            week = String.valueOf(currentWeek);
                    }
                }
            }
        }

        PageInfo<TeachTableView> pageInfo = teachTaskService.findTeachTableByCurrentCycleWeekAndSchoolId(schoolId, NumberConvertUtil.convertS2I(week), pageNum, pageSize, gradeClassId, integers, teachCycle.getId());
        Map<Integer, List<Map<Integer, TeachTableView>>> mapMap = new LinkedHashMap<>();
        Gson gson = new Gson();
        //数字和字符串星期几之间的转换
        List<TeachTableView> teachTableViews = pageInfo.getList();
        Integer totalWeek = teachCycle.getWeekCount();
        List<Integer> integerList = new ArrayList<>();
        if (totalWeek != null && totalWeek > 0) {
            for (int i = 0; i < totalWeek; i++) {
                integerList.add(i + 1);
            }
        }

        //==============================================================================================================
//        Map<Integer, TeachTableView> map = new HashedMap();
//        Map<String, Map<Integer, TeachTableView>> mapMap = new HashedMap();
//        List<Map<String, TeachTableView>> nodelist = new ArrayList<>();

//        List<TeachTableView> teachTables = pageInfo.getList();
//
//        //总节数
//        List<Integer> totalNodeList = new ArrayList<>();
//        for (int n = 0; n < totalHour; n++) {
//            totalNodeList.add(n);
//        }
//
//        List<Integer> totalExistNodeList = new ArrayList<>();
//        List<TeachTableView> totalExistTeachViewList = new ArrayList<>();
//        for (int m = 0; m < totalExistTeachViewList.size(); m++) {
//            String node = totalExistTeachViewList.get(m).getNode();
//            totalExistNodeList.add(Integer.parseInt(node));
//        }
//
//        //不存在的节数
//        List<Integer> unExist = new ArrayList<Integer>();
//        for (Integer l : totalNodeList) {
//            if (!totalExistNodeList.contains(l)) {
//                unExist.add(l);
//            }
//        }
//
//        if (unExist.size() > 0) {
//            for (Integer oneUnExist : unExist) {
//                map = new TreeMap<>();
//                map.put(oneUnExist, new TeachTableView());
////                teachTableViewMaps.add(map);
//            }
//        }
//
//        //实际存在的节数
//        if (teachTables != null && teachTables.size() > 0) {
//            for (TeachTableView teachTableView : teachTableViews) {
//                for (int oneskts = 1; oneskts <= skts; oneskts++) {
//                    if (oneskts == Integer.parseInt(teachTableView.getWeekDay())) {
//                        totalExistTeachViewList.add(teachTableView);
//                    }
//                }
//            }
//        }
//
//        if (teachTables != null && teachTables.size() > 0) {
//            if (totalHour > 0 && skts > 0) {
//                for (int hour = 0; hour < totalHour; hour++) {
//                    int index = 0;
//                    map = new HashedMap();
//                    for (TeachTableView teachTableView : teachTables) {
//                        if (teachTableView.getNode().equals(String.valueOf(hour))) {
//                            if (teachTableView.getWeekDay().equals("1")) {
//                                map.put(1, teachTableView);
//                            } else if (teachTableView.getWeekDay().equals("2")) {
//                                map.put(2, teachTableView);
//                            } else if (teachTableView.getWeekDay().equals("3")) {
//                                map.put(3, teachTableView);
//                            } else if (teachTableView.getWeekDay().equals("4")) {
//                                map.put(4, teachTableView);
//                            } else {
//                                map.put(5, teachTableView);
//                            }
//                        }
//                        index++;
//                    }
//                    if (index == teachTables.size()) {
//                        mapMap.put(String.valueOf(hour), map);
//                    }
//                }
//            }
//        }

        //==============================================================================================================
        if (dailyHour != null) {
            if (StringUtils.isNotEmpty(gradeClassId)) {

                if (teachTableViews.size() > 0 && teachTableViews != null) {
                    //总节数
                    List<Integer> totalNodeList = new ArrayList<>();
                    for (int n = 0; n < totalHour; n++) {
                        totalNodeList.add(n);
                    }
                    if (dailyHour != null) {
                        skts = dailyHour.getSkts();
                        for (int k = 1; k <= skts; k++) {
                            Map<Integer, TeachTableView> map = null;
                            List<Map<Integer, TeachTableView>> teachTableViewMaps = new ArrayList<>();
                            //实际存在的节数
                            List<Integer> totalExistNodeList = new ArrayList<>();
                            List<TeachTableView> totalExistTeachViewList = new ArrayList<>();
                            for (TeachTableView teachTableView : teachTableViews) {
                                if (k == Integer.parseInt(teachTableView.getWeekDay())) {
                                    totalExistTeachViewList.add(teachTableView);
                                }
                            }
                            for (int m = 0; m < totalExistTeachViewList.size(); m++) {
                                String node = totalExistTeachViewList.get(m).getNode();
                                totalExistNodeList.add(Integer.parseInt(node));
                            }

                            //不存在的节数
                            List<Integer> unExist = new ArrayList<Integer>();
                            for (Integer l : totalNodeList) {
                                if (!totalExistNodeList.contains(l)) {
                                    unExist.add(l);
                                }
                            }

                            if (unExist.size() > 0) {
                                for (Integer oneUnExist : unExist) {
                                    map = new TreeMap<>();
                                    map.put(oneUnExist, new TeachTableView());
                                    teachTableViewMaps.add(map);
                                }
                            }

                            for (int i = 0; i < totalHour; i++) {
                                int index = 1;
                                for (TeachTableView teachTableView : totalExistTeachViewList) {
                                    if (k == Integer.parseInt(teachTableView.getWeekDay())) {
                                        map = new TreeMap<>();
                                        if (i == Integer.parseInt(teachTableView.getNode())) {
                                            map.put(Integer.parseInt(teachTableView.getNode()), teachTableView);
                                            teachTableViewMaps.add(map);
                                        }

                                        if (index == totalExistTeachViewList.size()) {
                                            Collections.sort(teachTableViewMaps, new Comparator<Map<Integer, TeachTableView>>() {
                                                @Override
                                                public int compare(Map<Integer, TeachTableView> o1, Map<Integer, TeachTableView> o2) {
                                                    if (o1.keySet().iterator().next() - o2.keySet().iterator().next() > 0) {
                                                        return 1;
                                                    } else {
                                                        return -1;
                                                    }
                                                }
                                            });
                                            if (k == 1) mapMap.put(1, teachTableViewMaps);
                                            if (k == 2) mapMap.put(2, teachTableViewMaps);
                                            if (k == 3) mapMap.put(3, teachTableViewMaps);
                                            if (k == 4) mapMap.put(4, teachTableViewMaps);
                                            if (k == 5) mapMap.put(5, teachTableViewMaps);
                                        }
                                        index++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        Map<Integer, Map<Integer, TeachTableView>> mapMap1 = new LinkedHashMap<>();
        Map<Integer, TeachTableView> map = new LinkedHashTreeMap<>();
        for (int oneHour = 0; oneHour < totalHour; oneHour++) {
            map = new LinkedHashTreeMap<>();
            int index = 0;
            for (Map.Entry<Integer, List<Map<Integer, TeachTableView>>> entry : mapMap.entrySet()) {
                List<Map<Integer, TeachTableView>> mapList = entry.getValue();
                if (null != mapList && mapList.size() > 0) {
                    for (Map<Integer, TeachTableView> map1 : mapList) {

                        for (Map.Entry<Integer, TeachTableView> entrye : map1.entrySet()) {
                            TeachTableView teachTableView = entrye.getValue();
                            if (teachTableView.getNode() != null) {
                                if (teachTableView.getNode().equals(String.valueOf(oneHour))) {

                                    //一周五天中的每一天的第hour节
                                    for (int onetofive = 1; onetofive <= skts; onetofive++) {
                                        if (teachTableView.getWeekDay().equals(String.valueOf(onetofive))) {
                                            map.put(onetofive, teachTableView);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                index++;
                if (mapMap.size() == index) {
                    mapMap1.put(oneHour, map);
                }
            }

        }


        RefClassRoom refClassRoom = new RefClassRoom();
        if (StringUtils.isNotEmpty(gradeClassId) && StringUtils.isNotEmpty(teachCycle.getId())) {
            refClassRoom = teachTaskService.findRefClassRoomByCycleIdAndGradeClassId(gradeClassId, teachCycle.getId());
        }
        if (refClassRoom != null) {
            model.addAttribute("refClassRoomId", refClassRoom.getId());
        }
        model.addAttribute("teachCycle", teachCycle);
        model.addAttribute("totalHour", totalHour);
        model.addAttribute("weekFetch", week);
        model.addAttribute("integerList", integerList);
        model.addAttribute("skts", skts);
        model.addAttribute("mapMap", JSONObject.parseObject(gson.toJson(mapMap1)).toString());
        model.addAttribute("gradeClassList", gradeClassList);
        model.addAttribute("sectionId", sectionId);
        model.addAttribute("gradeClassId", gradeClassId);
        model.addAttribute("njList", njList);
        model.addAttribute("nj", nj);
        model.addAttribute("cycleYear", cycleYear);
        model.addAttribute("cycleId", teachCycle.getId());
        model.addAttribute("cycleSemester", cycleSemester);
        model.addAttribute("yearList", yearMap.keySet());
        model.addAttribute("semesterList", semesterList);
        model.addAttribute("sectionList", sectionList);
        model.addAttribute("pageInfo", pageInfo);
        return "teachTask/courseTable";
    }

    //课表增加
    /*
    * 课表逻辑：
        if（自习）
              1.课程信息不存在直接return
              2.课表中信息已经存在直接return
              3.课表中id存在，但课表中课程信息不存在，执行更新操作
              4.课表中id不存在，设置id 放到list中，等待批量插入
              5.修改时用户直接输入空，但此时id存在，则根据id直接删除后，执行continue操作
        if（老师-课程）
               1.课程不存在直接return;
               2.老师不存在直接return
               3.老师课程不存在直接return
               4.课表中信息存在，直接return
               5.课表中id存在，但课表中课程信息不存在，执行更新操作
               6.课表中id不存在，设置id 放到list中，等待批量插入
               7.修改时用户直接输入空，但此时id存在，则根据id直接删除后，执行continue操作
    * */
    @ResponseBody
    @RequestMapping(value = "/table/save", method = RequestMethod.POST)
    public ResultEntity courseTableAdd(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        String jsonString = getParamVal(request, "str");
        String cycleId = getParamVal(request, "cycleId");
        String gradeClassId = getParamVal(request, "gradeClassId");
        String nj = getParamVal(request, "nj");
        String weekArr = getParamVal(request, "weekArr");
//        List<TeachTable> teachTablesAll = new ArrayList<>();
        //查询课程表中所有的数据 根据当前时间  第几周  这个必须要cycle的参数  计算当前周次
        TeachCycle teachCycle = teachTaskService.findTeachCycleByPrimarykey(cycleId);
//        Integer currentCycleWeek = gerCurrentCycleWeek(teachCycle);
        Integer currentCycleWeek = NumberConvertUtil.convertS2I(getParamVal(request, "week"));
        //以班级为单位存全部的任课教师
        Map<String, CourseClassView> courseClassViewMap = new HashedMap();

        //以班级为单位存取所有的课表信息
        Map<String, TeachTableView> teachTableViewMap = new HashedMap();

        List<CourseClassView> courseClassViewList = teachTaskService.findAllCourseTeacherBySchoolId(schoolId, gradeClassId);
        for (CourseClassView courseClassView : courseClassViewList) {
            courseClassViewMap.put(courseClassView.getTeacherId() + courseClassView.getCourseId() + courseClassView.getClassId(), courseClassView);
        }

        PageInfo<TeachTableView> pageInfo = teachTaskService.findTeachTableByCurrentCycleWeekAndSchoolId(schoolId, 0, null, null, gradeClassId, null, teachCycle.getId());
        List<TeachTableView> teachTableViews = pageInfo.getList();
        for (TeachTableView teachTableView : teachTableViews) {
            teachTableViewMap.put(teachTableView.getTeacherId() + teachTableView.getCourseId() + teachTableView.getNode() + teachTableView.getClassId() + teachTableView.getWeekDay() + teachTableView.getWeekend() + teachTableView.getCycleId(), teachTableView);
        }

        List<Teacher> teacherLlist = teacherService.findAllTeacher(schoolId);
        Map<String, Teacher> teacherMap = new HashedMap();
        Map<String, Teacher> teacherMapName = new HashedMap();
        for (Teacher teacher : teacherLlist) {
            teacherMapName.put(teacher.getName() + schoolId, teacher);
            teacherMap.put(teacher.getNo() + schoolId, teacher);
        }


        //查询所有课程的名字
        Map courseNameMap = new HashMap();
        List<Course> courseList = teachTaskService.findAllCourseBySchoolIdAndCycleId(schoolId, cycleId);
        for (Course course : courseList) {
            courseNameMap.put(course.getCycleId() + course.getName(), course.getId());
        }


        //根据gradeClassId、cycleId查询相应教室信息
        RefClassRoom refClassRoom = teachTaskService.findRefClassRoomByCycleIdAndGradeClassId(gradeClassId, cycleId);
        String roomId = "";
        if (refClassRoom != null) {
            roomId = refClassRoom.getRoomId();
        } else {
            return ResultEntity.newErrEntity("班级教室信息不存在");
        }
        if (StringUtils.isEmpty(jsonString)) {
            return ResultEntity.newErrEntity("数据为空，请填写相应数据后再提交保存");
        }

        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        JSONArray jsonArray = jsonObject.getJSONArray("oneWeek");
        List<TeachTable> teachTables = new ArrayList<>();

        List<Integer> weeks = new ArrayList<>();
        if (StringUtils.isNotEmpty(weekArr)) {
            String[] weekArray = weekArr.split(",");
            if (weekArray.length > 0) {
                for (int i = 0; i < weekArray.length; i++) {
                    weeks.add(Integer.parseInt(weekArray[i]));
                }
            }
        }

        List<TeachTable> delTeachTableIds = new ArrayList<>();
        List<TeachTable> updateBatchTeachTables = new ArrayList<>();
        if (weeks != null && weeks.size() > 0) {
            for (int oneweek = 0; oneweek < weeks.size(); oneweek++) {
                System.out.println(weeks.get(oneweek) + "===========");
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject one = jsonArray.getJSONObject(i);
                    String teacher = (String) one.get("teacher");
                    String node = (String) one.get("node");
                    String course = (String) one.get("course");
                    String oneday = (String) one.get("oneday");
                    String id = (String) one.get("id");
                    String onedayofWeek = convert(oneday);
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                    Teacher teacherObject = null;
                    if (StringUtils.isNotEmpty(id)) {
                        if (StringUtils.isEmpty(course)) {
                            //根据id直接删除原来的数据
                            //根据cycle weekend node tableId 删除学期中所有数据
                            TeachTable teachTable = new TeachTable();
                            teachTable.setClassId(gradeClassId);
                            //在数据库中该条数据的id已经存在
//                                    teachTable.setId(id);
                            teachTable.setSchoolId(schoolId);
                            teachTable.setTableId(onedayofWeek + "," + node);
                            teachTable.setWeekend(weeks.get(oneweek));
                            teachTable.setClassRoomId(roomId);
                            teachTable.setCycleId(cycleId);
                            teachTable.setId(id);
                            delTeachTableIds.add(teachTable);
                        } else {
                            if (StringUtils.isEmpty(teacher)) {
                                if (course.equals("自习")) {
                                    //课程信息不存在直接返回
                                    String courseId = (String) courseNameMap.get(cycleId + course);
                                    if (StringUtils.isEmpty(courseId)) {
                                        return ResultEntity.newErrEntity(String.valueOf(i));
                                    }

                                    //在数据库中该条信息已经存在，继续循环
                                    TeachTableView teachTableView = teachTableViewMap.get(null + courseId + node + gradeClassId + onedayofWeek + weeks.get(oneweek) + cycleId);
                                    if (teachTableView != null) continue;

                                    TeachTable teachTable = new TeachTable();
                                    teachTable.setClassId(gradeClassId);
                                    //在数据库中该条数据的id已经存在
                                    teachTable.setId(id);
                                    teachTable.setSchoolId(schoolId);
                                    teachTable.setCourseId(courseId);
                                    teachTable.setTableId(onedayofWeek + "," + node);
                                    teachTable.setTeacherId(null);
                                    teachTable.setWeekend(weeks.get(oneweek));
                                    teachTable.setClassRoomId(roomId);
                                    updateBatchTeachTables.add(teachTable);

                                } else {
                                    return ResultEntity.newErrEntity(i + "");
                                }
                            } else {
                                if (teacher.contains("(")) {
                                    //根据编号查询老师（因为老师有重名的情况）
                                    teacher.replace("（", "(");
                                    teacher.replace("）", ")");
                                    teacher = teacher.substring(teacher.indexOf("("), teacher.length() - 1);
                                    //执行查询
                                    teacherObject = teacherMap.get(teacher + schoolId);
                                } else {
                                    //根据名字查询教师信息
                                    teacherObject = teacherMapName.get(teacher + schoolId);
                                }

                                //教师信息不存在
                                if (teacherObject == null) {
                                    return ResultEntity.newErrEntity(String.valueOf(i));
                                }

                                String teacherId = teacherObject.getId();

                                //课程信息不存在
                                String courseId = (String) courseNameMap.get(cycleId + course);
                                if (StringUtils.isEmpty(courseId)) {
                                    return ResultEntity.newErrEntity(String.valueOf(i));
                                }

                                //任课教师信息不存在
                                if (null == courseClassViewMap.get(teacherId + courseId + gradeClassId)) {
                                    return ResultEntity.newErrEntity(String.valueOf(i));
                                }

                                //该课程信息已经在数据库中存在
//                                TeachTableView teachTableView = teachTableViewMap.get(teacherId + courseId + node + gradeClassId + onedayofWeek + weeks.get(oneweek) + cycleId);
//                                if (teachTableView != null) continue;

                                TeachTable teachTable = new TeachTable();
                                teachTable.setClassId(gradeClassId);
                                teachTable.setSchoolId(schoolId);
                                teachTable.setCourseId(courseId);
                                teachTable.setTableId(onedayofWeek + "," + node);
                                teachTable.setTeacherId(teacherId);
                                teachTable.setWeekend(weeks.get(oneweek));
                                teachTable.setClassRoomId(roomId);
                                teachTable.setId(id);
                                teachTable.setCycleId(cycleId);
                                updateBatchTeachTables.add(teachTable);
                            }
                        }
                    } else {
                        if (StringUtils.isEmpty(course)) {
                            continue;
                        } else {
                            if (StringUtils.isEmpty(teacher)) {
                                if (course.equals("自习")) {
                                    //课程信息不存在直接返回
                                    String courseId = (String) courseNameMap.get(cycleId + course);
                                    if (StringUtils.isEmpty(courseId)) {
                                        return ResultEntity.newErrEntity(String.valueOf(i));
                                    }

                                    //在数据库中该条信息已经存在，继续循环
//                                    TeachTableView teachTableView = teachTableViewMap.get(null + courseId + node + gradeClassId + onedayofWeek + weeks.get(oneweek) + cycleId);
//                                    if (teachTableView != null) continue;

                                    TeachTable teachTable = new TeachTable();
                                    teachTable.setClassId(gradeClassId);
                                    //在数据库中该条数据的id已经存在
                                    teachTable.setId(PrimaryKey.get());
                                    teachTable.setSchoolId(schoolId);
                                    teachTable.setCourseId(courseId);
                                    teachTable.setTableId(onedayofWeek + "," + node);
                                    teachTable.setTeacherId(null);
                                    teachTable.setWeekend(weeks.get(oneweek));
                                    teachTable.setClassRoomId(roomId);
                                    teachTable.setCycleId(cycleId);
                                    teachTables.add(teachTable);

                                } else {
                                    return ResultEntity.newErrEntity(i + "");
                                }
                            } else {
                                if (teacher.contains("(")) {
                                    //根据编号查询老师（因为老师有重名的情况）
                                    teacher.replace("（", "(");
                                    teacher.replace("）", ")");
                                    teacher = teacher.substring(teacher.indexOf("("), teacher.length() - 1);
                                    //执行查询
                                    teacherObject = teacherMap.get(teacher + schoolId);
                                } else {
                                    //根据名字查询教师信息
                                    teacherObject = teacherMapName.get(teacher + schoolId);
                                }

                                //教师信息不存在
                                if (teacherObject == null) {
                                    return ResultEntity.newErrEntity(String.valueOf(i));
                                }

                                String teacherId = teacherObject.getId();

                                //课程信息不存在
                                String courseId = (String) courseNameMap.get(cycleId + course);
                                if (StringUtils.isEmpty(courseId)) {
                                    return ResultEntity.newErrEntity(String.valueOf(i));
                                }

                                //任课教师信息不存在
                                if (null == courseClassViewMap.get(teacherId + courseId + gradeClassId)) {
                                    return ResultEntity.newErrEntity(String.valueOf(i));
                                }

                                //该课程信息已经在数据库中存在
                                TeachTableView teachTableView = teachTableViewMap.get(teacherId + courseId + node + gradeClassId + onedayofWeek + weeks.get(oneweek) + cycleId);
                                if (teachTableView != null) continue;

                                TeachTable teachTable = new TeachTable();
                                teachTable.setClassId(gradeClassId);
                                teachTable.setSchoolId(schoolId);
                                teachTable.setCourseId(courseId);
                                teachTable.setTableId(onedayofWeek + "," + node);
                                teachTable.setTeacherId(teacherId);
                                teachTable.setId(PrimaryKey.get());
                                teachTable.setWeekend(weeks.get(oneweek));
                                teachTable.setClassRoomId(roomId);
                                teachTable.setCycleId(cycleId);
                                teachTables.add(teachTable);
                            }
                        }
                    }
                    //++++++++++++++++=====================================================================================================================================

//                    if (course.equals("自习")) {
//                        String courseId = (String) courseNameMap.get(cycleId + course);
//
//                        //课程信息不存在直接返回
//                        if (StringUtils.isEmpty(courseId)) {
//                            return ResultEntity.newErrEntity(String.valueOf(i));
//                        }
//
//                        //在数据库中该条信息已经存在，继续循环
//                        TeachTableView teachTableView = teachTableViewMap.get(null + courseId + node + gradeClassId + onedayofWeek + weeks.get(oneweek) + cycleId);
//                        if (teachTableView != null) continue;
//
//
//                        TeachTable teachTable = new TeachTable();
//                        teachTable.setClassId(gradeClassId);
//
//                        //在数据库中该条数据的id已经存在
//                        if (StringUtils.isNotBlank(id)) {
//                            teachTable.setId(id);
//                        }
//                        teachTable.setSchoolId(schoolId);
//                        teachTable.setCourseId(courseId);
//                        teachTable.setTableId(onedayofWeek + "," + node);
//                        teachTable.setTeacherId(null);
////                teachTable.setWeekend(currentCycleWeek);
//                        teachTable.setClassRoomId(roomId);
//                        if (course == "") {
////                        int delsucc = teachTaskService.delTeachTableByPrimarykey(id);
//                            continue;
//                        }
//                        teachTable.setId(PrimaryKey.get());
//                        teachTables.add(teachTable);
//                    }


//                    if (StringUtils.isNotBlank(teacher) && StringUtils.isNotBlank(course)) {
//                        if (teacher.contains("(")) {
//                            //根据编号查询老师（因为老师有重名的情况）
//                            teacher.replace("（", "(");
//                            teacher.replace("）", ")");
//                            teacher = teacher.substring(teacher.indexOf("("), teacher.length() - 1);
//                            //执行查询
//                            teacherObject = teacherMap.get(teacher + schoolId);
//                        } else {
//                            //根据名字查询教师信息
//                            teacherObject = teacherMapName.get(teacher + schoolId);
//                        }
//                        if (teacherObject == null) {
//                            return ResultEntity.newErrEntity(String.valueOf(i));
////                    return ResultEntity.newErrEntity("该学校老师信息不存在");
//                        }
//                        String teacherId = teacherObject.getId();
//
//                        String courseId = (String) courseNameMap.get(cycleId + course);
//                        if (StringUtils.isEmpty(courseId)) {
////                    return ResultEntity.newErrEntity("对应的教学周期内" + course + "课程信息不存在");
//                            return ResultEntity.newErrEntity(String.valueOf(i));
//                        }
//                        if (null == courseClassViewMap.get(teacherId + courseId + gradeClassId)) {
//
//                            return ResultEntity.newErrEntity(String.valueOf(i));
////                    return ResultEntity.newErrEntity(teacher+"-"+course + "任课教师信息不存在");
//                        }
//
//                        TeachTableView teachTableView = teachTableViewMap.get(teacherId + courseId + node + gradeClassId + onedayofWeek + weeks.get(oneweek));
//                        if (teachTableView != null) continue;
//
//                        TeachTable teachTable = new TeachTable();
//                        teachTable.setClassId(gradeClassId);
////                if (StringUtils.isNotBlank(id)) {
////                    teachTable.setId(id);
////                }
//                        teachTable.setSchoolId(schoolId);
//                        teachTable.setCourseId(courseId);
//                        teachTable.setTableId(onedayofWeek + "," + node);
//                        teachTable.setTeacherId(teacherId);
////                teachTable.setWeekend(currentCycleWeek);
//                        teachTable.setClassRoomId(roomId);
//                        teachTable.setId(PrimaryKey.get());
//                        teachTables.add(teachTable);
//                    } else {
//                        continue;
//                    }
                }
            }
        }

//        if (teachTables.size() > 0) {
//            if (teachCycle != null) {
//                if (weeks.size() > 0) {
//                    for (int k = 0; k < weeks.size(); k++) {
//                        if (teachTables.size() > 0) {
//                            for (TeachTable teachTable : teachTables) {
//                                TeachTable teachTable1 = new TeachTable();
//                                teachTable1.setWeekend(weeks.get(k));
//                                teachTable1.setClassRoomId(teachTable.getClassRoomId());
//                                teachTable1.setTeacherId(teachTable.getTeacherId());
//                                teachTable1.setId(PrimaryKey.get());
//                                teachTable1.setTableId(teachTable.getTableId());
//                                teachTable1.setWeekend(weeks.get(k));
//                                teachTable1.setSchoolId(schoolId);
//                                teachTable1.setClassId(teachTable.getClassId());
//                                teachTable1.setCourseId(teachTable.getCourseId());
//                                teachTable1.setCycleId(cycleId);
//                                teachTablesAllNeedInsert.add(teachTable1);
//
//                            }
//                        }
//                    }
//                }
//            }
//        }
        if (updateBatchTeachTables.size() > 0) {
            teachTaskService.batchUpdateTeachTable(updateBatchTeachTables, delTeachTableIds);
        }
        if (teachTables.size() > 0) {
            teachTaskService.batchInsertTeachTable(teachTables);
        }
        return ResultEntity.newResultEntity("保存成功", "");
    }

    public Integer gerCurrentCycleWeek(TeachCycle teachCycle) {
        //得到是当年的第几周
        Calendar c = Calendar.getInstance();
        int currentWeek = c.get(Calendar.WEEK_OF_YEAR);

        //根据学校Id查询最近的教学周期，获取开学时间
        Long termBeginTime = 0L;
        if (teachCycle.getId() != null) {
            termBeginTime = teachCycle.getTermBeginTime();
        }

        //确定开学时间是第几周
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(termBeginTime);
        int beginWeek = calendar.get(Calendar.WEEK_OF_YEAR);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        try {
            if (termBeginTime != null) {
                simpleDateFormat.format(longToDate(termBeginTime, "yyyy-MM-dd"));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //对应的当前教学周次
        int currentCycleWeek = (currentWeek - beginWeek) + 1;
        return currentCycleWeek;
    }

    public String convert(String oneday) {
        String onedayofweek = "";
        if (oneday.equals("monday")) {
            onedayofweek = "1";
        }
        if (oneday.equals("tuesday")) {
            onedayofweek = "2";
        }
        if (oneday.equals("wednesday")) {
            onedayofweek = "3";
        }
        if (oneday.equals("thursday")) {
            onedayofweek = "4";
        }
        if (oneday.equals("friday")) {
            onedayofweek = "5";
        }
        return onedayofweek;
    }

    public String njConvert(String nj) {
        String njStr = "";
        if (nj.equals("1")) {
            njStr = "一";
        }
        if (nj.equals("2")) {
            njStr = "二";
        }
        if (nj.equals("3")) {
            njStr = "三";
        }
        if (nj.equals("4")) {
            njStr = "四";
        }
        if (nj.equals("5")) {
            njStr = "五";
        }
        if (nj.equals("6")) {
            njStr = "六";
        }
        return njStr;
    }


    //课表模板
    @RequestMapping(value = "/table/download")
    public void exportTableTemplate(HttpServletResponse response) {
        try {
            String fileName = "课表信息导入模板.xlsx";
            String anno = "注释：橙色字段为必填项\n";
            new ExportExcel(false, "课表信息导入模板", IOCTableView.class, 2, anno, 1).setDataList(new ArrayList()).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //下载导入失败的table列表
    @RequestMapping(value = "/table/error/export", method = RequestMethod.POST)
    public void errorTable(HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileName = "错误信息列表.xlsx";
            String anno = "注释：橙色字段为必填项";
            String msg = getParamVal(request, "msg");
            JsonArray jsonArray = new JsonParser().parse(msg).getAsJsonArray();
            List<IOCTableView> exportFile = new ArrayList<IOCTableView>();
            for (JsonElement jsonElement : jsonArray) {
                IOCTableView importBundling = GsonUtil.fromJson(jsonElement.getAsJsonObject(), IOCTableView.class);
                exportFile.add(importBundling);
            }
            new ExportExcel(false, "课表错误信息", IOCTableView.class, 2, anno, 1).setDataList(exportFile).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/fileImport", method = RequestMethod.GET)
    public String tableImportPop(HttpServletRequest request, Model model) {
        String submitUrl = getParamVal(request, "url");
        model.addAttribute("url", submitUrl);
        String cycleId = getParamVal(request, "cycleId");
        TeachCycle teachCycle = new TeachCycle();
        if (StringUtils.isNotEmpty(cycleId)) {
            teachCycle = teachTaskService.findTeachCycleByPrimarykey(cycleId);
        }
        Integer countweek = 0;
        if (null != teachCycle) {
            countweek = teachCycle.getWeekCount();
        }
        List<Integer> integerList = new ArrayList<>();
        if (countweek != null && countweek > 0) {
            for (int i = 0; i < countweek; i++) {
                integerList.add(i + 1);
            }
        }

        model.addAttribute("integerList", integerList);
        model.addAttribute("cycleId", cycleId);
        model.addAttribute("countweek", countweek);
        User user = getLoginUser();
        String schoolId = user.getSchoolId();

        //根据学校id查询

        //根据schoold查询所有的学段
        List<ClassSection> classSections = classService.getAllClassSectionBySchoolId(schoolId);
        model.addAttribute("classSections", classSections);
        return "teachTask/pop/courseTableImportPop";
    }

    @ResponseBody
    @RequestMapping(value = "/table/import/pop/select/change")
    public ResultEntity xdNjBanjiSelectChange(HttpServletRequest request, String mydata) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();

        Map<String, Object> map = classCardService.selectCascadeClass(mydata, schoolId);
        return ResultEntity.newResultEntity(map);
    }


    //导入任课教师
    @ResponseBody
    @RequestMapping(value = "/table/import")
    public ResponseEntity tableImport(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) throws Exception {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        Long begin = System.currentTimeMillis();
        String xdId = getParamVal(request, "xdId");
        String nj = getParamVal(request, "nj");
        String banji = getParamVal(request, "banji");
        String weekArr = getParamVal(request, "weekArr");
        String cycleId = getParamVal(request, "cycleId");

        //根据cycleId和classId查询班级教室信息
        RefClassRoom refClassRoom = new RefClassRoom();
        DailyHour dailyHour = new DailyHour();
        if (StringUtils.isNotEmpty(cycleId) && StringUtils.isNotEmpty(banji)) {
            refClassRoom = teachTaskService.findRefClassRoomByCycleIdAndGradeClassId(banji, cycleId);
            dailyHour = teachTaskService.fingDailyHourByCycleIdAndClassId(cycleId, banji);
        }

        List<Integer> weeks = new ArrayList<>();
        if (StringUtils.isNotEmpty(weekArr)) {
            String[] weekArray = weekArr.split(",");
            if (weekArray.length > 0) {
                for (int i = 0; i < weekArray.length; i++) {
                    weeks.add(Integer.parseInt(weekArray[i]));
                }
            }
        }

        List<IOCTableView> errorIOCTeachTableViewList = new ArrayList<IOCTableView>();
        List<TeachTable> correctTeachTable = new ArrayList<TeachTable>();
        List<Double> correctTeachTableCount = new ArrayList<>();
        List<IOCTableView> errTeachTableCount = new ArrayList<>();
        IOCTableView errTeachTableView = new IOCTableView();
        ImportExcel importExcel = new ImportExcel(file, 2, 0);
        List<IOCTableView> list = importExcel.getDataList(IOCTableView.class, 1);

        List<Teacher> teacherLlist = teacherService.findAllTeacher(schoolId);
        Map<String, Teacher> teacherMap = new HashedMap();
        Map<String, Teacher> teacherMapName = new HashedMap();
        for (Teacher teacher : teacherLlist) {
            teacherMapName.put(teacher.getName() + schoolId, teacher);
            teacherMap.put(teacher.getNo() + schoolId, teacher);
        }

        //查询所有课程的名字
        Map courseNameMap = new HashMap();
        List<Course> courseList = teachTaskService.findAllCourseBySchoolIdAndCycleId(schoolId, null);
        for (Course course : courseList) {
            courseNameMap.put(course.getCycleId() + course.getName(), course.getId());
        }

        Map<String, TeachTableView> teachTableViewMap = new HashedMap();
        PageInfo<TeachTableView> pageInfo = teachTaskService.findTeachTableByCurrentCycleWeekAndSchoolId(schoolId, 0, null, null, banji, null, cycleId);
        List<TeachTableView> teachTableViews = pageInfo.getList();
        for (TeachTableView teachTableView : teachTableViews) {
            teachTableViewMap.put(teachTableView.getTeacherId() + teachTableView.getCourseId() + teachTableView.getNode() + teachTableView.getClassId() + teachTableView.getWeekDay() + teachTableView.getWeekend() + teachTableView.getCycleId(), teachTableView);
        }

        //查询所有的任课教师
        Map courseTeacherMap = new HashMap();

        //用来判断这个学期的课程班级信息能不能对应的上是不是
        Map courseClassMapForId = new HashedMap();
        List<CourseClass> courseTeacherList = teachTaskService.findAllCourseClassByCourseList(courseList);
        for (CourseClass courseClass : courseTeacherList) {
            courseTeacherMap.put(courseClass.getCourseId() + courseClass.getClassId() + courseClass.getTeacherId(), courseClass.getId());
            courseClassMapForId.put(courseClass.getClassId() + courseClass.getCourseId(), courseClass.getId());
        }

        int i = 0;

        if (weeks != null && weeks.size() > 0) {
            for (int oneweek = 0; oneweek < weeks.size(); oneweek++) {
                int iocViewNode = 0;
                if (list != null && list.size() > 0) {
                    for (IOCTableView iocTableView : list) {
                        errTeachTableView = new IOCTableView();
                        errTeachTableView.setMonday(iocTableView.getMonday());
                        errTeachTableView.setNodeAndWeek(iocTableView.getNodeAndWeek());
                        errTeachTableView.setTuesday(iocTableView.getTuesday());
                        errTeachTableView.setWednsday(iocTableView.getWednsday());
                        errTeachTableView.setThursday(iocTableView.getThursday());
                        errTeachTableView.setFriday(iocTableView.getFriday());
                        try {
                            if (StringUtils.isNotEmpty(banji) && StringUtils.isNotEmpty(cycleId)) {
                                //根基banji和cycleId查询班级日常课时

                                if (dailyHour != null) {
                                    Integer skts = dailyHour.getSkts();
                                    if (skts > 0) {
                                        String courseTeacher1 = iocTableView.getMonday();
                                        //判断各种信息是否存在 课程 老师  课程-老师   以及是否已经存在于系统中
                                        TeachTable teachTable1 = isInsertTeachTable(courseNameMap, teacherMap,
                                                teacherMapName, courseClassMapForId, courseTeacher1, iocTableView,
                                                cycleId, banji, weeks.get(oneweek), teachTableViewMap, iocViewNode,
                                                1, weeks.get(oneweek), refClassRoom, schoolId, courseTeacherMap);
                                        if (null != teachTable1) {
                                            correctTeachTable.add(teachTable1);
//                                            iocTableView.setMonday("");
                                            errTeachTableView.setMonday("");
                                        } else {
                                            errTeachTableView.setTuesday(courseTeacher1);
                                            errTeachTableCount.add(errTeachTableView);
                                        }

                                        String courseTeacher2 = iocTableView.getTuesday();
                                        TeachTable teachTable2 = isInsertTeachTable(courseNameMap, teacherMap,
                                                teacherMapName, courseClassMapForId, courseTeacher2, iocTableView,
                                                cycleId, banji, weeks.get(oneweek), teachTableViewMap, iocViewNode,
                                                2, weeks.get(oneweek), refClassRoom, schoolId, courseTeacherMap);
                                        if (null != teachTable2) {
                                            correctTeachTable.add(teachTable2);
//                                            iocTableView.setTuesday("");
                                            errTeachTableView.setTuesday("");
                                        } else {
                                            errTeachTableView.setTuesday(courseTeacher2);
                                            errTeachTableCount.add(errTeachTableView);
                                        }

                                        String courseTeacher3 = iocTableView.getWednsday();
                                        TeachTable teachTable3 = isInsertTeachTable(courseNameMap, teacherMap,
                                                teacherMapName, courseClassMapForId, courseTeacher3, iocTableView,
                                                cycleId, banji, weeks.get(oneweek), teachTableViewMap, iocViewNode,
                                                3, weeks.get(oneweek), refClassRoom, schoolId, courseTeacherMap);
                                        if (null != teachTable3) {
                                            correctTeachTable.add(teachTable3);
//                                            iocTableView.setWednsday("");
                                            errTeachTableView.setWednsday("");
                                        } else {
                                            errTeachTableView.setWednsday(courseTeacher3);
                                            errTeachTableCount.add(errTeachTableView);
                                        }

                                        String courseTeacher4 = iocTableView.getThursday();
                                        TeachTable teachTable4 = isInsertTeachTable(courseNameMap, teacherMap,
                                                teacherMapName, courseClassMapForId, courseTeacher4, iocTableView,
                                                cycleId, banji, weeks.get(oneweek), teachTableViewMap, iocViewNode,
                                                4, weeks.get(oneweek), refClassRoom, schoolId, courseTeacherMap);
                                        if (null != teachTable4) {
                                            correctTeachTable.add(teachTable4);
                                            errTeachTableView.setThursday("");
                                        } else {
                                            errTeachTableView.setThursday(courseTeacher4);
                                            errTeachTableCount.add(errTeachTableView);
                                        }

                                        String courseTeacher5 = iocTableView.getFriday();
                                        TeachTable teachTable5 = isInsertTeachTable(courseNameMap, teacherMap,
                                                teacherMapName, courseClassMapForId, courseTeacher5, iocTableView,
                                                cycleId, banji, weeks.get(oneweek), teachTableViewMap, iocViewNode,
                                                5, weeks.get(oneweek), refClassRoom, schoolId, courseTeacherMap);
                                        if (null != teachTable5) {
                                            correctTeachTable.add(teachTable5);
//                                            iocTableView.setFriday("");
                                            errTeachTableView.setFriday("");
                                        } else {
                                            errTeachTableView.setFriday(courseTeacher5);
                                            errTeachTableCount.add(errTeachTableView);
                                        }
//                                        if (StringUtils.isEmpty(courseTeacher1) || StringUtils.isEmpty(courseTeacher2) ||
//                                                StringUtils.isEmpty(courseTeacher3) || StringUtils.isEmpty(courseTeacher4) ||
//                                                StringUtils.isEmpty(courseTeacher5)) {
//                                            errorIOCTeachTableViewList.add(errTeachTableView);
//                                        }
                                        errorIOCTeachTableViewList.add(errTeachTableView);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            continue;
                        }
                        iocViewNode++;
                    }

                }
                if (errorIOCTeachTableViewList.size() > 0) {
                    continue;
                }
            }
        }
        if (correctTeachTable.size() > 0) {
            //执行批量更新，因为course和class已经有这条数据，只能是更新
            teachTaskService.batchInsertTeachTable(correctTeachTable);
        }
        List<IOCTableView> errIocTeachTableViewForOneweek = new ArrayList<>();
        if (dailyHour != null) {
            if (dailyHour.getSwks() != null && dailyHour.getXwks() != null) {
                Integer total = dailyHour.getSwks() + dailyHour.getXwks();
                if (errorIOCTeachTableViewList.size() > total) {
                    for (int m = 0; m < total; m++) {
                        errIocTeachTableViewForOneweek.add(errorIOCTeachTableViewList.get(m));
                    }
                }
            }
        }

        Long end = System.currentTimeMillis();
        Map res = new HashMap();
        res.put("msg", "共" + correctTeachTable.size() + "条成功，" + errTeachTableCount.size() + "条失败,耗时" + (end - begin) / 1000 + "秒");
        res.put("errorList", errIocTeachTableViewForOneweek);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    public TeachTable isInsertTeachTable(Map courseNameMap, Map teacherMap, Map teacherMapName, Map courseClassMap,
                                         String courseTeacher, IOCTableView iocTableView, String cycleId, String classId,
                                         Integer oneweek, Map teachTableViewMap, int node, int onedayofWeek, int week,
                                         RefClassRoom refClassRoom, String schoolId, Map courseTeacherMap) {
        if (StringUtils.isEmpty(courseTeacher)) {
            return null;
        }

        if (courseTeacher.equals("自习")) {
            //课程存不存在
            String courseId = (String) courseNameMap.get(cycleId + courseTeacher);
            if (StringUtils.isEmpty(courseId)) {
                return null;
            }

            //课程的授课班级存不存在
            String refCourseClassId = (String) courseClassMap.get(classId + courseId);
            if (StringUtils.isEmpty(refCourseClassId)) {
                return null;
            }

            //查看改课程信息是否已经导入过
            TeachTableView teachTableView = (TeachTableView) teachTableViewMap.get(null + courseId + node + classId + onedayofWeek + week + cycleId);
            if (teachTableView != null) return null;

            TeachTable teachTable = new TeachTable();
            teachTable.setCycleId(cycleId);
            teachTable.setClassId(classId);
            //班级教室信息不存在也返回空
            if (refClassRoom != null) {
                teachTable.setClassRoomId(refClassRoom.getRoomId());
            } else {
                return null;
            }
            teachTable.setId(PrimaryKey.get());
            teachTable.setSchoolId(schoolId);
            teachTable.setTeacherId(null);
            teachTable.setWeekend(week);
            teachTable.setCourseId(courseId);
            teachTable.setTableId(onedayofWeek + "," + node);

        } else {

            if (!courseTeacher.replace("-", "-").contains("-")) {
                return null;
            }
            String[] arr = courseTeacher.split("-");
            if (arr.length < 2) {
                return null;
            }
            String teacher = arr[0];
            String course = arr[1];

            //课程信息不存在
            String courseId = (String) courseNameMap.get(cycleId + course);
            if (StringUtils.isEmpty(courseId)) {
                return null;
            }

            //课程的授课班级存不存在
            String refCourseClassId = (String) courseClassMap.get(classId + courseId);
            if (StringUtils.isEmpty(refCourseClassId)) {
                return null;
            }

            Teacher teacherObject = null;
            if (teacher.contains("(")) {
                //根据编号查询老师（因为老师有重名的情况）
                teacher.replace("（", "(");
                teacher.replace("）", ")");
                teacher = teacher.substring(teacher.indexOf("("), teacher.length() - 1);
                //执行查询
                teacherObject = (Teacher) teacherMap.get(teacher + schoolId);
            } else {
                //根据名字查询教师信息
                teacherObject = (Teacher) teacherMapName.get(teacher + schoolId);
            }

            //教师信息不存在
            if (teacherObject == null) {
                return null;
            }
            String teacherId = teacherObject.getId();

            //任课教师信息不存在
            if (null == courseTeacherMap.get(courseId + classId + teacherId)) {
                return null;
            }


            //查看改课程信息是否已经导入过
            TeachTableView teachTableView = (TeachTableView) teachTableViewMap.get(teacherId + courseId + node + classId + onedayofWeek + week + cycleId);
            if (teachTableView != null) return null;

            TeachTable teachTable = new TeachTable();
            teachTable.setCycleId(cycleId);
            teachTable.setClassId(classId);
            //班级教室信息不存在也返回空
            if (refClassRoom != null) {
                teachTable.setClassRoomId(refClassRoom.getRoomId());
            } else {
                return null;
            }
            teachTable.setId(PrimaryKey.get());
            teachTable.setSchoolId(schoolId);
            teachTable.setTeacherId(teacherId);
            teachTable.setWeekend(week);
            teachTable.setCourseId(courseId);
            teachTable.setTableId(onedayofWeek + "," + node);
            return teachTable;
        }
        return null;
    }


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        List<String> list1 = new ArrayList<>();
        list1.add("2");
        list1.add("3");
        list1.add("4");

        List<String> l2 = new ArrayList<>(list1);
        List<String> l22 = new ArrayList<>(list);

        System.out.println(list.removeAll(list1));
        System.out.println(list);
        System.out.println(list.removeAll(l2));
        System.out.println(list1.removeAll(l22));
        System.out.println(list1);

    }
}

