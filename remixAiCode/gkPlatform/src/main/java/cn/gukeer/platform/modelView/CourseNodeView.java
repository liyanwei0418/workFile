package cn.gukeer.platform.modelView;

import cn.gukeer.platform.persistence.entity.CourseNode;

import java.io.Serializable;

/**
 * Created by LL on 2017/5/25.
 */
public class CourseNodeView  implements Serializable{
    private CourseNode courseNode;
    private String startTime;
    private String endTime;

    public CourseNode getCourseNode() {
        return courseNode;
    }

    public void setCourseNode(CourseNode courseNode) {
        this.courseNode = courseNode;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
