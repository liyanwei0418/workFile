package cn.gukeer.platform.modelView.importExport;

import cn.gukeer.common.utils.excel.annotation.ExcelField;

import java.io.Serializable;

/**
 * Created by LL on 2017/5/4.
 */
public class IOCRefClassRoomView implements Serializable {
    private String cycleYear;
    private Integer cycleSemester;
    private String xdName;//学段
    private String nj;//年级
    private String bj;//班级
    private String xiaoQu;
    private String teachBuilding;//教学楼名称
    private String classRoomNum;
    private String floor;



    @ExcelField(title = "楼层", align = 2, sort = 8, groups = {1, 2})
    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    @ExcelField(title = "学年", align = 2, sort = 1, groups = {1, 2})
    public String getCycleYear() {
        return cycleYear;
    }

    public void setCycleYear(String cycleYear) {
        this.cycleYear = cycleYear;
    }
    @ExcelField(title = "学期", align = 2, sort = 2, groups = {1, 2})
    public Integer getCycleSemester() {
        return cycleSemester;
    }
    @ExcelField(title = "学段", align = 2, sort = 3, groups = {1, 2})
    public String getXdName() {
        return xdName;
    }

    public void setXdName(String xdName) {
        this.xdName = xdName;
    }

    @ExcelField(title = "年级", align = 2, sort = 4, groups = {1, 2})
    public String getNj() {
        return nj;
    }

    public void setNj(String nj) {
        this.nj = nj;
    }

    @ExcelField(title = "班级", align = 2, sort = 5, groups = {1, 2})
    public String getBj() {
        return bj;
    }

    public void setBj(String bj) {
        this.bj = bj;
    }

    @ExcelField(title = "校区", align = 2, sort = 6, groups = {1, 2})
    public String getXiaoQu() {
        return xiaoQu;
    }

    public void setXiaoQu(String xiaoQu) {
        this.xiaoQu = xiaoQu;
    }

    @ExcelField(title = "教学楼名称", align = 2, sort =7, groups = {1, 2})
    public String getTeachBuilding() {
        return teachBuilding;
    }

    public void setTeachBuilding(String teachBuilding) {
        this.teachBuilding = teachBuilding;
    }

    @ExcelField(title = "教室号", align =2, sort = 9, groups = {1, 2})
    public String getClassRoomNum() {
        return classRoomNum;
    }

    public void setClassRoomNum(String classRoomNum) {
        this.classRoomNum = classRoomNum;
    }



    public void setCycleSemester(Integer cycleSemester) {
        this.cycleSemester = cycleSemester;
    }
}
