package com.example.cliniko.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cliniko.model.Fisio;
import com.example.cliniko.model.Paciente;
import com.example.cliniko.repository.PacienteRepository;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente findById(Long id) {
        return pacienteRepository.findById(id).orElse(null);
    }
    public Paciente save(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }
    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }
}
