import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private int port;
    private ArrayList<String> datos;

    public Server(int port) {
        this.port = port;
        this.datos = new ArrayList<>();
    }

    public static void main(String[] args) {
        Server server = new Server(4444);
        server.start();
    }

    private void start() {

        try{
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            PrintStream salida = new PrintStream(socket.getOutputStream());
            String mensaje = entrada.readLine();
            salida.println(resultado1(mensaje));
            salida.println(resultado2(mensaje));

            serverSocket.close();
            socket.close();
            entrada.close();
            salida.close();

        }
        catch (IOException e) {
            System.out.println("Error al crear Servidor");
        }

    }

    private int resultado2(String mensaje) {

        int result = Integer.parseInt(mensaje)*2;

        return result;

    }

    private int resultado1(String mensaje) {

        int result = Integer.parseInt(mensaje);

        return result;

    }


}
