package cn.gukeer.classcard.service.impl;

import cn.gukeer.classcard.persistence.dao.ClassCardCustomContentRefMapper;
import cn.gukeer.classcard.persistence.entity.ClassCardCustomContentRef;
import cn.gukeer.classcard.persistence.entity.ClassCardCustomContentRefExample;
import cn.gukeer.classcard.service.ClassCardCustomContentRefService;
import cn.gukeer.common.utils.PrimaryKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by leeyh on 2018/1/18.
 */

@Service
public class ClassCardCustomContentRefServiceImpl implements ClassCardCustomContentRefService {

    @Autowired
    ClassCardCustomContentRefMapper contentRefMapper;

    @Override
    public Integer deleteCustomContentById(String pageId) {
        ClassCardCustomContentRefExample example = new ClassCardCustomContentRefExample();
        example.createCriteria().andPageIdEqualTo(pageId);
        return contentRefMapper.deleteByExample(example);
    }

    @Override
    public Integer insertContent(String pageId, String names, String contents) {
        int count = 0;
        String[] nameList = names.split(",");
        String[] contentList = contents.split("##@@");
        for (int i = 0; i < nameList.length; i++) {
            ClassCardCustomContentRef contentRef = new ClassCardCustomContentRef();
            contentRef.setId(PrimaryKey.get());
            contentRef.setName(nameList[i]);
            contentRef.setPageId(pageId);
            contentRef.setValue(contentList[i]);
            count += contentRefMapper.insertSelective(contentRef);
        }
        return count;
    }

    @Override
    public Integer updateContent(String pageId, String names, String contents) {
        int count = 0;
        count += deleteCustomContentById(pageId);
        count += insertContent(pageId,names,contents);
        return count;
    }

    @Override
    public List<ClassCardCustomContentRef> findContentByPageId(String pageId) {
        ClassCardCustomContentRefExample example = new ClassCardCustomContentRefExample();
        example.createCriteria().andPageIdEqualTo(pageId);
        return contentRefMapper.selectByExample(example);
    }
}
