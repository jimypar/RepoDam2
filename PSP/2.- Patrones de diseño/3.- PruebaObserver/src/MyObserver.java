import java.util.Observable;
import java.util.Observer;

/**
 * Clase que implementa un ejemplo de observador
 *
 * @author Teru
 */
class MyObserver implements Observer {

    /**
     * Método que se activa cuando el observador recibe una notificación de que se
     * ha producido una actualización
     *
     * @param o objeto de la clase Observable que representa el observado que ha
     *          enviado la notificación
     *
     * @param o1 objeto que recibe el observador por parte del observado
     */
    @Override
    public void update(Observable o, Object o1) {
        //Cuando se recibe una actualización se imprime un mensaje
        System.out.println("Uppsss Something happened!");
    }

}
