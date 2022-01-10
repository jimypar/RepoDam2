/**
 * Clase de prueba, destinada a probar las clases Observado y Observador
 *
 * Hay que tener en cuenta que la clase observado implementa tambien el
 * patron de diseño Singleton
 *
 * @author Teru
 */
public class PruebaObserverSingleton {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Se crea un Observado utilizando Patrón de diseño Singleton
        Observado observado = Observado.getSingletonInstance("Ricardo Moya");

        //Se crea un nuevo observador
        Observador observador = new Observador();

        /*Se añade un observador al observado. De esta manera cuando se produzca una actualización se notificará al observador */
        observado.addObserver(observador);

        //Se fuerza a que el observado a que realice tres cambios
        observado.cambiarMensaje("Cambio 1");
        observado.cambiarMensaje("Cambio 2");
        observado.cambiarMensaje("Cambio 3");

    }

}
