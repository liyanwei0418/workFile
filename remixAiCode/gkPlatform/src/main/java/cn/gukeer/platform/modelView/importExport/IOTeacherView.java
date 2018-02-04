package cn.gukeer.platform.modelView.importExport;

import cn.gukeer.common.utils.excel.annotation.ExcelField;

import java.io.Serializable;

/**
 * Created by conn on 2016/9/17.
 */
public class IOTeacherView implements Serializable {
    /* 职工编号，，，，，，，。，，。,，，，，，，身份，外语水平，最高学制，学位数量，
* 任教学科级别，校区，，岗位分类（副），，，，最高学历，最高毕业学校，原专业，评职时间，来我校时间，住宅电话，工资岗位，，办公室电话，是否华侨，是否班主任，外语语种，原学制，
* 最高学位，专业技术岗位分类，任教学科，实职级别，工资岗位（副）
*
* */

    private String no ;//教职工编号
    private String name ;//姓名
    private String mobile;//手机号码
    private String mail;//邮箱
    private String identity;//身份证号码
    private String gender;//性别
    private String bumen;//部门
    private String startWork;//开始工作时间
    private String hignTime;//最高毕业时间
    private String hignJob;//最高专业
    private String pzxx;//评职详细
    private String adrress;//家庭住址详细
    private String ggjsjb;//骨干教师级别
    private String htkssj;//合同开始时间z
    private String cym;//曾用名
    private String jtyb;//家庭邮编
    private String sfzrjs;//是否专任教师
    private String shenfen;//身份
    private String wysp;//外语水平
    private String zgxz;//最高学制
    private String xwsl;//学位数量
    private String rjxkjb;//任教学科级别
   /* private String xq;//校区*/
    private String xj;//薪级
    private String gwflf;//岗位分类副
    private String jg;//籍贯
    private String zzmm;//政治面貌
    private String ysbysj;//原始毕业时间
    private String zgxl;//最高学历
    private String zgbyxx;//最高毕业学校
    private String yzy;//原专业
    private String pzsj;//评职时间
    private String lwxsj;//来我校时间
    private String zzdh;//住宅电话
    private String gzgw;//工资岗位
    private String htjssj;//合同结束时间
    private String bgsdh;//办公室电话
    private String sfhq;//是否华侨
    private String sfbzr;//是否班主任
    private String wyyz;//外语语种
    private String yxz;//原学制
    private String zgxw;//最高学位
    private String zyjsgwfl;//专业技术岗位分类
    private String rjxk;//任教学科
    private String szjb;//实职级别
    private String gzgwf;//工资岗位副

    @ExcelField(title = "姓名", align = 2, sort = 1, groups = {1, 2},isnull=1)
    public String getName() {
        return name;
    }
    @ExcelField(title = "职工编号", align = 2, sort = 2, groups = {1, 2},isnull=1)
    public String getNo() {
        return no;
    }
    @ExcelField(title = "手机号码", align = 2, sort = 3, groups = {1, 2})
    public String getMobile() {
        return mobile;
    }
    @ExcelField(title = "邮箱", align = 2, sort = 4, groups = {1, 2})
    public String getMail() {
        return mail;
    }
    @ExcelField(title = "身份证号码", align = 2, sort = 5, groups = {1, 2})
    public String getIdentity() {
        return identity;
    }
    @ExcelField(title = "性别", align = 2, sort = 6, groups = {1, 2})
    public String getGender() {
        return gender;
    }
    @ExcelField(title = "部门", align = 2, sort = 7, groups = {1, 2})
    public String getBumen() {
        return bumen;
    }

