package cn.gukeer.platform.modelView.importExport;

import cn.gukeer.common.utils.excel.annotation.ExcelField;

import java.io.Serializable;

/**
 * Created by LL on 2017/4/20.
 */
public class IOCMasterView implements Serializable {
    private String xdName;//学段
    private String  nj;//年级
    private String bj;//班级
    private String masterName;//班主任
    private String deputymasterName;//副班主任
    private String cycleYear;
    private Integer cycleSemester;

//    private String info;
//
//    @ExcelField(title = "失败原因", align = 2, sort = 8, groups = {1, 2})
//    public String getInfo() {
//        return info;
//    }
//
//    public void setInfo(String info) {
//        this.info = info;
//    }

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

    @ExcelField(title = "班主任", align = 2, sort = 6, groups = {1, 2})
    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    @ExcelField(title = "副班主任", align = 2, sort = 7, groups = {1, 2})
    public String getDeputymasterName() {
        return deputymasterName;
    }

    public void setDeputymasterName(String deputymasterName) {
        this.deputymasterName = deputymasterName;
    }


    public void setCycleSemester(Integer cycleSemester) {
        this.cycleSemester = cycleSemester;
    }
}
