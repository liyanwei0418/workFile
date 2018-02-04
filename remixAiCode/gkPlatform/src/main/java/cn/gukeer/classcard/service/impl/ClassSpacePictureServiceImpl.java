package cn.gukeer.classcard.service.impl;

import cn.gukeer.classcard.persistence.dao.A_ClassSpacePictureMapper;
import cn.gukeer.classcard.persistence.dao.ClassSpacePictureMapper;
import cn.gukeer.classcard.persistence.entity.ClassSpacePicture;
import cn.gukeer.classcard.persistence.entity.ClassSpacePictureExample;
import cn.gukeer.classcard.service.ClassSpacePictureService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alpha on 17-7-18.
 */
@Service
public class ClassSpacePictureServiceImpl implements ClassSpacePictureService {
    @Autowired
    ClassSpacePictureMapper classSpacePictureMapper;

    @Autowired
    A_ClassSpacePictureMapper a_classSpacePictureMapper;


    @Override
    public int insertPictrue(ClassSpacePicture classSpacePicture) {
        int count = classSpacePictureMapper.insertSelective(classSpacePicture);
        return count;
    }

    @Override
    public ClassSpacePicture findPicById(String id) {
        ClassSpacePicture classSpacePicture = classSpacePictureMapper.selectByPrimaryKey(id);
        return classSpacePicture;
    }

    @Override
    public int updatPicture(ClassSpacePicture classSpacePicture) {
        ClassSpacePictureExample example = new ClassSpacePictureExample();
        example.createCriteria().andIdEqualTo(classSpacePicture.getId());
        int count = classSpacePictureMapper.updateByExampleSelective(classSpacePicture, example);
        return count;
    }

    @Override
    public int deletePicture(String id) {
        int count = classSpacePictureMapper.deleteByPrimaryKey(id);
        return count;
    }

    @Override
    public PageInfo<ClassSpacePicture> findPicByClassCardIdAndPid(String classCardId, String pid, int pageNum, int pageSize) {
        ClassSpacePictureExample example = new ClassSpacePictureExample();
        example.createCriteria().andClassCardIdEqualTo(classCardId).andPidEqualTo(pid);
        example.setOrderByClause("create_date desc");
        if (pageSize != -1) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<ClassSpacePicture> classSpacePictures = classSpacePictureMapper.selectByExample(example);
        PageInfo<ClassSpacePicture> pageInfo = new PageInfo<>(classSpacePictures);
        return pageInfo;
    }

    @Override
    public List<ClassSpacePicture> findPicByClassCardIdAndPid(String classCardId, String pid) {
        ClassSpacePictureExample example = new ClassSpacePictureExample();
        example.createCriteria().andClassCardIdEqualTo(classCardId).andPidEqualTo(pid);
        example.setOrderByClause("create_date desc");
        List<ClassSpacePicture> classSpacePictures = classSpacePictureMapper.selectByExample(example);
        return classSpacePictures;
    }

    @Override
    public List<ClassSpacePicture> findThreePicForIndex(String classId) {
        List<ClassSpacePicture> list = a_classSpacePictureMapper.findThreePic(classId);
        return list;
    }

    @Override
    public String getThumbnail(String oriUrl) {
        if (StringUtils.isBlank(oriUrl)) {
            return "";
        }
        String prefix = oriUrl.substring(0, oriUrl.lastIndexOf("."));
        String suffix = ".jpg";
        String tmpurl = prefix + suffix;
        StringBuilder stringBuilder = new StringBuilder();
        String[] arr = tmpurl.split("/");
        for (int i = 0; i < arr.length; i++) {
            stringBuilder.append(arr[i]);
            if (i == arr.length - 2) {
                stringBuilder.append("/thumbnail");
            }
            if (i != arr.length - 1) {
                stringBuilder.append("/");
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public List<ClassSpacePicture> findPicByPid(String pid) {
        ClassSpacePictureExample example = new ClassSpacePictureExample();
        example.createCriteria().andPidEqualTo(pid).andDelFlagEqualTo(0);
        List<ClassSpacePicture> pictures = classSpacePictureMapper.selectByExample(example);
        return pictures;
    }

    @Override
    public PageInfo<ClassSpacePicture> findPicByPid_(String pid, int pageNum, int pageSize) {
        if (pageSize != -1) {
            PageHelper.startPage(pageNum, pageSize);
        }
        ClassSpacePictureExample example = new ClassSpacePictureExample();
        example.createCriteria().andPidEqualTo(pid).andDelFlagEqualTo(0);
        example.setOrderByClause("create_date desc");
        List<ClassSpacePicture> pictures = classSpacePictureMapper.selectByExample(example);
        PageInfo<ClassSpacePicture> pageInfo = new PageInfo<>(pictures);
        return pageInfo;
    }

}
