package com.capacitacion.gestion_cursos.service;

import com.capacitacion.gestion_cursos.model.Seat;
import com.capacitacion.gestion_cursos.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SeatService {

    private final SeatRepository repo;

    public SeatService(SeatRepository repo) {
        this.repo = repo;
    }

    public List<Seat> findAll() {
        return repo.findAll();
    }

    public List<Seat> findByStudentUserId(Long userId) {
        return repo.findByStudentUserId(userId);
    }

    public List<Seat> findByStudentName(String name) {
        return repo.findByStudentNameContainingIgnoreCase(name);
    }

    public List<Seat> findByStudentNumber(UUID studentNumber) {
        return repo.findByStudentStudentNumber(studentNumber);
    }

    public List<Seat> findByCourseId(Long courseId) {
        return repo.findByCourseId(courseId);
    }

    public void save(Seat seat) {
        repo.save(seat);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
