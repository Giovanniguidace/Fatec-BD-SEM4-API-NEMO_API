package br.gov.sp.fatec.nemo.domains.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

@Entity
public class SelectionProcessCandidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "can_id")
    public Integer id;

    @Column(name = "can_name")
    public String name;
    public String email;
    public String cpf;
    public String phone;
    public String gender;
    public Date birthday;
    public String availability;
    public String country;
    public String city;
    public String neighborhood;
    public String street;

    @Column(name = "home_number")
    public Integer homeNumber;
    public String complement;

    @Column(name = "zip_code")
    public String zipCode;
    public Double latitude;
    public Double longitude;

    @Column(name = "desired_journey")
    public String desiredJourney;

    @Column(name = "work_modality")
    public String workModality;

    public BigDecimal pretensionSalary;

    @Column(name = "fk_status_name")
    public String status;
    public String obs;

    public SelectionProcessCandidate() {
    }

    public SelectionProcessCandidate(Integer id, String name, String email, String cpf, String phone, String gender, Date birthday, String availability, String country, String city, String neighborhood, String street, Integer homeNumber, String complement, String zipCode, Double latitude, Double longitude, String desiredJourney, String workModality, BigDecimal pretentionSalary, String status, String obs) {
        this.id = id;
        this.name = name == null ? "" : name;
        this.email = email == null ? "" : email;
        this.cpf = cpf == null ? "" : cpf;
        this.phone = phone == null ? "" : phone;
        this.gender = gender == null ? "" : gender;
        this.birthday = birthday == null ? Date.from(Instant.MIN) : birthday;
        this.availability = availability == null ? "" : availability;
        this.country = country == null ? "" : country;
        this.city = city == null ? "" : city;
        this.neighborhood = neighborhood == null ? "" : neighborhood;
        this.street = street == null ? "" : street;
        this.homeNumber = homeNumber == null ? 0 : homeNumber;
        this.complement = complement == null ? "" : complement;
        this.zipCode = zipCode == null ? "" : zipCode;
        this.latitude = latitude == null ? 0 : latitude;
        this.longitude = longitude == null ? 0 : longitude;
        this.desiredJourney = desiredJourney == null ? "" : desiredJourney;
        this.workModality = workModality == null ? "" : workModality;
        this.pretensionSalary = pretentionSalary == null ? BigDecimal.ZERO : pretentionSalary;
        this.status = status == null ? "" : status;
        this.obs = obs == null ? "" : obs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
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

    public Integer getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(Integer homeNumber) {
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getDesiredJourney() {
        return desiredJourney;
    }

    public void setDesiredJourney(String desiredJourney) {
        this.desiredJourney = desiredJourney;
    }

    public String getWorkModality() {
        return workModality;
    }

    public void setWorkModality(String workModality) {
        this.workModality = workModality;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public BigDecimal getPretensionSalary() {
        return pretensionSalary;
    }

    public void setPretensionSalary(BigDecimal pretensionSalary) {
        this.pretensionSalary = pretensionSalary;
    }
}
