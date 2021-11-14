package br.gov.sp.fatec.nemo.domains.repositories;

import br.gov.sp.fatec.nemo.domains.entities.LegsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LegsRepository extends JpaRepository<LegsEntity, Long> {
}
