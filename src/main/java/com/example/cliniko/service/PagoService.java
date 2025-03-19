package com.example.cliniko.service;

import com.example.cliniko.model.Pago;
import com.example.cliniko.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    public List<Pago> getAllPagos() {
        return pagoRepository.findAll();
    }

    public Optional<Pago> getPagoById(Long id) {
        return pagoRepository.findById(id);
    }

    public Pago savePago(Pago pago) {
        return pagoRepository.save(pago);
    }

    public void deletePago(Long id) {
        pagoRepository.deleteById(id);
    }
}
