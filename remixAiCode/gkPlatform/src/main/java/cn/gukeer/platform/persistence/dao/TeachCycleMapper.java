package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.TeachCycle;
import cn.gukeer.platform.persistence.entity.TeachCycleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeachCycleMapper {
    int deleteByExample(TeachCycleExample example);

    int deleteByPrimaryKey(String id);

    int insert(TeachCycle record);

    int insertSelective(TeachCycle record);

    List<TeachCycle> selectByExample(TeachCycleExample example);

    TeachCycle selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TeachCycle record, @Param("example") TeachCycleExample example);

    int updateByExample(@Param("record") TeachCycle record, @Param("example") TeachCycleExample example);

    int updateByPrimaryKeySelective(TeachCycle record);

    int updateByPrimaryKey(TeachCycle record);
}