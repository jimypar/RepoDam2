import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class LeeHTML {
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

        Type userListType = new TypeToken<ArrayList<Circuito>>(){}.getType();

        ArrayList<Circuito> userArray = gson.fromJson(contenido, userListType);

        for(Circuito c : userArray) {
            System.out.println(c.getName());
        }


    }
}
