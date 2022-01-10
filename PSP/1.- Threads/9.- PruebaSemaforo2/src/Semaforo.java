
import java.util.concurrent.Semaphore;

/**
 *
 * @author Teru
 */
public class Semaforo implements Runnable {

    /**
     * Indica el n&uacute;mero de procesos que se pueden ejecutar al tiempo.
     */
    //private static final Semaphore DISPONIBILIDAD = new Semaphore(2);
    /**
     * Nombre del proceso.
     */
    private final String nombre;
    private static Semaphore sem;

    public Semaforo(String nombre, Semaphore sem) {
        this.nombre = nombre;
        this.sem = sem;
    }

    @Override
    public void run() {
        try {

            // Solicita disponibilidad.


            for (int i=1;i<=10; i++){
                //System.out.println("El proceso [ " + this.nombre + " ] dormira " + "por 5 segundos");
                sem.acquire();
                System.out.println(this.nombre);
                sem.release();
                Thread.sleep(1000);

            }


            //System.out.println("Finaliza el proceso [ " + this.nombre + " ]");

            // Libera disponibilidad.

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
