package br.gov.sp.fatec.nemo.domains.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "legs")
public class LegsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "serial")
    @SequenceGenerator(name = "serial", sequenceName = "serial",
        allocationSize = 1,initialValue=1)
    private Long id;

    @Column(name = "start_address")
    @JsonIgnore
    private String startAddress;

    @Column(name = "end_address")
    @JsonIgnore
    private String endAddress;

    @Column(name = "distance")
    private Float distance;

    @Column(name = "duration")
    private Float duration;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "distance_matrix_id")
    @JsonIgnore
    private DistanceMatrix distanceMatrix;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "legsEntity")
    private List<Steps> steps;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
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

    public DistanceMatrix getDistanceMatrix() {
        return distanceMatrix;
    }

    public void setDistanceMatrix(DistanceMatrix distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
    }

    public List<Steps> getSteps() {
        return steps;
    }

    public void setSteps(List<Steps> steps) {
        this.steps = steps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LegsEntity legs = (LegsEntity) o;
        return Objects.equals(id, legs.id) && Objects.equals(startAddress, legs.startAddress) && Objects.equals(endAddress, legs.endAddress) && Objects.equals(distance, legs.distance) && Objects.equals(duration, legs.duration) && Objects.equals(distanceMatrix, legs.distanceMatrix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startAddress, endAddress, distance, duration, distanceMatrix);
    }
}
