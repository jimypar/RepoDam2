package com.elenajif.estructuradatos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DAM on 15/10/2021.
 */
public class EjercicioArrayList {
    public static void main(String[] args) {
        System.out.println("Añadir elementos");
        List<String> listaCadenas = new ArrayList<>();
        String nuevaCadena = "Esto es una cadena";
        String nuevaCadena1 = "Esto es una cadena1";
        String nuevaCadena2 = "Esto es una cadena2";
        listaCadenas.add(nuevaCadena);
        listaCadenas.add(nuevaCadena1);
        listaCadenas.add(nuevaCadena2);

        System.out.println("Muestro datos");
        for (String elemento : listaCadenas) {
            System.out.println(elemento);
        }

        System.out.println("Añadir un elemento en un posición determinada");
        int posicion = 3;
        listaCadenas.add(posicion, "Esto es una cadena3");

        System.out.println("Muestro datos");
        for (String elemento : listaCadenas) {
            System.out.println(elemento);
        }

        System.out.println("Reemplazar el elemento que ocupa una posición determinada");
        String otraCadena = "Esto es otra cadena diferente";
        listaCadenas.set(posicion, otraCadena);

        System.out.println("Muestro datos");
        for (String elemento : listaCadenas) {
            System.out.println(elemento);
        }

        System.out.println("Eliminar un elemento");
        listaCadenas.remove(posicion);

        System.out.println("Muestro datos");
        for (String elemento : listaCadenas) {
            System.out.println(elemento);
        }

        System.out.println("Obtener la referencia a un elemento");
        String unaCadena = listaCadenas.get(2);
        System.out.println("Elemento2 " + unaCadena);
        System.out.println("Conocer el número de elementos");
        System.out.println("El arrayList tiene " + listaCadenas.size() + " elementos");
        System.out.println("Eliminar un elemento concreto de la lista");
        listaCadenas.remove(unaCadena);

        System.out.println("Muestro datos");
        for (String elemento : listaCadenas) {
            System.out.println(elemento);
        }

        System.out.println("Eliminar todos los elementos de la lista");
        listaCadenas.clear();

        System.out.println("Comprobar si está vacia");
        if (listaCadenas.isEmpty()) {
            System.out.println("La cadena no tiene elementos");
        }

        System.out.println("Añadir todos los elementos de otra lista");
        //creo nueva lista
        List<String> otraListaCadenas = new ArrayList<>();
        String otraNuevaCadena = "cadena";
        String otraNuevaCadena1 = "cadena1";
        String otraNuevaCadena2 = "cadena2";
        otraListaCadenas.add(otraNuevaCadena);
        otraListaCadenas.add(otraNuevaCadena1);
        otraListaCadenas.add(otraNuevaCadena2);

        System.out.println("Se puede pasar como parámetro un objeto que implemente el interfaz Collection");
        listaCadenas.addAll(otraListaCadenas);

        System.out.println("Muestro datos");
        for (String elemento : listaCadenas) {
            System.out.println(elemento);
        }

        System.out.println("Obtener un array (estático) con todos los elementos de la lista");
        Object[] arrayCadenas = listaCadenas.toArray();

        System.out.println("Muestro datos");
        for (int i = 0; i < arrayCadenas.length; i++) {
            System.out.println(arrayCadenas[i]);
        }
    }
}
