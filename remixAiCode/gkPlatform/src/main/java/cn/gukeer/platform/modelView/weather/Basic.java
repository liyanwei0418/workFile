package cn.gukeer.platform.modelView.weather;

import java.io.Serializable;

public class Basic implements Serializable {

    public String city;
    public String cnty;
    public String id;
    public String lat;
    public String lon;
    public String prov;
    public Update update;
    public void setCity(String city) {
        this.city = city;
    }
    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "{" +
                "city='" + city + '\'' +
                ", cnty='" + cnty + '\'' +
                ", id='" + id + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", prov='" + prov + '\'' +
                ", update=" + update +
                '}';
    }

    public void setCnty(String cnty) {
        this.cnty = cnty;
    }
    public String getCnty() {
        return cnty;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
    public String getLat() {
        return lat;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
    public String getLon() {
        return lon;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }
    public String getProv() {
        return prov;
    }

    public void setUpdate(Update update) {
        this.update = update;
    }
    public Update getUpdate() {
        return update;
    }

}
