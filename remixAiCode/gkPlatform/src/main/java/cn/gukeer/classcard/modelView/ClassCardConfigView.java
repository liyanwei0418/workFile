package cn.gukeer.classcard.modelView;

import cn.gukeer.classcard.persistence.entity.ClassCardConfig;

public class ClassCardConfigView extends ClassCardConfig {
    private String startDateView;
    private String endDateView;
    private String startTimeView;
    private String endTimeView;

    public String getStartDateView() {
        return startDateView;
    }

    public void setStartDateView(String startDateView) {
        this.startDateView = startDateView;
    }

    public String getEndDateView() {
        return endDateView;
    }

    public void setEndDateView(String endDateView) {
        this.endDateView = endDateView;
    }

    public String getStartTimeView() {
        return startTimeView;
    }

    public void setStartTimeView(String startTimeView) {
        this.startTimeView = startTimeView;
    }

    public String getEndTimeView() {
        return endTimeView;
    }

    public void setEndTimeView(String endTimeView) {
        this.endTimeView = endTimeView;
    }
}
