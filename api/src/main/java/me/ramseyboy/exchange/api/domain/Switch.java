package me.ramseyboy.exchange.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vividsolutions.jts.geom.Point;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "switches")
public class Switch {

    @Id
    @JsonProperty("id")
    @Column(name = "ogc_fid")
    private int id;

    @JsonProperty("geometry")
    @Column(name = "wkb_geometry", columnDefinition = "Point")
    private Point geometry;

    @JsonProperty("block")
    private String block;

    @JsonProperty("rate_centre")
    @Column(name = "rate_centre")
    private String rateCentre;

    @JsonProperty("disc_date")
    @Column(name = "disc_date")
    private String discDate;

    @JsonProperty("ocn")
    private String ocn;

    @JsonProperty("eff_date")
    @Column(name = "eff_date")
    private String effDate;

    @JsonProperty("lata")
    private String lata;

    @JsonProperty("npa_nxx")
    @Column(name = "npa_nxx")
    private String npaNxx;

    @JsonProperty("switch_id")
    @Column(name = "switch")
    private String switchId;

    @JsonProperty("region")
    private String region;

    public int getId() {
        return id;
    }

    public String getBlock() {
        return block;
    }

    public String getRateCentre() {
        return rateCentre;
    }

    public String getDiscDate() {
        return discDate;
    }

    public String getOcn() {
        return ocn;
    }

    public String getEffDate() {
        return effDate;
    }

    public String getLata() {
        return lata;
    }

    public String getNpaNxx() {
        return npaNxx;
    }

    public String getSwitchAddress() {
        return switchId;
    }

    public String getRegion() {
        return region;
    }

    public Point getGeometry() {
        return geometry;
    }

    @Override
    public String toString() {
        return "Switch{" +
                "id=" + id +
                ", geometry=" + geometry +
                ", block='" + block + '\'' +
                ", rateCentre='" + rateCentre + '\'' +
                ", discDate='" + discDate + '\'' +
                ", ocn='" + ocn + '\'' +
                ", effDate='" + effDate + '\'' +
                ", lata='" + lata + '\'' +
                ", npaNxx='" + npaNxx + '\'' +
                ", switchId='" + switchId + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}