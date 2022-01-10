package Ejercicio1;

public class Ejercicio1 {

    public static void main(String[] args) {

        //Creo una red social con singleton
        RedSocial rs = RedSocial.getSingletonInstance("RS");

        //Creo tres clientes
        Cliente cliente1 = new Cliente("Jaime");
        Cliente cliente2 = new Cliente("Pepe");
        Cliente cliente3 = new Cliente("Jose");

        //Creo un observador
        Observador observador = new Observador();

        //Añado el observador a los 3 clientes
        cliente1.addObserver(observador);
        cliente2.addObserver(observador);
        cliente3.addObserver(observador);

        //Creo un mensaje para enviar
        String mensaje = "Nuevo blog";

        //La red social envia el mensaje a los usuarios
        rs.enviarMensaje(mensaje,cliente1);
        rs.enviarMensaje(mensaje,cliente2);
        rs.enviarMensaje(mensaje,cliente3);

    }

}
