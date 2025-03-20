package com.example.cliniko.controller;

import com.example.cliniko.model.Pago;
import com.example.cliniko.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @GetMapping
    public List<Pago> getAllPagos() {
        return pagoService.getAllPagos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> getPagoById(@PathVariable Long id) {
        Optional<Pago> pago = pagoService.getPagoById(id);
        return pago.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pago createPago(@RequestBody Pago pago) {
        return pagoService.savePago(pago);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePago(@PathVariable Long id) {
        pagoService.deletePago(id);
        return ResponseEntity.noContent().build();
    }
}
