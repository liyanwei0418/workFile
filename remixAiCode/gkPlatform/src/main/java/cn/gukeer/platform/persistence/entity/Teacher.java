package cn.gukeer.platform.persistence.entity;

import cn.gukeer.sync.annotation.NoSync;
import cn.gukeer.sync.annotation.PrimaryKey;
import cn.gukeer.sync.annotation.TableSync;

import java.io.Serializable;

@TableSync(SyncTableName = "user_teacher",TargetName = "sync_teacher")
public class Teacher implements Serializable {
    @PrimaryKey
    private String id;

    private String schoolId;

    private String departmentId;

    private String name;
    @NoSync
    private String quanPin;

    private Integer gender;

    private String identity;

    private String account;

    private Integer isManage;
    @NoSync
    private String roleId;

    private String titleId;

    private String no;

    private String phone;

    private String mobile;

    private String email;

    private String startWork;

    private String headUrl;
    @NoSync
    private String createBy;
    @NoSync
    private Long createDate;
    @NoSync
    private String updateBy;
    @NoSync
    private Long updateDate;
    @NoSync
    private String remarks;

    private String highTime;
    @NoSync
    private String highJob;

    private String zc;
    @NoSync
    private String pzxx;
    @NoSync
    private String address;
    @NoSync
    private String ggjsjb;
    @NoSync
    private String htkssj;
    @NoSync
    private String htjssj;
    @NoSync
    private String cym;
    @NoSync
    private String jtyb;

    private String sfzrjs;
    @NoSync
    private String salary;

    private String jg;

    private String zzmm;
    @NoSync
    private String cjgzsj;
    @NoSync
    private String ysbysj;

    private String rjxk;
    @NoSync
    private String sf;
    @NoSync
    private String wysp;
    @NoSync
    private String zgxz;
    @NoSync
    private String xwsl;
    @NoSync
    private String rjxkjb;

    private String xq;
    @NoSync
    private String gwflf;

    private String zgxl;
    @NoSync
    private String zgbyxx;
    @NoSync
    private String yzy;
    @NoSync
    private String pzsj;

    private String lwxsj;
    @NoSync
    private String zzdh;
    @NoSync
    private String gzgw;
    @NoSync
    private String bgsdh;

    private String sfhq;

    private String sfbzr;

    private String wyyz;
    @NoSync
    private String yxz;
    @NoSync
    private String zgxw;

    private String zyjsgwfl;
    @NoSync
    private String szjb;
    @NoSync
    private String gzgwf;

    private Integer delFlag;
    @NoSync
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId == null ? null : schoolId.trim();
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId == null ? null : departmentId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getQuanPin() {
        return quanPin;
    }

