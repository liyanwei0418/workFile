package cn.gukeer.sync.netty.classCardAttendance;

import cn.gukeer.classcard.modelView.CheckAttendanceView;
import cn.gukeer.classcard.persistence.entity.ClassCardApp;
import cn.gukeer.classcard.util.ClassCardCommand;
import cn.gukeer.common.utils.PropertiesUtil;
import cn.gukeer.platform.common.CourseUtil;
import cn.gukeer.platform.service.impl.CacheServiceImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wulianedu.netty.listener.MessageListener;
import com.wulianedu.netty.server.ServerInstance;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by alpha on 17-11-8.
 */
@Component
public class ClassCardServer implements MessageListener {
    Properties properties = PropertiesUtil.getProperties("api.properties");
    Integer netty_classcard_port = Integer.parseInt((String) properties.get("netty_classcard_port"));

    private static CacheManager cacheManager;

    private static ServerInstance instance;

    private static ClassCardServer classCardServer;

    public static synchronized ClassCardServer getClassCardServer() {
        if (null == classCardServer) {
            classCardServer = new ClassCardServer();
        }
        return classCardServer;
    }

    public static CacheManager getCacheManager() {
        return cacheManager;
    }

    public static void setCacheManager(CacheManager cacheManager) {
        ClassCardServer.cacheManager = cacheManager;
    }

    public static ServerInstance getInstance() {
        return instance;
    }

    public static void setInstance(ServerInstance instance) {
        ClassCardServer.instance = instance;
    }

    private ClassCardServer() {
    }

    public void startServer() {
        instance = new ServerInstance(this, netty_classcard_port);
        instance.connect();
    }

    @Override
    public void onMessageReceive(String mac) {

        System.out.println("@@@@@@@@@@@[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()) + "]@@@@@@@@@@@@@@@Preparing push cache data !! mac is:" + mac);

        //===========================缓存中有考勤数据便推送
        CacheServiceImpl cacheService = new CacheServiceImpl(cacheManager, "classcard-cache");
        Object obj = cacheService.getCacheByKey(mac);
        if (null != obj && instance.channelStatus(mac)) {

            int currentNode = CourseUtil.currentNode(mac);
            if (currentNode == -1) {
                return;
            }
            List<CheckAttendanceView> retList = new ArrayList<>();
            Gson gson = new Gson();
            List<CheckAttendanceView> checkAttendanceList = gson.fromJson(gson.toJson(obj), new TypeToken<List<CheckAttendanceView>>() {
            }.getType());

            for (CheckAttendanceView cav : checkAttendanceList) {
                if (cav.getLessonNo() == currentNode) {
                    retList.add(cav);
                }
            }
            if (retList.size() > 0) {
                Map<String,Object> attdenceMap=new HashMap<>();
                attdenceMap.put("command",ClassCardCommand.CLASSCARD_ATTENDANCE);
                attdenceMap.put("data",retList);
                instance.sendMessage(mac, new Gson().toJson(attdenceMap));
                System.out.println("@@@@@@@@@@@[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()) + "]@@@@@@@@@@@@@@@Cache data is sent successfully !! data is:" + new Gson().toJson(attdenceMap));
            }
            cacheService.removeCache(mac);
        }

        //===========================缓存中有apk指令便推送
        CacheServiceImpl commandCacheService = new CacheServiceImpl(cacheManager, "classcardcommand-cache");
        //安装指令缓存
        Object installObj = commandCacheService.getCacheByKey(mac + "_" + ClassCardCommand.APK_INSTALL);
        //卸载指令缓存
        Object unInstallObj = commandCacheService.getCacheByKey(mac + "_" + ClassCardCommand.APK_UNINSTALL);
        //班牌配置缓存
        Object configObj = commandCacheService.getCacheByKey(mac + "_" + ClassCardCommand.CONFIG_SWITCHGEAR);
        //班牌自定义界面缓冲
        Object customObj = commandCacheService.getCacheByKey(mac + "_" +ClassCardCommand.CUSTOM_PUBLISH);

        Map<String, Object> installMap = new HashMap<>();
        if (null != installObj) {
            installMap = (Map<String, Object>) installObj;
        }
        List<Map<String, Object>> installDatas = null;
        if (null != installMap && installMap.get("data") != null) {
            installDatas = (List<Map<String, Object>>) installMap.get("data");
        }

        Map<String, Object> unInstallMap = new HashMap<>();
        if (null != unInstallObj) {
            unInstallMap = (Map<String, Object>) unInstallObj;
        }
        List<Map<String, Object>> unInstallDatas = null;
        if (null != unInstallMap && unInstallMap.get("data") != null) {
            unInstallDatas = (List<Map<String, Object>>) unInstallMap.get("data");
        }

        Map<String, Object> removeRedundantMap = removeRedundant(installDatas, unInstallDatas);

        if (null != removeRedundantMap.get("installDatas") && instance.channelStatus(mac)) {
            Map<String, Object> retInstallMap = new HashMap<>();
            retInstallMap.put("command", ClassCardCommand.APK_INSTALL);
            retInstallMap.put("data", removeRedundantMap.get("installDatas"));
            instance.sendMessage(mac, new Gson().toJson(retInstallMap));
            System.out.println("@@@@@@@@@@@[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()) + "]@@@@@@@@@@@@@@@ APK_INSTALL Cache data is sent successfully !! data is:" + new Gson().toJson(retInstallMap));
        }
        if (null != removeRedundantMap.get("unInstallDatas") && instance.channelStatus(mac)) {
            Map<String, Object> retUnInstallMap = new HashMap<>();
            retUnInstallMap.put("command", ClassCardCommand.APK_UNINSTALL);
            retUnInstallMap.put("data", removeRedundantMap.get("unInstallDatas"));
            instance.sendMessage(mac, new Gson().toJson(retUnInstallMap));
            System.out.println("@@@@@@@@@@@[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()) + "]@@@@@@@@@@@@@@@ APK_UNINSTALL Cache data is sent successfully !! data is:" + new Gson().toJson(retUnInstallMap));
        }
        System.out.println("@@@@@@@@@@@[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()) + "]@@@@@@@@@@@@@@@ APK_COMMAND Cache data is sent successfully !! ");
        commandCacheService.removeCache(mac + "_" + ClassCardCommand.APK_INSTALL);
        commandCacheService.removeCache(mac + "_" + ClassCardCommand.APK_UNINSTALL);

        if (null != configObj && instance.channelStatus(mac)) {
            instance.sendMessage(mac, new Gson().toJson(configObj));
        }
        System.out.println("@@@@@@@@@@@[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()) + "]@@@@@@@@@@@@@@@ CONFIG_COMMAND Cache data is sent successfully !! ");
        commandCacheService.removeCache(mac + "_" + ClassCardCommand.CONFIG_SWITCHGEAR);

        if (null != customObj && instance.channelStatus(mac)) {
            instance.sendMessage(mac,new Gson().toJson(customObj));
        }
        System.out.println("@@@@@@@@@@@[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()) + "]@@@@@@@@@@@@@@@ CustomPage_COMMAND Cache data is sent successfully !! ");
        commandCacheService.removeCache(mac + "_" + ClassCardCommand.CUSTOM_PUBLISH);

        if(instance.channelStatus(mac)){
            instance.sendMessage(mac,"过来了～～～～～～～");
        }
    }

