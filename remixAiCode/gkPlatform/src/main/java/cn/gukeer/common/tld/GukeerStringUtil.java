package cn.gukeer.common.tld;

import cn.gukeer.platform.persistence.entity.ClassSection;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by conn on 2016/8/12.
 */
public class GukeerStringUtil {

    public static Boolean isEmpty(String str) {

        return StringUtils.isEmpty(str);

    }
    //若参数为0则在前端显示为空
    public static String intToString(String str){
       String format="";
        if("0".equals(str)||null==str){

        }else{
            format =str;
        }
        return format;
    }

    public static Boolean notEmpty(String str) {

        return !isEmpty(str);

    }

    /**
     * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty
     *
     * @param obj
     * @return
     */
    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null)
            return true;

        if (obj instanceof CharSequence)
            return ((CharSequence) obj).length() == 0;

        if (obj instanceof Collection)
            return ((Collection) obj).isEmpty();

        if (obj instanceof Map)
            return ((Map) obj).isEmpty();

        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!isNullOrEmpty(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }

    public  static  Boolean listContainsString(List<String> param,String check){
        return param.contains(check);
    }

    public  static Boolean isContainsString(String param,String check){
        return param.contains(check);
    }

    public static  String subStrBefore(String str){
        return str.substring(0,str.indexOf(','));
    }


    public static  String subStrAfter(String str){
        return str.substring(str.indexOf(',')+1,str.length());
    }

    public static void main(String[] args) {
        System.out.println(GukeerStringUtil.isNullOrEmpty(new ClassSection()));
    }
}
