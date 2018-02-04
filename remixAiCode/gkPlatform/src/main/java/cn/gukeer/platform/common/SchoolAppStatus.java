package cn.gukeer.platform.common;

/**
 * Created by lx on 2016/11/24.
 */
public enum SchoolAppStatus {
    //上线
    ONLINE(2),

    //下线
    OFFLINE(3);

    private final int statenum;

    SchoolAppStatus(int statenum){
        this.statenum = statenum;
    }

    public int getStatenum() {
        return statenum;
    }
}
