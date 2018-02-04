package cn.gukeer.platform.persistence.entity;

import java.io.Serializable;
import java.util.Date;

public class SyncTeacher implements Serializable {
    private Integer id;

    private String syncId;

    private String schoolId;

    private String departmentId;

    private String name;

    private Integer gender;

    private String identity;

    private String account;

    private Integer isManage;

    private String titleId;

    private String no;

    private String phone;

    private String mobile;

    private String email;

    private Long startWork;

    private String headUrl;

    private Long highTime;

    private String zc;

    private String sfzrjs;

    private String jg;

    private String zzmm;

    private String rjxk;

    private String xq;

    private String zgxl;

    private Long lwxsj;

    private String sfhq;

    private String sfbzr;

    private String wyyz;

    private String zyjsgwfl;

    private Integer delFlag;

    private String event;

    private String source;

    private Date syncDate;

    private String syncDelFlag;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSyncId() {
        return syncId;
    }

    public void setSyncId(String syncId) {
        this.syncId = syncId == null ? null : syncId.trim();
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

    public Long getStartWork() {
        return startWork;
    }

    public void setStartWork(Long startWork) {
        this.startWork = startWork;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl == null ? null : headUrl.trim();
    }

    public Long getHighTime() {
        return highTime;
    }

    public void setHighTime(Long highTime) {
        this.highTime = highTime;
    }

    public String getZc() {
        return zc;
    }

    public void setZc(String zc) {
        this.zc = zc == null ? null : zc.trim();
    }

    public String getSfzrjs() {
        return sfzrjs;
    }

    public void setSfzrjs(String sfzrjs) {
        this.sfzrjs = sfzrjs == null ? null : sfzrjs.trim();
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

    public String getRjxk() {
        return rjxk;
    }

    public void setRjxk(String rjxk) {
        this.rjxk = rjxk == null ? null : rjxk.trim();
    }

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq == null ? null : xq.trim();
    }

    public String getZgxl() {
        return zgxl;
    }

    public void setZgxl(String zgxl) {
        this.zgxl = zgxl == null ? null : zgxl.trim();
    }

    public Long getLwxsj() {
        return lwxsj;
    }

    public void setLwxsj(Long lwxsj) {
        this.lwxsj = lwxsj;
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

    public String getZyjsgwfl() {
        return zyjsgwfl;
    }

    public void setZyjsgwfl(String zyjsgwfl) {
        this.zyjsgwfl = zyjsgwfl == null ? null : zyjsgwfl.trim();
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event == null ? null : event.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public Date getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(Date syncDate) {
        this.syncDate = syncDate;
    }

    public String getSyncDelFlag() {
        return syncDelFlag;
    }

    public void setSyncDelFlag(String syncDelFlag) {
        this.syncDelFlag = syncDelFlag == null ? null : syncDelFlag.trim();
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
        SyncTeacher other = (SyncTeacher) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSyncId() == null ? other.getSyncId() == null : this.getSyncId().equals(other.getSyncId()))
            && (this.getSchoolId() == null ? other.getSchoolId() == null : this.getSchoolId().equals(other.getSchoolId()))
            && (this.getDepartmentId() == null ? other.getDepartmentId() == null : this.getDepartmentId().equals(other.getDepartmentId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
            && (this.getIdentity() == null ? other.getIdentity() == null : this.getIdentity().equals(other.getIdentity()))
            && (this.getAccount() == null ? other.getAccount() == null : this.getAccount().equals(other.getAccount()))
            && (this.getIsManage() == null ? other.getIsManage() == null : this.getIsManage().equals(other.getIsManage()))
            && (this.getTitleId() == null ? other.getTitleId() == null : this.getTitleId().equals(other.getTitleId()))
            && (this.getNo() == null ? other.getNo() == null : this.getNo().equals(other.getNo()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getStartWork() == null ? other.getStartWork() == null : this.getStartWork().equals(other.getStartWork()))
            && (this.getHeadUrl() == null ? other.getHeadUrl() == null : this.getHeadUrl().equals(other.getHeadUrl()))
            && (this.getHighTime() == null ? other.getHighTime() == null : this.getHighTime().equals(other.getHighTime()))
            && (this.getZc() == null ? other.getZc() == null : this.getZc().equals(other.getZc()))
            && (this.getSfzrjs() == null ? other.getSfzrjs() == null : this.getSfzrjs().equals(other.getSfzrjs()))
            && (this.getJg() == null ? other.getJg() == null : this.getJg().equals(other.getJg()))
            && (this.getZzmm() == null ? other.getZzmm() == null : this.getZzmm().equals(other.getZzmm()))
            && (this.getRjxk() == null ? other.getRjxk() == null : this.getRjxk().equals(other.getRjxk()))
            && (this.getXq() == null ? other.getXq() == null : this.getXq().equals(other.getXq()))
            && (this.getZgxl() == null ? other.getZgxl() == null : this.getZgxl().equals(other.getZgxl()))
            && (this.getLwxsj() == null ? other.getLwxsj() == null : this.getLwxsj().equals(other.getLwxsj()))
            && (this.getSfhq() == null ? other.getSfhq() == null : this.getSfhq().equals(other.getSfhq()))
            && (this.getSfbzr() == null ? other.getSfbzr() == null : this.getSfbzr().equals(other.getSfbzr()))
            && (this.getWyyz() == null ? other.getWyyz() == null : this.getWyyz().equals(other.getWyyz()))
            && (this.getZyjsgwfl() == null ? other.getZyjsgwfl() == null : this.getZyjsgwfl().equals(other.getZyjsgwfl()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getEvent() == null ? other.getEvent() == null : this.getEvent().equals(other.getEvent()))
            && (this.getSource() == null ? other.getSource() == null : this.getSource().equals(other.getSource()))
            && (this.getSyncDate() == null ? other.getSyncDate() == null : this.getSyncDate().equals(other.getSyncDate()))
            && (this.getSyncDelFlag() == null ? other.getSyncDelFlag() == null : this.getSyncDelFlag().equals(other.getSyncDelFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSyncId() == null) ? 0 : getSyncId().hashCode());
        result = prime * result + ((getSchoolId() == null) ? 0 : getSchoolId().hashCode());
        result = prime * result + ((getDepartmentId() == null) ? 0 : getDepartmentId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result + ((getIdentity() == null) ? 0 : getIdentity().hashCode());
        result = prime * result + ((getAccount() == null) ? 0 : getAccount().hashCode());
        result = prime * result + ((getIsManage() == null) ? 0 : getIsManage().hashCode());
        result = prime * result + ((getTitleId() == null) ? 0 : getTitleId().hashCode());
        result = prime * result + ((getNo() == null) ? 0 : getNo().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getStartWork() == null) ? 0 : getStartWork().hashCode());
        result = prime * result + ((getHeadUrl() == null) ? 0 : getHeadUrl().hashCode());
        result = prime * result + ((getHighTime() == null) ? 0 : getHighTime().hashCode());
        result = prime * result + ((getZc() == null) ? 0 : getZc().hashCode());
        result = prime * result + ((getSfzrjs() == null) ? 0 : getSfzrjs().hashCode());
        result = prime * result + ((getJg() == null) ? 0 : getJg().hashCode());
        result = prime * result + ((getZzmm() == null) ? 0 : getZzmm().hashCode());
        result = prime * result + ((getRjxk() == null) ? 0 : getRjxk().hashCode());
        result = prime * result + ((getXq() == null) ? 0 : getXq().hashCode());
        result = prime * result + ((getZgxl() == null) ? 0 : getZgxl().hashCode());
        result = prime * result + ((getLwxsj() == null) ? 0 : getLwxsj().hashCode());
        result = prime * result + ((getSfhq() == null) ? 0 : getSfhq().hashCode());
        result = prime * result + ((getSfbzr() == null) ? 0 : getSfbzr().hashCode());
        result = prime * result + ((getWyyz() == null) ? 0 : getWyyz().hashCode());
        result = prime * result + ((getZyjsgwfl() == null) ? 0 : getZyjsgwfl().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getEvent() == null) ? 0 : getEvent().hashCode());
        result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
        result = prime * result + ((getSyncDate() == null) ? 0 : getSyncDate().hashCode());
        result = prime * result + ((getSyncDelFlag() == null) ? 0 : getSyncDelFlag().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", syncId=").append(syncId);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", departmentId=").append(departmentId);
        sb.append(", name=").append(name);
        sb.append(", gender=").append(gender);
        sb.append(", identity=").append(identity);
        sb.append(", account=").append(account);
        sb.append(", isManage=").append(isManage);
        sb.append(", titleId=").append(titleId);
        sb.append(", no=").append(no);
        sb.append(", phone=").append(phone);
        sb.append(", mobile=").append(mobile);
        sb.append(", email=").append(email);
        sb.append(", startWork=").append(startWork);
        sb.append(", headUrl=").append(headUrl);
        sb.append(", highTime=").append(highTime);
        sb.append(", zc=").append(zc);
        sb.append(", sfzrjs=").append(sfzrjs);
        sb.append(", jg=").append(jg);
        sb.append(", zzmm=").append(zzmm);
        sb.append(", rjxk=").append(rjxk);
        sb.append(", xq=").append(xq);
        sb.append(", zgxl=").append(zgxl);
        sb.append(", lwxsj=").append(lwxsj);
        sb.append(", sfhq=").append(sfhq);
        sb.append(", sfbzr=").append(sfbzr);
        sb.append(", wyyz=").append(wyyz);
        sb.append(", zyjsgwfl=").append(zyjsgwfl);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", event=").append(event);
        sb.append(", source=").append(source);
        sb.append(", syncDate=").append(syncDate);
        sb.append(", syncDelFlag=").append(syncDelFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}