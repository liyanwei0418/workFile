package cn.gukeer.platform.common;

/**
 * Created by conn on 2016/8/24.
 */
public class GradeClassUtil {

    public static String getGradeNj (int nj) {
        String rst = null;
        if (1 == nj) {
            rst = "一年级";
        } else if (2 == nj) {
            rst = "二年级";
        } else if (3 == nj) {
            rst = "三年级";
        } else if (4 == nj) {
            rst = "四年级";
        } else if (5 == nj) {
            rst = "五年级";
        } else if (6 == nj) {
            rst = "六年级";
        } else if (7 == nj) {
            rst = "七年级";
        } else if (8 == nj) {
            rst = "八年级";
        } else if (9 == nj) {
            rst = "九年级";
        } else if (10 == nj) {
            rst = "十年级";
        } else if (11 == nj) {
            rst = "十一年级";
        } else if (12 == nj) {
            rst = "十二年级";
        }
        return rst;
    }
}
