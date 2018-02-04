package cn.gukeer.platform.persistence.entity;

import java.io.Serializable;

/**
 * Created by LL on 2017/9/25.
 */
public class SeaFileAccountInfo implements Serializable {
    private String strTotal;
    private String strUsage;
    private Long createTime;
    private String strCreateTime;
    private String email;
    private Double total;
    private Double usage;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStrTotal() {
        return strTotal;
    }

    public void setStrTotal(String strTotal) {
        this.strTotal = strTotal;
    }

    public String getStrUsage() {
        return strUsage;
    }

    public void setStrUsage(String strUsage) {
        this.strUsage = strUsage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getUsage() {
        return usage;
    }

    public void setUsage(Double usage) {
        this.usage = usage;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getStrCreateTime() {
        return strCreateTime;
    }

    public void setStrCreateTime(String strCreateTime) {
        this.strCreateTime = strCreateTime;
    }
}
