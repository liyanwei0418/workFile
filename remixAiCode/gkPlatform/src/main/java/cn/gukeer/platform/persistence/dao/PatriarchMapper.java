package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.Patriarch;
import cn.gukeer.platform.persistence.entity.PatriarchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PatriarchMapper {
    int deleteByExample(PatriarchExample example);

    int deleteByPrimaryKey(String id);

    int insert(Patriarch record);

    int insertSelective(Patriarch record);

    List<Patriarch> selectByExample(PatriarchExample example);

    Patriarch selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Patriarch record, @Param("example") PatriarchExample example);

    int updateByExample(@Param("record") Patriarch record, @Param("example") PatriarchExample example);

    int updateByPrimaryKeySelective(Patriarch record);

    int updateByPrimaryKey(Patriarch record);
}