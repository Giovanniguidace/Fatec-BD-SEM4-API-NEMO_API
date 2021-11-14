package br.gov.sp.fatec.nemo.usecases.impls;
import br.gov.sp.fatec.nemo.domains.entities.JobOpportunity;
import br.gov.sp.fatec.nemo.domains.entities.JobOpportunity_;
import br.gov.sp.fatec.nemo.domains.repositories.JobOpportunityRepository;
import br.gov.sp.fatec.nemo.usecases.dtos.JobOpportunityCriteria;
import br.gov.sp.fatec.nemo.usecases.interfaces.FindJobOpportunityUseCase;
import io.github.jhipster.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


@Service

@Transactional(readOnly = false)
public class FindJobOpportunityUseCaseImpl extends QueryService<JobOpportunity> implements FindJobOpportunityUseCase {

    @Autowired
    private JobOpportunityRepository jobOpportunityRepository;

    @Override
    public List<JobOpportunity> findJobOpportunity(
        JobOpportunityCriteria criteria
    ) {
        Specification<JobOpportunity> specification = createSpecification(criteria);
        return jobOpportunityRepository.findAll(specification);
    }

    public Optional<JobOpportunity> findById(Long id) throws Exception {
        return jobOpportunityRepository.findById(id);
    }

    public JobOpportunity save(JobOpportunity jobOpportunity){
        return jobOpportunityRepository.save(jobOpportunity);
    }


    public JobOpportunity update(JobOpportunity jobOpportunity) throws Exception {
        if (jobOpportunity.getId() != null){
            Optional<JobOpportunity> jobOpportunityToSave = jobOpportunityRepository
                .findById(jobOpportunity.getId());
            if (jobOpportunityToSave.isPresent()){
                Long idToSave = jobOpportunityToSave.get().getId();
                jobOpportunity.setId(idToSave);
                return jobOpportunityRepository.save(jobOpportunity);
            } else {
                throw new Exception("Oportunity not found");
            }
        }
        return null;
    }

    private Specification<JobOpportunity> createSpecification(JobOpportunityCriteria criteria) {
        Specification<JobOpportunity> specification = Specification.where(null);
        if (criteria.getId() != null) {
            specification = specification.and(buildSpecification(criteria.getId(), JobOpportunity_.id));
        }
        if (criteria.getJoName() != null) {
            specification = specification.and(buildSpecification(criteria.getJoName(), JobOpportunity_.joName));
        }
        if (criteria.getDescription() != null) {
            specification = specification.and(buildSpecification(criteria.getDescription(), JobOpportunity_.description));
        }
        if (criteria.getContractType() != null) {
            specification = specification.and(buildSpecification(criteria.getDescription(), JobOpportunity_.contractType));
        }
        if (criteria.getGender() != null) {
            specification = specification.and(buildSpecification(criteria.getGender(), JobOpportunity_.gender));
        }
        if (criteria.getWorkplaceCity() != null) {
            specification = specification.and(buildSpecification(criteria.getWorkplaceCity(), JobOpportunity_.workplaceCity));
        }
        if (criteria.getWorkplaceNeighborhood() != null) {
            specification = specification.and(buildSpecification(criteria.getWorkplaceNeighborhood(), JobOpportunity_.workplaceNeighborhood));
        }
        if (criteria.getWorkplaceStreet() != null) {
            specification = specification.and(buildSpecification(criteria.getWorkplaceStreet(), JobOpportunity_.workplaceStreet));
        }

        return specification;
    }




}

