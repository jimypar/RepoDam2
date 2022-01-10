import java.util.Observable;

/**
 * Clase Observado que representa a una clase Observable u observada.
 * Será la clase que notificará a los observadores cuando se produzca
 * alguna actualizació
 *
 * @author Teru
 */
class Observado extends Observable {

    String mensaje;

    /**
     * Constructor de la clase
     */
    public Observado(){
        //Se le dá valor al mensaje
        mensaje = "Objeto Observado Iniciado";
    }


    /**
     * Método set que asigna un nuevo mensaje a enviar. Avisa de que se ha producido
     * una actualización y se notifica a los observadores que esten pendientes.
     *
     * @param m String que representa el mensaje que se enviará a los observadores
     */
    public void cambiarMensaje(String m){

        mensaje = m;
        //Marcamos el objeto observable como objeto que ha cambiados
        setChanged();
        //Notificamos a los observadores y le enviamos el nuevo valor
        notifyObservers(mensaje);
        //notifyObservers(); Este metodo solo notifica que hubo cambios en el objeto

    }
}
