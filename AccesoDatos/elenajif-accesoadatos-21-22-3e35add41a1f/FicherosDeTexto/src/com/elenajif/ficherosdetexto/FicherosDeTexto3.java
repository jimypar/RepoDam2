package com.elenajif.ficherosdetexto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by DAM on 07/10/2021.
 */
public class FicherosDeTexto3 {
    public static void main(String[] args) throws IOException {
        //si quiero que no me sobreescriba el fichero de destino
        FileWriter writer = new FileWriter("fichero3.txt",true);
        writer.write("Traspaso a fichero de texto \n");
        writer.write("Traspaso a fichero de texto \n");
        writer.write("Traspaso a fichero de texto \n");
        writer.close();

        //leer de fichero de texto plano
        File fichero = new File("fichero3.txt");
        Scanner lector=null;

        lector=new Scanner(fichero);
        while (lector.hasNextLine()) {
            System.out.println(lector.nextLine());
        }
        lector.close();
    }
}
