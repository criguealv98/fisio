package com.example.cliniko.controller;

import com.example.cliniko.model.Paciente;
import com.example.cliniko.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping
    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }

    @PostMapping
    public Paciente createPaciente(@RequestBody Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @PutMapping("/{id}")
    public Paciente updatePaciente(@PathVariable Long id, @RequestBody Paciente pacienteDetalles) {
        return pacienteRepository.findById(id).map(paciente -> {
            paciente.setNombre(pacienteDetalles.getNombre());
            paciente.setApellido(pacienteDetalles.getApellido());
            paciente.setEmail(pacienteDetalles.getEmail());
            return pacienteRepository.save(paciente);
        }).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
    }
}
