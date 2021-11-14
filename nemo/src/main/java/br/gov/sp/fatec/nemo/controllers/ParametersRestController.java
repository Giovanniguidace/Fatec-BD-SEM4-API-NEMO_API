package br.gov.sp.fatec.nemo.controllers;

import br.gov.sp.fatec.nemo.domains.entities.Parameters;
import br.gov.sp.fatec.nemo.usecases.interfaces.ParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ParametersRestController {

    @Autowired
    private ParametersService parametersService;

    @PostMapping(value = "nemo/v1/parameters")
    public ResponseEntity<Parameters> saveParameter(
        @RequestBody Parameters parameters
    ) {
        Parameters parameterSave = parametersService.saveParameter(parameters);
        return ResponseEntity.ok().body(parameters);
    }

    @GetMapping(value = "nemo/v1/parameters")
    public ResponseEntity<List<Parameters>> listAll () {
        return ResponseEntity.ok().body(parametersService.listAll());
    }

    @GetMapping(value = "nemo/v1/parameters/{id}")
    public ResponseEntity<Parameters> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(parametersService.findById(id).get());
    }

    @PutMapping(value = "nemo/v1/parameters")
    public ResponseEntity<Parameters> update(@RequestBody Parameters parameters) throws Exception {
        return ResponseEntity.ok().body(parametersService.update(parameters));
    }

    @DeleteMapping(value = "nemo/v1/parameters/{id}")
    public void delete(@PathVariable("id") Long id) throws Exception {
        parametersService.delete(id);
    }


}
