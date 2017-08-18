package me.ramseyboy.exchange.api.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import me.ramseyboy.exchange.api.domain.Switch;

public class DistanceResponse {
    @JsonProperty("begin")
    private Switch begin;

    @JsonProperty("end")
    private Switch end;

    @JsonProperty("distance")
    private double distance;

    public DistanceResponse(Switch begin, Switch end, double distance) {
        this.begin = begin;
        this.end = end;
        this.distance = distance;
    }

    public Switch getBegin() {
        return begin;
    }

    public Switch getEnd() {
        return end;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "DistanceResponse{" +
                "begin=" + begin +
                ", end=" + end +
                ", distance=" + distance +
                '}';
    }
}
