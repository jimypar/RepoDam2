package com.jaimepardo.ficherodetexto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FicherosDeTexto2 {

    public static void main(String[] args) throws IOException {

        FileWriter writer  = new FileWriter("fichero2.txt");
        writer.write("Traspaso a fichero de texto \n");
        writer.close();

        File fichero = new File("fichero2.txt");
        Scanner scan = new Scanner(fichero);

        while (scan.hasNextLine()){
            System.out.println(scan.nextLine());
        }
        scan.close();



    }

}
