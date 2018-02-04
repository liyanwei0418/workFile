package cn.gukeer.classcard.persistence.dao;

import cn.gukeer.classcard.persistence.entity.ClassCardApp;
import cn.gukeer.classcard.persistence.entity.ClassCardAppExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassCardAppMapper {
    int deleteByExample(ClassCardAppExample example);

    int deleteByPrimaryKey(String id);

    int insert(ClassCardApp record);

    int insertSelective(ClassCardApp record);

    List<ClassCardApp> selectByExample(ClassCardAppExample example);

    ClassCardApp selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ClassCardApp record, @Param("example") ClassCardAppExample example);

    int updateByExample(@Param("record") ClassCardApp record, @Param("example") ClassCardAppExample example);

    int updateByPrimaryKeySelective(ClassCardApp record);

    int updateByPrimaryKey(ClassCardApp record);
}