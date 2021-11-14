package br.gov.sp.fatec.nemo.domains.repositories;

import br.gov.sp.fatec.nemo.domains.entities.DistanceMatrix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DistanceMatrixRepository extends JpaRepository <DistanceMatrix, Long> {
    @Query(value = "select * from distance_matrix where can_id = ?1 and jo_id = ?2", nativeQuery = true)
    Optional<DistanceMatrix> findByCandidateIdAndJobOpportunityId(Long cadidate, Long id);
}
