package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.NotifyRecord;
import cn.gukeer.platform.persistence.entity.NotifyRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NotifyRecordMapper {
    int deleteByExample(NotifyRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(NotifyRecord record);

    int insertSelective(NotifyRecord record);

    List<NotifyRecord> selectByExample(NotifyRecordExample example);

    NotifyRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") NotifyRecord record, @Param("example") NotifyRecordExample example);

    int updateByExample(@Param("record") NotifyRecord record, @Param("example") NotifyRecordExample example);

    int updateByPrimaryKeySelective(NotifyRecord record);

    int updateByPrimaryKey(NotifyRecord record);
}