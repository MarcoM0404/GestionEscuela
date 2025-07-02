// src/main/java/com/capacitacion/gestion_cursos/service/ProfessorService.java
package com.capacitacion.gestion_cursos.service;

import com.capacitacion.gestion_cursos.model.Professor;
import com.capacitacion.gestion_cursos.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {
    private final ProfessorRepository repo;
    public ProfessorService(ProfessorRepository repo) {
        this.repo = repo;
    }
    public List<Professor> findAll() {
        return repo.findAll();
    }
    public List<Professor> findByNameContainingIgnoreCase(String txt) {
        return repo.findByNameContainingIgnoreCase(txt);
    }
    public Optional<Professor> findByUserId(Long userId) {
        return repo.findByUserId(userId);
    }
    public Optional<Professor> findById(Long id) {
        return repo.findById(id);
    }
    public Professor save(Professor p) {
        return repo.save(p);
    }
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
