package br.gov.sp.fatec.nemo.usecases.impls.dtos;

import br.gov.sp.fatec.nemo.domains.entities.CandidateSkill;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@NamedStoredProcedureQuery(
    name = "searchcandidate",
    procedureName = "searchcandidate",
    parameters = {
        @StoredProcedureParameter(name = "habilit_experience", mode = ParameterMode.IN, type = String[].class),
        @StoredProcedureParameter(name = "latitudePar", mode = ParameterMode.IN, type = Double.class),
        @StoredProcedureParameter(name = "longitudePar", mode = ParameterMode.IN, type = Double.class),
        @StoredProcedureParameter(name = "distanceLimit", mode = ParameterMode.IN, type = Double.class),

    }
)
@Table(name = "candidate")
public class CandidateFunction implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "can_id")
    @JsonIgnore
    private Long id;

    @NotBlank
    @Column(name = "can_name")
    private String name;

    @Email
    private String email;

    @CPF
    private String cpf;

    @NotBlank
    private String phone;

    @NotBlank
    private String gender;

    @NotNull
    private LocalDate birthday;

    @NotBlank
    private String country;

    @NotBlank
    private String city;

    @NotBlank
    private String neighborhood;

    @NotBlank
    private String street;

    @NotBlank
    @Column(name = "home_number")
    private String homeNumber;

    @NotBlank
    private String complement;

    @NotBlank
    @Column(name = "zip_code")
    private String zipCode;

    @NotNull
    private Float latitude;

    @NotNull
    private Float longitude;

    @NotBlank
    @Column(name = "distance")
    private Double distance;

    @OneToMany(mappedBy = "candidate",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    private List<CandidateSkill> skills;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public List<CandidateSkill> getSkills() {
        return skills;
    }

    public void setSkills(List<CandidateSkill> skills) {
        this.skills = skills;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
