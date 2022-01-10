
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Teru
 */
public class Sumador extends Thread{
    private int cuenta;
    private Semaphore sem;

    Sumador(int hasta, int id, Semaphore sem){
        this.cuenta = hasta;
        this.sem = sem;
    }


    public void sumar(){
        Acumula.acumulador ++;
    }


    public void run(){
        for (int i=0;i<cuenta; i++){
            try{
                sem.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(Sumador.class.getName()).log(Level.SEVERE, null, ex);
            }
            sumar();
            sem.release();
        }
    }

}
