package br.gov.sp.fatec.nemo.domains.repositories;

import br.gov.sp.fatec.nemo.domains.entities.Candidate;
import br.gov.sp.fatec.nemo.domains.enums.SkillLevel;
import br.gov.sp.fatec.nemo.domains.repositories.interfaces.GeometryCandidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    @Query("SELECT DISTINCT c FROM Candidate c " +
            "INNER JOIN FETCH c.experiences e " +
            "WHERE " +
            "(:gender is null or c.gender = :gender) and " +
            "(:country is null or c.country = :country) and " +
            "(:city is null or c.city = :city) and " +
            "(:zip_code is null or c.zipCode = :zip_code) and " +
            "(:availablePeriod is null or c.availablePeriod.name = :availablePeriod) and " +
            "(:workModality is null or c.workModality.name = :workModality) and " +
            "(:desiredJourney is null or c.desiredJourney.name = :desiredJourney) and " +
            "(:pretensionSalary is null or c.pretensionSalary <= :pretensionSalary) and " +
            "(:companyName is null or e.company.name = :companyName) and " +
            "(:postName is null or e.post.name = :postName)"
    )
    List<Candidate> findCandidateByAnyParams(
            @Param("gender") String gender,
            @Param("country") String country,
            @Param("city") String city,
            @Param("zip_code") String zipCode,
            @Param("availablePeriod") String availablePeriod,
            @Param("workModality") String workModality,
            @Param("pretensionSalary") Double pretensionSalary,
            @Param("desiredJourney") String desiredJourney,
            @Param("companyName") String companyName,
            @Param("postName") String postName
    );

    @Query("SELECT c FROM Candidate c " +
            "INNER JOIN FETCH c.formations f " +
            "WHERE " +
            "(id in :ids) and " +
            "(:course is null or f.course.name = :course) and " +
            "(:institution is null or f.institution.name = :institution) "
    )
    List<Candidate> findCandidateByCourseAndInstitutionAndId(
            @Param("ids") List<Long> ids,
            @Param("course") String course,
            @Param("institution") String institution
    );

    @Query("SELECT c FROM Candidate c " +
            "INNER JOIN FETCH c.skills s " +
            "INNER JOIN FETCH s.skill sk " +
            "WHERE " +
            "(id in :ids) and " +
            "(sk.description = :skill) "
    )
    List<Candidate> findCandidateBySkillAndId(
            @Param("ids") List<Long> ids,
            @Param("skill") String skill
    );

    @Query(value = "SELECT DISTINCT * FROM candidate where can_id in (:ids) and ST_Distance_Sphere(geom, ST_MakePoint(:longitude,:latitude))/1000 <= :kilometers",
            nativeQuery = true)
    List<Candidate> findRadiusCandidate(
            @Param("longitude") Double longitude,
            @Param("latitude") Double latitude,
            @Param("ids") List<Long> ids,
            @Param("kilometers") Double kilometers
    );

    @Query(value = "SELECT can_id as id, ST_Distance_Sphere(geom, ST_MakePoint(:longitude,:latitude))/1000 as kilometer  FROM candidate where can_id in (:ids) and ST_Distance_Sphere(geom, ST_MakePoint(:longitude,:latitude))/1000 <= :kilometers",
        nativeQuery = true )
    Set<GeometryCandidate> findRadiusCandidateInterface(
        @Param("longitude") Double longitude,
        @Param("latitude") Double latitude,
        @Param("ids") List<Long> ids,
        @Param("kilometers") Double kilometers
    );

    Set<Candidate> findAllBySkills_Skill_DescriptionIn(List<String> skills);

    Set<Candidate> findAllBySkills_Skill_DescriptionInAndSkills_SkillLevelIn(List<String> skills, List<SkillLevel> skillLevels);

    @Query(value = "SELECT * from searchCandidate(array[:skills])", nativeQuery = true)
    Set<Long> findCandidateWithoutGeom(
        @Param("skills") String skills
    );

//    @Procedure(procedureName = "searchcandidate")
//    Object[] findCandidateWithoutGeom(
//        @Param("habilit_experience") List<String> skills,
//        @Param("longitudePar") Double longitude,
//        @Param("latitudePar") Double latitude,
//        @Param("distanceLimit") Double kilometers
//    );

}
