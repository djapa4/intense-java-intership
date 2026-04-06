package org.example.intense.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.intense.model.Skill;
import org.example.intense.service.SkillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    // svi skilovi
    @Operation(summary = "Get all skills", description = "Returns a list of all skills")
    @GetMapping
    public List<Skill> getAllSkils() {
        return skillService.allSkils();
    }

    // dodavanje skila
    @Operation(summary = "Create skill", description = "Adds a new skill")
    @PostMapping
    public Skill addSkill(@RequestBody Skill skill) {
        return skillService.addSkill(skill);
    }

}
