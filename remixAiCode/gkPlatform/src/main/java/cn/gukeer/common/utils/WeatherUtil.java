package cn.gukeer.common.utils;

/**
 * java获取新浪天气预报代码
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 解析xml文档，包括本地文档和url
 *
 */
public class WeatherUtil {

    InputStream inStream;

    Element root;

    public InputStream getInStream() {

        return inStream;

    }

    public void setInStream(InputStream inStream) {

        this.inStream = inStream;

    }

    public Element getRoot() {

        return root;

    }

    public void setRoot(Element root) {

        this.root = root;

    }

    public WeatherUtil() {

    }

    /**
     * 通过输入流来获取新浪接口信息
     * @param inStream
     */
    public WeatherUtil(InputStream inStream) {

        if (inStream != null) {

            this.inStream = inStream;

            DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();

            try {

                DocumentBuilder domBuilder = domfac.newDocumentBuilder();

                Document doc = domBuilder.parse(inStream);

                root = doc.getDocumentElement();

            } catch (ParserConfigurationException e) {

                e.printStackTrace();

            } catch (SAXException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            }
        }
    }

    public WeatherUtil(String path) {

        InputStream inStream = null;

        try {

            inStream = new FileInputStream(path);

        } catch (FileNotFoundException e1) {

            e1.printStackTrace();

        }

        if (inStream != null) {

            this.inStream = inStream;

            DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();

            try {

                DocumentBuilder domBuilder = domfac.newDocumentBuilder();

                Document doc = domBuilder.parse(inStream);

                root = doc.getDocumentElement();

            } catch (ParserConfigurationException e) {

                e.printStackTrace();

            } catch (SAXException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            }
        }
    }

    public WeatherUtil(URL url) {

        InputStream inStream = null;

        try {

            inStream = url.openStream();

        } catch (IOException e1) {

            e1.printStackTrace();

        }

        if (inStream != null) {

            this.inStream = inStream;

            DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();

            try {

                DocumentBuilder domBuilder = domfac.newDocumentBuilder();

                Document doc = domBuilder.parse(inStream);

                root = doc.getDocumentElement();

            } catch (ParserConfigurationException e) {

                e.printStackTrace();

            } catch (SAXException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            }
        }
    }

    /**
     *
     * @param nodes
     * @return 单个节点多个值以分号分隔
     */
    public Map<String, String> getValue(String[] nodes) {

        if (inStream == null || root==null) {

            return null;

        }

        Map<String, String> map = new HashMap<String, String>();

        // 初始化每个节点的值为null
        for (int i = 0; i < nodes.length; i++) {

            map.put(nodes[i], null);

        }

        // 遍历第一节点
        NodeList topNodes = root.getChildNodes();

        if (topNodes != null) {

            for (int i = 0; i < topNodes.getLength(); i++) {

                Node book = topNodes.item(i);

                if (book.getNodeType() == Node.ELEMENT_NODE) {

                    for (int j = 0; j < nodes.length; j++) {

                        for (Node node = book.getFirstChild(); node != null; node = node.getNextSibling()) {

                            if (node.getNodeType() == Node.ELEMENT_NODE) {

                                if (node.getNodeName().equals(nodes[j])) {

                                    String val = node.getTextContent();

                                    String temp = map.get(nodes[j]);

                                    if (temp != null && !temp.equals("")) {

                                        temp = temp + ";" + val;

                                    } else {

                                        temp = val;

                                    }

                                    map.put(nodes[j], temp);

                                }
                            }
                        }
                    }
                }
            }
        }
        return map;
    }
    /*标签	说明
city	对应的查询城市
status1	白天天气情况
status2	夜间天气情况
figure1	白天天气情况拼音
figure2	夜间天气情况拼音
direction1	白天风向
direction2	夜晚风向
power1	白天风力
power2	夜间风力
temperature1	白天温度
temperature2	夜间温度
ssd	体感指数
tgd1	白天体感温度
tgd2	夜间体感温度
zwx	紫外线强度
ktk	空调指数
pollution	污染指数
xcz	洗车指数
zho
diy	没猜出来是什么指数，没有数值
fas	同上
chy	穿衣指数
zho_shuoming	zho的说明，
diy_shuoming	同上
fas_shuoming	同上
chy_shuoming	穿衣指数说明
pollution_l	污染程度
zwx_l	紫外线指数概述
ssd_l	体感指数概述
fas_l	这个不知道
zho_l	这个也不清楚
chy_l	穿衣指数概述（可理解为穿衣建议）
ktk_l	空调指数概述
xcz_l	洗车指数概述
diy_l	这个不知道
pollution_s	污染指数详细说明
zwx_s	紫外线详细说明
ssd_s	体感详细说明
ktk_s	空调指数详细说明
xcz_s	洗车详细说明
gm	感冒指数
gm_l	感冒指数概述
gm_s	感冒指数详细说明
yd	运动指数
yd_l	运动指数概述
yd_s	运动指数详细说明
savedate_weather	天气数据日期
savedate_life	生活数据日期
savedate_zhishu	指数数据日期
udatetime	更新时间*/
    /**
     * 查询天气
     *
     * @param city 城市名
     * @param day  天 0 当天，1明天 2天后  3 3天后 4 4天后
     * @throws Exception
     */
    public static  Map<String, String> getWeather(String city,Integer day){
        try {
            city=URLEncoder.encode(city,"GBK");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        String link="http://php.weather.sina.com.cn/xml.php?city="+city+"&password=DJOYnieT8234jlsK&day="+day;


        URL url;
        Map<String, String> map=new HashMap<String, String>();
        try {
            url = new URL(link);
            WeatherUtil parser = new WeatherUtil(url);
            String[] nodes = {"figure1","figure2","direction1","direction2","power1","power2","ssd","tgd1","tgd2","zwx","ktk","city","status1","temperature1","status2","temperature2","udatetime","savedate_zhishu","savedate_life","savedate_weather","yd_s","yd_l","yd","gm_s","gm_l","gm","ktk_s","ssd_s","zwx_s","pollution_s","ktk_l","chy_l","ssd_l","zwx_l","pollution_l","chy_shuoming"};
            map = parser.getValue(nodes);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return  map;
    }

}
