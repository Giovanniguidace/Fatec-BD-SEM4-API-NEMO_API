package br.gov.sp.fatec.nemo.domains.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
@Table(name = "parameter")
@Entity
public class Parameters {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "serial")
    @SequenceGenerator(name = "serial", sequenceName = "serial",
        allocationSize = 1,initialValue=1)
    @Column(name = "parameter_id")
    private Long id;

    @Column(name = "hability")
    private Integer hability;

    @Column(name = "experience")
    private Integer experience;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "distance_parameter_id", referencedColumnName = "distance_parameter_id")
    private DistanceParameters distanceParameters;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    private Client client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHability() {
        return hability;
    }

    public void setHability(Integer hability) {
        this.hability = hability;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public DistanceParameters getDistanceParameters() {
        return distanceParameters;
    }

    public void setDistanceParameters(DistanceParameters distanceParameters) {
        this.distanceParameters = distanceParameters;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameters that = (Parameters) o;
        return Objects.equals(id, that.id) && Objects.equals(hability, that.hability) && Objects.equals(experience, that.experience) && Objects.equals(distanceParameters, that.distanceParameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hability, experience, distanceParameters);
    }
}
