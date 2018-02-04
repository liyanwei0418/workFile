package cn.gukeer.platform.service;

import cn.gukeer.platform.persistence.entity.App;
import cn.gukeer.platform.persistence.entity.School;
import cn.gukeer.platform.persistence.entity.SchoolApp;

import java.util.List;

/**
 * Created by conn on 2016/8/8.
 */
public interface SchoolAppService {

    int insertSchoolApp(SchoolApp schoolApp);

    int deleteSchoolApp(SchoolApp schoolApp);

    //根据应用删除已经推送的学校关联
    int deleteSchoolAppByAppId(String appId);

    List<SchoolApp> findAllListBySchoolId(String schoolId);

    List<App> findAppListBySchoolId(String schoolId);

    List<School> findSchoolListByAppId(String appId);

    //应用下线，取消用户添加的记录，删除包括：常用应用记录，个人添加应用记录
    int deleteAddedApp(List<String> appIdList);

}
