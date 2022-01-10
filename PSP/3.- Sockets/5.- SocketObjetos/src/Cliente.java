import java.io.*;
import java.net.Socket;

public class Cliente {

    private int puerto;
    private String host;
    private Socket socket;


    /**
     *Clase Cliente. Representa a la aplicación cliente del ejercicio.
     *
     * @author Teru
     */
    public Cliente(String host, int puerto) {
        this.puerto = puerto;
		this.host = host;
    }


    public void conectar() throws IOException, ClassNotFoundException {

        socket = new Socket(host, puerto);

        // Declaramos un DataInputStream para leer los datos
        ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());

        // Declaramos un DataOutputStream para escribir los datos
        ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());

        //Se crea una persona
        Persona p = new Persona("Felipe Juan Froilan de todos los Santos", "De Marichalar", "De borbón", 20);

        System.out.println("Envio Mensaje: "+p);
        //Se envía la persona al servidor por el socket
        salida.writeObject(p);

        //Se obtiene la persona que envía el servidor
        p = (Persona) entrada.readObject();

        //Se escriben los datos de la persona obtenida
        System.out.println("Mensaje Recibido: " + p);

        //Se cierra en canal de entrada
        entrada.close();
        //Se cierra el canal de salida
        salida.close();
        //Se cierra el socket
        socket.close();
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //Se crea un nuevo cliente
        Cliente c = new Cliente("localhost",4444);
        //Se conecta al servidor
        c.conectar();
    }
}
