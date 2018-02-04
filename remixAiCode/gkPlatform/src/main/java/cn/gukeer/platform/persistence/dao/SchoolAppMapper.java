package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.SchoolApp;
import cn.gukeer.platform.persistence.entity.SchoolAppExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchoolAppMapper {
    int deleteByExample(SchoolAppExample example);

    int insert(SchoolApp record);

    int insertSelective(SchoolApp record);

    List<SchoolApp> selectByExample(SchoolAppExample example);

    int updateByExampleSelective(@Param("record") SchoolApp record, @Param("example") SchoolAppExample example);

    int updateByExample(@Param("record") SchoolApp record, @Param("example") SchoolAppExample example);
}