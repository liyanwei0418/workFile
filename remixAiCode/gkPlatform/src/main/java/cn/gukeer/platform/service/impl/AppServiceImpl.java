package cn.gukeer.platform.service.impl;

import cn.gukeer.common.service.BasicService;
import cn.gukeer.common.utils.NumberConvertUtil;
import cn.gukeer.platform.persistence.dao.*;
import cn.gukeer.platform.persistence.entity.*;
import cn.gukeer.platform.service.AppService;
import cn.gukeer.platform.service.SchoolAppService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by conn on 2016/8/8.
 */
@Service
public class AppServiceImpl extends BasicService implements AppService {

    @Autowired
    AppMapper appMapper;

    @Autowired
    A_AppExtensionMapper appExtensionMapper;

    @Autowired
    MyAppMapper myAppMapper;

    @Autowired
    SchoolAppMapper schoolAppMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    CommonlyUsedAppMapper commonUsedAppMapper;

    @Autowired
    SchoolAppService schoolAppService;

    @Override
    public List<App> findByName(String name) {
        return appExtensionMapper.findByName(name);
    }

    @Override
    public PageInfo<App> findAllList(int pageNum, int pageSize) {

        AppExample example = new AppExample();
        example.createCriteria().andDelFlagEqualTo(0);
        example.setOrderByClause("create_date desc");

        PageHelper.startPage(pageNum, pageSize);
        List<App> list = appMapper.selectByExample(example);
        PageInfo<App> pageInfo = new PageInfo<App>(list);

        return pageInfo;
    }

    @Override
    public List<App> findNotHaveList(String schoolId) {
        return appExtensionMapper.findNotHaveList(schoolId);
    }

    @Override
    public App findAppById(String id) {

        App app = appMapper.selectByPrimaryKey(id);
        return app;
    }

    @Override
    public int updateApp(App app) {
        AppExample example = new AppExample();
        example.createCriteria().andIdEqualTo(app.getId());
        int count = appMapper.updateByExampleSelective(app, example);
        return count;
    }

    @Override
    public int insertApp(App app) {
        int count = appMapper.insertSelective(app);
        return count;
    }

    @Override
    public int saveApp(App app) {
        int count = 0;
        App findApp = appMapper.selectByPrimaryKey(app.getId());
        if (null == findApp) {
            count = appMapper.insertSelective(app);
        } else {
            count = appMapper.updateByPrimaryKeySelective(app);
        }
        return count;
    }

    @Override
    public List<App> findAppBySchool(String schoolId) {
        return appExtensionMapper.findAppBySchool(schoolId);
    }

    @Override
    public List<App> selectAppByUserIdAndUserType(User user) {
        return appExtensionMapper.selectAppByUserIdAndUserType(user);
    }

    @Override
    public List<MyApp> selectAppByUser(User user) {
        MyAppExample example = new MyAppExample();
        example.createCriteria().andUserIdEqualTo(user.getId());
        List<MyApp> myAppList = myAppMapper.selectByExample(example);
        return myAppList;
    }

    @Override
    public List<App> selectAppIdInList(List<String> idList) {
        AppExample appExample = new AppExample();
        appExample.createCriteria().andIdIn(idList).andDelFlagEqualTo(0);
        List<App> myApps = appMapper.selectByExample(appExample);
        return myApps;
    }

    @Override
    public List<App> findAppByNameAndCategoryAndTargetUser(Integer permissionFlag, String name, String category, String targetUser, String area, String schoolId) {

        List<Integer> permission = new ArrayList<>();
        permission.add(3);
        permission.add(permissionFlag);

        AppExample example = new AppExample();
        AppExample.Criteria criteria = example.createCriteria();
        criteria.andDelFlagEqualTo(0).andAppPermissionIn(permission);

        if (StringUtil.isNotEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        if (StringUtil.isNotEmpty(category) && !category.equals("all")) {
            criteria.andCategoryEqualTo(category);
        }
        if (StringUtil.isNotEmpty(targetUser) && NumberConvertUtil.convertS2I(targetUser) > 0 && !targetUser.equals("all")) {
            criteria.andTargetUserLike("%" + targetUser + "%");
        }

       /* if (NumberConvertUtil.convert S2I(area) == 0){
            return findAppByCriteria();
        }*/

        List<App> defaultAppList = findDefaultAppList(permissionFlag);//系统默认应用
        List<App> schoolAppList = findAppBySchoolId(schoolId, NumberConvertUtil.convertS2I(area));//已推送/已上线给学校的应用

        schoolAppList.addAll(defaultAppList);//将默认应用和推送应用联合
        List<String> schoolAppIdList = new ArrayList<>();
        for (App app : schoolAppList) {
            schoolAppIdList.add(app.getId());
        }

        criteria.andIdIn(schoolAppIdList);//要在学校能看到的应用范围内查询

        List<App> appList = appMapper.selectByExample(example);
        return appList;
    }

    @Override
    public List<App> findAppByCriteria(Integer permission) {
        AppExample appExample = new AppExample();
        List<Integer> permissionList = new ArrayList<>();

        if (permission == -1) {
            permissionList.add(1);
            permissionList.add(2);
        } else {
            permissionList.add(permission);
        }


        permissionList.add(3);

        AppExample.Criteria criteria = appExample.createCriteria();
        criteria.andDelFlagEqualTo(0).andAppPermissionIn(permissionList);

        List<App> appList = appMapper.selectByExample(appExample);
        return appList;
    }

    @Override
    public List<App> findAppByIdDefaultByTargetUser(String targetUser, Integer userPermission) {
        AppExample example = new AppExample();
        AppExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("category");
        criteria.andDelFlagEqualTo(0).andIsDefaultEqualTo(0).andAppPermissionEqualTo(userPermission);//1 区级应用，2 校级应用

        AppExample.Criteria criteria1 = example.createCriteria();
        criteria1.andIsDefaultEqualTo(0).andAppPermissionEqualTo(3).andDelFlagEqualTo(0);

        example.or(criteria1);

        example.setOrderByClause("id");
        List<App> appList = appMapper.selectByExample(example);
        if (appList == null || appList.size() == 0)
            return new ArrayList<App>();
        List<App> finalList = new ArrayList<>();
        for (App app : appList) {
            String tgUser = app.getTargetUser();
            if (tgUser == null || tgUser == "")      //当应用没有目标用户时，不获取
                continue;
            if (Arrays.asList(tgUser.split(",")).contains(targetUser))
                finalList.add(app);
        }
        return finalList;
    }

    @Override
    public User findUserByUsername(String username) {

        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);

        List<User> res = userMapper.selectByExample(example);

        if (res.size() > 0)
            return res.get(0);
        else
            return null;
    }

