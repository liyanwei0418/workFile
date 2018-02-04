package cn.gukeer.platform.service.impl;

import cn.gukeer.common.controller.BasicController;
import cn.gukeer.common.tld.GukeerStringUtil;
import cn.gukeer.common.utils.NumberConvertUtil;
import cn.gukeer.common.utils.PrimaryKey;
import cn.gukeer.platform.common.ConstantUtil;
import cn.gukeer.platform.modelView.*;
import cn.gukeer.platform.persistence.dao.*;
import cn.gukeer.platform.persistence.entity.*;
import cn.gukeer.platform.service.ClassService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by conn on 2016/8/19.
 */
@Service
public class ClassServiceImpl extends BasicController implements ClassService {

    @Autowired
    SchoolMapper schoolMapper;

    @Autowired
    GradeClassMapper gradeClassMapper;

    @Autowired
    ClassSectionMapper classSectionMapper;

    @Autowired
    A_GradeClassExtensionMapper gradeClassExtensionMapper;

    @Autowired
    A_TeacherExtensionMapper teacherExtensionMapper;

    @Autowired
    A_ClassSectionExtensionMapper classSectionExtensionMapper;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    PatriarchMapper patriarchMapper;

    @Autowired
    SchoolTypeMapper schoolTypeMapper;

    @Autowired
    A_StudentExtensionMapper studentExtensionMapper;

    @Autowired
    TeacherClassMapper teacherClassMapper;

    private String getClassSectionName(String xd, List<ClassSection> sectionList) {
        String rst = "";
        if (null == sectionList || sectionList.size() == 0) {
            return rst;
        }
        for (ClassSection section : sectionList) {
            if (section.getId() == xd) {
                rst = section.getName();
            }
        }
        return rst;
    }

    @Override
    public PageInfo<GradeClass> selectClassBySchoolIdAndNj(String schoolId, String xq, int nj, int pageNum, int pageSize, String xd) { //添加xd

        GradeClassExample example = new GradeClassExample();
        GradeClassExample.Criteria criteria = example.createCriteria();
        criteria.andSchoolIdEqualTo(schoolId);
        example.setOrderByClause("name+''");
        criteria.andXdEqualTo(xd);
        criteria.andNjEqualTo(nj);
        if (!xq.equals("0"))
            criteria.andXqEqualTo(xq);
        criteria.andDelFlagEqualTo(0);
        example.setOrderByClause("bh");
        PageHelper.startPage(pageNum, pageSize);
        Page<GradeClass> page = (Page<GradeClass>) gradeClassMapper.selectByExample(example);
        PageInfo<GradeClass> pageInfo = new PageInfo<GradeClass>(page);

        return pageInfo;
    }

    @Override
    public int saveGradeClass(GradeClass gradeClass) {
        int count = 0;
        if (null != gradeClass.getId() && gradeClass.getId() != "") {
            GradeClassExample example = new GradeClassExample();
            example.createCriteria().andIdEqualTo(gradeClass.getId());
            count = gradeClassMapper.updateByExampleSelective(gradeClass, example);
        } else {

            gradeClass.setId(PrimaryKey.get());
            count = gradeClassMapper.insert(gradeClass);
        }
        return count;
    }

    private SchoolView getSchoolView(String schoolId, List<SchoolView> schoolViewList) {
        SchoolView rst = null;
        for (SchoolView schoolView : schoolViewList) {
            if (schoolId == schoolView.getId()) {
                rst = schoolView;
                break;
            }
        }
        if (null == rst) {
            rst = new SchoolView();
            schoolViewList.add(rst);
        }
        return rst;
    }

    private ClassSectionView getClassSectionView(String xd, SchoolView schoolView) {
        ClassSectionView rst = null;
        List<ClassSectionView> classSectionViews = schoolView.getSections();
        if (null == classSectionViews) {
            classSectionViews = new ArrayList<ClassSectionView>();
            schoolView.setSections(classSectionViews);
        }

        for (ClassSectionView sectionView : classSectionViews) {
            if (xd == sectionView.getSection()) {
                rst = sectionView;
                break;
            }
        }

        if (null == rst) {
            rst = new ClassSectionView();
            classSectionViews.add(rst);
        }

        return rst;
    }

