package cn.gukeer.sync.netty.classCardAttendance;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

/**
 * Created by alpha on 17-11-2.
 */
@Component
public class WebContextListener implements InitializingBean {

    @Autowired
    CacheManager cacheManager;

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            ClassCardServer.getClassCardServer().startServer();
            ClassCardServer.setCacheManager(cacheManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
