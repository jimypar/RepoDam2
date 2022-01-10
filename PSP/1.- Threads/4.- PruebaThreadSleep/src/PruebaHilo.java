
/**
 *
 * @author Teru
 *
 * Clase que crea un proceso hijo que realiza iteraciones en las que muestra un
 * mensaje.
 *
 */

public class PruebaHilo extends Thread {

    //Iteraciones que se realizarán en la aplicación
    public static int ITERACIONES = 10;

    /* Constructor vacio */
    public PruebaHilo() {

    }


    /* Método que se pondrá en ejecución cuando se llame al método start() del hilo.
     * Se mostrarán los mensajes de iteración haciendo una pausa. Para ello hace uso
     * del método sleep
     * */
    @Override
    public void run() {
        //Se realizan las iteraciones
        for (int i=1;i<=ITERACIONES; i++){
            //En cada iteracion se muestra el mensaje
            System.out.println("Dentro del hilo : " + i);
            try {
                //En cada iteracion se hace una pequeña pausa
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
