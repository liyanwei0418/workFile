package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.TeacherClass;
import cn.gukeer.platform.persistence.entity.TeacherClassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeacherClassMapper {
    int deleteByExample(TeacherClassExample example);

    int insert(TeacherClass record);

    int insertSelective(TeacherClass record);

    List<TeacherClass> selectByExample(TeacherClassExample example);

    int updateByExampleSelective(@Param("record") TeacherClass record, @Param("example") TeacherClassExample example);

    int updateByExample(@Param("record") TeacherClass record, @Param("example") TeacherClassExample example);
}