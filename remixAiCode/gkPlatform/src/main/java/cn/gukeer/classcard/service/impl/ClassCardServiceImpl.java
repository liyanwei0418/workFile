package cn.gukeer.classcard.service.impl;

import cn.gukeer.classcard.modelView.ClassCardView;
import cn.gukeer.classcard.modelView.ClassIntroductionView;
import cn.gukeer.classcard.persistence.dao.*;
import cn.gukeer.classcard.persistence.entity.*;
import cn.gukeer.classcard.service.ClassCardService;
import cn.gukeer.common.utils.NumberConvertUtil;
import cn.gukeer.platform.common.ConstantUtil;
import cn.gukeer.platform.common.UserRoleType;
import cn.gukeer.platform.modelView.*;
import cn.gukeer.platform.persistence.dao.*;
import cn.gukeer.platform.persistence.entity.*;
import cn.gukeer.platform.service.TeachTaskService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by alpha on 17-6-26.
 */
@Service
public class ClassCardServiceImpl implements ClassCardService {
    @Autowired
    ClassCardModeMapper classCardModeMapper;

    @Autowired
    ClassCardMapper classCardMapper;

    @Autowired
    A_ClassRoomMapper a_classRoomMapper;

    @Autowired
    GradeClassMapper gradeClassMapper;

    @Autowired
    A_ClassCardMapper a_classCardMapper;

    @Autowired
    SchoolMapper schoolMapper;

    @Autowired
    SchoolTypeMapper schoolTypeMapper;

    @Autowired
    ClassSectionMapper classSectionMapper;

    @Autowired
    ClassIntroductionMapper classIntroductionMapper;

    @Autowired
    A_ClassIntroductionMapper a_classIntroductionMapper;

    @Autowired
    TeachTaskService teachTaskService;

    @Autowired
    A_GradeClassMapper a_GradeClassMapper;

    @Autowired
    SchoolCultureMapper schoolCultureMapper;

    @Override
    public int insertClassCard(ClassCard classCard) {
        int count = classCardMapper.insert(classCard);
        return count;
    }

    @Override
    public int updateClassCard(ClassCard classCard) {
        ClassCardExample example = new ClassCardExample();
        example.createCriteria().andIdEqualTo(classCard.getId());
        int count = classCardMapper.updateByExampleSelective(classCard, example);
        return count;
    }

    @Override
    public ClassCard selectClassCardById(String id) {
        ClassCard classCard = classCardMapper.selectByPrimaryKey(id);
        return classCard;
    }

    @Override
    public PageInfo<ClassCardView> selectClassCardByChoose(String sid, String cid, String xd, int nj, String xq, int pageNum, int pageSize) {

        if (pageSize != -1) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<String> cidList = new ArrayList<>();
        if (StringUtils.isNotEmpty(cid) && cid.contains(",")) {
            String[] classIdArray = cid.split(",");
            cidList = Arrays.asList(classIdArray);
        }
        List<ClassCardView> views = a_classCardMapper.selectByClassIdAndNj(cidList, sid, xd, nj, xq);
        PageInfo<ClassCardView> pageInfo = new PageInfo<ClassCardView>(views);

        return pageInfo;
    }

    @Override
    public Map<String, Object> selectCascadeClassRoom(String condition, String schoolId) {
        JsonObject params = new JsonParser().parse(condition).getAsJsonObject();
        String flag = params.get("flag").getAsString();
        String xqId = params.get("xqId").getAsString();
        String teachBuilding = params.get("teachBuilding").getAsString();
        String floor = params.get("floor").getAsString();


        List<ClassRoom> classRooms = a_classRoomMapper.findCascadeClassRoom(flag, xqId, teachBuilding, floor, schoolId);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("classRomms", classRooms);
        resultMap.put("flag", flag);

        return resultMap;
    }

