package com.jaimepardo.ejercicio2;

public class RedBulo extends Thread{

    private double km = 0;

    public RedBulo(){
    }

    public void run(){

        this.km = 1.1;

    }

    public double getKm() {
        return km;
    }
}
