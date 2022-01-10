import java.util.Observable;

/**
 * Clase que representa a un observado o observable, siguiendo las bases del
 * patron de diseño observer
 *
 * @author Teru
 */

public class MyObservable extends Observable implements Runnable{

    /**
     * Método que lanza el aviso a los observadores
     */
    public void fire_event() {
        //Se llama al método que avisa a los observadores
        this.notifyObservers("Ey! What's up?");
        //Se fuerza a que se marque que hay una actualización
        this.setChanged();
    }

    /**
     * Método que se ejecuta cuando se lanza el Thread de la clase
     *
     */
    @Override
    public void run() {
        while (true) {
            //Se avisa de que hay una actualización
            fire_event();
            //Se llama el método sleep para hacer una pausa en el proceso
            sleep();
        }
    }

    /**
     * Método que realiza una pausa en la ejecución del Thread de la clase
     *
     */
    private void sleep() {
        try {
            //Se hace una pausa de 0.1 seg.
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
