package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.CourseClass;
import cn.gukeer.platform.persistence.entity.CourseClassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseClassMapper {
    int deleteByExample(CourseClassExample example);

    int deleteByPrimaryKey(String id);

    int insert(CourseClass record);

    int insertSelective(CourseClass record);

    List<CourseClass> selectByExample(CourseClassExample example);

    CourseClass selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CourseClass record, @Param("example") CourseClassExample example);

    int updateByExample(@Param("record") CourseClass record, @Param("example") CourseClassExample example);

    int updateByPrimaryKeySelective(CourseClass record);

    int updateByPrimaryKey(CourseClass record);
}