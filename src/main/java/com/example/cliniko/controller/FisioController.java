package com.example.cliniko.controller;

import com.example.cliniko.model.Fisio;
import com.example.cliniko.repository.FisioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fisios")
public class FisioController {

    @Autowired
    private FisioRepository fisioRepository;

    // Obtener todos los fisios
    @GetMapping
    public List<Fisio> getAllFisios() {
        return fisioRepository.findAll();
    }

    // Crear un nuevo fisio
    @PostMapping
    public Fisio createFisio(@RequestBody Fisio fisio) {
        return fisioRepository.save(fisio);
    }
}
