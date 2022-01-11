import com.google.gson.Gson;

public class PruebaGson {

    public static void main (String [ ] args) {

        /* Se crea una persona */
        Persona p = new Persona("Froilan", "Torres","Ibañez",20 );

        //Se instancia el objeto GSON
        Gson gson = new Gson();

        //Se convierte el objeto a JSON
        String json = gson.toJson(p);
        //Se muestra el JSON por pantalla
        System.out.println(json);

        //Se convierte de JSON a objeto
        Persona p2 = gson.fromJson(json, Persona.class);
        System.out.println(p2.datosPersona());
    }

}
