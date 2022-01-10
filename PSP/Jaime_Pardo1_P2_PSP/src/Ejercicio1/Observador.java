package Ejercicio1;

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;

public class Observador implements Observer {
    @Override
    public void update(Observable o, Object arg) {

        //Muestra el mensaje enviado, a quien y cuando
        System.out.println("Mensaje a "+o.toString()+" a las: "+ LocalDateTime.now());

    }
}
