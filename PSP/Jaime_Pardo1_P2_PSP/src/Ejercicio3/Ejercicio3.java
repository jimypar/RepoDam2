package Ejercicio3;


import Ejercicio3.gui.Termostato;

public class Ejercicio3 {


    public static void main(String[] args) {

        //Creo un termostato con singleton
        Termostato termostato = Termostato.getSingletoInstance();

        //Creo 4 aires observadores
        AireAcondicionado aire1 = new AireAcondicionado();
        AireAcondicionado aire2 = new AireAcondicionado();
        AireAcondicionado aire3 = new AireAcondicionado();
        AireAcondicionado aire4 = new AireAcondicionado();

        //Añado los 4 observadores de aire al termostato
        termostato.addObserver(aire1);
        termostato.addObserver(aire2);
        termostato.addObserver(aire3);
        termostato.addObserver(aire4);



    }


}
