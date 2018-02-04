package cn.gukeer.classcard.service;

import cn.gukeer.classcard.modelView.ClassCardConfigView;
import cn.gukeer.classcard.persistence.entity.ClassCardConfig;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ClassCardConfigService {

    PageInfo<ClassCardConfigView> findAllConfigBySchoolId(String schoolId, int pageNum, int pageSize);

    PageInfo<ClassCardConfigView> findAllConfig(int pageNum, int pageSize);

    List<ClassCardConfigView> transforConfig(List<ClassCardConfigView> classCardConfigList);

    ClassCardConfigView transforConfigOne(ClassCardConfigView classCardConfigList);

    Integer insertClassCardNotify(ClassCardConfig classCardConfig);

    ClassCardConfigView findOneClassCardConfigById(String classCardConfigId);

    Integer updateClassCardConfig(ClassCardConfig classCardConfig);

    Integer deleteClassCardConfig(String[] configIdArr);
}
