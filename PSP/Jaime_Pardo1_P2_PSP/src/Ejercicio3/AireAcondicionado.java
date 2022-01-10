package Ejercicio3;

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;

public class AireAcondicionado implements Observer {
    @Override
    public void update(Observable o, Object arg) {

        //Muestra la temperatura
        System.out.println("Temperatura "+arg);

    }
}