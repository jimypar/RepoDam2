package com.jaimepardo.ejercicio2;

public class MariMerche extends Thread {

    private double km = 0;

    public MariMerche(){
    }

    public void run(){

        this.km = 1;

    }

    public double getKm() {
        return km;
    }
}
