package me.ramseyboy.exchange.api.domain

import com.fasterxml.jackson.annotation.JsonProperty
import com.vividsolutions.jts.geom.Geometry
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "area_codes")
data class AreaCode(

        @Id
        @JsonProperty("id")
        @Column(name = "ogc_fid")
        val id: Int,

        @JsonProperty("geometry")
        @Column(name = "wkb_geometry", columnDefinition = "Geometry")
        val geometry: Geometry,

        @JsonProperty("object_id")
        @Column(name = "objectid")
        val objectId: String,

        val npa: String,

        val state: String,

        val type: String,

        val begindial: String,

        val prevnpa: String,

        val enddial: String,

        val country: String,

        @JsonProperty("tags_added")
        @Column(name = "tgs_added")
        val tagsAdded: String,

        @JsonProperty("tags_modified")
        @Column(name = "tgs_mod")
        val tagsModified: String,

        @JsonProperty("shape_length")
        @Column(name = "shape_leng")
        val shapeLength: Double,

        @JsonProperty("shape_area")
        @Column(name = "shape_area")
        val shapeArea: Double
)