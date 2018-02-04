package cn.gukeer.platform.modelView;

import cn.gukeer.platform.persistence.entity.FileFromSeafileEntity;

/**
 * Created by LL on 2017/8/31.
 */
public class FileFromSeafileEntityView extends FileFromSeafileEntity {
    private String fullPath;

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }
}
