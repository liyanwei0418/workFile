package cn.gukeer.platform.modelView;

import cn.gukeer.platform.persistence.entity.TeachCycle;

/**
 * Created by LL on 2017/4/27.
 */
public class TeachCycleView extends TeachCycle{
    private TeachCycle teachCycle;
    private String strBeginDate;
    private String strEndDate;

    public TeachCycle getTeachCycle() {
        return teachCycle;
    }

    public void setTeachCycle(TeachCycle teachCycle) {
        this.teachCycle = teachCycle;
    }

    public String getStrBeginDate() {
        return strBeginDate;
    }

    public void setStrBeginDate(String strBeginDate) {
        this.strBeginDate = strBeginDate;
    }

    public String getStrEndDate() {
        return strEndDate;
    }

    public void setStrEndDate(String strEndDate) {
        this.strEndDate = strEndDate;
    }
}
