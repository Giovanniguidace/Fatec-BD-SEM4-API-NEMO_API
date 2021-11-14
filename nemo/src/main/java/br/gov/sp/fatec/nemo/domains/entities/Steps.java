package br.gov.sp.fatec.nemo.domains.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "steps")
public class Steps implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "serial")
    @SequenceGenerator(name = "serial", sequenceName = "serial",
        allocationSize = 1, initialValue = 1)
    private Long id;

    private Float distance;
    private Float duration;

    @Column(name = "html_instruction")
    private String htmlInstruction;

    @Column(name = "travel_mode")
    private String travelMode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leg_id")
    @JsonIgnore
    private LegsEntity legsEntity;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
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

    public LegsEntity getLegsEntity() {
        return legsEntity;
    }

    public void setLegsEntity(LegsEntity legsEntity) {
        this.legsEntity = legsEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Steps steps = (Steps) o;
        return Objects.equals(id, steps.id) && Objects.equals(distance, steps.distance) && Objects.equals(duration, steps.duration) && Objects.equals(htmlInstruction, steps.htmlInstruction) && Objects.equals(travelMode, steps.travelMode) && Objects.equals(legsEntity, steps.legsEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, distance, duration, htmlInstruction, travelMode, legsEntity);
    }
}
