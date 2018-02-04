package cn.gukeer.classcard.service.impl;

import cn.gukeer.classcard.persistence.dao.ClassCardCustomTemplateMapper;
import cn.gukeer.classcard.persistence.entity.ClassCardCustomTemplate;
import cn.gukeer.classcard.persistence.entity.ClassCardCustomTemplateExample;
import cn.gukeer.classcard.service.ClassCardCustomTemplateService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by leeyh on 2018/1/18.
 */
@Service
public class ClassCardCustomTemplateServiceImpl implements ClassCardCustomTemplateService {

    @Autowired
    ClassCardCustomTemplateMapper templateMapper;

    @Override
    public Integer deleteTemplateById(String id) {
        return templateMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<ClassCardCustomTemplate> findAllTemplateBySchoolId(String schoolId, int pageNum, int pageSize) {
        ClassCardCustomTemplateExample example = new ClassCardCustomTemplateExample();
        //有共享的模板也可以使用。两个查询结果的并集去重？
        if (pageSize != -1) {
            PageHelper.startPage(pageNum,pageSize);
        }
        example.createCriteria().andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0);
        List<ClassCardCustomTemplate> templates = templateMapper.selectByExample(example);
        PageInfo<ClassCardCustomTemplate> pageInfo = new PageInfo<>(templates);
        return pageInfo;
    }

    @Override
    public ClassCardCustomTemplate findOneById(String templateId) {

        return templateMapper.selectByPrimaryKey(templateId);
    }
}
