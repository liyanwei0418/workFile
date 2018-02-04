package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.App;
import cn.gukeer.platform.persistence.entity.CommonlyUsedApp;
import cn.gukeer.platform.persistence.entity.MyApp;
import cn.gukeer.platform.persistence.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by chen on 2016/9/12.
 */
public interface A_AppExtensionMapper {
    List<App> selectAppByUserIdAndUserType(User record);

    List<App> selectAppByIsDefault();

    int deleteMyApp(MyApp myApp);

    List<App> findAppBySchool(String schoolId);

    List<App> findNotHaveList(String schoolId);
    
    List<App> findByName(String name);

    int insertBatchMyApp(@Param("list") List<MyApp> list);

    int insertBatchCommonUsedApp(@Param("list") List<CommonlyUsedApp> list);

}
