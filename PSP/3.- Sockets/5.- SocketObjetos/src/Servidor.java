import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *Clase Servidor. Representa a la aplicación servidor del ejercicio.
 *
 * @author Teru
 */
public class Servidor {

    private ServerSocket socketServidor;
    private int puerto;

    /**
     * Constructor de la clase Servidor. Esta clase inicia un servidor trivial que espera una conexión
     * de una aplicacion cliente por socket. Una vez recibida la conexion de un cliente el servidor
     * escribirá un código, el servidor lo interpretará y devolverá la información
     * que el cliente ha solicitado o un aviso en caso de error
     *
     * @param puerto int que representa el puerto por el que se realizará la conexión a través de socket.
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
    private void arrancarServidor() {

        //control de errores
        try {
            //Se crea el ServerSocket
            socketServidor = new ServerSocket(this.puerto);

            while (true) {
                /*  Se crea el socket de conexión del cliente para trabajar con él.
                    En un caso más real se utilizaría para crear los canales de
                    comunicacion de entrada y salida con la aplicación cliente
                */
                Socket cienteConectado = socketServidor.accept();

                //Declaramos un ObjectOutputStream para escribir datos
                ObjectOutputStream salida = new ObjectOutputStream(cienteConectado.getOutputStream());

                //Declaramos un ObjectOutputStream para leer los datos
                ObjectInputStream entrada = new ObjectInputStream(cienteConectado.getInputStream());

                //Se obtiene una Persona desde el cliente
                Persona p = (Persona) entrada.readObject();
                System.out.println("Mensaje Recibido: " + p);

                //Modifico el nombre y la edad de la persona
                p.setNombre("Vicente");
                p.setEdad(33);

                //Envío el objeto por el socket al cliente
                salida.writeObject(p);

                //Se cierra el canal de entrada
                entrada.close();
                //Se cierra el canal de salida
                salida.close();
                //Se cierra el socket
                cienteConectado.close();

            }

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * Método que retorna al cliente un mensaje en caso de error
     *
     * @param salida PrintStream que representa el canal de escritura por socket
     *               del servidor al cliente
     */
    private void EnviarError(PrintStream salida) {
        salida.println("Error");
    }


    public static void main(String[] args) throws IOException {
        //Se arranca el servidor
        Servidor s = new Servidor(4444);
    }

}
