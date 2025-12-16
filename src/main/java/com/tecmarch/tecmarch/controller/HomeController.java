package com.tecmarch.tecmarch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/productos")
    public String productos() {
        return "productos";
    }

    @GetMapping("/contacto")
    public String contacto() {
        return "contacto";
    }

    @GetMapping("/serviciotecnico")
    public String servicioTecnico() {
        return "serviciotecnico";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}