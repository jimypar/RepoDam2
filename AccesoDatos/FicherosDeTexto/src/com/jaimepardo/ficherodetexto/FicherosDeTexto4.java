package com.jaimepardo.ficherodetexto;

import java.io.*;
import java.util.Scanner;

public class FicherosDeTexto4 {

    public static void main(String[] args) throws IOException {

        FileWriter fw = new FileWriter("fichero3.txt");
        BufferedWriter writer = new BufferedWriter(fw);
        writer.write("texto a escribir");
        writer.close();

        FileReader fr = new FileReader("fichero3.txt");
        BufferedReader reader = new BufferedReader(fr);
        String linea;
        while ((linea = reader.readLine()) != null) {

            System.out.println(linea);

        }

        reader.close();

    }

}
