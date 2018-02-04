package cn.gukeer.classcard.service;

import cn.gukeer.classcard.persistence.entity.ClassCardCustomTemplate;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by leeyh on 2018/1/18.
 */
public interface ClassCardCustomTemplateService {
    Integer deleteTemplateById(String id);

    PageInfo<ClassCardCustomTemplate> findAllTemplateBySchoolId(String schoolId,int pageNum,int pageSize);

    ClassCardCustomTemplate findOneById(String templateId);
}
