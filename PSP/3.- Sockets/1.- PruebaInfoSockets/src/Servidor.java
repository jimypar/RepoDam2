import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Teru
 */
public class Servidor {

    private ServerSocket socketServidor;
    //public static final int PUERTO = 4444;
    private int puerto;


    /**
     * Constructor de la clase Servidor. Esta clase inicia un servidor trivial que espera una conexión
     * de una aplicacion cliente por socket. Una vez recibida la conexion de un cliente muestra sus datos
     * de conexión.
     *
     *
     * @param puerto  int que representa el puerto por el que se realizará la conexión a través de socket.
     */
    public Servidor(int puerto) {
        this.puerto = puerto;
        //Se inicia el servidor
        arrancarServidor();
    }


    /**
     * Método privado que arranca de manera automatica el servidor. Esperará que se realice la
     * conexión de una aplicación cliente
     */
    private void arrancarServidor(){

        //control de errores
        try {
            //Se crea el ServerSocket
            socketServidor = new ServerSocket(this.puerto);
            /* Se crea el socket de conexión del cliente para trabajar con él.
               En un caso más real se utilizaría para crear los canales de
               comunicacion de entrada y salida con la aplicación cliente
            */
            Socket cienteConectado = socketServidor.accept();

            //Se obtiene el InetAdress de la conexión del cliente
            InetAddress inet = cienteConectado.getInetAddress();
            /* Se muestran datos de conexión del cliente obtenidos del InetAddress */
            System.out.println("HostAdress => "+ inet.getHostAddress());
            System.out.println("Nombre => "+ inet.getHostName());
            System.out.println();
            /* Se muestrandatos del cliente obtenidos del socket del cliente*/
            System.out.println("Dirección Local=> " + cienteConectado.getLocalAddress());
            System.out.println("Puerto Local=> "+ cienteConectado.getLocalPort());
            System.out.println("SocketAdress => " + cienteConectado.getLocalSocketAddress());

            cienteConectado.close();

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws IOException {
        Servidor s = new Servidor(4444);
    }

}
