package com.example.palabrasEncadenadas;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PalabrasEncadenadasApplication {

	public static void main(String[] args) {
		//SpringApplication.run(PalabrasEncadenadasApplication.class, args);
		NuevoJuego juego = new NuevoJuego();
		juego.start();
	}

}
