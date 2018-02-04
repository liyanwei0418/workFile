package cn.gukeer.platform.persistence.entity;

import java.io.Serializable;

/**
 * Created by LL on 2017/8/18.
 */
public class FileFromSeafileEntity implements Serializable{
    private String permission;//文件的权限
    private Long mtime;//文件的更新时间
    private String type;//文件类型 文件夹、文档、视频、音频、其他
    private String name;//文件名字
    private String id;//文件的id
    private String time;//字符串类型的时间
    private String sizeStr;//格式化的大小
    private Long size;
    private String parent_dir;

    public String getParent_dir() {
        return parent_dir;
    }

    public void setParent_dir(String parent_dir) {
        this.parent_dir = parent_dir;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Long getMtime() {
        return mtime;
    }

    public void setMtime(Long mtime) {
        this.mtime = mtime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSizeStr() {
        return sizeStr;
    }

    public void setSizeStr(String sizeStr) {
        this.sizeStr = sizeStr;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
