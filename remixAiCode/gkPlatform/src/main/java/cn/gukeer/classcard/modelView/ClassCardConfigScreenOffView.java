package cn.gukeer.classcard.modelView;

import cn.gukeer.classcard.persistence.entity.ClassCardConfigScreenOffRef;

public class ClassCardConfigScreenOffView extends ClassCardConfigScreenOffRef {
    private String sStartTimeView;
    private String sEndTimeView;

    public String getsStartTimeView() {
        return sStartTimeView;
    }

    public void setsStartTimeView(String sStartTimeView) {
        this.sStartTimeView = sStartTimeView;
    }

    public String getsEndTimeView() {
        return sEndTimeView;
    }

    public void setsEndTimeView(String sEndTimeView) {
        this.sEndTimeView = sEndTimeView;
    }
}
