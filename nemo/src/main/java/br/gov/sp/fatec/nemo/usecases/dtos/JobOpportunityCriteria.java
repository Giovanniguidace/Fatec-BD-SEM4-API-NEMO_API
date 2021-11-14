package br.gov.sp.fatec.nemo.usecases.dtos;

import br.gov.sp.fatec.nemo.domains.enums.Availability;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import lombok.Data;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;
@Data
public class JobOpportunityCriteria implements Serializable, Criteria {

    private LongFilter id;

    private StringFilter joName;

    private StringFilter description;

    private StringFilter contractType;

    private StringFilter workingHours;

    private StringFilter salaryRangeIni;

    private StringFilter salaryRangeEnd;

    private StringFilter gender;

    private StringFilter availability;

    private StringFilter workplaceCountry;

    private StringFilter workplaceCity;

    private StringFilter workplaceNeighborhood;

    private StringFilter workplaceStreet;

    private StringFilter workplaceNumber;

    private StringFilter workplaceComplement;

    private StringFilter workplaceZipCode;

    private FloatFilter workplaceLatitude;

    private FloatFilter workplaceLongitude;

    private StringFilter divulgationIni;

    private StringFilter divulgationEnd;

    private StringFilter createDt;

    private StringFilter updateDt;

    public JobOpportunityCriteria(JobOpportunityCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.joName = other.joName == null ? null : other.joName.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.contractType = other.contractType == null ? null : other.contractType.copy();
        this.workingHours = other.workingHours == null ? null : other.workingHours.copy();
        this.salaryRangeIni = other.salaryRangeIni == null ? null : other.salaryRangeIni.copy();
        this.salaryRangeEnd = other.salaryRangeEnd == null ? null : other.salaryRangeEnd.copy();
        this.gender = other.gender == null ? null : other.gender.copy();
        this.availability = other.availability == null ? null : other.availability.copy();
        this.workplaceCountry = other.workplaceCountry == null ? null : other.workplaceCountry.copy();
        this.workplaceCity = other.workplaceCity == null ? null : other.workplaceCity.copy();
        this.workplaceNeighborhood = other.workplaceNeighborhood == null ? null : other.workplaceNeighborhood.copy();
        this.workplaceStreet = other.workplaceStreet == null ? null : other.workplaceStreet.copy();
        this.workplaceNumber = other.workplaceNumber == null ? null : other.workplaceNumber.copy();
        this.workplaceComplement = other.workplaceComplement == null ? null : other.workplaceComplement.copy();
        this.workplaceZipCode = other.workplaceZipCode == null ? null : other.workplaceZipCode.copy();
        this.workplaceLatitude = other.workplaceLatitude == null ? null : other.workplaceLatitude.copy();
        this.workplaceLongitude = other.workplaceLongitude == null ? null : other.workplaceLongitude.copy();
        this.divulgationIni = other.divulgationIni == null ? null : other.divulgationIni.copy();
        this.divulgationEnd = other.divulgationEnd == null ? null : other.divulgationEnd.copy();
        this.createDt = other.createDt == null ? null : other.createDt.copy();
        this.updateDt = other.updateDt == null ? null : other.updateDt.copy();
    }

    public JobOpportunityCriteria(){}

    @Override
    public JobOpportunityCriteria copy() {
        return new JobOpportunityCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getJoName() {
        return joName;
    }

    public void setJoName(StringFilter joName) {
        this.joName = joName;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public StringFilter getContractType() {
        return contractType;
    }

    public void setContractType(StringFilter contractType) {
        this.contractType = contractType;
    }

    public StringFilter getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(StringFilter workingHours) {
        this.workingHours = workingHours;
    }

    public StringFilter getSalaryRangeIni() {
        return salaryRangeIni;
    }

    public void setSalaryRangeIni(StringFilter salaryRangeIni) {
        this.salaryRangeIni = salaryRangeIni;
    }

    public StringFilter getSalaryRangeEnd() {
        return salaryRangeEnd;
    }

    public void setSalaryRangeEnd(StringFilter salaryRangeEnd) {
        this.salaryRangeEnd = salaryRangeEnd;
    }

    public StringFilter getGender() {
        return gender;
    }

    public void setGender(StringFilter gender) {
        this.gender = gender;
    }

    public StringFilter getAvailability() {
        return availability;
    }

    public void setAvailability(StringFilter availability) {
        this.availability = availability;
    }

    public StringFilter getWorkplaceCountry() {
        return workplaceCountry;
    }

    public void setWorkplaceCountry(StringFilter workplaceCountry) {
        this.workplaceCountry = workplaceCountry;
    }

    public StringFilter getWorkplaceCity() {
        return workplaceCity;
    }

    public void setWorkplaceCity(StringFilter workplaceCity) {
        this.workplaceCity = workplaceCity;
    }

    public StringFilter getWorkplaceNeighborhood() {
        return workplaceNeighborhood;
    }

    public void setWorkplaceNeighborhood(StringFilter workplaceNeighborhood) {
        this.workplaceNeighborhood = workplaceNeighborhood;
    }

    public StringFilter getWorkplaceStreet() {
        return workplaceStreet;
    }

    public void setWorkplaceStreet(StringFilter workplaceStreet) {
        this.workplaceStreet = workplaceStreet;
    }

    public StringFilter getWorkplaceNumber() {
        return workplaceNumber;
    }

    public void setWorkplaceNumber(StringFilter workplaceNumber) {
        this.workplaceNumber = workplaceNumber;
    }

    public StringFilter getWorkplaceComplement() {
        return workplaceComplement;
    }

    public void setWorkplaceComplement(StringFilter workplaceComplement) {
        this.workplaceComplement = workplaceComplement;
    }

    public StringFilter getWorkplaceZipCode() {
        return workplaceZipCode;
    }

    public void setWorkplaceZipCode(StringFilter workplaceZipCode) {
        this.workplaceZipCode = workplaceZipCode;
    }

    public FloatFilter getWorkplaceLatitude() {
        return workplaceLatitude;
    }

    public void setWorkplaceLatitude(FloatFilter workplaceLatitude) {
        this.workplaceLatitude = workplaceLatitude;
    }

    public FloatFilter getWorkplaceLongitude() {
        return workplaceLongitude;
    }

    public void setWorkplaceLongitude(FloatFilter workplaceLongitude) {
        this.workplaceLongitude = workplaceLongitude;
    }

    public StringFilter getDivulgationIni() {
        return divulgationIni;
    }

    public void setDivulgationIni(StringFilter divulgationIni) {
        this.divulgationIni = divulgationIni;
    }

    public StringFilter getDivulgationEnd() {
        return divulgationEnd;
    }

    public void setDivulgationEnd(StringFilter divulgationEnd) {
        this.divulgationEnd = divulgationEnd;
    }

    public StringFilter getCreateDt() {
        return createDt;
    }

    public void setCreateDt(StringFilter createDt) {
        this.createDt = createDt;
    }

    public StringFilter getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(StringFilter updateDt) {
        this.updateDt = updateDt;
    }
}
