package br.gov.sp.fatec.nemo.controllers;

import br.gov.sp.fatec.nemo.domains.entities.JobOpportunity;
import br.gov.sp.fatec.nemo.usecases.dtos.JobOpportunityCriteria;
import br.gov.sp.fatec.nemo.usecases.interfaces.FindJobOpportunityUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class JobOpportunityRestController {

    @Autowired
    private FindJobOpportunityUseCase findJobOpportunityUseCase;

    @GetMapping(value = "nemo/v1/jobopportunity", produces = "application/json")
    public ResponseEntity<List<JobOpportunity>> getJobOpportunity(JobOpportunityCriteria criteria) {
        return Optional
            .ofNullable(findJobOpportunityUseCase.findJobOpportunity(criteria))
            .map(jobopportunity -> ResponseEntity.ok().body(jobopportunity))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "nemo/v1/jobopportunity")
    public ResponseEntity<JobOpportunity> postJobOpportunity(@RequestBody JobOpportunity jobOpportunity){
        return Optional
            .ofNullable(findJobOpportunityUseCase.save(jobOpportunity))
            .map(jobopportunity -> ResponseEntity.ok().body(jobopportunity))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }


}

