package Ejercicio4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ejercicio4 {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        //Se crea un arrayList que contenga las palabras del texto con el metodo convertirArray()
        ArrayList<String> palabras = convertirArray();

        //Se crean los hilos correspondientes para contar.
        ContarPalabras contar = new ContarPalabras(palabras);
        ContarLineas contarLineas = new ContarLineas();
        ContarTitulo contarTitulos = new ContarTitulo(palabras,"TITULO");
        ContarTitulo contarArticulos = new ContarTitulo(palabras,"Art.");
        ContarTitulo contarCapitulos = new ContarTitulo(palabras,"CAPITULO");
        ContarPalabra contarConstitucion = new ContarPalabra(palabras,"constitucion");
        ContarPalabra contarRey = new ContarPalabra(palabras,"Rey");

        //Se inicia el hilo y se espera con el join a que termine y se muestra el resultado con el get()
        contar.start();
        contar.join();
        System.out.println("Palabras: "+contar.getNumPalabras());
        contarLineas.start();
        contarLineas.join();
        System.out.println("Lineas: "+contarLineas.getNumLineas());
        contarTitulos.start();
        contarTitulos.join();
        System.out.println("Titulos: "+contarTitulos.getNumPalabras());
        contarArticulos.start();
        contarArticulos.join();
        System.out.println("Articulos: "+contarArticulos.getNumPalabras());
        contarCapitulos.start();
        contarCapitulos.join();
        System.out.println("Capitulos: "+contarCapitulos.getNumPalabras());
        contarConstitucion.start();
        contarConstitucion.join();
        System.out.println("Palabra constitucion: " +contarConstitucion.getNumPalabras());
        contarRey.start();
        contarRey.join();
        System.out.println("Palabra rey: "+contarRey.getNumPalabras());

    }

    //Metodo lee el fichero de la constitucion y lo pasa palabra por palabra a un array que devuelve
    private static ArrayList<String> convertirArray() throws FileNotFoundException {

        ArrayList<String> palabras = new ArrayList<>();
        FileReader fichero = new FileReader("Contitucion1812.txt");
        Scanner scan = new Scanner(fichero);

        while(scan.hasNext()){
            palabras.add(scan.next());
        }

        return palabras;

    }

}
