package cn.gukeer.classcard.service;

import cn.gukeer.classcard.persistence.entity.ClassCardConfigRef;

import java.util.List;

public interface ClassCardConfigRefService {

    Integer insertClassCardConfigRef(String refId, String schoolId , List<String> classCardList);

    Integer deleteClassCardConfigRef(String[] configIdArr, String schoolId);

    Integer updateClassCardConfigRef(String id, String schoolId , List<String> classCardList);

    String findClassCardConfigCheckedIds(String classCardConfigId, String schoolId);

    List<ClassCardConfigRef> findRefByConfigId(String configId);
}
