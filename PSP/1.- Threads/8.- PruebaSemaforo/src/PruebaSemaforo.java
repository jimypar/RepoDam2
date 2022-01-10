import java.util.concurrent.Semaphore;

/**
 *
 * @author Teru
 */




public class PruebaSemaforo {


    private static Semaphore DISPONIBILIDAD;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DISPONIBILIDAD = new Semaphore(2);
        new Thread(new Semaforo("SI",DISPONIBILIDAD)).start();
        new Thread(new Semaforo("NO",DISPONIBILIDAD)).start();
        // Ejecutamos 10 procesos.


    }

}
