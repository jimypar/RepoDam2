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
            while(datos.size()<2){
                datos.add(entrada.readLine());
            }
            salida.println(resultado());

        }
        catch (IOException e) {
            System.out.println("Error al crear Servidor");
        }

    }

    private String resultado() {

        return datos.get(0)+datos.get(1);

    }


}
