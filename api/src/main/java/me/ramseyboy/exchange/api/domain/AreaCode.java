package me.ramseyboy.exchange.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "area_codes")
public class AreaCode {

    @Id
    @JsonProperty("id")
    @Column(name = "ogc_fid")
    private int id;

    @JsonProperty("geometry")
    @Column(name = "wkb_geometry", columnDefinition = "Geometry")
    private Geometry geometry;

    @JsonProperty("object_id")
    @Column(name = "objectid")
    private String objectId;

    private String npa;

    private String state;

    private String type;

    private String begindial;

    private String prevnpa;

    private String enddial;

    private String country;

    @JsonProperty("tags_added")
    @Column(name = "tgs_added")
    private String tagsAdded;

    @JsonProperty("tags_modified")
    @Column(name = "tgs_mod")
    private String tagsModified;

    @JsonProperty("shape_length")
    @Column(name = "shape_leng")
    private Double shapeLength;

    @JsonProperty("shape_area")
    @Column(name = "shape_area")
    private Double shapeArea;

    public int getId() {
        return id;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getNpa() {
        return npa;
    }

    public String getState() {
        return state;
    }

    public String getType() {
        return type;
    }

    public String getBegindial() {
        return begindial;
    }

    public String getPrevnpa() {
        return prevnpa;
    }

    public String getEnddial() {
        return enddial;
    }

    public String getCountry() {
        return country;
    }

    public String getTagsAdded() {
        return tagsAdded;
    }

    public String getTagsModified() {
        return tagsModified;
    }

    public Double getShapeLength() {
        return shapeLength;
    }

    public Double getShapeArea() {
        return shapeArea;
    }
}