    @Override
    public List<Map<String, String>> selectXdBySchool(String schoolId) {
        List<GradeClass> gradeClasses = gradeClassMapper.selectXdBySchoolId(schoolId);
        List<Map<String, String>> list = new ArrayList<>();
        for (GradeClass gc : gradeClasses) {
            Map<String, String> map = new HashMap<>();
            map.put("xd", gc.getName());
            map.put("xdId", gc.getXd());
            list.add(map);
        }
        return list;
    }

    @Override
    public Map<String, Object> selectCascadeClass(String condition, String schoolId) {
        JsonObject params = new JsonParser().parse(condition).getAsJsonObject();
        String flag = params.get("flag").getAsString();
        String xdId = params.get("xdId").getAsString();
        String nj = params.get("nj").getAsString();
        List<GradeClass> gradeClasses = gradeClassMapper.selectClassCascade(flag, xdId, nj, schoolId);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("gradeClasses", gradeClasses);
        resultMap.put("flag", flag);
        return resultMap;
    }

    @Override
    public int deleteClassCard(List<String> ids) {
        ClassCardExample example = new ClassCardExample();
        example.createCriteria().andIdIn(ids).andDelFlagEqualTo(0);
        int count = 0;
        List<ClassCard> classCards = classCardMapper.selectByExample(example);
        for (ClassCard cc : classCards) {
            cc.setDelFlag(1);
            count += classCardMapper.updateByPrimaryKey(cc);
        }
        if (count != ids.size()) {
            count = -1;
        }
        return count;
    }

    @Override
    public int deleteClassCardById(String classCardId) {
        int count = classCardMapper.deleteByPrimaryKey(classCardId);
        return count;
    }

    @Override
    public ClassCard selectClassCardByTeachClassRoomId(String teachClassRoomId) {
        ClassCardExample example = new ClassCardExample();
        example.createCriteria().andTeachClassRoomIdEqualTo(teachClassRoomId).andDelFlagEqualTo(0);
        List<ClassCard> classCards = classCardMapper.selectByExample(example);
        ClassCard classCard = null;
        if (classCards != null && classCards.size() > 0) {
            classCard = classCards.get(0);
        }
        return classCard;
    }

    @Override
    public int selectClassCardByClassIdAndCardId(String classId, String classCardId) {
        ClassCardExample example = new ClassCardExample();
        example.createCriteria().andClassIdEqualTo(classId).andIdEqualTo(classCardId).andDelFlagEqualTo(0);
        List<ClassCard> classCards = classCardMapper.selectByExample(example);
        int count = 0;
        if (classCards != null && classCards.size() > 0) {
            count = classCards.size();
        }
        return count;
    }

    @Override
    public int selectClassCardByRoomIdAndCardId(String roomId, String classCardId) {
        ClassCardExample example = new ClassCardExample();
        example.createCriteria().andTeachClassRoomIdEqualTo(roomId).andIdEqualTo(classCardId).andDelFlagEqualTo(0);
        List<ClassCard> classCards = classCardMapper.selectByExample(example);
        int count = 0;
        if (classCards != null && classCards.size() > 0) {
            count = classCards.size();
        }
        return count;
    }

    @Override
    public ClassCard selectClassCardByClassId(String classId) {
        ClassCardExample example = new ClassCardExample();
        example.createCriteria().andClassIdEqualTo(classId).andDelFlagEqualTo(0);
        List<ClassCard> classCards = classCardMapper.selectByExample(example);
        ClassCard classCard = null;
        if (classCards != null && classCards.size() > 0) {
            classCard = classCards.get(0);
        }
        return classCard;
    }

    @Override
    public ClassCard selectClassCardByMacId(String macId) {
        ClassCardExample example = new ClassCardExample();
        example.createCriteria().andMacIdEqualTo(macId).andDelFlagEqualTo(0);
        List<ClassCard> classCards = classCardMapper.selectByExample(example);
        ClassCard classCard = null;
        if (classCards != null && classCards.size() > 0) {
            classCard = classCards.get(0);
        }
        return classCard;
    }

