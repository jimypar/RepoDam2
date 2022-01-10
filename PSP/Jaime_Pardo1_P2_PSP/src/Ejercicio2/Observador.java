package Ejercicio2;

import java.util.Observable;
import java.util.Observer;

public class Observador implements Observer {
    @Override
    public void update(Observable o, Object arg) {

        //Muestra el mensaje pasado
        System.out.println(" "+arg);

    }
}
