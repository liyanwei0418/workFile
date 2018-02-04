package cn.gukeer.platform.modelView.importExport;

//import cn.gukeer.common.utils.excelUtil.annotation.ExcelField;

import cn.gukeer.common.utils.excel.annotation.ExcelField;

import java.io.Serializable;

/**
 * Created by conn on 2016/9/17.
 */
public class OutputStudentView implements Serializable {

    private String xd;
    private String xq;
    private String nj;
    private String bj;
    private String name;
    private String gender;
    private String yxzjlx;
    private String yxzjh;
    private String xh;
    private String jyid;
    private String pinyin;
    private String rxnd;
    private String xslb;
    private String gb;
    private String mz;
    private String jg;
    private String zzmm;
    private String status;
    private String xzz;
    private String hkxz;
    private String lydq;
    private String hkszdXX;
    private String sfbshk;
    private String csrq;
    private String zslb;

    private String faname;
    private String fawork;
    private String faworkat;
    private String faphone;

    private String maname;
    private String mawork;
    private String maworkat;
    private String maphone;

    @ExcelField(title = "学段", align = 2, sort = 1, groups = {1, 2}, isnull = 1)
    public String getXd() {
        return xd;
    }

    public void setXd(String xd) {
        this.xd = xd;
    }

    @ExcelField(title = "校区", align = 2, sort = 2, groups = {1, 2}, isnull = 1)
    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }

    @ExcelField(title = "年级", align = 2, sort = 3, groups = {1, 2}, isnull = 1)
    public String getNj() {
        return nj;
    }

    public void setNj(String nj) {
        this.nj = nj;
    }

    @ExcelField(title = "班级", align = 2, sort = 4, groups = {1, 2}, isnull = 1)
    public String getBj() {
        return bj;
    }

    public void setBj(String bj) {
        this.bj = bj;
    }

    @ExcelField(title = "姓名", align = 2, sort = 5, groups = {1, 2}, isnull = 1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ExcelField(title = "性别", align = 2, sort = 6, groups = {1, 2}, isnull = 0)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @ExcelField(title = "有效证件类型", align = 2, sort = 7, groups = {1, 2}, isnull = 0)
    public String getYxzjlx() {
        return yxzjlx;
    }

    public void setYxzjlx(String yxzjlx) {
        this.yxzjlx = yxzjlx;
    }

    @ExcelField(title = "有效证件号", align = 2, sort = 8, groups = {1, 2}, isnull = 0)
    public String getYxzjh() {
        return yxzjh;
    }

    public void setYxzjh(String yxzjh) {
        this.yxzjh = yxzjh;
    }

    @ExcelField(title = "学籍号", align = 2, sort = 9, groups = {1, 2}, isnull = 0)
    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    @ExcelField(title = "教育Id号", align = 2, sort = 10, groups = {1, 2}, isnull = 0)
    public String getJyid() {
        return jyid;
    }

    public void setJyid(String jyid) {
        this.jyid = jyid;
    }

    @ExcelField(title = "拼音", align = 2, sort = 11, groups = {1, 2}, isnull = 0)
    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @ExcelField(title = "入学年度", align = 2, sort = 12, groups = {1, 2}, isnull = 0)
    public String getRxnd() {
        return rxnd;
    }

    public void setRxnd(String rxnd) {
        this.rxnd = rxnd;
    }

    @ExcelField(title = "学生类别", align = 2, sort = 13, groups = {1, 2}, isnull = 0)
    public String getXslb() {
        return xslb;
    }

    public void setXslb(String xslb) {
        this.xslb = xslb;
    }

    @ExcelField(title = "国别", align = 2, sort = 14, groups = {1, 2}, isnull = 0)
    public String getGb() {
        return gb;
    }

    public void setGb(String gb) {
        this.gb = gb;
    }

    @ExcelField(title = "民族", align = 2, sort = 15, groups = {1, 2}, isnull = 0)
    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }

    @ExcelField(title = "籍贯", align = 2, sort = 16, groups = {1, 2}, isnull = 0)
    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg;
    }

    @ExcelField(title = "政治面貌", align = 2, sort = 17, groups = {1, 2}, isnull = 0)
    public String getZzmm() {
        return zzmm;
    }

    public void setZzmm(String zzmm) {
        this.zzmm = zzmm;
    }

    @ExcelField(title = "在校状态", align = 2, sort = 18, groups = {1, 2}, isnull = 0)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @ExcelField(title = "现居住地", align = 2, sort = 19, groups = {1, 2}, isnull = 0)
    public String getXzz() {
        return xzz;
    }

    public void setXzz(String xzz) {
        this.xzz = xzz;
    }

    @ExcelField(title = "户口性质", align = 2, sort = 20, groups = {1, 2}, isnull = 0)
    public String getHkxz() {
        return hkxz;
    }

    public void setHkxz(String hkxz) {
        this.hkxz = hkxz;
    }

    @ExcelField(title = "来源地区", align = 2, sort = 21, groups = {1, 2}, isnull = 0)
    public String getLydq() {
        return lydq;
    }

    public void setLydq(String lydq) {
        this.lydq = lydq;
    }

    @ExcelField(title = "户口所在地", align = 2, sort = 22, groups = {1, 2}, isnull = 0)
    public String getHkszdXX() {
        return hkszdXX;
    }

    public void setHkszdXX(String hkszdXX) {
        this.hkszdXX = hkszdXX;
    }

    @ExcelField(title = "是否按照本地户口对待", align = 2, sort = 23, groups = {1, 2}, isnull = 0)
    public String getSfbshk() {
        return sfbshk;
    }

    public void setSfbshk(String sfbshk) {
        this.sfbshk = sfbshk;
    }

    @ExcelField(title = "出生日期", align = 2, sort = 24, groups = {1, 2}, isnull = 0)
    public String getCsrq() {
        return csrq;
    }

    public void setCsrq(String csrq) {
        this.csrq = csrq;
    }

    @ExcelField(title = "招生类别", align = 2, sort = 25, groups = {1, 2}, isnull = 0)
    public String getZslb() {
        return zslb;
    }

    public void setZslb(String zslb) {
        this.zslb = zslb;
    }

    @ExcelField(title = "父亲姓名", align = 2, sort = 26, groups = {1, 2}, isnull = 0)
    public String getFaname() {
        return faname;
    }

    public void setFaname(String faname) {
        this.faname = faname;
    }

    @ExcelField(title = "父亲职务或职业", align = 2, sort = 27, groups = {1, 2}, isnull = 0)
    public String getFawork() {
        return fawork;
    }

    public void setFawork(String fawork) {
        this.fawork = fawork;
    }

    @ExcelField(title = "父亲工作单位", align = 2, sort = 28, groups = {1, 2}, isnull = 0)
    public String getFaworkat() {
        return faworkat;
    }

    public void setFaworkat(String faworkat) {
        this.faworkat = faworkat;
    }

    @ExcelField(title = "父亲联系电话", align = 2, sort = 29, groups = {1, 2}, isnull = 0)
    public String getFaphone() {
        return faphone;
    }

    public void setFaphone(String faphone) {
        this.faphone = faphone;
    }

    @ExcelField(title = "母亲姓名", align = 2, sort = 30, groups = {1, 2}, isnull = 0)
    public String getManame() {
        return maname;
    }

    public void setManame(String maname) {
        this.maname = maname;
    }

    @ExcelField(title = "母亲职务或职业", align = 2, sort = 31, groups = {1, 2}, isnull = 0)
    public String getMawork() {
        return mawork;
    }

    public void setMawork(String mawork) {
        this.mawork = mawork;
    }

    @ExcelField(title = "母亲工作单位", align = 2, sort = 32, groups = {1, 2}, isnull = 0)
    public String getMaworkat() {
        return maworkat;
    }

    public void setMaworkat(String maworkat) {
        this.maworkat = maworkat;
    }

    @ExcelField(title = "母亲联系电话", align = 2, sort = 33, groups = {1, 2}, isnull = 0)
    public String getMaphone() {
        return maphone;
    }

    public void setMaphone(String maphone) {
        this.maphone = maphone;
    }
}
