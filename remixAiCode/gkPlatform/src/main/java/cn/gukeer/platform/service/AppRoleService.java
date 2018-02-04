package cn.gukeer.platform.service;

import cn.gukeer.platform.persistence.entity.AppRole;

import java.util.List;

/**
 * Created by conn on 2016/8/8.
 */
public interface AppRoleService {

    int deleteAppRole(AppRole appRole);

    int insertAppRole(AppRole appRole);

    int deleteAppRoleByAppId(String appId);

    int batchInsertAppRole(List<AppRole> list);

}
