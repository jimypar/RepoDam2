

class Hilo extends Thread {

    String msg = "";

    /**
     * Constructor de la clase SiNoThread. Se introduce como parámetro un String
     * que reprensenta el mensaje que escribira el proceso
     * @param name String que representa el parametro que escribirá elhijo cuando
     *             esté en ejecución
     */
    public Hilo(String name) {
        this.msg = name;
    }


    /* Método que se pondrá en ejecución cuando se llame al método start() del hilo.
     * Se mostrarán los mensajes de iteración.
     * */
    public void run() {

        synchronized (getClass()) {
            /*Se realizan 10 iteraciones */
            for (int i = 0; i < 10; i++) {
                //Se escribe el mensaje
                System.out.print(msg);
                //Se vacía el buffer para forzar a que se escriba el mensaje
                System.out.flush();
                //Se realiza la notificacion
                getClass().notifyAll();
                try {
                    //Se espera el proceso hasta que reciba una notificación
                    getClass().wait();
                } catch (InterruptedException e) {
                    System.err.println("ERROR en el Try");
                }
            }
            //Se realiza una notificación a los procesos restantes
            getClass().notifyAll();
        }

    }
}





/**
 *
 * @author Teru
 */
public class SiNo5 {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        Hilo s = new Hilo("SI");
        Hilo n = new Hilo("NO");
        n.start();
        s.start();
    }

}
