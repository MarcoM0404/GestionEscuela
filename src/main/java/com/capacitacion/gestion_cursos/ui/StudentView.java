package com.capacitacion.gestion_cursos.ui;

import com.capacitacion.gestion_cursos.model.Course;
import com.capacitacion.gestion_cursos.model.Seat;
import com.capacitacion.gestion_cursos.model.Role;
import com.capacitacion.gestion_cursos.model.User;
import com.capacitacion.gestion_cursos.service.SeatService;
import com.capacitacion.gestion_cursos.service.StudentService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.util.List;

@Route(value = "student", layout = MainLayout.class)
public class StudentView extends VerticalLayout {

    private final SeatService    seatService;
    private final StudentService studentService;
    private final Grid<Course>   grid = new Grid<>(Course.class, false);

    public StudentView(SeatService seatService,
                       StudentService studentService) {
        this.seatService    = seatService;
        this.studentService = studentService;

        User u = VaadinSession.getCurrent().getAttribute(User.class);
        if (u == null || u.getRole() != Role.STUDENT) {
            UI.getCurrent().navigate("login");
            return;
        }

        setSizeFull();
        add(new H2("🎓 Bienvenido, " + u.getUsername()));
        add(new Button("✏️ Mi Perfil", e ->
            UI.getCurrent().navigate("student/profile")
        ));

        grid.addColumn(Course::getId).setHeader("ID").setWidth("70px");
        grid.addColumn(Course::getName).setHeader("Curso").setAutoWidth(true);
        grid.setSizeFull();

        add(new H2("📋 Mis Cursos"), grid);

        studentService.findByUserId(u.getId()).ifPresent(student -> {
            List<Course> cursos = seatService
                .findByStudentUserId(u.getId())
                .stream()
                .map(Seat::getCourse)
                .distinct()
                .toList();
            grid.setItems(cursos);
        });
    }
}