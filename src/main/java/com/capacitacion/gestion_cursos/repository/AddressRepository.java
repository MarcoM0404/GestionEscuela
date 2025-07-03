package com.capacitacion.gestion_cursos.repository;

import com.capacitacion.gestion_cursos.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
