package com.capacitacion.gestion_cursos.repository;

import com.capacitacion.gestion_cursos.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Optional<Professor> findByUserId(Long userId);
    List<Professor> findByNameContainingIgnoreCase(String name);
}
