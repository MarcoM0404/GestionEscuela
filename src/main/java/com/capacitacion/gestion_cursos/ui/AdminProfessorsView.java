package com.capacitacion.gestion_cursos.ui;

import com.capacitacion.gestion_cursos.model.*;
import com.capacitacion.gestion_cursos.service.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Route(value = "admin/professors", layout = MainLayout.class)
public class AdminProfessorsView extends VerticalLayout {

    private final ProfessorService profService;
    private final PersonService    personService;
    private final AddressService   addressService;
    private final UserService      userService;
    private final PasswordEncoder  passwordEncoder;
    private final Grid<Professor>  grid = new Grid<>(Professor.class, false);

    @Autowired
    public AdminProfessorsView(ProfessorService profService,
                               PersonService personService,
                               AddressService addressService,
                               UserService userService,
                               PasswordEncoder passwordEncoder) {
        this.profService    = profService;
        this.personService  = personService;
        this.addressService = addressService;
        this.userService    = userService;
        this.passwordEncoder = passwordEncoder;

        User u = VaadinSession.getCurrent().getAttribute(User.class);
        if (u == null || u.getRole() != Role.ADMIN) {
            getUI().ifPresent(ui -> ui.navigate("login"));
            return;
        }

        setSizeFull();
        add(new H2("👩‍🏫 Gestión de Profesores"),
            new Button("➕ Nuevo Profesor", e -> openEditor(new Professor())));

        configureGrid();
        add(grid);
        refreshGrid();
    }

    private void configureGrid() {
        grid.addColumn(Professor::getId).setHeader("ID").setWidth("70px");
        grid.addColumn(Professor::getName).setHeader("Nombre");
        grid.addColumn(Professor::getEmail).setHeader("Email");
        grid.addColumn(Professor::getPhone).setHeader("Teléfono");
        grid.addColumn(Professor::getSalary).setHeader("Salario");
        grid.setSizeFull();
        grid.asSingleSelect().addValueChangeListener(ev -> {
            if (ev.getValue() != null) {
                openEditor(ev.getValue());
            }
        });
    }

    private void refreshGrid() {
        grid.setItems(profService.findAll());
    }

    private void openEditor(Professor prof) {
        if (prof.getAddress() == null) {
            prof.setAddress(new Address());
        }
        if (prof.getUser() == null) {
            prof.setUser(new User());
        }

        Dialog dialog = new Dialog();
        dialog.setWidth("400px");

        Binder<Professor> binder = new Binder<>(Professor.class);
        TextField      name     = new TextField("Nombre");
        TextField      email    = new TextField("Email");
        TextField      phone    = new TextField("Teléfono");
        NumberField    salary   = new NumberField("Salario");
        TextField      username = new TextField("Username");
        PasswordField  password = new PasswordField("Password");
        TextField      street   = new TextField("Calle");
        TextField      city     = new TextField("Ciudad");
        TextField      state    = new TextField("Provincia");
        TextField      country  = new TextField("País");

        binder.forField(name).asRequired("Requerido").bind(Professor::getName,Professor::setName);
        binder.forField(email).asRequired("Requerido").bind(Professor::getEmail,Professor::setEmail);
        binder.forField(phone).bind(Professor::getPhone,Professor::setPhone);
        binder.forField(salary).asRequired("Requerido").bind(Professor::getSalary,Professor::setSalary);
        binder.forField(street).bind(p->p.getAddress().getStreet(),(p,v)->p.getAddress().setStreet(v));
        binder.forField(city).bind(p->p.getAddress().getCity(),(p,v)->p.getAddress().setCity(v));
        binder.forField(state).bind(p->p.getAddress().getState(),(p,v)->p.getAddress().setState(v));
        binder.forField(country).bind(p->p.getAddress().getCountry(),(p,v)->p.getAddress().setCountry(v));

        binder.forField(username).asRequired("Requerido").bind(p->p.getUser().getUsername(),(p,v)->p.getUser().setUsername(v));

        binder.readBean(prof);

        Button save = new Button("Guardar", ev -> {
            if (binder.writeBeanIfValid(prof)) {
                User user = prof.getUser();
                user.setRole(Role.PROFESSOR);
                if (!password.getValue().isBlank()) {
                    user.setPassword(passwordEncoder.encode(password.getValue()));
                }
                userService.save(user);

                addressService.save(prof.getAddress());
                personService.save(prof);
                profService.save(prof);
                refreshGrid();
                dialog.close();
                Notification.show("Profesor guardado");
            }
        });
        Button cancel = new Button("Cancelar", ev -> dialog.close());

        dialog.add(new VerticalLayout(
            name,email,phone,salary,
            username,password,
            street,city,state,country,
            new HorizontalLayout(save,cancel)
        ));
        dialog.open();
    }
}