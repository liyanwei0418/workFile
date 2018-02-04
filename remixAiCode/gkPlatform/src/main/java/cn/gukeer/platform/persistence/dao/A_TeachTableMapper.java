package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.modelView.TeachTableView;
import cn.gukeer.platform.persistence.entity.TeachTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by LL on 2017/7/18.
 */
public interface A_TeachTableMapper {
    List<TeachTableView> findTeachTableByCurrentCycleWeekAndSchoolId(@Param("schoolId") String schoolId,
                                                                     @Param("currentweek")Integer currentweek ,
                                                                     @Param("classId")String classId,
                                                                     @Param("nodeList")List<Integer> nodeList,
                                                                     @Param("cycleId")String cycleId);

    List<Map> findLatestCycleGroupSchoolId();

    void batchInsertTeachTable(@Param("teachTables")List<TeachTable> teachTables);

    void updateBatchTeachTables(@Param("updateBatchTeachTables")List<TeachTable> updateBatchTeachTables);

    List<TeachTableView> findCurrentTeachTable(@Param("currentWeek") int currentWeek,
                                               @Param("classId") String classId,
                                               @Param("cycleId") String cycleId,
                                               @Param("weekDay") int weekDay);
}
