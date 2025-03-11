package com.example.cliniko.controller;

import com.example.cliniko.model.Cita;
import com.example.cliniko.model.Paciente;
import com.example.cliniko.repository.CitaRepository;
import com.example.cliniko.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping
    public List<Cita> getAllCitas() {
        return citaRepository.findAll();
    }

    @PostMapping("/{pacienteId}")
    public Cita createCita(@PathVariable Long pacienteId, @RequestBody Cita cita) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        cita.setPaciente(paciente);
        return citaRepository.save(cita);
    }

    @PutMapping("/{id}")
    public Cita updateCita(@PathVariable Long id, @RequestBody Cita citaDetalles) {
        return citaRepository.findById(id).map(cita -> {
            cita.setFechaHora(citaDetalles.getFechaHora());
            return citaRepository.save(cita);
        }).orElseThrow(() -> new RuntimeException("Cita no encontrada"));
    }
}
