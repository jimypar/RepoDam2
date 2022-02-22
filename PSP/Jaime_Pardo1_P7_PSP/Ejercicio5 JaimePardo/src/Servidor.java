import com.google.gson.Gson;
import libreria.Libreria;

import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {

    private static int idUsuario;
    private ArrayList<HiloCliente> clientes;
    private int puerto;
    private boolean continuar;
    private int turno;
    private int[][] tablero = new int[3][3];

    //Clase principal que crea el servidor con el puerto y se inicia.
    public static void main(String[] args) {
        Servidor servidor = new Servidor(4444);
        servidor.start();
    }

    //Constructor del servidor que asigna el puerto y se inicia el array de clientes
    public Servidor(int puerto) {
        this.puerto = puerto;
        clientes = new ArrayList<HiloCliente>();
    }

    //Metodo que inicia el servidor
    //Crea los dos clientes
    //acepta conexiones y les asigna un hilo
    public void start() {

        turno=1;
        continuar = true;
        try
        {
            ServerSocket serverSocket = new ServerSocket(puerto);

            ClienteGUI cliente1 = new ClienteGUI(300,300, "Jugador 1");
            ClienteGUI cliente2 = new ClienteGUI(600,300, "Jugador 2");

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

    //Hilo de cada cliente que contiene el socket, la entrada,la salida y una id de usuario
    class HiloCliente extends Thread {
        Socket socket;
        DataInputStream entrada;
        PrintStream salida;
        int id;

        //Contructor del hilo que le asigna una id, inicia el socket y la entrada y salida.
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

        //El hilo mientras estes conectado recibe datos del cliente y los envia a todos
        public void run() {

            if (id<=2){

                boolean continuar = true;

                while(continuar) {
                    String mensaje = "";
                    try {
                        mensaje = Libreria.desencriptar(entrada.readLine());
                        String resultado = Libreria.encriptar(calcularTabla(mensaje,id));
                        enviarTodos(resultado);
                    } catch (SocketException s){
                        continuar = false;
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }



                }
                cerrar();

            }

        }

        //Metodo que cierra la conexion con el cliente
        private void cerrar() {
            try {
                salida.close();
                entrada.close();
                socket.close();
            }
            catch (Exception e) {}
        }

    }

    //Metodo que envia a todos uun mensaje
    private void enviarTodos(String resultado) {

        for (int i = 0;i<clientes.size();i++){
            clientes.get(i).salida.println(resultado);
        }

    }

    //Metodo que comprueba que el movimiento es valido y cambia el turno
    private String calcularTabla(String mensaje, int id) throws Exception {

        //Se instancia el objeto GSON
        Gson gson = new Gson();

        //Crea el mensaje desde el JSON recibido
        Mensaje m = gson.fromJson(mensaje, Mensaje.class);

        if (turno==id){
            if (estaVacio(mensaje)){
                    if (turno == 1){
                        turno=2;
                    }else {
                        turno=1;
                    }
                pintarMatriz(mensaje,id);
                if (calcularVictoria(id)){
                    enviarTodos(Libreria.encriptar(gson.toJson(m)));
                    m = new Mensaje(id,true,false);
                    return gson.toJson(m);
                }else if(calcularEmpate()){
                    enviarTodos(Libreria.encriptar(gson.toJson(m)));
                    m = new Mensaje(id,false,true);
                    return gson.toJson(m);
                }else {
                    m.setId(id);
                    return gson.toJson(m);
                }
            }
        }

        return "";

    }

    //Metodo que calcula si hay empate
    private boolean calcularEmpate() {

        for (int i=0;i<tablero.length;i++){
            for (int j=0;j<tablero.length;j++){
                if (tablero[i][j]==0) {
                    return false;
                }
            }
        }

        return true;

    }

    //Metodo que calcula si una celda esta vacia
    private boolean estaVacio(String mensaje) {

        //Se instancia el objeto GSON
        Gson gson = new Gson();

        //Crea el objeto de mensaje desde el JSON recibido
        Mensaje m = gson.fromJson(mensaje, Mensaje.class);

        //Segun las cordenadas del mensaje comprueba si esta vacio
       if (tablero[m.getPosX()][m.getPosY()]==0){
           return true;
       }else {
           return false;
       }

    }

    //Metodo que comprueba si una id ha ganado.
    private boolean calcularVictoria(int id) {


            for (int i = 0;i<3;i++){
                if (tablero[i][0]==id && tablero[i][1]==id && tablero[i][2]==id){
                    return true;
                }
            }
            for (int i = 0;i<3;i++){
                if (tablero[0][i]==id && tablero[1][i]==id && tablero[2][i]==id){
                    return true;
                }
            }

            if (tablero[0][0]==id && tablero[1][1]==id && tablero[2][2]==id){
                return true;
            }
            if (tablero[0][2]==id && tablero[1][1]==id && tablero[2][0]==id){
                return true;
            }

            return false;
    }

    //Metodo que rellena la matriz
    private void pintarMatriz(String mensaje, int id) {

        //Se instancia el objeto GSON
        Gson gson = new Gson();

        //Se crea el mensaje segun el JSON recibido
        Mensaje m = gson.fromJson(mensaje, Mensaje.class);

        //Rellena el tablero segun el mensaje
        tablero[m.getPosX()][m.getPosY()] = id;

    }
}

