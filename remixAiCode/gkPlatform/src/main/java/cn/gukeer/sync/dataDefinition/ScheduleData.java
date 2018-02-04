package cn.gukeer.sync.dataDefinition;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/9.
 */

public class ScheduleData implements Serializable {

    private String area;//教室所属校区
    private String classRoomId;//教室Id
    private String courseTeachId;//教师授课安排标识号
    private String courseTime;//课程时长
    private String dsz;//单双周（0普通，1单周，2双周
    private String isLock;//是否锁定
    private String layer;//教室楼层
    private String no;//教室楼号
    private String number;//教室编号
    private String schoolId;//学校ID
    private String tableId;//周节次（如:1,2 代表周一第二节课）

    public ScheduleData() {
    }

    public ScheduleData(String area, String classRoomId, String courseTeachId, String courseTime, String dsz, String isLock, String layer, String no, String number, String schoolId, String tableId) {
        this.area = area;
        this.classRoomId = classRoomId;
        this.courseTeachId = courseTeachId;
        this.courseTime = courseTime;
        this.dsz = dsz;
        this.isLock = isLock;
        this.layer = layer;
        this.no = no;
        this.number = number;
        this.schoolId = schoolId;
        this.tableId = tableId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getClassRoomId() {
        return classRoomId;
    }

    public void setClassRoomId(String classRoomId) {
        this.classRoomId = classRoomId;
    }

    public String getCourseTeachId() {
        return courseTeachId;
    }

    public void setCourseTeachId(String courseTeachId) {
        this.courseTeachId = courseTeachId;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getDsz() {
        return dsz;
    }

    public void setDsz(String dsz) {
        this.dsz = dsz;
    }

    public String getIsLock() {
        return isLock;
    }

    public void setIsLock(String isLock) {
        this.isLock = isLock;
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
}
