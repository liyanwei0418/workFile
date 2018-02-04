package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.DailyHour;
import cn.gukeer.platform.persistence.entity.DailyHourExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DailyHourMapper {
    int deleteByExample(DailyHourExample example);

    int deleteByPrimaryKey(String id);

    int insert(DailyHour record);

    int insertSelective(DailyHour record);

    List<DailyHour> selectByExample(DailyHourExample example);

    DailyHour selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DailyHour record, @Param("example") DailyHourExample example);

    int updateByExample(@Param("record") DailyHour record, @Param("example") DailyHourExample example);

    int updateByPrimaryKeySelective(DailyHour record);

    int updateByPrimaryKey(DailyHour record);
}