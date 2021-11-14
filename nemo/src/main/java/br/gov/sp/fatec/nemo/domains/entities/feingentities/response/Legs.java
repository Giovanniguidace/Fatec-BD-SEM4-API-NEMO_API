package br.gov.sp.fatec.nemo.domains.entities.feingentities.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Legs {
    private TextValue distance;
    private TextValue duration;
    private List<Steps> steps;

    @JsonProperty("end_address")
    private String endAddresss;

    @JsonProperty("end_location")
    private LatLng endLocation;

    @JsonProperty("start_address")
    private String startAddresss;

    public TextValue getDistance() {
        return distance;
    }

    public void setDistance(TextValue distance) {
        this.distance = distance;
    }

    public TextValue getDuration() {
        return duration;
    }

    public void setDuration(TextValue duration) {
        this.duration = duration;
    }

    public String getEndAddresss() {
        return endAddresss;
    }

    public void setEndAddresss(String endAddresss) {
        this.endAddresss = endAddresss;
    }

    public LatLng getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(LatLng endLocation) {
        this.endLocation = endLocation;
    }

    public String getStartAddresss() {
        return startAddresss;
    }

    public void setStartAddresss(String startAddresss) {
        this.startAddresss = startAddresss;
    }

    public List<Steps> getSteps() {
        return steps;
    }

    public void setSteps(List<Steps> steps) {
        this.steps = steps;
    }
}
