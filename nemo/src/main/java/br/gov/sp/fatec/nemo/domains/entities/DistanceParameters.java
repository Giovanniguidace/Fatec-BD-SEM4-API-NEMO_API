package br.gov.sp.fatec.nemo.domains.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;
@Table(name = "distance_parameters")
@Entity
public class DistanceParameters {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "serial")
    @SequenceGenerator(name = "serial", sequenceName = "serial",
        allocationSize = 1,initialValue=1)
    @Column(name = "distance_parameter_id")
    private Long id;

    @Column(name = "start_low_distance")
    private Integer startLowDistance;

    @Column(name = "end_low_distance")
    private Integer endLowDistance;

    @Column(name = "low_distance_value")
    private Integer lowDistanceValue;

    @Column(name = "start_medium_distance")
    private Integer startMediumDistance;

    @Column(name = "end_medium_distance")
    private Integer endMediumDistance;

    @Column(name = "medium_distance_value")
    private Integer mediumDistanceValue;

    @Column(name = "start_high_distance")
    private Integer startHighDistance;

    @Column(name = "end_high_distance")
    private Integer endHighDistance;

    @Column(name = "high_distance_value")
    private Integer valueHighDistance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStartLowDistance() {
        return startLowDistance;
    }

    public void setStartLowDistance(Integer startLowDistance) {
        this.startLowDistance = startLowDistance;
    }

    public Integer getEndLowDistance() {
        return endLowDistance;
    }

    public void setEndLowDistance(Integer endLowDistance) {
        this.endLowDistance = endLowDistance;
    }

    public Integer getLowDistanceValue() {
        return lowDistanceValue;
    }

    public void setLowDistanceValue(Integer lowDistanceValue) {
        this.lowDistanceValue = lowDistanceValue;
    }

    public Integer getStartMediumDistance() {
        return startMediumDistance;
    }

    public void setStartMediumDistance(Integer startMediumDistance) {
        this.startMediumDistance = startMediumDistance;
    }

    public Integer getEndMediumDistance() {
        return endMediumDistance;
    }

    public void setEndMediumDistance(Integer endMediumDistance) {
        this.endMediumDistance = endMediumDistance;
    }

    public Integer getMediumDistanceValue() {
        return mediumDistanceValue;
    }

    public void setMediumDistanceValue(Integer mediumDistanceValue) {
        this.mediumDistanceValue = mediumDistanceValue;
    }

    public Integer getStartHighDistance() {
        return startHighDistance;
    }

    public void setStartHighDistance(Integer startHighDistance) {
        this.startHighDistance = startHighDistance;
    }

    public Integer getEndHighDistance() {
        return endHighDistance;
    }

    public void setEndHighDistance(Integer endHighDistance) {
        this.endHighDistance = endHighDistance;
    }

    public Integer getValueHighDistance() {
        return valueHighDistance;
    }

    public void setValueHighDistance(Integer valueHighDistance) {
        this.valueHighDistance = valueHighDistance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistanceParameters that = (DistanceParameters) o;
        return Objects.equals(id, that.id) && Objects.equals(startLowDistance, that.startLowDistance) && Objects.equals(endLowDistance, that.endLowDistance) && Objects.equals(lowDistanceValue, that.lowDistanceValue) && Objects.equals(startMediumDistance, that.startMediumDistance) && Objects.equals(endMediumDistance, that.endMediumDistance) && Objects.equals(mediumDistanceValue, that.mediumDistanceValue) && Objects.equals(startHighDistance, that.startHighDistance) && Objects.equals(endHighDistance, that.endHighDistance) && Objects.equals(valueHighDistance, that.valueHighDistance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startLowDistance, endLowDistance, lowDistanceValue, startMediumDistance, endMediumDistance, mediumDistanceValue, startHighDistance, endHighDistance, valueHighDistance);
    }
}
