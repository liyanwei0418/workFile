package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.CourseNodeInit;
import cn.gukeer.platform.persistence.entity.CourseNodeInitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseNodeInitMapper {
    int deleteByExample(CourseNodeInitExample example);

    int deleteByPrimaryKey(String id);

    int insert(CourseNodeInit record);

    int insertSelective(CourseNodeInit record);

    List<CourseNodeInit> selectByExample(CourseNodeInitExample example);

    CourseNodeInit selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CourseNodeInit record, @Param("example") CourseNodeInitExample example);

    int updateByExample(@Param("record") CourseNodeInit record, @Param("example") CourseNodeInitExample example);

    int updateByPrimaryKeySelective(CourseNodeInit record);

    int updateByPrimaryKey(CourseNodeInit record);
}