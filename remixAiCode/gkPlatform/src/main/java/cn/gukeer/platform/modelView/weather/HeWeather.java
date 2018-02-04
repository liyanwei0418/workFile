package cn.gukeer.platform.modelView.weather;

import cn.gukeer.sync.dataDefinition.QueryData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/7.
 */

public class HeWeather implements Serializable {
    public Basic basic;
    public List<Daily_forecast> daily_forecast;
    public String status;

    public void setBasic(Basic basic) {
        this.basic = basic;
    }
    public Basic getBasic() {
        return basic;
    }

    public void setDaily_forecast(List<Daily_forecast> daily_forecast) {
        this.daily_forecast = daily_forecast;
    }
    public List<Daily_forecast> getDaily_forecast() {
        return daily_forecast;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "{" +
                "basic=" + basic +
                ", daily_forecast=" + daily_forecast +
                ", status='" + status + '\'' +
                '}';
    }

    public static void main(String[] args) {

        String dataList = "{'groupOp':'and','rules':[{'field':'schoolId','op':'eq','data':'b90911e594420ab250f2'}],'page':1,'limit':10}";
        Gson gson = new Gson();
        QueryData viewList = gson.fromJson(dataList,
                new TypeToken<QueryData>() {
                }.getType());

        System.out.println(viewList);

       String aa =  new Gson().toJson(viewList);
        System.out.println(aa);

        System.out.println();



     /*   String res = "[{wifi=wifif1, pass=1234}, {wifi=wifi2, pass=1234}]";
        JSONArray ar = JSONArray.fromString(res);


        JSONObject map = ar.optJSONObject(0);
        System.out.println(map.get("wifi"));*/

        //System.out.println(new Gson().fromJson(res.getWifiList(),Object.class));


      /*  String dataList = "{\"daily_forecast\":[{\"wind\":{\"dir\":\"北风\",\"deg\":\"290\",\"sc\":\"微风\",\"spd\":\"8\"},\"hum\":\"41\",\"astro\":{\"mr\":\"20:24\",\"sr\":\"04:56\",\"ms\":\"05:47\",\"ss\":\"19:43\"},\"pcpn\":\"0.0\",\"uv\":\"9\",\"tmp\":{\"min\":\"23\",\"max\":\"36\"},\"pop\":\"0\",\"date\":\"2017-07-10\",\"pres\":\"999\",\"cond\":{\"code_d\":\"100\",\"txt_n\":\"晴\",\"code_n\":\"100\",\"txt_d\":\"晴\"},\"vis\":\"19\"},{\"wind\":{\"dir\":\"南风\",\"deg\":\"172\",\"sc\":\"微风\",\"spd\":\"4\"},\"hum\":\"36\",\"astro\":{\"mr\":\"21:03\",\"sr\":\"04:57\",\"ms\":\"06:43\",\"ss\":\"19:43\"},\"pcpn\":\"4.2\",\"uv\":\"9\",\"tmp\":{\"min\":\"27\",\"max\":\"36\"},\"pop\":\"84\",\"date\":\"2017-07-11\",\"pres\":\"1001\",\"cond\":{\"code_d\":\"101\",\"txt_n\":\"多云\",\"code_n\":\"101\",\"txt_d\":\"多云\"},\"vis\":\"19\"},{\"wind\":{\"dir\":\"南风\",\"deg\":\"197\",\"sc\":\"微风\",\"spd\":\"7\"},\"hum\":\"53\",\"astro\":{\"mr\":\"21:40\",\"sr\":\"04:58\",\"ms\":\"07:41\",\"ss\":\"19:43\"},\"pcpn\":\"0.0\",\"uv\":\"10\",\"tmp\":{\"min\":\"27\",\"max\":\"35\"},\"pop\":\"0\",\"date\":\"2017-07-12\",\"pres\":\"1001\",\"cond\":{\"code_d\":\"101\",\"txt_n\":\"多云\",\"code_n\":\"101\",\"txt_d\":\"多云\"},\"vis\":\"20\"}],\"basic\":{\"id\":\"CN101010100\",\"update\":{\"utc\":\"2017-07-10 01:49\",\"loc\":\"2017-07-10 09:49\"},\"lon\":\"116.40528870\",\"cnty\":\"中国\",\"lat\":\"39.90498734\",\"city\":\"北京\"},\"status\":\"ok\"}";

        Gson gson = new Gson();
        HeWeather viewList = gson.fromJson(dataList,
                new TypeToken<HeWeather>() {
                }.getType());

        System.out.println(viewList);

        HeWeather vi = gson.fromJson(viewList.toString(), new TypeToken<HeWeather>() {
        }.getType());

        System.out.println();
        System.out.println(vi);*/

    }
}


