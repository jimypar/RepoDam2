import java.net.*;
import java.io.*;

public class Cliente {

    private DataInputStream entrada;
    private PrintStream salida;
    private Socket socket;

    private ClienteGUI gui;

    private String server;
    private int port;

    //Constructor cliente
    Cliente(String server, int port, ClienteGUI gui) {
        this.server = server;
        this.port = port;
        this.gui = gui;
    }


    public boolean iniciar() {
        //Crear socket cliente
        try {
            socket = new Socket(server, port);
        }
        catch(Exception ec) {
            return false;
        }

        String msg = "Introduce un año";
        mostrar(msg);

        //Se crea el socket, el PrintStream y el DataInputStream
        try
        {
            entrada = new DataInputStream(socket.getInputStream());
            salida = new PrintStream(socket.getOutputStream());
        }
        catch (IOException eIO) {
        }
        //Crear metodo que escucha los mensajes
        Escuchar escuchar = new Escuchar();
        escuchar.start();

        return true;
    }

    //Muestra el mensaje en la pantalla
    private void mostrar(String msg) {
        gui.append(msg + "\n");
    }

    //Envia mensaje al servidor
    void enviarMensaje(String msg) {
        salida.println(msg);
    }

    //Hilo que lee constantemente del servidor
    class Escuchar extends Thread {

        public void run() {
            while(true) {
                try {
                    String msg = entrada.readLine();
                        gui.append(msg+"\n");
                }
                catch(IOException e) {
                    gui.falloConexion();
                    break;
                }
            }
        }
    }
}
