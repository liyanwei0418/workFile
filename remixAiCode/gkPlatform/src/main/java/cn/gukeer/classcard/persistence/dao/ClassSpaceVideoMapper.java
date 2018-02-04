package cn.gukeer.classcard.persistence.dao;

import cn.gukeer.classcard.persistence.entity.ClassSpaceVideo;
import cn.gukeer.classcard.persistence.entity.ClassSpaceVideoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassSpaceVideoMapper {
    int deleteByExample(ClassSpaceVideoExample example);

    int deleteByPrimaryKey(String id);

    int insert(ClassSpaceVideo record);

    int insertSelective(ClassSpaceVideo record);

    List<ClassSpaceVideo> selectByExample(ClassSpaceVideoExample example);

    ClassSpaceVideo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ClassSpaceVideo record, @Param("example") ClassSpaceVideoExample example);

    int updateByExample(@Param("record") ClassSpaceVideo record, @Param("example") ClassSpaceVideoExample example);

    int updateByPrimaryKeySelective(ClassSpaceVideo record);

    int updateByPrimaryKey(ClassSpaceVideo record);
}