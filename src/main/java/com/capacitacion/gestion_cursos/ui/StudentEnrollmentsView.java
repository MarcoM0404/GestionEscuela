package com.capacitacion.gestion_cursos.ui;

import com.capacitacion.gestion_cursos.model.Seat;
import com.capacitacion.gestion_cursos.model.User;
import com.capacitacion.gestion_cursos.service.SeatService;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.util.List;

@Route(value = "student/enrollments", layout = MainLayout.class)
public class StudentEnrollmentsView extends VerticalLayout {

    private final SeatService seatService;
    private final Grid<Seat>  grid = new Grid<>(Seat.class, false);

    public StudentEnrollmentsView(SeatService seatService) {
        this.seatService = seatService;
        setSizeFull();

        // Título
        add(new H2("📋 Mis Inscripciones"));

        // Configuro columnas
        grid.addColumn(Seat::getId).setHeader("ID").setWidth("70px");
        grid.addColumn(s -> s.getCourse().getName())
            .setHeader("Curso")
            .setAutoWidth(true);
        grid.addColumn(Seat::getYear).setHeader("Año");
        grid.addColumn(Seat::getMark).setHeader("Nota");
        grid.setSizeFull();

        add(grid);

        // Cargo las inscripciones del usuario autenticado
        User current = VaadinSession.getCurrent().getAttribute(User.class);
        if (current == null) {
            getUI().ifPresent(ui -> ui.navigate("login"));
        } else {
            List<Seat> inscripciones = 
                seatService.findByStudentUserId(current.getId());
            grid.setItems(inscripciones);
        }
    }
}
