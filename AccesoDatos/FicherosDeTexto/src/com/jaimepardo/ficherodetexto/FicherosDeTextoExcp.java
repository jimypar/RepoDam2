package com.jaimepardo.ficherodetexto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FicherosDeTextoExcp {

    public static void main(String[] args) {

        PrintWriter escritor = null;
        try {
            escritor = new PrintWriter("fichero.txt");


            escritor.println("Hello world");
            escritor.close();

            File fichero = new File("fichero.txt");

            Scanner scan = new Scanner(System.in);

            scan = new Scanner(fichero);

            while (scan.hasNext()) {
                System.out.println(scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
