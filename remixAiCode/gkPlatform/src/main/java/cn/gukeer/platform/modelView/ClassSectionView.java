package cn.gukeer.platform.modelView;

import cn.gukeer.platform.persistence.entity.GradeClass;

import java.util.List;

/**
 * Created by conn on 2016/8/23.
 */
public class ClassSectionView {

    private String section;
    private String id;
    private String pid;
    private String name;
    private boolean open;
    private List<SchoolTypeView> schoolTypeView;
    private List<GradeClassView> njview;
    private List<GradeClass> classes;

    public String getSection() {
        return section;
    }
    public void setSection(String section) {
        this.section = section;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GradeClass> getClasses() {
        return classes;
    }

    public void setClasses(List<GradeClass> classes) {
        this.classes = classes;
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

	public void setPid(String string) {
		this.pid = string;
	}

	public List<SchoolTypeView> getSchoolTypeView() {
		return schoolTypeView;
	}

	public void setSchoolTypeView(List<SchoolTypeView> schoolTypeView) {
		this.schoolTypeView = schoolTypeView;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}


    public List<GradeClassView> getNjview() {
        return njview;
    }

    public void setNjview(List<GradeClassView> njview) {
        this.njview = njview;
    }
    
    
}
