package com.example.cliniko.controller;

import com.example.cliniko.model.Clinica;
import com.example.cliniko.repository.ClinicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clinicas")
public class ClinicaController {

    @Autowired
    private ClinicaRepository clinicaRepository;

    // Obtener todas las clínicas
    @GetMapping
    public List<Clinica> getAllClinicas() {
        return clinicaRepository.findAll();
    }

    // Crear una nueva clínica
    @PostMapping
    public Clinica createClinica(@RequestBody Clinica clinica) {
        return clinicaRepository.save(clinica);
    }
}
