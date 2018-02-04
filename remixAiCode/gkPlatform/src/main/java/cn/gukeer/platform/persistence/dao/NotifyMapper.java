package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.Notify;
import cn.gukeer.platform.persistence.entity.NotifyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NotifyMapper {
    int deleteByExample(NotifyExample example);

    int deleteByPrimaryKey(String id);

    int insert(Notify record);

    int insertSelective(Notify record);

    List<Notify> selectByExampleWithBLOBs(NotifyExample example);

    List<Notify> selectByExample(NotifyExample example);

    Notify selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Notify record, @Param("example") NotifyExample example);

    int updateByExampleWithBLOBs(@Param("record") Notify record, @Param("example") NotifyExample example);

    int updateByExample(@Param("record") Notify record, @Param("example") NotifyExample example);

    int updateByPrimaryKeySelective(Notify record);

    int updateByPrimaryKeyWithBLOBs(Notify record);

    int updateByPrimaryKey(Notify record);
}