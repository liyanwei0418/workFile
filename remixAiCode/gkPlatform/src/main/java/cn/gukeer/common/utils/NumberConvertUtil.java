package cn.gukeer.common.utils;

import cn.gukeer.common.tld.GukeerStringUtil;
import cn.gukeer.platform.common.EnumeratedConstant;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 接受默认值，处理异常
 * <p>
 * Created by conn on 2016/8/8.
 */
public class NumberConvertUtil {

    public static int convertS2I(String val) {
        return convertS2I(val, 0);
    }

    public static int convertS2I(String val, int def) {
        int rst = def;
        try {
            rst = Integer.parseInt(val);
        } catch (Exception e) {

        }
        return rst;
    }

    public static int getVal(Integer val) {
        if (null == val) return 0;
        return val;
    }

    public static float getVal(Float val) {
        if (null == val) return 0f;
        return val;
    }

    public static double getVal(Double val) {
        if (null == val) return 0;
        return val;
    }

    public static long getVal(Long val) {
        if (null == val) return 0l;
        return val;
    }

    public static String getNumFromStr(String str) {
        if (str == "" || str == null) {
            return "0";
        } else {
            String regEx = "[^0-9]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            String res = m.replaceAll("").trim();
            if (res.equals("")) {
                return "0";
            } else {
                return m.replaceAll("").trim();
            }
        }
    }

    public static Double sub(String val1, String val2) {
        // 进行减法运算
        BigDecimal b1 = new BigDecimal(val1);
        BigDecimal b2 = new BigDecimal(val2);
        return b1.subtract(b2).doubleValue();
    }

    public static Double add(String d1, String d2) {        // 进行加法运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.add(b2).doubleValue();
    }

    public static Double mul(String d1, String d2) {        // 进行乘法运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.multiply(b2).doubleValue();
    }

    public static Double division(Object d1, Object d2, int len) {// 进行除法运算
        if (GukeerStringUtil.isNullOrEmpty(d1) || GukeerStringUtil.isNullOrEmpty(d2))
            return 0.0;
        BigDecimal b1 = new BigDecimal(d1.toString());
        BigDecimal b2 = new BigDecimal(d2.toString());
        double res = b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).doubleValue();
        return res;
    }

    public static void main(String[] args) {
        System.out.println(EnumeratedConstant.Platform.getName());
        System.out.println(EnumeratedConstant.Platform.getIndex());
    }

}
