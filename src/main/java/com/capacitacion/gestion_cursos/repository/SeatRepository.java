package com.capacitacion.gestion_cursos.repository;

import com.capacitacion.gestion_cursos.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    @Query("SELECT s FROM Seat s WHERE s.student.user.id = :userId")
    List<Seat> findByStudentUserId(@Param("userId") Long userId);
    List<Seat> findByStudentNameContainingIgnoreCase(String name);
    List<Seat> findByStudentStudentNumber(UUID studentNumber);	
    List<Seat> findByCourseId(Long courseId);

}
