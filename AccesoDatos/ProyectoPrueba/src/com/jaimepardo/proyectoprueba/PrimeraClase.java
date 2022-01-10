package com.jaimepardo.proyectoprueba;

import java.util.Scanner;

public class PrimeraClase {

    private String color;

    public PrimeraClase(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public static void main(String[] args) {


        Scanner scan = new Scanner(System.in);

        String cadena = "hola";
        String cadena1 = "1234";
        System.out.println(cadena);
        System.out.println(Integer.parseInt(cadena1));

    }

    @Override
    public String toString() {
        return "PrimeraClase{" +
                "color='" + color + '\'' +
                '}';
    }
}
