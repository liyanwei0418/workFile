package cn.gukeer.common.utils;

import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by connli on 16/4/6.
 */
public class GlobalUtils {
    public static final String REGEX_MOBILE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(147)|(17[0-9]))\\d{8}$";

    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    public static boolean isPhoneNum(String phone) {
        if (StringUtils.isEmpty(phone)) return false;
        return Pattern.matches(REGEX_MOBILE, phone);
    }

    public static boolean isEmail(String email) {
        if (StringUtils.isEmpty(email)) return false;
        return Pattern.matches(REGEX_EMAIL, email);
    }

    public static String generatorGroupTag(long id) {
        String tag = String.format("%d_%d", id, System.currentTimeMillis());

        return new String(Base64Utils.encode(tag.getBytes()));
    }

    public static long parseIdFormGroupTag(String tag) {
        byte[] bytes = Base64Utils.decode(tag.getBytes());
        String ori = new String(bytes);
        String id = ori.split("_")[0];
        long _id = 0;
        try {
            _id = Long.valueOf(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return _id;
    }

    public static boolean isNullList(List list) {
        return null == list || list.isEmpty();
    }

    public static boolean isNotNullList(List list) {
        return !isNullList(list);
    }

}
