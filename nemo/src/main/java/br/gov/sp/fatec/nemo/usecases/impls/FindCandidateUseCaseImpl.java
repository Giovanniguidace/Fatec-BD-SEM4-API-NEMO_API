package br.gov.sp.fatec.nemo.usecases.impls;

import br.gov.sp.fatec.nemo.domains.entities.*;
import br.gov.sp.fatec.nemo.domains.enums.SkillLevel;
import br.gov.sp.fatec.nemo.domains.repositories.CandidateRepository;
import br.gov.sp.fatec.nemo.domains.repositories.interfaces.GeometryCandidate;
import br.gov.sp.fatec.nemo.usecases.impls.dtos.CandidateDTO;
import br.gov.sp.fatec.nemo.usecases.interfaces.FindJobOpportunityUseCase;
import br.gov.sp.fatec.nemo.usecases.interfaces.ParametersService;
import br.gov.sp.fatec.nemo.usecases.services.DistanceMatrixService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FindCandidateUseCaseImpl {


    @PersistenceContext
    private EntityManager em;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private ParametersService parametersService;

    @Autowired
    private FindJobOpportunityUseCase findJobOpportunityUseCase;

    @Autowired
    private DistanceMatrixService distanceMatrixService;




    public List<Candidate> findCandidate(
            String gender,
            String country,
            String city,
            String zipCode,
            String skill,
            Double longitude,
            Double latitude,
            Double kilometers,
            String availablePeriod,
            String course,
            String institution,
            String workModality,
            Double pretensionSalary,
            String desiredJourney,
            String companyName,
            String postName
    ) {
        List<Candidate> candidates = candidateRepository.findCandidateByAnyParams(gender, country, city, zipCode, availablePeriod,
                workModality,
                pretensionSalary,
                desiredJourney,
                companyName,
                postName);
        if (skill != null) {
            List<Long> ids = candidates.stream().map(Candidate::getId).collect(Collectors.toList());
            candidates = candidateRepository.findCandidateBySkillAndId(ids, skill);
        }
        if (institution != null || course != null) {
            List<Long> ids = candidates.stream().map(Candidate::getId).collect(Collectors.toList());
            candidates = candidateRepository.findCandidateByCourseAndInstitutionAndId(ids, course, institution);
        }
        if (latitude != null && longitude != null) {
            List<Long> ids = candidates.stream().map(Candidate::getId).collect(Collectors.toList());
            candidates = candidateRepository.findRadiusCandidate(longitude, latitude, ids, kilometers);
        }
        return candidates;
    }

    @SneakyThrows
    public List<CandidateDTO> findCandidateV2(
        List<String> hability,
        Double longitude,
        Double latitude,
        Double kilometers,
        Long parameter,
        List<SkillLevel> skillLevels
    ) throws Exception {

        Set<Long> listIds = null;
        Set<Candidate> candidates = null;
        Set<GeometryCandidate> geometryCandidates = null;
        if (hability != null) {
            List<String> invalidString = hability.stream()
                .filter(f -> Arrays.stream(f.split("\\.")).count() == 1).collect(Collectors.toList());
            if (invalidString.size() > 0) {
                throw new Exception("Parametro 'hability' está fora do padrão. ||Padrão: Skill.Level||");
            }
            String habilityString = String.join(",", hability);
            listIds = candidateRepository.findCandidateWithoutGeom(habilityString);
            candidates = candidateRepository.findAllById(listIds).stream().collect(Collectors.toSet());

        } else {
            throw new Exception("Habilidades e level são obrigatorios para a pesquisa. ||Padrão: Skill.Level||");
        }
        if (longitude != null && latitude != null && kilometers != null) {
            geometryCandidates = candidateRepository.findRadiusCandidateInterface(
                longitude,
                latitude,
                listIds.stream().collect(Collectors.toList()),
                kilometers
            );
        }
        List<CandidateDTO> classify = null;
        if (parameter == null) {
            classify = classifyCandidate(candidates, hability, geometryCandidates);
        } else {
            classify = classifyCandidateWithParameter(candidates, hability, geometryCandidates, parameter);
        }

        Collections.sort(classify, new SortById());
        return classify;
    }



    private List<CandidateDTO> classifyCandidateWithParameter(
        Set<Candidate> candidates,
        List<String> hability,
        Set<GeometryCandidate> geometryCandidateSet,
        Long parameter
    ) throws Exception {
        Optional<Parameters> parameters = parametersService.findById(parameter);
        if (parameters.isPresent()) {
            return candidates.stream().map((candidate -> {
                Parameters param = parameters.get();
                Integer points = 0;
                CandidateDTO candidateDTO = new CandidateDTO().fromCandidateDTO(candidate);
                if (hability != null) {
                    List<CandidateSkill> skill = candidate.getSkills()
                        .stream()
                        .filter(f -> hability.contains(f.getSkill().getDescription() + "." + f.getSkillLevel())).collect(Collectors.toList());
                    Integer skills = skill.size();
                    points += (skills * param.getHability()) / hability.size();
                } else {
                    points += 50;
                }


                if (geometryCandidateSet != null) {
                    DistanceParameters distanceParameters = param.getDistanceParameters();
                    List<GeometryCandidate> geometryCandidate = geometryCandidateSet
                        .stream().filter(f -> f.getId().equals(candidate.getId()))
                        .collect(Collectors.toList());
                    Double distance = geometryCandidate.get(0).getKilometer();
                    candidateDTO.setDistance(distance);
                    if (distance >= distanceParameters.getStartLowDistance() && distance <= distanceParameters.getEndLowDistance()) {
                        points += distanceParameters.getLowDistanceValue();
                    } else if (distance >= distanceParameters.getStartMediumDistance() && distance <= distanceParameters.getEndMediumDistance()) {
                        points += distanceParameters.getMediumDistanceValue();
                    } else {
                        points += distanceParameters.getValueHighDistance();
                    }


                } else {
                    points += 50;
                }

                candidateDTO.setPoints(points);

                return candidateDTO;
            })).collect(Collectors.toList());
        } else {
            throw new Exception("Parametro com o seguinte id: " + parameter + " não foi encontrado");
        }
    }

    private List<CandidateDTO> classifyCandidate(
        Set<Candidate> candidates, List<String> hability, Set<GeometryCandidate> geometryCandidateSet) {
        return candidates.stream().map(candidate -> {
            Integer points = 0;
            CandidateDTO candidateDTO = new CandidateDTO().fromCandidateDTO(candidate);

            if (hability != null) {

                List<CandidateSkill> skill = candidate.getSkills()
                    .stream()
                    .filter(f -> hability.contains(f.getSkill().getDescription() + "." + f.getSkillLevel())).collect(Collectors.toList());

                Integer skills = skill.size();
                points += (skills * 50) / hability.size();
            } else {
                points += 50;
            }


            if (geometryCandidateSet != null) {
                List<GeometryCandidate> geometryCandidate = geometryCandidateSet
                    .stream().filter(f -> f.getId().equals(candidate.getId()))
                    .collect(Collectors.toList());
                Double distance = geometryCandidate.get(0).getKilometer();
                candidateDTO.setDistance(distance);
                if (distance >= 0 && distance <= 20) {
                    points += 50;
                } else if (distance >= 20 && distance <= 50) {
                    points += 30;
                } else {
                    points += 15;
                }


            } else {
                points += 50;
            }


            candidateDTO.setPoints(points);

            return candidateDTO;
        }).collect(Collectors.toList());
    }


    private Integer buffHability(String hability) {
        Integer value = 0;
        switch (hability) {
            case "FIVE":
                value = 5;
                break;
            case "FOUR":
                value = 4;
                break;
            case "THREE":
                value = 3;
                break;
            case "TWO":
                value = 2;
                break;
            case "ONE":
                value = 1;
                break;
        }
        return value;
    }


    public void processDirections(Long idWork, Long idCandidate) throws Exception {
        Optional<JobOpportunity> jobOpportunity = findJobOpportunityUseCase.findById(idWork);
        Optional<Candidate> candidateOptional = candidateRepository.findById(idCandidate);

        if (jobOpportunity.isPresent() && candidateOptional.isPresent()){
            distanceMatrixService.processDirections(candidateOptional.get(), jobOpportunity.get());
        } else {
            throw new Exception("Dados inexistente");
        }

    };


}

class SortById implements Comparator<CandidateDTO> {
    public int compare(CandidateDTO candidateDTO, CandidateDTO candidateDTO2) {
        return (int) candidateDTO2.getPoints() - (int) candidateDTO.getPoints();
    }
}
