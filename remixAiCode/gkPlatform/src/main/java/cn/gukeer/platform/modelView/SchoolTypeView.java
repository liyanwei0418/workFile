package cn.gukeer.platform.modelView;

import java.util.List;

/**
 * Created by pc-daisike on 2016/10/17.
 */
public class SchoolTypeView {
    private String id;
    private String pid;
    private String name;
    private int sort;
    private List<GradeClassView> njview;
    private boolean open;

    public String getId() {
        return id;
    }

    public void setId(String string) {
        this.id = string;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public List<GradeClassView> getNjview() {
        return njview;
    }

    public void setNjview(List<GradeClassView> njview) {
        this.njview = njview;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }


}
