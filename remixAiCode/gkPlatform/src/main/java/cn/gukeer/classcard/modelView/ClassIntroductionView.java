package cn.gukeer.classcard.modelView;

import cn.gukeer.classcard.persistence.entity.ClassIntroduction;

/**
 * Created by alpha on 17-7-11.
 */
public class ClassIntroductionView extends ClassIntroduction {
    //班主任
    private String headTeacher;
    //副班主任
    private String viceTeacher;

    private String picUrl;

    private String picName;

    private String thumbnailUrl;

    private String videoName;

    private String videoUrl;

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getHeadTeacher() {
        return headTeacher;
    }

    public void setHeadTeacher(String headTeacher) {
        this.headTeacher = headTeacher;
    }

    public String getViceTeacher() {
        return viceTeacher;
    }

    public void setViceTeacher(String viceTeacher) {
        this.viceTeacher = viceTeacher;
    }
}
