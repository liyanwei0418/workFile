package cn.gukeer.classcard.persistence.entity;

import java.io.Serializable;

public class ClassCardConfigScreenOffRef implements Serializable {
    private String id;

    private String classCardConfigId;

    private String screenOffWeek;

    private Long screenOffStartTime;

    private Long screenOffEndTime;

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

    public String getScreenOffWeek() {
        return screenOffWeek;
    }

    public void setScreenOffWeek(String screenOffWeek) {
        this.screenOffWeek = screenOffWeek == null ? null : screenOffWeek.trim();
    }

    public Long getScreenOffStartTime() {
        return screenOffStartTime;
    }

    public void setScreenOffStartTime(Long screenOffStartTime) {
        this.screenOffStartTime = screenOffStartTime;
    }

    public Long getScreenOffEndTime() {
        return screenOffEndTime;
    }

    public void setScreenOffEndTime(Long screenOffEndTime) {
        this.screenOffEndTime = screenOffEndTime;
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
        ClassCardConfigScreenOffRef other = (ClassCardConfigScreenOffRef) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getClassCardConfigId() == null ? other.getClassCardConfigId() == null : this.getClassCardConfigId().equals(other.getClassCardConfigId()))
            && (this.getScreenOffWeek() == null ? other.getScreenOffWeek() == null : this.getScreenOffWeek().equals(other.getScreenOffWeek()))
            && (this.getScreenOffStartTime() == null ? other.getScreenOffStartTime() == null : this.getScreenOffStartTime().equals(other.getScreenOffStartTime()))
            && (this.getScreenOffEndTime() == null ? other.getScreenOffEndTime() == null : this.getScreenOffEndTime().equals(other.getScreenOffEndTime()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getClassCardConfigId() == null) ? 0 : getClassCardConfigId().hashCode());
        result = prime * result + ((getScreenOffWeek() == null) ? 0 : getScreenOffWeek().hashCode());
        result = prime * result + ((getScreenOffStartTime() == null) ? 0 : getScreenOffStartTime().hashCode());
        result = prime * result + ((getScreenOffEndTime() == null) ? 0 : getScreenOffEndTime().hashCode());
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
        sb.append(", screenOffWeek=").append(screenOffWeek);
        sb.append(", screenOffStartTime=").append(screenOffStartTime);
        sb.append(", screenOffEndTime=").append(screenOffEndTime);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}