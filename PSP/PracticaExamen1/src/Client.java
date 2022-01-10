import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private int puerto;
    private String host;
    private Socket socket;

    public static void main(String[] args) throws IOException {
        Client c = new Client("localhost",4444);
        c.conectar();
    }

    public Client(String host, int puerto) {
        this.puerto = puerto;
        this.host = host;
    }

    private void conectar() throws IOException {

        socket = new Socket(host, puerto);

        DataInputStream entrada = new DataInputStream(socket.getInputStream());
        PrintStream salida = new PrintStream(socket.getOutputStream());

        String mensaje;
        Scanner scan = new Scanner(System.in);
        mensaje=scan.nextLine();
        salida.println(mensaje);
        mensaje=scan.nextLine();
        salida.println(mensaje);

        mensaje = entrada.readLine();
        System.out.println(mensaje);


    }

}
