package cn.gukeer.classcard.persistence.dao;

import cn.gukeer.classcard.persistence.entity.ClassCard;
import cn.gukeer.classcard.persistence.entity.ClassCardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassCardMapper {
    int deleteByExample(ClassCardExample example);

    int deleteByPrimaryKey(String id);

    int insert(ClassCard record);

    int insertSelective(ClassCard record);

    List<ClassCard> selectByExample(ClassCardExample example);

    ClassCard selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ClassCard record, @Param("example") ClassCardExample example);

    int updateByExample(@Param("record") ClassCard record, @Param("example") ClassCardExample example);

    int updateByPrimaryKeySelective(ClassCard record);

    int updateByPrimaryKey(ClassCard record);
}