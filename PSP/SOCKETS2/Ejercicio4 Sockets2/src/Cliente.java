import java.net.*;
import java.io.*;

public class Cliente {

    private DataInputStream entrada;
    private PrintStream salida;
    private Socket socket;

    private ClienteGUI gui;

    private String server;
    private int port;

    //Constructor del cliente con el puerto y la GUI.
    Cliente(String server, int port, ClienteGUI gui) {
        this.server = server;
        this.port = port;
        this.gui = gui;
    }

    //Metodo que inicia el cliente con el socket, la entrada, la salida y inicia el hilo de escucha.
    public boolean iniciar() {
        try {
            socket = new Socket(server, port);
        }
        catch(Exception ec) {
            return false;
        }

        try
        {
            entrada = new DataInputStream(socket.getInputStream());
            salida = new PrintStream(socket.getOutputStream());
        }
        catch (IOException eIO) {
        }

        Escuchar escuchar = new Escuchar();
        escuchar.start();

        return true;
    }

    //Metodo que envia un mensaje al servidor
    void enviarMensaje(String msg) {
        salida.println(msg);
    }


    //Hilo que recibe mensaje y se los envia al GUI.
    class Escuchar extends Thread {

        public void run() {
            while(true) {
                try {
                    String msg = entrada.readLine();
                        gui.append(msg);
                }
                catch(IOException e) {
                }
            }
        }
    }
}