    @Override
    public Integer batchInsertClassCard(List<ClassCard> classCardList) {
        int count = a_classCardMapper.insertClassCardBatch(classCardList);
        return count;
    }

    @Override
    public List<ClassCard> findUnboundClassCard() {
        ClassCardExample example = new ClassCardExample();
        example.createCriteria().andClassIdIsNull().andTeachClassRoomIdIsNull().andDelFlagEqualTo(0);
        List<ClassCard> classCardList = classCardMapper.selectByExample(example);
        return classCardList;
    }

    @Override
    public List<ClassCard> findClassCardByClassIds(List<String> classIds) {
        List<ClassCard> classCardList = new ArrayList<>();
        if (classIds != null && classIds.size() > 0) {
            ClassCardExample example = new ClassCardExample();
            example.createCriteria().andClassIdIn(classIds).andDelFlagEqualTo(0);
            classCardList = classCardMapper.selectByExample(example);
        }
        return classCardList;
    }

    @Override
    public List<ClassCard> findDelClassCardByClassId(String classId) {
        ClassCardExample example = new ClassCardExample();
        example.createCriteria().andClassIdEqualTo(classId).andDelFlagEqualTo(1);
        List<ClassCard> classCardList = classCardMapper.selectByExample(example);
        return classCardList;
    }

    @Override
    public PageInfo<ClassCardView> findFreeClass(String schoolId, int pageNum, int pageSize) {
        if (pageSize != -1) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<ClassCardView> classCardViews = a_classCardMapper.selectFreeClass(schoolId);
        PageInfo pageInfo = new PageInfo(classCardViews);
        return pageInfo;
    }

    @Override
    public PageInfo<ClassCardMode> selectAllUnDelMode() {
        ClassCardModeExample example = new ClassCardModeExample();
        example.createCriteria().andDelFlagEqualTo(0);

        List<ClassCardMode> modes = classCardModeMapper.selectByExample(example);
        List<ClassCardMode> resultList = delOutDateMode(modes);

        PageInfo<ClassCardMode> page = new PageInfo<>(resultList);
        return page;
    }

    @Override
    public ClassCardMode selectModeById(String id) {
        ClassCardModeExample example = new ClassCardModeExample();
        example.createCriteria().andIdEqualTo(id);
        ClassCardMode classCardMode = classCardModeMapper.selectByPrimaryKey(id);
        return classCardMode;
    }

    @Override
    public int updateMode(ClassCardMode classCardMode) {
        ClassCardModeExample example = new ClassCardModeExample();
        example.createCriteria().andIdEqualTo(classCardMode.getId());
        int count = classCardModeMapper.updateByExampleSelective(classCardMode, example);
        return count;
    }

    @Override
    public int insertMode(ClassCardMode classCardMode) {
        int count = classCardModeMapper.insert(classCardMode);
        return count;
    }

    @Override
    public List<ClassCardMode> delOutDateMode(List<ClassCardMode> classCardModes) {
        List<ClassCardMode> resultList = new ArrayList<>();
        if (classCardModes != null && classCardModes.size() > 0) {
            for (ClassCardMode ccm : classCardModes) {
                if (ccm.getTimeEnd() < System.currentTimeMillis()) {
                    ccm.setDelFlag(1);
                } else {
                    resultList.add(ccm);
                }
            }
        }
        return resultList;
    }

