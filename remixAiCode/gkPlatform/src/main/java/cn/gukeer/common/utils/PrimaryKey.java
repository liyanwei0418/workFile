package cn.gukeer.common.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

/**
 * Created by connli on 16-12-15.
 */
public class PrimaryKey {

    public static String get() {

        String uuid = UUID.randomUUID().toString();
        String key = DigestUtils.md5Hex(uuid);

        return key;
    }

}
