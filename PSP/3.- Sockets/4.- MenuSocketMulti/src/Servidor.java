import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase Servidor que es capaz de trabajar con varios clientes a la vez
 * gracias a la utilización de Threads
 *
 */
public class Servidor {
    public static void main(String args[]) throws IOException {
        ServerSocket ss;
        System.out.print("Inicializando servidor... ");
        try {
            //Se crea el ServerSocket
            ss = new ServerSocket(10578);
            System.out.println("\t[OK]");
            //Se crea un id de sesión
            int idSession = 0;
            //Se crea el bucle para recibir las peticiones de los clientes
            while (true) {
                Socket socket;
                socket = ss.accept(); //Se aceptan las peticiones de los clientes
                System.out.println("Nueva conexión entrante: "+socket);
                /*Se crea un hilo por cada petición para poder atenderla de
                forma independiente, y a la vez. Todo el proceso de comunicación
                se realizará a través de este Thread */
                ((ServidorHilo) new ServidorHilo(socket, idSession)).start();
                idSession++;
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}