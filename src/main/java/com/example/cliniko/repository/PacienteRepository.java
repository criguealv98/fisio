package com.example.cliniko.repository;

import com.example.cliniko.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> { }
