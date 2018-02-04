package cn.gukeer.classcard.service.impl;

import cn.gukeer.classcard.persistence.dao.ClassSpaceVideoMapper;
import cn.gukeer.classcard.persistence.entity.ClassSpaceVideo;
import cn.gukeer.classcard.service.ClassSpaceVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by alpha on 18-1-10.
 */
@Service
public class ClassSpaceVideoServiceImpl implements ClassSpaceVideoService {
    @Autowired
    ClassSpaceVideoMapper classSpaceVideoMapper;

    @Override
    public boolean deleteVideo(String id) {
        boolean flag = false;
        int count = classSpaceVideoMapper.deleteByPrimaryKey(id);
        if (count == 1) {
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean insertVideo(ClassSpaceVideo classSpaceVideo) {
        boolean flag = false;
        int count = classSpaceVideoMapper.insertSelective(classSpaceVideo);
        if (count == 1) {
            flag = true;
        }
        return flag;
    }

    @Override
    public ClassSpaceVideo findById(String id) {
        ClassSpaceVideo classSpaceVideo = classSpaceVideoMapper.selectByPrimaryKey(id);
        return classSpaceVideo;
    }
}
