package br.gov.sp.fatec.nemo.usecases.impls;

import br.gov.sp.fatec.nemo.controllers.dtos.SelectionProcessCandidateDTO;
import br.gov.sp.fatec.nemo.domains.entities.JobOpportunity;
import br.gov.sp.fatec.nemo.domains.entities.SelectionProcessCandidate;
import br.gov.sp.fatec.nemo.domains.repositories.JobOpportunityRepository;
import br.gov.sp.fatec.nemo.domains.repositories.SelectionProcessCandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FindSelectionProcessCandidateUseCaseImpl {

    @Autowired
    private SelectionProcessCandidateRepository selectionProcessCandidateRepository;

    @Autowired
    private JobOpportunityRepository jobOpportunityRepository;

    public SelectionProcessCandidateDTO findSelectionProcessCandidateByJobId(Long jobId) {
        List<SelectionProcessCandidate> selectionProcessCandidateList = selectionProcessCandidateRepository.findCandidateInJobOpportunity(jobId);
        Optional<JobOpportunity> jobOpportunity = jobOpportunityRepository.findById(jobId);
        return jobOpportunity.map(opportunity -> new SelectionProcessCandidateDTO(selectionProcessCandidateList, opportunity)).orElse(null);
    }
}
