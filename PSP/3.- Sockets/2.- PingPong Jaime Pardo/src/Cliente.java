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

        for (int i = 0;i<50; i++){
            enviar(salida);
            recibir(entrada);
        }



        //Se cierra en canal de entrada
        entrada.close();
        //Se cierra el canal de salida
        salida.close();
        //Se cierra el socket
        socket.close();
    }

    private void recibir(DataInputStream entrada) throws IOException {
        String recibida;
        recibida = entrada.readLine();
        System.out.println("Soy el cliente, y he recibido un "+recibida);
    }

    private void enviar(PrintStream salida) {
        salida.println("Ping");
    }


    public static void main(String[] args) throws IOException {
        Cliente c = new Cliente("localhost",4444);
        c.conectar();

    }


}
