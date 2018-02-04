package cn.gukeer.sync.netty.open;

import cn.gukeer.platform.persistence.dao.AppMapper;
import cn.gukeer.platform.persistence.dao.MonitorMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LL on 2017/11/3.
 */
@Service
public class OpenClientServiceImpl implements InitializingBean {
    @Autowired
    MonitorMapper monitorMapper;
    @Autowired
    AppMapper appMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        new OpenClient(monitorMapper,appMapper).startClient();
    }
}
