package com.example.cliniko.controller;

import com.example.cliniko.model.Paciente;
import com.example.cliniko.model.Usuario;
import com.example.cliniko.repository.PacienteRepository;
import com.example.cliniko.service.PacienteService;
import com.example.cliniko.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class PacienteController {


    @Autowired
    private PacienteRepository pacienteRepository;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private PacienteService pacienteService;




    @GetMapping("/pacientes")
    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }
    @GetMapping("/pacientes/id")
    public List<Paciente> getIdPaciente(Authentication authentication, Model model) {
    	String username = authentication.getName();
        Usuario usuario = usuarioService.findByUsername(username);
        List<Paciente> lista = new ArrayList();
        Paciente p = pacienteService.findById(usuario.getPacienteId());
        lista.add(p);
        return lista;
    }
}
