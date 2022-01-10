package com.danielberges.estructuradatos;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EjercicioHashMap {

    //ArrayList, TreeSet y LinkedList implementan Collection
    public static void main(String[] args) {

        HashMap<String, Libro> mapLibros = new HashMap<>();
        System.out.println("Insertamos dato:");
        Libro libro = new Libro();
        libro.setAutor("Pepe");
        libro.setTitulo("Harry Potter");
        mapLibros.put(libro.getTitulo(),libro);

        System.out.println("Mostrarme un valor dado un titulo");
        String tituloLibro = "Harry Potter";
        System.out.println(mapLibros.get(tituloLibro));

        System.out.println("Insertamos dato:");
        Libro libro2 = new Libro();
        libro2.setAutor("Antonio");
        libro2.setTitulo("Peppa Pig");
        mapLibros.put(libro2.getTitulo(),libro2);

        System.out.println("Obtener coleccion de todos lo valores");
        System.out.println("Mostramos valores");
        Collection<Libro> coleccionLibros = mapLibros.values();
        for (Libro elemento: coleccionLibros) {
            System.out.println(elemento+" ");
            }
        System.out.println();

        System.out.println("Comprobar si existe la clave");
        String titulo = "Peppa Pig";
        if (mapLibros.containsKey(titulo)){

            System.out.println("El libro "+ titulo +" existe ");

        }

        System.out.println("Obtener todas las claves");
        Set<String> titulos = mapLibros.keySet();

        System.out.println("Comprobar tamaño");
        System.out.println("Tienes "+mapLibros.size()+" libros");

        System.out.println("Concatenar todos los elementos de otro map");
        Map<String, Libro> masLibros = new HashMap<>();

        System.out.println("Insertamos dato:");
        Libro libro3 = new Libro("Juegos del hambre","Martin");
        mapLibros.put(libro3.getTitulo(),libro3);
        Libro libro4 = new Libro("La Biblia","Jose");
        mapLibros.put(libro4.getTitulo(),libro4);

        mapLibros.putAll(masLibros);

        Collection<Libro> coleccionLibros2 = masLibros.values();
        for (Libro elemento: coleccionLibros2) {
            System.out.println(elemento+" ");
        }
        System.out.println();

    }

}
