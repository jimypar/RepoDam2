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

            ClienteGUI cliente1 = new ClienteGUI(300,300);
            ClienteGUI cliente2 = new ClienteGUI(600,300);

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
                        mensaje = entrada.readLine();
                    } catch (SocketException s){
                        continuar = false;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    String resultado = calcularTabla(mensaje,id);
                    enviarTodos(resultado);

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
    private String calcularTabla(String mensaje, int id) {

        if (turno==id){
            if (estaVacio(mensaje)){
                    if (turno == 1){
                        turno=2;
                    }else {
                        turno=1;
                    }
                pintarMatriz(mensaje,id);
                if (calcularVictoria(id)){
                    enviarTodos(id+":"+mensaje);
                    return id+":victoria";
                }else if(calcularEmpate()){
                    enviarTodos(id+":"+mensaje);
                    return id+":empate";
                }else {
                    return id+":"+mensaje;
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

        switch (mensaje) {
            case "00":
                if (tablero[0][0]==0){
                    return true;
                }
                break;
            case "01":
                if (tablero[0][1]==0){
                    return true;
                }
                break;
            case "02":
                if (tablero[0][2]==0){
                    return true;
                }
                break;
            case "10":
                if (tablero[1][0]==0){
                    return true;
                }
                break;
            case "11":
                if (tablero[1][1]==0){
                    return true;
                }
                break;
            case "12":
                if (tablero[1][2]==0){
                    return true;
                }
                break;
            case "20":
                if (tablero[2][0]==0){
                    return true;
                }
                break;
            case "21":
                if (tablero[2][1]==0){
                    return true;
                }
                break;
            case "22":
                if (tablero[2][2]==0){
                    return true;
                }
                break;
        }

        return false;

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

        switch (mensaje) {
            case "00":
                tablero[0][0] = id;
                break;
            case "01":
                tablero[0][1] = id;
                break;
            case "02":
                tablero[0][2] = id;
                break;
            case "10":
                tablero[1][0] = id;
                break;
            case "11":
                tablero[1][1] = id;
                break;
            case "12":
                tablero[1][2] = id;
                break;
            case "20":
                tablero[2][0] = id;
                break;
            case "21":
                tablero[2][1] = id;
                break;
            case "22":
                tablero[2][2] = id;
                break;
        }

    }
}

