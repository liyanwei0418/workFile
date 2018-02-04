package cn.gukeer.common.service;

import cn.gukeer.common.utils.LoggerWrapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by conn on 2016/8/5.
 */
@Service
public abstract class BasicService extends LoggerWrapper {
    public Object getValue(Map map, String key) {
        Object value = map.get(key);
        if (value != null)
            return value;
        else
            return "";
    }

}
