import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {

    public static void main(String[] args) {
        //Crea servidor mandando el parametro del puerto
        Servidor servidor = new Servidor(4444);
        //Iniciar servidor
        servidor.start();
    }

    private static int idUsuario;
    private ArrayList<HiloCliente> clientes;
    private ArrayList<String> years;
    private ArrayList<String> incremento;
    private int puerto;
    private boolean continuar;

    //Constructor de la clase
    public Servidor(int puerto) {
        this.puerto = puerto;
        clientes = new ArrayList<HiloCliente>();
    }

    public void start() {
        //Llamo a la clase para cargar el csv y guardarlo en arrays
        CargarCSV csv = new CargarCSV();
        csv.cargarCSV();
        this.years = csv.getYears();
        this.incremento = csv.getIncremento();
        //Crea el socket del servidor y recibe conexiones y mete los cliente en el arraylist
        continuar = true;
        try
        {
            ServerSocket serverSocket = new ServerSocket(puerto);
            while(continuar){
                Socket socket = serverSocket.accept();

                if(!continuar){
                    break;
                }

                HiloCliente hiloCliente = new HiloCliente(socket);
                clientes.add(hiloCliente);
                hiloCliente.start();
            }
            //Una vez terminada la conexion se cierra el serversocket y todos los clientes
            serverSocket.close();
            for(int i = 0; i < clientes.size(); ++i) {
                HiloCliente cliente = clientes.get(i);
                    cliente.entrada.close();
                    cliente.salida.close();
                    cliente.socket.close();
            }
        }
        catch (IOException e) {
            System.out.println("Error al crear Servidor");
        }
    }

    class HiloCliente extends Thread {
        Socket socket;
        DataInputStream entrada;
        PrintStream salida;

        int id;
        //Constructor del cliente
        HiloCliente(Socket socket) {
            //Se asigna una id de usuario
            id = ++idUsuario;
            //Se crea el socket, el PrintStream y el DataInputStream
            this.socket = socket;
            try
            {
                salida = new PrintStream(socket.getOutputStream());
                entrada = new DataInputStream(socket.getInputStream());
            }
            catch (IOException e) {
            }
        }

        public void run() {
            boolean continuar = true;
            //Bucle de leer mensajes
            while(continuar) {
                String mensaje = "";
                try {
                    mensaje = entrada.readLine();
                } catch (SocketException s){
                    continuar = false;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Calcula el incremento y lo devuelve.
                String resultado = calcularIncremento(mensaje);
                salida.println(resultado);


            }
            cerrar();
        }

        //Metodo para cerrar las conexiones del cliente
        private void cerrar() {
            try {
                salida.close();
                entrada.close();
                socket.close();
            }
            catch (Exception e) {}
        }

    }

    //Metodo que busca el incremento en los arrays
    private String calcularIncremento(String mensaje) {

        for (int i=0;i<years.size();i++){
            if (years.get(i).equalsIgnoreCase(mensaje)){
                return incremento.get(i);
            }
        }
        return "No se ha encontrado datos del: "+mensaje;
    }
}

