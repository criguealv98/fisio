package com.example.cliniko.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cliniko.model.Fisio;
import com.example.cliniko.repository.FisioRepository;

@Service
public class FisioService {

    private final FisioRepository fisioRepository;

    @Autowired
    public FisioService(FisioRepository fisioRepository) {
        this.fisioRepository = fisioRepository;
    }

    public Fisio findById(Long fisioId) {
        return fisioRepository.findById(fisioId).orElse(null);
    }
    
    public Fisio save(Fisio fisio) {
        return fisioRepository.save(fisio);
    }
    public List<Fisio> findAll() {
        return fisioRepository.findAll();
    }
}
