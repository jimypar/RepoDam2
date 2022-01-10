import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Cliente {

    private int puerto;
    private String host;
    private Socket socket;

    public Cliente(String host, int puerto) {
        this.puerto = puerto;
		this.host = host;
    }


    public void conectar() throws IOException {

        socket = new Socket(host, puerto);

        // Declaramos un DataInputStream para leer los datos
        DataInputStream entrada = new DataInputStream(socket.getInputStream());

        // Declaramos un DataOutputStream para escribir los datos
        PrintStream salida = new PrintStream(socket.getOutputStream());

        // Creamos un String para la informacion recibida
        String recibida;

        salida.println("Primero");
        //salida.println("Segundo");
        //salida.println("Postre");
        //salida.println("Bebida");

        //Se obtiene la respuesta del servidor y se muestra
        recibida = entrada.readLine();
        System.out.println(recibida);

        //Se cierra en canal de entrada
        entrada.close();
        //Se cierra el canal de salida
        salida.close();
        //Se cierra el socket
        socket.close();
    }


    public static void main(String[] args) throws IOException {
        //Se crea un nuevo cliente
        Cliente c = new Cliente("localhost",4444);
        //Se conecta al servidor
        c.conectar();
    }


}
