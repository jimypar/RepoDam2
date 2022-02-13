package com.elenajif.ficherosdetexto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by DAM on 07/10/2021.
 */
public class FicherosDeTexto1 {
    public static void main(String[] args) throws FileNotFoundException {
        //escribir ficheros de texto plano
        PrintWriter escritor = null;

        escritor = new PrintWriter("fichero1.txt");
        escritor.println("Esto es una linea del fichero");
        escritor.close();
        //leer ficheros de texto plano
        File fichero = new File("fichero1.txt");
        Scanner lector = null;

        lector = new Scanner(fichero);
        while (lector.hasNext()) {
            System.out.println(lector.nextLine());
        }
        lector.close();
        //CTRL+ALT+L tabular
    }
}
