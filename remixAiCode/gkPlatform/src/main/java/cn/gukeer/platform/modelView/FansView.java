package cn.gukeer.platform.modelView;

public class FansView {
    private String wb_num;//微博数动态
    private String follow;//关注数
    private String fans;//粉丝数
    private String follow_url;//点击关注数对应的url
    private String wb_url;//点击微博数对应的url
    private String fans_url;//点击粉丝数对应的url

    public String getWb_num() {
        return wb_num;
    }

    public void setWb_num(String wb_num) {
        this.wb_num = wb_num;
    }

    public String getFollow() {
        return follow;
    }

    public void setFollow(String follow) {
        this.follow = follow;
    }

    public String getFans() {
        return fans;
    }

    public void setFans(String fans) {
        this.fans = fans;
    }

    public String getFollow_url() {
        return follow_url;
    }

    public void setFollow_url(String follow_url) {
        this.follow_url = follow_url;
    }

    public String getWb_url() {
        return wb_url;
    }

    public void setWb_url(String wb_url) {
        this.wb_url = wb_url;
    }

    public String getFans_url() {
        return fans_url;
    }

    public void setFans_url(String fans_url) {
        this.fans_url = fans_url;
    }
}
