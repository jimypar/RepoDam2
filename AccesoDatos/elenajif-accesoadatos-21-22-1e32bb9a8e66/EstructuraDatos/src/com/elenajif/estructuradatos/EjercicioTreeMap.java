package com.elenajif.estructuradatos;

import java.util.Collection;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by DAM on 15/10/2021.
 */
public class EjercicioTreeMap {
    public static void main(String[] args) {
        System.out.println("Añadir un elemento (pareja clave-valor)");
        TreeMap<String,Libro> librosTreeMap = new TreeMap<String,Libro>();
        Libro libro=new Libro();
        libro.setTitulo("Secuestrado");
        libro.setAutor("Robert Louis Stevenson");
        librosTreeMap.put(libro.getTitulo(),libro);

        System.out.println("Añadimos uno más");
        Libro libro1=new Libro();
        libro1.setTitulo("El amor en tiempos de cólera");
        libro1.setAutor("Gabriel García Márquez");
        librosTreeMap.put(libro1.getTitulo(),libro1);

        System.out.println("Mostramos datos: Obtener una colección con todos los valores");
        //los muestra ordenados
        Collection<Libro> colec = librosTreeMap.values();
        for (Libro lib: colec) {
            System.out.println(lib.getTitulo()+"-"+lib.getAutor());
        }

        System.out.println("Obtener un elemento");
        String tituloLibro="Secuestrado";
        librosTreeMap.get(tituloLibro);

        System.out.println("Comprobar si existe una clave");
        String titulo="Secuestrado";
        if (librosTreeMap.containsKey(titulo)) {
            System.out.println("El libro con el titulo "+titulo
                    + "existe en tu colección");
        }

        System.out.println("Obtener un set con todas las claves");
        Set<String> titulos = librosTreeMap.keySet();
        //TreeSet y HashSet implementan el interface Set
        System.out.println("Mostramos los titulos");
        for (Object elemento: titulos) {
            System.out.println(elemento+" ");
        }
        System.out.println();

        System.out.println("Comprobar el tamaño del map");
        System.out.println("Tienes "+librosTreeMap.size()+
         " libros en tu colección");

        System.out.println("Concatenar todos los elementos en otro Map");
        TreeMap<String, Libro> masLibros = new TreeMap<String,Libro>();
        Libro libro2 = new Libro();
        libro2.setTitulo("Los pilares de la tierra");
        libro2.setAutor("Ken Follet");
        masLibros.put(libro2.getTitulo(),libro2);
        Libro libro3 = new Libro();
        libro3.setTitulo("La montaña mágica");
        libro3.setAutor("Tomas Mann");
        masLibros.put(libro3.getTitulo(),libro3);

        librosTreeMap.putAll(masLibros);

        System.out.println("Mostramos datos");
        for (Libro lib: colec) {
            System.out.println(lib.getTitulo()+"-"+lib.getAutor());
        }

        System.out.println("Eliminar un elemento (por clave)");
        titulo="Secuestrado";
        librosTreeMap.remove(titulo);

        System.out.println("Mostramos datos");
        for (Libro lib: colec) {
            System.out.println(lib.getTitulo()+"-"+lib.getAutor());
        }

        System.out.println("Eliminar todos los elementos");
        librosTreeMap.clear();

        System.out.println("Comprobar que está vacio");
        if (librosTreeMap.isEmpty()) {
            System.out.println("Tu colección de datos está vacia");
        }

    }
}
