package me.ramseyboy.exchange.api.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DistanceResponseGeom {

    @JsonProperty("distance")
    private double distance;

    public DistanceResponseGeom(double distance) {
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }
}
