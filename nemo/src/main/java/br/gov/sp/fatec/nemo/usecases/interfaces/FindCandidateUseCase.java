package br.gov.sp.fatec.nemo.usecases.interfaces;

import br.gov.sp.fatec.nemo.domains.entities.Candidate;
import br.gov.sp.fatec.nemo.domains.enums.SkillLevel;
import br.gov.sp.fatec.nemo.usecases.impls.dtos.CandidateDTO;

import java.util.List;

public interface FindCandidateUseCase {
    List<Candidate> findCandidate(
        String gender,
        String country,
        String city,
        String zipCode,
        String skill,
        Double longitude,
        Double latitude,
        Double kilometers);

    List<CandidateDTO> findCandidateV2(
        List<String> hability,
        Double longitude,
        Double latitude,
        Double kilometers,
        Long parameter,
        List<SkillLevel> skillLevels
    ) throws Exception;

    void processDirections(Long idWork, Long idCandidate) throws Exception;
}
