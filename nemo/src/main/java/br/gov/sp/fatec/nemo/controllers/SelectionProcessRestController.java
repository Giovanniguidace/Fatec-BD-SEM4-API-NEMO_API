package br.gov.sp.fatec.nemo.controllers;

import br.gov.sp.fatec.nemo.controllers.dtos.SelectionProcessCandidateDTO;
import br.gov.sp.fatec.nemo.usecases.impls.ExportSelectionProcessUseCase;
import br.gov.sp.fatec.nemo.usecases.impls.FindSelectionProcessCandidateUseCaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class SelectionProcessRestController {

    @Autowired
    ExportSelectionProcessUseCase exportSelectionProcessUseCase;

    @Autowired
    private FindSelectionProcessCandidateUseCaseImpl findSelectionProcessCandidateUseCase;

    @GetMapping(value = "nemo/v1/selection/jobOpportunity/{id}", produces = "application/json")
    public ResponseEntity<SelectionProcessCandidateDTO> getSelectionProcessByJobOpportunity(@PathVariable("id") Long jobOpportunityId) {
        return Optional
                .ofNullable(findSelectionProcessCandidateUseCase.findSelectionProcessCandidateByJobId(jobOpportunityId))
                .map(selection -> ResponseEntity.ok().body(selection))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "nemo/v1/selection/jobOpportunity/{id}/download")
    public ResponseEntity<Resource> downloadSelectionProcessByJobOpportunity(@PathVariable("id") Long jobOpportunityId) throws IOException {
        String filename = LocalDateTime.now().toString() + "_candidatos_vaga_" + jobOpportunityId.toString() + ".csv";
        InputStreamResource file = new InputStreamResource(exportSelectionProcessUseCase.exportByJobId(jobOpportunityId));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

}
