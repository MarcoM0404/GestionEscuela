// src/main/java/com/capacitacion/gestion_cursos/repository/UserRepository.java
package com.capacitacion.gestion_cursos.repository;

import com.capacitacion.gestion_cursos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // opcional: búsqueda por username
    User findByUsername(String username);
}
