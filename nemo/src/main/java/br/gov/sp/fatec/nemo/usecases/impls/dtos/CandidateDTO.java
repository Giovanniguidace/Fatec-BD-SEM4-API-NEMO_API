package br.gov.sp.fatec.nemo.usecases.impls.dtos;

import br.gov.sp.fatec.nemo.domains.entities.Candidate;
import br.gov.sp.fatec.nemo.domains.entities.CandidateSkill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private String name;

    private String email;

    private String cpf;

    private String phone;

    private String gender;

    private LocalDate birthday;

    private String country;

    private String city;

    private String neighborhood;

    private String street;

    private Integer homeNumber;

    private String complement;

    private String zipCode;

    private Float latitude;

    private Float longitude;

    private List<CandidateSkill> skills;

    private int points;

    private Double distance;


    public CandidateDTO fromCandidateDTO(Candidate candidate){
        CandidateDTO candidateDTO = new CandidateDTO();
        candidateDTO.setId(candidate.getId());
        candidateDTO.setName(candidate.getName());
        candidateDTO.setEmail(candidate.getEmail());
        candidateDTO.setCpf(candidate.getCpf());
        candidateDTO.setPhone(candidate.getPhone());
        candidateDTO.setGender(candidate.getGender());
        candidateDTO.setBirthday(candidate.getBirthday());
        candidateDTO.setCountry(candidate.getCountry());
        candidateDTO.setCity(candidate.getCity());
        candidateDTO.setNeighborhood(candidate.getNeighborhood());
        candidateDTO.setStreet(candidate.getStreet());
        candidateDTO.setHomeNumber(candidate.getHomeNumber());
        candidateDTO.setComplement(candidate.getComplement());
        candidateDTO.setZipCode(candidate.getZipCode());
        candidateDTO.setLatitude(candidate.getLatitude());
        candidateDTO.setLongitude(candidate.getLongitude());
        candidateDTO.setSkills(candidate.getSkills());

        return candidateDTO;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
