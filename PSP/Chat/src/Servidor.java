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
    Socket cienteConectado;
    Socket clienteConectado2;
    static int i=0;
    String recibido;
    PrintStream salida;
    DataInputStream entrada;
    DataInputStream entrada2;
    PrintStream salida2;

    /**
     * Constructor de la clase Servidor. Esta clase inicia un servidor trivial que espera una conexión
     * de una aplicacion cliente por socket. Una vez recibida la conexion de un cliente el servidor
     * escribirá Ping, que será mostrado porn el cliente, y este le responderá PONG.
     *
     *
     * @param puerto  int que representa el puerto por el que se realizará la conexión a través de socket.
     */
    public Servidor(int puerto) throws IOException {
        this.puerto = puerto;
        //Se inicia el servidor
        arrancarServidor();
    }




    /**
     * Método privado que arranca de manera automatica el servidor. Esperará que se realice la
     * conexión de una aplicación cliente
     */
    private void arrancarServidor() throws IOException {


        //control de errores
        try {
            //Se crea el ServerSocket
            socketServidor = new ServerSocket(this.puerto);

            /* Se crea el socket de conexión del cliente para trabajar con él.
               En un caso más real se utilizaría para crear los canales de
               comunicacion de entrada y salida con la aplicación cliente
            */
            cienteConectado = socketServidor.accept();
            clienteConectado2 = socketServidor.accept ();
            //Declaramos un PrintStream para escribir datos
            salida= new PrintStream(cienteConectado.getOutputStream());
            entrada = new DataInputStream(cienteConectado.getInputStream());
            entrada2 = new DataInputStream(clienteConectado2.getInputStream());
            salida2 = new PrintStream(clienteConectado2.getOutputStream());
            // Creamos un String recibido para cuando recibamos la orden del cliente
            //Declaramos un DataInputStream para leer los datos



          for(int j=0; j>=0;j++) {
              if (i %2==0) {
                  this.recibido = leer ( entrada );
                  escribir2 ( "Cliente 1: "+this.recibido );
                  i++;

              }else{
                  this.recibido = leer2 ( entrada2 );
                  escribir ( "Cliente 2:" + this.recibido );
                  i++;

              }

          }
            //Se cierra el canal de entrada
            entrada.close();
            entrada2.close ();
            //Se cierra el canal de salida
            salida.close();
            salida2.close ();
            //Se cierra el socket
            cienteConectado.close();
            clienteConectado2.close ();

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String leer(DataInputStream entrada) throws IOException {

        recibido = entrada.readLine();
        System.out.println("Soy el servidor, he recibido un "+this.recibido);
        return  this.recibido;
    }

    private String leer2(DataInputStream entrada2) throws IOException {

        recibido = entrada2.readLine();
        System.out.println("Soy el servidor, he recibido un "+this.recibido);
        return  this.recibido;
    }

    private void escribir(String recibido1) throws IOException {
        this.salida.println (recibido1);


    }
    private void escribir2(String recibido2) throws IOException {
        this.salida2.println (recibido2);


    }


    public static void main(String[] args) throws IOException {
        Servidor s = new Servidor(4444);
    }




}
