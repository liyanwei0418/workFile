package cn.gukeer.platform.modelView;

/**
 * Created by conn on 2016/8/19.
 */
public class AppView{

    private String id;//应用id appId

    private String name;//应用名称

    private String iconUrl;//应用图标图片地址

    private String webUrl;//应用跳转路径

    private Integer appStatus;//个人是否添加该应用

    private String appType;//应用类别

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public Integer getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(Integer appStatus) {
        this.appStatus = appStatus;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }
}
