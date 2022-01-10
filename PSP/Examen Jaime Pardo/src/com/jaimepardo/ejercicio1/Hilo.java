package com.jaimepardo.ejercicio1;

public class Hilo extends Thread{

    private int x,y;
    private String hilo;

    public Hilo(int x,int y,String nombreHilo){
        this.x = x;
        this.y = y;
        this.hilo = nombreHilo;
    }

    public void run(){

        for (int i = x; i<=y;i++){
            System.out.println(this.hilo+" vale " +i);
        }

    }


}
