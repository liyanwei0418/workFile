package cn.gukeer.platform.modelView.weather;

import java.io.Serializable;

public class Daily_forecast implements Serializable {

    public Astro astro;
    public Cond cond;
    public String date;
    public String hum;
    public String pcpn;
    public String pop;
    public String pres;
    public Tmp tmp;

    public String getUv() {
        return uv;
    }

    public void setUv(String uv) {
        this.uv = uv;
    }

    public String uv;
    public String vis;
    public Wind wind;
    public void setAstro(Astro astro) {
        this.astro = astro;
    }
    public Astro getAstro() {
        return astro;
    }

    public void setCond(Cond cond) {
        this.cond = cond;
    }
    public Cond getCond() {
        return cond;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return date;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }
    public String getHum() {
        return hum;
    }

    public void setPcpn(String pcpn) {
        this.pcpn = pcpn;
    }
    public String getPcpn() {
        return pcpn;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }
    public String getPop() {
        return pop;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }
    public String getPres() {
        return pres;
    }

    public void setTmp(Tmp tmp) {
        this.tmp = tmp;
    }
    public Tmp getTmp() {
        return tmp;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }
    public String getVis() {
        return vis;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }
    public Wind getWind() {
        return wind;
    }

    @Override
    public String toString() {
        return "{" +
                "astro=" + astro +
                ", cond=" + cond +
                ", date='" + date + '\'' +
                ", hum='" + hum + '\'' +
                ", pcpn='" + pcpn + '\'' +
                ", pop='" + pop + '\'' +
                ", pres='" + pres + '\'' +
                ", tmp=" + tmp +
                ", uv='" + uv + '\'' +
                ", vis='" + vis + '\'' +
                ", wind=" + wind +
                '}';
    }
}
