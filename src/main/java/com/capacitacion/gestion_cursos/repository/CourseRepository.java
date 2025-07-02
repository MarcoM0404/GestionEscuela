// src/main/java/com/capacitacion/gestion_cursos/repository/CourseRepository.java
package com.capacitacion.gestion_cursos.repository;

import com.capacitacion.gestion_cursos.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByProfessorId(Long professorId);
}
