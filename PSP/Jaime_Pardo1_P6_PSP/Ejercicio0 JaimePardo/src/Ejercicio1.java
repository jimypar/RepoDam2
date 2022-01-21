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


        String direccion_url = "https://raw.githubusercontent.com/bacinger/f1-circuits/master/f1-locations.json"; //Url

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

        Gson gson = new Gson();

        Type listaTipoCircuitos = new TypeToken<ArrayList<Circuito>>(){}.getType();

        ArrayList<Circuito> circuitosArray = gson.fromJson(contenido, listaTipoCircuitos);

        for(Circuito c : circuitosArray) {
            System.out.println("ID Circuito: "+c.getId());
            System.out.println("Nombre: "+c.getName());
            System.out.println("Ciudad: "+c.getLocation());
            System.out.println("Coordenadas: "+c.getLat()+","+c.getLon());
            System.out.println();
        }


    }
}
