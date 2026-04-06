package org.example.intense.service;

import org.example.intense.model.Skill;
import org.example.intense.repository.SkillRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    // dodavanje skila
    public Skill addSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    // svi skilovi
    public List<Skill> allSkils() {
        return skillRepository.findAll();
    }

}
