package cn.gukeer.platform.service.impl;

import cn.gukeer.classcard.persistence.dao.ClassCardMapper;
import cn.gukeer.classcard.persistence.entity.ClassCard;
import cn.gukeer.classcard.persistence.entity.ClassCardExample;
import cn.gukeer.common.service.BasicService;
import cn.gukeer.common.utils.NumberConvertUtil;
import cn.gukeer.common.utils.PrimaryKey;
import cn.gukeer.platform.modelView.*;
import cn.gukeer.platform.persistence.dao.*;
import cn.gukeer.platform.persistence.entity.*;

import cn.gukeer.platform.service.TeachTaskService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.codec.language.bm.Languages;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeachTaskServiceImpl extends BasicService implements TeachTaskService {

    @Autowired
    RefRoomCycleMapper refRoomCycleMapper;

    @Autowired
    TeachCycleMapper teachCycleMapper;

    @Autowired
    ClassRoomMapper classRoomMapper;

    @Autowired
    RoomTypeMapper roomTypeMapper;

    @Autowired
    CourseTypeMapper courseTypeMapper;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    GradeClassMapper gradeClassMapper;

    @Autowired
    A_CourseClassMapper a_courseClassMapper;

    @Autowired
    ClassSectionMapper classSectionMapper;

    @Autowired
    A_MasterMapper a_masterMapper;

    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    A_ClassViewMapper a_classViewMapper;

    @Autowired
    A_TeacherExtensionMapper a_teacherExtensionMapper;

    @Autowired
    CourseClassMapper courseClassMapper;

    @Autowired
    A_RefTeacherClassMapper a_refTeacherClassMapper;

    @Autowired
    TeacherClassMapper teacherClassMapper;

    @Autowired
    A_CourseMapper a_courseMapper;

    @Autowired
    SchoolTypeMapper schoolTypeMapper;

    @Autowired
    A_RefClassRoomMapper a_refClassRoomMapper;

    @Autowired
    RefClassRoomMapper refClassRoomMapper;

    @Autowired
    A_ClassRoomMapper a_classRoomMapper;

    @Autowired
    A_GradeClassMapper a_gradeClassMapper;

    @Autowired
    StandardCourseMapper standardCourseMapper;

    @Autowired
    A_StandardCourseMapper a_standardCourseMapper;

    @Autowired
    DailyHourMapper dailyHourMapper;

    @Autowired
    A_DailyHoureMapper a_dailyHoureMapper;

    @Autowired
    CourseNodeMapper courseNodeMapper;

    @Autowired
    A_CourseNodeMapper a_courseNodeMapper;

    @Autowired
    CourseNodeInitMapper courseNodeInitMapper;

    @Autowired
    A_RefRoomCycleMapper a_refRoomCycleMapper;

    @Autowired
    A_TeachTableMapper a_teachTableMapper;

    @Autowired
    UserMapper userMapper;

    @Resource
    ClassCardMapper classCardMapper;

    @Autowired
    TeachTableMapper teachTableMapper;

    @Autowired
    A_UserExtensionMapper a_userExtensionMapper;

    @Autowired
    RoleMapper roleMapper;


    @Override
    public int saveRefRoomCycle(RefRoomCycle roomCycle) {
        return refRoomCycleMapper.insertSelective(roomCycle);
    }

    @Override
    public int batchSaveRefRoomCycle(List<RefRoomCycle> list) {
        return a_classRoomMapper.batchSaveRefRoomCycle(list);
    }

    @Override
    public PageInfo<TeachCycle> getTeachCycle(Map param) {
        int pageNum = NumberConvertUtil.convertS2I(getValue(param, "pageNum").toString());
        int pageSize = NumberConvertUtil.convertS2I(getValue(param, "pageSize").toString());
        String schoolId = getValue(param, "schoolId").toString();

        PageHelper.startPage(pageNum, pageSize);
        TeachCycleExample example = new TeachCycleExample();
        example.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId);
        example.setOrderByClause("term_begin_time desc");
        List<TeachCycle> list = teachCycleMapper.selectByExample(example);
        PageInfo<TeachCycle> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    @Transactional
    public int saveTeachCycle(TeachCycle teachCycle) {
        int flag = 0;
        if (StringUtil.isEmpty(teachCycle.getId())) {
            //执行新增操作
            teachCycle.setCreateDate(System.currentTimeMillis());
            teachCycle.setDelFlag(0);
            teachCycle.setId(PrimaryKey.get());
            flag = teachCycleMapper.insertSelective(teachCycle);
        } else {
            //执行修改操作
            if (teachCycle.getDelFlag() == 1) {

                //删除班主任信息
                TeacherClassExample teacherClassExample = new TeacherClassExample();
                teacherClassExample.createCriteria().andCycleIdEqualTo(teachCycle.getId());
                teacherClassMapper.deleteByExample(teacherClassExample);

                //删除课程信息
                CourseExample courseExample = new CourseExample();
                courseExample.createCriteria().andCycleIdEqualTo(teachCycle.getId());

                //删除所有的课程班级信息 由cycleId拿到所有的课程
                List<Course> courseList = courseMapper.selectByExample(courseExample);
                List<String> courseIds = new ArrayList<>();
                if (courseList != null && courseList.size() > 0) {
                    for (Course course : courseList) {
                        courseIds.add(course.getId());
                    }
                }
                courseMapper.deleteByExample(courseExample);

                //根据课程删除所有的课程班级信息
                if(courseIds!= null&&courseIds.size()>0) {
                    CourseClassExample courseClassExample = new CourseClassExample();
                    courseClassExample.createCriteria().andCourseIdIn(courseIds);
                    courseClassMapper.deleteByExample(courseClassExample);
                }

                //删除所有的教室信息
                RefClassRoomExample refClassRoomExample = new RefClassRoomExample();
                refClassRoomExample.createCriteria().andCycleIdEqualTo(teachCycle.getId());
                List<RefClassRoom> refClassRoomList = refClassRoomMapper.selectByExample(refClassRoomExample);

                List<String> roomIds = new ArrayList<>();
                if (null != refClassRoomList && refClassRoomList.size() > 0) {
                    for (RefClassRoom refClassRoom : refClassRoomList) {
                        roomIds.add(refClassRoom.getId());
                    }

                }


                if (roomIds != null && roomIds.size() > 0) {
                    ClassRoomExample classRoomExample = new ClassRoomExample();
                    classRoomExample.createCriteria().andIdIn(roomIds);
                    classRoomMapper.deleteByExample(classRoomExample);
                }


                //删除所有的courseNodeInit信息
                CourseNodeInitExample courseNodeInitExample = new CourseNodeInitExample();
                courseNodeInitExample.createCriteria().andCycleIdEqualTo(teachCycle.getId());
                courseNodeInitMapper.deleteByExample(courseNodeInitExample);

                //删除所有的node信息
                CourseNodeExample courseNodeExample = new CourseNodeExample();
                courseNodeExample.createCriteria().andCycleIdEqualTo(teachCycle.getId());
                courseNodeMapper.deleteByExample(courseNodeExample);


                //删除所有的班级教室信息
                RefClassRoomExample refClassRoomExample1 = new RefClassRoomExample();
                refClassRoomExample.createCriteria().andCycleIdEqualTo(teachCycle.getId());
                refClassRoomMapper.deleteByExample(refClassRoomExample1);

                //删除教学周期本身
                teachCycleMapper.deleteByPrimaryKey(teachCycle.getId());
            } else {
                teachCycle.setUpdateDate(System.currentTimeMillis());
                flag = teachCycleMapper.updateByPrimaryKeySelective(teachCycle);
            }

        }
        return flag;
    }


    @Override
    public TeachCycle selectByKey(String cycleId) {
        TeachCycle teachCycle = teachCycleMapper.selectByPrimaryKey(cycleId);
        return teachCycle;
    }


    @Override
    public ClassRoom getRoomByPri(String pri) {
        return classRoomMapper.selectByPrimaryKey(pri);
    }

    @Override
    public List<TeachCycle> getCycleList(String schoolId) {

        TeachCycleExample example = new TeachCycleExample();
        example.setOrderByClause("term_begin_time desc");
        example.createCriteria().andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0);

        return teachCycleMapper.selectByExample(example);
    }

    @Override
    public TeachCycle getCycleByYearSemester(String year, Integer semester, String schoolId) {

        TeachCycleExample example = new TeachCycleExample();
        TeachCycleExample.Criteria criteria = example.createCriteria();

        if (semester != 0) {
            example.setOrderByClause("cycle_semester");
            criteria.andCycleSemesterEqualTo(semester);
        }

        criteria.andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0).
                andCycleYearEqualTo(year);

        List<TeachCycle> res = teachCycleMapper.selectByExample(example);
        if (res.size() > 0)
            return res.get(0);
        return new TeachCycle();
    }

    @Override
    public PageInfo<ClassRoom> getClassRoomList(Map param) {

        int pageNum = NumberConvertUtil.convertS2I(getValue(param, "pageNum").toString());
        int pageSize = NumberConvertUtil.convertS2I(getValue(param, "pageSize").toString());
        String schoolId = getValue(param, "schoolId").toString();
        String cycleId = getValue(param, "cycleId").toString();


        ClassRoomExample example = new ClassRoomExample();
        example.setOrderByClause("create_date DESC");
        ClassRoomExample.Criteria criteria = example.createCriteria();
        criteria.andDelFlagEqualTo(0);
        if (StringUtil.isNotEmpty(schoolId)) {
            criteria.andSchoolIdEqualTo(schoolId);
        }
        if (StringUtil.isNotEmpty(cycleId)) {
            List<String> list = getRoomIdList(cycleId);
            if (list.size() > 0)
                criteria.andIdIn(list);
            else return new PageInfo<>(new ArrayList<ClassRoom>());
        }

        pageSize = (pageSize == 0 ? 10 : pageSize);
        if (pageSize != -1) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<ClassRoom> roomList = classRoomMapper.selectByExample(example);
        PageInfo<ClassRoom> pageInfo = new PageInfo<ClassRoom>(roomList);

        return pageInfo;
    }

    @Override
    public int saveClassRoom(ClassRoom classRoom, String pri) {
        int flag = 0;

        if (StringUtil.isEmpty(classRoom.getId())) {
            //执行新增操作
            classRoom.setId(pri);
            flag = classRoomMapper.insertSelective(classRoom);
            return flag;
        } else {
            //执行修改操作
            if (classRoom.getDelFlag() == 0) {
                //首先需要根据楼 楼层 房间去查是否已经存在，如果已经存在该房间则不能够修改
                ClassRoomExample classRoomExample = new ClassRoomExample();
                classRoomExample.createCriteria().andTeachBuildingEqualTo(classRoom.getTeachBuilding())
                        .andFloorEqualTo(classRoom.getFloor()).andRoomNumEqualTo(classRoom.getRoomNum());
                List<ClassRoom> classRoomList = classRoomMapper.selectByExample(classRoomExample);
                if (classRoomList != null && classRoomList.size() > 0) {
                    return flag;
                }
                //通过id查询班牌
                flag = classRoomMapper.updateByPrimaryKeySelective(classRoom);

            } else {
                ClassCardExample example = new ClassCardExample();
                example.createCriteria().andDelFlagEqualTo(0).andClassroomEqualTo(classRoom.getId());
                List<ClassCard> classCards = classCardMapper.selectByExample(example);
                if (null != classCards && classCards.size() > 0) {
                    return flag;
                }

                //通过id查询所有的班级教室信息
                RefClassRoomExample refClassRoomExample = new RefClassRoomExample();
                refClassRoomExample.createCriteria().andRoomIdEqualTo(classRoom.getId());
                List<RefClassRoom> refClassRoomList = refClassRoomMapper.selectByExample(refClassRoomExample);
                if (null != refClassRoomList && refClassRoomList.size() > 0) {
                    return flag;
                }

                //通过roomId查询课表中是否有数据
                TeachTableExample teachTableExample = new TeachTableExample();
                teachTableExample.createCriteria().andClassRoomIdEqualTo(classRoom.getId());
                List<TeachTable> teachTables = teachTableMapper.selectByExample(teachTableExample);
                if (null != teachTables && teachTables.size() > 0) {
                    return flag;
                }
                return classRoomMapper.deleteByPrimaryKey(classRoom.getId());
            }

        }
        return flag;
    }

    @Override
    @Transactional
    public int insertClassRoomBatch(List<ClassRoom> list) {
        a_classRoomMapper.insertClassRoomBatch(list);
        return list.size();
    }


    @Override
    public List<RoomType> roomTypeList(String schoolId) {
        RoomTypeExample example = new RoomTypeExample();
        example.createCriteria().andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0);
        example.setOrderByClause("create_date desc");
        return roomTypeMapper.selectByExample(example);
    }

    public PageInfo<CourseType> findAllCourseType(Map param) {
        CourseTypeExample example = new CourseTypeExample();
        int pageNum = NumberConvertUtil.convertS2I(getValue(param, "pageNum").toString());
        int pageSize = NumberConvertUtil.convertS2I(getValue(param, "pageSize").toString());
        String schoolId = getValue(param, "schoolId").toString();
        PageHelper.startPage(pageNum, pageSize);
//        example.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId);
        example.createCriteria().andDelFlagEqualTo(0);
        example.setOrderByClause("create_date desc");
        List<CourseType> list = courseTypeMapper.selectByExample(example);
        PageInfo pageInfo = null;
        if (list.size() > 0 && list != null) {
            pageInfo = new PageInfo<>(list);
            return pageInfo;
        }
        return new PageInfo<CourseType>(new ArrayList<CourseType>());
    }

    public Course getCourseByPrimaryKey(String id) {
        Course course = courseMapper.selectByPrimaryKey(id);
        return course;
    }

    public List<GradeClass> findBjList(Integer nj, String xd, String schoolId) {
        GradeClassExample gradeClassExample = new GradeClassExample();
        gradeClassExample.createCriteria().andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0).andNjEqualTo(nj).andXdEqualTo(xd);
        gradeClassExample.setOrderByClause("NAME+\"\"");
        List<GradeClass> list = gradeClassMapper.selectByExample(gradeClassExample);

        return list;
    }

    @Override
    public int saveCourseType(CourseType courseType) {
        int flag = 0;
        if (StringUtil.isEmpty(courseType.getId())) {
            //执行新增操作
            courseType.setId(PrimaryKey.get());
            courseType.setCreateDate(System.currentTimeMillis());
            flag = courseTypeMapper.insertSelective(courseType);
        } else {
            //执行修改操作
            flag = courseTypeMapper.updateByPrimaryKeySelective(courseType);
        }
        return flag;
    }

    @Override
    public CourseType selectCourseTypeByKey(String id) {
        CourseType courseType = courseTypeMapper.selectByPrimaryKey(id);
        return courseType;
    }


    @Override
    public PageInfo<CourseView> getAllCourseListByParam(Map param) {

        String schoolId = getValue(param, "schoolId").toString();
        int pageNum = NumberConvertUtil.convertS2I(getValue(param, "pageNum").toString());
        int pageSize = NumberConvertUtil.convertS2I(getValue(param, "pageSize").toString());
        String cycleId = getValue(param, "cycleId").toString();

        PageHelper.startPage(pageNum, pageSize);
        List<CourseView> courseViewList = a_courseMapper.findCourseBySchoolIdAndCycleId(schoolId, cycleId);
        PageInfo<CourseView> pageInfo = new PageInfo<>(courseViewList);
        return pageInfo;
    }


    @Override
    public PageInfo<BZRView> findteacherByNameAndSchoolICycleId(String cycleId, String schoolId, String name, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<BZRView> bzrViewList = a_masterMapper.findteacherByNameAndSchoolICycleId(cycleId, schoolId, name);
        PageInfo<BZRView> pageInfo = new PageInfo<>(bzrViewList);
        return pageInfo;
    }

    @Override
    public List<Course> findAllCourseBySchoolIdAndCycleId(String schoolId, String cycleId) {
//        schoolId = (schoolId == null ? "" : schoolId);
//        cycleId = (cycleId == null ? "" : cycleId);
        CourseExample example = new CourseExample();
        CourseExample.Criteria criteria = example.createCriteria();
        criteria.andDelFlagEqualTo(0);
        if (schoolId != null)
            criteria.andSchoolIdEqualTo(schoolId);
        if (StringUtils.isNotEmpty(cycleId))
            criteria.andCycleIdEqualTo(cycleId);

        example.setOrderByClause("create_date");
        List<Course> courseList = courseMapper.selectByExample(example);
        return courseList;
    }

    @Override
    @Transactional
    public void batchInsertCourseClass(List<CourseClass> courseClassList) {
        if (null != courseClassList && courseClassList.size() > 0)
            a_courseClassMapper.batchInsertCourseClass(courseClassList);
    }

    @Override
    public PageInfo<BZRView> findAllCourseTeacherBycourseClassList(List<CourseClass> courseClassList, int pageNum, int pageSize, String cycleId, Integer nj, String xdId) {
        PageHelper.startPage(pageNum, pageSize);
        List<BZRView> bzrViewList = a_masterMapper.findAllCourseTeacherBycourseClassList(courseClassList, cycleId, nj, xdId);
        PageInfo<BZRView> pageInfo = new PageInfo<>(bzrViewList);
        return pageInfo;
    }

    @Override
    public List<CourseClass> findAllCourseClassByCourseId(String courseId) {
        CourseClassExample example = new CourseClassExample();
        example.createCriteria().andCourseIdEqualTo(courseId);
        List<CourseClass> courseClasses = courseClassMapper.selectByExample(example);
        if (courseClasses != null) {
            return courseClasses;
        }
        return new ArrayList<CourseClass>();
    }

    @Override
    public int updateCourseClassByPrimareyKey(CourseClass courseClass) {
        int succ = courseClassMapper.updateByPrimaryKeySelective(courseClass);
        return succ;
    }

    @Override
    @Transactional
    public void insertBatchTeacherClass(List<TeacherClass> correctTeacherClassList) {
        List<String> teacherIdList = new ArrayList<>();
        if (correctTeacherClassList.size() > 0) {
            for (TeacherClass teacherClass : correctTeacherClassList) {
                teacherIdList.add(teacherClass.getTeacherId());
            }
        }

        UserExample example = new UserExample();
        example.createCriteria().andRefIdIn(teacherIdList);
        List<User> userList = userMapper.selectByExample(example);
        List<UserRole> userRoleList = new ArrayList<>();

        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andRoleIdentifyEqualTo("headTeacher");
        List<Role> roles = roleMapper.selectByExample(roleExample);
        String roleId = "";
        if (roles.size() > 0) {
            roleId = roles.get(0).getId();
        }

        if (userList.size() > 0) {
            if (correctTeacherClassList.size() > 0) {
                for (User user : userList) {
                    for (TeacherClass teacherClass : correctTeacherClassList) {
                        if (user.getRefId().equals(teacherClass.getTeacherId())) {
                            UserRole userRole = new UserRole();
                            userRole.setSchoolId(user.getSchoolId());
                            userRole.setUserId(user.getId());
                            userRole.setRoleId(roleId);
                            userRoleList.add(userRole);
                        }
                    }
                }
            }
        }

        if (null != userRoleList && userRoleList.size() > 0 && null != correctTeacherClassList && correctTeacherClassList.size() > 0) {
            a_userExtensionMapper.insertUserRoleBatch(userRoleList);
            a_masterMapper.insertBatchTeacherClass(correctTeacherClassList);
        }

    }


    @Override
    public List<CourseClassView> findAllCourseTeacherBySchoolId(String schoolId, String gradeClassId) {
        List<CourseClassView> courseClassViewList = a_courseClassMapper.findAllCourseTeacherBySchoolId(schoolId, gradeClassId);
        return courseClassViewList;
    }

    @Override
    public List<RoomType> getAllRoomTypeBySchoolId(String schoolId) {
        RoomTypeExample example = new RoomTypeExample();
        example.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId);
        List<RoomType> list = roomTypeMapper.selectByExample(example);
        if (list != null) {
            return list;
        }
        return new ArrayList<RoomType>();
    }

    @Override
    public List<CourseClass> findClassIdByCourseId(String courseId) {
        CourseClassExample example = new CourseClassExample();
        example.createCriteria().andCourseIdEqualTo(courseId);
        List<CourseClass> courseClasses = courseClassMapper.selectByExample(example);
        return courseClasses;
    }

    @Override
    public CourseType findCourseTypeByName(String oneCourse, String schoolId) {
        CourseTypeExample example = new CourseTypeExample();
        example.createCriteria().andDelFlagEqualTo(0).andNameEqualTo(oneCourse);
//        example.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId).andNameEqualTo(oneCourse);
        List<CourseType> courseTypeList = courseTypeMapper.selectByExample(example);
        if (courseTypeList.size() > 0) {
            return courseTypeList.get(0);
        }
        return new CourseType();
    }

    @Override
    public int deleteTeacherClassByClassId(String classId, String cycleId) {
        TeacherClassExample example = new TeacherClassExample();
        example.createCriteria().andClassIdEqualTo(classId);
        if (StringUtils.isNotEmpty(cycleId)) example.createCriteria().andCycleIdEqualTo(cycleId);
        int succ = teacherClassMapper.deleteByExample(example);
        return succ;
    }

    @Override
    public CourseClass selectCourseClassByKey(String courseClassId) {
        CourseClass courseClass = courseClassMapper.selectByPrimaryKey(courseClassId);
        return courseClass;
    }

    @Override
    public List<BZRView> findCourseTeacherByCycleIdAndSchoolIdAndName(String schoolId, String cycleId, String teacherName) {
        List<BZRView> bzrViewList = a_masterMapper.findCourseTeacherByCycleIdAndSchoolIdAndName(cycleId, schoolId, teacherName);
        return bzrViewList;
    }

    @Override
    public TeacherClass findTeacherClassByClassIdCycleIdTeacherId(String classId, String cycleId, String teacherId, int i) {
        TeacherClassExample example = new TeacherClassExample();
        example.createCriteria().andClassIdEqualTo(classId).andTeacherIdEqualTo(teacherId).andCycleIdEqualTo(cycleId).andTypeEqualTo(i);
        List<TeacherClass> teacherClasses = teacherClassMapper.selectByExample(example);
        if (teacherClasses.size() > 0) {
            return teacherClasses.get(0);
        }
        return new TeacherClass();
    }


    @Override
    public List<TeacherClass> findAllByTeacherId(String teacherId) {
        List<TeacherClass> teacherClasses = new ArrayList<>();
        if (StringUtils.isNotEmpty(teacherId)) {
            TeacherClassExample example = new TeacherClassExample();
            example.createCriteria().andTeacherIdEqualTo(teacherId);
            teacherClasses = teacherClassMapper.selectByExample(example);
        }
        return teacherClasses;
    }

    @Override
    public PageInfo<BZRView> getAllMasterByGradeClassIds(int pageNum, int pageSize, String xdId, int nj, String cycleId, Integer type) {
        PageHelper.startPage(pageNum, pageSize);
        List<BZRView> list = a_masterMapper.getAllMasterByGradeClassIds(xdId, nj, cycleId, type);
        PageInfo<BZRView> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<TeacherClass> findLastMasterByPreCycleId(String preCycleId) {
        TeacherClassExample example = new TeacherClassExample();
        example.createCriteria().andCycleIdEqualTo(preCycleId);
        List<TeacherClass> teacherClasses = teacherClassMapper.selectByExample(example);
        return teacherClasses;
    }


    @Override
    public List<BZRView> findLastCourseClassByPreCycleId(String preCycleId) {
        CourseExample example = new CourseExample();
        example.createCriteria().andCycleIdEqualTo(preCycleId).andDelFlagEqualTo(0);
        List<Course> courseList = courseMapper.selectByExample(example);
        List<String> courseIdList = new ArrayList<>();
        for (Course course : courseList) {
            courseIdList.add(course.getId());
        }
        List<BZRView> bzrViewList = a_masterMapper.findAllCourseTeacherBycourseClassIdList(courseIdList);
        return bzrViewList;
    }

    @Override
    @Transactional
    public void batchInsertCourse(List<Course> courseList, List<CourseClass> courseClassList) {
        if (null != courseClassList && courseClassList.size() > 0) {
            a_courseClassMapper.batchInsertCourseClass(courseClassList);
        }
        if (courseList != null && courseList.size() > 0)
            a_courseMapper.batchInsertCourse(courseList);
    }

    @Override
    public List<ClassRoom> findAllClassRoomByCycleId(String cycleId) {
        ClassRoomExample example = new ClassRoomExample();
        ClassRoomExample.Criteria criteria = example.createCriteria();

        List<String> list = getRoomIdList(cycleId);
        if (list.size() > 0)
            criteria.andIdIn(list);
        else
            return new ArrayList<ClassRoom>();

        List<ClassRoom> classRoomList = classRoomMapper.selectByExample(example);
        return classRoomList;
    }


    @Override
    public List<ClassRoom> findAllClassRoomBySchoolId(String schoolId) {
        ClassRoomExample example = new ClassRoomExample();
        example.createCriteria().andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0);
        List<ClassRoom> classRoomList = classRoomMapper.selectByExample(example);
        return classRoomList;
        /* if (classRoomList.size() > 0) {
            return classRoomList;
        }
        return new ArrayList<ClassRoom>();*/
    }

    @Override
    public List<RefClassRoom> findAllRefClassRoomByClassRoomId(List<ClassRoom> classRoomListByschoolId) {
        List<RefClassRoom> refClassRoomList = null;
        if (classRoomListByschoolId != null && classRoomListByschoolId.size() > 0) {
            refClassRoomList = a_refClassRoomMapper.findAllRefClassRoomByClassRoomId(classRoomListByschoolId);
        }

        if (null != refClassRoomList && refClassRoomList.size() > 0) {
            return refClassRoomList;
        }
        return new ArrayList<RefClassRoom>();
    }

    @Override
    public RefClassRoom findClassRoomByRoomId(String roomId) {
        RefClassRoom refClassRoom=new RefClassRoom();
        RefClassRoomExample example=new RefClassRoomExample();
        example.createCriteria().andRoomIdEqualTo(roomId);
        List<RefClassRoom> classRooms = refClassRoomMapper.selectByExample(example);
        if(null!=classRooms&&classRooms.size()==1){
            refClassRoom=classRooms.get(0);
        }
        return refClassRoom;
    }

    @Override
    public void batchInsertRefClassRoom(List<RefClassRoom> correctRefClassRoomList) {
        if (correctRefClassRoomList != null && correctRefClassRoomList.size() > 0)
            a_refClassRoomMapper.batchInsertRefClassRoom(correctRefClassRoomList);
    }

    @Override
    public PageInfo<RefClassRoomView> getRefClassRoomList(int pageNum, int pageSize, String schoolId, String cycleId, int nj, String xdId) {
        PageHelper.startPage(pageNum, pageSize);
        List<RefClassRoomView> list = a_refClassRoomMapper.getRefClassRoomList(schoolId, cycleId, nj, xdId);
        PageInfo<RefClassRoomView> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

//    @Override
//    public List<SchoolType> findAllSchoolTypeBySchoolId(String schoolId) {
//        SchoolTypeExample example = new SchoolTypeExample();
//        example.createCriteria().andSchoolIdEqualTo(schoolId);
//        List<SchoolType> schoolTypeList = schoolTypeMapper.selectByExample(example);
//        return schoolTypeList;
//    }

    @Override
    public int updateRefClassRoomByKey(RefClassRoom refClassRoom) {
        return refClassRoomMapper.updateByPrimaryKeySelective(refClassRoom);
    }

    public List<String> getRoomIdList(String cycleId) {
        RefRoomCycleExample example = new RefRoomCycleExample();
        example.createCriteria().andCycleIdEqualTo(cycleId);

        List<RefRoomCycle> list = refRoomCycleMapper.selectByExample(example);

        List<String> roomIdList = new ArrayList<>();
        for (RefRoomCycle refRoomCycle : list) {
            roomIdList.add(refRoomCycle.getRoomId());
        }

        return roomIdList;
    }

    @Override
    public RefClassRoomView findRefClassRoomViewByRefId(String refId) {
        RefClassRoomView classRoomView = a_refClassRoomMapper.findRefClassRoomViewByRefId(refId);
        return classRoomView;
    }

    @Override
    public List<ClassRoom> findBuildingByschoolTypeId(String schoolTypeId) {
        List<ClassRoom> list = a_classRoomMapper.findBuildingByschoolTypeId(schoolTypeId);
        return list;
    }

    @Override
    public List<CourseClassView> findRefCourseClassByCycleIdCourseId(String cycleId, String courseId) {
        List<CourseClassView> courseClassViewList = a_courseClassMapper.findRefCourseClassByCycleIdCourseId(cycleId, courseId);
        return courseClassViewList;
    }

    @Override
    public List<GradeClass> findGradeClassBySectionIdAndNj(List<CourseClassHourView> a_courseClassHourArrayList) {
        List<GradeClass> gradeClassList = a_gradeClassMapper.findGradeClassBySectionIdAndNj(a_courseClassHourArrayList);
        return gradeClassList;
    }

    @Override
    public CourseClass findCourseClassByClassIdAndCourseId(String courseId, String id) {
        CourseClassExample example = new CourseClassExample();
        example.createCriteria().andClassIdEqualTo(id).andCourseIdEqualTo(courseId);
        List<CourseClass> courseClasses = courseClassMapper.selectByExample(example);
        if (courseClasses.size() > 0) {
            return courseClasses.get(0);
        }
        return new CourseClass();
    }

    @Override
    public List<ClassRoom> findRooomsBySchoolTypeIdAndBuilding(String schoolTypeId, String building) {
        ClassRoomExample example = new ClassRoomExample();
        example.createCriteria().andSchoolTypeEqualTo(schoolTypeId).andTeachBuildingEqualTo(building);
        List<ClassRoom> rooms = classRoomMapper.selectByExample(example);
        return rooms;
    }

    @Override
    public List<RefClassRoom> findRefClassRoomByCycleId(String preCycleId) {
        RefClassRoomExample example = new RefClassRoomExample();
        example.createCriteria().andCycleIdEqualTo(preCycleId);
        return refClassRoomMapper.selectByExample(example);
    }

    @Override
    @Transactional
    public List<CourseClass> findAllCourseClassByCourseList(List<Course> coursesListPre) {
        CourseClassExample example = new CourseClassExample();
        List<String> courseIdList = new ArrayList<>();

        if (coursesListPre.size() > 0) {
            for (Course course : coursesListPre) {
                courseIdList.add(course.getId());
            }
        }
        example.createCriteria().andCourseIdIn(courseIdList);
        return courseClassMapper.selectByExample(example);
    }

    @Override
    public List<GradeClass_view> findAllGradeClassBySchoolId(String schoolId) {
        List<GradeClass_view> gradeClassExtentionList = a_gradeClassMapper.findAllGradeClassBySchoolId(schoolId);
        return gradeClassExtentionList;
    }

    @Override
    public void saveStandardCourse(StandardCourse standardCourse) {

        standardCourse.setUpdateDate(System.currentTimeMillis());
        standardCourse.setDelFlag(0);
        if (standardCourse.getIsDictionary() != null && standardCourse.getIsDictionary().equals('1')) {
            standardCourse.setCourseTypeId(null);
        }
        if (StringUtils.isNotBlank(standardCourse.getId())) {

            standardCourseMapper.updateByPrimaryKeySelective(standardCourse);
        } else {
            standardCourse.setId(PrimaryKey.get());
            standardCourse.setCreateDate(System.currentTimeMillis());
            standardCourseMapper.insert(standardCourse);
        }

    }

    @Override
    public List<StandardCourse> findAllStandardCourseBySchoolId(String schoolId) {
        StandardCourseExample example = new StandardCourseExample();
        example.createCriteria().andSchoolIdEqualTo(schoolId);

        return standardCourseMapper.selectByExample(example);
    }

    @Override
    public StandardCourse findStandardCourseById(String id) {
        return standardCourseMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delStandardCourseById(String id) {
        if (id != null) {
            standardCourseMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public void saveDailyHour(DailyHour dailyHour) {
        dailyHour.setDelFlag(0);
        dailyHour.setUpdateTime(System.currentTimeMillis());
        if (null == dailyHour.getId()) {
            dailyHour.setId(PrimaryKey.get());
            dailyHour.setCreateTime(System.currentTimeMillis());
            dailyHourMapper.insert(dailyHour);
        } else {
            dailyHourMapper.updateByPrimaryKeySelective(dailyHour);
        }
    }

    @Override
    public void batchInsertDailyHour(List<DailyHour> dailyHourList) {
        if (dailyHourList != null && dailyHourList.size() > 0)
            a_dailyHoureMapper.batchInsertDailyHour(dailyHourList);

    }

    @Override
    public PageInfo<DailyHourView> findDailyHourByXdAndCycleIdAndNj(String schoolId, String xdId, String cycleId, String nj, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<DailyHourView> dailyHourViewList = a_dailyHoureMapper.findDailyHourByXdAndCycleIdAndNj(schoolId, xdId, cycleId, nj);
        PageInfo<DailyHourView> pageInfo = new PageInfo<>(dailyHourViewList);
        return pageInfo;
    }

    @Override
    public DailyHour findDailyHourById(String dailyHourId) {
        return dailyHourMapper.selectByPrimaryKey(dailyHourId);
    }

    @Override
    public void delDailyHourById(String dailyId) {
        dailyHourMapper.deleteByPrimaryKey(dailyId);
    }


//    @Override
//    public void batchSaveCourseNode(List<CourseNode> courseNodeList) {
//        a_courseNodeMapper.batchSaveCourseNode(courseNodeList);
//    }

    @Override
    public CourseNodeInit findCourNodeInitByCycleIdAndSchoolIdAndTimeSection(String schoolId, String cycleId, String time_section) {
//        CourseNodeInitExample example = new CourseNodeInitExample();
//        example.createCriteria().andSchoolIdEqualTo(schoolId).andCycleIdEqualTo(cycleId).andMonthStartEndEqualTo(time_section);
//        List<CourseNodeInit> courseNodeInitList = courseNodeInitMapper.selectByExample(example);
//        if (courseNodeInitList.size() > 0) {
//            return courseNodeInitList.get(0);
//        }
        return new CourseNodeInit();
    }

    @Override
    @Transactional
    public void saveCourseNodeInit(CourseNodeInit courseNodeInit, List<CourseNode> courseNodeList) {
        int succ = 0;
        courseNodeInit.setCreateTime(System.currentTimeMillis());
        courseNodeInit.setDelFlag(0);
        //根据initId查询是否已存在
        CourseNodeInit courseNodeInitDB = courseNodeInitMapper.selectByPrimaryKey(courseNodeInit.getId());
        if (courseNodeInitDB == null) {
            succ = courseNodeInitMapper.insert(courseNodeInit);
        } else {
            succ = courseNodeInitMapper.updateByPrimaryKeySelective(courseNodeInit);
            CourseNodeExample example = new CourseNodeExample();
            example.createCriteria().andCourseNodeInitIdEqualTo(courseNodeInit.getId());
            succ = courseNodeMapper.deleteByExample(example);
        }

        if (succ > 0 && courseNodeList != null && courseNodeList.size() > 0) {
            a_courseNodeMapper.batchSaveCourseNode(courseNodeList);
        }
    }

    public PageInfo<CourseNodeInit> findCourseNodeInitBySchoolId(String schoolId, Integer pageNum, Integer pageSize, String cycleId) {
        schoolId = (schoolId == null ? "" : schoolId);
        cycleId = (cycleId == null ? "" : cycleId);

        CourseNodeInitExample example = new CourseNodeInitExample();
        example.createCriteria().andSchoolIdEqualTo(schoolId).andCycleIdEqualTo(cycleId).andDelFlagEqualTo(0);
        PageHelper.startPage(pageNum, pageSize);
        List<CourseNodeInit> courseNodeInitList = courseNodeInitMapper.selectByExample(example);
        PageInfo<CourseNodeInit> pageInfo = new PageInfo<>(courseNodeInitList);
        return pageInfo;
    }

    @Override
    public CourseNodeInit findCourseNodeInitById(String nodeId) {
        return courseNodeInitMapper.selectByPrimaryKey(nodeId);
    }

    @Override
    public List<CourseNode> findCourseNodeByNodeId(String nodeId) {
        nodeId = (nodeId == null ? "" : nodeId);

        CourseNodeExample example = new CourseNodeExample();
        example.createCriteria().andCourseNodeInitIdEqualTo(nodeId);
        example.setOrderByClause("node");
        return courseNodeMapper.selectByExample(example);
    }

    @Transactional
    public void delCourseNodeInit(String nodeId) {
        courseNodeInitMapper.deleteByPrimaryKey(nodeId);

        CourseNodeExample example = new CourseNodeExample();
        example.createCriteria().andCourseNodeInitIdEqualTo(nodeId);
        courseNodeMapper.deleteByExample(example);

    }


    @Override
    public int updateCourseNodeInitById(CourseNodeInit courseNodeInit) {
        return courseNodeInitMapper.updateByPrimaryKeySelective(courseNodeInit);

    }

    @Override
    @Transactional
    public void updateCourseClassByCourseIdAndClassId(CourseClass courseClass, List<TeacherClass> teacherClasses) {
        int t = courseClassMapper.updateByPrimaryKeySelective(courseClass);
        a_masterMapper.insertBatchTeacherClass(teacherClasses);
    }

    @Override
    public int saveRoomType(RoomType roomType, User user) {
        roomType.setUpdateBy(user.getId());
        int succ = 0;
        if (StringUtils.isNotEmpty(roomType.getId())) {
            roomType.setUpdateDate(System.currentTimeMillis());

            //通过rootypeId查询所有的room 更改teach_room表里的数据
            ClassRoomExample example = new ClassRoomExample();
            example.createCriteria().andRoomTypeEqualTo(roomType.getId());
            List<ClassRoom> classRoomList = classRoomMapper.selectByExample(example);
            if (classRoomList.size() > 0) {
                for (ClassRoom classRoom : classRoomList) {
                    classRoom.setRoomTypeName(roomType.getName());
                    classRoomMapper.updateByPrimaryKeySelective(classRoom);
                }
            }
            succ = roomTypeMapper.updateByPrimaryKeySelective(roomType);
        } else {
            roomType.setCreateBy(user.getId());
            roomType.setCreateDate(System.currentTimeMillis());
            roomType.setId(PrimaryKey.get());
            roomType.setDelFlag(0);//0显示 1隐藏
            roomType.setSchoolId(user.getSchoolId());
            succ = roomTypeMapper.insert(roomType);
        }
        return succ;
    }

    public void batchDelCourseClass(String courseId) {
        a_courseClassMapper.batchDelByCourseId(courseId);
    }

    @Override
    public PageInfo<StandardCourse> findAllStandardCourseBySchoolIdAndPageNum(String schoolId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<StandardCourse> standardCourseList = a_standardCourseMapper.findAllStandardCourseBySchoolIdAndPageNum(schoolId);
        PageInfo<StandardCourse> pageInfo = new PageInfo<>(standardCourseList);
        return pageInfo;
    }


    @Override
    public int saveCourse(Course course, User user) {
        int flag = 0;
        course.setSchoolId(user.getSchoolId());
        if (StringUtil.isEmpty(course.getId())) {
            //执行新增操作
            course.setId(PrimaryKey.get());
            course.setDelFlag(0);
            flag = courseMapper.insertSelective(course);
        } else {
            if (course.getDelFlag() == 1) {
                //根据courseId查询courseClass表
                CourseClassExample courseClassExample = new CourseClassExample();
                courseClassExample.createCriteria().andCourseIdEqualTo(course.getId());
                List<CourseClass> courseClasses = courseClassMapper.selectByExample(courseClassExample);
                if (courseClasses != null && courseClasses.size() > 0) {
                    return 0;
                }

                //查询课表
                TeachTableExample teachTableExample = new TeachTableExample();
                teachTableExample.createCriteria().andCourseIdEqualTo(course.getId());
                List<TeachTable> teachTables = teachTableMapper.selectByExample(teachTableExample);
                if (teachTables != null && teachTables.size() > 0) {
                    return 0;
                }
            } else {
                return courseMapper.deleteByPrimaryKey(course.getId());
            }

            //执行修改操作
            flag = courseMapper.updateByPrimaryKeySelective(course);
        }
        return flag;
    }

    public List<CourseNodeInit> findCourseNodeInitByCycleId(String cycleId) {
        CourseNodeInitExample example = new CourseNodeInitExample();
        example.createCriteria().andCycleIdEqualTo(cycleId).andDelFlagEqualTo(0);
        example.setOrderByClause("create_time desc");
        return courseNodeInitMapper.selectByExample(example);
    }

    public void batchInsertRefRoomCycle(List<RefRoomCycle> refRoomCycleList) {
        if (refRoomCycleList != null && refRoomCycleList.size() > 0)
            a_refRoomCycleMapper.batchInsertRefRoomCycle(refRoomCycleList);
    }

    @Override
    public List<GradeClass> getAllClassBySchoolIdAndNj(String schoolId, String nj, String xdId) {
        GradeClassExample example = new GradeClassExample();
        example.createCriteria().andSchoolIdEqualTo(schoolId).andNjEqualTo(NumberConvertUtil.convertS2I(nj)).andXdEqualTo(xdId);
        example.setOrderByClause("nj");
        example.setOrderByClause("bh");
        return gradeClassMapper.selectByExample(example);
    }

    @Override
    public List<BZRView> findAllTeacherClassByClassIdAndCycleId(String classId, String cycleId) {
        return a_masterMapper.findAllTeacherClassByClassIdAndCycleId(classId, cycleId);
    }

    @Override
    public PageInfo<TeachTableView> findTeachTableByCurrentCycleWeekAndSchoolId(String schoolId, Integer currentweek, Integer pageNum, Integer pageSize, String classId, List<Integer> nodeList, String cycleId) {
//        PageHelper.startPage(pageNum, pageSize);
        List<TeachTableView> list = a_teachTableMapper.findTeachTableByCurrentCycleWeekAndSchoolId(schoolId, currentweek, classId, nodeList, cycleId);
        PageInfo pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public DailyHour fingDailyHourByCycleIdAndClassId(String cycleId, String classId) {
        DailyHourExample example = new DailyHourExample();
        DailyHourExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(cycleId)) {
            criteria.andCycleIdEqualTo(cycleId);
        }
        if (StringUtils.isNotBlank(classId)) {
            criteria.andGradeClassIdEqualTo(classId);
        }
        criteria.andDelFlagEqualTo(0);
        List<DailyHour> dailyHourList = dailyHourMapper.selectByExample(example);
        if (dailyHourList.size() > 0) {
            return dailyHourList.get(0);
        }
        return null;
    }

    @Override
    public List<GradeClass> findAllGradeClassByXdAndNj(String section, String nj) {
        GradeClassExample example = new GradeClassExample();
        example.createCriteria().andXdEqualTo(section).andDelFlagEqualTo(0).andNjEqualTo(NumberConvertUtil.convertS2I(nj));
        example.setOrderByClause("bh");
        return gradeClassMapper.selectByExample(example);
    }

    @Override
    public TeachCycle findTeachCycleByPrimarykey(String cycleId) {
        return teachCycleMapper.selectByPrimaryKey(cycleId);
    }

    @Override
    public List<TeachTable> findTableListByIdList(List<String> tableIdList) {

        if (tableIdList.size() == 0)

            return null;

        else {

            TeachTableExample example = new TeachTableExample();
            example.createCriteria().andIdIn(tableIdList);

            List<TeachTable> res = teachTableMapper.selectByExample(example);
            return res;
        }

    }

    @Override
    @Transactional
    public void batchInsertTeachTable(List<TeachTable> teachTables) {
        if (teachTables != null && teachTables.size() > 0) {
            a_teachTableMapper.batchInsertTeachTable(teachTables);
        }
    }

    @Override
    public int updateTeachTableByPrimarykey(TeachTable teachTable) {
        return teachTableMapper.updateByPrimaryKeySelective(teachTable);
    }

    @Override
    public int delTeachTableByPrimarykey(String id) {
        return teachTableMapper.deleteByPrimaryKey(id);
    }

    @Override
    public RefClassRoom findRefClassRoomByCycleIdAndGradeClassId(String gradeClassId, String cycleId) {
        RefClassRoomExample example = new RefClassRoomExample();
        example.createCriteria().andCycleIdEqualTo(cycleId).andGradeClassEqualTo(gradeClassId);
        List<RefClassRoom> list = refClassRoomMapper.selectByExample(example);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<ClassRoom> findCascadeClassRoom(String flag, String schoolTypeId, String building, String floor, String schoolId) {
        return a_classRoomMapper.findCascadeClassRoom(flag, schoolTypeId, building, floor, schoolId);
    }

    @Override
    public RefClassRoom findRefClassRoomByPrimarykey(String refId) {
        return refClassRoomMapper.selectByPrimaryKey(refId);
    }

    @Override
    public ClassRoom findClassRoomByPrimarykey(String roomId) {
        return classRoomMapper.selectByPrimaryKey(roomId);
    }

    @Override
    public ClassSection findGradeClassBySecctionByPrimarykey(String sectionId) {
        return classSectionMapper.selectByPrimaryKey(sectionId);
    }

    @Override
    public int deleteTeacherClassByClassIdAndCycleId(String classId, String cycleId) {
        TeacherClassExample example = new TeacherClassExample();
        TeacherClassExample.Criteria criteria = example.createCriteria();

        if (classId != null) criteria.andClassIdEqualTo(classId);

        if (cycleId != null) criteria.andCycleIdEqualTo(cycleId);


        return teacherClassMapper.deleteByExample(example);
    }

    @Override
    @Transactional
    public int delCourseTeacherByPrimarykey(String refId) {

        //删除课表中的信息
        CourseClass courseClass = courseClassMapper.selectByPrimaryKey(refId);
        String courseId = courseClass.getCourseId();
        String teacherId = courseClass.getTeacherId();
        TeachTableExample example = new TeachTableExample();
        example.createCriteria().andTeacherIdEqualTo(teacherId).andCourseIdEqualTo(courseId);
        teachTableMapper.deleteByExample(example);

        return courseClassMapper.deleteByPrimaryKey(refId);
    }

    @Override
    public int delRefClassRoomByPrimarykey(String refId) {
        return refClassRoomMapper.deleteByPrimaryKey(refId);
    }

    @Override
    public List<TeachCycle> findTeachCycleByCycleYearAndSchoolId(String schoolId, String cycleYear) {
        TeachCycleExample example = new TeachCycleExample();
        TeachCycleExample.Criteria criteria = example.createCriteria();
        criteria.andSchoolIdEqualTo(schoolId);

        if (StringUtils.isNotEmpty(cycleYear)) {
            criteria.andCycleYearEqualTo(cycleYear);
        }
        criteria.andDelFlagEqualTo(0);

        return teachCycleMapper.selectByExample(example);
    }

    @Override
    public List<CourseNode> findCourseNodeByCourseNodeInitIdList(List<String> initIdsList) {
        CourseNodeExample example = new CourseNodeExample();
        example.createCriteria().andDelFlagEqualTo(0);
        if (initIdsList.size() > 0) {
            example.createCriteria().andCourseNodeInitIdIn(initIdsList);
        }

        return courseNodeMapper.selectByExample(example);
    }

    @Override
    @Transactional
    public void batchInsertCourseNodeInit(List<CourseNodeInit> courseNodeInitList, List<CourseNode> courseNodeList) {
        if (null != courseNodeInitList && courseNodeInitList.size() > 0) {
            a_courseNodeMapper.batchSaveCourseNodeInit(courseNodeInitList);
            a_courseNodeMapper.batchSaveCourseNode(courseNodeList);
        }
    }

    @Override
    public PageInfo<BZRView> findAllCourseTeacherByCourseIdAndXdAndNjAndCycleId(String courseId, int pageNum, int pageSize, String cycleId, int nj, String xdId) {

        PageHelper.startPage(pageNum, pageSize);
        List<BZRView> bzrs = a_masterMapper.findAllCourseTeacherByCourseIdAndXdAndNjAndCycleId(courseId, cycleId, nj, xdId);
        PageInfo<BZRView> pageInfo = new PageInfo<>(bzrs);
        return pageInfo;
    }

    @Override
    public int delCourseClassByCourseIdAndClassId(String courseId, String oldbanjineedDel) {
        CourseClassExample example = new CourseClassExample();
        example.createCriteria().andCourseIdEqualTo(courseId).andClassIdEqualTo(oldbanjineedDel);
        return courseClassMapper.deleteByExample(example);
    }

    @Override
    public List<ClassRoom> findClassRoomBySchoolType(String schoolTypeId) {
        ClassRoomExample example = new ClassRoomExample();
        example.createCriteria().andDelFlagEqualTo(0).andSchoolTypeEqualTo(schoolTypeId);
        classRoomMapper.selectByExample(example);

        return null;
    }

    @Override
    public void delCourseTableByCycleIdAndWeek(String cycleId, Integer oneweek, String classId, String schoolId) {
        TeachTableExample example = new TeachTableExample();
        example.createCriteria().andClassIdEqualTo(classId).andCycleIdEqualTo(cycleId).andSchoolIdEqualTo(schoolId).andWeekendEqualTo(oneweek);
        teachTableMapper.deleteByExample(example);
    }

    @Override
    public Map<String, Object> njBanjiSelecChange(String madata, String schoolId) {
        JsonObject params = new JsonParser().parse(madata).getAsJsonObject();
        String flag = params.get("flag").getAsString();
        String xd = params.get("xd").getAsString();
        String nj = params.get("nj").getAsString();
        String banji = params.get("banji").getAsString();


        List<GradeClass> gradeClassList = a_gradeClassMapper.findXdNjBanjiSelecChange(flag, xd, NumberConvertUtil.convertS2I(nj), banji, schoolId);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("gradeClassList", gradeClassList);
        resultMap.put("flag", flag);
        return resultMap;
    }

    @Override
    @Transactional
    public void batchUpdateTeachTable(List<TeachTable> updateBatchTeachTables, List<TeachTable> delTeachTableIds) {

        if (null != delTeachTableIds && delTeachTableIds.size() > 0) {
            for (TeachTable teachTable : delTeachTableIds) {
                TeachTableExample teachTableExample = new TeachTableExample();
                teachTableExample.createCriteria().andClassIdEqualTo(teachTable.getClassId())
                        .andCycleIdEqualTo(teachTable.getCycleId()).andSchoolIdEqualTo(teachTable.getSchoolId())
                        .andClassRoomIdEqualTo(teachTable.getClassRoomId()).andTableIdEqualTo(teachTable.getTableId())
                        .andWeekendEqualTo(teachTable.getWeekend());
                teachTableMapper.deleteByExample(teachTableExample);
            }

        }
        if (null != updateBatchTeachTables && updateBatchTeachTables.size() > 0) {
//            a_teachTableMapper.updateBatchTeachTables(updateBatchTeachTables);
            for (TeachTable teachTable : updateBatchTeachTables) {
                TeachTableExample teachTableExample = new TeachTableExample();
                teachTableExample.createCriteria().andClassIdEqualTo(teachTable.getClassId())
                        .andCycleIdEqualTo(teachTable.getCycleId()).andSchoolIdEqualTo(teachTable.getSchoolId())
                        .andClassRoomIdEqualTo(teachTable.getClassRoomId()).andTableIdEqualTo(teachTable.getTableId())
                        .andWeekendEqualTo(teachTable.getWeekend());
                teachTableMapper.updateByExampleSelective(teachTable, teachTableExample);
            }
        }
    }

    @Override
    @Transactional
    public void delTeacherClassByClassIdAndTeacherIdAndType(String teacherIdFromHouTai, String classId, int i, String cycleId, TeacherClass teacherClass) {
        TeacherClassExample teacherClassExample = new TeacherClassExample();
        TeacherClassExample.Criteria criteria = teacherClassExample.createCriteria();
        criteria.andCycleIdEqualTo(cycleId).andClassIdEqualTo(classId).andTypeEqualTo(3).andTeacherIdEqualTo(teacherIdFromHouTai);
        teacherClassMapper.deleteByExample(teacherClassExample);
        teacherClassMapper.insert(teacherClass);
    }

    @Override
    public Course findCourseById(String courseId) {
        return  courseMapper.selectByPrimaryKey(courseId);
    }

    @Override
    public ClassSection findClassSectionById(String xdId) {
        return classSectionMapper.selectByPrimaryKey(xdId);
    }

    @Override
    public List<TeachTableView> findCurrentTeachTable(int currentWeek, String classId, String cycleId, int weekDay) {
        List<TeachTableView> currentTeachTable = a_teachTableMapper.findCurrentTeachTable(currentWeek, classId, cycleId, weekDay);
        return currentTeachTable;
    }

}
