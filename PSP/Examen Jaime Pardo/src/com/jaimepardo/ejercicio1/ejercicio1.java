package com.jaimepardo.ejercicio1;

public class ejercicio1 {

    public static void main(String[] args) throws InterruptedException {

        Hilo hilo1 = new Hilo(1,10,"Hilo 1");
        Hilo hilo2 = new Hilo(11,20,"Hilo 2");
        Hilo hilo3 = new Hilo(21,30,"Hilo 3");

        hilo1.start();
        hilo1.join();
        hilo2.start();
        hilo2.join();
        hilo3.start();
        hilo3.join();

    }

}
