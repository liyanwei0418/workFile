package cn.gukeer.platform.modelView;

import cn.gukeer.platform.persistence.entity.GradeClass;

import java.util.List;

/**
 * Created by conn on 2016/8/23.
 */
public class GradeClassView extends GradeClass {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1194115323749241189L;
	private String tid;
    private String pid;
    private String njname;
    private String name;
    private List<BanjiView> banjiview;
    private String schoolName;
    private String xdName;
    private String bjName;
    private boolean open;
    
	public String getTid() {
		return tid;
	}
	public void setTid(String string) {
		this.tid = string;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String string) {
		this.pid = string;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<BanjiView> getBanjiview() {
		return banjiview;
	}
	public void setBanjiview(List<BanjiView> banjiview) {
		this.banjiview = banjiview;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getXdName() {
		return xdName;
	}
	public void setXdName(String xdName) {
		this.xdName = xdName;
	}
	
	public String getNjname() {
		return njname;
	}
	public void setNjname(String njname) {
		this.njname = njname;
	}
	public String getBjName() {
		return bjName;
	}
	public void setBjName(String bjName) {
		this.bjName = bjName;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
    
    
}
