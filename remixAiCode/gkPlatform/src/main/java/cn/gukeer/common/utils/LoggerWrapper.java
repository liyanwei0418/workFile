package cn.gukeer.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by conn on 2016/8/9.
 */
public abstract class LoggerWrapper {
    protected Logger logger = LoggerFactory.getLogger(getClass());
}
