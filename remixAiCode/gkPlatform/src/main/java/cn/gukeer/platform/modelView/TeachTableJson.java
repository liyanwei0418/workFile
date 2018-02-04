package cn.gukeer.platform.modelView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LL on 2017/7/19.
 */
public class TeachTableJson {
    private String weekday;
    private Map<String,TeachTableView> map=new HashMap<>();

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public Map<String, TeachTableView> getMap() {
        return map;
    }

    public void setMap(Map<String, TeachTableView> map) {
        this.map = map;
    }
}
