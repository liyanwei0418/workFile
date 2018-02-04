package cn.gukeer.platform.modelView;

import cn.gukeer.common.utils.excel.annotation.ExcelField;

import java.io.Serializable;

/**
 * Created by LL on 2017/9/13.
 */
public class IOCTableView implements Serializable {
    private String nodeAndWeek;
    private String monday;
    private String tuesday;
    private String wednsday;
    private String thursday;
    private String friday;

    @ExcelField(title = "节次/星期", align = 2, sort = 1, groups = {1, 2})
    public String getNodeAndWeek() {
        return nodeAndWeek;
    }

    public void setNodeAndWeek(String nodeAndWeek) {
        this.nodeAndWeek = nodeAndWeek;
    }

    @ExcelField(title = "星期一", align = 2, sort = 2, groups = {1, 2})
    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    @ExcelField(title = "星期二", align = 2, sort = 3, groups = {1, 2})
    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    @ExcelField(title = "星期三", align = 2, sort = 4, groups = {1, 2})
    public String getWednsday() {
        return wednsday;
    }

    public void setWednsday(String wednsday) {
        this.wednsday = wednsday;
    }

    @ExcelField(title = "星期四", align = 2, sort = 5, groups = {1, 2})
    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    @ExcelField(title = "星期五", align = 2, sort = 6, groups = {1, 2})
    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }
}
