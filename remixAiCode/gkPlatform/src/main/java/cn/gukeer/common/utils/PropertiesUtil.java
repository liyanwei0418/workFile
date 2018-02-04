package cn.gukeer.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by lx on 2016/11/21.
 */
public class PropertiesUtil {
    private static Log log = LogFactory.getLog(PropertiesUtil.class);
    public static Properties getProperties(String path) {
        Properties prop = null;
        InputStream inStream = null;
        try {
            prop = new Properties();
            inStream = new ClassPathResource(path).getInputStream();
            prop.load(inStream);
            inStream.close();
        } catch (Exception e) {
            log.warn("获取properties文件失败", e);
        }
        return prop;
    }
}
