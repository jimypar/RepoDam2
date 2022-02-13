package com.elenajif.estructuradatos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by DAM on 15/10/2021.
 */
public class EjercicioLinkedList {
    public static void main(String[] args) {
        System.out.println("Añadir un elemento");
         List<Libro> listaLinkedLibros = new LinkedList<>();

         Libro libro = new Libro();
         libro.setTitulo("Secuestrado");
         libro.setAutor("Robert Louis Stenvenson");

         listaLinkedLibros.add(libro);

        System.out.println("Mostrar elementos");
        Iterator it = listaLinkedLibros.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }

        System.out.println("Añadir un elemento al principio");

        Libro libro1=new Libro();
        libro1.setTitulo("La montaña mágica");
        libro1.setAutor("Tomas Mann");

        ((LinkedList<Libro>)listaLinkedLibros).addFirst(libro1);

        System.out.println("Mostrar elementos");
        Iterator it1 = listaLinkedLibros.iterator();
        while (it1.hasNext()) {
            System.out.println(it1.next().toString());
        }

        System.out.println("Añadir un elemento al final");

        Libro libro2=new Libro();
        libro2.setTitulo("Moby-dick");
        libro2.setAutor("Herman Melville");

        ((LinkedList<Libro>) listaLinkedLibros).addLast(libro2);

        System.out.println("Mostrar elementos");
        Iterator it2 = listaLinkedLibros.iterator();
        while (it2.hasNext()) {
            System.out.println(it2.next().toString());
        }

        System.out.println("Añadir toda una colección al final");

        List<Libro> otraListaDeLibros = new ArrayList<>();

        Libro otroLibro=new Libro();
        otroLibro.setTitulo("El cuaderno dorado");
        otroLibro.setTitulo("Doris Lessing");
        otraListaDeLibros.add(otroLibro);
        Libro otroLibro1 = new Libro();
        otroLibro1.setTitulo("Los pilares de la tierra");
        otroLibro1.setAutor("Ken Follet");
        otraListaDeLibros.add(otroLibro1);

        listaLinkedLibros.addAll(otraListaDeLibros);

        System.out.println("Mostrar elementos");
        Iterator it3 = listaLinkedLibros.iterator();
        while (it3.hasNext()) {
            System.out.println(it3.next().toString());
        }

        System.out.println("Obtener un elemento");
        System.out.println(listaLinkedLibros.get(4));

        System.out.println("Obtener el primer elemento");
        ((LinkedList<Libro>) listaLinkedLibros).getFirst();

        System.out.println("Obtener el numero de elementos de la lista");
        System.out.println("Esta lista tiene "+listaLinkedLibros.size()
                + " elementos");

        System.out.println("Eliminar (y obtener) un elemento");
        System.out.println(listaLinkedLibros.remove(3));
        System.out.println("Eliminar (y obtener) el primer elemento");
        System.out.println(((LinkedList<Libro>) listaLinkedLibros).removeFirst());
        System.out.println("Eliminar (y obtener) el ultimo elemento");
        System.out.println(((LinkedList<Libro>) listaLinkedLibros).removeLast());

        System.out.println("Eliminar todos los elementos");
        listaLinkedLibros.clear();

    }
}
