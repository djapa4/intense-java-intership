package org.example.intense.service;

import org.example.intense.exception.ResourceNotFound;
import org.example.intense.model.Candidate;
import org.example.intense.model.Skill;
import org.example.intense.repository.CandidateRepository;
import org.example.intense.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final SkillRepository skillRepository;

    public  CandidateService(CandidateRepository candidateRepository, SkillRepository skillRepository) {
        this.candidateRepository = candidateRepository;
        this.skillRepository = skillRepository;
    }

    // dodavanje kandidata
    public Candidate addCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    // dodavanje skila kandidatu
    public Candidate addSkillToCandidate(Long candidateId, Long skillId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Kandidat nije pronadjen."));
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skil nije pronadjen."));
        candidate.getSkills().add(skill);
        return  candidateRepository.save(candidate);
    }

    // uklanjanje skila kandidatu
    public Candidate removeSkillFromCandidate(Long candidateId, Long skillId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Kandidat nije pronadjen."));
        boolean skillExist = false;
        for (Skill skill : candidate.getSkills()) {
            if (skill.getId().equals(skillId)) {
                skillExist = true;
                break;
            }
        }

        if (!skillExist) {
            throw new RuntimeException("Kandidat nema taj skill.");
        }

        candidate.getSkills().removeIf(skill -> skill.getId().equals(skillId));
        return candidateRepository.save(candidate);
    }

    // brisanje kandidata
    public void removeCandidate(Long candidateId) {
        if (!candidateRepository.existsById(candidateId)) {
            throw new ResourceNotFound("Kandidat sa ID " + candidateId + " nije pronadjen.");
        }
        candidateRepository.deleteById(candidateId);
    }

    // apdejt kandidata
    public Candidate updateCandidate(Long candidateId, Candidate updatedCandidate) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Kandidat nije pronadjen."));

        candidate.setName(updatedCandidate.getName());
        candidate.setDateOfBirth(updatedCandidate.getDateOfBirth());
        candidate.setContactNumber(updatedCandidate.getContactNumber());
        candidate.setEmail(updatedCandidate.getEmail());

        return candidateRepository.save(candidate);
    }

    // pretraga po imenu
    public List<Candidate> searchByName(String name) {
        return candidateRepository.findByNameContainingIgnoreCase(name);
    }

    // pretraga po skilovima
    public List<Candidate> searchBySkill(List<String> skillNames) {
        return candidateRepository.findBySkills(skillNames, skillNames.size());
    }

    // vracanje svih kandidata
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

}














