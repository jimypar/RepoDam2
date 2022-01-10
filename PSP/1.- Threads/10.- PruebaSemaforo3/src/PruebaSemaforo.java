
import java.util.concurrent.Semaphore;

/**
 *
 * @author Teru
 */
public class PruebaSemaforo {


    private static Sumador sumadores[];
    private static Semaphore sem = new Semaphore(1);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        int n_sum = 5;
        sumadores = new Sumador[n_sum];

        for (int i = 0; i<n_sum; i++){
            sumadores[i] = new Sumador(1000, i, sem);
            sumadores[i].start();
        }

        for (int i = 0; i<n_sum; i++){
            sumadores[i].join();
        }

        System.out.println("Acumulador: "+Acumula.acumulador);
    }

}
