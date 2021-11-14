package br.gov.sp.fatec.nemo.domains.entities.feingentities.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GeocodedWaypoints {
    @JsonProperty("geocoder_status")
    private String geocodeStatus;
    @JsonProperty("place_id")
    private String placeId;
    @JsonProperty("types")
    private List<String> types;

    public String getGeocodeStatus() {
        return geocodeStatus;
    }

    public void setGeocodeStatus(String geocodeStatus) {
        this.geocodeStatus = geocodeStatus;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
