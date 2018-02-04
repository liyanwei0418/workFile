package cn.gukeer.platform.service;

import cn.gukeer.platform.persistence.entity.App;
import cn.gukeer.platform.persistence.entity.CommonlyUsedApp;
import cn.gukeer.platform.persistence.entity.MyApp;
import cn.gukeer.platform.persistence.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by conn on 2016/8/8.
 */
public interface AppService {

    List<App> findByName(String name);

    PageInfo<App> findAllList(int pageNum, int pageSize);

    List<App> findNotHaveList(String schoolId);

    App findAppById(String id);

    int updateApp(App app);

    int insertApp(App app);

    int saveApp(App app);

    List<App> findAppByNameAndCategoryAndTargetUser(Integer permissionFlag, String name, String category, String targetUser, String area, String schoolId);

    List<App> findAppBySchool(String schoolId);

    List<App> selectAppByUserIdAndUserType(User user);

    List<MyApp> selectAppByUser(User user);

    List<App> selectAppIdInList(List<String> idList);

    List<App> findAppByCriteria(Integer permission);

    List<App> findAppByIdDefaultByTargetUser(String targetUser, Integer userPermission);

    User findUserByUsername(String username);

    //常用应用删除
    int deleteCommonUsedApp(CommonlyUsedApp commonlyUsedApp);

    //常用应用添加
    int addCommonUsedApp(CommonlyUsedApp commonlyUsedApp);

    //常用应用批量添加
    int insertBatchCommonUsedApp(List<CommonlyUsedApp> list);

    //查询常用应用列表
    List<App> findCommonUsedAppList(String userId);

    //查询默认应用列表
    List<App> findDefaultAppList(Integer permissionFlag);

    //查询 已经推送/已经上线的应用列表 1 已推送, 2 已上线, 3 已下线 4 其他
    List<App> findAppBySchoolId(String schoolId, Integer status);

    //查询机构所有未添加的应用（机构可使用+默认应用）
    List<App> findOtherAppByUser(User user);
}