    @Override
    public Map<String, Map<String, List<ClassCardView>>> selectEquipmentForNotify() {
        List<ClassCardView> classCardViews = new ArrayList<>();
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        if (subject.isAuthenticated()) {
            if (subject.hasRole(UserRoleType.ROLE_CLASSCARDADMIN) || subject.hasRole(UserRoleType.ROLE_SCHOOLADMIN)) {
                classCardViews = a_classCardMapper.selectAllClassCardView(null, user.getSchoolId());
            } else if (subject.hasRole(UserRoleType.ROLE_HEADTEACHER)) {
                String teacherId = user.getRefId();
                List<TeacherClass> teacherClasses = teachTaskService.findAllByTeacherId(teacherId);
                List<String> classIds = new ArrayList<>();
                if (teacherClasses != null && teacherClasses.size() > 0) {
                    for (TeacherClass tc : teacherClasses) {
                        classIds.add(tc.getClassId());
                    }
                }
                classCardViews = a_classCardMapper.selectAllClassCardView(classIds, user.getSchoolId());
            }
        }

        Map<String, Map<String, List<ClassCardView>>> resultMap = new HashMap<>();
        for (ClassCardView ccv : classCardViews) {
            //学段
            if (resultMap.get(ccv.getXd()) == null) {
                Map<String, List<ClassCardView>> njMap = new HashMap<>();
                List<ClassCardView> list = new ArrayList<>();
                njMap.put(ccv.getNj(), list);
                resultMap.put(ccv.getXd(), njMap);
            }
            //年级
            Map<String, List<ClassCardView>> stringListMap = resultMap.get(ccv.getXd());
            if (stringListMap.get(ccv.getNj()) != null) {
                List<ClassCardView> views = stringListMap.get(ccv.getNj());
                views.add(ccv);
                stringListMap.put(ccv.getNj(), views);
            } else {
                List<ClassCardView> list = new ArrayList<>();
                list.add(ccv);
                stringListMap.put(ccv.getNj(), list);
            }
        }
        return resultMap;
    }

    @Override
    public SchoolView selectAndMakeTree(String schoolId, String[] judge, boolean showOther) {
        //树节点
        SchoolView schoolView = new SchoolView();
        //容器
        //学段容器
        List<ClassSectionView> sectionViewList = new ArrayList<ClassSectionView>();

        //初始化schoolView
        School school = schoolMapper.selectByPrimaryKey(schoolId);
        schoolView.setId("school_" + schoolId);
        if (school != null) {
            schoolView.setName(school.getName());
        } else schoolView.setName("无相应学校");
        schoolView.setPid(-1);
        //学段查询条件
        ClassSectionExample xueduanexample = new ClassSectionExample();
        //班级查询条件

        xueduanexample.createCriteria().andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0);
        //得到学段集合
        List<ClassSection> classSectionList = classSectionMapper.selectByExample(xueduanexample);

