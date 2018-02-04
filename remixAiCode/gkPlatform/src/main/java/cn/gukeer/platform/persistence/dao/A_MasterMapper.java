package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.modelView.BZRView;
import cn.gukeer.platform.persistence.entity.CourseClass;
import cn.gukeer.platform.persistence.entity.TeacherClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by LL on 2017/4/18.
 */
public interface A_MasterMapper {

    List<BZRView> findteacherByNameAndSchoolICycleId(@Param("cycleId")String cycleId,@Param("schoolId") String schoolId,@Param("name") String name);

    int  insertBatchTeacherClass(@Param("correctTeacherClassList")List<TeacherClass> correctTeacherClassList);

    List<BZRView> getAllMasterByGradeClassIds(@Param("xdId") String xdId, @Param("nj") int nj, @Param("cycleId")String cycleId, @Param("type")Integer type);

    List<BZRView> findAllCourseTeacherBycourseClassList(@Param("courseClassList") List<CourseClass> courseClassList, @Param("cycleId") String cycleId,@Param("nj") Integer nj,@Param("xdId") String xdId);

    List<BZRView> findCourseTeacherByCycleIdAndSchoolIdAndName(@Param("cycleId")String cycleId,@Param("schoolId") String schoolId,@Param("name") String name);

    List<BZRView> findAllCourseTeacherBycourseClassIdList(@Param("courseIdList")List<String> courseIdList);

    List<BZRView> findAllTeacherClassByClassIdAndCycleId(@Param("classId")String classId, @Param("cycleId")String cycleId);

    List<BZRView> findAllCourseTeacherByCourseIdAndXdAndNjAndCycleId(@Param("courseId")String courseId,@Param("cycleId")  String cycleId, @Param("nj") int nj, @Param("xdId") String xdId);
}
