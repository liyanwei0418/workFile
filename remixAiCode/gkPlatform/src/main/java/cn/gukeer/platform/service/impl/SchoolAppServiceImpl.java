package cn.gukeer.platform.service.impl;

import cn.gukeer.common.service.BasicService;
import cn.gukeer.common.utils.StringUtils;
import cn.gukeer.platform.persistence.dao.*;
import cn.gukeer.platform.persistence.entity.*;
import cn.gukeer.platform.service.SchoolAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GW on 2016/9/9.
 */
@Service
public class SchoolAppServiceImpl extends BasicService implements SchoolAppService {
    @Autowired
    SchoolAppMapper schoolAppMapper;

    @Autowired
    CommonlyUsedAppMapper commonlyUsedAppMapper;

    @Autowired
    MyAppMapper myAppMapper;

    @Autowired
    SchoolMapper schoolMapper;

    @Autowired
    AppMapper appMapper;

    @Override
    public int insertSchoolApp(SchoolApp schoolApp) {
        return schoolAppMapper.insert(schoolApp);
    }

    @Override
    public int deleteSchoolApp(SchoolApp schoolApp) {
        SchoolAppExample schoolAppExample = new SchoolAppExample();
        SchoolAppExample.Criteria criteria = schoolAppExample.createCriteria();

        if (!StringUtils.isEmpty(schoolApp.getAppId())) {
            criteria.andAppIdEqualTo(schoolApp.getAppId());
        }

        if (null != schoolApp.getAppStatus() && !"".equals(schoolApp.getAppStatus())) {
            criteria.andAppStatusEqualTo(schoolApp.getAppStatus());
        }

        criteria.andSchoolIdEqualTo(schoolApp.getSchoolId());

        return schoolAppMapper.deleteByExample(schoolAppExample);
    }

    @Override
    public int deleteSchoolAppByAppId(String appId) {
        SchoolAppExample schoolAppExample = new SchoolAppExample();
        schoolAppExample.createCriteria().andAppIdEqualTo(appId);

        return schoolAppMapper.deleteByExample(schoolAppExample);
    }

    @Override
    public List<SchoolApp> findAllListBySchoolId(String schoolId) {
        SchoolAppExample schoolAppExample = new SchoolAppExample();
        schoolAppExample.createCriteria().andSchoolIdEqualTo(schoolId);
        List<SchoolApp> schoolAppList = schoolAppMapper.selectByExample(schoolAppExample);
        return schoolAppList;
    }

    @Override
    public List<App> findAppListBySchoolId(String schoolId) {
        List<SchoolApp> schoolAppList = findAllListBySchoolId(schoolId);

        if (schoolAppList.size() == 0) {
            return new ArrayList<>();
        }

        List<String> appIdList = new ArrayList<>();
        for (SchoolApp schoolApp : schoolAppList) {
            appIdList.add(schoolApp.getAppId());
        }

        AppExample example = new AppExample();
        example.createCriteria().andIdIn(appIdList);

        return appMapper.selectByExample(example);
    }

    @Override
    public List<School> findSchoolListByAppId(String appId) {
        SchoolAppExample schoolAppExample = new SchoolAppExample();
        schoolAppExample.createCriteria().andAppIdEqualTo(appId);

        List<SchoolApp> res = schoolAppMapper.selectByExample(schoolAppExample);

        List<String> schoolIdList = new ArrayList<>();

        for (SchoolApp schoolApp : res) {
            schoolIdList.add(schoolApp.getSchoolId());
        }

        if (schoolIdList.size() > 0) {
            SchoolExample schoolExample = new SchoolExample();
            schoolExample.createCriteria().andIdIn(schoolIdList);

            List<School> schoolList = schoolMapper.selectByExample(schoolExample);
            return schoolList;
        }
        return new ArrayList<School>();
    }

    @Override
    public int deleteAddedApp(List<String> appIdList) {

        if (null == appIdList || appIdList.size() == 0) {
            appIdList.add("");//如果要不要删 除的id集合为空，是全删
        }

        //默认应用，不会参与下线操作
        AppExample appExample = new AppExample();
        appExample.createCriteria().andIsDefaultEqualTo(0).andDelFlagEqualTo(0);
        List<App> defaultAppList = appMapper.selectByExample(appExample);
        List<String> defaultAppIdList = new ArrayList<>();
        for (App app : defaultAppList) {
            defaultAppIdList.add(app.getId());
        }

        appIdList.addAll(defaultAppIdList);//将所有默认应用的id集合添加到不被删除的集合

        CommonlyUsedAppExample commonlyUsedAppExample = new CommonlyUsedAppExample();
        commonlyUsedAppExample.createCriteria().andAppIdNotIn(appIdList);
        commonlyUsedAppMapper.deleteByExample(commonlyUsedAppExample);

        MyAppExample myAppExample = new MyAppExample();
        myAppExample.createCriteria().andAppIdNotIn(appIdList);
        myAppMapper.deleteByExample(myAppExample);

        return 0;
    }

}
