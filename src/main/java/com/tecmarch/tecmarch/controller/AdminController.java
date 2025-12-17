package com.tecmarch.tecmarch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Métricas de ejemplo (luego las sacaremos de la BD)
        model.addAttribute("totalProductos", 156);
        model.addAttribute("ventasMesActual", 32450.00);
        model.addAttribute("citasPendientes", 12);
        model.addAttribute("contactosSinResponder", 8);
        model.addAttribute("porcentajeVentas", 78);
        model.addAttribute("porcentajeSatisfaccion", 92);

        // Título de la página
        model.addAttribute("pageTitle", "Panel Administrativo - TecMerch");

        return "admin/dashboard";  // → buscará el archivo admin/dashboard.html
    }
}