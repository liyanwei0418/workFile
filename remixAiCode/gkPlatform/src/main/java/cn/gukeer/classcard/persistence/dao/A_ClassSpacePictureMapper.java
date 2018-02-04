package cn.gukeer.classcard.persistence.dao;


import cn.gukeer.classcard.persistence.entity.ClassSpacePicture;

import java.util.List;

/**
 * Created by alpha on 17-7-25.
 */
public interface A_ClassSpacePictureMapper {

    List<ClassSpacePicture> findThreePic(String classId);
}
