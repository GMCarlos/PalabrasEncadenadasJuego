package com.example.palabrasEncadenadas.controllers;

import java.util.ArrayList;

import com.example.palabrasEncadenadas.services.PalabrasService;
import com.example.palabrasEncadenadas.services.PalabrasServiceImpl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/palabras")
public class PalabrasController {
    private final PalabrasService palabrasService;
    

    public PalabrasController() {
        this.palabrasService = new PalabrasServiceImpl();
        
    }
    @GetMapping("/")
    public String cargarPalabra(){
        return palabrasService.generarPalabraAleatoria();
    }

    @PostMapping("/")
    public Boolean buscarPalabra(String palabra){
        return palabrasService.existe(palabra);
    }


}
