package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.NetDiskAccount;
import cn.gukeer.platform.persistence.entity.NetDiskAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NetDiskAccountMapper {
    int deleteByExample(NetDiskAccountExample example);

    int deleteByPrimaryKey(String id);

    int insert(NetDiskAccount record);

    int insertSelective(NetDiskAccount record);

    List<NetDiskAccount> selectByExample(NetDiskAccountExample example);

    NetDiskAccount selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") NetDiskAccount record, @Param("example") NetDiskAccountExample example);

    int updateByExample(@Param("record") NetDiskAccount record, @Param("example") NetDiskAccountExample example);

    int updateByPrimaryKeySelective(NetDiskAccount record);

    int updateByPrimaryKey(NetDiskAccount record);
}