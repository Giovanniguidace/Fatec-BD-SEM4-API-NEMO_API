package br.gov.sp.fatec.nemo.domains.repositories;

import br.gov.sp.fatec.nemo.domains.entities.JobOpportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobOpportunityRepository
    extends JpaRepository<JobOpportunity, Long>, JpaSpecificationExecutor<JobOpportunity> {

    @Query(value = "SELECT * FROM job_opportunity", nativeQuery = true )
    List<JobOpportunity> findJobOpportunityByAllParams();

}
