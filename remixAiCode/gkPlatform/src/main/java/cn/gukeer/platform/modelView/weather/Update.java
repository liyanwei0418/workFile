package cn.gukeer.platform.modelView.weather;

import java.io.Serializable;

public class Update implements Serializable {

    public String loc;
    public String utc;
    public void setLoc(String loc) {
        this.loc = loc;
    }
    public String getLoc() {
        return loc;
    }

    public void setUtc(String utc) {
        this.utc = utc;
    }
    public String getUtc() {
        return utc;
    }

    @Override
    public String toString() {
        return "{" +
                "loc='" + loc + '\'' +
                ", utc='" + utc + '\'' +
                '}';
    }
}