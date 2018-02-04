package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.RefClassRoom;
import cn.gukeer.platform.persistence.entity.RefClassRoomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RefClassRoomMapper {
    int deleteByExample(RefClassRoomExample example);

    int deleteByPrimaryKey(String id);

    int insert(RefClassRoom record);

    int insertSelective(RefClassRoom record);

    List<RefClassRoom> selectByExample(RefClassRoomExample example);

    RefClassRoom selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RefClassRoom record, @Param("example") RefClassRoomExample example);

    int updateByExample(@Param("record") RefClassRoom record, @Param("example") RefClassRoomExample example);

    int updateByPrimaryKeySelective(RefClassRoom record);

    int updateByPrimaryKey(RefClassRoom record);
}