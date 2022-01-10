package Ejercicio2;

public class ejercicio2 {

    public static void main(String[] args) throws InterruptedException {

        //Se crean cuatro hilos cada uno con un mensaje
        Hilo h1 = new Hilo("Dale a tu cuerpo alegria macarena");
        Hilo h2 = new Hilo("Que tu cuerpo es pa darle alegria y cosa buena");
        Hilo h3 = new Hilo("Dale a tu cuerpo alegria macarena");
        Hilo h4 = new Hilo("Heeeeyyyyy Macarena");

        //Se ejecutan los hilos uno a uno esperando con un join a que termine el anterior
        h1.start();
        h1.join();
        h2.start();
        h2.join();
        h3.start();
        h3.join();
        h4.start();
        h4.join();

    }

}

class Hilo extends Thread{

    private String mensaje;

    //Constructor clase Hilo
    public Hilo(String mensaje){
        this.mensaje = mensaje;
    }

    //Metodo que muestra el mensaje pasado como parametro
    public void run(){

        System.out.println(mensaje);

    }

}
