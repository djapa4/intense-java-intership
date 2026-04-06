package org.example.intense.service;

import org.example.intense.model.Candidate;
import org.example.intense.model.Skill;
import org.example.intense.repository.CandidateRepository;
import org.example.intense.repository.SkillRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CandidateServiceTest {

    @Mock
    private SkillRepository skillRepository;

    @Mock
    private CandidateRepository candidateRepository;

    @InjectMocks
    private CandidateService candidateService;

    @Test
    void shouldAddCandidate() {
        Candidate c = new Candidate();
        c.setName("Marko");

        when(candidateRepository.save(c)).thenReturn(c);

        Candidate result = candidateService.addCandidate(c);

        assertEquals("Marko", result.getName());
        verify(candidateRepository).save(c);
    }

    @Test
    void shouldAddSkillToCandidate() {
        Candidate c = new Candidate();
        c.setId(1L);

        Skill s = new Skill();
        s.setId(2L);

        when(candidateRepository.findById(1L)).thenReturn(Optional.of(c));
        when(skillRepository.findById(2L)).thenReturn(Optional.of(s));
        when(candidateRepository.save(c)).thenReturn(c);

        Candidate result = candidateService.addSkillToCandidate(1L, 2L);

        assertTrue(result.getSkills().contains(s));
        verify(candidateRepository).save(c);
    }

    @Test
    void shouldRemoveSkillFromCandidate() {
        Candidate c = new Candidate();
        c.setId(1L);

        Skill s = new Skill();
        s.setId(2L);

        c.getSkills().add(s);

        when(candidateRepository.findById(1L)).thenReturn(Optional.of(c));
        when(candidateRepository.save(c)).thenReturn(c);

        Candidate result = candidateService.removeSkillFromCandidate(1L, 2L);

        assertFalse(result.getSkills().contains(s));
        verify(candidateRepository).save(c);
    }

    @Test
    void shouldUpdateCandidate() {
        Candidate c = new Candidate();
        c.setId(1L);
        c.setName("Marko");

        Candidate updated = new Candidate();
        updated.setName("New name");

        when(candidateRepository.findById(1L)).thenReturn(Optional.of(c));
        when(candidateRepository.save(c)).thenReturn(c);

        Candidate result = candidateService.updateCandidate(1L, updated);

        assertEquals("New name", result.getName());
    }

    @Test
    void shouldSearchByName() {
        Candidate c = new Candidate();
        c.setName("Marko");

        when(candidateRepository.findByNameContainingIgnoreCase("Marko"))
                .thenReturn(List.of(c));

        List<Candidate> result = candidateService.searchByName("Marko");

        assertEquals(1, result.size());
        assertEquals("Marko", result.get(0).getName());
    }

}
