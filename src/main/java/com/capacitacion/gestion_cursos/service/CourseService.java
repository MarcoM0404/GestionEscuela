package com.capacitacion.gestion_cursos.service;

import com.capacitacion.gestion_cursos.model.Course;
import com.capacitacion.gestion_cursos.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository repo;
    public CourseService(CourseRepository repo) {
        this.repo = repo;
    }
    public List<Course> findAll() {
        return repo.findAll();
    }
    public Optional<Course> findById(Long id) {
        return repo.findById(id);
    }
    public List<Course> findByProfessorId(Long profId) {
        return repo.findByProfessorId(profId);
    }
    public Course save(Course c) {
        return repo.save(c);
    }
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
