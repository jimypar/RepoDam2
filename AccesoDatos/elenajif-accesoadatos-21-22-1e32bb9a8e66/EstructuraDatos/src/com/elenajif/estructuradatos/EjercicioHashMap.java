package com.elenajif.estructuradatos;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by DAM on 15/10/2021.
 */
public class EjercicioHashMap {
    //ArrayList, TreeSet y LinkedList implementan la interface Collection
    //TreeSet y HashSet immplementan el interface Set

    public static void main(String[] args) {
        HashMap<String, Libro> mapLibros = new HashMap<String,Libro>();
        System.out.println("Insertamos un dato");
        //creo un libro (recordar que solo tenemos dos atributos)
        Libro libro = new Libro();
        libro.setTitulo("Secuestrado");
        libro.setAutor("Robert Louis Stevenson");
        //añadimos el libro y su clave, nuestra clave es el titulo
        mapLibros.put(libro.getTitulo(),libro);

        System.out.println("Mostramos un valor dado un titulo");
        String tituloLibro="Secuestrado";
        System.out.println(mapLibros.get(tituloLibro));

        System.out.println("Insertamos dos datos más");
        Libro libro1 = new Libro("El amor en tiempos de cólera","Gabriel García Márquez");
        mapLibros.put(libro1.getTitulo(),libro1);
        Libro libro2 = new Libro("Don quijote de la mancha","Miguel de Cervantes");
        mapLibros.put(libro2.getTitulo(),libro2);

        System.out.println("Obtener colección con todos los valores");
        System.out.println("Mostramos valores");
        Collection<Libro> coleccionLibros =mapLibros.values();
        for (Libro elemento: coleccionLibros) {
            System.out.println(elemento+" ");
        }
        System.out.println("");

        System.out.println("Comprobar si existe la clave");
        //recordamos, nuestra clave es el titulo
        String titulo="Secuestrado";
        if (mapLibros.containsKey(titulo)) {
            System.out.println("El libro con el titulo "+titulo+" existe en la colección");
        }

        System.out.println("Obtener un set con todas las claves");
        Set<String> titulos = mapLibros.keySet();

        System.out.println("Comprobar el tamaño del map");
        System.out.println("Tienes "+mapLibros.size()+ " libros en tu colección");

        System.out.println("Concatenar todos los elementos de otro Map");
        Map<String, Libro> masLibros=new HashMap<String, Libro>();

        Libro masLibros1 = new Libro("La montaña mágica","Tomas Mann");
        mapLibros.put(masLibros1.getTitulo(),masLibros1);
        Libro masLibros2 = new Libro("Moby-Dick","Herman Melville");
        mapLibros.put(masLibros2.getTitulo(),masLibros2);
        Libro masLibros3 = new Libro("El cuaderno dorado","Dorisa Lessing");
        mapLibros.put(masLibros3.getTitulo(),masLibros3);

        mapLibros.putAll(masLibros);

        System.out.println("Mostramos valores");
        for (Libro elemento: coleccionLibros) {
            System.out.println(elemento+" ");
        }
        System.out.println("");

        System.out.println("Eliminar un elemento (por clave)");
        titulo="Secuestrado";
        mapLibros.remove(titulo);

        System.out.println("Mostramos valores");
        for (Libro elemento: coleccionLibros) {
            System.out.println(elemento+" ");
        }
        System.out.println("");

        System.out.println("Eliminar todos los elementos");
        mapLibros.clear();

        System.out.println("Comprobar si está vacio");
        if (mapLibros.isEmpty()) {
            System.out.println("La colección de libros está vacia");
        }

    }

}
