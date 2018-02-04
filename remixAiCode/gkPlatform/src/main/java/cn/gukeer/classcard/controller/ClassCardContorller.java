package cn.gukeer.classcard.controller;

import cn.gukeer.classcard.modelView.*;
import cn.gukeer.classcard.persistence.entity.*;
import cn.gukeer.classcard.service.*;
import cn.gukeer.common.controller.BasicController;
import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.common.exception.ErrcodeException;
import cn.gukeer.common.tld.GukeerStringUtil;
import cn.gukeer.common.utils.*;
import cn.gukeer.common.utils.excel.ExportExcel;
import cn.gukeer.common.utils.excel.ImportExcel;
import cn.gukeer.platform.common.UserRoleType;
import cn.gukeer.platform.modelView.*;
import cn.gukeer.platform.modelView.importExport.IOClassCardView;
import cn.gukeer.platform.persistence.dao.A_GradeClassMapper;
import cn.gukeer.platform.persistence.dao.ClassRoomMapper;
import cn.gukeer.platform.persistence.dao.RefClassRoomMapper;
import cn.gukeer.platform.persistence.entity.*;
import cn.gukeer.platform.service.*;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.google.common.collect.Lists;
import com.google.gson.*;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.xpath.SourceTree;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.List;

/**
 * Created by alpha on 17-6-26.
 */
@Controller
@RequestMapping(value = "/classcard")
public class ClassCardContorller extends BasicController {
    @Autowired
    ClassCardService classCardService;

    @Autowired
    ClassService classService;

    @Autowired
    TeachTaskService teachTaskService;

    @Autowired
    ClassRoomMapper classRoomMapper;

    @Autowired
    A_GradeClassMapper a_gradeClassMapper;

    @Autowired
    RefClassRoomMapper refClassRoomMapper;

    @Autowired
    SchoolService schoolService;

    @Autowired
    ClassSpacePictureService classSpacePictureService;

    @Autowired
    ClassSpaceVideoService classSpaceVideoService;

    /**
     * 从云平台进入班牌
     *
     * @param request
     * @param model
     * @return
     */
    //@RequiresPermissions("classCard:equipment:view")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String indexGet(HttpServletRequest request, Model model) {
        String appId = getParamVal(request, "appId");
        if (!"".equals(appId)) {
            request.getSession().setAttribute("appId", appId);
        }
        SchoolView schoolview = new SchoolView();
        PageInfo<ClassCardView> pageInfo = new PageInfo<>();

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        String schoolId = user.getSchoolId();

        if (subject.isAuthenticated()) {
            if (subject.hasRole(UserRoleType.ROLE_CLASSCARDADMIN) || subject.hasRole(UserRoleType.ROLE_SCHOOLADMIN)) {
                String[] judge = {"", "", "", ""};
                schoolview = classService.selectAndMakeTree(schoolId, judge, true);
                pageInfo = classCardService.selectClassCardByChoose(schoolId, "", "", 0, "", 0, 10);
            } else if (subject.hasRole(UserRoleType.ROLE_HEADTEACHER)) {
                String teacherId = user.getRefId();
                List<TeacherClass> teacherClasses = teachTaskService.findAllByTeacherId(teacherId);
                StringBuilder stringBuilder = new StringBuilder();
                for (TeacherClass tc : teacherClasses) {
                    stringBuilder.append(tc.getClassId()).append(",");
                }
//                String[] judge = {stringBuilder.toString(), "", "", ""};
                pageInfo = classCardService.selectClassCardByChoose(schoolId, stringBuilder.toString(), "", 0, "", 0, 10);
            }
        }
        if (null != pageInfo) {
            model.addAttribute("pageInfo", pageInfo);
        } else {
            model.addAttribute("pageInfo", null);
        }
        model.addAttribute("schoolview", schoolview);
        model.addAttribute("focusNode", "school_" + schoolId);
        model.addAttribute("schoolId", schoolId);

