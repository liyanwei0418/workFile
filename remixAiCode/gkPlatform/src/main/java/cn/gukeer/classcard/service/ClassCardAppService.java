package cn.gukeer.classcard.service;

import cn.gukeer.classcard.modelView.ClassCardAppView;
import cn.gukeer.classcard.persistence.entity.ClassCardApp;
import cn.gukeer.classcard.persistence.entity.ClassCardAppRef;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by alpha on 18-1-9.
 */
public interface ClassCardAppService {

    PageInfo<ClassCardAppView> findAllClassCardApp(String schoolId,String appName,int pageNum, int pageSize);

    boolean insertClassCardApp(ClassCardApp classCardApp);

    boolean updateClassCardApp(ClassCardApp classCardApp);

    boolean deleteClassCardApp(List<String> appIds);

    ClassCardApp findClassCardAppById(String id);

    ClassCardAppRef findRefByClassCardIdAndAppId(String classCardId,String classCardAppId);

    int batchInsertClassCardAppRef(List<ClassCardAppRef> classCardAppRefs);

    List<ClassCardAppRef> findRefByClassCardId(String classCardId);

    List<ClassCardApp> findAppByIds(List<String> ids);

    boolean deleteClassCardAppRef(String classCardId, String appId);

    ClassCardApp findAppByName(String appName,String schoolId);

    ClassCardApp findAppByPackageName(String packageName,String schoolId);


}
