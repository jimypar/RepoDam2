import java.util.Observable;

/**
 *Clase Observado, que extiende observable y además del patrón de diseño
 * observer también utiliza el patron de diseño Singleton
 *
 * @author Teru
 */
class Observado extends Observable {


    String mensaje; // String que representa un mensaje

    //Instancia estatica de la calse (Singleton)
    private static Observado observado;

    //En el "constructor" privado se le dá valor a la variable mensaje
    private Observado(){
        mensaje = "Objeto Observado Iniciado";
    }

    public void cambiarMensaje(String m){
        mensaje = m;
        //Marcamos el objeto observable como objeto que ha cambiado
        setChanged();
        //Notificamos a los observadores y le enviamos el nuevo valor
        notifyObservers(mensaje);
        //notifyObservers(); Este metodo solo notifica que hubo cambios en el objeto
    }

    public static Observado getSingletonInstance(String nombre) {

        //Si no se ha creado la Instancia ...
        if (observado == null){
            //Se crea una instancia nueva
            observado = new Observado();
        }
        else{
            //En caso contrario se avisa de que no se puede crear una nueva instancia
            System.out.println("No se puede crear el objeto "+ nombre + " porque ya existe un objeto de la clase SoyUnico");
        }
        //Se devuelve la instancia de la clase
        return observado;
    }
}

