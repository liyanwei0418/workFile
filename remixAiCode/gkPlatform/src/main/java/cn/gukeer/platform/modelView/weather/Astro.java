package cn.gukeer.platform.modelView.weather;

import java.io.Serializable;

public class Astro implements Serializable {

    public String mr;
    public String ms;
    public String sr;
    public String ss;
    public void setMr(String mr) {
        this.mr = mr;
    }
    public String getMr() {
        return mr;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }
    public String getMs() {
        return ms;
    }

    public void setSr(String sr) {
        this.sr = sr;
    }
    public String getSr() {
        return sr;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }
    public String getSs() {
        return ss;
    }

    @Override
    public String toString() {
        return "{" +
                "mr='" + mr + '\'' +
                ", ms='" + ms + '\'' +
                ", sr='" + sr + '\'' +
                ", ss='" + ss + '\'' +
                '}';
    }
}
