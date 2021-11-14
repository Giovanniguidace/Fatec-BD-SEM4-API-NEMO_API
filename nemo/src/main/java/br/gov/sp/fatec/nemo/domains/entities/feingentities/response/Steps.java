package br.gov.sp.fatec.nemo.domains.entities.feingentities.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Steps {
    private TextValue distance;
    private TextValue duration;
    @JsonProperty(value = "html_instructions")
    private String htmlInstruction;
    @JsonProperty(value = "travel_mode")
    private String travelMode;

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

    public String getHtmlInstruction() {
        return htmlInstruction;
    }

    public void setHtmlInstruction(String htmlInstruction) {
        this.htmlInstruction = htmlInstruction;
    }

    public String getTravelMode() {
        return travelMode;
    }

    public void setTravelMode(String travelMode) {
        this.travelMode = travelMode;
    }
}
