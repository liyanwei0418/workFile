package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.modelView.RefClassRoomView;
import cn.gukeer.platform.persistence.entity.ClassRoom;
import cn.gukeer.platform.persistence.entity.RefClassRoom;
import cn.gukeer.platform.persistence.entity.SchoolType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by LL on 2017/5/5.
 */
public interface A_RefClassRoomMapper {
    List<RefClassRoom> findAllRefClassRoomByClassRoomId(@Param("classRoomListByschoolId") List<ClassRoom> classRoomListByschoolId);

    void batchInsertRefClassRoom(@Param("correctRefClassRoomList")List<RefClassRoom> correctRefClassRoomList);

    List<RefClassRoomView> getRefClassRoomList(@Param("schoolId")String schoolId, @Param("cycleId")String cycleId,@Param("nj")int nj,@Param("xdId")String xdId);

    RefClassRoomView findRefClassRoomViewByRefId( @Param("refId")String refId);
}
