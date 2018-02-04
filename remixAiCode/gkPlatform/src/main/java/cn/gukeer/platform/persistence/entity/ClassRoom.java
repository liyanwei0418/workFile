package cn.gukeer.platform.persistence.entity;

import cn.gukeer.sync.annotation.NoSync;
import cn.gukeer.sync.annotation.PrimaryKey;
import cn.gukeer.sync.annotation.TableSync;

import java.io.Serializable;
@TableSync(SyncTableName="teach_class_room",TargetName = "sync_teach_class_room")
public class ClassRoom implements Serializable {
    @PrimaryKey
    private String id;

    private String roomName;

    private String roomType;

    private String roomTypeName;

    private String roomNum;

    private String schoolId;

    private String teachBuilding;

    private Integer teachBuildingNum;

    private String schoolType;

    private String schoolTypeName;

    private String floor;

    private Integer count;

    private Integer availableSeat;

    private Integer examSeat;

    private Integer courseSelect;

    @NoSync
    private String remarks;
    @NoSync
    private String updateBy;
    @NoSync
    private Long updateDate;
    @NoSync
    private String createBy;
    @NoSync
    private Long createDate;

    private Integer delFlag;
    @NoSync
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName == null ? null : roomName.trim();
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType == null ? null : roomType.trim();
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName == null ? null : roomTypeName.trim();
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum == null ? null : roomNum.trim();
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId == null ? null : schoolId.trim();
    }

    public String getTeachBuilding() {
        return teachBuilding;
    }

    public void setTeachBuilding(String teachBuilding) {
        this.teachBuilding = teachBuilding == null ? null : teachBuilding.trim();
    }

    public Integer getTeachBuildingNum() {
        return teachBuildingNum;
    }

    public void setTeachBuildingNum(Integer teachBuildingNum) {
        this.teachBuildingNum = teachBuildingNum;
    }

    public String getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType == null ? null : schoolType.trim();
    }

    public String getSchoolTypeName() {
        return schoolTypeName;
    }

    public void setSchoolTypeName(String schoolTypeName) {
        this.schoolTypeName = schoolTypeName == null ? null : schoolTypeName.trim();
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor == null ? null : floor.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getAvailableSeat() {
        return availableSeat;
    }

    public void setAvailableSeat(Integer availableSeat) {
        this.availableSeat = availableSeat;
    }

    public Integer getExamSeat() {
        return examSeat;
    }

    public void setExamSeat(Integer examSeat) {
        this.examSeat = examSeat;
    }

    public Integer getCourseSelect() {
        return courseSelect;
    }

    public void setCourseSelect(Integer courseSelect) {
        this.courseSelect = courseSelect;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
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
        ClassRoom other = (ClassRoom) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getRoomName() == null ? other.getRoomName() == null : this.getRoomName().equals(other.getRoomName()))
                && (this.getRoomType() == null ? other.getRoomType() == null : this.getRoomType().equals(other.getRoomType()))
                && (this.getRoomTypeName() == null ? other.getRoomTypeName() == null : this.getRoomTypeName().equals(other.getRoomTypeName()))
                && (this.getRoomNum() == null ? other.getRoomNum() == null : this.getRoomNum().equals(other.getRoomNum()))
                && (this.getSchoolId() == null ? other.getSchoolId() == null : this.getSchoolId().equals(other.getSchoolId()))
                && (this.getTeachBuilding() == null ? other.getTeachBuilding() == null : this.getTeachBuilding().equals(other.getTeachBuilding()))
                && (this.getTeachBuildingNum() == null ? other.getTeachBuildingNum() == null : this.getTeachBuildingNum().equals(other.getTeachBuildingNum()))
                && (this.getSchoolType() == null ? other.getSchoolType() == null : this.getSchoolType().equals(other.getSchoolType()))
                && (this.getSchoolTypeName() == null ? other.getSchoolTypeName() == null : this.getSchoolTypeName().equals(other.getSchoolTypeName()))
                && (this.getFloor() == null ? other.getFloor() == null : this.getFloor().equals(other.getFloor()))
                && (this.getCount() == null ? other.getCount() == null : this.getCount().equals(other.getCount()))
                && (this.getAvailableSeat() == null ? other.getAvailableSeat() == null : this.getAvailableSeat().equals(other.getAvailableSeat()))
                && (this.getExamSeat() == null ? other.getExamSeat() == null : this.getExamSeat().equals(other.getExamSeat()))
                && (this.getCourseSelect() == null ? other.getCourseSelect() == null : this.getCourseSelect().equals(other.getCourseSelect()))
                && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()))
                && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
                && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
                && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
                && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
                && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRoomName() == null) ? 0 : getRoomName().hashCode());
        result = prime * result + ((getRoomType() == null) ? 0 : getRoomType().hashCode());
        result = prime * result + ((getRoomTypeName() == null) ? 0 : getRoomTypeName().hashCode());
        result = prime * result + ((getRoomNum() == null) ? 0 : getRoomNum().hashCode());
        result = prime * result + ((getSchoolId() == null) ? 0 : getSchoolId().hashCode());
        result = prime * result + ((getTeachBuilding() == null) ? 0 : getTeachBuilding().hashCode());
        result = prime * result + ((getTeachBuildingNum() == null) ? 0 : getTeachBuildingNum().hashCode());
        result = prime * result + ((getSchoolType() == null) ? 0 : getSchoolType().hashCode());
        result = prime * result + ((getSchoolTypeName() == null) ? 0 : getSchoolTypeName().hashCode());
        result = prime * result + ((getFloor() == null) ? 0 : getFloor().hashCode());
        result = prime * result + ((getCount() == null) ? 0 : getCount().hashCode());
        result = prime * result + ((getAvailableSeat() == null) ? 0 : getAvailableSeat().hashCode());
        result = prime * result + ((getExamSeat() == null) ? 0 : getExamSeat().hashCode());
        result = prime * result + ((getCourseSelect() == null) ? 0 : getCourseSelect().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
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
        sb.append(", roomName=").append(roomName);
        sb.append(", roomType=").append(roomType);
        sb.append(", roomTypeName=").append(roomTypeName);
        sb.append(", roomNum=").append(roomNum);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", teachBuilding=").append(teachBuilding);
        sb.append(", teachBuildingNum=").append(teachBuildingNum);
        sb.append(", schoolType=").append(schoolType);
        sb.append(", schoolTypeName=").append(schoolTypeName);
        sb.append(", floor=").append(floor);
        sb.append(", count=").append(count);
        sb.append(", availableSeat=").append(availableSeat);
        sb.append(", examSeat=").append(examSeat);
        sb.append(", courseSelect=").append(courseSelect);
        sb.append(", remarks=").append(remarks);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", createBy=").append(createBy);
        sb.append(", createDate=").append(createDate);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}