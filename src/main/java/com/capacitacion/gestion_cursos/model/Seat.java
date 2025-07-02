package com.capacitacion.gestion_cursos.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Fecha de inscripción
    private LocalDate year;
    private LocalDate evaluationDate;

    // Nota (puede ser null si aún no la pusiste)
    private Double mark;

    @ManyToOne
    private Course course;

    @ManyToOne
    private Student student;
    	

    // --- getters y setters ---

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getYear() {
        return year;
    }
    public void setYear(LocalDate year) {
        this.year = year;
    }

    public Double getMark() {
        return mark;
    }
    public void setMark(Double mark) {
        this.mark = mark;
    }

    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
	public LocalDate getEvaluationDate() {
		return evaluationDate;
	}
	public void setEvaluationDate(LocalDate evaluationDate) {
		this.evaluationDate = evaluationDate;
	}
}
