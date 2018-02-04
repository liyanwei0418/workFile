package cn.gukeer.classcard.persistence.dao;

import cn.gukeer.classcard.persistence.entity.ClassSpacePicture;
import cn.gukeer.classcard.persistence.entity.ClassSpacePictureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassSpacePictureMapper {
    int deleteByExample(ClassSpacePictureExample example);

    int deleteByPrimaryKey(String id);

    int insert(ClassSpacePicture record);

    int insertSelective(ClassSpacePicture record);

    List<ClassSpacePicture> selectByExample(ClassSpacePictureExample example);

    ClassSpacePicture selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ClassSpacePicture record, @Param("example") ClassSpacePictureExample example);

    int updateByExample(@Param("record") ClassSpacePicture record, @Param("example") ClassSpacePictureExample example);

    int updateByPrimaryKeySelective(ClassSpacePicture record);

    int updateByPrimaryKey(ClassSpacePicture record);
}