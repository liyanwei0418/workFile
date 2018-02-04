package cn.gukeer.classcard.persistence.dao;

import cn.gukeer.classcard.persistence.entity.ClassCardCustomPage;
import cn.gukeer.classcard.persistence.entity.ClassCardCustomPageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassCardCustomPageMapper {
    int deleteByExample(ClassCardCustomPageExample example);

    int deleteByPrimaryKey(String id);

    int insert(ClassCardCustomPage record);

    int insertSelective(ClassCardCustomPage record);

    List<ClassCardCustomPage> selectByExample(ClassCardCustomPageExample example);

    ClassCardCustomPage selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ClassCardCustomPage record, @Param("example") ClassCardCustomPageExample example);

    int updateByExample(@Param("record") ClassCardCustomPage record, @Param("example") ClassCardCustomPageExample example);

    int updateByPrimaryKeySelective(ClassCardCustomPage record);

    int updateByPrimaryKey(ClassCardCustomPage record);
}