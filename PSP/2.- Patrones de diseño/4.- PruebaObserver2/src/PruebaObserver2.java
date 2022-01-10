/**
 * Clase de prueba que se utiliza para probar las clases Observado y Observador
 *
 * @author Teru
 */

public class PruebaObserver2 {

    /**
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Se crea el observado
        Observado observado = new Observado();

        //Se crea un observador
        Observador observador = new Observador();

        //Se añade el observador al observado
        observado.addObserver(observador);

        //Se fuerzan las notificaciones que envía el observado al obervador
        observado.cambiarMensaje("Cambio 1");
        observado.cambiarMensaje("Cambio 2");
        observado.cambiarMensaje("Cambio 3");
    }

}
