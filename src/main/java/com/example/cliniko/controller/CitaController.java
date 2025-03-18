package com.example.cliniko.controller;

import com.example.cliniko.model.Cita;
import com.example.cliniko.model.Paciente;
import com.example.cliniko.repository.CitaRepository;
import com.example.cliniko.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    // Obtener todas las citas
    @GetMapping
    public List<Cita> getAllCitas() {
        return citaRepository.findAll();
    }

    // Obtener todos los pacientes
    @GetMapping("/pacientes")
    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }

    // Crear una cita
    @PostMapping("/{pacienteId}")
    public ResponseEntity<?> createCita(@PathVariable Long pacienteId, @RequestBody Cita cita) {
        // Verificar si el paciente existe
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        // Validar que la fecha de la cita sea entre lunes y viernes y entre 17:00 y 20:00
        if (!isValidFechaHora(cita.getFechaHora())) {
            return ResponseEntity.badRequest().body("La cita debe ser de lunes a viernes, entre las 17:00 y las 20:00.");
        }

        // Asignar el paciente a la cita
        cita.setPaciente(paciente);

        // Guardar la cita en la base de datos
        citaRepository.save(cita);
        return ResponseEntity.ok(cita);
    }

    // Método para validar la fecha y hora de la cita
    private boolean isValidFechaHora(LocalDateTime fechaHora) {
        // Verificar si el día es entre lunes (1) y viernes (5)
        DayOfWeek diaSemana = fechaHora.getDayOfWeek();
        if (diaSemana == DayOfWeek.SATURDAY || diaSemana == DayOfWeek.SUNDAY) {
            return false;
        }

        // Verificar si la hora está entre las 17:00 y las 20:00
        int hora = fechaHora.getHour();
        return hora >= 17 && hora < 20;
    }
}
