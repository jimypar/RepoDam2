/**
 * Clase NoThread, cuya finalidad es escribir un conjunto de veces (20)
 * la palabra NO
 */
class NoThread extends Thread {
    public void run() {
        int i;
        //Se realizan 20 iteraciones
        for (i = 1; i <= 20; i++)
            //Se escribe el mensaje
            System.out.print("NO ");
    }
}

/**
 * Clase SiThread, cuya finalidad es escribir un conjunto de veces (20)
 * la palabra SI
 */
class SiThread extends Thread {
    public void run() {
        int i;
        //Se realizan 20 iteraciones
        for (i = 1; i <= 20; i++)
            //Se escribe el mensaje
            System.out.print("SI ");
    }
}


/**
 * Clase de prueba de las clases NoThread y SiThread. Crea un hilo de ejecución
 * de cada clase para que escriban sus mensajes y se pueda comprobar la forma en
 * que se ejecutan los hilos, entrelanzo susmensajes
 *
 * @author Teru
 */
public class SiNo {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Se crea una instancia de cada clase
        NoThread n = new NoThread();
        SiThread s = new SiThread();
        //Se arranca la ejecución de ambos hilos
        n.start();
        s.start();
    }

}


