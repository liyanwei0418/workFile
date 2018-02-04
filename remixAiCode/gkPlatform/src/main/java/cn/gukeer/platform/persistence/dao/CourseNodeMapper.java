package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.CourseNode;
import cn.gukeer.platform.persistence.entity.CourseNodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseNodeMapper {
    int deleteByExample(CourseNodeExample example);

    int deleteByPrimaryKey(String id);

    int insert(CourseNode record);

    int insertSelective(CourseNode record);

    List<CourseNode> selectByExample(CourseNodeExample example);

    CourseNode selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CourseNode record, @Param("example") CourseNodeExample example);

    int updateByExample(@Param("record") CourseNode record, @Param("example") CourseNodeExample example);

    int updateByPrimaryKeySelective(CourseNode record);

    int updateByPrimaryKey(CourseNode record);
}