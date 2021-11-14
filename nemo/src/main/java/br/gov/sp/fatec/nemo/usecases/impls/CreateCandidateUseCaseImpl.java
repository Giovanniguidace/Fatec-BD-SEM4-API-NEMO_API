package br.gov.sp.fatec.nemo.usecases.impls;

import br.gov.sp.fatec.nemo.controllers.dtos.CandidateExpRequest;
import br.gov.sp.fatec.nemo.controllers.dtos.CandidateFormationRequest;
import br.gov.sp.fatec.nemo.controllers.dtos.CandidateRequest;
import br.gov.sp.fatec.nemo.controllers.dtos.CandidateSkillRequest;
import br.gov.sp.fatec.nemo.domains.entities.*;
import br.gov.sp.fatec.nemo.domains.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class CreateCandidateUseCaseImpl {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private CourseRepository courseRepository;

    public Candidate createCandidate(CandidateRequest request) {
        Candidate newCandidate = request.toCandidate();
        for (CandidateExpRequest exp : request.getExperiences()) {
            CandidateExp newExp = new CandidateExp();
            Company newCompany = companyRepository.findByName(exp.getCompany());
            if (newCompany == null) {
                newCompany = new Company();
                newCompany.setName(exp.getCompany());
                companyRepository.save(newCompany);
            }
            newExp.setCompany(newCompany);
            Post newPost = postRepository.findByName(exp.getPost());
            if (newPost == null) {
                newPost = new Post();
                newPost.setName(exp.getPost());
                postRepository.save(newPost);
            }
            newExp.setPost(newPost);
            newExp.setDtEnd(exp.getDtEnd());
            newExp.setDescription(exp.getDescription());
            newExp.setDtStart(exp.getDtStart());
            newExp.setCandidate(newCandidate);
            newCandidate.getExperiences().add(newExp);
        }
        for (CandidateFormationRequest form : request.getFormations()) {
            CandidateFormation newForm = new CandidateFormation();
            Institution newInst = institutionRepository.findByName(form.getInstitution());
            if (newInst == null) {
                newInst = new Institution();
                newInst.setName(form.getInstitution());
                institutionRepository.save(newInst);
            }
            newForm.setInstitution(newInst);
            Course newCourse = courseRepository.findByName(form.getCourse());
            if (newCourse == null) {
                newCourse = new Course();
                newCourse.setName(form.getCourse());
                courseRepository.save(newCourse);
            }
            newForm.setCourse(newCourse);
            newForm.setDtEnd(form.getDtEnd());
            newForm.setDtStart(form.getDtStart());
            newForm.setCandidate(newCandidate);
            newCandidate.getFormations().add(newForm);
        }
        for (CandidateSkillRequest skill : request.getSkills()) {
            CandidateSkill newCandidateSkill = new CandidateSkill();
            Skill newSkill = skillRepository.findByDescription(skill.getSkill());
            if (newSkill == null) {
                newSkill = new Skill();
                newSkill.setDescription(skill.getSkill());
                skillRepository.save(newSkill);
            }
            newCandidateSkill.setSkill(newSkill);
            newCandidateSkill.setSkillLevel(skill.getSkillLevel());
            newCandidateSkill.setCandidate(newCandidate);
            newCandidate.getSkills().add(newCandidateSkill);
        }
        return candidateRepository.save(newCandidate);
    }
}
