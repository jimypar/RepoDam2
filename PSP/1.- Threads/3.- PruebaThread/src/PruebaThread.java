
/**
 *
 * @author Teru
 *
 * Clase utilizada para realizar una prueba sencilla de Threads.
 * Se crea un proceso hijo (PruebaThread) que realiza iteraciones en las
 * que muestra un mensaje. A su vez el proceso padre realiza la misma labor
 * que el hijo. Se busca el observar como se entrelazan las iteraciones de los
 * procesos (padre e hijo)
 *
 */
public class PruebaThread {

    /**
     * Programa principal de prueba
     *
     */
    public static void main(String[] args) {
        // Se crea el proceso hijo
        PruebaHilo hilo = new PruebaHilo();
        hilo.start(); //Se arranca el proceso hijo

        //Se realizan las iteraciones
        for (int i=1;i<=PruebaHilo.ITERACIONES; i++){
            //En cada iteracion se muestra el mensaje
            System.out.println("Dentro del Programa Principal : " + i);
        }
    }
}

