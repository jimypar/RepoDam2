import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {
    private static int idUsuario;
    private ArrayList<HiloCliente> clientes;
    private ServidorGUI gui;
    private int puerto;
    private boolean continuar;

    public Servidor(int puerto, ServidorGUI gui) {
        this.gui = gui;
        this.puerto = puerto;
        clientes = new ArrayList<HiloCliente>();
    }

    public void start() {
        continuar = true;
        try
        {
            ServerSocket serverSocket = new ServerSocket(puerto);
            mostrar("Servidor iniciado puerto: " + puerto);
            while(continuar){
                Socket socket = serverSocket.accept();

                if(!continuar){
                    break;
                }

                HiloCliente hiloCliente = new HiloCliente(socket);
                clientes.add(hiloCliente);
                hiloCliente.start();
            }

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

    protected void cerrarServer() {
        continuar = false;
        try {
            new Socket("localhost", puerto);
        }
        catch(Exception e) {
        }
    }

    private void mostrar(String msg){
        gui.mostrar(msg + "\n");
    }

    private void enviarTodos(String mensaje) {

        gui.mostrar(mensaje + "\n");

        for(int i = clientes.size(); --i >= 0;) {
            HiloCliente cliente = clientes.get(i);
            if(!cliente.escribirMensaje(mensaje + "\n")) {
                clientes.remove(i);
                mostrar(cliente.nombreUsuario + " se ha desconectado");
            }
        }
    }

    class HiloCliente extends Thread {
        Socket socket;
        DataInputStream entrada;
        PrintStream salida;

        int id;
        String nombreUsuario;

        HiloCliente(Socket socket) {
            id = ++idUsuario;
            this.socket = socket;
            try
            {
                salida = new PrintStream(socket.getOutputStream());
                entrada = new DataInputStream(socket.getInputStream());
                nombreUsuario = entrada.readLine();
                mostrar(nombreUsuario + " se ha conectado");
            }
            catch (IOException e) {
            }
        }

        public void run() {
            boolean continuar = true;

            while(continuar) {
                String mensaje = "";
                try {
                    mensaje = entrada.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (mensaje.equals("$$$DESCONECTARR$$$")){
                    mostrar(nombreUsuario + " se ha desconectado");
                    continuar = false;
                }else if (mensaje.equals("$$$SALIR$$$")) {
                    mostrar(nombreUsuario + " se ha desconectado");
                    continuar = false;
                }
                else {
                    enviarTodos(nombreUsuario + ": " + mensaje);
                }


            }
            cerrar();
        }

        private void cerrar() {
            try {
                salida.close();
                entrada.close();
                socket.close();
            }
            catch (Exception e) {}
        }


        private boolean escribirMensaje(String msg) {

            if (msg!=null){
                salida.println(msg);
                return true;
            }else {
                return false;
            }

        }
    }
}

