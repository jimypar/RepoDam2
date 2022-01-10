import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

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

                //Declaramos un PrintStream para escribir datos
                PrintStream salida = new PrintStream(cienteConectado.getOutputStream());

                // Creamos un String recibido para cuando recibamos la orden del cliente
                String recibido = "";

                //Declaramos un DataInputStream para leer los datos
                DataInputStream entrada = new DataInputStream(cienteConectado.getInputStream());

                recibido = entrada.readLine();
                System.out.println("Mensaje Recibido: " + recibido);


                switch (recibido) {
                    //En caso de ser el primer plato
                    case "Primero":
                        /* Se llama el método que escribe el primer plato */
                        enviarPrimeros(salida);
                        break;
                    //En caso de ser el segundo plato
                    case "Segundo":
                        /* Se llama el método que escribe el segundo plato */
                        enviarSegundos(salida);
                        break;
                    //En caso de ser el postre
                    case "Postre":
                        /* Se llama el método que escribe el postre */
                        EnviarPostres(salida);
                        break;
                    //En caso de no ser ninguno
                    default:
                        EnviarError(salida);
                }
                //salida.println("Pong");

                //Se cierra el canal de entrada
                entrada.close();
                //Se cierra el canal de salida
                salida.close();
                //Se cierra el socket
                cienteConectado.close();

            }

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que retorna al cliente el primer plato
     *
     * @param salida PrintStream que representa el canal de escritura por socket
     *               del servidor al cliente
     */
    private void enviarPrimeros(PrintStream salida) {
        String primeros = "Sopa de cocido";
        salida.println(primeros);
    }

    /**
     * Método que retorna al cliente el segundo plato
     *
     * @param salida PrintStream que representa el canal de escritura por socket
     *               del servidor al cliente
     */
    private void enviarSegundos(PrintStream salida) {
        String segundos = "Bistec de ternera";
        salida.println(segundos);
    }

    /**
     * Método que retorna al cliente el postre
     *
     * @param salida PrintStream que representa el canal de escritura por socket
     *               del servidor al cliente
     */
    private void EnviarPostres(PrintStream salida) {
        String postre = "Tarta de queso";
        salida.println(postre);
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
