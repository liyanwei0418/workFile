package cn.gukeer.classcard.service.impl;


import cn.gukeer.classcard.persistence.dao.ClassCardCustomRefMapper;
import cn.gukeer.classcard.persistence.entity.ClassCardCustomRef;
import cn.gukeer.classcard.persistence.entity.ClassCardCustomRefExample;
import cn.gukeer.classcard.service.ClassCardCustomRefService;
import cn.gukeer.common.utils.PrimaryKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by leeyh on 2018/1/18.
 */

@Service
public class ClassCardCustomRefServiceImpl implements ClassCardCustomRefService {

    @Autowired
    ClassCardCustomRefMapper customRefMapper;

    @Override
    public Integer deleteCustomRefById(String pageId) {
        ClassCardCustomRefExample example = new ClassCardCustomRefExample();
        example.createCriteria().andPageIdEqualTo(pageId).andDelFlagEqualTo(0);
        return customRefMapper.deleteByExample(example);
    }

    @Override
    public Integer updatePageRelease(String classCardList, String pageId) {
        int count = 0;
        String[] classCards = classCardList.split(",");
        deleteCustomRefById(pageId);
        for (int i = 0; i < classCards.length; i++) {
            ClassCardCustomRef customRef = new ClassCardCustomRef();
            customRef.setId(PrimaryKey.get());
            customRef.setPageId(pageId);
            customRef.setClassCardId(classCards[i]);
            count += customRefMapper.insertSelective(customRef);
        }
        return count;
    }

    /**
     * 查询自定义界面发布的设备，拼接为string返回，用逗号分隔
     * @param pageId
     * @return
     */
    @Override
    public String findCheckIds(String pageId) {
        String checkIds = "";
        ClassCardCustomRefExample example = new ClassCardCustomRefExample();
        example.createCriteria().andPageIdEqualTo(pageId).andDelFlagEqualTo(0);
        List<ClassCardCustomRef> customRefs = customRefMapper.selectByExample(example);
        for (ClassCardCustomRef customRef : customRefs) {
            checkIds += customRef.getClassCardId() + ",";
        }
        return checkIds;
    }
}
