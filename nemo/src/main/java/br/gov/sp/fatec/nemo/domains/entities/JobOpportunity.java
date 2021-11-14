package br.gov.sp.fatec.nemo.domains.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vividsolutions.jts.geom.Geometry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.br.CPF;
import br.gov.sp.fatec.nemo.domains.enums.SkillLevel;
import br.gov.sp.fatec.nemo.domains.enums.Availability;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "job_opportunity")
public class JobOpportunity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "serial")
    @SequenceGenerator(name = "serial", sequenceName = "serial",
        allocationSize = 1,initialValue=1)
    @Column(name = "jo_id")
    @JsonIgnore
    private Long id;

    @NotBlank
    @Column(name = "jo_name")
    private String joName;

    @NotBlank
    private String description;

    @NotBlank
    @Column(name = "contract_type")
    private String contractType;

    @NotBlank
    @Column(name = "working_hours")
    private Time workingHours;

    @NotBlank
    @Column(name = "salary_range_ini")
    private Double salaryRangeIni;

    @NotBlank
    @Column(name = "salary_range_end")
    private Double salaryRangeEnd;

    @NotBlank
    private String gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "availability", columnDefinition = "availability")
    @Type(type = "pgsql_enum")
    private Availability availability;

    @Column(name = "workplace_country")
    private String workplaceCountry;

    @Column(name = "workplace_city")
    private String workplaceCity;

    @Column(name = "workplace_neighborhood")
    private String workplaceNeighborhood;

    @Column(name = "workplace_street")
    private String workplaceStreet;

    @Column(name = "workplace_home_number")
    private Integer workplaceNumber;

    @Column(name = "workplace_complement")
    private String workplaceComplement;

    @Column(name = "workplace_zip_code")
    private String workplaceZipCode;

    @Column(name = "workplace_latitude")
    private Double workplaceLatitude;

    @Column(name = "workplace_longitude")
    private Double workplaceLongitude;

    @NotBlank
    @Column(name = "divulgation_ini")
    private LocalDateTime divulgationIni;

    @NotBlank
    @Column(name = "divulgation_end")
    private LocalDateTime divulgationEnd;

    @OneToMany(mappedBy = "jobOpportunity",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    private List<DistanceMatrix> distanceMatrices;


    @NotBlank
    @Column(name = "create_dt")
    private LocalDateTime createDt;

    @NotBlank
    @Column(name = "update_dt")
    private LocalDateTime updateDt;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JobOpportunity)) {
            return false;
        }
        return id != null && id.equals(((JobOpportunity) o).id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJoName() {
        return joName;
    }

    public void setJoName(String joName) {
        this.joName = joName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public Time getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Time workingHours) {
        this.workingHours = workingHours;
    }

    public Double getSalaryRangeIni() {
        return salaryRangeIni;
    }

    public void setSalaryRangeIni(Double salaryRangeIni) {
        this.salaryRangeIni = salaryRangeIni;
    }

    public Double getSalaryRangeEnd() {
        return salaryRangeEnd;
    }

    public void setSalaryRangeEnd(Double salaryRangeEnd) {
        this.salaryRangeEnd = salaryRangeEnd;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public String getWorkplaceCountry() {
        return workplaceCountry;
    }

    public void setWorkplaceCountry(String workplaceCountry) {
        this.workplaceCountry = workplaceCountry;
    }

    public String getWorkplaceCity() {
        return workplaceCity;
    }

    public void setWorkplaceCity(String workplaceCity) {
        this.workplaceCity = workplaceCity;
    }

    public String getWorkplaceNeighborhood() {
        return workplaceNeighborhood;
    }

    public void setWorkplaceNeighborhood(String workplaceNeighborhood) {
        this.workplaceNeighborhood = workplaceNeighborhood;
    }

    public String getWorkplaceStreet() {
        return workplaceStreet;
    }

    public void setWorkplaceStreet(String workplaceStreet) {
        this.workplaceStreet = workplaceStreet;
    }

    public Integer getWorkplaceNumber() {
        return workplaceNumber;
    }

    public void setWorkplaceNumber(Integer workplaceNumber) {
        this.workplaceNumber = workplaceNumber;
    }

    public String getWorkplaceComplement() {
        return workplaceComplement;
    }

    public void setWorkplaceComplement(String workplaceComplement) {
        this.workplaceComplement = workplaceComplement;
    }

    public String getWorkplaceZipCode() {
        return workplaceZipCode;
    }

    public void setWorkplaceZipCode(String workplaceZipCode) {
        this.workplaceZipCode = workplaceZipCode;
    }

    public Double getWorkplaceLatitude() {
        return workplaceLatitude;
    }

    public void setWorkplaceLatitude(Double workplaceLatitude) {
        this.workplaceLatitude = workplaceLatitude;
    }

    public Double getWorkplaceLongitude() {
        return workplaceLongitude;
    }

    public void setWorkplaceLongitude(Double workplaceLongitude) {
        this.workplaceLongitude = workplaceLongitude;
    }

    public LocalDateTime getDivulgationIni() {
        return divulgationIni;
    }

    public void setDivulgationIni(LocalDateTime divulgationIni) {
        this.divulgationIni = divulgationIni;
    }

    public LocalDateTime getDivulgationEnd() {
        return divulgationEnd;
    }

    public void setDivulgationEnd(LocalDateTime divulgationEnd) {
        this.divulgationEnd = divulgationEnd;
    }

    public LocalDateTime getCreateDt() {
        return createDt;
    }

    public void setCreateDt(LocalDateTime createDt) {
        this.createDt = createDt;
    }

    public LocalDateTime getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(LocalDateTime updateDt) {
        this.updateDt = updateDt;
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "JobOpportunity{" +
            "id=" + id +
            ", joName='" + joName + '\'' +
            ", description='" + description + '\'' +
            ", contractType='" + contractType + '\'' +
            ", workingHours=" + workingHours +
            ", salaryRangeIni='" + salaryRangeIni + '\'' +
            ", salaryRangeEnd='" + salaryRangeEnd + '\'' +
            ", gender='" + gender + '\'' +
            ", availability=" + availability +
            ", workplaceCountry='" + workplaceCountry + '\'' +
            ", workplaceCity='" + workplaceCity + '\'' +
            ", workplaceNeighborhood='" + workplaceNeighborhood + '\'' +
            ", workplaceStreet='" + workplaceStreet + '\'' +
            ", workplaceNumber='" + workplaceNumber + '\'' +
            ", workplaceComplement='" + workplaceComplement + '\'' +
            ", workplaceZipCode='" + workplaceZipCode + '\'' +
            ", workplaceLatitude=" + workplaceLatitude +
            ", workplaceLongitude=" + workplaceLongitude +
            ", divulgationIni=" + divulgationIni +
            ", divulgationEnd=" + divulgationEnd +
            ", createDt=" + createDt +
            ", updateDt=" + updateDt +
            '}';
    }
}
