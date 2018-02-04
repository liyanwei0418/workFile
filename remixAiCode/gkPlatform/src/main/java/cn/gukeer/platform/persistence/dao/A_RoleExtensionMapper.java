package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.modelView.RoleView;
import cn.gukeer.platform.persistence.entity.Role;
import cn.gukeer.platform.persistence.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by conn on 2016/8/19.
 */
public interface A_RoleExtensionMapper {
    List<RoleView> selectRoleViewByUserId(String userId);
    
    List<Role> findRoleByApp(String appId);

    List<Teacher> findRoleTeacher(@Param("roleId") String roleId, @Param("schoolId") String schoolId);
}
