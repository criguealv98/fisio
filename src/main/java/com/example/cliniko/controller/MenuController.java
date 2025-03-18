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
            // Si no hay usuario autenticado, mostrar mensaje predeterminado para la gestión de citas
            model.addAttribute("mensaje", "Bienvenido a la gestión de citas");
        } else {
            // Si el usuario está autenticado
            String username = authentication.getName();
            Usuario usuario = usuarioService.findByUsername(username);

            if (usuario != null) {
                boolean isFisio = usuario.getFisioId() != null;
                boolean isPaciente = usuario.getPacienteId() != null;
                Fisio fisio = fisioService.findById(usuario.getFisioId());
                

                model.addAttribute("canCreateCita", isPaciente); // Paciente puede crear citas
                model.addAttribute("canViewCitas", isFisio); // Fisio puede ver citas
                model.addAttribute("canViewPacientes", isFisio); // Fisio puede ver pacientes

                // Si el usuario es fisio, muestra el nombre de la clínica
                if (isFisio) {
                    // Aquí asumo que tienes un método para obtener la clínica del fisio.
                    // Añade la lógica para obtener la clínica del fisio, si es necesario
                    model.addAttribute("mensaje", "Bienvenido a la clínica " + fisio.getClinica().getNombre());
                } else if (isPaciente) {
                    // Si es paciente
                    model.addAttribute("mensaje", "Bienvenido a la gestión de citas");
                }
            }
        }

        return "menu"; // Nombre de tu vista de menú
    }

}
