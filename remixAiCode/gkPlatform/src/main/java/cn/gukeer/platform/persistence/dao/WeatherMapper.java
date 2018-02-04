package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.Weather;
import cn.gukeer.platform.persistence.entity.WeatherExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WeatherMapper {
    int deleteByExample(WeatherExample example);

    int deleteByPrimaryKey(String id);

    int insert(Weather record);

    int insertSelective(Weather record);

    List<Weather> selectByExampleWithBLOBs(WeatherExample example);

    List<Weather> selectByExample(WeatherExample example);

    Weather selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Weather record, @Param("example") WeatherExample example);

    int updateByExampleWithBLOBs(@Param("record") Weather record, @Param("example") WeatherExample example);

    int updateByExample(@Param("record") Weather record, @Param("example") WeatherExample example);

    int updateByPrimaryKeySelective(Weather record);

    int updateByPrimaryKeyWithBLOBs(Weather record);

    int updateByPrimaryKey(Weather record);
}