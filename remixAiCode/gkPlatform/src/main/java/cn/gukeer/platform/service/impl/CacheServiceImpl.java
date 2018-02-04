package cn.gukeer.platform.service.impl;

import cn.gukeer.platform.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;


@Service
public class CacheServiceImpl implements CacheService {

    private Cache cache;

    @Autowired
    public CacheServiceImpl(CacheManager cacheManager) {
        //default
        this.cache = cacheManager.getCache("action-cache");
    }

    public CacheServiceImpl(CacheManager cacheManager,String cacheName){
        this.cache = cacheManager.getCache(cacheName);
    }

    @Override
    public void addCache(String key, Object value) {
        cache.put(key, value);
    }

    @Override
    public Object getCacheByKey(String key) {
        Cache.ValueWrapper res = cache.get(key);
        if (res != null) {
            return res.get();
        } else {
            return null;
        }
    }

    @Override
    public void removeCache(String key) {
        cache.evict(key);
    }

    @Override
    public void removeAll() {
        cache.clear();
    }

}
