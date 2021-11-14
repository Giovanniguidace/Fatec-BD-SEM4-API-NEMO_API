package br.gov.sp.fatec.nemo.usecases.impls;

import br.gov.sp.fatec.nemo.domains.entities.SelectionProcessCandidate;
import br.gov.sp.fatec.nemo.domains.repositories.SelectionProcessCandidateRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@Service
public class ExportSelectionProcessUseCase {

    private static final String[] HEADER = {"ID", "NOME", "EMAIL", "CPF", "TELEFONE", "GENERO", "DATA DE NASCIMENTO", "DISPONIBILIDADE", "PAIS", "CIDADE", "BAIRRO", "RUA", "NUMERO", "COMPLEMENTO", "CEP", "LATITUDE", "LONGITUDE", "JORNADA DESEJADA", "MODALIDADE DE TRABALHO", "PRETENSAO SALARIAL", "STATUS", "OBS"};

    @Autowired
    SelectionProcessCandidateRepository selectionProcessCandidateRepository;

    public ByteArrayInputStream exportByJobId(Long jobId) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), CSVFormat.DEFAULT);
        List<SelectionProcessCandidate> candidates = selectionProcessCandidateRepository.findCandidateInJobOpportunity(jobId);
        csvPrinter.printRecord(Arrays.asList(HEADER));
        for (SelectionProcessCandidate candidate : candidates) {
            List<String> data = Arrays.asList(
                    String.valueOf(candidate.getId()),
                    candidate.getName(),
                    candidate.getEmail(),
                    candidate.getCpf(),
                    candidate.getPhone(),
                    candidate.getGender(),
                    candidate.getBirthday().toString(),
                    candidate.getAvailability(),
                    candidate.getCountry(),
                    candidate.getStreet(),
                    String.valueOf(candidate.getHomeNumber()),
                    candidate.getComplement(),
                    candidate.getZipCode(),
                    String.valueOf(candidate.getLatitude()),
                    String.valueOf(candidate.getLongitude()),
                    candidate.getDesiredJourney(),
                    candidate.getWorkModality(),
                    candidate.getPretensionSalary().toString(),
                    candidate.getStreet(),
                    candidate.getObs()
            );

            csvPrinter.printRecord(data);
        }

        csvPrinter.flush();
        return new ByteArrayInputStream(out.toByteArray());
    }
}
