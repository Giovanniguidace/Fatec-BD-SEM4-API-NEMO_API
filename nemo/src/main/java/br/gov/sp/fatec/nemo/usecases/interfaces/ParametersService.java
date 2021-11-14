package br.gov.sp.fatec.nemo.usecases.interfaces;

import br.gov.sp.fatec.nemo.domains.entities.Parameters;

import java.util.List;
import java.util.Optional;

public interface ParametersService {
    public Parameters saveParameter(Parameters parameters);

    public Optional<Parameters> findById(Long id);

    public List<Parameters> listAll();

    public Parameters update(Parameters parametersToSave) throws Exception;

    public void delete(Long id) throws Exception;
}
