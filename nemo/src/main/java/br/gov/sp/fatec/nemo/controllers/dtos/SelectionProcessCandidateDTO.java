package br.gov.sp.fatec.nemo.controllers.dtos;

import br.gov.sp.fatec.nemo.domains.entities.JobOpportunity;
import br.gov.sp.fatec.nemo.domains.entities.SelectionProcessCandidate;

import java.io.Serializable;
import java.util.List;

public class SelectionProcessCandidateDTO implements Serializable {
    private List<SelectionProcessCandidate> selectionProcessCandidateList;
    private JobOpportunity jobOpportunity;

    public SelectionProcessCandidateDTO(List<SelectionProcessCandidate> selectionProcessCandidateList, JobOpportunity jobOpportunity) {
        this.selectionProcessCandidateList = selectionProcessCandidateList;
        this.jobOpportunity = jobOpportunity;
    }

    public List<SelectionProcessCandidate> getSelectionProcessCandidateList() {
        return selectionProcessCandidateList;
    }

    public void setSelectionProcessCandidateList(List<SelectionProcessCandidate> selectionProcessCandidateList) {
        this.selectionProcessCandidateList = selectionProcessCandidateList;
    }

    public JobOpportunity getJobOpportunity() {
        return jobOpportunity;
    }

    public void setJobOpportunity(JobOpportunity jobOpportunity) {
        this.jobOpportunity = jobOpportunity;
    }
}
