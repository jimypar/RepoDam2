import com.google.gson.Gson;

import java.security.NoSuchAlgorithmException;

public class PruebaGson {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {

        //Se crea el objeto de la clase usuario
        Usuario u = new Usuario("Felipe Juan Froilan de todos los Santos","de Marichalar", "de Borbon", 22);
        //Se crea el objetode la clase Balance
        Balance b = new Balance();
        //Se crea el objeto de la clase Macedonia
        Macedonia m = new Macedonia();

        //Se crea el objeto Gson
        Gson g = new Gson();

        //Se convierte el objeto de la clase usuario a JSON
        String usuario = g.toJson(u);
        //Se convierte el objeto de la clase balance a JSON
        String balance = g.toJson(b);
        //Se convierte el objeto de la clase macedonia a JSON
        String macedonia = g.toJson(m);


        //Se muestran los objetos convertidos a JSON
        System.out.println("JSON usuario => "+usuario);
        System.out.println("JSON balance => "+balance);
        System.out.println("JSON macedonia => "+macedonia);

        System.out.println();
        System.out.println();
        System.out.println();


        //Se crean los objetos a partir de las cadenas JSON
        Usuario u2 = g.fromJson(usuario, Usuario.class);
        Balance b2 = g.fromJson(balance, Balance.class);
        Macedonia m2 = g.fromJson(macedonia, Macedonia.class);

        //Se muestran los objetos convertidos desde JSON
        System.out.println(u2.toString());
        System.out.println(b2.toString());
        System.out.println(m2.toString());


    }

}
