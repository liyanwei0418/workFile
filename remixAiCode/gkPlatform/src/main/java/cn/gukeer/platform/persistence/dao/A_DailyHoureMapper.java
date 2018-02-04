package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.modelView.DailyHourView;
import cn.gukeer.platform.persistence.entity.DailyHour;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by LL on 2017/5/23.
 */
public interface A_DailyHoureMapper {
    void batchInsertDailyHour(@Param("list") List<DailyHour> list);

    List<DailyHourView> findDailyHourByXdAndCycleIdAndNj(@Param("schoolId") String schoolId,@Param("xdId") String xdId, @Param("cycleId")String cycleId,@Param("nj") String nj);
}
