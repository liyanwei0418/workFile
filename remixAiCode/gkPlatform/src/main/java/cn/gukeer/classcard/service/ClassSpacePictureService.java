package cn.gukeer.classcard.service;

import cn.gukeer.classcard.persistence.entity.ClassSpacePicture;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by alpha on 17-7-18.
 */
public interface ClassSpacePictureService {

    int insertPictrue(ClassSpacePicture classSpacePicture);

    ClassSpacePicture findPicById(String id);

    int updatPicture(ClassSpacePicture classSpacePicture);

    int deletePicture(String id);

    PageInfo<ClassSpacePicture> findPicByClassCardIdAndPid(String classCardId, String pid, int pageNum, int pageSize);

    List<ClassSpacePicture> findPicByClassCardIdAndPid(String classCardId, String pid);

    //班级空间接口首页的３张合集的照片
    List<ClassSpacePicture> findThreePicForIndex(String classId);

    public String getThumbnail(String oriUrl);

    List<ClassSpacePicture> findPicByPid(String pid);

    PageInfo<ClassSpacePicture>findPicByPid_(String pid,int pageNum,int pageSize);




}
