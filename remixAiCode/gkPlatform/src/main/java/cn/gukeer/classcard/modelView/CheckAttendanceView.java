package cn.gukeer.classcard.modelView;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by alpha on 17-11-9.
 */
public class CheckAttendanceView implements Serializable {
    private String roomId;
    private int weekendNo;
    private int lessonNo;
    private Long date;
    //5:在班,0:不在班
    private List<Map<String ,String>> studentStatus;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public int getWeekendNo() {
        return weekendNo;
    }

    public void setWeekendNo(int weekendNo) {
        this.weekendNo = weekendNo;
    }

    public int getLessonNo() {
        return lessonNo;
    }

    public void setLessonNo(int lessonNo) {
        this.lessonNo = lessonNo;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public List<Map<String, String>> getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(List<Map<String, String>> studentStatus) {
        this.studentStatus = studentStatus;
    }
}
