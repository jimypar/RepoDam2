import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase auxiliar de cliente que extiende de Threads y será la encargada de
 * conectar y realizar la omunicacion con el servidor
 *
 */
class Persona extends Thread {
    protected Socket sk;
    //Se crean los canales de comunicación
    protected DataOutputStream dos;
    protected DataInputStream dis;
    private int id;
    public Persona(int id) {
        this.id = id;
    }
    @Override
    public void run() {
        try {
            //conecta con el servidor
            sk = new Socket("127.0.0.1", 10578);
            //Y crea los canales de comunicación
            dos = new DataOutputStream(sk.getOutputStream());
            dis = new DataInputStream(sk.getInputStream());
            System.out.println(id + " envía saludo");
            //Escribe al servidor
            dos.writeUTF("hola");
            String respuesta="";
            //Y espera la respuesta
            respuesta = dis.readUTF();
            //La muestra
            System.out.println(id + " Servidor devuelve saludo: " + respuesta);
            //Cierra los canales de comunicación
            dis.close();
            dos.close();
            sk.close();
        } catch (IOException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


/**
 * Clase de prueba que crea 5 clientes (personas) y cada una de ellas
 * realiza una conexión independiente con el servidor
 *
 */
public class Cliente {
    public static void main(String[] args) {
        //Se crea un arrayList de clientes
        ArrayList<Thread> clients = new ArrayList<Thread>();
        for (int i = 0; i < 5; i++) {
            clients.add(new Persona(i));
        }
        //Se arranca a todos los clientes
        for (Thread thread : clients) {
            thread.start();
        }
    }
}