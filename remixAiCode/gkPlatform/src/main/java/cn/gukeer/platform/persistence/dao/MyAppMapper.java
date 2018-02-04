package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.MyApp;
import cn.gukeer.platform.persistence.entity.MyAppExample;
import cn.gukeer.platform.persistence.entity.MyAppKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MyAppMapper {
    int deleteByExample(MyAppExample example);

    int deleteByPrimaryKey(MyAppKey key);

    int insert(MyApp record);

    int insertSelective(MyApp record);

    List<MyApp> selectByExample(MyAppExample example);

    MyApp selectByPrimaryKey(MyAppKey key);

    int updateByExampleSelective(@Param("record") MyApp record, @Param("example") MyAppExample example);

    int updateByExample(@Param("record") MyApp record, @Param("example") MyAppExample example);

    int updateByPrimaryKeySelective(MyApp record);

    int updateByPrimaryKey(MyApp record);
}