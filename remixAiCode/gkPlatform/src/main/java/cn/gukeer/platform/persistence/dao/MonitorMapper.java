package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.Monitor;
import cn.gukeer.platform.persistence.entity.MonitorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MonitorMapper {
    int deleteByExample(MonitorExample example);

    int deleteByPrimaryKey(String id);

    int insert(Monitor record);

    int insertSelective(Monitor record);

    List<Monitor> selectByExample(MonitorExample example);

    Monitor selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Monitor record, @Param("example") MonitorExample example);

    int updateByExample(@Param("record") Monitor record, @Param("example") MonitorExample example);

    int updateByPrimaryKeySelective(Monitor record);

    int updateByPrimaryKey(Monitor record);
}