package com.capacitacion.gestion_cursos.service;

import com.capacitacion.gestion_cursos.model.Student;
import com.capacitacion.gestion_cursos.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }



    public List<Student> findByNameContainingIgnoreCase(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }


    public Optional<Student> findByUserId(Long userId) {
        return repo.findByUserId(userId);
    }

    public List<Student> findAll() {
        return repo.findAll();
    }

    public Student save(Student s) {
        return repo.save(s);
    }
}
