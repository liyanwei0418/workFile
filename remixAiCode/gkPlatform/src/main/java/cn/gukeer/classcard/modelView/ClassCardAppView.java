package cn.gukeer.classcard.modelView;

import cn.gukeer.classcard.persistence.entity.ClassCardApp;

/**
 * Created by alpha on 18-1-9.
 */
public class ClassCardAppView extends ClassCardApp {
    private String uploadTime;
    private String uploadPersion;

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getUploadPersion() {
        return uploadPersion;
    }

    public void setUploadPersion(String uploadPersion) {
        this.uploadPersion = uploadPersion;
    }
}
