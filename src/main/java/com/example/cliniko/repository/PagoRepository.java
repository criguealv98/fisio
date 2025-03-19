package com.example.cliniko.repository;

import  com.example.cliniko.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PagoRepository extends JpaRepository<Pago, Long> {
	    List<Pago> findByPacienteId(Long pacienteId);
	    List<Pago> findByFisioId(Long fisioId);
	    List<Pago> findByClinicaId(Long clinicaId);
	    List<Pago> findByEstado(String estado);
	
}
