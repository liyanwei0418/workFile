package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.GradeClass;
import cn.gukeer.platform.persistence.entity.GradeClassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GradeClassMapper {
    int deleteByExample(GradeClassExample example);

    int deleteByPrimaryKey(String id);

    int insert(GradeClass record);

    int insertSelective(GradeClass record);

    List<GradeClass> selectByExample(GradeClassExample example);

    List<GradeClass> selectXdBySchoolId(String schoolId);

    List<GradeClass> selectClassCascade(@Param("flag")String flag,@Param("xdId")String xdId,@Param("nj")String nj,@Param("schoolId")String schoolId);

    GradeClass selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GradeClass record, @Param("example") GradeClassExample example);

    int updateByExample(@Param("record") GradeClass record, @Param("example") GradeClassExample example);

    int updateByPrimaryKeySelective(GradeClass record);

    int updateByPrimaryKey(GradeClass record);
}