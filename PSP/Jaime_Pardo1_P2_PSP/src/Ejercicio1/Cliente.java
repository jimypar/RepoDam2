package Ejercicio1;

import java.util.Observable;

public class Cliente extends Observable{

    String nombre;
    String mensaje;

    //Constructor de cliente
    public Cliente(String nombre) {
        this.nombre = nombre;
        this.mensaje = "";
    }

    public void mensaje(String m){
        this.mensaje = m;

        //Marcamos el objeto observable como objeto que ha cambiado
        setChanged();
        //Notificamos a los observadores y le enviamos el nuevo valor
        notifyObservers(m);

    }

    @Override
    public String toString() {
        return nombre +", mensaje: '" + mensaje + '\'';
    }
}
