package cn.gukeer.platform.common;

/**
 * Created by conn on 2016/9/12.
 */
public class UtilEntity {
    private String key;
    private String value;
    public UtilEntity(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getVal() {
        return value;
    }

    public void setVal(String value) {
        this.value = value;
    }
}
