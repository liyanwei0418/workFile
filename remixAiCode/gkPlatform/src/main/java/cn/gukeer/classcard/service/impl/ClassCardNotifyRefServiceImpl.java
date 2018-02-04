package cn.gukeer.classcard.service.impl;

import cn.gukeer.classcard.persistence.dao.ClassCardNotifyRefMapper;
import cn.gukeer.classcard.persistence.entity.ClassCardNotifyRef;
import cn.gukeer.classcard.persistence.entity.ClassCardNotifyRefExample;
import cn.gukeer.classcard.service.ClassCardNotifyRefService;
import cn.gukeer.common.utils.PrimaryKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by alpha on 17-7-5.
 */
@Service
public class ClassCardNotifyRefServiceImpl implements ClassCardNotifyRefService {

    @Autowired
    ClassCardNotifyRefMapper classCardNotifyRefMapper;

    @Override
    public boolean insertClassCardNotifyRef(String checkedIds, String notifyId, String schoolId, String createBy) {
        String[] classCardIds = checkedIds.split(",");
        boolean rtn = false;
        int count = 0;
        for (String id : classCardIds) {
            ClassCardNotifyRef classCardNotifyRef = new ClassCardNotifyRef();
            classCardNotifyRef.setId(PrimaryKey.get());
            classCardNotifyRef.setClassCardId(id);
            classCardNotifyRef.setClassCardNotifyId(notifyId);
            classCardNotifyRef.setCreateBy(createBy);
            classCardNotifyRef.setCreateDate(System.currentTimeMillis());
            classCardNotifyRef.setSchoolId(schoolId);
            count += classCardNotifyRefMapper.insert(classCardNotifyRef);
        }
        if (count == classCardIds.length) {
            rtn = true;
        }
        return rtn;
    }

    @Override
    public int deleteClassCardNotifyRef(String unCheckedIds, String notifyId) {
        String[] classCardIds = unCheckedIds.split(",");
        ClassCardNotifyRefExample example = new ClassCardNotifyRefExample();
        example.createCriteria().andClassCardIdIn(Arrays.asList(classCardIds)).andClassCardNotifyIdEqualTo(notifyId);

        int count = classCardNotifyRefMapper.deleteByExample(example);
        return count;
    }

    @Override
    public List<ClassCardNotifyRef> findAllByNotifyId(String notifyId) {
        ClassCardNotifyRefExample example = new ClassCardNotifyRefExample();
        example.createCriteria().andClassCardNotifyIdEqualTo(notifyId);
        List<ClassCardNotifyRef> classCardNotifyRefs = classCardNotifyRefMapper.selectByExample(example);
        return classCardNotifyRefs;
    }

    @Override
    public int deleteRefByNotifyId(String[] notifyId) {
        ClassCardNotifyRefExample example = new ClassCardNotifyRefExample();
        example.createCriteria().andClassCardNotifyIdIn(Arrays.asList(notifyId));
        int count = classCardNotifyRefMapper.deleteByExample(example);
        return count;
    }

    @Override
    public List<ClassCardNotifyRef> findAllByClassCardIds(List<String> classCardIds) {
        List<ClassCardNotifyRef> classCardNotifyRefs = new ArrayList<>();
        if (classCardIds != null && classCardIds.size() > 0) {
            ClassCardNotifyRefExample example = new ClassCardNotifyRefExample();
            example.createCriteria().andClassCardIdIn(classCardIds);
            classCardNotifyRefs = classCardNotifyRefMapper.selectByExample(example);
        }
        return classCardNotifyRefs;
    }

}
