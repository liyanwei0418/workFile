package cn.gukeer.platform.modelView.importExport;

import cn.gukeer.common.utils.excel.annotation.ExcelField;

import java.io.Serializable;

/**
 * Created by LL on 2017/3/31.
 */
public class IORefCycleTeacherClassView implements Serializable {
    private int bh;
    private String semester;
    private Long teachYear;
    private int nj;
    private String master_name;
    private String deputy_master_name;

    @ExcelField(title = "班级", align = 2, sort = 3, groups = {1, 2})
    public int getBh() {
        return bh;
    }

    public void setBh(int bh) {
        this.bh = bh;
    }

    @ExcelField(title = "学期", align = 2, sort = 3, groups = {1, 2})
    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @ExcelField(title = "学年", align = 2, sort = 3, groups = {1, 2})
    public Long getTeachYear() {
        return teachYear;
    }

    public void setTeachYear(Long teachYear) {
        this.teachYear = teachYear;
    }

    @ExcelField(title = "年级", align = 2, sort = 3, groups = {1, 2})
    public int getNj() {
        return nj;
    }

    public void setNj(int nj) {
        this.nj = nj;
    }

    @ExcelField(title = "班主任", align = 2, sort = 3, groups = {1, 2})
    public String getMaster_name() {
        return master_name;
    }

    public void setMaster_name(String master_name) {
        this.master_name = master_name;
    }

    @ExcelField(title = "副班主任", align = 2, sort = 3, groups = {1, 2})
    public String getDeputy_master_name() {
        return deputy_master_name;
    }

    public void setDeputy_master_name(String deputy_master_name) {
        this.deputy_master_name = deputy_master_name;
    }
}
