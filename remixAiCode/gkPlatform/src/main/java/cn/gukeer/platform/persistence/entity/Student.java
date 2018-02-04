package cn.gukeer.platform.persistence.entity;

import cn.gukeer.sync.annotation.NoSync;
import cn.gukeer.sync.annotation.PrimaryKey;
import cn.gukeer.sync.annotation.TableSync;

import java.io.Serializable;
@TableSync(SyncTableName="user_student",TargetName = "sync_student")
public class Student implements Serializable {
    @PrimaryKey
    private String id;

    private String schoolId;

    private String classId;

    private String account;

    private String xsxm;
    @NoSync
    private String xmpy;

    private String xszp;

    private String phone;

    private Long csrq;

    private Long rxrq;

    private Integer xsxb;

    private String xssg;

    private String xd;

    private Integer nj;

    private String xh;

    private String xjh;

    private String qgxjh;

    private String jyid;

    private String syd;

    private Integer yxzjlx;

    private String yxzjh;

    private String jbs;

    private Integer sfsbt;

    private Integer sbtxh;

    private Integer xslb;

    private String gb;

    private String mz;

    private String jg;

    private Integer zzmm;

    private Integer zslb;

    private String lydq;

    private String hkszd;

    private String xjzd;

    private String hkxz;
    @NoSync
    private Integer sfbshk;

    private Integer status;

    private Integer delFlag;
    @NoSync
    private String remarks;
    @NoSync
    private String createBy;
    @NoSync
    private Long createDate;
    @NoSync
    private String updateBy;
    @NoSync
    private Long updateDate;
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

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getXsxm() {
        return xsxm;
    }

    public void setXsxm(String xsxm) {
        this.xsxm = xsxm == null ? null : xsxm.trim();
    }

    public String getXmpy() {
        return xmpy;
    }

    public void setXmpy(String xmpy) {
        this.xmpy = xmpy == null ? null : xmpy.trim();
    }

    public String getXszp() {
        return xszp;
    }

