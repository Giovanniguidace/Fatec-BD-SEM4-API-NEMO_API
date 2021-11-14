package br.gov.sp.fatec.nemo.usecases.impls;

import br.gov.sp.fatec.nemo.domains.entities.Parameters;
import br.gov.sp.fatec.nemo.domains.repositories.ParameterRepository;
import br.gov.sp.fatec.nemo.usecases.interfaces.ParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ParametersServiceImpl implements ParametersService {
    @Autowired
    ParameterRepository parameterRepository;


    public Parameters saveParameter(Parameters parameters){

        return parameterRepository.saveAndFlush(parameters);
    }

    public Optional<Parameters> findById(Long id){
        return parameterRepository.findById(id);
    }

    public List<Parameters> listAll() {
        return parameterRepository.findAll();
    }

    public Parameters update(Parameters parametersToSave) throws Exception {
        if (parametersToSave.getId() != null) {
            Optional<Parameters> parameters = parameterRepository.findById(parametersToSave.getId());
            if (parameters.isPresent()) {
                parametersToSave.setId(parameters.get().getId());
                return parameterRepository.saveAndFlush(parametersToSave);
            }
        } else {
            throw new Exception("Parametro não encontrado");
        }
        return null;
    }

    public void delete(Long id) throws Exception {
        if (id != null) {
            parameterRepository.deleteById(id);
        }
        else {
            throw new Exception("Parametro não encontrado");
        }
    }

}
