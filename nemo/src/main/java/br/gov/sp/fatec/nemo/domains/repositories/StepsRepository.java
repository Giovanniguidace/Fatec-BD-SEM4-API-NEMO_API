package br.gov.sp.fatec.nemo.domains.repositories;

import br.gov.sp.fatec.nemo.domains.entities.Steps;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StepsRepository extends JpaRepository<Steps, Long> {
}
