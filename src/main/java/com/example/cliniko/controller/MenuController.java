package com.example.cliniko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.cliniko.model.Fisio;
import com.example.cliniko.model.Usuario;
import com.example.cliniko.service.FisioService;
import com.example.cliniko.service.UsuarioService;

@Controller
public class MenuController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private FisioService fisioService;

    @GetMapping("/menu")
    public String getMenu(Authentication authentication, Model model) {
        if (authentication == null || authentication.getName() == null) {
            model.addAttribute("mensaje", "Bienvenido a la gestión de citas");
            return "menu"; 
        }

        String username = authentication.getName();
        Usuario usuario = usuarioService.findByUsername(username);

        if (usuario != null) {
            boolean isFisio = usuario.getFisioId() != null;
            boolean isPaciente = usuario.getPacienteId() != null;

            model.addAttribute("canCreateCita", isPaciente); // Los pacientes pueden crear citas
            model.addAttribute("canViewCitas", true); // Todos pueden ver citas, pero se controla en la vista qué citas
            model.addAttribute("canViewPacientes", isFisio); // Solo los fisios pueden ver pacientes

            if (isFisio) {
                Fisio fisio = fisioService.findById(usuario.getFisioId());
                model.addAttribute("mensaje", "Bienvenido a la clínica " + fisio.getClinica().getNombre());
            } else {
                model.addAttribute("mensaje", "Bienvenido a la gestión de citas");
            }
        }

        return "menu";
    }
}
