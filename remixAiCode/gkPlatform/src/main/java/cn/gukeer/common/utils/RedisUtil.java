package cn.gukeer.common.utils;


import redis.clients.jedis.Jedis;

public final class RedisUtil {

    /**
     * 释放jedis资源
     *
     * @param jedis
     */
    public static void returnResource(final Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * 查询数据
     */
    public static String find(String key, Jedis jedis) {
        try {
            return jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 查询特定字符串
     */
    public static String findSubStr(String key, Integer startOffset, Integer endOffset, Jedis jedis) {
        try {
            return jedis.getrange(key, startOffset, endOffset);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
    * 设置缓存的key的有效时间,单位秒
    * */

    public static int setExpire(String key, int expire, Jedis jedis) {
        try {
            jedis.expire(key, expire);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 向缓存中设置字符串内容 新增数据|修改
     *
     * @param key   key
     * @param value value
     * @return
     * @throws Exception
     */
    public static int add(String key, String value, Jedis jedis) throws Exception {
        try {
            jedis.set(key, value);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int addObject(String key, Object object, Jedis jedis) throws Exception {
        try {
            jedis.set(key.getBytes(), SerializeUtil.serialize(object));
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static Object getObject(String key, Jedis jedis) {
        byte[] value = jedis.get(key.getBytes());
        return SerializeUtil.unserialize(value);
    }

    /**
     * 删除缓存中得对象，根据key
     *
     * @param key
     * @return
     */
    public static int del(String key, Jedis jedis) {
        try {
            jedis.del(key);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
