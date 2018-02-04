package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.SyncTeacher;
import cn.gukeer.platform.persistence.entity.SyncTeacherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SyncTeacherMapper {
    int deleteByExample(SyncTeacherExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SyncTeacher record);

    int insertSelective(SyncTeacher record);

    List<SyncTeacher> selectByExample(SyncTeacherExample example);

    SyncTeacher selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SyncTeacher record, @Param("example") SyncTeacherExample example);

    int updateByExample(@Param("record") SyncTeacher record, @Param("example") SyncTeacherExample example);

    int updateByPrimaryKeySelective(SyncTeacher record);

    int updateByPrimaryKey(SyncTeacher record);
}