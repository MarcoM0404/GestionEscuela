package com.capacitacion.gestion_cursos.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "admin", layout = MainLayout.class)
public class AdminView extends VerticalLayout {
    public AdminView() {
        // nada de UI aquí, simplemente redirige a /admin/courses
        UI.getCurrent().navigate("admin/courses");
    }
}
