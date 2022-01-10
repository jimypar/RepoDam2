/**
 * Clase que extiende de Thread y escribe un mensaje introducido por parametro
 */
class SiNoThread extends Thread {

    private String SiNo;  // mensaje que escribirá el hilo
    static int Contador=0;

    /**
     * Constructor de la clase SiNoThread. Se introduce como parámetro un String
     * que reprensenta el mensaje que escribira el proceso
     *
     * @param s String que representa el parametro que escribirá elhijo cuando
     *          esté en ejecución
     *
     */
    public SiNoThread(String s) {
        super();
        SiNo=s;
    }

    /* Método que se pondrá en ejecución cuando se llame al método start() del hilo.
     * Se mostrarán los mensajes de iteración.
     * */
    public void run() {
        int i;
        //Realiza las iteraciones
        for (i=1;i<=10; i++)
            //Muestra el mensaje
            System.out.print(++Contador+":"+SiNo+" ");
    }
}


/**
 * Clase de prueba de la clase SiNoThread. Creará dos procesos hijos. Uno de ellos
 * escribirá por pantalla el texto "SI" mientras que el otro escribirá el texto "NO"
 * @author Teru
 */
public class SiNo2 {

    /**
     * Programa principal de prueba
     *
     */
    public static void main(String[] args) {
        //Se crea la instancia del hilo que escribirá SI
        SiNoThread s = new SiNoThread("SI");
        //Se crea la instancia del hilo que escribirá NO
        SiNoThread n = new SiNoThread("NO");
        //Se arrancan los dos hilos
        s.start();
        n.start();
    }
}

