package com.capacitacion.gestion_cursos.service;

import com.capacitacion.gestion_cursos.model.User;
import com.capacitacion.gestion_cursos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;
    

    public User findByUsername(String username) {
        return repo.findByUsername(username);
    }

    public List<User> findAll() {
        return repo.findAll();
    }

    public Optional<User> findById(Long id) {
        return repo.findById(id);
    }

    public User save(User user) {
        return repo.save(user);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
