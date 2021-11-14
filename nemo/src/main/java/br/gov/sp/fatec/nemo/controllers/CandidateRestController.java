package br.gov.sp.fatec.nemo.controllers;

import br.gov.sp.fatec.nemo.controllers.dtos.CandidateRequest;
import br.gov.sp.fatec.nemo.domains.entities.Candidate;
import br.gov.sp.fatec.nemo.domains.enums.SkillLevel;
import br.gov.sp.fatec.nemo.usecases.impls.CreateCandidateUseCaseImpl;
import br.gov.sp.fatec.nemo.usecases.impls.DeleteCandidateUseCaseImpl;
import br.gov.sp.fatec.nemo.usecases.impls.FindCandidateUseCaseImpl;
import br.gov.sp.fatec.nemo.usecases.impls.dtos.CandidateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CandidateRestController {

    @Autowired
    private FindCandidateUseCaseImpl findCandidateUseCase;

    @Autowired
    private CreateCandidateUseCaseImpl createCandidateUseCase;

    @Autowired
    private DeleteCandidateUseCaseImpl deleteCandidateUseCase;

    @GetMapping(value = "nemo/v1/candidate", produces = "application/json")
    public ResponseEntity<List<Candidate>> getCandidate(
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String zipCode,
            @RequestParam(required = false) String skill,
            @RequestParam(required = false) Double longitude,
            @RequestParam(required = false) Double latitude,
            @RequestParam(required = false) Double kilometers,
            @RequestParam(required = false) String availablePeriod,
            @RequestParam(required = false) String course,
            @RequestParam(required = false) String institution,
            @RequestParam(required = false) String workModality,
            @RequestParam(required = false) Double pretensionSalary,
            @RequestParam(required = false) String desiredJourney,
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) String postName
    ) {
        return Optional
                .ofNullable(findCandidateUseCase.findCandidate(gender, country, city, zipCode, skill, longitude, latitude, kilometers, availablePeriod, course, institution, workModality, pretensionSalary, desiredJourney,
                        companyName, postName))
                .map(candidate -> ResponseEntity.ok().body(candidate))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "nemo/v2/candidate", produces = "application/json")
    public ResponseEntity<List<CandidateDTO>> getCandidateV2(
        @RequestParam(required = false) List<String> hability,
        @RequestParam(required = false) Double longitude,
        @RequestParam(required = false) Double latitude,
        @RequestParam(required = false) Double kilometers,
        @RequestParam(required = false) Long idParameter,
        @RequestParam(required = false) List<SkillLevel> skillLevels

    ) throws Exception {
        return Optional
            .ofNullable(findCandidateUseCase
                .findCandidateV2(hability, longitude, latitude, kilometers, idParameter, skillLevels))
            .map(candidateDTOS -> ResponseEntity.ok().body(candidateDTOS))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "nemo/directions/{idWork}/{idCandidate}", produces = "application/json")
    public void getDirections(@PathVariable("idWork") Long id, @PathVariable("idCandidate") Long idCandidate)
        throws Exception {
        findCandidateUseCase.processDirections(id, idCandidate);
    }

    @PostMapping("nemo/v1/candidate/")
    public ResponseEntity<Candidate> criarCandidate(@RequestBody CandidateRequest candidate) {
        try {
            return ResponseEntity.ok().body(createCandidateUseCase.createCandidate(candidate));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("nemo/v1/candidate/{id}")
    public ResponseEntity<String> deleteCandidate(@PathVariable("id") Long id) {
        try {
            deleteCandidateUseCase.deleteCandidateById(id);
            return ResponseEntity.ok("Candidato deletado com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
