package cn.gukeer.platform.modelView;

import java.io.Serializable;

/**
 * Created by LL on 2017/5/5.
 */
public class RefClassRoomView implements Serializable{
    private String refId;
    private String gradeClassId;
    private Integer nj;
    private String banji;
    private String sectionId;
    private String sectionName;
    private String schoolTypeId;
    private String schoolTypeName;
    private String roomId;
    private String teachBuildingName;
    private String roomNum;

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getGradeClassId() {
        return gradeClassId;
    }

    public void setGradeClassId(String gradeClassId) {
        this.gradeClassId = gradeClassId;
    }

    public Integer getNj() {
        return nj;
    }

    public void setNj(Integer nj) {
        this.nj = nj;
    }

    public String getBanji() {
        return banji;
    }

    public void setBanji(String banji) {
        this.banji = banji;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSchoolTypeId() {
        return schoolTypeId;
    }

    public void setSchoolTypeId(String schoolTypeId) {
        this.schoolTypeId = schoolTypeId;
    }

    public String getSchoolTypeName() {
        return schoolTypeName;
    }

    public void setSchoolTypeName(String schoolTypeName) {
        this.schoolTypeName = schoolTypeName;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getTeachBuildingName() {
        return teachBuildingName;
    }

    public void setTeachBuildingName(String teachBuildingName) {
        this.teachBuildingName = teachBuildingName;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }
}
