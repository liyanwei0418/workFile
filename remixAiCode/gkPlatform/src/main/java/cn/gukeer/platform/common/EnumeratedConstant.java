package cn.gukeer.platform.common;

/**
 * Created by Administrator on 2017/9/19.
 */
public enum EnumeratedConstant {

    //[0:root, 1:教师, 2:学生, 3:家长]
    PrimarySchool(1,"小学"),JuniorSchool(2,"初中"),HighSchool(3,"高中"),MixSchool(4,"一贯制"),
    Platform(1,"区平台"),School(2,"学校"),
    Man(1,"男"),Woman(2,"女"),
    ROOT(0,"root"),TEACHER(1,"老师"),STUDENT(2,"学生"),PATRIARCH(3,"家长");

    public static final int IS_MANAGER = 1;//是管理员
    public static final String MAIN_SCHOOL = "主校区";//主校区
    public static final String DEFAULT_MANAGER_NAME = "管理员";//管理员默认名称
    public static final String DEFAULT_MANAGER_PASSWORD = "000000";//管理员默认密码
    public static final String DEFAULT_MANAGER_SCHOOL_ADMIN = "1c0204e3e6519185f048bae0dd55a333";//学校最大管理员默认角色id
    public static final String DEFAULT_COMMON_STUDENT = "1c0204e3e6519185f048bae0dd55a444";//普通学生角色id
    public static final String DEFAULT_COMMON_PATRIARCH = "1c0204e3e6519185f048bae0dd55a555";//普通家长角色id
    public static final String DEFAULT_COMMON_TEACHER = "1c0204e3e6519185f048bae0dd55a777";//普通老师角色id


    private int index;
    private String name;

    EnumeratedConstant(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
