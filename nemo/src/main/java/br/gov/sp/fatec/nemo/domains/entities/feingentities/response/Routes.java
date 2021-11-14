package br.gov.sp.fatec.nemo.domains.entities.feingentities.response;

import java.util.List;

public class Routes {
    private Bounds bounds;
    private String copyrights;
    private List<Legs> legs;


    public Bounds getBounds() {
        return bounds;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    public String getCopyrights() {
        return copyrights;
    }

    public void setCopyrights(String copyrights) {
        this.copyrights = copyrights;
    }

    public List<Legs> getLegs() {
        return legs;
    }

    public void setLegs(List<Legs> legs) {
        this.legs = legs;
    }

}

