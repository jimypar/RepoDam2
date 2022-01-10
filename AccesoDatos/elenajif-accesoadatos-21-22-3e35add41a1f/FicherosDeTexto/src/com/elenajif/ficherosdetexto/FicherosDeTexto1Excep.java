package com.elenajif.ficherosdetexto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by DAM on 07/10/2021.
 */
public class FicherosDeTexto1Excep {
    public static void main(String[] args) {
        //escribir ficheros de texto plano
        PrintWriter escritor = null;

        try {
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

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
