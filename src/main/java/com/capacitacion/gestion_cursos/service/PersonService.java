package com.capacitacion.gestion_cursos.service;

import com.capacitacion.gestion_cursos.model.Person;
import com.capacitacion.gestion_cursos.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repo;

    public List<Person> findAll() {
        return repo.findAll();
    }

    public Optional<Person> findById(Long id) {
        return repo.findById(id);
    }

    public Person save(Person person) {
        return repo.save(person);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
