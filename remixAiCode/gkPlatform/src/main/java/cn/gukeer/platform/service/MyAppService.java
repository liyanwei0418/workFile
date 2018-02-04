package cn.gukeer.platform.service;

import cn.gukeer.platform.persistence.entity.MyApp;

import java.util.List;

/**
 * Created by chen on 2016/9/10.
 */
public interface MyAppService {

    List<MyApp> findMyApp(String userId,int userType,String _id);

    int insertMyApp(MyApp myApp);

    int deleteMyApp(MyApp myApp);

    int insertBatchMyApp(List<MyApp> list);
}
