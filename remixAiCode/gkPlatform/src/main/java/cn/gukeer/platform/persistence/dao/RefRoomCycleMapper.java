package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.RefRoomCycle;
import cn.gukeer.platform.persistence.entity.RefRoomCycleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RefRoomCycleMapper {
    int deleteByExample(RefRoomCycleExample example);

    int deleteByPrimaryKey(String id);

    int insert(RefRoomCycle record);

    int insertSelective(RefRoomCycle record);

    List<RefRoomCycle> selectByExample(RefRoomCycleExample example);

    RefRoomCycle selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RefRoomCycle record, @Param("example") RefRoomCycleExample example);

    int updateByExample(@Param("record") RefRoomCycle record, @Param("example") RefRoomCycleExample example);

    int updateByPrimaryKeySelective(RefRoomCycle record);

    int updateByPrimaryKey(RefRoomCycle record);
}