package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.modelView.CourseClassHourView;
import cn.gukeer.platform.modelView.GradeClass_view;
import cn.gukeer.platform.persistence.entity.GradeClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by LL on 2017/5/10.
 */
public interface A_GradeClassMapper {
    List<GradeClass> findGradeClassBySectionIdAndNj(@Param("list") List<CourseClassHourView> list);

    List<GradeClass_view> findAllGradeClassBySchoolId(@Param("schoolId") String schoolId);

    GradeClass_view findByClassId(@Param("classId")String classId);

    //根据classId查找出学段id
    List<GradeClass_view> findXdIdByClassIds(@Param("classIds")List<String>classIds);

    List<GradeClass> findXdNjBanjiSelecChange(@Param("flag")String flag, @Param("xd")String xd, @Param("nj")Integer nj, @Param("baji")String banji,@Param("schoolId") String schoolId);
}
