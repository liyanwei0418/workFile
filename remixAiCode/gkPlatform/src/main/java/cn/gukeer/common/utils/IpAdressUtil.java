package cn.gukeer.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

/**
 * @ClassName: IPUtil
 * @version 1.0
 * @Desc: Ip工具类
 * @author huaping hu
 * @date 2016年6月1日下午5:26:56
 * @history v1.0
 *
 */
public class IpAdressUtil {
    private final static String URL="http://ip.taobao.com/service/getIpInfo.php?ip=";

    /**
     *
     * 描述：获取IP地址
     *
     * @author huaping hu
     * @date 2016年6月1日下午5:25:44
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");

        if (ip == null || ip.length() == 0 || "nuknown".equalsIgnoreCase(ip)) {

            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "nuknown".equalsIgnoreCase(ip)) {

            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "nuknown".equalsIgnoreCase(ip)) {

            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     *
     * 描述：获取IP+[IP所属地址]
     *
     * @author huaping hu
     * @date 2016年6月1日下午6:01:09
     * @param request
     * @return
     */
    public static String getIpBelongAddress(HttpServletRequest request) {

        String ip = getIpAddress(request);
        String belongIp = getIPbelongAddress(ip);

        return ip + belongIp;
    }

    /**
     *
     * 描述：获取IP所属地址
     *
     * @author huaping hu
     * @date 2016年6月1日下午5:59:43
     * @param ip
     * @return
     */
    public static String getIPbelongAddress(String ip) {

        String ipAddress = "[]";
        try {
            // 淘宝提供的服务地址
            String context = call(URL + ip);
            JSONObject fromObject = JSONObject.fromObject(context);
            String code = fromObject.getString("code");
            if (code.equals("0")) {
                JSONObject jsonObject = fromObject.getJSONObject("data");
                ipAddress = "[" + jsonObject.get("country") + "/" + jsonObject.get("city") + "]";
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return ipAddress;
    }

    /**
     *
     * 描述：获取Ip所属地址
     *
     * @author huaping hu
     * @date 2016年6月1日下午5:38:55
     * @param urlStr
     * @return
     */
    public static String call(String urlStr) {

        try {

            URL url = new URL(urlStr);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

            httpCon.setConnectTimeout(3000);
            httpCon.setDoInput(true);
            httpCon.setRequestMethod("GET");

            int code = httpCon.getResponseCode();

            if (code == 200) {
                return streamConvertToSting(httpCon.getInputStream());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * 描述：将InputStream转换成String
     *
     * @author huaping hu
     * @date 2016年6月1日下午5:51:53
     * @param is
     * @return
     */
    public static String streamConvertToSting(InputStream is) {

        String tempStr = "";
        try {

            if (is == null)
                return null;
            ByteArrayOutputStream arrayOut = new ByteArrayOutputStream();
            byte[] by = new byte[1024];
            int len = 0;
            while ((len = is.read(by)) != -1) {
                arrayOut.write(by, 0, len);
            }
            tempStr = new String(arrayOut.toByteArray());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return tempStr;
    }

    private static String ascii2native ( String asciicode )
    {
        String[] asciis = asciicode.split ("\\\\u");
        String nativeValue = asciis[0];
        try
        {
            for ( int i = 1; i < asciis.length; i++ )
            {
                String code = asciis[i];
                nativeValue += (char) Integer.parseInt (code.substring (0, 4), 16);
                if (code.length () > 4)
                {
                    nativeValue += code.substring (4, code.length ());
                }
            }
        }
        catch (NumberFormatException e)
        {
            return asciicode;
        }
        return nativeValue;
    }
    public static Map jsonToObject(String jsonStr){
        JSONObject jsonObj = new JSONObject(jsonStr);
        Iterator<String> nameItr = jsonObj.keys();
        String name;
        Map<String, String> outMap = new HashMap<String, String>();
        while (nameItr.hasNext()) {
            name = nameItr.next();
            outMap.put(name, jsonObj.getString(name));
        }
        return outMap;
    }
//0:0:0:0:0:0:0:1
    public  static String getCityByIp(String ip){
        if(ip.indexOf("192.168")>=0||ip.indexOf("127.0.0")>=0||ip.indexOf("0:0:0:0:0:")>=0){
            return "北京";
        }else{
            Map map=jsonToObject(ascii2native(call(IpAdressUtil.URL+ip)).replace("省", "").replace("市", ""));
            Map dataMap=jsonToObject(map.get("data").toString());
            return dataMap.get("city").toString();
        }
    }

    public static void main(String[] args) {
      //  System.out.println(getCityByIp("58.51.40.184"));
        System.out.println(getCityByIp("192.168.1111.111"));
    }
}