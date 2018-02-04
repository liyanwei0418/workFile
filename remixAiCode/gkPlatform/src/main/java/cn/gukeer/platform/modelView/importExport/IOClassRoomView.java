package cn.gukeer.platform.modelView.importExport;

import cn.gukeer.common.utils.excel.annotation.ExcelField;

import java.io.Serializable;

/**
 * Created by conn on 2016/9/17.
 */
public class IOClassRoomView implements Serializable {
   // private String roomName;//教室
    private String roomTypeName;//教室类型名字    5
    private String schoolTypeName;//所在校区        1
    private String floor;//楼层                   3
    private Integer count;//容纳人数                6
    private Integer availableSeat;//有效座位数       7
    private Integer examSeat;//考试座位数            8
    private String roomNum;//房间号                4
    private String courseSelect;//是否用于选课    9
    private String remarks;//备注                 10
    private String teachBuilding;//所在楼           2

/*
    @ExcelField(title = "教室", align = 2, sort = 1, groups = {1, 2})
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
*/

    @ExcelField(title = "所在校区", align = 2, sort = 2, groups = {1, 2})
    public String getSchoolTypeName() {
        return schoolTypeName;
    }

    public void setSchoolTypeName(String schoolTypeName) {
        this.schoolTypeName = schoolTypeName;
    }

    @ExcelField(title = "所在楼", align = 2, sort = 3, groups = {1, 2})
    public String getTeachBuilding() {
        return teachBuilding;
    }

    public void setTeachBuilding(String teachBuilding) {
        this.teachBuilding = teachBuilding;
    }

    @ExcelField(title = "楼层", align = 2, sort = 4, groups = {1, 2})
    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    @ExcelField(title = "房间号", align = 2, sort = 5, groups = {1, 2})
    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    @ExcelField(title = "教室类型", align = 2, sort =6, groups = {1, 2})
    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    @ExcelField(title = "容纳人数", align = 2, sort = 7, groups = {1, 2})
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @ExcelField(title = "有效座位数", align = 2, sort = 8, groups = {1, 2})
    public Integer getAvailableSeat() {
        return availableSeat;
    }

    public void setAvailableSeat(Integer availableSeat) {
        this.availableSeat = availableSeat;
    }

    @ExcelField(title = "考试座位数", align = 2, sort = 9, groups = {1, 2})
    public Integer getExamSeat() {
        return examSeat;
    }

    public void setExamSeat(Integer examSeat) {
        this.examSeat = examSeat;
    }

    @ExcelField(title = "是否用于选课", align = 2, sort = 10, groups = {1, 2})
    public String getCourseSelect() {
        return courseSelect;
    }

    public void setCourseSelect(String courseSelect) {
        this.courseSelect = courseSelect;
    }


    @ExcelField(title = "备注", align = 2, sort = 11, groups = {1, 2})
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
