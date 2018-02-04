package cn.gukeer.platform.modelView;

public class BanjiView {
    private String id;
    private String pid;
    private String name;
    private boolean open;
    private String selectedId;

    public boolean isOpen() {
        return open;
    }

    public String getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(String selectedId) {
        this.selectedId = selectedId;
    }

    public String getId() {
        return id;
    }

    public void setId(String string) {
        this.id = string;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getOpen() {
        return open;
    }

    public void setOpen(boolean b) {
        this.open = b;
    }


}
