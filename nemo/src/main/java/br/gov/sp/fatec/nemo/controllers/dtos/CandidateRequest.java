package br.gov.sp.fatec.nemo.controllers.dtos;

import br.gov.sp.fatec.nemo.domains.entities.Candidate;
import br.gov.sp.fatec.nemo.domains.enums.Availability;
import br.gov.sp.fatec.nemo.domains.enums.DesiredJourney;
import br.gov.sp.fatec.nemo.domains.enums.WorkModality;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class CandidateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

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
    private Double pretensionSalary;
    private DesiredJourney desiredJourney;
    private Availability availablePeriod;
    private WorkModality workModality;
    private List<CandidateSkillRequest> skills;
    private List<CandidateFormationRequest> formations;
    private List<CandidateExpRequest> experiences;

    public Candidate toCandidate() {
        Candidate newCandidate = new Candidate();
        newCandidate.setName(name);
        newCandidate.setEmail(email);
        newCandidate.setCpf(cpf);
        newCandidate.setPhone(phone);
        newCandidate.setGender(gender);
        newCandidate.setBirthday(birthday);
        newCandidate.setCountry(country);
        newCandidate.setCity(city);
        newCandidate.setNeighborhood(neighborhood);
        newCandidate.setStreet(street);
        newCandidate.setHomeNumber(homeNumber);
        newCandidate.setComplement(complement);
        newCandidate.setZipCode(zipCode);
        newCandidate.setLatitude(latitude);
        newCandidate.setLongitude(longitude);
        newCandidate.setPretensionSalary(pretensionSalary);
        newCandidate.setDesiredJourney(desiredJourney.name());
        newCandidate.setAvailablePeriod(availablePeriod.name());
        newCandidate.setWorkModality(workModality.name());
        newCandidate.setSkills(new ArrayList<>());
        newCandidate.setFormations(new ArrayList<>());
        newCandidate.setExperiences(new ArrayList<>());
        return newCandidate;
    }
}
