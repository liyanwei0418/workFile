package cn.gukeer.platform.common;

public class GradeNameUtil {
	  public static String getStringName (int key) {
	        String rst = null;
	       
	               if (1 == key) {
	            rst = "一年级";
	        } else if (2 == key) {
	            rst = "二年级";
	        } else if (3 == key) {
	            rst = "三年级";
	        } else if (4 == key) {
	            rst = "四年级";
	        } else if (5 == key) {
	            rst = "五年级";
	        } else if (6 == key) {
	            rst = "六年级";
	        }
	        return rst;
	    }
}
