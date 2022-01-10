import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Clase que proporciona un hilo de ejecución a cada conexión que es aceptada
 * por el servidor
 *
 */
public class ServidorHilo extends Thread {
    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private int idSessio;

    /**
     * Constructor de la clase
     *
     * @param socket socket de conexión con el cliente
     * @param id int que representa el id de sesion asignado por el servidor
     */
    public ServidorHilo(Socket socket, int id) {
        this.socket = socket;
        this.idSessio = id;
        try {
            //Se crean los canales de comunicación con el cliente
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que proporciona
     */
    public void desconnectar() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que activa el código a ejecutar cuando se inicia el hilo
     */
    @Override
    public void run() {
        String accion = "";
        try {
            //obtiene el mensaje del cliente
            accion = dis.readUTF();
            if(accion.equals("hola")){
                System.out.println("El cliente con idSesion "+this.idSessio+" saluda");
                //Escribe un mensaje al cliente
                dos.writeUTF("adios");
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
        //realiza la desconexión
        desconnectar();
    }
}