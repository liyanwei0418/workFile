package cn.gukeer.platform.service;

public interface CacheService {

    void addCache(String key, Object value);

    Object getCacheByKey(String key);

    void removeCache(String key);

    void removeAll();


}
