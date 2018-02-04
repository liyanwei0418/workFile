package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.ClassSection;
import cn.gukeer.platform.persistence.entity.ClassSectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassSectionMapper {
    int deleteByExample(ClassSectionExample example);

    int deleteByPrimaryKey(String id);

    int insert(ClassSection record);

    int insertSelective(ClassSection record);

    List<ClassSection> selectByExample(ClassSectionExample example);

    ClassSection selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ClassSection record, @Param("example") ClassSectionExample example);

    int updateByExample(@Param("record") ClassSection record, @Param("example") ClassSectionExample example);

    int updateByPrimaryKeySelective(ClassSection record);

    int updateByPrimaryKey(ClassSection record);
}