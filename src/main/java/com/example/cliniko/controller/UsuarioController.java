package com.example.cliniko.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cliniko.model.Fisio;
import com.example.cliniko.model.Paciente;
import com.example.cliniko.model.Usuario;
import com.example.cliniko.service.FisioService;
import com.example.cliniko.service.UsuarioService;


@RestController
public class UsuarioController {

	private final UsuarioService usuarioService;
	private final FisioService fisioService;  // Servicio para obtener los detalles del fisioterapeuta

    @Autowired
    public UsuarioController(UsuarioService usuarioService, FisioService fisioService) {
        this.usuarioService = usuarioService;
        this.fisioService = fisioService;
    }
    
    @GetMapping("/usuario/estado")
    public Map<String, Object> obtenerEstadoUsuario(Authentication authentication) {
        Usuario usuario = usuarioService.findByUsername(authentication.getName());

        Map<String, Object> estado = new HashMap<>();
        String mensajeBienvenida;
        boolean isFisio = false;
        boolean canCreateCita = false;
        boolean canViewCitas = false;
        boolean canViewPacientes = false;

        // Verifica si el usuario es un paciente o un fisio
        if (usuario.getPacienteId() != null) {
            mensajeBienvenida = "Bienvenido a la gestión de citas";
            canViewCitas = true;  // Un paciente puede ver sus citas
        } else if (usuario.getFisioId() != null) {
            isFisio = true;  // Es un fisioterapeuta
            canCreateCita = true;  // Puede crear citas
            canViewCitas = true;  // Puede ver citas
            canViewPacientes = true;  // Puede ver pacientes

            // Obtener la clínica del fisioterapeuta
            Fisio fisio = fisioService.findById(usuario.getFisioId());
            if (fisio != null && fisio.getClinica() != null) {
                mensajeBienvenida = "Bienvenido a la clínica " + fisio.getClinica().getNombre();
            } else {
                mensajeBienvenida = "Bienvenido a la gestión de citas";  // Si no tiene clínica
            }
        } else {
            mensajeBienvenida = "Bienvenido a la gestión de citas";
        }

        estado.put("mensajeBienvenida", mensajeBienvenida);
        estado.put("isFisio", isFisio);
        estado.put("canCreateCita", canCreateCita);
        estado.put("canViewCitas", canViewCitas);
        estado.put("canViewPacientes", canViewPacientes);

        return estado;
    }
    
    

}
