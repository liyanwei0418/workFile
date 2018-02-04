package cn.gukeer.platform.modelView.weather;

import java.io.Serializable;

public class Wind implements Serializable {

    public String deg;
    public String dir;
    public String sc;
    public String spd;
    public void setDeg(String deg) {
        this.deg = deg;
    }
    public String getDeg() {
        return deg;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
    public String getDir() {
        return dir;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }
    public String getSc() {
        return sc;
    }

    public void setSpd(String spd) {
        this.spd = spd;
    }
    public String getSpd() {
        return spd;
    }

    @Override
    public String toString() {
        return "{" +
                "deg='" + deg + '\'' +
                ", dir='" + dir + '\'' +
                ", sc='" + sc + '\'' +
                ", spd='" + spd + '\'' +
                '}';
    }
}
