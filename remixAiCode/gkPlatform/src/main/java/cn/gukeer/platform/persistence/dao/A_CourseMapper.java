package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.modelView.CourseView;
import cn.gukeer.platform.persistence.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by LL on 2017/5/9.
 */
public interface A_CourseMapper {
    void batchInsertCourse(@Param("courseList") List<Course> courseList);

    List<CourseView> findCourseBySchoolIdAndCycleId(@Param("schoolId")String schoolId, @Param("cycleId")String cycleId);
}
