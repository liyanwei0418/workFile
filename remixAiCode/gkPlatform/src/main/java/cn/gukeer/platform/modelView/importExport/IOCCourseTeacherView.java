package cn.gukeer.platform.modelView.importExport;

import cn.gukeer.common.utils.excel.annotation.ExcelField;

import java.io.Serializable;

/**
 * Created by LL on 2017/4/20.
 */
public class IOCCourseTeacherView implements Serializable {
    private String xdName;//学段
    private String nj;//年级

    private String bj;//班级
    private String course;
    private String courseTeacher;


    @ExcelField(title = "学段", align = 2, sort = 1, groups = {1, 2})
    public String getXdName() {
        return xdName;
    }

    public void setXdName(String xdName) {
        this.xdName = xdName;
    }

    @ExcelField(title = "年级", align = 2, sort = 2, groups = {1, 2})
    public String getNj() {
        return nj;
    }

    public void setNj(String nj) {
        this.nj = nj;
    }



    @ExcelField(title = "班级", align = 2, sort = 3, groups = {1, 2})
    public String getBj() {
        return bj;
    }

    public void setBj(String bj) {
        this.bj = bj;
    }

    @ExcelField(title = "科目", align = 2, sort = 4, groups = {1, 2})
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @ExcelField(title = "任课教师", align = 2, sort = 5, groups = {1, 2})
    public String getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(String courseTeacher) {
        this.courseTeacher = courseTeacher;
    }
}
