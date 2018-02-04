package cn.gukeer.platform.service;

import cn.gukeer.platform.persistence.entity.Monitor;

import java.util.List;

/**
 * Created by admin on 2017/2/21.
 */
public interface MonitorService {

    List<Monitor> selectMonitor();

    int saveMonitor(Monitor monitor);
}
