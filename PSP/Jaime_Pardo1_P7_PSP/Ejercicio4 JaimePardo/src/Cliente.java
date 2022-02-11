import java.net.*;
import java.io.*;

public class Cliente {

    private DataInputStream entrada;
    private PrintStream salida;
    private Socket socket;

    private IniciarSesionGUI gui;
    private String server;
    private int port;

    private boolean conectado;

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    //Constructor del cliente con el nombre del host,el puerto y la interfaz grafica.
    Cliente(String server, int port, IniciarSesionGUI gui) {
        this.server = server;
        this.port = port;
        this.gui = gui;
    }

    //Metodo que crea el socket del cliente,la entrada, la salida y llama al hilo de escucha.
    public boolean iniciar() {
        try {
            socket = new Socket(server, port);
        }
        catch(Exception ec) {
            return false;
        }

        try{
            entrada = new DataInputStream(socket.getInputStream());
            salida = new PrintStream(socket.getOutputStream());
        }
        catch (IOException eIO) {
        }

        Escuchar escuchar = new Escuchar();
        escuchar.start();

        return true;
    }

    //Metodo que envia un mensaje al servidor.
    void enviarMensaje(String msg) {
        salida.println(msg);
    }

    //Hilo que esta escuchando la entrada de un mensaje y lo manda a la GUI
    class Escuchar extends Thread {

        public void run() {
            while(true) {
                try {
                    String msg = entrada.readLine();
                    gui.recibir(msg);
                }
                catch(IOException e) {
                }
            }
        }
    }

}
