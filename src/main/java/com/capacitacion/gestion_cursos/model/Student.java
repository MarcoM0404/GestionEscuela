package com.capacitacion.gestion_cursos.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "students")
public class Student extends Person {

    @Column(name = "student_number", nullable = false, unique = true)
    private UUID studentNumber;

    @Column(name = "avg_mark")
    private Double avgMark;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats = new ArrayList<>();


    public Student() {
        super();  
    }


    public Student(UUID studentNumber, Double avgMark) {
        this.studentNumber = studentNumber;
        this.avgMark       = avgMark;
    }



    public UUID getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(UUID studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Double getAvgMark() {
        return avgMark;
    }

    public void setAvgMark(Double avgMark) {
        this.avgMark = avgMark;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
