package cn.gukeer.common.utils;
import com.google.gson.*;
import org.springframework.util.StringUtils;

import java.lang.reflect.Type;

/**
 * Created by xali on 15/3/20.
 */
public class GsonUtil {
    public static final Gson gson = new Gson();


    /**
     * 对象转换成json字符串
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    /**
     * json字符串转成对象
     * @param str
     * @param type
     * @return
     */
    public static <T> T fromJson(String str, Type type) {
        try {
            return gson.fromJson(str, type);
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * json转成对象
     * @param json
     * @param type
     * @return
     */
    public static <T> T fromJson(JsonObject json, Type type) {
        try {
            return gson.fromJson(json, type);
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * json字符串转成对象
     * @param str
     * @param type
     * @return
     */
    public static <T> T fromJson(String str, Class<T> type) {
        return gson.fromJson(str, type);
    }

    /**
     * json对象转成对象
     * @param json
     * @param type
     * @return
     */
    public static <T> T fromJson(JsonObject json, Class<T> type) {
        return gson.fromJson(json, type);
    }

    /**
     * json字符串转成Json对象
     * @param str
     * @return
     */
    public static JsonElement str2Json(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new JsonParser().parse(str);
        } catch (Exception e) {

        }
        return null;
    }

    public static String valueOfString(JsonObject jsonObject,String memeber){

        if(null != jsonObject && jsonObject.has(memeber)){
            return jsonObject.get(memeber).getAsString();
        }else{
            return null;
        }

    }

    public static Integer valueOfInteger(JsonObject jsonObject,String memeber){

        if(null != jsonObject && jsonObject.has(memeber)){
            return jsonObject.get(memeber).getAsInt();
        }else{
            return null;
        }

    }

    /**
     * 获取通用的json返回样式
     * @param msg
     * @param errcode
     * @param data
     * @return
     */
    public static JsonElement getCommonJson(String msg,String errcode,JsonElement data) {
        JsonObject retJson = new JsonObject();
        retJson.addProperty("msg",msg);
        retJson.addProperty("errcode",errcode);
        if (data != null) {
            retJson.add("data",data);
        }else {
            retJson.addProperty("data","");
        }
        return retJson;
    }

    //    防止int转换成double,转换null
    public static Gson noneIntDouble(){
        Gson gson = new GsonBuilder().serializeNulls().registerTypeAdapter(Double.class, new JsonSerializer<Double>() {
            @Override
            public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
                if (src == src.longValue())
                    return new JsonPrimitive(src.longValue());
                return new JsonPrimitive(src);
            }
        }).create();
        return gson;
    }
}
