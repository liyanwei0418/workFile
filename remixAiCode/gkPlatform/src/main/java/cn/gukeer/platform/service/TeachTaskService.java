package cn.gukeer.platform.service;


import cn.gukeer.platform.modelView.*;
import cn.gukeer.platform.persistence.entity.*;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface TeachTaskService {


    int saveRefRoomCycle(RefRoomCycle roomCycle);

    int batchSaveRefRoomCycle(List<RefRoomCycle> list);

    PageInfo<TeachCycle> getTeachCycle(Map param);

    int saveTeachCycle(TeachCycle teachCycle);

    TeachCycle selectByKey(String cycleId);

    List<TeachCycle> getCycleList(String schoolId);

    //根据学年，学期，得到教学周期
    TeachCycle getCycleByYearSemester(String year, Integer semester, String schoolId);
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
   *   教室类型方法
   *
   * */
    //获取所有教室类型
    List<RoomType> roomTypeList(String schoolId);

    List<RoomType> getAllRoomTypeBySchoolId(String schoolId);

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
    *   教室管理方法
    *
    * */

    List<ClassRoom> findAllClassRoomByCycleId(String cycleId);

    List<ClassRoom> findAllClassRoomBySchoolId(String schoolId);

    PageInfo<ClassRoom> getClassRoomList(Map param);

    int saveClassRoom(ClassRoom classRoom ,String pri);

    int insertClassRoomBatch(List<ClassRoom> list);

    ClassRoom getRoomByPri(String pri);

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
    *   课程类型方法
    *
    * */
    //保存
    int saveCourseType(CourseType courseType);

    //主键查询
    CourseType selectCourseTypeByKey(String id);

    //名称查询
    CourseType findCourseTypeByName(String oneCourse, String schoolId);

    //查询所有
    PageInfo<CourseType> findAllCourseType(Map param);
/////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
    *     课程方法
    *
    * */

    //保存
    int saveCourse(Course courseFromDB, User user);

    //批量保存
    void batchInsertCourse(List<Course> courseList,List<CourseClass> courseClassList);

    //查询所有
    PageInfo<CourseView> getAllCourseListByParam(Map param);

    //根据schoolId  cycleId查询所有课程
    List<Course> findAllCourseBySchoolIdAndCycleId(String schoolId, String cycleId);

    //课程授课班级的增加
    void batchInsertCourseClass(List<CourseClass> courseClassList);

    //课程编辑授课班级功能的方法
    int updateCourseClassByPrimareyKey(CourseClass courseClass);

    //课程编辑授课班级的时候首先查询已有班级作为checkbox默认选中的条件
    List<CourseClass> findClassIdByCourseId(String courseId);

/////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *     班主任方法
     *
     * */
    //班主任首页查询的方法
    PageInfo<BZRView> getAllMasterByGradeClassIds( int pageNum, int pageSize, String xdId, int nj, String cycleId,Integer type);

    //这个仅仅是针对中间表的操作
    List<TeacherClass> findLastMasterByPreCycleId(String preCycleId);

    //班主任批量导入 插入数据的方法
    void  insertBatchTeacherClass (List<TeacherClass> correctTeacherClassList);

    //批量插入时为避免重复先执行的批量删除操作
    int deleteTeacherClassByClassId(String classId,String cycleId);

    //班主任导入首先查询是否已经导入了数据
    TeacherClass findTeacherClassByClassIdCycleIdTeacherId(String classId, String cycleId, String teacherId, int i);

    //班主任搜索
    PageInfo<BZRView> findteacherByNameAndSchoolICycleId(String cycleId, String schoolId, String name, int pageNum, int pageSize);

    /**
     *     任课教师方法
     *
     * */
    //任课教师首页的方法
    PageInfo<BZRView> findAllCourseTeacherBycourseClassList(List<CourseClass> courseClassList, int pageNum, int pageSize, String cycleId,Integer nj,String xdId);

    //查询所有任课教师安排的方法
    List<CourseClassView> findAllCourseTeacherBySchoolId(String schoolId,String gradeClassId);

    //任课教师的根据名字搜索功能
    List<BZRView> findCourseTeacherByCycleIdAndSchoolIdAndName(String schoolId, String cycleId, String teacherName);

    //任课教师根据课程条件查询
    List<CourseClass> findAllCourseClassByCourseId(String courseId);

    //根据cycleId查询任课教师
    List<BZRView> findLastCourseClassByPreCycleId(String preCycleId);

    //任课教师编辑的时候用到主键更新
    CourseClass selectCourseClassByKey(String courseClassId);

    List<TeacherClass> findAllByTeacherId(String teacherId);
/////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *     班级教师方法
     *
     * */
    //查询所有的所有的classRoom，在导入班级教师的时候作为i字典查询
    List<RefClassRoom> findAllRefClassRoomByClassRoomId(List<ClassRoom> classRoomListByschoolId);

    RefClassRoom findClassRoomByRoomId(String roomId);

    //班级教师的批量导入 插入方法
    void batchInsertRefClassRoom(List<RefClassRoom> correctRefClassRoomList);

    //班级教师的首页查询
    PageInfo<RefClassRoomView> getRefClassRoomList(int pageNum, int pageSize, String schoolId, String cycleId, int nj, String xdId);

    //在班级教室编辑的时候有学校校区的下拉框
//    List<SchoolType> findAllSchoolTypeBySchoolId(String schoolId);

    //班级教室编辑方法
    int updateRefClassRoomByKey(RefClassRoom refId);

    //在班级教室编辑功能的弹窗 所做的查询操作
    RefClassRoomView findRefClassRoomViewByRefId(String refId);

    //根据schoolTypeId查询所有的教学楼 并根据教学楼分组
    List<ClassRoom> findBuildingByschoolTypeId(String schoolTypeId);

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *     科目课时方法
     *
     * */

    //根据cycleId 和 courseId 查询中间表  并关联grade_class表  根据nj分组
    List<CourseClassView> findRefCourseClassByCycleIdCourseId(String cycleId, String courseId);

    List<GradeClass> findGradeClassBySectionIdAndNj(List<CourseClassHourView> a_courseClassHourArrayList);

    CourseClass findCourseClassByClassIdAndCourseId(String courseId, String id);

    List<ClassRoom> findRooomsBySchoolTypeIdAndBuilding(String schoolTypeId, String building);

    //创建教学周期时同步数据查询上一学期所有数据的方法
    List<RefClassRoom> findRefClassRoomByCycleId(String preCycleId);

    List<CourseClass> findAllCourseClassByCourseList(List<Course> coursesListPre);

    List<GradeClass_view> findAllGradeClassBySchoolId(String schoolId);


    //////////////////////////////////////////////
    //标准课程方法
    PageInfo<StandardCourse> findAllStandardCourseBySchoolIdAndPageNum(String schoolId, int pageNum, int pageSize);

    void saveStandardCourse(StandardCourse standardCourse);

    List<StandardCourse> findAllStandardCourseBySchoolId(String schoolId);

    StandardCourse findStandardCourseById(String id);

    void delStandardCourseById(String id);

    void saveDailyHour(DailyHour dailyHour);

    //批量插入班级课时信息
    void batchInsertDailyHour(List<DailyHour> dailyHourList);

    //班级日常课时首页信息的查询
    PageInfo<DailyHourView> findDailyHourByXdAndCycleIdAndNj(String schoolId,String xdId, String cycleId, String nj, int pageNum, int pageSize);

    DailyHour findDailyHourById(String dailyHourId);

    void delDailyHourById(String dailyId);

//    void batchSaveCourseNode(List<CourseNode> courseNodeList);

    CourseNodeInit findCourNodeInitByCycleIdAndSchoolIdAndTimeSection(String schoolId, String cycleId, String time_section);

    void saveCourseNodeInit(CourseNodeInit courseNodeInit, List<CourseNode> courseNodeList);

    PageInfo<CourseNodeInit> findCourseNodeInitBySchoolId(String schoolId, Integer pageNum, Integer pageSize, String cycleId);

    CourseNodeInit findCourseNodeInitById(String nodeId);

    List<CourseNode> findCourseNodeByNodeId(String nodeId);

    void delCourseNodeInit(String nodeId);

    int updateCourseNodeInitById(CourseNodeInit courseNodeInit);

    void updateCourseClassByCourseIdAndClassId(CourseClass courseClass,List<TeacherClass> teacherClasses);

    //保存教室类型的方法
    int saveRoomType(RoomType roomType, User user);

    void batchDelCourseClass( String courseId);

    List<CourseNodeInit> findCourseNodeInitByCycleId(String cycleId);

    void batchInsertRefRoomCycle(List<RefRoomCycle> refRoomCycleList);

    List<GradeClass> getAllClassBySchoolIdAndNj( String schoolId, String nj,String xdId);

    List<BZRView> findAllTeacherClassByClassIdAndCycleId(String classId, String cycleId);

    PageInfo<TeachTableView> findTeachTableByCurrentCycleWeekAndSchoolId(String schoolId, Integer currentweek,Integer pageNum,Integer pageSize,String classId,List<Integer> nodeList,String cycleId);

    DailyHour fingDailyHourByCycleIdAndClassId(String cycleId, String classId);

    List<GradeClass> findAllGradeClassByXdAndNj(String section, String nj);

    TeachCycle findTeachCycleByPrimarykey(String cycleId);

    List<TeachTable> findTableListByIdList(List<String> tableIdList);

    void batchInsertTeachTable(List<TeachTable> teachTables);

    int updateTeachTableByPrimarykey(TeachTable teachTable);

    int delTeachTableByPrimarykey(String id);

    RefClassRoom findRefClassRoomByCycleIdAndGradeClassId(String gradeClassId, String cycleId);

    List<ClassRoom> findCascadeClassRoom(String flag, String schoolTypeId, String building, String floor, String schoolId);

    RefClassRoom findRefClassRoomByPrimarykey(String refId);

    ClassRoom findClassRoomByPrimarykey(String roomId);

    ClassSection findGradeClassBySecctionByPrimarykey(String sectionId);

    int deleteTeacherClassByClassIdAndCycleId(String classId, String cycleId);

    int delCourseTeacherByPrimarykey(String refId);

    int delRefClassRoomByPrimarykey(String refId);

    List<TeachCycle> findTeachCycleByCycleYearAndSchoolId(String schoolId, String cycleYear);

    List<CourseNode> findCourseNodeByCourseNodeInitIdList(List<String> initIdsList);

    void batchInsertCourseNodeInit(List<CourseNodeInit> courseNodeInitList, List<CourseNode> courseNodeList);

    PageInfo<BZRView> findAllCourseTeacherByCourseIdAndXdAndNjAndCycleId(String courseId, int pageNum, int pageSize, String id, int i, String xdId);

    int delCourseClassByCourseIdAndClassId(String courseId, String oldbanjineedDel);

    List<ClassRoom> findClassRoomBySchoolType(String schoolTypeId);

    void delCourseTableByCycleIdAndWeek(String cycleId, Integer oneweek,String classId,String schoolId);

    Map<String,Object> njBanjiSelecChange(String madata, String schoolId);

    void batchUpdateTeachTable(List<TeachTable> updateBatchTeachTables, List<TeachTable> delTeachTableIds);


    void delTeacherClassByClassIdAndTeacherIdAndType(String teacherIdFromHouTai, String classId, int i, String cycleId, TeacherClass teacherClass);

    Course findCourseById(String courseId);

    ClassSection findClassSectionById(String xdId);

    //查询当天课表信息
    List<TeachTableView> findCurrentTeachTable(int currentWeek,String classId,String cycleId,int weekDay);
}
