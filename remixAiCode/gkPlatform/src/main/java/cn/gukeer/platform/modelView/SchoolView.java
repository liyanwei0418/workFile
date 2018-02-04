package cn.gukeer.platform.modelView;

import java.util.List;

/**
 * Created by conn on 2016/8/23.
 */
public class SchoolView {

    private String id;
    private int pid;
    private String name;
    private int sort;
    private List<ClassSectionView> sections;

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

    public List<ClassSectionView> getSections() {
        return sections;
    }

    public void setSections(List<ClassSectionView> sections) {
        this.sections = sections;
    }

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}
    
    
}
