package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.RefRoomCycle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by LL on 2017/6/30.
 */
public interface A_RefRoomCycleMapper {
    void batchInsertRefRoomCycle(@Param("refRoomCycleList") List<RefRoomCycle> refRoomCycleList);
}
