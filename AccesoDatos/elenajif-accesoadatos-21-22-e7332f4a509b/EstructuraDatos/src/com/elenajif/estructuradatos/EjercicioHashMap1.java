package com.elenajif.estructuradatos;

import java.util.*;

/**
 * Created by DAM on 15/10/2021.
 */
public class EjercicioHashMap1 {
    public static void main(String[] args) {
        Map<String, String> mapLibros=new HashMap<>();
        System.out.println("Añadir un elemento (pareja clave-valor)");
        mapLibros.put("Secuestrado","Robert Louis Stevenson");
        System.out.println("Mostramos valor");
        System.out.println("Autor "+mapLibros.get("Secuestrado"));
        System.out.println("Añadimos mas");
        mapLibros.put("El amor en tiempos de cólera","Gabriel García Márquez");
        mapLibros.put("Don Quijote de la Mancha", "Miguel de Cervantes");
        mapLibros.put("Los cuentos de Canterbury", "Geoffrey Chaucer");

        System.out.println("Imprimimos el Map con foreach");
        String autor;
        for (String titulo: mapLibros.keySet()) {
            autor=mapLibros.get(titulo);
            System.out.println(titulo+" - "+autor);
        }

        System.out.println("Obtener un elemento");
        String tituloLibro="Secuestrado";
        System.out.println(mapLibros.get(tituloLibro));

        System.out.println("Imprimimos el Map con iterator");
        String titulo;
        Iterator<String> it =mapLibros.keySet().iterator();
        while (it.hasNext()) {
            titulo=it.next(); //devuelve una clave
            autor=mapLibros.get(titulo);
            System.out.println(titulo+" - "+autor);
        }

        System.out.println("Comprobar si existe una clave");
        if (mapLibros.containsKey("Secuestrado")) {
            System.out.println("El libro existe en tu colección");
        }

        System.out.println("Obtener una colección con todos los valores");
        Collection<String> coleccionLibros=mapLibros.values();

        System.out.println("Mostrar valores (autores)");
        for (Object elemento: coleccionLibros) {
            System.out.println(elemento+" ");
        }
        System.out.println(" ");

        System.out.println("Obtener un set con todas las claves");
        Set<String> titulos = mapLibros.keySet();
        System.out.println("Mostrar valores (titulos)");
        for (Object elemento: titulos) {
            System.out.println(elemento+" ");
        }
        System.out.println(" ");

        System.out.println("Comprobar el tamaño del Map");
        System.out.println("Tienes "+mapLibros.size()+" libros en tu colección");


        System.out.println("Concatenar todos los elementos de otro Map");
        Map<String,String> masLibros = new HashMap<>();
        masLibros.put("La montaña mágica","Tomas Mann");
        masLibros.put("Moby-Dick","Herman Melville");
        masLibros.put("El cuaderno dorado","Doris Leassing");

        mapLibros.putAll(masLibros);
        System.out.println("Imprimimos el Map con foreach");
        for (String titulo2: mapLibros.keySet()) {
            autor=mapLibros.get(titulo2);
            System.out.println(titulo2+" - "+autor);
        }
        System.out.println("");

        System.out.println("Eliminar todos los elementos");
        mapLibros.clear();

        System.out.println("Comprobar si esta vacio");
        if (mapLibros.isEmpty()) {
            System.out.println("Tu colección está vacia");
        }

    }
}
