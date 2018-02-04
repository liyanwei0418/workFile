package cn.gukeer.classcard.persistence.dao;

import cn.gukeer.classcard.persistence.entity.ClassCardConfigRef;
import cn.gukeer.classcard.persistence.entity.ClassCardConfigRefExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassCardConfigRefMapper {
    int deleteByExample(ClassCardConfigRefExample example);

    int deleteByPrimaryKey(String id);

    int insert(ClassCardConfigRef record);

    int insertSelective(ClassCardConfigRef record);

    List<ClassCardConfigRef> selectByExample(ClassCardConfigRefExample example);

    ClassCardConfigRef selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ClassCardConfigRef record, @Param("example") ClassCardConfigRefExample example);

    int updateByExample(@Param("record") ClassCardConfigRef record, @Param("example") ClassCardConfigRefExample example);

    int updateByPrimaryKeySelective(ClassCardConfigRef record);

    int updateByPrimaryKey(ClassCardConfigRef record);
}