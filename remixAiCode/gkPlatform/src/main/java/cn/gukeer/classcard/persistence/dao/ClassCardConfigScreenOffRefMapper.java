package cn.gukeer.classcard.persistence.dao;

import cn.gukeer.classcard.persistence.entity.ClassCardConfigScreenOffRef;
import cn.gukeer.classcard.persistence.entity.ClassCardConfigScreenOffRefExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassCardConfigScreenOffRefMapper {
    int deleteByExample(ClassCardConfigScreenOffRefExample example);

    int deleteByPrimaryKey(String id);

    int insert(ClassCardConfigScreenOffRef record);

    int insertSelective(ClassCardConfigScreenOffRef record);

    List<ClassCardConfigScreenOffRef> selectByExample(ClassCardConfigScreenOffRefExample example);

    ClassCardConfigScreenOffRef selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ClassCardConfigScreenOffRef record, @Param("example") ClassCardConfigScreenOffRefExample example);

    int updateByExample(@Param("record") ClassCardConfigScreenOffRef record, @Param("example") ClassCardConfigScreenOffRefExample example);

    int updateByPrimaryKeySelective(ClassCardConfigScreenOffRef record);

    int updateByPrimaryKey(ClassCardConfigScreenOffRef record);
}