    @Override
    public ClassSection selectClassSectionById(String sectionId) {

        ClassSection classSection = classSectionMapper.selectByPrimaryKey(sectionId);

        return classSection;
    }

    @Override
    public GradeClass selectClassById(String id) {

        GradeClass gradeClass = gradeClassMapper.selectByPrimaryKey(id);

        return gradeClass;
    }

    @Override
    public List<GradeClass> selectClassByXdAndSchoolId(String xd, String schoolId) {

        GradeClassExample example = new GradeClassExample();
        example.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId).andXdEqualTo(xd);
        example.setOrderByClause("nj");
        List<GradeClass> gradeClasses = gradeClassMapper.selectByExample(example);

        return gradeClasses;
    }

    @Override
    public List<GradeClass> getClassBySchoolType(String schoolId, String schoolType) {
        GradeClassExample example = new GradeClassExample();
        example.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId).andXqEqualTo(schoolType);
        List<GradeClass> gradeClasses = gradeClassMapper.selectByExample(example);
        return gradeClasses;
    }

    @Override
    public int saveClassSection(ClassSection section) {

        int count = 0;

        if (null == section.getId() || section.getId() == "") {
            section.setId(PrimaryKey.get());
            count = classSectionMapper.insertSelective(section);
        } else {
            ClassSectionExample example = new ClassSectionExample();
            example.createCriteria().andIdEqualTo(section.getId());
            count = classSectionMapper.updateByExampleSelective(section, example);
        }

        return count;
    }

    @Override
    public int changeDelFlag(String id) {
        int count = 0;

        if (null != id || id != "")
            count = gradeClassExtensionMapper.changeDelFlag(id);
        return count;
    }

    @Override
    public List<GradeClass> getAllClassBySchoolId(String schoolId) {
        GradeClassExample example = new GradeClassExample();
        example.createCriteria().andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0);
        example.setOrderByClause("nj");
        example.setOrderByClause("bh");
        return gradeClassMapper.selectByExample(example);
    }

    @Override
    public List<ClassSection> getAllClassSectionBySchoolId(String schoolId) {
        ClassSectionExample example = new ClassSectionExample();
        example.createCriteria().andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0);
        example.setOrderByClause("section_year asc");
        example.setOrderByClause("limit_age");
        return classSectionMapper.selectByExample(example);
    }

    @Override
    public List<GradeClass> selectClassBySchoolIdXdNj(String schoolId, String xd, int nj) {
        GradeClassExample example = new GradeClassExample();
        example.setOrderByClause("name+\"\"");
        GradeClassExample.Criteria criteria = example.createCriteria();
        if (nj != 0) {
            criteria.andNjEqualTo(nj);
        }
        criteria.andSchoolIdEqualTo(schoolId).andXdEqualTo(xd).andDelFlagEqualTo(0);
        return gradeClassMapper.selectByExample(example);
    }

    @Override
    public SchoolView selectAndMakeTree(String schoolId, String[] judge,boolean showOther) {
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

        for (ClassSection classSection : classSectionList) {
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
                    GradeClassExample bjexample = new GradeClassExample();
                    bjexample.setOrderByClause("NAME+\"\"");
                    bjexample.createCriteria().andNjEqualTo(gradeClass.getNj()).andXdEqualTo(gradeClass.getXd()).andSchoolIdEqualTo(gradeClass.getSchoolId()).andDelFlagEqualTo(0).andXqEqualTo(gradeClass.getXq());
                    List<GradeClass> bjList = gradeClassMapper.selectByExample(bjexample);
                    //得到年级下的班级集合
                    for (int m = 0; m < bjList.size(); m++) {
                        BanjiView banjiView = new BanjiView();
                        GradeClass banji = bjList.get(m);
                        if (judge.length == 4) {
                            //多个班级id
                            if(!"".equals(judge[0])&&judge[0].contains(",")){
                                List<String>classIdList=new ArrayList<>();
                                if(!"".equals(judge[0])){
                                    String [] classIdArray=judge[0].split(",");
                                    classIdList=Arrays.asList(classIdArray);
                                }
                                for(String classId:classIdList) {
                                    if (banji.getId() == judge[0]) {
//                                banjiView.setOpen(true);
                                        classView.setOpen(true);
                                        sectionView.setOpen(true);
                                        schoolTypeView.setOpen(true);
                                    }
                                }
                            }else{
                                if (banji.getId() == judge[0]) {
//                                banjiView.setOpen(true);
                                    classView.setOpen(true);
                                    sectionView.setOpen(true);
                                    schoolTypeView.setOpen(true);
                                } else if (banji.getXd() == judge[1] && banji.getXq() == judge[2] && banji.getNj() == Integer.valueOf(judge[3])) {
//                                classView.setOpen(true);
                                    sectionView.setOpen(true);
                                    schoolTypeView.setOpen(true);
                                } else if (banji.getXd() == judge[1] && banji.getXq() == judge[2]) {
                                    sectionView.setOpen(true);
//                                schoolTypeView.setOpen(true);
                                } else if (banji.getXd() == judge[1]) {
//                                sectionView.setOpen(true);
                                }
                            }
                        }
                        if (judge.length == 3)
                            if (banji.getXd() == judge[0] && banji.getNj() == Integer.valueOf(judge[2]) && banji.getXq() == judge[1]) {
                                banjiView.setOpen(true);
                                classView.setOpen(true);
                                sectionView.setOpen(true);
                                schoolTypeView.setOpen(true);
                            }

                        banjiView.setId("banji" + banji.getId());
                        banjiView.setName(banji.getName());
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
        if(showOther){
            //走班班牌(其他班牌)
            ClassSectionView freeClass = new ClassSectionView();
            freeClass.setName("其他");
            freeClass.setPid("school_" + schoolId);
            freeClass.setId("school_" + schoolId + "section_" +"qt");
            sectionViewList.add(freeClass);
            schoolView.setSections(sectionViewList);
        }
        return schoolView;
    }

    @Override
    public int saveClassAndStudent(String classId, String xd, int nj) {
        StudentExample example = new StudentExample();
        example.createCriteria().andClassIdEqualTo(classId);
        List<Student> list = studentMapper.selectByExample(example);
        for (Student student : list) {
            student.setXd(xd);
            student.setNj(nj);
            studentMapper.updateByPrimaryKey(student);
        }
        return 0;
    }

    @Override
    public SchoolView makeClassTree(String schoolId, String[] judge) {
        SchoolView schoolView = new SchoolView();
        List<ClassSectionView> sectionViewList = new ArrayList<ClassSectionView>();
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

        SchoolTypeExample example = new SchoolTypeExample();
        example.createCriteria().andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0);
        List<SchoolType> schoolTypeList = schoolTypeMapper.selectByExample(example);

        for (ClassSection classSection : classSectionList) {
            //初始化学段集合
            //年级容器

            ClassSectionView sectionView = new ClassSectionView();
            List<SchoolTypeView> schoolTypeViews = new ArrayList<SchoolTypeView>();
            sectionView.setId("school_" + schoolId + "section_" + classSection.getId());
            sectionView.setName(classSection.getName());
            sectionView.setPid("school_" + schoolId);
            for (SchoolType schoolType : schoolTypeList) {
                ArrayList<GradeClassView> njlist = new ArrayList<GradeClassView>();
//                ArrayList<SchoolTypeView> schoolTypeViews = new ArrayList<SchoolTypeView>();
                SchoolTypeView schoolTypeView = new SchoolTypeView();
                schoolTypeView.setPid(sectionView.getId());
                schoolTypeView.setId(sectionView.getId() + "xq_" + schoolType.getId());
                schoolTypeView.setName(schoolType.getName());
                schoolTypeView.setSort(schoolType.getSort());

                int year = classSection.getSectionYear();
                for (int i = 1; i <= year; i++) {
                    GradeClassView classView = new GradeClassView();
                    classView.setTid("school_" + schoolId + "section_" + classSection.getId() + "xq_" + schoolType.getId() + "nianji" + i);
//                    classView.setNjname(GradeNameUtil.getStringName(i));
                    classView.setNjname(ConstantUtil.getValueByKeyAndFlag(i, "nj"));
                    classView.setPid("school_" + schoolId + "section_" + classSection.getId() + "xq_" + schoolType.getId());
                    if (judge.length == 3)
                        if (classSection.getId() == judge[0] && i == Integer.valueOf(judge[2]) && schoolType.getId() == judge[1]) {
                            classView.setOpen(true);
                            sectionView.setOpen(true);
                            schoolTypeView.setOpen(true);
                        }
                    njlist.add(classView);
                }
                schoolTypeView.setNjview(njlist);
                schoolTypeViews.add(schoolTypeView);
            }
            //校区排序
            if (schoolTypeViews.size() != 0) {
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
            }
            sectionView.setSchoolTypeView(schoolTypeViews);
            sectionViewList.add(sectionView);
            schoolView.setSections(sectionViewList);
        }
        return schoolView;
    }


    @Override
    public PageInfo<ClassSection> getSectionContainDefault(String schoolId, int pageNum, int pageSize) {

        List<String> nameList = new ArrayList<String>();
        nameList.add("小学");
        nameList.add("初中");
        nameList.add("高中");

        ClassSectionExample defaultExample = new ClassSectionExample();
        defaultExample.createCriteria().andSchoolIdEqualTo(schoolId).andNameIn(nameList);
        defaultExample.or().andSchoolIdEqualTo(schoolId).andNameNotIn(nameList).andDelFlagEqualTo(0);
//        List<ClassSection> defaultList = classSectionMapper.selectByExample(defaultExample);

        PageHelper.startPage(pageNum, pageSize);
        Page<ClassSection> page = (Page<ClassSection>) classSectionMapper.selectByExample(defaultExample);
        PageInfo<ClassSection> pageInfo = new PageInfo<ClassSection>(page);

        return pageInfo;
    }

    @Override
    public List<Teacher> getTeacherByClassId(String classId, int type) {
        List<Teacher> teachers = teacherExtensionMapper.selectByClassId(classId, type);
        return teachers;
    }

    @Override
    public int saveTeacherClass(String teahcerid, String classId, int type) {
        TeacherClass teacherClass = new TeacherClass();
        teacherClass.setClassId(classId);
        teacherClass.setType(type);
        teacherClass.setTeacherId(teahcerid);
        return teacherClassMapper.insert(teacherClass);
    }

    @Override
    public int delTeacherClass(String teahcerid, String classId, int type) {
        return teacherExtensionMapper.delTeacherClass(teahcerid, classId, type);
    }

    @Override
    public int batchInsertGradeClass(List<GradeClass> list) {
        int flag = gradeClassExtensionMapper.batchInsertGradeClass(list);
        return flag;
    }

    @Override
    public PageInfo<Map> parentInfoList(Map param) {
        if (!GukeerStringUtil.isNullOrEmpty(param.get("pageNum"))) {
            int pageNum = NumberConvertUtil.convertS2I(param.get("pageNum").toString());
            int pageSize = NumberConvertUtil.convertS2I(param.get("pageSize").toString());
            PageHelper.startPage(pageNum, pageSize);
        }

        List<Map> res = classSectionExtensionMapper.parentInfoList(param);
        PageInfo<Map> pageInfo = new PageInfo<Map>(res);
        return pageInfo;
    }

    @Override
    public int deleteParent(String prim) {
        Patriarch patriarch = new Patriarch();
        patriarch.setDelFlag(1);
        patriarch.setId(prim);
        int flag = patriarchMapper.updateByPrimaryKeySelective(patriarch);
        return flag;
    }

    @Override
    public Map selectParentByPrim(String prim) {
        Map res = classSectionExtensionMapper.selectParentByPrim(prim);
        return res;
    }

    @Override
    public PageInfo<Map> getAreaStuList(Map param) {
        if (!GukeerStringUtil.isNullOrEmpty(param.get("pageNum"))) {
            int pageNum = NumberConvertUtil.convertS2I(param.get("pageNum").toString());
            int pageSize = NumberConvertUtil.convertS2I(param.get("pageSize").toString());
            PageHelper.startPage(pageNum, pageSize);
        }

        List<Map> res = studentExtensionMapper.getAreaStuList(param);
        PageInfo<Map> pageInfo = new PageInfo<Map>(res);
        return pageInfo;
    }

}
