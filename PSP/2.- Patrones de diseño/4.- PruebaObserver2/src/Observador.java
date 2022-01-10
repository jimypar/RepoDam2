import java.util.Observable;
import java.util.Observer;

/**
 * Clase Observador, que representa a un observador que estará esperando
 * una actualizacion del Observado
 *
 * @author Teru
 */
class Observador implements Observer {

    /**
     * Método que se dispara de manera automática cuando se recibe una
     * notificacion del observado.
     *
     * @param o que representa el Observable (observado) que ha realizado la
     *          notificación.
     *
     * @param o1 Objeto que enviado por el observado
     */
    @Override
    public void update(Observable o, Object o1) {
        //Se muestra el objeto recogido
        System.out.println("Nueva Actualizacion: "+o+" -> "+o1);
    }
}
