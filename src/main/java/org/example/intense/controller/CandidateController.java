package org.example.intense.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.intense.model.Candidate;
import org.example.intense.service.CandidateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    // svi kandidati
    @Operation(summary = "Get all candidates", description = "Returns a list of all job candidates")
    @GetMapping
    public List<Candidate> getAllCandidates() {
        return candidateService.getAllCandidates();
    }

    // dodavanje kandidata
    @Operation(summary = "Create candidate", description = "Adds a new job candidate")
    @PostMapping
    public Candidate addCandidate(@RequestBody Candidate candidate) {
        return candidateService.addCandidate(candidate);
    }

    // apdejtovanje kandidata
    @Operation(summary = "Update candidate", description = "Updates existing candidate information")
    @PutMapping("/{id}")
    public Candidate updateCandidate(@PathVariable Long id, @RequestBody Candidate candidate) {
        return candidateService.updateCandidate(id, candidate);
    }

    // brisanje kandidata
    @Operation(summary = "Delete candidate", description = "Removes candidate by ID")
    @DeleteMapping("/{id}")
    public void deleteCandidate(@PathVariable Long id) {
        candidateService.removeCandidate(id);
    }

    // dodavanje skila kandidatu
    @Operation(summary = "Add skill to candidate", description = "Assigns an existing skill to a candidate")
    @PostMapping("/{candidateId}/skills/{skillId}")
    public Candidate addSkillToCandidate(@PathVariable Long candidateId, @PathVariable Long skillId) {
        return candidateService.addSkillToCandidate(candidateId, skillId);
    }

    // brisanje skila kandidatu
    @Operation(summary = "Remove skill from candidate", description = "Removes a skill from a candidate")
    @DeleteMapping("/{candidateId}/skills/{skillId}")
    public Candidate removeSkillFromCandidate(@PathVariable Long candidateId, @PathVariable Long skillId) {
        return candidateService.removeSkillFromCandidate(candidateId, skillId);
    }

    // pretraga po imenu
    @Operation(summary = "Search candidates by name", description = "Finds candidates whose name contains given text")
    @GetMapping("/search")
    public List<Candidate> searchByName(@RequestParam String name) {
        return candidateService.searchByName(name);
    }

    // pretraga po skilovima
    @Operation(summary = "Search candidates by skills", description = "Finds candidates that have all given skills")
    @GetMapping("/search/skills")
    public List<Candidate> searchBySkill(@RequestParam List<String> skillNames) {
        return candidateService.searchBySkill(skillNames);
    }
}

















