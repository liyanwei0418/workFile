package cn.gukeer.classcard.modelView;


import cn.gukeer.classcard.persistence.entity.ClassCardNotify;

/**
 * Created by alpha on 17-7-5.
 */
public class ClassCardNotifyView extends ClassCardNotify {
    String creatorName;
    String publishTime;

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
}
