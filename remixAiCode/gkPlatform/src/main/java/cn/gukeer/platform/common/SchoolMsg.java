package cn.gukeer.platform.common;

import java.util.Map;

/**
 * Created by pc-daisike on 2016/11/3.
 */
public class SchoolMsg {

    private static SchoolMsg instance = null;

    public Map<String, Map<String, String>> urlMap = null;
    public Long creatTime = null;

    private SchoolMsg() {
    }

    //添加数据
    public static synchronized SchoolMsg addMsg(Map<String, Map<String, String>> newMap) {
        if (instance == null) {
            instance = new SchoolMsg();
            instance.creatTime = System.currentTimeMillis();    //创建单例时赋值时间
        }
        if(instance.urlMap == null){
            instance.urlMap = newMap;
        }
        else {
            instance.urlMap.putAll(newMap);
        }
        instance.urlMap.putAll(newMap);
        return instance;
    }

    //获取对象
    public static synchronized SchoolMsg getInstance() {
        if (instance == null) {
            return null;
        } else {
            return instance;
        }
    }

    //单例中是否已经存在学校url 调用前应判断url不为空
    public static synchronized boolean ifHaveSchool(String url) {
        if (instance == null) {
            return false;
        } else {
            return instance.urlMap.containsKey(url);
        }
    }

    //获取url对应数据
    public static synchronized Map<String, String> getMsg(String url) {
        if(url != null && url != ""){
            if (instance != null && ifHaveSchool(url)) {
                return instance.urlMap.get(url);
            }
            else return null;
        }
        else return null;
    }

    //删除url及其对应数据
    public static synchronized void deleteMsg(String url) {
        if(url != null && url != ""){
            if (instance != null && ifHaveSchool(url)) {
                instance.urlMap.remove(url);
            }
        }
    }

    //重置单例
    public static synchronized void ifReset() {
        if (instance != null && instance.creatTime != null) {
            if(System.currentTimeMillis() - instance.creatTime > 86400000){    // 当前时间 - 创建时间 > 24h
                instance = null;
            }
        }
    }
}
