import com.google.gson.Gson;

import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {

    private BaseDatos bd;
    private static int idUsuario;
    private ArrayList<HiloCliente> clientes;
    private int puerto;
    private boolean continuar;

    //Metodo principal de la clase que inicia la base de datos que
    // crea el servidor con el puerto correspondiente y lo inicia
    public static void main(String[] args) {
        BaseDatos bd = new BaseDatos();
        Servidor servidor = new Servidor(4444,bd);
        servidor.start();
    }

    //Constructor del servidor que le asigna el puerto y la base de datos e inicia el array de clientes.
    public Servidor(int puerto, BaseDatos bd) {
        this.puerto = puerto;
        this.bd = bd;
        clientes = new ArrayList<HiloCliente>();
    }

    //Metodo que inicia el servidor crea la conexion con la base de datos
    //Inicia el socket del server y constantemente busca conexiones de clientes
    //acepta la conexion, la añade al array y crea el hilo.
    public void start() {

        if (!bd.conectar()){
            System.exit(0);
        }

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

    //Hilo del cliente que contiene el socket, la entrada, la salida y un ID.
    class HiloCliente extends Thread {
        Socket socket;
        DataInputStream entrada;
        PrintStream salida;

        int id;
        boolean conectado;

        //Constructor del hilo del cliente que inicia el socket le asigna una id
        //y inicia la salida y entrada
        HiloCliente(Socket socket) {
            id = ++idUsuario;
            this.socket = socket;
            try
            {
                salida = new PrintStream(socket.getOutputStream());
                entrada = new DataInputStream(socket.getInputStream());

            }
            catch (IOException e) {
            }
        }

        //El hilo recibe mensajes del cliente y
        //Si NO esta conectado:
        //  -Comprueba el mensaje si es el usuario y contraseña correctos
        //  -Le devuelve los permisos que tiene es usuario.
        //  Si esta conectado:
        //  -Recibe un mensaje y realiza la consulta el la base de datos.
        public void run() {

            boolean continuar = true;

            while(continuar) {
                String mensaje = "";
                try {
                    mensaje = entrada.readLine();
                } catch (SocketException s){
                    continuar = false;
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (conectado){

                    String resultado = consulta(mensaje);
                    salida.println(resultado);

                    if (mensaje.equals("$$$DESCONECTARR$$$")) {
                        continuar = false;
                    }
                }else {
                    int resultado;

                    resultado = conectarUsuario(mensaje);
                    if (resultado!=-1){conectado=true;}
                    Gson gson = new Gson();
                    MensajeInicioSesion m = new MensajeInicioSesion(resultado);
                    String envio = gson.toJson(m,MensajeInicioSesion.class);
                    System.out.println(envio);
                    salida.println(envio);
                }
            }
            cerrar();
        }

        //Cierra la conexion del usuario
        private void cerrar() {
            try {
                salida.close();
                entrada.close();
                socket.close();
            }
            catch (Exception e) {}
        }

    }



    //Divide el mensaje recibido y comprueba que tipo de consulta es y devuelve el resultado.
    private String consulta(String mensaje) {

        //Se instancia el objeto GSON
        Gson gson = new Gson();

        //Se crea el mensaje segun el json recibido
        Mensaje m = gson.fromJson(mensaje, Mensaje.class);

        //Segun el mensaje que se recibe se realiza una consulta y se devuelve el JSON
        try {
            switch (m.getTipoconsulta()){
                case 1:
                    switch (m.getTipoObjeto()){
                        case 1:
                            return gson.toJson(new Mensaje(m.getTipoObjeto(),bd.consultarEntrenador(m.getConsulta())));
                        case 2:
                            return gson.toJson(new Mensaje(m.getTipoObjeto(),bd.consultarJugador(m.getConsulta())));
                        case 3:
                            return gson.toJson(new Mensaje(m.getTipoObjeto(),bd.consultarEstadio(m.getConsulta())));
                    }
                    break;
                case 2:
                    switch (m.getTipoObjeto()){
                        case 1:
                            bd.modificarEntrenador(m.getBusqueda(),m.getConsulta());
                        case 2:
                            bd.modificarJugador(m.getBusqueda(),m.getConsulta());
                        case 3:
                            bd.modificarEstadio(m.getBusqueda(),m.getConsulta());
                    }
                    break;
                case 3:
                    switch (m.getTipoObjeto()){
                        case 1:
                            bd.eliminarEntrenador(m.getConsulta());
                        case 2:
                            bd.eliminarJugador(m.getConsulta());
                        case 3:
                            bd.eliminarEstadio(m.getConsulta());
                    }
                    break;
            }
        }catch (Exception e){}


        return "No se ha encontrado la peticion";

    }

    //Comprueba que el usuairo es valido y que permisos tiene
    private int conectarUsuario(String mensaje) {

        Gson gson = new Gson();
        MensajeInicioSesion m = gson.fromJson(mensaje, MensajeInicioSesion.class);

        int resultado = -1;
        if (m.getTipoconsulta()==1)
            resultado =  bd.consultarUsuario(m.getUsuario(),m.getPassword());

        if (m.getTipoconsulta()==2) {
            resultado = bd.registrarUsuario(m.getUsuario(), m.getPassword());
        }

        return resultado;


    }



}

