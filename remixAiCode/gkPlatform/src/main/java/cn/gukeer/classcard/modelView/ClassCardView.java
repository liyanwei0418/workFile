package cn.gukeer.classcard.modelView;


import cn.gukeer.classcard.persistence.entity.ClassCard;

/**
 * Created by alpha on 17-6-27.
 */
public class ClassCardView extends ClassCard {
    private String locationName;
    private String xd;
    private String nj;
    private String xq;

    private String schoolName;

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }

    public String getXd() {
        return xd;
    }

    public void setXd(String xd) {
        this.xd = xd;
    }

    public String getNj() {
        return nj;
    }

    public void setNj(String nj) {
        this.nj = nj;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
