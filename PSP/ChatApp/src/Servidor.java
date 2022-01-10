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
            while(continuar)
            {

                Socket socket = serverSocket.accept();

                if(!continuar){
                    break;
                }

                HiloCliente hiloCliente = new HiloCliente(socket);
                clientes.add(hiloCliente);
                hiloCliente.start();
            }

            try {
                serverSocket.close();
                for(int i = 0; i < clientes.size(); ++i) {
                    HiloCliente cliente = clientes.get(i);
                    try {
                        cliente.entrada.close();
                        cliente.salida.close();
                        cliente.socket.close();
                    }
                    catch(IOException e) {
                    }
                }
            }
            catch(Exception e) {
                mostrar("Error al cerrar servidor " + e);
            }
        }
        catch (IOException e) {
            mostrar("Error en ServerSocket");
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

    private synchronized void enviarTodos(String mensaje) {

        gui.mostrar(mensaje + "\n");

        for(int i = clientes.size(); --i >= 0;) {
            HiloCliente cliente = clientes.get(i);
            if(!cliente.escribirMensaje(mensaje + "\n")) {
                clientes.remove(i);
                mostrar(cliente.username + " se ha desconectado");
            }
        }
    }

    synchronized void borrarCliente(int id) {
        for(int i = 0; i < clientes.size(); ++i) {
            HiloCliente cliente = clientes.get(i);
            if(cliente.id == id) {
                clientes.remove(i);
                return;
            }
        }
    }


    class HiloCliente extends Thread {
        Socket socket;
        ObjectInputStream entrada;
        ObjectOutputStream salida;

        int id;
        String username;
        Mensaje mensaje;

        HiloCliente(Socket socket) {
            // a unique id
            id = ++idUsuario;
            this.socket = socket;
            try
            {
                // create output first
                salida = new ObjectOutputStream(socket.getOutputStream());
                entrada = new ObjectInputStream(socket.getInputStream());
                // read the username
                username = (String) entrada.readObject();
                mostrar(username + " se ha conectado");
            }
            catch (IOException e) {
                return;
            }
            catch (ClassNotFoundException e) {
            }
        }

        public void run() {
            boolean continuar = true;

            while(continuar) {
                try {
                    mensaje = (Mensaje) entrada.readObject();
                }
                catch (IOException | ClassNotFoundException e) {
                    break;
                }
                String message = mensaje.getMensaje();

                switch(mensaje.getTipo()) {

                    case Mensaje.MENSAJE:
                        enviarTodos(username + ": " + message);
                        break;
                    case Mensaje.SALIR:
                        mostrar(username + " se ha desconectado");
                        continuar = false;
                        break;
                    case Mensaje.CONECTADOS:
                        escribirMensaje("Usuarios conectados \n");
                        for(int i = 0; i < clientes.size(); ++i) {
                            HiloCliente ct = clientes.get(i);
                            escribirMensaje((i+1) + ") " + ct.username+"\n");
                        }
                        break;
                }
            }
            borrarCliente(id);
            cerrar();
        }

        private void cerrar() {
            try {
                if(salida != null) salida.close();
            }
            catch(Exception e) {}
            try {
                if(entrada != null) entrada.close();
            }
            catch(Exception e) {};
            try {
                if(socket != null) socket.close();
            }
            catch (Exception e) {}
        }


        private boolean escribirMensaje(String msg) {

            if(!socket.isConnected()) {
                cerrar();
                return false;
            }
            try {
                salida.writeObject(msg);
            }
            catch(IOException e) {
            }
            return true;
        }
    }
}

