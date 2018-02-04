package cn.gukeer.common.utils;

import cn.gukeer.platform.service.CacheService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Cache工具类
 */
@Component
public class CacheUtils {

    @Autowired
    private CacheService cacheService;
    private static CacheUtils cacheUtils;

    public void setCache(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @PostConstruct
    public void init() {
        cacheUtils = this;
        cacheUtils.cacheService = this.cacheService;
    }

    /**
     * 获取SYS_CACHE缓存
     *
     * @param key
     * @return
     */
    public static Object get(String key) {
        return cacheUtils.cacheService.getCacheByKey(key);
    }

    /**
     * 写入SYS_CACHE缓存
     *
     * @param key
     * @return
     */
    public static void put(String key, Object value) {
        cacheUtils.cacheService.addCache(key, value);
    }

    /**
     * 从SYS_CACHE缓存中移除
     *
     * @param key
     * @return
     */
    public static void remove(String key) {
        cacheUtils.cacheService.removeCache(key);
    }


}
