package com.capacitacion.gestion_cursos.service;

import com.capacitacion.gestion_cursos.model.Address;
import com.capacitacion.gestion_cursos.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repo;

    public List<Address> findAll() {
        return repo.findAll();
    }

    public Optional<Address> findById(Long id) {
        return repo.findById(id);
    }

    public Address save(Address address) {
        return repo.save(address);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
