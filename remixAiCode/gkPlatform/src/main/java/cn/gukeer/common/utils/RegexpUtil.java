package cn.gukeer.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by conn on 2016/9/9.
 */
public class RegexpUtil {

    public final static String REGEX_MAC = "^[a-fA-F0-9]{2}+:[a-fA-F0-9]{2}+:[a-fA-F0-9]{2}+:[a-fA-F0-9]{2}+:[a-fA-F0-9]{2}+:[a-fA-F0-9]{2}$";//MAC地址
    public final static String REGEX_MAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";//邮箱
    public final static String REGEX_MOBILE = "^(13|15|18)\\d{9}$";//手机号码
    public final static String REGEX_IDENTIFY = "^\\d{15}(\\d{2}[A-Za-z0-9])?$";//身份证
    public final static String[] REGEX_SEX = {"男", "女", "", null};
    public final static String[] REGEX_FLAG = {"是", "否", "", null};
    public final static String[] REGEX_ZGXL = {"小学", "初中", "中职/高中", "专科", "本科", "硕士研究生", "博士研究生", "", null};
    public final static String[] REGEX_ZZMM = {"其他", "群众", "共青团员", "中共党员", "", null};

    public static boolean scoreFormat(String itemUnit, String score) {
        if ((itemUnit.contains("分") && !itemUnit.contains("分钟")) || itemUnit.contains("1000") || itemUnit.contains("800")) {
            int indexS = score.indexOf("′");
            int indexSd = score.indexOf("'");
            if (indexS != indexSd) {
                return true;
            } else {
                return false;
            }
        } else {
            /*String reg = "^\\d+(\\.\\d+)?$";
            return Pattern.compile(reg).matcher(score).find();*/
            return isFloat(score);
        }
    }

    //判断浮点数
    public static boolean isFloat(String param) {
        if (StringUtils.isEmpty(param)) {
            return false;
        }
        String reg = "^(-?\\d+)(\\.\\d+)?$";
        return Pattern.compile(reg).matcher(param).find();
    }

    //判断 "^\\d+(\\.\\d+)?$"　　//非负浮点数（正浮点数   +   0）
    public static boolean isFloatPlus(String param) {
        if (StringUtils.isEmpty(param)) {
            return false;
        }
        String reg = "^\\d+(\\.\\d+)?$";
        return Pattern.compile(reg).matcher(param).find();
    }

    //判断 "^\\d+$"　　//非负整数（正整数   +   0）
    public static boolean isNumPlus(String param) {
        if (StringUtils.isEmpty(param)) {
            return false;
        }
        String reg = "^\\d+$";
        return Pattern.compile(reg).matcher(param).find();
    }

    public static boolean isLevel(String level) {
        if (StringUtils.isEmpty(level)) {
            return false;
        }
        String[] arry1 = {"优秀", "良好", "及格", "不及格"};
        int index1 = Arrays.asList(arry1).indexOf(level);
        if (index1 >= 0) {
            return true;
        } else {
            return false;
        }
    }

    //判断 2012年春，2012年秋。。。。
    public static boolean isPeDate(String param) {
        if (StringUtils.isEmpty(param)) {
            return false;
        }
        String reg = "^\\d{4}年.{1}$";
        return Pattern.compile(reg).matcher(param).find();
    }

    public static boolean isDateForMat(String dateStr) {
        if (StringUtils.isEmpty(dateStr)) {
            return false;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 设置日期转化成功标识
        boolean dateflag = true;
        // 这里要捕获一下异常信息
        try {
            Date date = format.parse(dateStr);
        } catch (ParseException e) {
            dateflag = false;
        } finally {
            //	成功：true ;失败:false
            return dateflag;
        }
    }

    public static boolean isMacFormat(String mac) {
        if (StringUtils.isEmpty(mac)) {
            return false;
        }
        String patternMac = "^[a-fA-F0-9]{2}+:[a-fA-F0-9]{2}+:[a-fA-F0-9]{2}+:[a-fA-F0-9]{2}+:[a-fA-F0-9]{2}+:[a-fA-F0-9]{2}$";

        return Pattern.compile(patternMac).matcher(mac).find();
    }

    public static boolean isDictionary(String original, String type) {

        if (StringUtils.isEmpty(original))
            return true;

        String[] reg = {};
        if (type.equals("flag"))
            reg = REGEX_FLAG;
        if (type.equals("sex"))
            reg = REGEX_SEX;
        if (type.equals("zgxl"))
            reg = REGEX_ZGXL;
        if (type.equals("zzmm"))
            reg = REGEX_ZZMM;

        List<String> list = Arrays.asList(reg);
        if (list.contains(original))
            return true;
        return false;
    }

    public static boolean formatValidate(String original, String type) {
        if (StringUtils.isEmpty(original)) {
            return true;
        }

        String patternMac = "";

        if (type.equals("mail"))
            patternMac = REGEX_MAIL;
        if (type.equals("mac"))
            patternMac = REGEX_MAC;
        if (type.equals("mobile"))
            patternMac = REGEX_MOBILE;
        if (type.equals("identify"))
            patternMac = REGEX_IDENTIFY;

        return Pattern.compile(patternMac).matcher(original).find();
    }

    public static void main(String[] args) {
        System.out.println(formatValidate("fengxinmei@dszx", "mail"));


    }


}
