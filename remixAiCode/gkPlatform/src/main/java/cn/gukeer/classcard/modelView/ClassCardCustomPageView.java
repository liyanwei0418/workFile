package cn.gukeer.classcard.modelView;

import cn.gukeer.classcard.persistence.entity.ClassCardCustomPage;

/**
 * Created by leeyh on 2018/1/18.
 */
public class ClassCardCustomPageView extends ClassCardCustomPage {
    private String startTimeView;
    private String endTimeView;
    private String createByName;

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

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }
}
