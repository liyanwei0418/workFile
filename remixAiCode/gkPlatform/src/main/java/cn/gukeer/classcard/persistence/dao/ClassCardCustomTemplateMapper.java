package cn.gukeer.classcard.persistence.dao;

import cn.gukeer.classcard.persistence.entity.ClassCardCustomTemplate;
import cn.gukeer.classcard.persistence.entity.ClassCardCustomTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassCardCustomTemplateMapper {
    int deleteByExample(ClassCardCustomTemplateExample example);

    int deleteByPrimaryKey(String id);

    int insert(ClassCardCustomTemplate record);

    int insertSelective(ClassCardCustomTemplate record);

    List<ClassCardCustomTemplate> selectByExample(ClassCardCustomTemplateExample example);

    ClassCardCustomTemplate selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ClassCardCustomTemplate record, @Param("example") ClassCardCustomTemplateExample example);

    int updateByExample(@Param("record") ClassCardCustomTemplate record, @Param("example") ClassCardCustomTemplateExample example);

    int updateByPrimaryKeySelective(ClassCardCustomTemplate record);

    int updateByPrimaryKey(ClassCardCustomTemplate record);
}