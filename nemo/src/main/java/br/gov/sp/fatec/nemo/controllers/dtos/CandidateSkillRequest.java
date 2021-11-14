package br.gov.sp.fatec.nemo.controllers.dtos;

import br.gov.sp.fatec.nemo.domains.enums.SkillLevel;
import lombok.Data;

import java.io.Serializable;

@Data
public class CandidateSkillRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    private String skill;
    private SkillLevel skillLevel;
}
