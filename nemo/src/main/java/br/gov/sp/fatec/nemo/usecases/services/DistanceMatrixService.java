package br.gov.sp.fatec.nemo.usecases.services;

import br.gov.sp.fatec.nemo.client.DirectionsClient;
import br.gov.sp.fatec.nemo.domains.entities.*;
import br.gov.sp.fatec.nemo.domains.entities.feingentities.response.DirectionsResponse;
import br.gov.sp.fatec.nemo.domains.entities.feingentities.response.Legs;
import br.gov.sp.fatec.nemo.domains.repositories.DistanceMatrixRepository;
import br.gov.sp.fatec.nemo.domains.repositories.LegsRepository;
import br.gov.sp.fatec.nemo.domains.repositories.StepsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class DistanceMatrixService {
    @Value( "${application.env.apikey}" )
    private static final String API_KEY = "AIzaSyCAD6BK9korOVuncaPn-a5ZhT9btRq2NO0";

    @Autowired
    private DirectionsClient directionsClient;

    @Autowired
    private DistanceMatrixRepository distanceMatrixRepository;

    @Autowired
    private LegsRepository legsRepository;

    @Autowired
    private StepsRepository stepsRepository;

    public void processDirections(Candidate candidateOptional, JobOpportunity jobOpportunity) throws Exception {
        String candidateAddress = null;
        String jobAddress = null;

            Candidate candidate = candidateOptional;
            candidateAddress = candidate.getStreet() + " + " + candidate.getNeighborhood() + "+" + candidate.getCity();
            jobAddress = jobOpportunity.getWorkplaceStreet() + " + " + jobOpportunity.getWorkplaceNeighborhood() +
                "+" + jobOpportunity.getWorkplaceCity();

            String key = API_KEY;
            String mode = "transit";

            ResponseEntity<DirectionsResponse> directionsResponse = directionsClient
                .getDirections(candidateAddress, jobAddress, key, mode);
            if (directionsResponse.getBody() != null) {
                if (directionsResponse.getBody().getRoutes().size() > 0) {
                    saveDate(directionsResponse.getBody(), candidate, jobOpportunity);
                } else {
                    throw new Exception("Não foram encontradas nenhuma rota");
                }

            }


    }

    @Transactional
    public void saveDate(DirectionsResponse directionsResponse, Candidate candidate, JobOpportunity jobOpportunity) {
        if (!distanceMatrixRepository.findByCandidateIdAndJobOpportunityId(candidate.getId(), jobOpportunity.getId()).isPresent()) {
            DistanceMatrix distanceMatrix = new DistanceMatrix();
            distanceMatrix.setStartAddress(directionsResponse.getRoutes().get(0).getLegs().get(0).getStartAddresss());
            distanceMatrix.setEndAddress(directionsResponse.getRoutes().get(0).getLegs().get(0).getEndAddresss());
            distanceMatrix.setCandidate(candidate);
            distanceMatrix.setJobOpportunity(jobOpportunity);

            distanceMatrix
                .setTotalDistance(
                    calculateTotalDistance(
                        directionsResponse.getRoutes().get(0).getLegs())
                );

            distanceMatrix =  distanceMatrixRepository.saveAndFlush(distanceMatrix);
            distanceMatrix.setLegs(setLegsToEntity(directionsResponse.getRoutes().get(0).getLegs(), distanceMatrix));
        }

    }

    private List<LegsEntity> setLegsToEntity(List<Legs> legs, DistanceMatrix distanceMatrix) {
        List<LegsEntity> legsEntities = new ArrayList<>();


        legs.forEach(leg -> {
            LegsEntity legsEntity = new LegsEntity();
            legsEntity.setStartAddress(leg.getStartAddresss());
            legsEntity.setEndAddress(leg.getEndAddresss());
            legsEntity.setDistance(leg.getDistance().getValue().floatValue() /1000);
            legsEntity.setDuration(leg.getDuration().getValue().floatValue() /60);
            legsEntity.setDistanceMatrix(distanceMatrix);

            legsEntity =  legsRepository.save(legsEntity);
            saveLegs(leg,legsEntity);
            legsEntities.add(legsEntity);

        });


        return legsEntities;

    }

    private void saveLegs(Legs legs, LegsEntity legsEntity) {
        List<Steps> stepsList = new ArrayList<>();
        legs.getSteps().forEach(step -> {
            Steps steps = new Steps();
            steps.setDistance(step.getDistance().getValue().floatValue() /1000);
            steps.setDuration(step.getDuration().getValue().floatValue() / 60);
            steps.setHtmlInstruction(step.getHtmlInstruction());
            steps.setLegsEntity(legsEntity);
            steps.setTravelMode(step.getTravelMode());
            stepsList.add(steps);
        });

        stepsRepository.saveAll(stepsList);
    }

    private Float calculateTotalDistance(List<Legs> legs){
        AtomicReference<Long> totalDistance = new AtomicReference<>(0L);
        legs.forEach(leg -> {
            totalDistance.updateAndGet(v -> v + leg.getDistance().getValue());
        });
        return totalDistance.get().floatValue() / 1000;
    }
}
