package com.example.palabrasEncadenadas.controllers;

import com.example.palabrasEncadenadas.services.PalabrasService;
import com.example.palabrasEncadenadas.services.PalabrasServiceImpl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/palabras")
public class PalabrasController {
    private final PalabrasService palabrasService;
    

    public PalabrasController() {
        this.palabrasService = new PalabrasServiceImpl();
        
    }
    @GetMapping("")
    public String cargarPalabra(){
        return palabrasService.generarPalabraAleatoria();
    }

    @GetMapping("/buscar")
    public Boolean buscarPalabra(@RequestParam String buscarPalabra){
        return palabrasService.existe(buscarPalabra);
    }

    @GetMapping("/silabaPrimeraPalabra")
    public String getPrimeraPalabraSilaba(){
        return palabrasService.getPrimeraPalabraSilaba();
    }

    @GetMapping("/silabaUltimaPalabra")
    public String getUltimaaPalabraSilaba(){
        return palabrasService.getUltimaPalabraSilaba();
    }

    


}
