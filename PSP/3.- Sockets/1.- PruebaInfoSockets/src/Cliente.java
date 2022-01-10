import java.io.IOException;
import java.net.Socket;

/**
 * @author Teru
 */
public class Cliente {


    private int puerto;
    private String host;
    private Socket socket;

    /**
     * Constructor de la clase clienteç
     *
     * @param host String que representa la dirección de conexión
     * @param puerto int que representa el puerto por el que se
     *               creará el socket para la conexión
     */
    public Cliente(String host, int puerto) {
        this.puerto = puerto;
        this.host = host;
    }

    /**
     * método que realiza la conexión con el servidor a través del
     * socket
     *
     * @throws IOException excepción que es lanzada por el método
     * en caso de que se produzca una excepción
     */
    public void conectar() throws IOException {
        socket = new Socket(host, puerto);

    }


    /**
     * Método de prueba de la clase cliente
     *
     * @throws IOException excepción que es recogida y lanzada en
     * caso de error de conexión
     */
    public static void main(String[] args) throws IOException {
        Cliente c = new Cliente("localhost", 4444);
        c.conectar();
    }
}
