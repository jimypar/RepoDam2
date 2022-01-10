
/**
 * Clase que extiende de Thread y escribe un mensaje introducido por parametro
 */
class SiNo3Thread extends Thread {
    private String SiNo;// mensaje que escribirá el hilo
    static int Contador=0;

    /**
     * Constructor de la clase SiNoThread. Se introduce como parámetro un String
     * que reprensenta el mensaje que escribira el proceso
     *
     * @param s String que representa el parametro que escribirá elhijo cuando
     *          esté en ejecución
     *
     */
    public SiNo3Thread(String s) {
        super();
        SiNo=s;
    }

    /* Método que se pondrá en ejecución cuando se llame al método start() del hilo.
     * Se mostrarán los mensajes de iteración.
     * */
    public void run() {
        int i;
        //Realiza las iteraciones
        for (i=1;i<=10; i++) {
            //Muestra el mensaje
            System.out.print(++Contador+":"+SiNo+" ");
            yield();
        }
    }
}

/**
 *
 * @author Teru
 */
public class SiNo3 {
    public static void main(String[] args) throws InterruptedException {
        SiNo3Thread s = new SiNo3Thread("SI");
        SiNo3Thread n = new SiNo3Thread("NO");
        n.start();
        n.join();
        s.start();

    }
}

