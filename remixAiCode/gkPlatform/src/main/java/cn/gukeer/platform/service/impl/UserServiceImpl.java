package cn.gukeer.platform.service.impl;

import cn.gukeer.common.service.BasicService;
import cn.gukeer.common.tld.GukeerStringUtil;
import cn.gukeer.common.utils.NumberConvertUtil;
import cn.gukeer.common.utils.PrimaryKey;
import cn.gukeer.platform.modelView.MenuView;
import cn.gukeer.platform.modelView.RoleView;
import cn.gukeer.platform.persistence.dao.*;
import cn.gukeer.platform.persistence.entity.*;
import cn.gukeer.platform.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by conn on 2016/7/27.
 */
@Service
public class UserServiceImpl extends BasicService implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    A_UserExtensionMapper a_userExtensionMapper;

    @Autowired
    SchoolMapper schoolMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    A_RoleExtensionMapper roleExtensionMapper;

    @Autowired
    A_MenuExtensionMapper menuExtensionMapper;

    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    PatriarchMapper patriarchMapper;

    @Autowired
    StudentMapper studentMapper;

    @Override
    public User saveUser(User user) {
        User u = new User();
        if (StringUtils.isEmpty(user.getId())) {
            user.setId(PrimaryKey.get());
            user.setCreateDate(System.currentTimeMillis());
            int count = userMapper.insertSelective(user);
            u = user;
        } else {
            userMapper.updateByPrimaryKeySelective(user);

            //因为修改头像要同步推送，必须使教师数据修改，才能触发推送条件
            Teacher teacher = teacherMapper.selectByPrimaryKey(user.getRefId());
            if (null != teacher) {
                teacher.setUpdateDate(System.currentTimeMillis());
                teacherMapper.updateByPrimaryKeySelective(teacher);
            }
        }
        logger.info("save user username: {} and createTime: {}", user.getUsername(), user.getCreateDate());
        return u;
    }

    @Override
    public Integer saveUserRoleBatch(List<UserRole> userRole) {
        return a_userExtensionMapper.insertUserRoleBatch(userRole);
    }

    @Override
    public User getByAccountAndPwd(String username, String pwd) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(pwd);
        List<User> users = (List<User>) userMapper.selectByExample(example);

        User user = null;
        if (null != users && users.size() > 0) {
            user = users.get(0);
            logger.info("login with username: {} and createTime: {}", user.getUsername(), user.getCreateDate());
        }
        return user;
    }

    @Override
    public User getUserByAccount(String account) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(account);
        List<User> users = (List<User>) userMapper.selectByExample(example);
        User user = null;
        if (null != users && users.size() > 0) {
            user = users.get(0);
            logger.info("login with username: {} and createTime: {}", user.getUsername(), user.getCreateDate());
        }
        return user;
    }

    @Override
    public int updateUserByRefId(User user, String refId, String schoolId, int userType) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andRefIdEqualTo(refId).andUserTypeEqualTo(userType).andDelFlagEqualTo(0);
        return userMapper.updateByExampleSelective(user, userExample);
    }

    @Override
    public User getUserById(String userId) {

        User user = userMapper.selectByPrimaryKey(userId);
        return user;
    }

    @Override
    public List<RoleView> selectRoleViewByUserId(String userId) {

        List<RoleView> roleViews = roleExtensionMapper.selectRoleViewByUserId(userId);

        return roleViews;
    }

    @Override
    public List<MenuView> selectMenusByRoleId(String roleId) {

        List<MenuView> menuViews = menuExtensionMapper.selectMenusByRoleId(roleId);

        return menuViews;
    }

    @Override
    public int deleteUserById(String studentId) {
        User user = new User();
        user.setDelFlag(1);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andRefIdEqualTo(studentId);
        try {
            userMapper.updateByExampleSelective(user, userExample);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public List<User> selectUsersInIds(List<String> ids) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIn(ids).andDelFlagEqualTo(0);
        return userMapper.selectByExample(userExample);
    }

    @Override
    public List<User> getTeacherByRefId(String refId) {
        UserExample example = new UserExample();
        example.createCriteria().andRefIdEqualTo(refId).andUserTypeEqualTo(1);
        return userMapper.selectByExample(example);
    }

    @Override
    public int saveUserRole(UserRole userRole) {
        return userRoleMapper.insertSelective(userRole);
    }

    @Override
    public String insertUserBackId(User user) {
        userMapper.insertSelective(user);
        return user.getId();
    }

    @Override
    public List<UserRole> findUserRoleByCriteria(List<String> ids, Role role, String schoolId) {

        UserRoleExample example = new UserRoleExample();
        UserRoleExample.Criteria criteria = example.createCriteria();
        criteria.andSchoolIdEqualTo(schoolId).andRoleIdIn(ids);

        if (!role.getId().equals("") && role.getId() != "") {
            criteria.andRoleIdEqualTo(role.getId());
        }
        List<UserRole> userRoleList = new ArrayList<UserRole>();
        if (ids.size() > 0) {
            userRoleList = userRoleMapper.selectByExample(example);
        }
        return userRoleList;
    }

    @Override
    public List<UserRole> findUserRoleByUserIdAndRoleId(String userId, String roleId) {
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andUserIdEqualTo(userId).andRoleIdEqualTo(roleId);
        List<UserRole> roleList = userRoleMapper.selectByExample(example);
        return roleList;
    }

    @Override
    public PageInfo<Teacher> findRoleTeacherList(String roleId, String schoolId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<Teacher> res = roleExtensionMapper.findRoleTeacher(roleId, schoolId);
        PageInfo<Teacher> pageInfo = new PageInfo<Teacher>(res);
        return pageInfo;
    }

    @Override
    public int deleteUserRole(UserRole userRole) {
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria().andUserIdEqualTo(userRole.getUserId()).andRoleIdEqualTo(
                userRole.getRoleId()).andSchoolIdEqualTo(userRole.getSchoolId());
        return userRoleMapper.deleteByExample(userRoleExample);
    }

    @Override
    public List<User> selectUserByCriteria(List<String> refIds, int userType) {
        UserExample example = new UserExample();
        example.createCriteria().andRefIdIn(refIds).andUserTypeEqualTo(userType);
        List<User> userList = userMapper.selectByExample(example);
        return userList;
    }

    @Override
    public String findSameUserNameMax(String userName, User userP) {   //-1:没有同名用户; 0:仅有一个且没有结尾数字;  >0:结尾最大的数字
        if (userName.length() == 0) {
            return userName;
        }
        UserExample example = new UserExample();
        example.createCriteria().andUsernameLike(userName + "%").andSchoolIdEqualTo(userP.getSchoolId()).andDelFlagEqualTo(0);     //模糊查询会查出多余数据
        List<User> userList = userMapper.selectByExample(example);

        for (int i = 0; i < userList.size(); i++) {             //去除多余数据（含有中文字）
            User user = userList.get(i);
            String extra = user.getUsername().substring(userName.length());

            if (!StringUtils.isEmpty(extra)) {
                String[] trueExtra = extra.split("@");
                if (trueExtra.length > 1) {
                    extra = trueExtra[0];
                }
            }

            if (extra.length() != 0) {
                Pattern p = Pattern.compile("[0-9]{1,}");
                Matcher m = p.matcher(extra);
                if (!m.matches()) {
                    userList.remove(user);
                    i--;
                }
            }
        }

        Integer max = -1;                   // 返回 -1：不存在同名用户
        if (userList.size() > 0) {          //如果已存在同名用户，取max
            max = 0;                        //返回0：存在一个用户。没有数值默认为0，否则取其max
            for (User user : userList) {
                String str = user.getUsername();
                String _num = "";
                for (int i = str.length() - 1; i >= 0; i--) {
                    if (Character.isDigit(str.charAt(i))) {
                        _num = str.charAt(i) + _num;
                    } else {
                        continue;
                    }
                }
                if (!_num.equals("")) {
                    Integer num = NumberConvertUtil.convertS2I(_num.toString());
                    if (num > max) max = num;
                }
            }
        }

        if (max >= 0) {
            max += 1;
            for (int i = userName.length() - 1; i >= 0; i--) { //设置当前用户账号，如果用户名不含数字：username+max；否则将username的尾部数字替换为max；
                if (!Character.isDigit(userName.charAt(i))) {
                    userName = userName.substring(0, i + 1) + max.toString();
                    break;
                }
            }
        }

        School school = schoolMapper.selectByPrimaryKey(userP.getSchoolId());
        userName = userName + "@" + school.getShortFlag();
        return userName;
    }

    @Override
    public Object getLoginUserDetail(String refId, Object o) {
        if (o instanceof Teacher) {
            TeacherExample teacherExample = new TeacherExample();
            teacherExample.createCriteria().andDelFlagEqualTo(0).andIdEqualTo(refId);
            List<Teacher> res = teacherMapper.selectByExample(teacherExample);
            if (res.size() > 0) {
                return res.get(0);
            }
        }
        if (o instanceof Patriarch) {
            PatriarchExample patriarchExample = new PatriarchExample();
            patriarchExample.createCriteria().andDelFlagEqualTo(0).andIdEqualTo(refId);
            List<Patriarch> res = patriarchMapper.selectByExample(patriarchExample);
            if (res.size() > 0) {
                return res.get(0);
            }
        }
        if (o instanceof Student) {
            StudentExample studentExample = new StudentExample();
            studentExample.createCriteria().andDelFlagEqualTo(0).andIdEqualTo(refId);
            List<Student> res = studentMapper.selectByExample(studentExample);
            if (res.size() > 0) {
                return res.get(0);
            }
        }
        return null;
    }

    @Override
    public boolean isAreaAdmin(String schoolId) {
        School school = schoolMapper.selectByPrimaryKey(schoolId);
        if (GukeerStringUtil.isNullOrEmpty(school))
            return false;
        if (school.getType() == 1)//1区平台。2 学校
            return true;
        return false;
    }


}
