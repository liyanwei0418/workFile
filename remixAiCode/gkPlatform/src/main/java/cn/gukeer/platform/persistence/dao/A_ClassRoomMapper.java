package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.ClassRoom;
import cn.gukeer.platform.persistence.entity.RefRoomCycle;
import cn.gukeer.platform.persistence.entity.SchoolType;
import com.google.gson.JsonElement;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by LL on 2017/5/8.
 */
public interface A_ClassRoomMapper {
    List<ClassRoom> findAllTeachBuilding(@Param("schoolId") String schoolId);

    int batchSaveRefRoomCycle(@Param("list") List<RefRoomCycle> list);

    List<ClassRoom> findBuildingBySchoolTypeList(@Param("schoolTypeList") List<SchoolType> schoolTypeList);

    List<ClassRoom> findBuildingByschoolTypeId(@Param("schoolTypeId")String schoolTypeId);

    void insertClassRoomBatch(@Param("roomList")List<ClassRoom> roomList);

    List<ClassRoom> findCascadeClassRoom(@Param("flag")String flag,@Param("xqId")String xqId,
                                         @Param("teachBuilding")String teachBuilding,@Param("floor")String floor,@Param("schoolId")String schoolId);
}
