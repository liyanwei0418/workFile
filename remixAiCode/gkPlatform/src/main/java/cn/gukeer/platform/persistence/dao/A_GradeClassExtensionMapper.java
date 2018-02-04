package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.GradeClass;

import java.util.List;

/**
 * Created by conn on 2016/8/23.
 */
public interface A_GradeClassExtensionMapper { 
    int changeDelFlag(String id);
    int batchInsertGradeClass(List<GradeClass> list);


}
