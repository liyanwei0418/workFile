package cn.gukeer.platform.modelView.importExport;

//import cn.gukeer.common.utils.excelUtil.annotation.ExcelField;

import cn.gukeer.common.utils.excel.annotation.ExcelField;

import java.io.Serializable;

/**
 * Created by conn on 2016/9/18.
 */
public class IOBJClassView implements Serializable {
    private String name ;
    private String shortName ;
    private String bh;
    private String xq;
    private String bjlx;
    private String rxnd;
    private String xd;
    private String nj;

    @ExcelField(title = "班级名称", align = 2, sort = 3, groups = {1, 2},isnull=1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @ExcelField(title = "班级简称", align = 2, sort = 4, groups = {1, 2})
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    @ExcelField(title = "班号", align = 2, sort = 5, groups = {1, 2})
    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }
    @ExcelField(title = "校区", align = 2, sort = 6, groups = {1, 2},isnull=1)
    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }
    @ExcelField(title = "班级类型", align = 2, sort =7, groups = {1, 2})
    public String getBjlx() {
        return bjlx;
    }

    public void setBjlx(String bjlx) {
        this.bjlx = bjlx;
    }
    @ExcelField(title = "入学年度", align = 2, sort = 8, groups = {1, 2})
    public String getRxnd() {
        return rxnd;
    }

    public void setRxnd(String rxnd) {
        this.rxnd = rxnd;
    }
    @ExcelField(title = "学段", align = 2, sort = 1, groups = {1, 2},isnull=1)
    public String getXd() {
        return xd;
    }

    public void setXd(String xd) {
        this.xd = xd;
    }
    @ExcelField(title = "年级", align = 2, sort = 2, groups = {1, 2},isnull=1)
    public String getNj() {
        return nj;
    }

    public void setNj(String nj) {
        this.nj = nj;
    }
}
