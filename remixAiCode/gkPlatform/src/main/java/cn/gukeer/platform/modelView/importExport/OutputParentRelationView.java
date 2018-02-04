package cn.gukeer.platform.modelView.importExport;

import cn.gukeer.common.utils.excel.annotation.ExcelField;

import java.io.Serializable;

/**
 * Created by conn on 2016/9/18.
 */
public class OutputParentRelationView implements Serializable {
    private String xq;
    private String xd;
    private String nj;
    private String className;
    private String stuNum;
    private String stuName;
    private String parName;
    private String relation;
    private String work;
    private String workAt;
    private String parentPhone;
    private String gender;
    private String guardian;
    private String lifeTogether;

    @ExcelField(title = "学籍号码", align = 2, sort = 1, groups = {1, 2}, isnull = 1)
    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    @ExcelField(title = "学生姓名", align = 2, sort = 2, groups = {1, 2}, isnull = 1)
    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    @ExcelField(title = "家长姓名", align = 2, sort = 3, groups = {1, 2}, isnull = 1)
    public String getParName() {
        return parName;
    }

    public void setParName(String parName) {
        this.parName = parName;
    }

    @ExcelField(title = "关系", align = 2, sort = 4, groups = {1, 2}, isnull = 1)
    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    @ExcelField(title = "家长职务", align = 2, sort = 5, groups = {1, 2}, isnull = 1)
    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    @ExcelField(title = "工作单位", align = 2, sort = 6, groups = {1, 2}, isnull = 1)
    public String getWorkAt() {
        return workAt;
    }

    public void setWorkAt(String workAt) {
        this.workAt = workAt;
    }

    @ExcelField(title = "联系方式", align = 2, sort = 7, groups = {1, 2}, isnull = 1)
    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }

    @ExcelField(title = "家长性别", align = 2, sort = 8, groups = {1, 2}, isnull = 1)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @ExcelField(title = "是否为监护人", align = 2, sort = 9, groups = {1, 2}, isnull = 1)
    public String getGuardian() {
        return guardian;
    }

    public void setGuardian(String guardian) {
        this.guardian = guardian;
    }

    @ExcelField(title = "是否一起生活", align = 2, sort = 10, groups = {1, 2}, isnull = 1)
    public String getLifeTogether() {
        return lifeTogether;
    }

    public void setLifeTogether(String lifeTogether) {
        this.lifeTogether = lifeTogether;
    }

    @ExcelField(title = "校区", align = 2, sort = 11, groups = {1, 2}, isnull = 1)
    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }

    @ExcelField(title = "学段", align = 2, sort = 12, groups = {1, 2}, isnull = 1)
    public String getXd() {
        return xd;
    }

    public void setXd(String xd) {
        this.xd = xd;
    }

    @ExcelField(title = "年级", align = 2, sort = 13, groups = {1, 2}, isnull = 1)
    public String getNj() {
        return nj;
    }

    public void setNj(String nj) {
        this.nj = nj;
    }

    @ExcelField(title = "班级", align = 2, sort = 14, groups = {1, 2}, isnull = 1)
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