    public void setQuanPin(String quanPin) {
        this.quanPin = quanPin == null ? null : quanPin.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity == null ? null : identity.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public Integer getIsManage() {
        return isManage;
    }

    public void setIsManage(Integer isManage) {
        this.isManage = isManage;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId == null ? null : titleId.trim();
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getStartWork() {
        return startWork;
    }

    public void setStartWork(String startWork) {
        this.startWork = startWork == null ? null : startWork.trim();
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl == null ? null : headUrl.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getHighTime() {
        return highTime;
    }

    public void setHighTime(String highTime) {
        this.highTime = highTime == null ? null : highTime.trim();
    }

    public String getHighJob() {
        return highJob;
    }

    public void setHighJob(String highJob) {
        this.highJob = highJob == null ? null : highJob.trim();
    }

    public String getZc() {
        return zc;
    }

    public void setZc(String zc) {
        this.zc = zc == null ? null : zc.trim();
    }

    public String getPzxx() {
        return pzxx;
    }

    public void setPzxx(String pzxx) {
        this.pzxx = pzxx == null ? null : pzxx.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getGgjsjb() {
        return ggjsjb;
    }

    public void setGgjsjb(String ggjsjb) {
        this.ggjsjb = ggjsjb == null ? null : ggjsjb.trim();
    }

    public String getHtkssj() {
        return htkssj;
    }

    public void setHtkssj(String htkssj) {
        this.htkssj = htkssj == null ? null : htkssj.trim();
    }

    public String getHtjssj() {
        return htjssj;
    }

    public void setHtjssj(String htjssj) {
        this.htjssj = htjssj == null ? null : htjssj.trim();
    }

    public String getCym() {
        return cym;
    }

    public void setCym(String cym) {
        this.cym = cym == null ? null : cym.trim();
    }

    public String getJtyb() {
        return jtyb;
    }

    public void setJtyb(String jtyb) {
        this.jtyb = jtyb == null ? null : jtyb.trim();
    }

    public String getSfzrjs() {
        return sfzrjs;
    }

    public void setSfzrjs(String sfzrjs) {
        this.sfzrjs = sfzrjs == null ? null : sfzrjs.trim();
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary == null ? null : salary.trim();
    }

    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg == null ? null : jg.trim();
    }

    public String getZzmm() {
        return zzmm;
    }

    public void setZzmm(String zzmm) {
        this.zzmm = zzmm == null ? null : zzmm.trim();
    }

    public String getCjgzsj() {
        return cjgzsj;
    }

    public void setCjgzsj(String cjgzsj) {
        this.cjgzsj = cjgzsj == null ? null : cjgzsj.trim();
    }

    public String getYsbysj() {
        return ysbysj;
    }

    public void setYsbysj(String ysbysj) {
        this.ysbysj = ysbysj == null ? null : ysbysj.trim();
    }

    public String getRjxk() {
        return rjxk;
    }

    public void setRjxk(String rjxk) {
        this.rjxk = rjxk == null ? null : rjxk.trim();
    }

    public String getSf() {
        return sf;
    }

    public void setSf(String sf) {
        this.sf = sf == null ? null : sf.trim();
    }

    public String getWysp() {
        return wysp;
    }

    public void setWysp(String wysp) {
        this.wysp = wysp == null ? null : wysp.trim();
    }

    public String getZgxz() {
        return zgxz;
    }

    public void setZgxz(String zgxz) {
        this.zgxz = zgxz == null ? null : zgxz.trim();
    }

    public String getXwsl() {
        return xwsl;
    }

    public void setXwsl(String xwsl) {
        this.xwsl = xwsl == null ? null : xwsl.trim();
    }

    public String getRjxkjb() {
        return rjxkjb;
    }

    public void setRjxkjb(String rjxkjb) {
        this.rjxkjb = rjxkjb == null ? null : rjxkjb.trim();
    }

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq == null ? null : xq.trim();
    }

    public String getGwflf() {
        return gwflf;
    }

    public void setGwflf(String gwflf) {
        this.gwflf = gwflf == null ? null : gwflf.trim();
    }

    public String getZgxl() {
        return zgxl;
    }

    public void setZgxl(String zgxl) {
        this.zgxl = zgxl == null ? null : zgxl.trim();
    }

    public String getZgbyxx() {
        return zgbyxx;
    }

    public void setZgbyxx(String zgbyxx) {
        this.zgbyxx = zgbyxx == null ? null : zgbyxx.trim();
    }

    public String getYzy() {
        return yzy;
    }

    public void setYzy(String yzy) {
        this.yzy = yzy == null ? null : yzy.trim();
    }

    public String getPzsj() {
        return pzsj;
    }

    public void setPzsj(String pzsj) {
        this.pzsj = pzsj == null ? null : pzsj.trim();
    }

    public String getLwxsj() {
        return lwxsj;
    }

    public void setLwxsj(String lwxsj) {
        this.lwxsj = lwxsj == null ? null : lwxsj.trim();
    }

    public String getZzdh() {
        return zzdh;
    }

    public void setZzdh(String zzdh) {
        this.zzdh = zzdh == null ? null : zzdh.trim();
    }

    public String getGzgw() {
        return gzgw;
    }

    public void setGzgw(String gzgw) {
        this.gzgw = gzgw == null ? null : gzgw.trim();
    }

    public String getBgsdh() {
        return bgsdh;
    }

    public void setBgsdh(String bgsdh) {
        this.bgsdh = bgsdh == null ? null : bgsdh.trim();
    }

    public String getSfhq() {
        return sfhq;
    }

    public void setSfhq(String sfhq) {
        this.sfhq = sfhq == null ? null : sfhq.trim();
    }

    public String getSfbzr() {
        return sfbzr;
    }

    public void setSfbzr(String sfbzr) {
        this.sfbzr = sfbzr == null ? null : sfbzr.trim();
    }

    public String getWyyz() {
        return wyyz;
    }

    public void setWyyz(String wyyz) {
        this.wyyz = wyyz == null ? null : wyyz.trim();
    }

    public String getYxz() {
        return yxz;
    }

    public void setYxz(String yxz) {
        this.yxz = yxz == null ? null : yxz.trim();
    }

    public String getZgxw() {
        return zgxw;
    }

    public void setZgxw(String zgxw) {
        this.zgxw = zgxw == null ? null : zgxw.trim();
    }

    public String getZyjsgwfl() {
        return zyjsgwfl;
    }

    public void setZyjsgwfl(String zyjsgwfl) {
        this.zyjsgwfl = zyjsgwfl == null ? null : zyjsgwfl.trim();
    }

    public String getSzjb() {
        return szjb;
    }

    public void setSzjb(String szjb) {
        this.szjb = szjb == null ? null : szjb.trim();
    }

    public String getGzgwf() {
        return gzgwf;
    }

    public void setGzgwf(String gzgwf) {
        this.gzgwf = gzgwf == null ? null : gzgwf.trim();
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Teacher other = (Teacher) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSchoolId() == null ? other.getSchoolId() == null : this.getSchoolId().equals(other.getSchoolId()))
            && (this.getDepartmentId() == null ? other.getDepartmentId() == null : this.getDepartmentId().equals(other.getDepartmentId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getQuanPin() == null ? other.getQuanPin() == null : this.getQuanPin().equals(other.getQuanPin()))
            && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
            && (this.getIdentity() == null ? other.getIdentity() == null : this.getIdentity().equals(other.getIdentity()))
            && (this.getAccount() == null ? other.getAccount() == null : this.getAccount().equals(other.getAccount()))
            && (this.getIsManage() == null ? other.getIsManage() == null : this.getIsManage().equals(other.getIsManage()))
            && (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
            && (this.getTitleId() == null ? other.getTitleId() == null : this.getTitleId().equals(other.getTitleId()))
            && (this.getNo() == null ? other.getNo() == null : this.getNo().equals(other.getNo()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getStartWork() == null ? other.getStartWork() == null : this.getStartWork().equals(other.getStartWork()))
            && (this.getHeadUrl() == null ? other.getHeadUrl() == null : this.getHeadUrl().equals(other.getHeadUrl()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()))
            && (this.getHighTime() == null ? other.getHighTime() == null : this.getHighTime().equals(other.getHighTime()))
            && (this.getHighJob() == null ? other.getHighJob() == null : this.getHighJob().equals(other.getHighJob()))
            && (this.getZc() == null ? other.getZc() == null : this.getZc().equals(other.getZc()))
            && (this.getPzxx() == null ? other.getPzxx() == null : this.getPzxx().equals(other.getPzxx()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getGgjsjb() == null ? other.getGgjsjb() == null : this.getGgjsjb().equals(other.getGgjsjb()))
            && (this.getHtkssj() == null ? other.getHtkssj() == null : this.getHtkssj().equals(other.getHtkssj()))
            && (this.getHtjssj() == null ? other.getHtjssj() == null : this.getHtjssj().equals(other.getHtjssj()))
            && (this.getCym() == null ? other.getCym() == null : this.getCym().equals(other.getCym()))
            && (this.getJtyb() == null ? other.getJtyb() == null : this.getJtyb().equals(other.getJtyb()))
            && (this.getSfzrjs() == null ? other.getSfzrjs() == null : this.getSfzrjs().equals(other.getSfzrjs()))
            && (this.getSalary() == null ? other.getSalary() == null : this.getSalary().equals(other.getSalary()))
            && (this.getJg() == null ? other.getJg() == null : this.getJg().equals(other.getJg()))
            && (this.getZzmm() == null ? other.getZzmm() == null : this.getZzmm().equals(other.getZzmm()))
            && (this.getCjgzsj() == null ? other.getCjgzsj() == null : this.getCjgzsj().equals(other.getCjgzsj()))
            && (this.getYsbysj() == null ? other.getYsbysj() == null : this.getYsbysj().equals(other.getYsbysj()))
            && (this.getRjxk() == null ? other.getRjxk() == null : this.getRjxk().equals(other.getRjxk()))
            && (this.getSf() == null ? other.getSf() == null : this.getSf().equals(other.getSf()))
            && (this.getWysp() == null ? other.getWysp() == null : this.getWysp().equals(other.getWysp()))
            && (this.getZgxz() == null ? other.getZgxz() == null : this.getZgxz().equals(other.getZgxz()))
            && (this.getXwsl() == null ? other.getXwsl() == null : this.getXwsl().equals(other.getXwsl()))
            && (this.getRjxkjb() == null ? other.getRjxkjb() == null : this.getRjxkjb().equals(other.getRjxkjb()))
            && (this.getXq() == null ? other.getXq() == null : this.getXq().equals(other.getXq()))
            && (this.getGwflf() == null ? other.getGwflf() == null : this.getGwflf().equals(other.getGwflf()))
            && (this.getZgxl() == null ? other.getZgxl() == null : this.getZgxl().equals(other.getZgxl()))
            && (this.getZgbyxx() == null ? other.getZgbyxx() == null : this.getZgbyxx().equals(other.getZgbyxx()))
            && (this.getYzy() == null ? other.getYzy() == null : this.getYzy().equals(other.getYzy()))
            && (this.getPzsj() == null ? other.getPzsj() == null : this.getPzsj().equals(other.getPzsj()))
            && (this.getLwxsj() == null ? other.getLwxsj() == null : this.getLwxsj().equals(other.getLwxsj()))
            && (this.getZzdh() == null ? other.getZzdh() == null : this.getZzdh().equals(other.getZzdh()))
            && (this.getGzgw() == null ? other.getGzgw() == null : this.getGzgw().equals(other.getGzgw()))
            && (this.getBgsdh() == null ? other.getBgsdh() == null : this.getBgsdh().equals(other.getBgsdh()))
            && (this.getSfhq() == null ? other.getSfhq() == null : this.getSfhq().equals(other.getSfhq()))
            && (this.getSfbzr() == null ? other.getSfbzr() == null : this.getSfbzr().equals(other.getSfbzr()))
            && (this.getWyyz() == null ? other.getWyyz() == null : this.getWyyz().equals(other.getWyyz()))
            && (this.getYxz() == null ? other.getYxz() == null : this.getYxz().equals(other.getYxz()))
            && (this.getZgxw() == null ? other.getZgxw() == null : this.getZgxw().equals(other.getZgxw()))
            && (this.getZyjsgwfl() == null ? other.getZyjsgwfl() == null : this.getZyjsgwfl().equals(other.getZyjsgwfl()))
            && (this.getSzjb() == null ? other.getSzjb() == null : this.getSzjb().equals(other.getSzjb()))
            && (this.getGzgwf() == null ? other.getGzgwf() == null : this.getGzgwf().equals(other.getGzgwf()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSchoolId() == null) ? 0 : getSchoolId().hashCode());
        result = prime * result + ((getDepartmentId() == null) ? 0 : getDepartmentId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getQuanPin() == null) ? 0 : getQuanPin().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result + ((getIdentity() == null) ? 0 : getIdentity().hashCode());
        result = prime * result + ((getAccount() == null) ? 0 : getAccount().hashCode());
        result = prime * result + ((getIsManage() == null) ? 0 : getIsManage().hashCode());
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        result = prime * result + ((getTitleId() == null) ? 0 : getTitleId().hashCode());
        result = prime * result + ((getNo() == null) ? 0 : getNo().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getStartWork() == null) ? 0 : getStartWork().hashCode());
        result = prime * result + ((getHeadUrl() == null) ? 0 : getHeadUrl().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
        result = prime * result + ((getHighTime() == null) ? 0 : getHighTime().hashCode());
        result = prime * result + ((getHighJob() == null) ? 0 : getHighJob().hashCode());
        result = prime * result + ((getZc() == null) ? 0 : getZc().hashCode());
        result = prime * result + ((getPzxx() == null) ? 0 : getPzxx().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getGgjsjb() == null) ? 0 : getGgjsjb().hashCode());
        result = prime * result + ((getHtkssj() == null) ? 0 : getHtkssj().hashCode());
        result = prime * result + ((getHtjssj() == null) ? 0 : getHtjssj().hashCode());
        result = prime * result + ((getCym() == null) ? 0 : getCym().hashCode());
        result = prime * result + ((getJtyb() == null) ? 0 : getJtyb().hashCode());
        result = prime * result + ((getSfzrjs() == null) ? 0 : getSfzrjs().hashCode());
        result = prime * result + ((getSalary() == null) ? 0 : getSalary().hashCode());
        result = prime * result + ((getJg() == null) ? 0 : getJg().hashCode());
        result = prime * result + ((getZzmm() == null) ? 0 : getZzmm().hashCode());
        result = prime * result + ((getCjgzsj() == null) ? 0 : getCjgzsj().hashCode());
        result = prime * result + ((getYsbysj() == null) ? 0 : getYsbysj().hashCode());
        result = prime * result + ((getRjxk() == null) ? 0 : getRjxk().hashCode());
        result = prime * result + ((getSf() == null) ? 0 : getSf().hashCode());
        result = prime * result + ((getWysp() == null) ? 0 : getWysp().hashCode());
        result = prime * result + ((getZgxz() == null) ? 0 : getZgxz().hashCode());
        result = prime * result + ((getXwsl() == null) ? 0 : getXwsl().hashCode());
        result = prime * result + ((getRjxkjb() == null) ? 0 : getRjxkjb().hashCode());
        result = prime * result + ((getXq() == null) ? 0 : getXq().hashCode());
        result = prime * result + ((getGwflf() == null) ? 0 : getGwflf().hashCode());
        result = prime * result + ((getZgxl() == null) ? 0 : getZgxl().hashCode());
        result = prime * result + ((getZgbyxx() == null) ? 0 : getZgbyxx().hashCode());
        result = prime * result + ((getYzy() == null) ? 0 : getYzy().hashCode());
        result = prime * result + ((getPzsj() == null) ? 0 : getPzsj().hashCode());
        result = prime * result + ((getLwxsj() == null) ? 0 : getLwxsj().hashCode());
        result = prime * result + ((getZzdh() == null) ? 0 : getZzdh().hashCode());
        result = prime * result + ((getGzgw() == null) ? 0 : getGzgw().hashCode());
        result = prime * result + ((getBgsdh() == null) ? 0 : getBgsdh().hashCode());
        result = prime * result + ((getSfhq() == null) ? 0 : getSfhq().hashCode());
        result = prime * result + ((getSfbzr() == null) ? 0 : getSfbzr().hashCode());
        result = prime * result + ((getWyyz() == null) ? 0 : getWyyz().hashCode());
        result = prime * result + ((getYxz() == null) ? 0 : getYxz().hashCode());
        result = prime * result + ((getZgxw() == null) ? 0 : getZgxw().hashCode());
        result = prime * result + ((getZyjsgwfl() == null) ? 0 : getZyjsgwfl().hashCode());
        result = prime * result + ((getSzjb() == null) ? 0 : getSzjb().hashCode());
        result = prime * result + ((getGzgwf() == null) ? 0 : getGzgwf().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", departmentId=").append(departmentId);
        sb.append(", name=").append(name);
        sb.append(", quanPin=").append(quanPin);
        sb.append(", gender=").append(gender);
        sb.append(", identity=").append(identity);
        sb.append(", account=").append(account);
        sb.append(", isManage=").append(isManage);
        sb.append(", roleId=").append(roleId);
        sb.append(", titleId=").append(titleId);
        sb.append(", no=").append(no);
        sb.append(", phone=").append(phone);
        sb.append(", mobile=").append(mobile);
        sb.append(", email=").append(email);
        sb.append(", startWork=").append(startWork);
        sb.append(", headUrl=").append(headUrl);
        sb.append(", createBy=").append(createBy);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", remarks=").append(remarks);
        sb.append(", highTime=").append(highTime);
        sb.append(", highJob=").append(highJob);
        sb.append(", zc=").append(zc);
        sb.append(", pzxx=").append(pzxx);
        sb.append(", address=").append(address);
        sb.append(", ggjsjb=").append(ggjsjb);
        sb.append(", htkssj=").append(htkssj);
        sb.append(", htjssj=").append(htjssj);
        sb.append(", cym=").append(cym);
        sb.append(", jtyb=").append(jtyb);
        sb.append(", sfzrjs=").append(sfzrjs);
        sb.append(", salary=").append(salary);
        sb.append(", jg=").append(jg);
        sb.append(", zzmm=").append(zzmm);
        sb.append(", cjgzsj=").append(cjgzsj);
        sb.append(", ysbysj=").append(ysbysj);
        sb.append(", rjxk=").append(rjxk);
        sb.append(", sf=").append(sf);
        sb.append(", wysp=").append(wysp);
        sb.append(", zgxz=").append(zgxz);
        sb.append(", xwsl=").append(xwsl);
        sb.append(", rjxkjb=").append(rjxkjb);
        sb.append(", xq=").append(xq);
        sb.append(", gwflf=").append(gwflf);
        sb.append(", zgxl=").append(zgxl);
        sb.append(", zgbyxx=").append(zgbyxx);
        sb.append(", yzy=").append(yzy);
        sb.append(", pzsj=").append(pzsj);
        sb.append(", lwxsj=").append(lwxsj);
        sb.append(", zzdh=").append(zzdh);
        sb.append(", gzgw=").append(gzgw);
        sb.append(", bgsdh=").append(bgsdh);
        sb.append(", sfhq=").append(sfhq);
        sb.append(", sfbzr=").append(sfbzr);
        sb.append(", wyyz=").append(wyyz);
        sb.append(", yxz=").append(yxz);
        sb.append(", zgxw=").append(zgxw);
        sb.append(", zyjsgwfl=").append(zyjsgwfl);
        sb.append(", szjb=").append(szjb);
        sb.append(", gzgwf=").append(gzgwf);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}