package cn.gukeer.classcard.persistence.entity;

import java.io.Serializable;

public class ClassCardConfigRef implements Serializable {
    private String id;

    private String classCardConfigId;

    private String classCardId;

    private String schoolId;

    private Integer delFlag;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getClassCardConfigId() {
        return classCardConfigId;
    }

    public void setClassCardConfigId(String classCardConfigId) {
        this.classCardConfigId = classCardConfigId == null ? null : classCardConfigId.trim();
    }

    public String getClassCardId() {
        return classCardId;
    }

    public void setClassCardId(String classCardId) {
        this.classCardId = classCardId == null ? null : classCardId.trim();
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId == null ? null : schoolId.trim();
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
        ClassCardConfigRef other = (ClassCardConfigRef) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getClassCardConfigId() == null ? other.getClassCardConfigId() == null : this.getClassCardConfigId().equals(other.getClassCardConfigId()))
            && (this.getClassCardId() == null ? other.getClassCardId() == null : this.getClassCardId().equals(other.getClassCardId()))
            && (this.getSchoolId() == null ? other.getSchoolId() == null : this.getSchoolId().equals(other.getSchoolId()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getClassCardConfigId() == null) ? 0 : getClassCardConfigId().hashCode());
        result = prime * result + ((getClassCardId() == null) ? 0 : getClassCardId().hashCode());
        result = prime * result + ((getSchoolId() == null) ? 0 : getSchoolId().hashCode());
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
        sb.append(", classCardConfigId=").append(classCardConfigId);
        sb.append(", classCardId=").append(classCardId);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}