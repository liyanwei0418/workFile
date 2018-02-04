package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.AppRole;
import cn.gukeer.platform.persistence.entity.AppRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppRoleMapper {
    int deleteByExample(AppRoleExample example);

    int insert(AppRole record);

    int insertSelective(AppRole record);

    List<AppRole> selectByExample(AppRoleExample example);

    int updateByExampleSelective(@Param("record") AppRole record, @Param("example") AppRoleExample example);

    int updateByExample(@Param("record") AppRole record, @Param("example") AppRoleExample example);
}