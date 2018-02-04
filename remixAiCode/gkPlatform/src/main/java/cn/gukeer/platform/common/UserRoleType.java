package cn.gukeer.platform.common;

/**
 * Created by conn on 2016/8/19.
 */
public enum UserRoleType {


    ROOT(0, "超级管理员"), ADMIN(1, "区级管理员"), TEACHER(2, "老师"), STUDENT(3, "学生"), PATRIARCH(4, "家长"),
    HEADTEACHER(5,"班主任"),CLASSCARDADMIN(6,"班牌管理员"),SCHOOLADMIN(7,"校级/区级管理员");

    /**
     * root
     */
    public static final String ROLE_ROOT = "root";

    /**
     * admin
     */
    public static final String ROLE_ADMIN = "admin";

    /**
     * teacher
     */
    public static final String ROLE_TEACHER = "teacher";

    /**
     * student
     */
    public static final String ROLE_STUDENT = "student";

    /**
     * 家长
     */
    public static final String ROLE_PATRIARCH = "patriarch";

    /**
     * 班主任
     */
    public static final String ROLE_HEADTEACHER = "headTeacher";
    /**
     * 班牌管理员
     */
    public static final String ROLE_CLASSCARDADMIN= "classCardAdmin";
    /**
     *  校级/区级管理员
     */
    public static final String ROLE_SCHOOLADMIN= "schoolAdmin";

    private int index;
    private String name;

    UserRoleType(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public static String getName(int index) {
        for (UserRoleType c : UserRoleType.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


}
