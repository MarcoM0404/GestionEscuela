package com.capacitacion.gestion_cursos.ui;

import com.capacitacion.gestion_cursos.model.Course;
import com.capacitacion.gestion_cursos.model.Role;
import com.capacitacion.gestion_cursos.model.Seat;
import com.capacitacion.gestion_cursos.model.User;
import com.capacitacion.gestion_cursos.service.SeatService;
import com.capacitacion.gestion_cursos.service.StudentService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "student", layout = MainLayout.class)
public class StudentView extends VerticalLayout {

    private final SeatService    seatService;
    private final StudentService studentService;
    private final Grid<Course>   grid = new Grid<>(Course.class, false);

    @Autowired
    public StudentView(SeatService seatService,
                       StudentService studentService) {
        this.seatService    = seatService;
        this.studentService = studentService;

        // Control de acceso: solo STUDENT
        User u = VaadinSession.getCurrent().getAttribute(User.class);
        if (u == null || u.getRole() != Role.STUDENT) {
            UI.getCurrent().navigate("login");
            return;
        }

        setSizeFull();

        // 1) Cabecera con saludo y acceso al perfil
        add(
            new H2("🎓 Bienvenido, " + u.getUsername()),
            new Button("✏️ Mi Perfil", e ->
                UI.getCurrent().navigate("student/profile")
            )
        );
        
        
        double promedio = seatService.findByStudentUserId(u.getId()).stream()
        	    .mapToDouble(s -> s.getMark() != null ? s.getMark() : 0.0)
        	    .average()
        	    .orElse(0.0);
        	add(new H3("📊 Tu promedio de notas: " + String.format("%.2f", promedio)));


        // 2) Grid de cursos en que estoy inscripto
        grid.addColumn(Course::getId)
            .setHeader("ID")
            .setWidth("70px");
        grid.addColumn(Course::getName)
            .setHeader("Curso")
            .setAutoWidth(true);
        grid.setSizeFull();

        add(new H2("📋 Mis Cursos"), grid);

        // 3) Cargar los cursos del alumno
        List<Course> cursos = seatService
            .findByStudentUserId(u.getId())
            .stream()
            .map(Seat::getCourse)
            .distinct()
            .toList();
        grid.setItems(cursos);
    }
}
