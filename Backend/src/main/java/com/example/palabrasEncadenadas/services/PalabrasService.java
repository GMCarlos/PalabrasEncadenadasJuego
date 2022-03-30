package com.example.palabrasEncadenadas.services;

import org.springframework.stereotype.Service;

@Service
public interface PalabrasService {

    Boolean existe(String palabra);

    String generarPalabraAleatoria();

    String getPrimeraPalabraSilaba();

    String getUltimaPalabraSilaba();
    
}
