package cn.gukeer.platform.service.impl;

import cn.gukeer.common.service.BasicService;
import cn.gukeer.platform.persistence.dao.A_AppExtensionMapper;
import cn.gukeer.platform.persistence.dao.MyAppMapper;
import cn.gukeer.platform.persistence.entity.MyApp;
import cn.gukeer.platform.persistence.entity.MyAppExample;
import cn.gukeer.platform.service.MyAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chen on 2016/9/10.
 */
@Service
public class MyAppServiceImpl extends BasicService implements MyAppService {
    @Autowired
    MyAppMapper myAppMapper;

    @Autowired
    A_AppExtensionMapper appExtensionMapper;


   /* @Override
    public PageInfo<MyApp> findAllList(int pageNum, int pageSize){
        return myAppMapper.
    }*/

    /*@Override
    public List<MyApp> selectMyAppByUserIdAndUserType(String userId, int userType){
        return myAppMapper.selectMyAppByUserIdAndUserType(userId,userType);
    }*/

    @Override
    public int insertMyApp(MyApp myApp) {
        return myAppMapper.insert(myApp);
    }

    @Override
    public int deleteMyApp(MyApp myApp) {
        MyAppExample example = new MyAppExample();
        example.createCriteria().andAppIdEqualTo(myApp.getAppId()).andUserIdEqualTo(myApp.getUserId()).andUserTypeEqualTo(myApp.getUserType());
        return myAppMapper.deleteByExample(example);
    }

    @Override
    public int insertBatchMyApp(List<MyApp> list) {
        appExtensionMapper.insertBatchMyApp(list);
        return list.size();
    }

    @Override
    public List<MyApp> findMyApp(String userId, int userType, String _id) {
        MyAppExample example = new MyAppExample();
        example.createCriteria().andUserIdEqualTo(userId).andUserTypeEqualTo(userType).andAppIdEqualTo(_id);
        List<MyApp> myAppList = myAppMapper.selectByExample(example);
        return myAppList;
    }

}
