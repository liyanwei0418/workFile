package cn.gukeer.platform.modelView;

import cn.gukeer.platform.persistence.entity.Student;

/**
 * Created by pc-daisike on 2016/9/27.
 */
public class StudentView extends Student {
    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
