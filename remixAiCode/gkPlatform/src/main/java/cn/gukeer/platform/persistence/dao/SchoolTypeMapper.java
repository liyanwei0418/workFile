package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.SchoolType;
import cn.gukeer.platform.persistence.entity.SchoolTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchoolTypeMapper {
    int deleteByExample(SchoolTypeExample example);

    int deleteByPrimaryKey(String id);

    int insert(SchoolType record);

    int insertSelective(SchoolType record);

    List<SchoolType> selectByExample(SchoolTypeExample example);

    SchoolType selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SchoolType record, @Param("example") SchoolTypeExample example);

    int updateByExample(@Param("record") SchoolType record, @Param("example") SchoolTypeExample example);

    int updateByPrimaryKeySelective(SchoolType record);

    int updateByPrimaryKey(SchoolType record);
}