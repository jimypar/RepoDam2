package com.jaimepardo.ejercicio2;

public class Fuelarre extends Thread{

    private double km = 0;

    public Fuelarre(){
    }

    public void run(){

        this.km = 0.75;

    }

    public double getKm() {
        return km;
    }
}
