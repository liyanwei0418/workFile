package cn.gukeer.classcard.persistence.dao;

import cn.gukeer.classcard.persistence.entity.ClassIntroduction;
import cn.gukeer.classcard.persistence.entity.ClassIntroductionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassIntroductionMapper {
    int deleteByExample(ClassIntroductionExample example);

    int deleteByPrimaryKey(String id);

    int insert(ClassIntroduction record);

    int insertSelective(ClassIntroduction record);

    List<ClassIntroduction> selectByExample(ClassIntroductionExample example);

    ClassIntroduction selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ClassIntroduction record, @Param("example") ClassIntroductionExample example);

    int updateByExample(@Param("record") ClassIntroduction record, @Param("example") ClassIntroductionExample example);

    int updateByPrimaryKeySelective(ClassIntroduction record);

    int updateByPrimaryKey(ClassIntroduction record);
}