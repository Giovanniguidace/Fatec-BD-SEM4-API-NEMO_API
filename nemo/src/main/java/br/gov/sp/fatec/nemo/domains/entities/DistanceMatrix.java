package br.gov.sp.fatec.nemo.domains.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "distance_matrix")
public class DistanceMatrix {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "serial")
    @SequenceGenerator(name = "serial", sequenceName = "serial",
        allocationSize = 1,initialValue=1)
    private Long id;

    @Column(name = "start_address")
    private String startAddress;

    @Column(name = "end_address")
    private String endAddress;

    @Column(name = "total_distance")
    private Float totalDistance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "can_id")
    @JsonIgnore
    private Candidate candidate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jo_id")
    @JsonIgnore
    private JobOpportunity jobOpportunity;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "distanceMatrix")
    private List<LegsEntity> legs;



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

    public Float getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(Float totalDistance) {
        this.totalDistance = totalDistance;
    }

    public List<LegsEntity> getLegs() {
        return legs;
    }

    public void setLegs(List<LegsEntity> legs) {
        this.legs = legs;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public JobOpportunity getJobOpportunity() {
        return jobOpportunity;
    }

    public void setJobOpportunity(JobOpportunity jobOpportunity) {
        this.jobOpportunity = jobOpportunity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistanceMatrix that = (DistanceMatrix) o;
        return Objects.equals(id, that.id) && Objects.equals(startAddress, that.startAddress) && Objects.equals(endAddress, that.endAddress) && Objects.equals(totalDistance, that.totalDistance) && Objects.equals(legs, that.legs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startAddress, endAddress, totalDistance, legs);
    }
}
