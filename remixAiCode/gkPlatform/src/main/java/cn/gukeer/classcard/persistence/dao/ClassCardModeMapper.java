package cn.gukeer.classcard.persistence.dao;

import cn.gukeer.classcard.persistence.entity.ClassCardMode;
import cn.gukeer.classcard.persistence.entity.ClassCardModeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassCardModeMapper {
    int deleteByExample(ClassCardModeExample example);

    int deleteByPrimaryKey(String id);

    int insert(ClassCardMode record);

    int insertSelective(ClassCardMode record);

    List<ClassCardMode> selectByExampleWithBLOBs(ClassCardModeExample example);

    List<ClassCardMode> selectByExample(ClassCardModeExample example);

    ClassCardMode selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ClassCardMode record, @Param("example") ClassCardModeExample example);

    int updateByExampleWithBLOBs(@Param("record") ClassCardMode record, @Param("example") ClassCardModeExample example);

    int updateByExample(@Param("record") ClassCardMode record, @Param("example") ClassCardModeExample example);

    int updateByPrimaryKeySelective(ClassCardMode record);

    int updateByPrimaryKeyWithBLOBs(ClassCardMode record);

    int updateByPrimaryKey(ClassCardMode record);
}