    public void setXszp(String xszp) {
        this.xszp = xszp == null ? null : xszp.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Long getCsrq() {
        return csrq;
    }

    public void setCsrq(Long csrq) {
        this.csrq = csrq;
    }

    public Long getRxrq() {
        return rxrq;
    }

    public void setRxrq(Long rxrq) {
        this.rxrq = rxrq;
    }

    public Integer getXsxb() {
        return xsxb;
    }

    public void setXsxb(Integer xsxb) {
        this.xsxb = xsxb;
    }

    public String getXssg() {
        return xssg;
    }

    public void setXssg(String xssg) {
        this.xssg = xssg == null ? null : xssg.trim();
    }

    public String getXd() {
        return xd;
    }

    public void setXd(String xd) {
        this.xd = xd == null ? null : xd.trim();
    }

    public Integer getNj() {
        return nj;
    }

    public void setNj(Integer nj) {
        this.nj = nj;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh == null ? null : xh.trim();
    }

    public String getXjh() {
        return xjh;
    }

    public void setXjh(String xjh) {
        this.xjh = xjh == null ? null : xjh.trim();
    }

    public String getQgxjh() {
        return qgxjh;
    }

    public void setQgxjh(String qgxjh) {
        this.qgxjh = qgxjh == null ? null : qgxjh.trim();
    }

    public String getJyid() {
        return jyid;
    }

    public void setJyid(String jyid) {
        this.jyid = jyid == null ? null : jyid.trim();
    }

    public String getSyd() {
        return syd;
    }

    public void setSyd(String syd) {
        this.syd = syd == null ? null : syd.trim();
    }

    public Integer getYxzjlx() {
        return yxzjlx;
    }

    public void setYxzjlx(Integer yxzjlx) {
        this.yxzjlx = yxzjlx;
    }

    public String getYxzjh() {
        return yxzjh;
    }

    public void setYxzjh(String yxzjh) {
        this.yxzjh = yxzjh == null ? null : yxzjh.trim();
    }

    public String getJbs() {
        return jbs;
    }

    public void setJbs(String jbs) {
        this.jbs = jbs == null ? null : jbs.trim();
    }

    public Integer getSfsbt() {
        return sfsbt;
    }

    public void setSfsbt(Integer sfsbt) {
        this.sfsbt = sfsbt;
    }

    public Integer getSbtxh() {
        return sbtxh;
    }

    public void setSbtxh(Integer sbtxh) {
        this.sbtxh = sbtxh;
    }

    public Integer getXslb() {
        return xslb;
    }

    public void setXslb(Integer xslb) {
        this.xslb = xslb;
    }

    public String getGb() {
        return gb;
    }

    public void setGb(String gb) {
        this.gb = gb == null ? null : gb.trim();
    }

    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz == null ? null : mz.trim();
    }

    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg == null ? null : jg.trim();
    }

    public Integer getZzmm() {
        return zzmm;
    }

    public void setZzmm(Integer zzmm) {
        this.zzmm = zzmm;
    }

    public Integer getZslb() {
        return zslb;
    }

    public void setZslb(Integer zslb) {
        this.zslb = zslb;
    }

    public String getLydq() {
        return lydq;
    }

    public void setLydq(String lydq) {
        this.lydq = lydq == null ? null : lydq.trim();
    }

    public String getHkszd() {
        return hkszd;
    }

    public void setHkszd(String hkszd) {
        this.hkszd = hkszd == null ? null : hkszd.trim();
    }

    public String getXjzd() {
        return xjzd;
    }

    public void setXjzd(String xjzd) {
        this.xjzd = xjzd == null ? null : xjzd.trim();
    }

    public String getHkxz() {
        return hkxz;
    }

    public void setHkxz(String hkxz) {
        this.hkxz = hkxz == null ? null : hkxz.trim();
    }

    public Integer getSfbshk() {
        return sfbshk;
    }

    public void setSfbshk(Integer sfbshk) {
        this.sfbshk = sfbshk;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
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
        Student other = (Student) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSchoolId() == null ? other.getSchoolId() == null : this.getSchoolId().equals(other.getSchoolId()))
            && (this.getClassId() == null ? other.getClassId() == null : this.getClassId().equals(other.getClassId()))
            && (this.getAccount() == null ? other.getAccount() == null : this.getAccount().equals(other.getAccount()))
            && (this.getXsxm() == null ? other.getXsxm() == null : this.getXsxm().equals(other.getXsxm()))
            && (this.getXmpy() == null ? other.getXmpy() == null : this.getXmpy().equals(other.getXmpy()))
            && (this.getXszp() == null ? other.getXszp() == null : this.getXszp().equals(other.getXszp()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getCsrq() == null ? other.getCsrq() == null : this.getCsrq().equals(other.getCsrq()))
            && (this.getRxrq() == null ? other.getRxrq() == null : this.getRxrq().equals(other.getRxrq()))
            && (this.getXsxb() == null ? other.getXsxb() == null : this.getXsxb().equals(other.getXsxb()))
            && (this.getXssg() == null ? other.getXssg() == null : this.getXssg().equals(other.getXssg()))
            && (this.getXd() == null ? other.getXd() == null : this.getXd().equals(other.getXd()))
            && (this.getNj() == null ? other.getNj() == null : this.getNj().equals(other.getNj()))
            && (this.getXh() == null ? other.getXh() == null : this.getXh().equals(other.getXh()))
            && (this.getXjh() == null ? other.getXjh() == null : this.getXjh().equals(other.getXjh()))
            && (this.getQgxjh() == null ? other.getQgxjh() == null : this.getQgxjh().equals(other.getQgxjh()))
            && (this.getJyid() == null ? other.getJyid() == null : this.getJyid().equals(other.getJyid()))
            && (this.getSyd() == null ? other.getSyd() == null : this.getSyd().equals(other.getSyd()))
            && (this.getYxzjlx() == null ? other.getYxzjlx() == null : this.getYxzjlx().equals(other.getYxzjlx()))
            && (this.getYxzjh() == null ? other.getYxzjh() == null : this.getYxzjh().equals(other.getYxzjh()))
            && (this.getJbs() == null ? other.getJbs() == null : this.getJbs().equals(other.getJbs()))
            && (this.getSfsbt() == null ? other.getSfsbt() == null : this.getSfsbt().equals(other.getSfsbt()))
            && (this.getSbtxh() == null ? other.getSbtxh() == null : this.getSbtxh().equals(other.getSbtxh()))
            && (this.getXslb() == null ? other.getXslb() == null : this.getXslb().equals(other.getXslb()))
            && (this.getGb() == null ? other.getGb() == null : this.getGb().equals(other.getGb()))
            && (this.getMz() == null ? other.getMz() == null : this.getMz().equals(other.getMz()))
            && (this.getJg() == null ? other.getJg() == null : this.getJg().equals(other.getJg()))
            && (this.getZzmm() == null ? other.getZzmm() == null : this.getZzmm().equals(other.getZzmm()))
            && (this.getZslb() == null ? other.getZslb() == null : this.getZslb().equals(other.getZslb()))
            && (this.getLydq() == null ? other.getLydq() == null : this.getLydq().equals(other.getLydq()))
            && (this.getHkszd() == null ? other.getHkszd() == null : this.getHkszd().equals(other.getHkszd()))
            && (this.getXjzd() == null ? other.getXjzd() == null : this.getXjzd().equals(other.getXjzd()))
            && (this.getHkxz() == null ? other.getHkxz() == null : this.getHkxz().equals(other.getHkxz()))
            && (this.getSfbshk() == null ? other.getSfbshk() == null : this.getSfbshk().equals(other.getSfbshk()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSchoolId() == null) ? 0 : getSchoolId().hashCode());
        result = prime * result + ((getClassId() == null) ? 0 : getClassId().hashCode());
        result = prime * result + ((getAccount() == null) ? 0 : getAccount().hashCode());
        result = prime * result + ((getXsxm() == null) ? 0 : getXsxm().hashCode());
        result = prime * result + ((getXmpy() == null) ? 0 : getXmpy().hashCode());
        result = prime * result + ((getXszp() == null) ? 0 : getXszp().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getCsrq() == null) ? 0 : getCsrq().hashCode());
        result = prime * result + ((getRxrq() == null) ? 0 : getRxrq().hashCode());
        result = prime * result + ((getXsxb() == null) ? 0 : getXsxb().hashCode());
        result = prime * result + ((getXssg() == null) ? 0 : getXssg().hashCode());
        result = prime * result + ((getXd() == null) ? 0 : getXd().hashCode());
        result = prime * result + ((getNj() == null) ? 0 : getNj().hashCode());
        result = prime * result + ((getXh() == null) ? 0 : getXh().hashCode());
        result = prime * result + ((getXjh() == null) ? 0 : getXjh().hashCode());
        result = prime * result + ((getQgxjh() == null) ? 0 : getQgxjh().hashCode());
        result = prime * result + ((getJyid() == null) ? 0 : getJyid().hashCode());
        result = prime * result + ((getSyd() == null) ? 0 : getSyd().hashCode());
        result = prime * result + ((getYxzjlx() == null) ? 0 : getYxzjlx().hashCode());
        result = prime * result + ((getYxzjh() == null) ? 0 : getYxzjh().hashCode());
        result = prime * result + ((getJbs() == null) ? 0 : getJbs().hashCode());
        result = prime * result + ((getSfsbt() == null) ? 0 : getSfsbt().hashCode());
        result = prime * result + ((getSbtxh() == null) ? 0 : getSbtxh().hashCode());
        result = prime * result + ((getXslb() == null) ? 0 : getXslb().hashCode());
        result = prime * result + ((getGb() == null) ? 0 : getGb().hashCode());
        result = prime * result + ((getMz() == null) ? 0 : getMz().hashCode());
        result = prime * result + ((getJg() == null) ? 0 : getJg().hashCode());
        result = prime * result + ((getZzmm() == null) ? 0 : getZzmm().hashCode());
        result = prime * result + ((getZslb() == null) ? 0 : getZslb().hashCode());
        result = prime * result + ((getLydq() == null) ? 0 : getLydq().hashCode());
        result = prime * result + ((getHkszd() == null) ? 0 : getHkszd().hashCode());
        result = prime * result + ((getXjzd() == null) ? 0 : getXjzd().hashCode());
        result = prime * result + ((getHkxz() == null) ? 0 : getHkxz().hashCode());
        result = prime * result + ((getSfbshk() == null) ? 0 : getSfbshk().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
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
        sb.append(", classId=").append(classId);
        sb.append(", account=").append(account);
        sb.append(", xsxm=").append(xsxm);
        sb.append(", xmpy=").append(xmpy);
        sb.append(", xszp=").append(xszp);
        sb.append(", phone=").append(phone);
        sb.append(", csrq=").append(csrq);
        sb.append(", rxrq=").append(rxrq);
        sb.append(", xsxb=").append(xsxb);
        sb.append(", xssg=").append(xssg);
        sb.append(", xd=").append(xd);
        sb.append(", nj=").append(nj);
        sb.append(", xh=").append(xh);
        sb.append(", xjh=").append(xjh);
        sb.append(", qgxjh=").append(qgxjh);
        sb.append(", jyid=").append(jyid);
        sb.append(", syd=").append(syd);
        sb.append(", yxzjlx=").append(yxzjlx);
        sb.append(", yxzjh=").append(yxzjh);
        sb.append(", jbs=").append(jbs);
        sb.append(", sfsbt=").append(sfsbt);
        sb.append(", sbtxh=").append(sbtxh);
        sb.append(", xslb=").append(xslb);
        sb.append(", gb=").append(gb);
        sb.append(", mz=").append(mz);
        sb.append(", jg=").append(jg);
        sb.append(", zzmm=").append(zzmm);
        sb.append(", zslb=").append(zslb);
        sb.append(", lydq=").append(lydq);
        sb.append(", hkszd=").append(hkszd);
        sb.append(", xjzd=").append(xjzd);
        sb.append(", hkxz=").append(hkxz);
        sb.append(", sfbshk=").append(sfbshk);
        sb.append(", status=").append(status);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", remarks=").append(remarks);
        sb.append(", createBy=").append(createBy);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}