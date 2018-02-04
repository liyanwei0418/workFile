package cn.gukeer.platform.persistence.entity;

import java.io.Serializable;

/**
 * Created by LL on 2017/9/25.
 */
public class SeaFileAllAccount implements Serializable{
    private String source;
    private String email;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
