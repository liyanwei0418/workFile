package cn.gukeer.classcard.persistence.dao;

import cn.gukeer.classcard.persistence.entity.ClassCardCustomRef;
import cn.gukeer.classcard.persistence.entity.ClassCardCustomRefExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassCardCustomRefMapper {
    int deleteByExample(ClassCardCustomRefExample example);

    int deleteByPrimaryKey(String id);

    int insert(ClassCardCustomRef record);

    int insertSelective(ClassCardCustomRef record);

    List<ClassCardCustomRef> selectByExample(ClassCardCustomRefExample example);

    ClassCardCustomRef selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ClassCardCustomRef record, @Param("example") ClassCardCustomRefExample example);

    int updateByExample(@Param("record") ClassCardCustomRef record, @Param("example") ClassCardCustomRefExample example);

    int updateByPrimaryKeySelective(ClassCardCustomRef record);

    int updateByPrimaryKey(ClassCardCustomRef record);
}