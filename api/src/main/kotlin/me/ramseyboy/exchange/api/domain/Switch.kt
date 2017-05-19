package me.ramseyboy.exchange.api.domain

import com.fasterxml.jackson.annotation.JsonProperty
import com.vividsolutions.jts.geom.Point
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "switches")
data class Switch(

        @Id
        @JsonProperty("id")
        @Column(name = "ogc_fid")
        val id: Int,

        @JsonProperty("geometry")
        @Column(name = "wkb_geometry", columnDefinition = "Point")
        val geometry: Point,

        @JsonProperty("block")
        val block: String,

        @JsonProperty("rate_centre")
        @Column(name = "rate_centre")
        val rateCentre: String,

        @JsonProperty("disc_date")
        @Column(name = "disc_date")
        val discDate: String,

        @JsonProperty("ocn")
        val ocn: String,

        @JsonProperty("eff_date")
        @Column(name = "eff_date")
        val effDate: String,

        @JsonProperty("lata")
        val lata: String,

        @JsonProperty("npa_nxx")
        @Column(name = "npa_nxx")
        val npaNxx: String,

        @JsonProperty("switch_id")
        @Column(name = "switch")
        val switchId: String,

        @JsonProperty("region")
        val region: String
)
