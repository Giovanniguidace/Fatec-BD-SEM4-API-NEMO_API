package br.gov.sp.fatec.nemo.usecases.impls;

import br.gov.sp.fatec.nemo.domains.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteCandidateUseCaseImpl {
    @Autowired
    private CandidateRepository candidateRepository;

    public void deleteCandidateById(Long id) {
        candidateRepository.deleteById(id);
    }
}
