package cn.gukeer.classcard.persistence.dao;

import cn.gukeer.classcard.persistence.entity.ClassCardNotify;
import cn.gukeer.classcard.persistence.entity.ClassCardNotifyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassCardNotifyMapper {
    int deleteByExample(ClassCardNotifyExample example);

    int deleteByPrimaryKey(String id);

    int insert(ClassCardNotify record);

    int insertSelective(ClassCardNotify record);

    List<ClassCardNotify> selectByExampleWithBLOBs(ClassCardNotifyExample example);

    List<ClassCardNotify> selectByExample(ClassCardNotifyExample example);

    ClassCardNotify selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ClassCardNotify record, @Param("example") ClassCardNotifyExample example);

    int updateByExampleWithBLOBs(@Param("record") ClassCardNotify record, @Param("example") ClassCardNotifyExample example);

    int updateByExample(@Param("record") ClassCardNotify record, @Param("example") ClassCardNotifyExample example);

    int updateByPrimaryKeySelective(ClassCardNotify record);

    int updateByPrimaryKeyWithBLOBs(ClassCardNotify record);

    int updateByPrimaryKey(ClassCardNotify record);
}