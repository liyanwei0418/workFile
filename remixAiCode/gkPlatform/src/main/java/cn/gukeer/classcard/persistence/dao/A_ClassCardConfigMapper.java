package cn.gukeer.classcard.persistence.dao;

import cn.gukeer.classcard.modelView.ClassCardConfigScreenOffView;
import cn.gukeer.classcard.modelView.ClassCardConfigView;

import java.util.List;

public interface A_ClassCardConfigMapper {

    List<ClassCardConfigView> findAllConfigViewBySchoolId(String schoolId);

    List<ClassCardConfigView> findAllConfigView();

    ClassCardConfigView findOneConfigViewById(String id);

    List<ClassCardConfigScreenOffView> findListClassCardScreenOffByCCCId(String classCardConfigId);

}
