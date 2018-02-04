package cn.gukeer.classcard.persistence.dao;

import cn.gukeer.classcard.persistence.entity.ClassCardConfig;
import cn.gukeer.classcard.persistence.entity.ClassCardConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassCardConfigMapper {
    int deleteByExample(ClassCardConfigExample example);

    int deleteByPrimaryKey(String id);

    int insert(ClassCardConfig record);

    int insertSelective(ClassCardConfig record);

    List<ClassCardConfig> selectByExample(ClassCardConfigExample example);

    ClassCardConfig selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ClassCardConfig record, @Param("example") ClassCardConfigExample example);

    int updateByExample(@Param("record") ClassCardConfig record, @Param("example") ClassCardConfigExample example);

    int updateByPrimaryKeySelective(ClassCardConfig record);

    int updateByPrimaryKey(ClassCardConfig record);
}