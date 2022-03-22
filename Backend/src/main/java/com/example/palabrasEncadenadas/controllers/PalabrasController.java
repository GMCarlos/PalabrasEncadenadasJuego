package com.example.palabrasEncadenadas.controllers;

import com.example.palabrasEncadenadas.services.PalabrasService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PalabrasController {
    private final PalabrasService palabrasService;

    PalabrasController(PalabrasService palabrasService){
        this.palabrasService = palabrasService;
    }

    @GetMapping("/")
    String cargarPalabra(){
        return palabrasService.generarPalabraAleatoria();
    }

    @PostMapping("/")
    Boolean buscarPalabra(String palabra){
        return palabrasService.existe(palabra);
    }


}
