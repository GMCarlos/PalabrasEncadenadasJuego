package com.example.palabrasEncadenadas.services;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;
import org.springframework.stereotype.Service;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
@Service
public class PalabrasServiceImpl implements PalabrasService{
    //static String rutaFichero = "C://Users//carlo//Desktop//PalabrasEncadenadas//Backend//src//main//java//com//example//palabrasEncadenadas//repository//diccionario.txt";
    static String url = "https://palabrasencadenadas-b5f62-default-rtdb.europe-west1.firebasedatabase.app/.json";
    static String ultimaPalabra;
    private ArrayList<String> diccionario;

    public PalabrasServiceImpl(){
        this.diccionario = new ArrayList<String>();
        cargarDatos();
    }
    


    private void cargarDatos() {
        String respuesta = "";
		try {
			respuesta = peticionHttpGet(url);
            //Pasar json a array string
            ObjectMapper om = new ObjectMapper();
            JsonNode tree = om.readTree(respuesta);
            //List<JsonNode> palabras = tree.findValues("palabras");
            //Cargamos en estructura lo leido desde la database de firebase

            ArrayNode arrayNode = (ArrayNode) tree.get("palabras");
            java.util.Iterator itr = arrayNode.elements();
            // and to get the string from one of the elements, use for example...
            
            StringBuilder sb;
            String aux;
            for(int i = 0; i<arrayNode.size(); i++){
                aux = itr.next().toString();
                sb = new StringBuilder(aux);
                // Removing the last character
                // of a string
                sb.deleteCharAt(aux.length() - 1);
                // Removing the first character
                // of a string
                sb.deleteCharAt(0);
                this.diccionario.add(sb.toString());
            }
            System.out.println("Hola");
            
		} catch (Exception e) {
			// Manejar excepción
			e.printStackTrace();
		}
    }



    @Override
    public Boolean existe (String palabra) {
        boolean existePalabra = false;
        if(this.diccionario.contains(palabra)){
            existePalabra = true;
        }
        return existePalabra;
    }


    @Override
    public String generarPalabraAleatoria() {
        String texto = "";
        Random rand = new Random(); //instance of random class
        int upperbound = 646615;
        //generate random values from [0-646614]
        int int_random = rand.nextInt(upperbound);
        texto = this.diccionario.get(int_random);
        return texto;
    }




    public String peticionHttpGet(String urlParaVisitar) throws Exception {
		// Esto es lo que vamos a devolver
		StringBuilder resultado = new StringBuilder();
		// Crear un objeto de tipo URL
		URL url = new URL(urlParaVisitar);

		// Abrir la conexión e indicar que será de tipo GET
		HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
		conexion.setRequestMethod("GET");
		// Búferes para leer
		BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream(), StandardCharsets.UTF_8));
		String linea;
		// Mientras el BufferedReader se pueda leer, agregar contenido a resultado
		while ((linea = rd.readLine()) != null) {
			resultado.append(linea);
		}
		// Cerrar el BufferedReader
		rd.close();
		// Regresar resultado, pero como cadena, no como StringBuilder
		return resultado.toString();
	}
    
}
