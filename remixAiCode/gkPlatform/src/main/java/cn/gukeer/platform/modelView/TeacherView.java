package cn.gukeer.platform.modelView;

import cn.gukeer.platform.persistence.entity.Teacher;

/**
 * Created by conn on 2016/8/21.
 */
public class TeacherView extends Teacher {
    private String departName;

    private String titleName;

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

}
