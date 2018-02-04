package test;

import org.omg.SendingContext.RunTime;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alpha on 17-11-28.
 */
public class JedisTest {

    public static void main(String[] args) {

        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(300);
        jedisPoolConfig.setMinIdle(300);


        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"127.0.0.1",6379);

        Jedis jedis = jedisPool.getResource();
        jedis.auth("zjy1388");

        Map<String, String> map = jedis.hgetAll("user");
        System.out.println(map);

        String listKey="listKey";
        Map<String,String> listHash1=new HashMap<>();
        listHash1.put("name","zhangsan");
        listHash1.put("age","10");
        jedis.lpush("hh",listHash1.toString());
        jedis.lpush("hh","string");

        List<String> hh = jedis.lrange("hh", 0, -1);
        System.out.println(jedis.lpop("hh"));


        jedis.zadd("zset1",6,"e");
        jedis.zadd("zset1",10,"a");
        jedis.zadd("zset1",9,"b");
        jedis.zadd("zset2",8,"c");
        jedis.zadd("zset2",7,"d");
        jedis.zadd("zset2",11,"f");
        System.out.println(jedis.zrange("zset1",0,-1));


        jedis.hset("user3","name","lisi");
        jedis.hset("user3","age","22");
        jedis.hset("user","sex","girl");
        System.out.println(jedis.zrange("zset2",0,-1));
        Map<String,String> hashMap=new HashMap<>();
        hashMap.put("name","wangwu");
        hashMap.put("salary","22");
        jedis.hmset("user5",hashMap);
        jedis.hgetAll("user5");
        System.out.println("Java进程可以向操作系统申请到的最大内存:"+(Runtime.getRuntime().maxMemory())/(1024*1024)+"M");
        System.out.println("Java进程空闲内存:"+(Runtime.getRuntime().freeMemory())/(1024*1024)+"M");
        System.out.println("Java进程现在从操作系统那里已经申请了内存:"+(Runtime.getRuntime().totalMemory())/(1024*1024)+"M");

        byte[] bys = new byte[1024*1024];//申请1M内存
        System.out.println("Java进程可以向操作系统申请到的最大内存:"+(Runtime.getRuntime().maxMemory())/(1024*1024)+"M");
        System.out.println("Java进程空闲内存:"+(Runtime.getRuntime().freeMemory())/(1024*1024)+"M");
        System.out.println("Java进程现在从操作系统那里已经申请了内存:"+(Runtime.getRuntime().totalMemory())/(1024*1024)+"M");

    }
}
