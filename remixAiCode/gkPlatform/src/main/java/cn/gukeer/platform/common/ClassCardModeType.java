package cn.gukeer.platform.common;

/**
 * Created by alpha on 17-6-26.
 */
public enum ClassCardModeType {


    EMERGENCY(0, "紧急通知"), EXAMINATION(1, "考场模式"), INSPECTION(2, "领导视察"), OTHER(3, "其他");

    /**
     * emergency
     */
    public static final String TYPE_EMERGENCY = "emergency";

    /**
     * examination
     */
    public static final String TYPE_EXAMINATION = "examination";

    /**
     * inspection
     */
    public static final String TYPE_INSPECTION = "inspection";

    /**
     * other
     */
    public static final String TYPE_OTHER = "other";

    private int index;
    private String name;

    ClassCardModeType(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public static String getName(int index) {
        for (ClassCardModeType c : ClassCardModeType.values()) {
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