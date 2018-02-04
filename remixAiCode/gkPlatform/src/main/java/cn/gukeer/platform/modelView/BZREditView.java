package cn.gukeer.platform.modelView;

import cn.gukeer.platform.persistence.entity.Teacher;

/**
 * Created by Administrator on 2017/5/21.
 */
public class BZREditView {
    private Teacher teacher;
    private String selectedId;

    public String getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(String selectedId) {
        this.selectedId = selectedId;
    }

    public Teacher getTeacher() {

        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
