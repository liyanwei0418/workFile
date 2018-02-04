package cn.gukeer.classcard.persistence.dao;

import cn.gukeer.classcard.modelView.ClassCardCustomPageView;

import java.util.List;

public interface A_ClassCardCustomMapper {
    List<ClassCardCustomPageView> findAllCustonPageBySchoolId(String schoolId);

    ClassCardCustomPageView findViewOneById(String pageId);
}
