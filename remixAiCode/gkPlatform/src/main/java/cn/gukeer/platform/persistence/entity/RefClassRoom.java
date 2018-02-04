package cn.gukeer.platform.persistence.entity;

import cn.gukeer.sync.annotation.NoSync;
import cn.gukeer.sync.annotation.PrimaryKey;
import cn.gukeer.sync.annotation.TableSync;

import java.io.Serializable;
@TableSync(SyncTableName="teach_ref_class_room",TargetName = "sync_teach_ref_class_room")
public class RefClassRoom implements Serializable {
    @PrimaryKey
    private String id;

    private String roomId;

    private String gradeClass;

    private String cycleId;

    private String schoolTypeId;

    @NoSync
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId == null ? null : roomId.trim();
    }

    public String getGradeClass() {
        return gradeClass;
    }

    public void setGradeClass(String gradeClass) {
        this.gradeClass = gradeClass == null ? null : gradeClass.trim();
    }

    public String getCycleId() {
        return cycleId;
    }

    public void setCycleId(String cycleId) {
        this.cycleId = cycleId == null ? null : cycleId.trim();
    }

    public String getSchoolTypeId() {
        return schoolTypeId;
    }

    public void setSchoolTypeId(String schoolTypeId) {
        this.schoolTypeId = schoolTypeId == null ? null : schoolTypeId.trim();
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
        RefClassRoom other = (RefClassRoom) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRoomId() == null ? other.getRoomId() == null : this.getRoomId().equals(other.getRoomId()))
            && (this.getGradeClass() == null ? other.getGradeClass() == null : this.getGradeClass().equals(other.getGradeClass()))
            && (this.getCycleId() == null ? other.getCycleId() == null : this.getCycleId().equals(other.getCycleId()))
            && (this.getSchoolTypeId() == null ? other.getSchoolTypeId() == null : this.getSchoolTypeId().equals(other.getSchoolTypeId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRoomId() == null) ? 0 : getRoomId().hashCode());
        result = prime * result + ((getGradeClass() == null) ? 0 : getGradeClass().hashCode());
        result = prime * result + ((getCycleId() == null) ? 0 : getCycleId().hashCode());
        result = prime * result + ((getSchoolTypeId() == null) ? 0 : getSchoolTypeId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roomId=").append(roomId);
        sb.append(", gradeClass=").append(gradeClass);
        sb.append(", cycleId=").append(cycleId);
        sb.append(", schoolTypeId=").append(schoolTypeId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}