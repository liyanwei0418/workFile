package cn.gukeer.platform.modelView;

import cn.gukeer.platform.persistence.entity.Role;

import java.io.Serializable;

/**
 * Created by conn on 2016/8/19.
 */
public class RoleView extends Role implements Serializable {

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
