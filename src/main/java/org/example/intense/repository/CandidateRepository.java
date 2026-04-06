package org.example.intense.repository;

import org.example.intense.model.Candidate;
import org.example.intense.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    // pretraga po imenima
    List<Candidate> findByNameContainingIgnoreCase(String name);

    // pretraga po skilu
    @Query("SELECT DISTINCT c FROM Candidate c JOIN c.skills s WHERE s.name IN :skillNames GROUP BY c HAVING COUNT(DISTINCT s.name) = :skillCount")
    List<Candidate> findBySkills(@Param("skillNames") List<String> skillNames, @Param("skillCount") long skillCount);
}
