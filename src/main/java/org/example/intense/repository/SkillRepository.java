package org.example.intense.repository;

import org.example.intense.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    // pretraga po imenu
    Optional<Skill> findByNameIgnoreCase(String name);

}
