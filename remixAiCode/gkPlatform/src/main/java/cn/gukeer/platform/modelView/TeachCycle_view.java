package cn.gukeer.platform.modelView;

import cn.gukeer.platform.persistence.entity.TeachCycle;

/**
 * Created by LL on 2017/4/6.
 */
public class TeachCycle_view extends TeachCycle {
    private String start;
    private String end;
    private TeachCycle teachCycle;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public TeachCycle getTeachCycle() {
        return teachCycle;
    }

    public void setTeachCycle(TeachCycle teachCycle) {
        this.teachCycle = teachCycle;
    }
}
