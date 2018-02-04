package cn.gukeer.classcard.service.impl;

import cn.gukeer.classcard.persistence.dao.ClassCardConfigRefMapper;
import cn.gukeer.classcard.persistence.entity.ClassCardConfigRef;
import cn.gukeer.classcard.persistence.entity.ClassCardConfigRefExample;
import cn.gukeer.classcard.service.ClassCardConfigRefService;
import cn.gukeer.common.utils.PrimaryKey;
import cn.gukeer.platform.persistence.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClassCardConfigRefServiceImpl implements ClassCardConfigRefService {


    @Autowired
    ClassCardConfigRefMapper classCardConfigRefMapper;

    @Override
    public Integer insertClassCardConfigRef(String refId, String schoolId, List<String> classCardList) {
        int count = 0;

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        for (int i = 0; i < classCardList.size(); i++) {
            ClassCardConfigRef classCardConfigRef = new ClassCardConfigRef();
            classCardConfigRef.setId(PrimaryKey.get());
            classCardConfigRef.setClassCardConfigId(refId);
            classCardConfigRef.setClassCardId(classCardList.get(i));
            classCardConfigRef.setSchoolId(schoolId);
            count += classCardConfigRefMapper.insertSelective(classCardConfigRef);
        }
        return count;
    }

    @Override
    public Integer deleteClassCardConfigRef(String[] configIdArr, String schoolId) {
        int count = 0;
        ClassCardConfigRefExample example = new ClassCardConfigRefExample();
        for (String configId : configIdArr) {
            example.createCriteria().andSchoolIdEqualTo(schoolId).andClassCardConfigIdEqualTo(configId);
            count += classCardConfigRefMapper.deleteByExample(example);
            example.clear();
        }
        return count;
    }

    @Override
    public Integer updateClassCardConfigRef(String id, String schoolId, List<String> classCardList) {
        int count = 0;
        //先删除，然后重新插入新的数据，修改表中的数据没有意义。
        String[] configIdArr = {id};
        count += deleteClassCardConfigRef(configIdArr, schoolId);
        count += insertClassCardConfigRef(id, schoolId, classCardList);
        return count;
    }

    @Override
    public String findClassCardConfigCheckedIds(String classCardConfigId, String schoolId) {
        String checkedIds = "";
        ClassCardConfigRefExample example = new ClassCardConfigRefExample();
        example.createCriteria().andClassCardConfigIdEqualTo(classCardConfigId).andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0);
        List<ClassCardConfigRef> checkedIdList = classCardConfigRefMapper.selectByExample(example);
        for (ClassCardConfigRef temp : checkedIdList) {
            checkedIds += temp.getClassCardId() + ",";
        }
        return checkedIds;
    }

    @Override
    public List<ClassCardConfigRef> findRefByConfigId(String configId) {
        ClassCardConfigRefExample example = new ClassCardConfigRefExample();
        example.createCriteria().andClassCardConfigIdEqualTo(configId).andDelFlagEqualTo(0);
        List<ClassCardConfigRef> refList = classCardConfigRefMapper.selectByExample(example);
        return refList;
    }
}
