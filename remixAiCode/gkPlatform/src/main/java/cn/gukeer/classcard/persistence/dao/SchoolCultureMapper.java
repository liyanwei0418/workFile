package cn.gukeer.classcard.persistence.dao;

import cn.gukeer.classcard.persistence.entity.SchoolCulture;
import cn.gukeer.classcard.persistence.entity.SchoolCultureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchoolCultureMapper {
    int deleteByExample(SchoolCultureExample example);

    int deleteByPrimaryKey(String id);

    int insert(SchoolCulture record);

    int insertSelective(SchoolCulture record);

    List<SchoolCulture> selectByExampleWithBLOBs(SchoolCultureExample example);

    List<SchoolCulture> selectByExample(SchoolCultureExample example);

    SchoolCulture selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SchoolCulture record, @Param("example") SchoolCultureExample example);

    int updateByExampleWithBLOBs(@Param("record") SchoolCulture record, @Param("example") SchoolCultureExample example);

    int updateByExample(@Param("record") SchoolCulture record, @Param("example") SchoolCultureExample example);

    int updateByPrimaryKeySelective(SchoolCulture record);

    int updateByPrimaryKeyWithBLOBs(SchoolCulture record);

    int updateByPrimaryKey(SchoolCulture record);
}