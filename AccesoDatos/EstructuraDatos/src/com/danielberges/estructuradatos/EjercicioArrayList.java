package com.danielberges.estructuradatos;

import java.util.ArrayList;
import java.util.List;

public class EjercicioArrayList {

    public static void main(String[] args) {

        System.out.println("añadir elementos");
        List<String> listaCadenas = new ArrayList<String>();
        String nuevacadena = "esto es una cadena";
        String nuevacadena1 = "esto es una cadena1";
        String nuevacadena2 = "esto es una cadena2";
        listaCadenas.add(nuevacadena);
        listaCadenas.add(nuevacadena1);
        listaCadenas.add(nuevacadena2);

        System.out.println("muestro datos");
        for (String elemento: listaCadenas) {
            System.out.println(elemento);
        }

        System.out.println("añadir un elemento en una posicion determinada");
        int posicion = 3;
        listaCadenas.add(posicion, "esto es una cadena");

        System.out.println("muestro datos");
        for (String elemento: listaCadenas) {
            System.out.println(elemento);
        }

        System.out.println("reemplazar elemento que ocupa posicion determinada");
        String otraCadena = "esto es otra cadena diferente";
        listaCadenas.set(posicion, otraCadena);

        System.out.println("muestro datos");
        for (String elemento: listaCadenas) {
            System.out.println(elemento);
        }

        System.out.println("eliminar datos");
        listaCadenas.remove(posicion);

        System.out.println("muestro datos");
        for (String elemento: listaCadenas) {
            System.out.println(elemento);
        }

        System.out.println("obtener referencia de elemento");
        String unaCadena = listaCadenas.get(2);
        System.out.println("elemento2 " + unaCadena);
        System.out.println("conocer numero de elementos");
        System.out.println("arraylist tiene " + listaCadenas.size() + " elementos");
        System.out.println("eliminar un elemento concreto de la lista");
        listaCadenas.remove(unaCadena);

        System.out.println("muestro datos");
        for (String elemento: listaCadenas) {
            System.out.println(elemento);
        }

        System.out.println("eliminar todos elementos de la lista");
        listaCadenas.clear();

        System.out.println("comprobar si esta vacia");
        if(listaCadenas.isEmpty()) {
            System.out.println("la cadena no tiene elementos");
        }

        System.out.println("añadir todos los elementos de otra lista");
        List<String> otraListaCadenas = new ArrayList<String>();
        String otranuevacadena = "esto es una otranuevacadena";
        String otranuevacadena1 = "esto es una otranuevacadena1";
        String otranuevacadena2 = "esto es una otranuevacadena2";
        listaCadenas.add(otranuevacadena);
        listaCadenas.add(otranuevacadena1);
        listaCadenas.add(otranuevacadena2);

        System.out.println("se puede pasar como parámetro un objeto que implemente");
        listaCadenas.addAll(otraListaCadenas);

        System.out.println("muestro datos");
        for (String elemento: otraListaCadenas) {
            System.out.println(elemento);
        }

        System.out.println("obtener un array (estático) con todos los elementos");
        Object[] arrayCadenas = listaCadenas.toArray();

        System.out.println("muestro datos");
        for (String elemento: otraListaCadenas) {
            System.out.println(elemento);
        }

    }

}