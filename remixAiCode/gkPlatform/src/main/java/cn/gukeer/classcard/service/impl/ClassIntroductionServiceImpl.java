package cn.gukeer.classcard.service.impl;

import cn.gukeer.classcard.service.ClassIntroductionService;
import cn.gukeer.classcard.persistence.dao.ClassIntroductionMapper;
import cn.gukeer.classcard.persistence.entity.ClassIntroduction;
import cn.gukeer.classcard.persistence.entity.ClassIntroductionExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by alpha on 2017/7/16.
 */
@Service
public class ClassIntroductionServiceImpl implements ClassIntroductionService {

    @Autowired
    ClassIntroductionMapper classIntroductionMapper;
    @Override
    public ClassIntroduction findClassIntroductionById(String id) {
        ClassIntroduction classIntroduction=classIntroductionMapper.selectByPrimaryKey(id);
        return classIntroduction;
    }

    @Override
    public int updateClassIntroduction(ClassIntroduction classIntroduction) {
        ClassIntroductionExample example=new ClassIntroductionExample();
        example.createCriteria().andIdEqualTo(classIntroduction.getId());

        int count=  classIntroductionMapper.updateByExampleSelective(classIntroduction,example);
        return count;
    }

    @Override
    public boolean insertClassIntroduction(ClassIntroduction classIntroduction) {
        int count=classIntroductionMapper.insertSelective(classIntroduction);
        if(count==0){
            return  false;
        }
        return true;
    }
}
