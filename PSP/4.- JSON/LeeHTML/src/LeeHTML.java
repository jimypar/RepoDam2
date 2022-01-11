import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class LeeHTML {
    public static void main(String[] args) throws IOException {


        String direccion_url = "https://as.com/"; //Url

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
        System.out.println(contenido);
    }
}
