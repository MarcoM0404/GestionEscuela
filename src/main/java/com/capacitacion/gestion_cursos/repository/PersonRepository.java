package com.capacitacion.gestion_cursos.repository;

import com.capacitacion.gestion_cursos.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
