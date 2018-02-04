package cn.gukeer.platform.modelView;

import cn.gukeer.common.utils.excel.annotation.ExcelField;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LL on 2017/4/5.
 */
public class BZRView implements Serializable{
    private String id;
    private String className;
    private String classId;
    private String teacherName;
    private String teacherId;
    private String deputymasterName;
    private String masterName;
    private String courseTeacher;
    private int type;
    private String deputyIds;
    private String teacherNo;//教师编号
    private String xdName;//学段
    private int nj;//年级
    private String bj;//班级
    private String courseId;
    private String courseName;
    private String courseClassId;
    private String sectionId;
    private String cycleId;

    public String getCycleId() {
        return cycleId;
    }

    public void setCycleId(String cycleId) {
        this.cycleId = cycleId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getCourseClassId() {
        return courseClassId;
    }

    public void setCourseClassId(String courseClassId) {
        this.courseClassId = courseClassId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @ExcelField(title = "学段", align = 2, sort = 1, groups = {1, 2})
    public String getXdName() {
        return xdName;
    }

    public void setXdName(String xdName) {
        this.xdName = xdName;
    }


    public int getNj() {
        return nj;
    }

    public void setNj(int nj) {
        this.nj = nj;
    }


    public String getBj() {
        return bj;
    }

    public void setBj(String bj) {
        this.bj = bj;
    }

    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    public String getDeputyIds() {
        return deputyIds;
    }

    public void setDeputyIds(String deputyIds) {
        this.deputyIds = deputyIds;
    }


    public String getDeputymasterName() {
        return deputymasterName;
    }

    public void setDeputymasterName(String deputymasterName) {
        this.deputymasterName = deputymasterName;
    }


    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(String courseTeacher) {
        this.courseTeacher = courseTeacher;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
}
