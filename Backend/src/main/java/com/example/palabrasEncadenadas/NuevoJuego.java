package com.example.palabrasEncadenadas;
import java.util.ArrayList;

import com.example.palabrasEncadenadas.controllers.PalabrasController;

public class NuevoJuego {
    private String palabra;
    private int puntos;
    private Silabeador silabeador;
    private PalabrasController palabrasController;
    //private Fichero fichero;

    public NuevoJuego() {
        this.puntos     = 0;
        this.silabeador = new Silabeador();
        this.palabrasController = new PalabrasController();
        this.palabra    = this.palabrasController.cargarPalabra();
    }

    //Almacenar las palabras insertadas para que no se puedan repetir y evitar así bucles
    public void start() {
        siguienteTurno();

    }

    public void siguienteTurno() {
        boolean palabraCorrecta = false;
        //Console.OutputEncoding = System.Text.Encoding.UTF8;
        while(true){
            while(!palabraCorrecta){
                System.out.println("Encadena la siguiente palabra: " + this.palabra);
                System.out.println("Inserte su palabra:");
                String palabraEscrita = System.console().readLine();
                if(this.palabrasController.buscarPalabra(palabraEscrita)){
                    //Comprobar que la primera silaba es igual a la ultima de this.palabra
                    ArrayList<String> palabraEscritaEnSilabas = silabeador.silabear(palabraEscrita);
                    ArrayList<String> palabraAnteriorEscritaEnSilabas = silabeador.silabear(this.palabra);
                    //Si la ultima silaba de la palabra que teníamos acaba por la misma silaba
                    //de la palabra que insertamos, es correcto
                    if(palabraEscritaEnSilabas.get(0).equals(palabraAnteriorEscritaEnSilabas.get(palabraAnteriorEscritaEnSilabas.size()-1))){
                        //Caso correcto, guardamos la nueva palabra como la última
                        this.palabra    = palabraEscrita;
                        palabraCorrecta = true;
                        this.puntos++;
                        System.out.println("Has ganado un punto!!");
                        System.out.println("Puntos actuales = " + this.puntos);
                    }else{
                        System.out.println("Las sílabas no coinciden!!!");
                        System.out.println("La silaba " + palabraAnteriorEscritaEnSilabas.get(palabraAnteriorEscritaEnSilabas.size()-1) + " de "
                        + this.palabra + " no coincide con "+ palabraEscritaEnSilabas.get(0) + " de " + palabraEscrita);
                    }

                }else{
                    System.out.println("La palabra no existe, inserte otra palabra.");
                }
            }
            palabraCorrecta=false;
        }
    }
    
}
