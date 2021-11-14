package br.gov.sp.fatec.nemo.domains.entities.feingentities.response;

public class Bounds {
    private Northeast northeast;
    private Southwest southwest;


    public Northeast getNortheast() {
        return northeast;
    }

    public void setNortheast(Northeast northeast) {
        this.northeast = northeast;
    }

    public Southwest getSouthwest() {
        return southwest;
    }

    public void setSouthwest(Southwest southwest) {
        this.southwest = southwest;
    }
}

class Northeast extends LatLng {

}

class Southwest extends LatLng {

}
