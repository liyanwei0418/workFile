package cn.gukeer.platform.modelView.weather;

import java.io.Serializable;

public class Tmp implements Serializable {

    public String max;
    public String min;
    public void setMax(String max) {
        this.max = max;
    }
    public String getMax() {
        return max;
    }

    public void setMin(String min) {
        this.min = min;
    }
    public String getMin() {
        return min;
    }

    @Override
    public String toString() {
        return "{" +
                "max='" + max + '\'' +
                ", min='" + min + '\'' +
                '}';
    }
}