        //校区
        SchoolTypeExample example = new SchoolTypeExample();
        example.createCriteria().andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0);
        List<SchoolType> schoolTypeList = schoolTypeMapper.selectByExample(example);

        for (ClassSection classSection : classSectionList) {//----------


            Subject subject = SecurityUtils.getSubject();
            User user = (User) subject.getPrincipal();

            if (subject.isAuthenticated()) {
                //是班主任则只操作自己班班牌
                if (subject.hasRole(UserRoleType.ROLE_HEADTEACHER) && !subject.hasRole(UserRoleType.ROLE_CLASSCARDADMIN) && !subject.hasRole(UserRoleType.ROLE_SCHOOLADMIN)) {
                    String teacherId = user.getRefId();
                    List<TeacherClass> teacherClasses = teachTaskService.findAllByTeacherId(teacherId);
                    //班级的id
                    List<String> classIds = new ArrayList<>();
                    StringBuilder classIdsSB = new StringBuilder();
                    //学段id 的map
                    Map<String, String> classSectionIdMap = new HashMap<>();
                    List<GradeClass_view> GradeClass_views = a_GradeClassMapper.findXdIdByClassIds(classIds);
                    //校区id的list
                    List<String> xqIds = new ArrayList<>();
                    for (GradeClass_view gv : GradeClass_views) {
                        xqIds.add(gv.getXq());
                    }

                    for (TeacherClass tc : teacherClasses) {
                        GradeClass gradeClass = gradeClassMapper.selectByPrimaryKey(tc.getClassId());
                        if (null != gradeClass) {
                            classSectionIdMap.put(gradeClass.getXd(), "");
                            classIds.add(gradeClass.getId());
                            classIdsSB.append(gradeClass.getId()).append(",");
                        }
                    }

                    if (classSectionIdMap.containsKey(classSection.getId())) {
                        //初始化学段集合
                        //年级容器
                        ClassSectionView sectionView = new ClassSectionView();
                        List<SchoolTypeView> schoolTypeViews = new ArrayList<SchoolTypeView>();
                        sectionView.setId("school_" + schoolId + "section_" + classSection.getId());
                        sectionView.setName(classSection.getName());
                        sectionView.setPid("school_" + schoolId);

                        //获得班主任班级的校区
                        SchoolTypeExample example2 = new SchoolTypeExample();
                        example2.createCriteria().andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0).andIdIn(xqIds);
                        schoolTypeList = schoolTypeMapper.selectByExample(example2);

                        for (SchoolType schoolType : schoolTypeList) {
//                          ArrayList<SchoolTypeView> schoolTypeViews = new ArrayList<SchoolTypeView>();
                            ArrayList<GradeClassView> njlist = new ArrayList<GradeClassView>();
                            SchoolTypeView schoolTypeView = new SchoolTypeView();
                            schoolTypeView.setPid(sectionView.getId());
                            schoolTypeView.setId(sectionView.getId() + "xq_" + schoolType.getId());
                            schoolTypeView.setName(schoolType.getName());
                            schoolTypeView.setSort(schoolType.getSort());
                            GradeClassExample njexample = new GradeClassExample();
                            njexample.createCriteria().andXdEqualTo(classSection.getId()).andDelFlagEqualTo(0).andXqEqualTo(schoolType.getId().toString()).andSchoolIdEqualTo(schoolId).andIdIn(classIds);
                            njexample.setOrderByClause("nj");
                            List<GradeClass> njList = gradeClassMapper.selectByExample(njexample);
                            for (int k = 0; k < njList.size() - 1; k++) {
                                for (int l = njList.size() - 1; l > k; l--) {
                                    if (njList.get(l).getNj().equals(njList.get(k).getNj())) {
                                        njList.remove(l);
                                    }
                                }
                            }

                            for (int i = 0; i < njList.size(); i++) {
                                ArrayList<BanjiView> bjlist = new ArrayList<BanjiView>();
                                GradeClass gradeClass = njList.get(i);
                                GradeClassView classView = new GradeClassView();
                                classView.setTid("school_" + schoolId + "section_" + classSection.getId() + "xq_" + schoolType.getId() + "nianji" + gradeClass.getNj());
                                classView.setNjname(ConstantUtil.getValueByKeyAndFlag(gradeClass.getNj(), "nj"));
                                classView.setPid("school_" + schoolId + "section_" + classSection.getId() + "xq_" + schoolType.getId());

                                PageInfo<ClassCardView> pageInfo = selectClassCardByChoose(schoolId, classIdsSB.toString(), gradeClass.getXd()
                                        , gradeClass.getNj(), gradeClass.getXq(), 0, 20);
                                List<ClassCardView> bjList = pageInfo.getList();

                                //得到年级下的班级班牌集合
                                for (int m = 0; m < bjList.size(); m++) {
                                    BanjiView banjiView = new BanjiView();
                                    // GradeClass banji = bjList.get(m);

                                    ClassCardView classCardView = bjList.get(m);

                                    if (judge.length == 4) {
                                        if (classCardView.getClassId() == judge[0]) {
//                                           banjiView.setOpen(true);
                                            classView.setOpen(true);
                                            sectionView.setOpen(true);
                                            schoolTypeView.setOpen(true);
                                        } else if (classCardView.getXd() == judge[1] && classCardView.getXq() == judge[2] && classCardView.getNj() == judge[3]) {
//                                           classView.setOpen(true);
                                            sectionView.setOpen(true);
                                            schoolTypeView.setOpen(true);
                                        } else if (classCardView.getXd() == judge[1] && classCardView.getXq() == judge[2]) {
                                            sectionView.setOpen(true);
//                                           schoolTypeView.setOpen(true);
                                        } else if (classCardView.getXd() == judge[1]) {
//                                          sectionView.setOpen(true);
                                        }
                                    }
                                    if (judge.length == 3)
                                        if (classCardView.getXd() == judge[0] && classCardView.getNj() == judge[2] && classCardView.getXq() == judge[1]) {
                                            banjiView.setOpen(true);
                                            classView.setOpen(true);
                                            sectionView.setOpen(true);
                                            schoolTypeView.setOpen(true);
                                        }

                                    banjiView.setId("classCard" + classCardView.getId());
                                    banjiView.setName(classCardView.getEquipmentName());
                                    banjiView.setPid("school_" + schoolId + "section_" + classSection.getId() + "xq_" + schoolType.getId() + "nianji" + gradeClass.getNj());
                                    if (judge.length == 4)
                                        bjlist.add(banjiView);
                                }

                                classView.setBanjiview(bjlist);
                                njlist.add(classView);

                            }
                            if (njList.size() == 0 && classSection.getId() == judge[1] && schoolType.getId() == judge[2]) {
                                sectionView.setOpen(true);
                                schoolTypeView.setOpen(true);
                            }
                            //年级排序
                            if (njlist.size() != 0)
                                for (int i = 0; i < njlist.size() - 1; i++) {
                                    for (int j = i + 1; j < njlist.size(); j++) {
                                        int left = NumberConvertUtil.convertS2I(njlist.get(i).getTid().substring(njlist.get(i).getTid().length() - 1));
                                        int right = NumberConvertUtil.convertS2I(njlist.get(j).getTid().substring(njlist.get(j).getTid().length() - 1));
                                        if (left > right) {
                                            GradeClassView temp = njlist.get(i);
                                            njlist.set(i, njlist.get(j));
                                            njlist.set(j, temp);
                                        }
                                    }
                                }
                            schoolTypeView.setNjview(njlist);
                            schoolTypeViews.add(schoolTypeView);
                        }
                        //校区排序
                        if (schoolTypeViews.size() != 0)
                            for (int i = 0; i < schoolTypeViews.size() - 1; i++) {
                                for (int j = i + 1; j < schoolTypeViews.size(); j++) {
                                    int left = schoolTypeViews.get(i).getSort();
                                    int right = schoolTypeViews.get(j).getSort();
                                    if (left > right) {
                                        SchoolTypeView temp = schoolTypeViews.get(i);
                                        schoolTypeViews.set(i, schoolTypeViews.get(j));
                                        schoolTypeViews.set(j, temp);
                                    }
                                }
                            }

                        sectionView.setSchoolTypeView(schoolTypeViews);
                        sectionViewList.add(sectionView);
                        schoolView.setSections(sectionViewList);


                    }

                } else {
                    //初始化学段集合
                    //年级容器
                    ClassSectionView sectionView = new ClassSectionView();
                    List<SchoolTypeView> schoolTypeViews = new ArrayList<SchoolTypeView>();
                    sectionView.setId("school_" + schoolId + "section_" + classSection.getId());
                    sectionView.setName(classSection.getName());
                    sectionView.setPid("school_" + schoolId);
                    for (SchoolType schoolType : schoolTypeList) {
//                ArrayList<SchoolTypeView> schoolTypeViews = new ArrayList<SchoolTypeView>();
                        ArrayList<GradeClassView> njlist = new ArrayList<GradeClassView>();
                        SchoolTypeView schoolTypeView = new SchoolTypeView();
                        schoolTypeView.setPid(sectionView.getId());
                        schoolTypeView.setId(sectionView.getId() + "xq_" + schoolType.getId());
                        schoolTypeView.setName(schoolType.getName());
                        schoolTypeView.setSort(schoolType.getSort());
                        GradeClassExample njexample = new GradeClassExample();
                        njexample.createCriteria().andXdEqualTo(classSection.getId()).andDelFlagEqualTo(0).andXqEqualTo(schoolType.getId().toString()).andSchoolIdEqualTo(schoolId);
                        njexample.setOrderByClause("nj");
                        List<GradeClass> njList = gradeClassMapper.selectByExample(njexample);
                        for (int k = 0; k < njList.size() - 1; k++) {
                            for (int l = njList.size() - 1; l > k; l--) {
                                if (njList.get(l).getNj().equals(njList.get(k).getNj())) {
                                    njList.remove(l);
                                }
                            }
                        }

                        for (int i = 0; i < njList.size(); i++) {
                            ArrayList<BanjiView> bjlist = new ArrayList<BanjiView>();
                            GradeClass gradeClass = njList.get(i);
                            GradeClassView classView = new GradeClassView();
                            classView.setTid("school_" + schoolId + "section_" + classSection.getId() + "xq_" + schoolType.getId() + "nianji" + gradeClass.getNj());
//                    classView.setNjname(GradeNameUtil.getStringName(gradeClass.getNj()));
                            classView.setNjname(ConstantUtil.getValueByKeyAndFlag(gradeClass.getNj(), "nj"));
                            classView.setPid("school_" + schoolId + "section_" + classSection.getId() + "xq_" + schoolType.getId());

                            PageInfo<ClassCardView> pageInfo = selectClassCardByChoose(schoolId, "", gradeClass.getXd()
                                    , gradeClass.getNj(), gradeClass.getXq(), 0, 20);
                            List<ClassCardView> bjList = pageInfo.getList();

                            //得到年级下的班级班牌集合
                            for (int m = 0; m < bjList.size(); m++) {
                                BanjiView banjiView = new BanjiView();
                                // GradeClass banji = bjList.get(m);

                                ClassCardView classCardView = bjList.get(m);

                                if (judge.length == 4) {
                                    if (classCardView.getClassId() == judge[0]) {
//                                banjiView.setOpen(true);
                                        classView.setOpen(true);
                                        sectionView.setOpen(true);
                                        schoolTypeView.setOpen(true);
                                    } else if (classCardView.getXd() == judge[1] && classCardView.getXq() == judge[2] && classCardView.getNj() == judge[3]) {
//                                classView.setOpen(true);
                                        sectionView.setOpen(true);
                                        schoolTypeView.setOpen(true);
                                    } else if (classCardView.getXd() == judge[1] && classCardView.getXq() == judge[2]) {
                                        sectionView.setOpen(true);
//                                schoolTypeView.setOpen(true);
                                    } else if (classCardView.getXd() == judge[1]) {
//                                sectionView.setOpen(true);
                                    }
                                }
                                if (judge.length == 3)
                                    if (classCardView.getXd() == judge[0] && classCardView.getNj() == judge[2] && classCardView.getXq() == judge[1]) {
                                        banjiView.setOpen(true);
                                        classView.setOpen(true);
                                        sectionView.setOpen(true);
                                        schoolTypeView.setOpen(true);
                                    }

                                banjiView.setId("classCard" + classCardView.getId());
                                banjiView.setName(classCardView.getEquipmentName());
                                banjiView.setPid("school_" + schoolId + "section_" + classSection.getId() + "xq_" + schoolType.getId() + "nianji" + gradeClass.getNj());
                                if (judge.length == 4)
                                    bjlist.add(banjiView);
                            }

                            classView.setBanjiview(bjlist);
                            njlist.add(classView);

                        }
                        if (njList.size() == 0 && classSection.getId() == judge[1] && schoolType.getId() == judge[2]) {
                            sectionView.setOpen(true);
                            schoolTypeView.setOpen(true);
                        }
                        //年级排序
                        if (njlist.size() != 0)
                            for (int i = 0; i < njlist.size() - 1; i++) {
                                for (int j = i + 1; j < njlist.size(); j++) {
                                    int left = NumberConvertUtil.convertS2I(njlist.get(i).getTid().substring(njlist.get(i).getTid().length() - 1));
                                    int right = NumberConvertUtil.convertS2I(njlist.get(j).getTid().substring(njlist.get(j).getTid().length() - 1));
                                    if (left > right) {
                                        GradeClassView temp = njlist.get(i);
                                        njlist.set(i, njlist.get(j));
                                        njlist.set(j, temp);
                                    }
                                }
                            }
                        schoolTypeView.setNjview(njlist);
                        schoolTypeViews.add(schoolTypeView);
                    }
                    //校区排序
                    if (schoolTypeViews.size() != 0)
                        for (int i = 0; i < schoolTypeViews.size() - 1; i++) {
                            for (int j = i + 1; j < schoolTypeViews.size(); j++) {
                                int left = schoolTypeViews.get(i).getSort();
                                int right = schoolTypeViews.get(j).getSort();
                                if (left > right) {
                                    SchoolTypeView temp = schoolTypeViews.get(i);
                                    schoolTypeViews.set(i, schoolTypeViews.get(j));
                                    schoolTypeViews.set(j, temp);
                                }
                            }
                        }

                    sectionView.setSchoolTypeView(schoolTypeViews);
                    sectionViewList.add(sectionView);
                    schoolView.setSections(sectionViewList);
                }
            }
        }//------------
        /*if(showOther){
            //走班班牌(其他班牌)
            ClassSectionView freeClass = new ClassSectionView();
            freeClass.setName("其他");
            freeClass.setPid("school_" + schoolId);
            freeClass.setId("school_" + schoolId + "section_" +"qt");
            sectionViewList.add(freeClass);
            schoolView.setSections(sectionViewList);
        }*/
        return schoolView;
    }

    @Override
    public ClassIntroduction selectClassIntroductionByCardId(String classCardId) {
        ClassIntroductionExample example = new ClassIntroductionExample();
        example.createCriteria().andClassCardIdEqualTo(classCardId).andDelFlagEqualTo(0);
        ClassIntroduction classIntroduction = new ClassIntroduction();
        List<ClassIntroduction> classIntroductions = classIntroductionMapper.selectByExample(example);
        if (classIntroductions != null && classIntroductions.size() > 0) {
            classIntroduction = classIntroductions.get(0);
        }
        return classIntroduction;
    }

    @Override
    public ClassIntroductionView selectViewByclassCardId(String classCardId) {
        ClassIntroductionView classIntroductionView = a_classIntroductionMapper.findIntroductionViewByClasscardId(classCardId);
        return classIntroductionView;
    }

    @Override
    public ClassCard findClassCardByClassCardId(String classCardId) {
        ClassCardExample example = new ClassCardExample();
        example.createCriteria().andIdEqualTo(classCardId).andDelFlagEqualTo(0);
        List<ClassCard> classCards = classCardMapper.selectByExample(example);
        if (classCards.size() > 0) {
            return classCards.get(0);
        }
        return null;
    }

    @Override
    public SchoolCulture findSchoolCultureBySchoolId(String schoolId) {
        SchoolCultureExample example = new SchoolCultureExample();
        example.createCriteria().andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0);
        List<SchoolCulture> schoolCultures = schoolCultureMapper.selectByExampleWithBLOBs(example);
        SchoolCulture schoolCulture = new SchoolCulture();
        if (schoolCultures != null && schoolCultures.size() > 0) {
            schoolCulture = schoolCultures.get(0);
        }
        return schoolCulture;
    }

    @Override
    public boolean insertSchoolCulture(SchoolCulture schoolCulture) {
        boolean flag = false;
        int count = schoolCultureMapper.insertSelective(schoolCulture);
        if (count == 1) {
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean updateSchoolCulture(SchoolCulture schoolCulture) {
        boolean flag = false;
        int count = schoolCultureMapper.updateByPrimaryKeySelective(schoolCulture);
        if (count == 1) {
            flag = true;
        }
        return flag;
    }

}
