package cn.gukeer.classcard.persistence.dao;

import cn.gukeer.classcard.persistence.entity.ClassCardAppRef;
import cn.gukeer.classcard.persistence.entity.ClassCardAppRefExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassCardAppRefMapper {
    int deleteByExample(ClassCardAppRefExample example);

    int deleteByPrimaryKey(String id);

    int insert(ClassCardAppRef record);

    int insertSelective(ClassCardAppRef record);

    List<ClassCardAppRef> selectByExample(ClassCardAppRefExample example);

    ClassCardAppRef selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ClassCardAppRef record, @Param("example") ClassCardAppRefExample example);

    int updateByExample(@Param("record") ClassCardAppRef record, @Param("example") ClassCardAppRefExample example);

    int updateByPrimaryKeySelective(ClassCardAppRef record);

    int updateByPrimaryKey(ClassCardAppRef record);
}