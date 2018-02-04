package cn.gukeer.platform.persistence.entity;

import cn.gukeer.sync.annotation.NoSync;
import cn.gukeer.sync.annotation.PrimaryKey;
import cn.gukeer.sync.annotation.TableSync;

import java.io.Serializable;
@TableSync(SyncTableName="teach_daily_hour",TargetName = "sync_teach_daily_hour")
public class DailyHour implements Serializable {
    @PrimaryKey
    private String id;

    private String schoolId;

    private String gradeClassId;

    private Integer skts;

    private Integer swks;

    private Integer xwks;

    private Integer kjc;

    private String cycleId;

    private String xn;

    private String xq;
    @NoSync
    private Long createTime;
    @NoSync
    private Long updateTime;
    @NoSync
    private String updateBy;

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

    public String getGradeClassId() {
        return gradeClassId;
    }

    public void setGradeClassId(String gradeClassId) {
        this.gradeClassId = gradeClassId == null ? null : gradeClassId.trim();
    }

    public Integer getSkts() {
        return skts;
    }

    public void setSkts(Integer skts) {
        this.skts = skts;
    }

    public Integer getSwks() {
        return swks;
    }

    public void setSwks(Integer swks) {
        this.swks = swks;
    }

    public Integer getXwks() {
        return xwks;
    }

    public void setXwks(Integer xwks) {
        this.xwks = xwks;
    }

    public Integer getKjc() {
        return kjc;
    }

    public void setKjc(Integer kjc) {
        this.kjc = kjc;
    }

    public String getCycleId() {
        return cycleId;
    }

    public void setCycleId(String cycleId) {
        this.cycleId = cycleId == null ? null : cycleId.trim();
    }

    public String getXn() {
        return xn;
    }

    public void setXn(String xn) {
        this.xn = xn == null ? null : xn.trim();
    }

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq == null ? null : xq.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
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
        DailyHour other = (DailyHour) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSchoolId() == null ? other.getSchoolId() == null : this.getSchoolId().equals(other.getSchoolId()))
            && (this.getGradeClassId() == null ? other.getGradeClassId() == null : this.getGradeClassId().equals(other.getGradeClassId()))
            && (this.getSkts() == null ? other.getSkts() == null : this.getSkts().equals(other.getSkts()))
            && (this.getSwks() == null ? other.getSwks() == null : this.getSwks().equals(other.getSwks()))
            && (this.getXwks() == null ? other.getXwks() == null : this.getXwks().equals(other.getXwks()))
            && (this.getKjc() == null ? other.getKjc() == null : this.getKjc().equals(other.getKjc()))
            && (this.getCycleId() == null ? other.getCycleId() == null : this.getCycleId().equals(other.getCycleId()))
            && (this.getXn() == null ? other.getXn() == null : this.getXn().equals(other.getXn()))
            && (this.getXq() == null ? other.getXq() == null : this.getXq().equals(other.getXq()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSchoolId() == null) ? 0 : getSchoolId().hashCode());
        result = prime * result + ((getGradeClassId() == null) ? 0 : getGradeClassId().hashCode());
        result = prime * result + ((getSkts() == null) ? 0 : getSkts().hashCode());
        result = prime * result + ((getSwks() == null) ? 0 : getSwks().hashCode());
        result = prime * result + ((getXwks() == null) ? 0 : getXwks().hashCode());
        result = prime * result + ((getKjc() == null) ? 0 : getKjc().hashCode());
        result = prime * result + ((getCycleId() == null) ? 0 : getCycleId().hashCode());
        result = prime * result + ((getXn() == null) ? 0 : getXn().hashCode());
        result = prime * result + ((getXq() == null) ? 0 : getXq().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
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
        sb.append(", gradeClassId=").append(gradeClassId);
        sb.append(", skts=").append(skts);
        sb.append(", swks=").append(swks);
        sb.append(", xwks=").append(xwks);
        sb.append(", kjc=").append(kjc);
        sb.append(", cycleId=").append(cycleId);
        sb.append(", xn=").append(xn);
        sb.append(", xq=").append(xq);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}