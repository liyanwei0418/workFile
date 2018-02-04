package cn.gukeer.platform.persistence.entity;

import java.io.Serializable;

public class NetDiskAccount implements Serializable {
    private String id;

    private String schoolId;

    private String email;

    private Long insertTime;

    private String token;

    private Integer delFlag;

    private String createrEmail;

    private String createrToken;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Long getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Long insertTime) {
        this.insertTime = insertTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreaterEmail() {
        return createrEmail;
    }

    public void setCreaterEmail(String createrEmail) {
        this.createrEmail = createrEmail == null ? null : createrEmail.trim();
    }

    public String getCreaterToken() {
        return createrToken;
    }

    public void setCreaterToken(String createrToken) {
        this.createrToken = createrToken == null ? null : createrToken.trim();
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
        NetDiskAccount other = (NetDiskAccount) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSchoolId() == null ? other.getSchoolId() == null : this.getSchoolId().equals(other.getSchoolId()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getInsertTime() == null ? other.getInsertTime() == null : this.getInsertTime().equals(other.getInsertTime()))
            && (this.getToken() == null ? other.getToken() == null : this.getToken().equals(other.getToken()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getCreaterEmail() == null ? other.getCreaterEmail() == null : this.getCreaterEmail().equals(other.getCreaterEmail()))
            && (this.getCreaterToken() == null ? other.getCreaterToken() == null : this.getCreaterToken().equals(other.getCreaterToken()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSchoolId() == null) ? 0 : getSchoolId().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getInsertTime() == null) ? 0 : getInsertTime().hashCode());
        result = prime * result + ((getToken() == null) ? 0 : getToken().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getCreaterEmail() == null) ? 0 : getCreaterEmail().hashCode());
        result = prime * result + ((getCreaterToken() == null) ? 0 : getCreaterToken().hashCode());
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
        sb.append(", email=").append(email);
        sb.append(", insertTime=").append(insertTime);
        sb.append(", token=").append(token);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", createrEmail=").append(createrEmail);
        sb.append(", createrToken=").append(createrToken);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}