package cn.gukeer.platform.service;

import cn.gukeer.platform.modelView.MenuView;
import cn.gukeer.platform.modelView.RoleView;
import cn.gukeer.platform.persistence.entity.Role;
import cn.gukeer.platform.persistence.entity.Teacher;
import cn.gukeer.platform.persistence.entity.User;
import cn.gukeer.platform.persistence.entity.UserRole;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {

	User saveUser(User user);

    Integer saveUserRoleBatch(List<UserRole> userRole);
	
	User getByAccountAndPwd(String account, String pwd);

    User getUserByAccount(String account);

    User getUserById(String userId);

    int updateUserByRefId(User user,String refId,String schoolId, int userType);

    List<RoleView> selectRoleViewByUserId(String userId);

    List<MenuView> selectMenusByRoleId(String roleId);
    
    int deleteUserById(String studentId);

    List<User> selectUsersInIds(List<String> ids);

    List<User> getTeacherByRefId(String refId);

    int saveUserRole(UserRole userRole);

    String insertUserBackId(User user);

    List<UserRole> findUserRoleByCriteria(List<String> ids, Role role, String schoolId);

    List<UserRole> findUserRoleByUserIdAndRoleId(String userId,String roleId);

    PageInfo<Teacher> findRoleTeacherList(String roleId, String schoolId, Integer pageSize, Integer pageNum);

    int deleteUserRole(UserRole userRole);

    List<User> selectUserByCriteria(List<String> refIds,int userType);

    String findSameUserNameMax(String userName,User user);

    Object getLoginUserDetail(String refId,Object o);

    boolean isAreaAdmin(String schoolId);//判断是否是区级人员

}
