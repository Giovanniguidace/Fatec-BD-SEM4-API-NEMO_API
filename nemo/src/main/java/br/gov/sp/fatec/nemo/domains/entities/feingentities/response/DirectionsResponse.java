package br.gov.sp.fatec.nemo.domains.entities.feingentities.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DirectionsResponse {
    @JsonProperty("geocoded_waypoints")
    private List<GeocodedWaypoints> geocondedWaypoints;
    @JsonProperty("routes")
    private List<Routes> routes;

    public List<GeocodedWaypoints> getGeocondedWaypoints() {
        return geocondedWaypoints;
    }

    public void setGeocondedWaypoints(List<GeocodedWaypoints> geocondedWaypoints) {
        this.geocondedWaypoints = geocondedWaypoints;
    }

    public List<Routes> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Routes> routes) {
        this.routes = routes;
    }
}
