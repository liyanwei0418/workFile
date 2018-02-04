package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.NotifyColumn;
import cn.gukeer.platform.persistence.entity.NotifyColumnExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NotifyColumnMapper {
    int deleteByExample(NotifyColumnExample example);

    int deleteByPrimaryKey(String id);

    int insert(NotifyColumn record);

    int insertSelective(NotifyColumn record);

    List<NotifyColumn> selectByExample(NotifyColumnExample example);

    NotifyColumn selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") NotifyColumn record, @Param("example") NotifyColumnExample example);

    int updateByExample(@Param("record") NotifyColumn record, @Param("example") NotifyColumnExample example);

    int updateByPrimaryKeySelective(NotifyColumn record);

    int updateByPrimaryKey(NotifyColumn record);
}