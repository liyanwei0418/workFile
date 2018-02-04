package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.StandardCourse;
import cn.gukeer.platform.persistence.entity.StandardCourseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StandardCourseMapper {
    int deleteByExample(StandardCourseExample example);

    int deleteByPrimaryKey(String id);

    int insert(StandardCourse record);

    int insertSelective(StandardCourse record);

    List<StandardCourse> selectByExample(StandardCourseExample example);

    StandardCourse selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") StandardCourse record, @Param("example") StandardCourseExample example);

    int updateByExample(@Param("record") StandardCourse record, @Param("example") StandardCourseExample example);

    int updateByPrimaryKeySelective(StandardCourse record);

    int updateByPrimaryKey(StandardCourse record);
}