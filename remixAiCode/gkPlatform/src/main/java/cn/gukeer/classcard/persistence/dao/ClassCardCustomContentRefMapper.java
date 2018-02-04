package cn.gukeer.classcard.persistence.dao;

import cn.gukeer.classcard.persistence.entity.ClassCardCustomContentRef;
import cn.gukeer.classcard.persistence.entity.ClassCardCustomContentRefExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassCardCustomContentRefMapper {
    int deleteByExample(ClassCardCustomContentRefExample example);

    int deleteByPrimaryKey(String id);

    int insert(ClassCardCustomContentRef record);

    int insertSelective(ClassCardCustomContentRef record);

    List<ClassCardCustomContentRef> selectByExample(ClassCardCustomContentRefExample example);

    ClassCardCustomContentRef selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ClassCardCustomContentRef record, @Param("example") ClassCardCustomContentRefExample example);

    int updateByExample(@Param("record") ClassCardCustomContentRef record, @Param("example") ClassCardCustomContentRefExample example);

    int updateByPrimaryKeySelective(ClassCardCustomContentRef record);

    int updateByPrimaryKey(ClassCardCustomContentRef record);
}