package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.CommonlyUsedApp;
import cn.gukeer.platform.persistence.entity.CommonlyUsedAppExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommonlyUsedAppMapper {
    int deleteByExample(CommonlyUsedAppExample example);

    int insert(CommonlyUsedApp record);

    int insertSelective(CommonlyUsedApp record);

    List<CommonlyUsedApp> selectByExample(CommonlyUsedAppExample example);

    int updateByExampleSelective(@Param("record") CommonlyUsedApp record, @Param("example") CommonlyUsedAppExample example);

    int updateByExample(@Param("record") CommonlyUsedApp record, @Param("example") CommonlyUsedAppExample example);
}