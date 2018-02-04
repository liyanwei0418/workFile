/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.gukeer.common.config;

import com.google.common.collect.Maps;
import org.apache.ibatis.io.Resources;

import java.io.FileOutputStream;
import java.io.Reader;
import java.util.Map;
import java.util.Properties;

/**
 * 全局配置类
 *
 * @author jeeplus
 * @version 2014-06-25
 */
public class Global {

    /**
     * 当前对象实例
     */
    private static Global global = new Global();

    /**
     * 显示/隐藏
     */
    public static final String SHOW = "1";
    public static final String HIDE = "0";

    /**
     * 是/否
     */
    public static final Integer YES = 1;
    public static final Integer NO = 0;

    /**
     * 对/错
     */
    public static final String TRUE = "true";
    public static final String FALSE = "false";

    /**
     * 获取当前对象实例
     */
    public static Global getInstance() {
        return global;
    }

}
