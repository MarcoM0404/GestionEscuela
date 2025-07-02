package com.capacitacion.gestion_cursos.repository;

import com.capacitacion.gestion_cursos.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Long> {
    // Busca el Student a partir de la FK user_id de Person → User
    Optional<Student> findByUserId(Long userId);
    List<Student> findByNameContainingIgnoreCase(String name);
}
