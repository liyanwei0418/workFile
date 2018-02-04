package cn.gukeer.platform.service.impl;

import cn.gukeer.common.service.BasicService;
import cn.gukeer.common.utils.PrimaryKey;
import cn.gukeer.platform.persistence.dao.MonitorMapper;
import cn.gukeer.platform.persistence.entity.Monitor;
import cn.gukeer.platform.persistence.entity.MonitorExample;
import cn.gukeer.platform.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by admin on 2017/2/21.
 */
@Service
public class MonitorServiceImpl extends BasicService implements MonitorService {

    @Autowired
    MonitorMapper monitorMapper;

    @Override
    public List<Monitor> selectMonitor() {
        MonitorExample example = new MonitorExample();
        example.createCriteria().andClientIdIsNotNull();
        List<Monitor> monitorList = monitorMapper.selectByExample( example);
        return monitorList;
    }

    @Override
    public int saveMonitor(Monitor monitor) {
        int count;
        if (StringUtils.isEmpty( monitor.getId())){
            monitor.setId(PrimaryKey.get());
            count = monitorMapper.insertSelective( monitor);
        } else if( null == monitorMapper.selectByPrimaryKey( monitor.getId())){
            count = monitorMapper.insertSelective( monitor);
        } else {
            count = monitorMapper.updateByPrimaryKey( monitor);
        }

        return count;
    }
}
