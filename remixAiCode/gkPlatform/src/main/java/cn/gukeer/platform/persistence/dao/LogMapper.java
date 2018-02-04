package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.Log;
import cn.gukeer.platform.persistence.entity.LogExample;
import cn.gukeer.platform.persistence.entity.LogWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogMapper {
    int deleteByExample(LogExample example);

    int deleteByPrimaryKey(String id);

    int insert(LogWithBLOBs record);

    int insertSelective(LogWithBLOBs record);

    List<LogWithBLOBs> selectByExampleWithBLOBs(LogExample example);

    List<Log> selectByExample(LogExample example);

    LogWithBLOBs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") LogWithBLOBs record, @Param("example") LogExample example);

    int updateByExampleWithBLOBs(@Param("record") LogWithBLOBs record, @Param("example") LogExample example);

    int updateByExample(@Param("record") Log record, @Param("example") LogExample example);

    int updateByPrimaryKeySelective(LogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(LogWithBLOBs record);

    int updateByPrimaryKey(Log record);
}