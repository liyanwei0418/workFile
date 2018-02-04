package cn.gukeer.sync.dataDefinition;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/9.
 */

public class ScheduleWeekData implements Serializable {

    private String changeStatu;//是否调课
    private String classId;//班级id
    private String classRoomId;//教室id
    private String classname;//班级名称
    private String classshort;//班级简称
    private String courseId;//课程id
    private String courseName;//课程名字
    private String courseshort;//课程简称
    private String dayTime;//日期，开始上课日期
    private String startTime;//开始时间
    private String endTime;//结束时间
    private String gradeId;//年纪id
    private String id;//课表主键
    private String schoolId;//机构id
    private String tableId;//周节次
    private String teacherId;//教师id
    private String weekStart;//开始周
    private String weekend;//周
    private String xn;//学年
    private String xq;//学期

    public String getChangeStatu() {
        return changeStatu;
    }

    public void setChangeStatu(String changeStatu) {
        this.changeStatu = changeStatu;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassRoomId() {
        return classRoomId;
    }

    public void setClassRoomId(String classRoomId) {
        this.classRoomId = classRoomId;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getClassshort() {
        return classshort;
    }

    public void setClassshort(String classshort) {
        this.classshort = classshort;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseshort() {
        return courseshort;
    }

    public void setCourseshort(String courseshort) {
        this.courseshort = courseshort;
    }

    public String getDayTime() {
        return dayTime;
    }

    public void setDayTime(String dayTime) {
        this.dayTime = dayTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getWeekStart() {
        return weekStart;
    }

    public void setWeekStart(String weekStart) {
        this.weekStart = weekStart;
    }

    public String getWeekend() {
        return weekend;
    }

    public void setWeekend(String weekend) {
        this.weekend = weekend;
    }

    public String getXn() {
        return xn;
    }

    public void setXn(String xn) {
        this.xn = xn;
    }

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }
}
