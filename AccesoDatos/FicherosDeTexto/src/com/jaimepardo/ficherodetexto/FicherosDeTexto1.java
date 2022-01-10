package com.jaimepardo.ficherodetexto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FicherosDeTexto1 {

    public static void main(String[] args) throws FileNotFoundException {

        PrintWriter escritor = new PrintWriter("fichero.txt");

        escritor.println("Hello world");
        escritor.close();

        File fichero = new File("fichero.txt");

        Scanner scan = new Scanner(fichero);

        while (scan.hasNext()) {
            System.out.println(scan.nextLine());
        }

    }

}
