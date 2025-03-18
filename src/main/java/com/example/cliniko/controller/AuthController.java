package com.example.cliniko.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    // Página de login
    @GetMapping("/login")
    public String login() {
        return "login";  // Vista de login
    }

    // Página del menú principal
    @GetMapping("/auth-menu")
    public String menu() {
        return "menu";  // Vista del menú principal
    }
}