    @ExcelField(title = "开始工作时间", align = 2, sort = 8, groups = {1, 2})
    public String getStartWork() {
        return startWork;
    }
    @ExcelField(title = "最高毕业时间", align = 2, sort = 9, groups = {1, 2})
    public String getHignTime() {
        return hignTime;
    }
    @ExcelField(title = "最高专业", align = 2, sort = 10, groups = {1, 2})
    public String getHignJob() {
        return hignJob;
    }
    @ExcelField(title = "评职详细", align = 2, sort = 11, groups = {1, 2},permission = 1)
    public String getPzxx() {
        return pzxx;
    }
    @ExcelField(title = "家庭住址详细", align = 2, sort = 12, groups = {1, 2})
    public String getAdrress() {
        return adrress;
    }
    @ExcelField(title = "骨干教师级别", align = 2, sort = 13, groups = {1, 2},permission = 1)
    public String getGgjsjb() {
        return ggjsjb;
    }
    @ExcelField(title = "合同开始时间", align = 2, sort = 14, groups = {1, 2})
    public String getHtkssj() {
        return htkssj;
    }
    @ExcelField(title = "曾用名", align = 2, sort = 15, groups = {1, 2})
    public String getCym() {
        return cym;
    }
    @ExcelField(title = "家庭邮编", align = 2, sort = 16, groups = {1, 2},permission = 1)
    public String getJtyb() {
        return jtyb;
    }
    @ExcelField(title = "是否专任教师", align = 2, sort = 17, groups = {1, 2},permission = 1)
    public String getSfzrjs() {
        return sfzrjs;
    }
    @ExcelField(title = "身份", align = 2, sort = 18, groups = {1, 2})
    public String getShenfen() {
        return shenfen;
    }
    @ExcelField(title = "外语水平", align = 2, sort = 19, groups = {1, 2})
    public String getWysp() {
        return wysp;
    }
    @ExcelField(title = "最高学制", align = 2, sort = 20, groups = {1, 2})
    public String getZgxz() {
        return zgxz;
    }
    @ExcelField(title = "学位数量", align = 2, sort = 21, groups = {1, 2})
    public String getXwsl() {
        return xwsl;
    }
    @ExcelField(title = "任教学科级别", align = 2, sort = 22, groups = {1, 2},permission = 1)
    public String getRjxkjb() {
        return rjxkjb;
    }
 /*   @ExcelField(title = "校区", align = 2, sort = 22, groups = {1, 2},permission = 1)
    public String getXq() {
        return xq;
    }*/
    @ExcelField(title = "薪资", align = 2, sort = 23, groups = {1, 2})
    public String getXj() {
        return xj;
    }
    @ExcelField(title = "岗位分类副", align = 2, sort = 24, groups = {1, 2},permission = 1)
    public String getGwflf() {
        return gwflf;
    }
    @ExcelField(title = "籍贯", align = 2, sort = 25, groups = {1, 2})
    public String getJg() {
        return jg;
    }
    @ExcelField(title = "政治面貌", align = 2, sort = 26, groups = {1, 2})
    public String getZzmm() {
        return zzmm;
    }
    @ExcelField(title = "原始毕业时间", align = 2, sort = 27, groups = {1, 2})
    public String getYsbysj() {
        return ysbysj;
    }
    @ExcelField(title = "最高学历", align = 2, sort = 28, groups = {1, 2})
    public String getZgxl() {
        return zgxl;
    }
    @ExcelField(title = "最高毕业学校", align = 2, sort = 29, groups = {1, 2})
    public String getZgbyxx() {
        return zgbyxx;
    }
    @ExcelField(title = "原专业", align = 2, sort = 30, groups = {1, 2})
    public String getYzy() {
        return yzy;
    }
    @ExcelField(title = "评职时间", align = 2, sort = 31, groups = {1, 2},permission = 1)
    public String getPzsj() {
        return pzsj;
    }
    @ExcelField(title = "来我校时间", align = 2, sort = 32, groups = {1, 2},permission = 1)
    public String getLwxsj() {
        return lwxsj;
    }
    @ExcelField(title = "住宅电话", align = 2, sort = 33, groups = {1, 2},permission = 1)
    public String getZzdh() {
        return zzdh;
    }
    @ExcelField(title = "工资岗位", align = 2, sort = 34, groups = {1, 2},permission = 1)
    public String getGzgw() {
        return gzgw;
    }
    @ExcelField(title = "合同结束时间", align = 2, sort = 35, groups = {1, 2})
    public String getHtjssj() {
        return htjssj;
    }
    @ExcelField(title = "办公室电话", align = 2, sort = 36, groups = {1, 2})
    public String getBgsdh() {
        return bgsdh;
    }
    @ExcelField(title = "是否华侨", align = 2, sort = 37, groups = {1, 2})
    public String getSfhq() {
        return sfhq;
    }
    @ExcelField(title = "是否班主任", align = 2, sort = 38, groups = {1, 2},permission = 1)
    public String getSfbzr() {
        return sfbzr;
    }
    @ExcelField(title = "外语语种", align = 2, sort = 39, groups = {1, 2})
    public String getWyyz() {
        return wyyz;
    }
    @ExcelField(title = "原学制", align = 2, sort = 40, groups = {1, 2})
    public String getYxz() {
        return yxz;
    }
    @ExcelField(title = "最高学位", align = 2, sort = 41, groups = {1, 2})
    public String getZgxw() {
        return zgxw;
    }
    @ExcelField(title = "专业技术岗位分类", align = 2, sort = 42, groups = {1, 2},permission = 1)
    public String getZyjsgwfl() {
        return zyjsgwfl;
    }
    @ExcelField(title = "任教学科", align = 2, sort = 43, groups = {1, 2},permission = 1)
    public String getRjxk() {
        return rjxk;
    }
    @ExcelField(title = "实职级别", align = 2, sort = 44, groups = {1, 2})
    public String getSzjb() {
        return szjb;
    }
    @ExcelField(title = "工资岗位副", align = 2, sort = 45, groups = {1, 2},permission = 1)
    public String getGzgwf() {
        return gzgwf;
    }

