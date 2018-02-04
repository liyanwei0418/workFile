package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.TeachTable;
import cn.gukeer.platform.persistence.entity.TeachTableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeachTableMapper {
    int deleteByExample(TeachTableExample example);

    int deleteByPrimaryKey(String id);

    int insert(TeachTable record);

    int insertSelective(TeachTable record);

    List<TeachTable> selectByExample(TeachTableExample example);

    TeachTable selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TeachTable record, @Param("example") TeachTableExample example);

    int updateByExample(@Param("record") TeachTable record, @Param("example") TeachTableExample example);

    int updateByPrimaryKeySelective(TeachTable record);

    int updateByPrimaryKey(TeachTable record);
}