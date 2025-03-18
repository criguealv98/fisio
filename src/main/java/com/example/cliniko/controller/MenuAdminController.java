package com.example.cliniko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.cliniko.model.Usuario;
import com.example.cliniko.model.Fisio;
import com.example.cliniko.model.Paciente;
import com.example.cliniko.service.UsuarioService;
import com.example.cliniko.service.FisioService;
import com.example.cliniko.service.PacienteService;

@Controller
public class MenuAdminController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private FisioService fisioService;

    @Autowired
    private PacienteService pacienteService;

    // Vista de administración sin necesidad de autenticación
    @GetMapping("/menu-admin")
    public String menuAdmin(Model model) {
        // Puedes cargar cualquier dato que necesites mostrar en la página de administración
        return "menu-admin";
    }

    // Métodos para manejar los formularios de creación de usuario, fisio y paciente
    @PostMapping("/crear-usuario")
    public String crearUsuario(Usuario usuario, Model model) {
        usuarioService.save(usuario); // Guarda el usuario
        model.addAttribute("mensaje", "Usuario creado con éxito");
        return "menu-admin";  // Redirige al menú de administración
    }

    @PostMapping("/crear-fisio")
    public String crearFisio(Fisio fisio, Model model) {
        fisioService.save(fisio);  // Guarda el fisio
        model.addAttribute("mensaje", "Fisio creado con éxito");
        return "menu-admin";  // Redirige al menú de administración
    }

    @PostMapping("/crear-paciente")
    public String crearPaciente(Paciente paciente, Model model) {
        pacienteService.save(paciente);  // Guarda el paciente
        model.addAttribute("mensaje", "Paciente creado con éxito");
        return "menu-admin";  // Redirige al menú de administración
    }
}
