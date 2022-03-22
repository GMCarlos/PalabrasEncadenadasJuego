package com.example.palabrasEncadenadas;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.Random;
import java.nio.charset.StandardCharsets;

public class Fichero {
    static String rutaFichero = "C://Users//carlo//Desktop//PalabrasEncadenadas//Backend//src//main//java//com//example//palabrasEncadenadas//repository//diccionario.txt";
    static String ultimaPalabra;

    public Fichero(){
        //Declarar una variable BufferedReader
        BufferedReader br = null;
        Random rand = new Random(); //instance of random class
        int upperbound = 646615;
        //generate random values from [0-646614]
        int int_random = rand.nextInt(upperbound);
        try {
           //Crear un objeto BufferedReader al que se le pasa 
           //   un objeto FileReader con el nombre del fichero
           br = new BufferedReader(new FileReader(rutaFichero, StandardCharsets.UTF_8));
           //Leer la primera línea, guardando en un String
           String texto = br.readLine();
           //Contador para parar cuando se obtenga la palabra en la linea del diccionario
           int cont = 0;
           //Repetir mientras no se llegue al final del fichero o se llegue a la palabra random
           while(texto != null && (int_random != cont))
           {
               //Hacer lo que sea con la línea leída
               //System.out.println(texto);
               //Leer la siguiente línea
               texto = br.readLine();
               cont++;
           }
           
           ultimaPalabra = texto;
           System.out.println(texto);
           System.out.println("El número generado ha sido el " + int_random);
           System.out.println("El contador ha llegado hasta " + cont);
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: Fichero no encontrado");
            System.out.println(e.getMessage());
        }
        catch(Exception e) {
            System.out.println("Error de lectura del fichero");
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if(br != null)
                    br.close();
            }
            catch (Exception e) {
                System.out.println("Error al cerrar el fichero");
                System.out.println(e.getMessage());
            }
        }
    }

    public boolean existe (String palabra) {
        boolean existePalabra = false;
        //Declarar una variable BufferedReader
        BufferedReader br = null;
        try {
            //Crear un objeto BufferedReader al que se le pasa 
            //   un objeto FileReader con el nombre del fichero
            br = new BufferedReader(new FileReader(rutaFichero, StandardCharsets.UTF_8));
            //Leer la primera línea, guardando en un String
            String texto = br.readLine();
            //Repetir mientras no se llegue al final del fichero o se llegue a la palabra random
            while(texto != null && !existePalabra)
            {
                if(texto.equals(palabra)){
                    existePalabra = true;
                    System.out.println("La palabra: "+ texto + " existe.");
                    ultimaPalabra = texto;
                }
                //Hacer lo que sea con la línea leída
                //System.out.println(texto);
                //Leer la siguiente línea
                if(!existePalabra){
                    texto = br.readLine();
                }
                
            }

            if(!existePalabra) {
                System.out.println("La palabra " + palabra + " no existe.");
            }
            
            
         }
         catch (FileNotFoundException e) {
             System.out.println("Error: Fichero no encontrado");
             System.out.println(e.getMessage());
         }
         catch(Exception e) {
             System.out.println("Error de lectura del fichero");
             System.out.println(e.getMessage());
         }
         finally {
             try {
                 if(br != null)
                     br.close();
             }
             catch (Exception e) {
                 System.out.println("Error al cerrar el fichero");
                 System.out.println(e.getMessage());
             }
         }

        return existePalabra;
    }

   
    
}
