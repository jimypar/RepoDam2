import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Ejercicio1 {
    public static void main(String[] args) throws IOException {

        //Se guarda el url en un string
        String direccion_url = "https://raw.githubusercontent.com/bacinger/f1-circuits/master/f1-locations.json"; //Url

        //Se instancia el url
        URL pagina = new URL(direccion_url);


        //Se crea el objeto URLConnection
        URLConnection uc = pagina.openConnection();
        uc.connect();
        //Creamos el objeto con el que vamos a leer
        BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
        String inputLine;
        String contenido = "";
        while ((inputLine = in.readLine()) != null) {
            contenido += inputLine + "\n";
        }
        in.close();

        //Se crea el GSON
        Gson gson = new Gson();

        //Se crea un una lista de tipo circuito
        Type listaTipoCircuitos = new TypeToken<ArrayList<Circuito>>(){}.getType();

        //Se crea un array de circutos y se inserta el contenido del JSON
        ArrayList<Circuito> circuitosArray = gson.fromJson(contenido, listaTipoCircuitos);

        //Se recorre el array de circuitos y se muestra los datos
        for(Circuito c : circuitosArray) {
            System.out.println("ID Circuito: "+c.getId());
            System.out.println("Nombre: "+c.getName());
            System.out.println("Ciudad: "+c.getLocation());
            System.out.println("Coordenadas: "+c.getLat()+","+c.getLon());
            System.out.println();
        }


    }
}
