package Ejercicio2;

import Ejercicio2.Observador;

public class Ejercicio2 {

    public static void main(String[] args) {

        //Creo cuatro observadores
        Observador observador1 = new Observador();
        Observador observador2 = new Observador();
        Observador observador3 = new Observador();
        Observador observador4 = new Observador();

        //Creo una operacion con singleton
        Operacion operacion = Operacion.getSingletonInstance();

        //Asigno los 4 observadores a la operacion
        operacion.addObserver(observador1);
        operacion.addObserver(observador2);
        operacion.addObserver(observador3);
        operacion.addObserver(observador4);

        //Introducir los numeros
        operacion.introducirNum();

        //Realiza la operacion 5 veces
        for (int i = 1 ; i<=5; i++){

            operacion.operacion();

        }

    }

}
