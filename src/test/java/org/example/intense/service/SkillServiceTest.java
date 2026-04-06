package org.example.intense.service;

import org.example.intense.model.Skill;
import org.example.intense.repository.SkillRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SkillServiceTest {

    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private SkillService skillService;

    @Test
    void shouldAddSkill() {
        Skill skill = new Skill();
        skill.setName("Skill");

        when(skillRepository.save(skill)).thenReturn(skill);

        Skill result =  skillService.addSkill(skill);

        assertEquals("Skill", result.getName());
        verify(skillRepository).save(skill);
    }

}
