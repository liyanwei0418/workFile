package cn.gukeer.platform.persistence.entity;

import cn.gukeer.sync.annotation.NoSync;
import cn.gukeer.sync.annotation.PrimaryKey;
import cn.gukeer.sync.annotation.TableSync;

import java.io.Serializable;

@TableSync(SyncTableName="org_school",TargetName = "sync_org_school")
public class School implements Serializable {
    @PrimaryKey
    private String id;

    private String parentId;

    private String name;

    private String ename;
    @NoSync
    private Integer sort;

    private String xz;
    @NoSync
    private String code;

    private Integer type;

    private Integer grade;

    private String logo;

    private String bgPicture;
    @NoSync
    private String homeText;
    @NoSync
    private String bottomText;

    private String address;
    @NoSync
    private String mId;
    @NoSync
    private String master;
    @NoSync
    private String zipCode;

    private String phone;
    @NoSync
    private String fax;
    @NoSync
    private String email;
    @NoSync
    private Integer patriarchRule;
    @NoSync
    private Integer studentRule;
    @NoSync
    private Integer teacherRule;

    private String shortFlag;
    @NoSync
    private String deployUrl;
    @NoSync
    private String userable;
    @NoSync
    private String primaryPersion;
    @NoSync
    private String deputyPersion;
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

    private Integer delFlag;
    @NoSync
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename == null ? null : ename.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getXz() {
        return xz;
    }

