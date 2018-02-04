package cn.gukeer.platform.persistence.entity;

/**
 * Created by LL on 2017/8/17.
 */
public class SeafileRepository {
    private  String permission;
    private Boolean encrypted;
    private String mtime_relative;
    private long mtime;
    private String owner;
    private String root;
    private String id;
    private long size;
    private String name;
    private String type;
    private boolean virtual;
    private Integer version;
    private String headCommitId;
    private String desc;
    private String sizeFommatted;

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Boolean getEncrypted() {
        return encrypted;
    }

    public void setEncrypted(Boolean encrypted) {
        this.encrypted = encrypted;
    }

    public String getMtime_relative() {
        return mtime_relative;
    }

    public void setMtime_relative(String mtime_relative) {
        this.mtime_relative = mtime_relative;
    }

    public long getMtime() {
        return mtime;
    }

    public void setMtime(long mtime) {
        this.mtime = mtime;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isVirtual() {
        return virtual;
    }

    public void setVirtual(boolean virtual) {
        this.virtual = virtual;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getHeadCommitId() {
        return headCommitId;
    }

    public void setHeadCommitId(String headCommitId) {
        this.headCommitId = headCommitId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSizeFommatted() {
        return sizeFommatted;
    }

    public void setSizeFommatted(String sizeFommatted) {
        this.sizeFommatted = sizeFommatted;
    }
}
