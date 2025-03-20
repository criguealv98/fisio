package com.example.cliniko.repository;

import com.example.cliniko.model.Cita;
import com.example.cliniko.model.Paciente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaRepository extends JpaRepository<Cita, Long> {
	List<Cita> findByPaciente(Paciente p);
}
