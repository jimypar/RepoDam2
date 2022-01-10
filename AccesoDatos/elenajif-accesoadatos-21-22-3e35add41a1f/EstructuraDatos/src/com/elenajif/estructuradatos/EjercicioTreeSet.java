package com.elenajif.estructuradatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by DAM on 15/10/2021.
 */
public class EjercicioTreeSet {
    public static void main(String[] args) {
        System.out.println("Creamos 4 productos y añadimos al TreeSet");
        String producto1="Pan";
        String producto2="Manzanas";
        String producto3="Brocoli";
        String producto4="Carne";

        //definir un TreeSet
        Set<String> listaTreeSet = new TreeSet<>();
        listaTreeSet.add(producto1);
        listaTreeSet.add(producto2);
        listaTreeSet.add(producto3);
        listaTreeSet.add(producto4);

        System.out.println("Mostrar elementos");
        for (Object elemento: listaTreeSet) {
            System.out.println(elemento+" ");
        }
        System.out.println("");

        System.out.println("Añadir un elemento");
        listaTreeSet.add("Lechuga");

        System.out.println("Mostrar elementos");
        for (Object elemento: listaTreeSet) {
            System.out.println(elemento+" ");
        }
        System.out.println("");

        System.out.println("Añadir todos los elementos de otra colección");
        List<String> listaArrayList = new ArrayList<>();
        listaArrayList.add("Lentejas");
        listaArrayList.add("Garbanzos");
        listaArrayList.add("Judias");

        listaTreeSet.addAll(listaArrayList);

        System.out.println("Mostrar elementos");
        for (Object elemento: listaTreeSet) {
            System.out.println(elemento+" ");
        }
        System.out.println("");

        System.out.println("Comprueba si existe un elemento en la lista");
        String texto="Lentejas";
        if (listaTreeSet.contains(texto)) {
            System.out.println("La cadena "+texto+" existe");
        }

        System.out.println("Obtener el primer elemento");
        System.out.println(((TreeSet<String>)listaTreeSet).first());
        System.out.println("Obtener y eliminar el primer elemento");
        System.out.println(((TreeSet<String>)listaTreeSet).pollFirst());

        System.out.println("Mostrar elementos");
        for (Object elemento: listaTreeSet) {
            System.out.println(elemento+" ");
        }
        System.out.println("");

        System.out.println("Obtener el ultimo elemento");
        System.out.println(((TreeSet<String>)listaTreeSet).last());
        System.out.println("Obtener y eliminar el ultimo elemento");
        System.out.println(((TreeSet<String>)listaTreeSet).pollLast());

        System.out.println("Mostrar elementos");
        for (Object elemento: listaTreeSet) {
            System.out.println(elemento+" ");
        }
        System.out.println("");

        System.out.println("Comprobar el numero de elementos");
        System.out.println("Tienes "+listaTreeSet.size() + " cadenas");

        System.out.println("Obtiene la parte del Set cuyos elementos son menores que uno pasado por parametro");
        String cadenaLimite="Judias";
        System.out.println(((TreeSet<String>) listaTreeSet).headSet(cadenaLimite));

        System.out.println("Obtiene la parte del Set entre dos elementos determinados");
        String cadenaMenor="Lechuga";
        String cadenaMayor="Pan";
        System.out.println(((TreeSet<String>) listaTreeSet).subSet(cadenaMenor,cadenaMayor));

        System.out.println("Eliminar todos los elementos");
        listaTreeSet.clear();

        System.out.println("Comprobar si está vacio");
        if (listaTreeSet.isEmpty()) {
            System.out.println("El set de cadenas está vacio");
        }
    }
}