    public void setXz(String xz) {
        this.xz = xz == null ? null : xz.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getBgPicture() {
        return bgPicture;
    }

    public void setBgPicture(String bgPicture) {
        this.bgPicture = bgPicture == null ? null : bgPicture.trim();
    }

    public String getHomeText() {
        return homeText;
    }

    public void setHomeText(String homeText) {
        this.homeText = homeText == null ? null : homeText.trim();
    }

    public String getBottomText() {
        return bottomText;
    }

    public void setBottomText(String bottomText) {
        this.bottomText = bottomText == null ? null : bottomText.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId == null ? null : mId.trim();
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master == null ? null : master.trim();
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode == null ? null : zipCode.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getPatriarchRule() {
        return patriarchRule;
    }

    public void setPatriarchRule(Integer patriarchRule) {
        this.patriarchRule = patriarchRule;
    }

    public Integer getStudentRule() {
        return studentRule;
    }

    public void setStudentRule(Integer studentRule) {
        this.studentRule = studentRule;
    }

    public Integer getTeacherRule() {
        return teacherRule;
    }

    public void setTeacherRule(Integer teacherRule) {
        this.teacherRule = teacherRule;
    }

    public String getShortFlag() {
        return shortFlag;
    }

    public void setShortFlag(String shortFlag) {
        this.shortFlag = shortFlag == null ? null : shortFlag.trim();
    }

    public String getDeployUrl() {
        return deployUrl;
    }

    public void setDeployUrl(String deployUrl) {
        this.deployUrl = deployUrl == null ? null : deployUrl.trim();
    }

    public String getUserable() {
        return userable;
    }

    public void setUserable(String userable) {
        this.userable = userable == null ? null : userable.trim();
    }

    public String getPrimaryPersion() {
        return primaryPersion;
    }

    public void setPrimaryPersion(String primaryPersion) {
        this.primaryPersion = primaryPersion == null ? null : primaryPersion.trim();
    }

    public String getDeputyPersion() {
        return deputyPersion;
    }

    public void setDeputyPersion(String deputyPersion) {
        this.deputyPersion = deputyPersion == null ? null : deputyPersion.trim();
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
        School other = (School) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getEname() == null ? other.getEname() == null : this.getEname().equals(other.getEname()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
            && (this.getXz() == null ? other.getXz() == null : this.getXz().equals(other.getXz()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getGrade() == null ? other.getGrade() == null : this.getGrade().equals(other.getGrade()))
            && (this.getLogo() == null ? other.getLogo() == null : this.getLogo().equals(other.getLogo()))
            && (this.getBgPicture() == null ? other.getBgPicture() == null : this.getBgPicture().equals(other.getBgPicture()))
            && (this.getHomeText() == null ? other.getHomeText() == null : this.getHomeText().equals(other.getHomeText()))
            && (this.getBottomText() == null ? other.getBottomText() == null : this.getBottomText().equals(other.getBottomText()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getmId() == null ? other.getmId() == null : this.getmId().equals(other.getmId()))
            && (this.getMaster() == null ? other.getMaster() == null : this.getMaster().equals(other.getMaster()))
            && (this.getZipCode() == null ? other.getZipCode() == null : this.getZipCode().equals(other.getZipCode()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getFax() == null ? other.getFax() == null : this.getFax().equals(other.getFax()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getPatriarchRule() == null ? other.getPatriarchRule() == null : this.getPatriarchRule().equals(other.getPatriarchRule()))
            && (this.getStudentRule() == null ? other.getStudentRule() == null : this.getStudentRule().equals(other.getStudentRule()))
            && (this.getTeacherRule() == null ? other.getTeacherRule() == null : this.getTeacherRule().equals(other.getTeacherRule()))
            && (this.getShortFlag() == null ? other.getShortFlag() == null : this.getShortFlag().equals(other.getShortFlag()))
            && (this.getDeployUrl() == null ? other.getDeployUrl() == null : this.getDeployUrl().equals(other.getDeployUrl()))
            && (this.getUserable() == null ? other.getUserable() == null : this.getUserable().equals(other.getUserable()))
            && (this.getPrimaryPersion() == null ? other.getPrimaryPersion() == null : this.getPrimaryPersion().equals(other.getPrimaryPersion()))
            && (this.getDeputyPersion() == null ? other.getDeputyPersion() == null : this.getDeputyPersion().equals(other.getDeputyPersion()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getEname() == null) ? 0 : getEname().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getXz() == null) ? 0 : getXz().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getGrade() == null) ? 0 : getGrade().hashCode());
        result = prime * result + ((getLogo() == null) ? 0 : getLogo().hashCode());
        result = prime * result + ((getBgPicture() == null) ? 0 : getBgPicture().hashCode());
        result = prime * result + ((getHomeText() == null) ? 0 : getHomeText().hashCode());
        result = prime * result + ((getBottomText() == null) ? 0 : getBottomText().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getmId() == null) ? 0 : getmId().hashCode());
        result = prime * result + ((getMaster() == null) ? 0 : getMaster().hashCode());
        result = prime * result + ((getZipCode() == null) ? 0 : getZipCode().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getFax() == null) ? 0 : getFax().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getPatriarchRule() == null) ? 0 : getPatriarchRule().hashCode());
        result = prime * result + ((getStudentRule() == null) ? 0 : getStudentRule().hashCode());
        result = prime * result + ((getTeacherRule() == null) ? 0 : getTeacherRule().hashCode());
        result = prime * result + ((getShortFlag() == null) ? 0 : getShortFlag().hashCode());
        result = prime * result + ((getDeployUrl() == null) ? 0 : getDeployUrl().hashCode());
        result = prime * result + ((getUserable() == null) ? 0 : getUserable().hashCode());
        result = prime * result + ((getPrimaryPersion() == null) ? 0 : getPrimaryPersion().hashCode());
        result = prime * result + ((getDeputyPersion() == null) ? 0 : getDeputyPersion().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
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
        sb.append(", parentId=").append(parentId);
        sb.append(", name=").append(name);
        sb.append(", ename=").append(ename);
        sb.append(", sort=").append(sort);
        sb.append(", xz=").append(xz);
        sb.append(", code=").append(code);
        sb.append(", type=").append(type);
        sb.append(", grade=").append(grade);
        sb.append(", logo=").append(logo);
        sb.append(", bgPicture=").append(bgPicture);
        sb.append(", homeText=").append(homeText);
        sb.append(", bottomText=").append(bottomText);
        sb.append(", address=").append(address);
        sb.append(", mId=").append(mId);
        sb.append(", master=").append(master);
        sb.append(", zipCode=").append(zipCode);
        sb.append(", phone=").append(phone);
        sb.append(", fax=").append(fax);
        sb.append(", email=").append(email);
        sb.append(", patriarchRule=").append(patriarchRule);
        sb.append(", studentRule=").append(studentRule);
        sb.append(", teacherRule=").append(teacherRule);
        sb.append(", shortFlag=").append(shortFlag);
        sb.append(", deployUrl=").append(deployUrl);
        sb.append(", userable=").append(userable);
        sb.append(", primaryPersion=").append(primaryPersion);
        sb.append(", deputyPersion=").append(deputyPersion);
        sb.append(", createBy=").append(createBy);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", remarks=").append(remarks);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}