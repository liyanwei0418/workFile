package cn.gukeer.classcard.persistence.entity;

import java.io.Serializable;

public class ClassCard implements Serializable {
    private String id;

    private String macId;

    private String equipmentName;

    private String teachClassRoomId;

    private String classId;

    private String classroom;

    private String classSlogan;

    private String schoolId;

    private String updateBy;

    private Long updateDate;

    private String createBy;

    private Long createDate;

    private Integer delFlag;

    private String bootDayOfWeek;

    private String startBootTimeOfDay;

    private String endBootTimeOfDay;

    private Integer isSynchro;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMacId() {
        return macId;
    }

    public void setMacId(String macId) {
        this.macId = macId == null ? null : macId.trim();
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName == null ? null : equipmentName.trim();
    }

    public String getTeachClassRoomId() {
        return teachClassRoomId;
    }

    public void setTeachClassRoomId(String teachClassRoomId) {
        this.teachClassRoomId = teachClassRoomId == null ? null : teachClassRoomId.trim();
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom == null ? null : classroom.trim();
    }

    public String getClassSlogan() {
        return classSlogan;
    }

    public void setClassSlogan(String classSlogan) {
        this.classSlogan = classSlogan == null ? null : classSlogan.trim();
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId == null ? null : schoolId.trim();
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

    public String getBootDayOfWeek() {
        return bootDayOfWeek;
    }

    public void setBootDayOfWeek(String bootDayOfWeek) {
        this.bootDayOfWeek = bootDayOfWeek == null ? null : bootDayOfWeek.trim();
    }

    public String getStartBootTimeOfDay() {
        return startBootTimeOfDay;
    }

    public void setStartBootTimeOfDay(String startBootTimeOfDay) {
        this.startBootTimeOfDay = startBootTimeOfDay == null ? null : startBootTimeOfDay.trim();
    }

    public String getEndBootTimeOfDay() {
        return endBootTimeOfDay;
    }

    public void setEndBootTimeOfDay(String endBootTimeOfDay) {
        this.endBootTimeOfDay = endBootTimeOfDay == null ? null : endBootTimeOfDay.trim();
    }

    public Integer getIsSynchro() {
        return isSynchro;
    }

    public void setIsSynchro(Integer isSynchro) {
        this.isSynchro = isSynchro;
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
        ClassCard other = (ClassCard) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMacId() == null ? other.getMacId() == null : this.getMacId().equals(other.getMacId()))
            && (this.getEquipmentName() == null ? other.getEquipmentName() == null : this.getEquipmentName().equals(other.getEquipmentName()))
            && (this.getTeachClassRoomId() == null ? other.getTeachClassRoomId() == null : this.getTeachClassRoomId().equals(other.getTeachClassRoomId()))
            && (this.getClassId() == null ? other.getClassId() == null : this.getClassId().equals(other.getClassId()))
            && (this.getClassroom() == null ? other.getClassroom() == null : this.getClassroom().equals(other.getClassroom()))
            && (this.getClassSlogan() == null ? other.getClassSlogan() == null : this.getClassSlogan().equals(other.getClassSlogan()))
            && (this.getSchoolId() == null ? other.getSchoolId() == null : this.getSchoolId().equals(other.getSchoolId()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getBootDayOfWeek() == null ? other.getBootDayOfWeek() == null : this.getBootDayOfWeek().equals(other.getBootDayOfWeek()))
            && (this.getStartBootTimeOfDay() == null ? other.getStartBootTimeOfDay() == null : this.getStartBootTimeOfDay().equals(other.getStartBootTimeOfDay()))
            && (this.getEndBootTimeOfDay() == null ? other.getEndBootTimeOfDay() == null : this.getEndBootTimeOfDay().equals(other.getEndBootTimeOfDay()))
            && (this.getIsSynchro() == null ? other.getIsSynchro() == null : this.getIsSynchro().equals(other.getIsSynchro()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMacId() == null) ? 0 : getMacId().hashCode());
        result = prime * result + ((getEquipmentName() == null) ? 0 : getEquipmentName().hashCode());
        result = prime * result + ((getTeachClassRoomId() == null) ? 0 : getTeachClassRoomId().hashCode());
        result = prime * result + ((getClassId() == null) ? 0 : getClassId().hashCode());
        result = prime * result + ((getClassroom() == null) ? 0 : getClassroom().hashCode());
        result = prime * result + ((getClassSlogan() == null) ? 0 : getClassSlogan().hashCode());
        result = prime * result + ((getSchoolId() == null) ? 0 : getSchoolId().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getBootDayOfWeek() == null) ? 0 : getBootDayOfWeek().hashCode());
        result = prime * result + ((getStartBootTimeOfDay() == null) ? 0 : getStartBootTimeOfDay().hashCode());
        result = prime * result + ((getEndBootTimeOfDay() == null) ? 0 : getEndBootTimeOfDay().hashCode());
        result = prime * result + ((getIsSynchro() == null) ? 0 : getIsSynchro().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", macId=").append(macId);
        sb.append(", equipmentName=").append(equipmentName);
        sb.append(", teachClassRoomId=").append(teachClassRoomId);
        sb.append(", classId=").append(classId);
        sb.append(", classroom=").append(classroom);
        sb.append(", classSlogan=").append(classSlogan);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", createBy=").append(createBy);
        sb.append(", createDate=").append(createDate);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", bootDayOfWeek=").append(bootDayOfWeek);
        sb.append(", startBootTimeOfDay=").append(startBootTimeOfDay);
        sb.append(", endBootTimeOfDay=").append(endBootTimeOfDay);
        sb.append(", isSynchro=").append(isSynchro);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}