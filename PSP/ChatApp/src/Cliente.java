import java.net.*;
import java.io.*;

public class Cliente {

    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    private Socket socket;

    private ClienteGUI gui;

    private String server, username;
    private int port;

    Cliente(String server, int port, String username, ClienteGUI gui) {
        this.server = server;
        this.port = port;
        this.username = username;
        this.gui = gui;
    }

    public boolean iniciar() {
        try {
            socket = new Socket(server, port);
        }
        catch(Exception ec) {
            return false;
        }

        String msg = "Conectado";
        mostrar(msg);

        try
        {
            entrada = new ObjectInputStream(socket.getInputStream());
            salida = new ObjectOutputStream(socket.getOutputStream());
        }
        catch (IOException eIO) {
        }

        Escuchar escuchar = new Escuchar();
        escuchar.start();

        try
        {
            salida.writeObject(username);
        }
        catch (IOException eIO) {
            desconectar();
            return false;
        }

        return true;
    }

    private void mostrar(String msg) {
        gui.append(msg + "\n");
    }

    void enviarMensaje(Mensaje msg) {
        try {
            salida.writeObject(msg);
        }
        catch(IOException e) {
        }
    }

    private void desconectar() {
        try {
            if(entrada != null) entrada.close();
        }
        catch(Exception e) {}
        try {
            if(salida != null) salida.close();
        }
        catch(Exception e) {}
        try{
            if(socket != null) socket.close();
        }
        catch(Exception e) {}

        gui.falloConexion();

    }

    class Escuchar extends Thread {

        public void run() {
            while(true) {
                try {
                    String msg = (String) entrada.readObject();
                    gui.append(msg);
                }
                catch(IOException | ClassNotFoundException e) {
                    gui.falloConexion();
                    break;
                }
            }
        }
    }
}