       /* List searchParam = new ArrayList();
        searchParam.add(schoolId);
        searchParam.add("");
        searchParam.add("");
        searchParam.add(0);
        searchParam.add(0);
        searchParam.add(-1);
        searchParam.add("");
        model.addAttribute("searchParam", searchParam);*/
        return "classCard/index";
    }

    // @RequiresPermissions("classCard:equipment:view")
    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String indexPost(HttpServletRequest request, Model model, String nodeList) {
        String _cId = getParamVal(request, "classId");
        String _sId = getParamVal(request, "schoolId");
        String _xd = getParamVal(request, "xd");
        String _xq = getParamVal(request, "xq");
        String _nj = getParamVal(request, "nj");
        String nowfocus = getParamVal(request, "focusNode");
        String schoolId = getLoginUser().getSchoolId();

        String xd, xq;
        int nj;
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
            xq = _xq;
        else xq = "";
        if (_nj != null && _nj != "")
            nj = NumberConvertUtil.convertS2I(_nj);
        else nj = 0;
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        PageInfo<ClassCardView> pageInfo = null;
        if (subject.isAuthenticated()) {
            SchoolView schoolview = new SchoolView();
            String[] judge = {cid, xd, xq, String.valueOf(nj)};
            if (nodeList == null || nodeList == "") {                 //可能性为0~
                if (subject.hasRole(UserRoleType.ROLE_CLASSCARDADMIN) || subject.hasRole(UserRoleType.ROLE_SCHOOLADMIN)) {
                    schoolview = classService.selectAndMakeTree(schoolId, judge, true);
                } else {
                    schoolview = classService.selectAndMakeTree(schoolId, judge, false);
                }
                model.addAttribute("schoolview", schoolview);
            } else {
                model.addAttribute("nodeList", nodeList);
            }
            //查询走班的班牌
            if ("qt".equals(xd)) {
                pageInfo = classCardService.findFreeClass(schoolId, pageNum, pageSize);
            } else {
                pageInfo = classCardService.selectClassCardByChoose(sid, cid, xd, nj, xq, pageNum, pageSize);
            }
        }
        if (null != pageInfo) {
            model.addAttribute("pageInfo", pageInfo);
        }
        model.addAttribute("classId", cid);
        model.addAttribute("schoolId", sid);
        model.addAttribute("focusNode", nowfocus);
        model.addAttribute("xd", xd);
        model.addAttribute("xq", xq);
        model.addAttribute("nj", nj);
        return "classCard/index";
    }

    /**
     * 编辑班牌信息
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editClassCard(HttpServletRequest request, Model model) {
        String schoolId = getLoginUser().getSchoolId();
        List<SchoolType> schoolTypes = schoolService.selectSchoolTypeBySchoolId(getLoginUser().getSchoolId());
        List<Map<String, String>> xds = classCardService.selectXdBySchool(schoolId);
        String _id = getParamVal(request, "id");
        if (!"".equals(_id)) {
            ClassCard classCard = classCardService.selectClassCardById(_id);
            model.addAttribute("classCard", classCard);
            //班牌位置
            ClassRoom classRoom = new ClassRoom();
            if (!"".equals(classCard.getTeachClassRoomId()) && classCard.getTeachClassRoomId() != null) {
                classRoom = classRoomMapper.selectByPrimaryKey(classCard.getTeachClassRoomId());
            }
            model.addAttribute("classRoom", classRoom);
            //班牌班级
            GradeClass_view gradeClassExtention = new GradeClass_view();
            if (!"".equals(classCard.getClassId()) && classCard.getClassId() != null) {
                gradeClassExtention = a_gradeClassMapper.findByClassId(classCard.getClassId());
            }
            model.addAttribute("gradeClassExtention", gradeClassExtention);
            model.addAttribute("disabled", "disabled");
        }

        List<ClassCard> classCardList = classCardService.findUnboundClassCard();
        model.addAttribute("classCardList", new Gson().toJson(classCardList));

        String option = getParamVal(request, "option");
        model.addAttribute("option", option);
        model.addAttribute("schoolTypes", schoolTypes);
        model.addAttribute("schoolId", schoolId);
        model.addAttribute("xds", xds);
        return "classCard/edit";
    }

    /**
     * 级联查询编辑班牌时的班级位置信息
     *
     * @param request
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cascadeclassroom")
    public ResultEntity cascadeClassRoom(HttpServletRequest request, Model model, String mydata) {
        String schoolId = getLoginUser().getSchoolId();
        Map<String, Object> map = classCardService.selectCascadeClassRoom(mydata, schoolId);
        return ResultEntity.newResultEntity(map);
    }

    /**
     * 级联查询编辑班牌时的班级
     *
     * @param request
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cascadeclass")
    public ResultEntity cascadeClass(HttpServletRequest request, Model model, String mydata) {
        String schoolId = getLoginUser().getSchoolId();
        Map<String, Object> map = classCardService.selectCascadeClass(mydata, schoolId);
        return ResultEntity.newResultEntity(map);
    }

    /**
     * 判断位置是否被占用
     *
     * @param request
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "islocationtoken")
    public ResultEntity isLocaltationToken(HttpServletRequest request, Model model) {
        String roomId = getParamVal(request, "roomId");
        String classCardId = getParamVal(request, "classCardId");

        ResultEntity resultEntity = null;
        ClassCard classCard = new ClassCard();
        int roomRefClassCard = 0;
        if (!"".equals(roomId)) {
            classCard = classCardService.selectClassCardByTeachClassRoomId(roomId);
            //若为编辑，判断当前班牌和教室是否关联
            if (!"".equals(classCardId)) {
                roomRefClassCard = classCardService.selectClassCardByRoomIdAndCardId(roomId, classCardId);
            }
        } else {
            return null;
        }
        //位置被非当前班牌绑定
        if (roomRefClassCard == 0 && classCard != null) {
            resultEntity = ResultEntity.newErrEntity();
        } else {
            resultEntity = ResultEntity.newResultEntity();
        }
        return resultEntity;
    }

    @ResponseBody
    @RequestMapping(value = "isclasstoken")
    public ResultEntity isClassToken(HttpServletRequest request, Model model) {
        String classId = getParamVal(request, "classId");
        String classCardId = getParamVal(request, "classCardId");

        ResultEntity resultEntity = null;
        ClassCard classCard = null;
        int classRefClassCard = 0;
        if (!"".equals(classId)) {
            classCard = classCardService.selectClassCardByClassId(classId);
            //若为编辑，判断当前班牌和班级是否关联
            if (!"".equals(classCardId)) {
                classRefClassCard = classCardService.selectClassCardByClassIdAndCardId(classId, classCardId);
            }
        } else {
            return null;
        }
        //选择班级后查出教室
      /*  RefClassRoomExample example=new RefClassRoomExample();
        example.createCriteria().andGradeClassEqualTo(classId);
        List<RefClassRoom>refClassRoomList=refClassRoomMapper.selectByExample(example);
        ClassRoom classroom=new ClassRoom();
        if(refClassRoomList!=null&&refClassRoomList.size()==1){
            //班级和教室的关联
            RefClassRoom rcr=refClassRoomList.get(0);

            ClassRoomExample example1=new ClassRoomExample();
            example1.createCriteria().andIdEqualTo(rcr.getRoomId());
            List<ClassRoom> classRooms = classRoomMapper.selectByExample(example1);
            if(classRooms!=null&&classRooms.size()==1){
                classroom=classRooms.get(0);
            }
        }*/
        if (classRefClassCard == 0 && classCard != null) {
            resultEntity = ResultEntity.newResultEntity(ResultEntity.ERR_CODE, ResultEntity.ERR_MSG, null);
        } else {
            resultEntity = ResultEntity.newResultEntity(ResultEntity.OK_CODE, ResultEntity.OK_MSG, null);
        }
        return resultEntity;
    }

    /**
     * 保存班牌
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save")
    public ResultEntity saveClassCard(HttpServletRequest request) {
        String schoolId = getParamVal(request, "schoolId");
        if ("".equals(schoolId)) {
            return ResultEntity.newErrEntity("保存失败");
        }
        String classId = getParamVal(request, "classId");
        String teachClassRoomId = getParamVal(request, "teachClassRoomId");
        if (!"".equals(classId) && !"".equals(teachClassRoomId)) {
            List<TeachCycle> cycleList = teachTaskService.getCycleList(schoolId);
            TeachCycle teachCycle = null;
            if (cycleList != null && cycleList.size() > 0) {
                teachCycle = cycleList.get(0);
            }
            if (teachCycle == null) {
                return ResultEntity.newErrEntity("当前学期为空！");
            }

            RefClassRoomExample example = new RefClassRoomExample();
            example.createCriteria().andGradeClassEqualTo(classId).andRoomIdEqualTo(teachClassRoomId).andCycleIdEqualTo(teachCycle.getId());
            List<RefClassRoom> refClassRoomList = refClassRoomMapper.selectByExample(example);
            if (refClassRoomList == null || refClassRoomList.size() != 1) {
                return ResultEntity.newErrEntity("班级和教室未匹配！");
            }
        }
        String id = getParamVal(request, "id");
        String macId = getParamVal(request, "macId");
        String classCardId = getParamVal(request, "classCardId");
        String equipmentName = getParamVal(request, "equipmentName");
        String classroom = getParamVal(request, "classroom");
        //String classSlogan = getParamVal(request, "classSlogan");
        String startBootTimeOfDay = getParamVal(request, "startBootTimeOfDay");
        String endBootTimeOfDay = getParamVal(request, "endBootTimeOfDay");
        String bootDayOfWeek = getParamVal(request, "bootDayOfWeek");
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        List<ClassCard> delClassCardList = classCardService.findDelClassCardByClassId(classId);
        //判断班级之前是否绑定过班牌
        try {
            if (delClassCardList != null && delClassCardList.size() == 1) {
                //将班级信息关联到新绑定的班牌上
                ClassCard oldClassCard = delClassCardList.get(0);
                oldClassCard.setMacId(macId);
                oldClassCard.setEquipmentName(equipmentName);
                oldClassCard.setClassroom(classroom);
                oldClassCard.setUpdateBy(user.getId());
                oldClassCard.setUpdateDate(System.currentTimeMillis());
                oldClassCard.setDelFlag(0);
                classCardService.updateClassCard(oldClassCard);
                //删除导入的mac记录
                classCardService.deleteClassCardById(classCardId);
            } else {
                ClassCard classCard = new ClassCard();
                classCard.setId(id);
                classCard.setMacId(macId);
                classCard.setEquipmentName(equipmentName);
                classCard.setTeachClassRoomId(teachClassRoomId);
                classCard.setClassId(classId);
                classCard.setClassroom(classroom);
                //classCard.setClassSlogan(classSlogan);
                classCard.setSchoolId(schoolId);
                classCard.setStartBootTimeOfDay(startBootTimeOfDay);
                classCard.setEndBootTimeOfDay(endBootTimeOfDay);
                classCard.setBootDayOfWeek(bootDayOfWeek);

                //新增
                if ("".equals(id)) {
                    //页面mac地址未点击选择!
                    if ("".equals(classCardId)) {
                        return ResultEntity.newErrEntity("请点击选择终端mac地址");
                    }
                    classCard.setId(classCardId);
                    classCard.setUpdateBy(user.getId());
                    classCard.setUpdateDate(System.currentTimeMillis());
                    classCard.setIsSynchro(1);//未推送
                    classCardService.updateClassCard(classCard);
                }
                //编辑
                else {
                    classCard.setUpdateBy(user.getId());
                    classCard.setUpdateDate(System.currentTimeMillis());
                    classCardService.updateClassCard(classCard);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultEntity.newResultEntity();
    }

    //逻辑删除班牌
    @RequestMapping(value = "multidelete")
    public ResultEntity multiDelete(HttpServletRequest request, Model model) {
        String classCardIds = getParamVal(request, "classCardIds");
        if ("".equals(classCardIds)) {
            return ResultEntity.newErrEntity("classCard id is null");
        }
        String[] idsStr = classCardIds.split(",");

        int count = 0;
        try {
            count = classCardService.deleteClassCard(Arrays.asList(idsStr));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResultEntity resultEntity = new ResultEntity();
        if (count > 0) {
            List<ClassCard> unBoundList = new ArrayList<>();
            //解绑的班牌mac新增为可选中状态
            for (String id : idsStr) {
                ClassCard classCard = classCardService.selectClassCardById(id);
                ClassCard unBoundClassCard = new ClassCard();
                unBoundClassCard.setId(PrimaryKey.get());
                unBoundClassCard.setMacId(classCard.getMacId());
                unBoundClassCard.setCreateDate(System.currentTimeMillis());
                unBoundClassCard.setCreateBy(getLoginUser().getId());
                unBoundClassCard.setDelFlag(0);
                unBoundList.add(unBoundClassCard);
            }
            try {
                classCardService.batchInsertClassCard(unBoundList);
            } catch (Exception e) {
                e.printStackTrace();
            }

            resultEntity = ResultEntity.newResultEntity(ResultEntity.OK_CODE, "删除成功", null);
        } else {
            resultEntity = ResultEntity.newErrEntity();
        }
        return resultEntity;
    }

    /**
     * mac导入模板下载
     *
     * @param response
     * @return
     */
    @RequestMapping(value = "/classcardtemplate")
    public String classCardTemplate(HttpServletResponse response) {
        try {
            String fileName = "班牌mac地址导入模板.xlsx";
            List<ClassCard> list = Lists.newArrayList();
            String anno = "*mac地址不能为空\n";
            new ExportExcel(false, "班牌mac地址数据", IOClassCardView.class, 2, anno, 1).setDataList(list).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @RequestMapping(value = "/fileimport")
    public String fileImport(HttpServletRequest request, Model model) {
        String submitUrl = getParamVal(request, "url");
        model.addAttribute("url", submitUrl);
        return "classCard/macImport";
    }

    //导入ｍａｃ
    @ResponseBody
    @RequestMapping(value = "/mac/import", method = RequestMethod.POST)
    public ResponseEntity macImport(@RequestParam(value = "file") MultipartFile file) {
        Long begin = System.currentTimeMillis();
        List<ClassCard> classCardList = new ArrayList<ClassCard>();
        List<IOClassCardView> errorClassCardList = new ArrayList<>();
        IOClassCardView errorClassCard = new IOClassCardView();
        ImportExcel importExcel = null; //从2开始
        try {
            importExcel = new ImportExcel(file, 2, 0);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<IOClassCardView> list = new ArrayList<>();
        try {
            list = importExcel.getDataList(IOClassCardView.class, 1);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Map<String, Integer> countMap = new HashMap<>();
        for (IOClassCardView icv : list) {
            try {
                errorClassCard = icv;
                if (icv.getMacId() == null || icv.getMacId() == "") {
                    throw new ErrcodeException("mac地址为空");
                }
                ClassCard isExiest = classCardService.selectClassCardByMacId(icv.getMacId());
                if (isExiest != null) {
                    throw new ErrcodeException("mac地址已存在");
                }

                //处理导入数据中有重复mac
                Integer i = countMap.get(icv.getMacId());
                if (null == i) {
                    countMap.put(icv.getMacId(), 1);
                } else {
                    countMap.put(icv.getMacId(), countMap.get(icv.getMacId()) + 1);
                }
                if (countMap.get(icv.getMacId()) > 1) {
                    throw new ErrcodeException("mac地址已存在");
                }

                ClassCard classCard = new ClassCard();
                classCard.setId(PrimaryKey.get());
                classCard.setMacId(icv.getMacId());
                classCard.setCreateDate(System.currentTimeMillis());
                classCard.setCreateBy(getLoginUser().getId());
                classCard.setDelFlag(0);
                classCardList.add(classCard);
            } catch (Exception e) {
                errorClassCardList.add(errorClassCard);
                e.printStackTrace();
                continue;
            }
        }
        if (classCardList.size() > 0) {
            try {
                classCardService.batchInsertClassCard(classCardList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Long end = System.currentTimeMillis();
        Map res = new HashMap();
        res.put("msg", "导入完成，共" + classCardList.size() + "条成功，" + errorClassCardList.size() + "条失败,耗时" + (end - begin) / 1000 + "秒");
        res.put("errorList", errorClassCardList);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    //下载导入失败的classCard列表
    @RequestMapping(value = "/error/export", method = RequestMethod.POST)
    public void errorClassCard(HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileName = "错误信息列表.xlsx";
            String anno = "*mac地址为空或已存在\n";
            String msg = getParamVal(request, "msg");
            JsonArray jsonArray = new JsonParser().parse(msg).getAsJsonArray();
            List<IOClassCardView> exportFile = new ArrayList<IOClassCardView>();
            for (JsonElement jsonElement : jsonArray) {
                IOClassCardView importBundling = GsonUtil.fromJson(jsonElement.getAsJsonObject(), IOClassCardView.class);
                exportFile.add(importBundling);
            }
            new ExportExcel(false, "班牌信息", IOClassCardView.class, 2, anno, 1).setDataList(exportFile).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 班牌模式首页
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/modetype/index")
    public String classCardModeIndex(HttpServletRequest request, Model model) {
        PageInfo<ClassCardMode> pageInfo = classCardService.selectAllUnDelMode();
        model.addAttribute("pageInfo", pageInfo);
        return null;
    }

    /**
     * 编辑模式
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/modetype/edit")
    public String editClassCardMode(HttpServletRequest request, Model model) {
        String id = getParamVal(request, "id");
        ClassCardMode classCardMode = classCardService.selectModeById(id);
        model.addAttribute("classCardMode", classCardMode);
        return null;
    }

    /**
     * 班牌模式保存
     *
     * @param request
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/modetype/save")
    public ResultEntity saveClassCardMode(HttpServletRequest request, Model model) {
        String id = getParamVal(request, "id");
        String title = getParamVal(request, "title");
        String type = getParamVal(request, "type");
        String time_start = getParamVal(request, "time_start");
        String time_end = getParamVal(request, "time_end");
        String content = getParamVal(request, "content");
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        ClassCardMode classCardMode = new ClassCardMode();
        classCardMode.setContent(content);
        classCardMode.setId(id);
        classCardMode.setTitle(title);
        classCardMode.setType(null);
        classCardMode.setTimeStart(null);
        classCardMode.setTimeEnd(null);
        classCardMode.setSchoolId(user.getSchoolId());

        try {
            if (!"".equals(id)) {
                classCardMode.setUpdateBy(user.getId());
                classCardMode.setUpdateDate(System.currentTimeMillis());
                classCardService.updateMode(classCardMode);
            } else {
                classCardMode.setId(PrimaryKey.get());
                classCardMode.setCreateBy(user.getId());
                classCardMode.setCreateDate(System.currentTimeMillis());
                classCardService.insertMode(classCardMode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultEntity.newResultEntity();
    }

    /**
     * 逻辑删除班牌模式
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/modetype/delete")
    public ResultEntity deleteMode(HttpServletRequest request) {
        String id = getParamVal(request, "id");
        if (!"".equals(id)) {
            ClassCardMode classCardMode = classCardService.selectModeById(id);
            classCardMode.setDelFlag(1);
            classCardService.updateMode(classCardMode);
        }
        return null;

    }

    /**
     * 校园文化首页
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "schoolculture", method = RequestMethod.GET)
    public String schoolCulture(HttpServletRequest request, Model model) {

        SchoolCulture schoolCulture = classCardService.findSchoolCultureBySchoolId(getLoginUser().getSchoolId());

        if (StringUtils.isNotBlank(schoolCulture.getSchoolBadgePicId())) {
            ClassSpacePicture schoolBadgePic = classSpacePictureService.findPicById(schoolCulture.getSchoolBadgePicId());
            model.addAttribute("schoolBadgePicName", schoolBadgePic.getPicName());
        }
        if (StringUtils.isNotBlank(schoolCulture.getVideoId())) {
            ClassSpaceVideo video = classSpaceVideoService.findById(schoolCulture.getVideoId());
            model.addAttribute("videoName", video.getVideoName());
        }

       /* if (StringUtils.isNotBlank(schoolCulture.getSchoolPicId())) {
            ClassSpacePicture schoolPic = classSpacePictureService.findPicById(schoolCulture.getSchoolPicId());
            List<ClassSpacePicture> pictures = classSpacePictureService.findPicByPid(schoolPic.getId());
            List<String> picNames = new ArrayList<>();
            for (ClassSpacePicture classSpacePicture : pictures) {
                picNames.add(classSpacePicture.getPicName());
            }
            model.addAttribute("picNames", picNames);
        }*/

        model.addAttribute("schoolCulture", schoolCulture);
        return "classCard/schoolculture/index";
    }

    @ResponseBody
    @RequestMapping(value = "deleteschoolpic")
    public ResultEntity deleteSchoolPic(HttpServletRequest request) {
        String id_urls = getParamVal(request, "id_urls");
        JsonArray jsonArray = new JsonParser().parse(id_urls).getAsJsonArray();
        for (JsonElement jsonElement : jsonArray) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String realPath = FileUtils.VFS_ROOT_PATH;
            //删除文件
            new File(realPath + jsonObject.get("url").getAsString()).delete();
            //删除记录
            String picId = jsonObject.get("id").getAsString();
            if (StringUtils.isNotBlank(picId)) {
                try {
                    classSpacePictureService.deletePicture(picId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return ResultEntity.newResultEntity();
    }

    /**
     * 保存校园文化编辑
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "schoolculture", method = RequestMethod.POST)
    public ResultEntity modifySchoolCulture(HttpServletRequest request) {

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        String schoolId = user.getSchoolId();
        String introduction = getParamVal(request, "introduction");
        String url_name = getParamVal(request, "url_name");
        String schoolBadgePicUrl = getParamVal(request, "schoolBadgePicUrl");
        String schoolBadgePicName = getParamVal(request, "schoolBadgePicName");
        String schoolBadgePicThumbnailUrl = getParamVal(request, "schoolBadgePicThumbnailUrl");
        String schoolVideoUrl = getParamVal(request, "schoolVideoUrl");
        String schoolVideoName = getParamVal(request, "schoolVideoName");

        SchoolCulture schoolCulture = classCardService.findSchoolCultureBySchoolId(schoolId);

        schoolCulture.setIntroduction(introduction);

        //校徽
        if (StringUtils.isNotBlank(schoolBadgePicUrl)) {
            ClassSpacePicture badgePic = new ClassSpacePicture();
            badgePic.setId(PrimaryKey.get());
            badgePic.setPicUrl(schoolBadgePicUrl);
            badgePic.setPicName(schoolBadgePicName);
            badgePic.setCreateDate(System.currentTimeMillis());
            badgePic.setCreateBy(user.getId());
            badgePic.setSchoolId(user.getSchoolId());
            if (Boolean.parseBoolean(schoolBadgePicThumbnailUrl)) {
                badgePic.setThumbnailUrl(classSpacePictureService.getThumbnail(schoolBadgePicUrl));
            }
            classSpacePictureService.insertPictrue(badgePic);
            schoolCulture.setSchoolBadgePicId(badgePic.getId());
            //todo  删除之前校徽
        }

        //视频
        if (StringUtils.isNotBlank(schoolVideoUrl)) {
            ClassSpaceVideo video = new ClassSpaceVideo();
            video.setId(PrimaryKey.get());
            video.setVideoName(schoolVideoName);
            video.setVideoUrl(schoolVideoUrl);
            video.setCreateBy(user.getId());
            video.setCreateDate(System.currentTimeMillis());
            video.setSchoolId(user.getSchoolId());
            if (classSpaceVideoService.insertVideo(video)) {
                schoolCulture.setVideoId(video.getId());
            }
            //todo 删除之前的视频
        }

        //有新增的校园图片
        if (StringUtils.isNotBlank(url_name)) {
            String schoolPicId = schoolCulture.getSchoolPicId();

            //之前未保存过校园图片
            if (StringUtils.isBlank(schoolPicId)) {
                //校园文化中的图片id（相当于一个集合）
                ClassSpacePicture picture = new ClassSpacePicture();
                picture.setId(PrimaryKey.get());
                picture.setCreateBy(getLoginUser().getId());
                picture.setCreateDate(System.currentTimeMillis());
                picture.setSchoolId(getLoginUser().getSchoolId());
                int count = classSpacePictureService.insertPictrue(picture);
                if (count == 1) {
                    schoolCulture.setSchoolPicId(picture.getId());
                } else {
                    return ResultEntity.newErrEntity();
                }
            }

            JsonArray jsonArray = new JsonParser().parse(url_name).getAsJsonArray();
            for (JsonElement jsonElement : jsonArray) {
                JsonObject fileObject = jsonElement.getAsJsonObject();
                String url = fileObject.get("url").getAsString();
                String name = fileObject.get("name").getAsString();
                boolean thumbnail = fileObject.get("thumbnail").getAsBoolean();

                //校园文化中的具体校园图片的实体
                ClassSpacePicture schoolPic = new ClassSpacePicture();

                schoolPic.setId(PrimaryKey.get());
                schoolPic.setPid(schoolCulture.getSchoolPicId());
                schoolPic.setPicUrl(url);
                schoolPic.setPicName(name);
                schoolPic.setSchoolId(getLoginUser().getSchoolId());
                schoolPic.setCreateBy(getLoginUser().getId());
                schoolPic.setCreateDate(System.currentTimeMillis());
                if (thumbnail) {
                    schoolPic.setThumbnailUrl(classSpacePictureService.getThumbnail(url));
                }
                classSpacePictureService.insertPictrue(schoolPic);
            }
        }
        //插入
        if (StringUtils.isBlank(schoolCulture.getId())) {
            schoolCulture.setId(PrimaryKey.get());
            schoolCulture.setCreateDate(System.currentTimeMillis());
            schoolCulture.setCreateBy(user.getId());
            schoolCulture.setSchoolId(user.getSchoolId());
            classCardService.insertSchoolCulture(schoolCulture);
            //更新
        } else {
            schoolCulture.setUpdateBy(user.getId());
            schoolCulture.setUpdateDate(System.currentTimeMillis());
            classCardService.updateSchoolCulture(schoolCulture);
        }
        return ResultEntity.newResultEntity();
    }

    /**
     * 校园文化-校园图片
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "schoolculture/pics")
    public String schoolculturePics(HttpServletRequest request, Model model) {
        SchoolCulture schoolCulture = classCardService.findSchoolCultureBySchoolId(getLoginUser().getSchoolId());

        PageInfo<ClassSpacePicture> pageInfo = new PageInfo<>();
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);

        if (StringUtils.isNotBlank(schoolCulture.getSchoolPicId())) {
            ClassSpacePicture schoolPic = classSpacePictureService.findPicById(schoolCulture.getSchoolPicId());
            pageInfo = classSpacePictureService.findPicByPid_((null == schoolPic || null == schoolPic.getId()) ? "" : schoolPic.getId(), pageNum, pageSize);
        }
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("pageNum", pageNum);
        return "classCard/schoolculture/pics";
    }
}