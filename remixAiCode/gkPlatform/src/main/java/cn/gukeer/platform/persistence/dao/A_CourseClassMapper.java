package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.modelView.CourseClassView;
import cn.gukeer.platform.persistence.entity.CourseClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by LL on 2017/4/17.
 */
public interface A_CourseClassMapper {
    int batchInsertCourseClass(@Param("courseClassList")List<CourseClass> courseClasses);

    List<CourseClassView> findAllCourseTeacherBySchoolId(@Param("schoolId") String schoolId,@Param("gradeClassId")String gradeClassId);

    List<CourseClassView> findRefCourseClassByCycleIdCourseId(@Param("cycleId")String cycleId, @Param("courseId")String courseId);

    void batchDelByCourseId(@Param("courseId")String courseId);
}
