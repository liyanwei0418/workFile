package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.StandardCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by LL on 2017/5/22.
 */
public interface A_StandardCourseMapper {
    List<StandardCourse> findAllStandardCourseBySchoolIdAndPageNum(@Param("schoolId") String schoolId);
}
