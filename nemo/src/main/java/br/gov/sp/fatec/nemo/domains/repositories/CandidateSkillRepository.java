package br.gov.sp.fatec.nemo.domains.repositories;

import br.gov.sp.fatec.nemo.domains.entities.CandidateSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, Long> {

}
