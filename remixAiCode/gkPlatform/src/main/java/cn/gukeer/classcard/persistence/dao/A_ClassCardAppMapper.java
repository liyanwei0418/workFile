package cn.gukeer.classcard.persistence.dao;

import cn.gukeer.classcard.modelView.ClassCardAppView;
import cn.gukeer.classcard.persistence.entity.ClassCardAppRef;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by alpha on 17-7-3.
 */
public interface A_ClassCardAppMapper {

    List<ClassCardAppView> findAllClassCardAppView(@Param("schoolId") String schoolId,@Param("appName") String appName);

    int batchDelete(List<String> appIds);

    int batchInsertClassCardAppRef(@Param("classCardAppRefs")List<ClassCardAppRef> classCardAppRefs);

}



