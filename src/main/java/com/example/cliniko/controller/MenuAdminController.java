package com.example.cliniko.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    	  List<Fisio> fisios = fisioService.findAll();
          List<Paciente> pacientes = pacienteService.findAll();

          model.addAttribute("fisios", fisios);
          model.addAttribute("pacientes", pacientes);
    	return "menu-admin";
    }

    // Métodos para manejar los formularios de creación de usuario, fisio y paciente
    @PostMapping("/crear-usuario")
    public String crearUsuario(@ModelAttribute Usuario usuario, Model model) {
        // Lógica para registrar al usuario
        Usuario usuarioRegistrado = usuarioService.save(usuario);

        if (usuarioRegistrado != null) {
            // Si el registro fue exitoso, pasar el objeto de usuario a la vista
            model.addAttribute("usuario", usuarioRegistrado);
            return "crear-usuario";  // Vista con mensaje de éxito
        } else {
            // Si hubo algún error, pasar null
            model.addAttribute("usuario", null);
            return "crear-usuario";  // Vista con mensaje de error
        }
    }

    @PostMapping("/crear-fisio")
    public String crearFisio(Fisio fisio, Model model) {
        fisioService.save(fisio);  // Guarda el fisio
        model.addAttribute("mensaje", "Fisio creado con éxito");
        return "crear-fisio";  // Redirige al menú de administración
    }

    @PostMapping("/crear-paciente")
    public String crearPaciente(Paciente paciente, Model model) {
        pacienteService.save(paciente);  // Guarda el paciente
        model.addAttribute("mensaje", "Paciente creado con éxito");
        return "crear-paciente";  // Redirige al menú de administración
    }
 // Ruta para la página de creación de fisio
    @GetMapping("/crear-fisio-form")
    public String mostrarFormularioCrearFisio() {
        return "crear-fisio-form"; // Devuelve la vista donde se crea el fisio
    }
    @GetMapping("/crear-usuario-form")
    public String mostrarFormularioCrearUsuario() {
        return "crear-usuario-form"; // Devuelve la vista donde se crea el usuario
    }

    // Ruta para la página de creación de paciente
    @GetMapping("/crear-paciente-form")
    public String mostrarFormularioCrearPaciente() {
        return "crear-paciente-form"; // Devuelve la vista donde se crea el paciente
    }
}