    @Override
    public int deleteCommonUsedApp(CommonlyUsedApp commonlyUsedApp) {
        CommonlyUsedAppExample example = new CommonlyUsedAppExample();
        example.createCriteria().andUserIdEqualTo(commonlyUsedApp.getUserId()).andAppIdEqualTo(commonlyUsedApp.getAppId());

        return commonUsedAppMapper.deleteByExample(example);
    }

    @Override
    public int addCommonUsedApp(CommonlyUsedApp commonlyUsedApp) {
        return commonUsedAppMapper.insert(commonlyUsedApp);
    }

    @Override
    public int insertBatchCommonUsedApp(List<CommonlyUsedApp> list) {
        return appExtensionMapper.insertBatchCommonUsedApp(list);
    }

    @Override
    public List<App> findCommonUsedAppList(String userId) {
        CommonlyUsedAppExample example = new CommonlyUsedAppExample();
        example.createCriteria().andUserIdEqualTo(userId);

        List<CommonlyUsedApp> commonlyUsedAppList = commonUsedAppMapper.selectByExample(example);

        if (commonlyUsedAppList.size() == 0)
            return new ArrayList<>();

        List<String> appIdList = new ArrayList<>();
        for (CommonlyUsedApp commonUsedApp : commonlyUsedAppList) {
            appIdList.add(commonUsedApp.getAppId());
        }

        List<App> res = selectAppIdInList(appIdList);

        return res;
    }

    @Override
    public List<App> findDefaultAppList(Integer permissionFlag) {
        AppExample example = new AppExample();

        List<Integer> permissionList = new ArrayList<>();
        permissionList.add(3);
        permissionList.add(permissionFlag);

        example.createCriteria().andDelFlagEqualTo(0).andIsDefaultEqualTo(0).andAppPermissionIn(permissionList);
        List<App> res = appMapper.selectByExample(example);
        return res;
    }

    @Override
    public List<App> findAppBySchoolId(String schoolId, Integer status) {

        SchoolAppExample example = new SchoolAppExample();
        SchoolAppExample.Criteria criteria = example.createCriteria();

        //取消上线操作，只要在应用表里的应用，所有学校都能read，使用权限，在school_app status=2时候才有权限使用
       /* if (status != 0)
            criteria.andAppStatusEqualTo(status);*/

        criteria.andSchoolIdEqualTo(schoolId);

        List<SchoolApp> schoolAppList = schoolAppMapper.selectByExample(example);

        List<String> appIdList = new ArrayList<>();

        for (SchoolApp schoolApp : schoolAppList) {
            appIdList.add(schoolApp.getAppId());
        }

        AppExample appExample = new AppExample();
        AppExample.Criteria criteria1 = appExample.createCriteria();
        criteria1.andDelFlagEqualTo(0);

        if (status != 0) {
            if (schoolAppList.size() == 0)
                return new ArrayList<>();
            else
                criteria1.andIdIn(appIdList);
        }

        List<App> res = appMapper.selectByExample(appExample);

        return res;
    }

    @Override
    public List<App> findOtherAppByUser(User user) {
        List<App> schoolAppList = findAppBySchoolId(user.getSchoolId(), 2);//查询学校已经上线的应用

        List<MyApp> refMyAppList = selectAppByUser(user);

        if (refMyAppList.size() == 0)
            return schoolAppList;

        List<String> appIdList = new ArrayList<>();
        for (MyApp myApp : refMyAppList) {
            appIdList.add(myApp.getAppId());
        }

        List<App> myAppList = selectAppIdInList(appIdList);

        schoolAppList.removeAll(myAppList);

        return schoolAppList;
    }

}