    /**
     * 去除两个命令集合中无效的命令
     *
     * @param installDatas
     * @param unInstallDatas
     * @return
     */
    public Map<String, Object> removeRedundant(List<Map<String, Object>> installDatas, List<Map<String, Object>> unInstallDatas) {

        Map<String, Object> retMap = new HashMap<>();
        if (null == installDatas && null != unInstallDatas) {
            retMap.put("unInstallDatas", unInstallDatas);
            retMap.put("installDatas", null);
            return retMap;
        }
        if (null != installDatas && null == unInstallDatas) {
            retMap.put("installDatas", installDatas);
            retMap.put("unInstallDatas", null);
            return retMap;
        }
        if (null == installDatas && null == unInstallDatas) {
            retMap.put("unInstallDatas", null);
            retMap.put("installDatas", null);
            return retMap;
        }

        List<Map<String, Object>> retInstall = new ArrayList<>();
        List<Map<String, Object>> retUnInstall = new ArrayList<>();

        install:
        for (Map<String, Object> installMap : installDatas) {
            for (Map<String, Object> unInstallMap : unInstallDatas) {
                ClassCardApp installApk = (ClassCardApp) installMap.get("apk");
                ClassCardApp unInstallApk = (ClassCardApp) unInstallMap.get("apk");
                if (installApk.getId().equals(unInstallApk.getId())) {
                    Long installTime = (Long) installMap.get("time");
                    Long unInstallTime = (Long) unInstallMap.get("time");
                    if (installTime > unInstallTime) {
                        retInstall.add(installMap);
                    }
                    continue install;
                }
            }
            retInstall.add(installMap);
        }

        unInstall:
        for (Map<String, Object> unInstallMap : unInstallDatas) {
            for (Map<String, Object> installMap : installDatas) {
                ClassCardApp installApk = (ClassCardApp) installMap.get("apk");
                ClassCardApp unInstallApk = (ClassCardApp) unInstallMap.get("apk");
                if (installApk.getId().equals(unInstallApk.getId())) {
                    Long installTime = (Long) installMap.get("time");
                    Long unInstallTime = (Long) unInstallMap.get("time");
                    if (installTime < unInstallTime) {
                        retUnInstall.add(unInstallMap);
                    }
                    continue unInstall;
                }
            }
            retUnInstall.add(unInstallMap);
        }

        retMap.put("unInstallDatas", retUnInstall.size() == 0 ? null : retUnInstall);
        retMap.put("installDatas", retInstall.size() == 0 ? null : retInstall);
        return retMap;

    }
}
