package br.gov.sp.fatec.nemo.usecases.interfaces;

import br.gov.sp.fatec.nemo.domains.entities.JobOpportunity;
import br.gov.sp.fatec.nemo.usecases.dtos.JobOpportunityCriteria;

import java.util.List;
import java.util.Optional;

public interface FindJobOpportunityUseCase {
    List<JobOpportunity> findJobOpportunity(JobOpportunityCriteria criteria);

    Optional<JobOpportunity> findById(Long id) throws Exception;

    public JobOpportunity save(JobOpportunity jobOpportunity);
}