    public void setBumen(String bumen) {
        this.bumen = bumen;
    }
    public void setNo(String no) {
        this.no = no;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setStartWork(String startWork) {
        this.startWork = startWork;
    }

    public void setHignTime(String hignTime) {
        this.hignTime = hignTime;
    }

    public void setHignJob(String hignJob) {
        this.hignJob = hignJob;
    }

    public void setPzxx(String pzxx) {
        this.pzxx = pzxx;
    }

    public void setAdrress(String adrress) {
        this.adrress = adrress;
    }

    public void setGgjsjb(String ggjsjb) {
        this.ggjsjb = ggjsjb;
    }

    public void setHtkssj(String htkssj) {
        this.htkssj = htkssj;
    }

    public void setCym(String cym) {
        this.cym = cym;
    }

    public void setJtyb(String jtyb) {
        this.jtyb = jtyb;
    }

    public void setSfzrjs(String sfzrjs) {
        this.sfzrjs = sfzrjs;
    }

    public void setShenfen(String shenfen) {
        this.shenfen = shenfen;
    }

    public void setWysp(String wysp) {
        this.wysp = wysp;
    }

    public void setZgxz(String zgxz) {
        this.zgxz = zgxz;
    }

    public void setXwsl(String xwsl) {
        this.xwsl = xwsl;
    }

    public void setRjxkjb(String rjxkjb) {
        this.rjxkjb = rjxkjb;
    }

/*    public void setXq(String xq) {
        this.xq = xq;
    }*/

    public void setXj(String xj) {
        this.xj = xj;
    }

    public void setGwflf(String gwflf) {
        this.gwflf = gwflf;
    }

    public void setJg(String jg) {
        this.jg = jg;
    }

    public void setZzmm(String zzmm) {
        this.zzmm = zzmm;
    }

    public void setYsbysj(String ysbysj) {
        this.ysbysj = ysbysj;
    }

    public void setZgxl(String zgxl) {
        this.zgxl = zgxl;
    }

    public void setZgbyxx(String zgbyxx) {
        this.zgbyxx = zgbyxx;
    }

    public void setYzy(String yzy) {
        this.yzy = yzy;
    }

    public void setPzsj(String pzsj) {
        this.pzsj = pzsj;
    }

    public void setLwxsj(String lwxsj) {
        this.lwxsj = lwxsj;
    }

    public void setZzdh(String zzdh) {
        this.zzdh = zzdh;
    }

    public void setGzgw(String gzgw) {
        this.gzgw = gzgw;
    }

    public void setHtjssj(String htjssj) {
        this.htjssj = htjssj;
    }

    public void setBgsdh(String bgsdh) {
        this.bgsdh = bgsdh;
    }

    public void setSfhq(String sfhq) {
        this.sfhq = sfhq;
    }

    public void setSfbzr(String sfbzr) {
        this.sfbzr = sfbzr;
    }

    public void setWyyz(String wyyz) {
        this.wyyz = wyyz;
    }

    public void setYxz(String yxz) {
        this.yxz = yxz;
    }

    public void setZgxw(String zgxw) {
        this.zgxw = zgxw;
    }

    public void setZyjsgwfl(String zyjsgwfl) {
        this.zyjsgwfl = zyjsgwfl;
    }

    public void setRjxk(String rjxk) {
        this.rjxk = rjxk;
    }

    public void setSzjb(String szjb) {
        this.szjb = szjb;
    }

    public void setGzgwf(String gzgwf) {
        this.gzgwf = gzgwf;
    }
}
