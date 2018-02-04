package cn.gukeer.platform.common;

import java.io.Serializable;

/**
 * Created by conn on 2016/9/12.
 */
public class KVEntity implements Serializable{
    private String key;
    private String value;
    public KVEntity(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
