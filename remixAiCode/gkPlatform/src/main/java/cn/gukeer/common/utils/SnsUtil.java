package cn.gukeer.common.utils;

import cn.gukeer.common.utils.syncdata.MD5Util;
import cn.gukeer.platform.modelView.AllForumView;
import cn.gukeer.platform.modelView.HotSpotView;
import cn.gukeer.platform.modelView.HotTopicView;
import cn.gukeer.platform.modelView.WeiBoView;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Administrator on 2016/10/31.
 */
public class SnsUtil {
    public static String SNS_URL;
    public static String SNS_KEY;
    public static String MOOC_KEY;
    public static String COMMON_KEY;

    static {
       Properties properties = PropertiesUtil.getProperties("api.properties");

        SNS_URL = properties.getProperty("sns_url");
        SNS_KEY = properties.getProperty("sns_key");
        MOOC_KEY = properties.getProperty("mooc_key");
        COMMON_KEY = properties.getProperty("common_key");
    }
    /*
    *url:接口地址
     * type:返回参数对象格式
      * count:获取返回的条数
      * interfaceType:接口类型（0：返回数组的接口  1：返回json的接口）
    * */
    public static List<Object> getSns(String url, Class type, Integer count, Integer interfaceType) {
        List<Object> resultList = new ArrayList<Object>();
        try {
            if (interfaceType == 0) {
                String res = HttpRequestUtil.get(SNS_URL + url, null, null);

                if (StringUtils.isEmpty(res) || "null".equalsIgnoreCase(res))
                    return new ArrayList<>();

                res = res.replace("/nb","&nbsp;");
                res = res.replace("/br","<br/>");
                JSONArray json = JSONArray.fromObject(res);
                resultList = JSONArray.toList(json, type);
            } else {
                String res = HttpRequestUtil.get(SNS_URL + url, null, null);

                if (StringUtils.isEmpty(res))
                    return new ArrayList<>();

                res = res.replace("/nb","&nbsp;");
                res = res.replace("/br","<br/>");

                JSONObject object = JSONObject.fromObject(res);
                Object obj = JSONObject.toBean(object, type);
                resultList.add(obj);
                return resultList;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (resultList.size() > count && resultList.size() != 0) {
            return resultList.subList(0, count);
        } else {
            return resultList;
        }
    }

    public static String getMoocLoginParams(String puid) {
        Long ptime = System.currentTimeMillis();
        String time = DateUtils.millsToyyyyMMdd(ptime);
        String mac = MD5Util.go(time + ptime + puid + MOOC_KEY);
        //String withParam = "&puid="+puid+"&ptime="+ptime+"&pkey="+mac;
        String withParam = "puid=" + puid + "&ptime=" + ptime + "&pkey=" + mac;
        return withParam;
    }

    public static String getThirdPartyParams(String schoolId, String appId, String refId, Integer type, String timeStamp) {
        String sign = MD5Util.go(schoolId + appId + refId + type.toString() + timeStamp + COMMON_KEY);
        String withParam = "school=" + schoolId + "&app=" + appId +
                "&user=" + refId + "&userType=" + type.toString() + "&time=" + timeStamp + "&sign=" + sign;
        return withParam;
    }

    public static void main(String[] args) {
        SnsUtil.getSns("/index.php/Api/Index/weibo_topic_hot?username=" + "admin@xiao", HotTopicView.class, 8, 0);
        SnsUtil.getSns("/index.php/Api/Index/weibo_all?username=" + "admin@xiao", WeiBoView.class, 5, 0);//微博
        SnsUtil.getSns("/index.php/Api/Index/plate_hot?username=" + "admin@xiao", HotSpotView.class, 5, 0);//板块
        SnsUtil.getSns("/index.php/Api/Index/forum_all?username=" + "admin@xiao", AllForumView.class, 5, 0);//帖子
    }

}
