package com.elenajif.ficherosdetexto;

import java.io.*;

/**
 * Created by DAM on 07/10/2021.
 */
public class FicherosDeTexto4 {
    public static void main(String[] args) throws IOException {
        //Con BufferedWriter / Reader
        //escritura de fichero
        FileWriter fw= new FileWriter("fichero4.txt");
        BufferedWriter writer = new BufferedWriter(fw);
        writer.write("texto a escribir\n");
        writer.close();

        //lectura de fichero
        FileReader fr = new FileReader("fichero4.txt");
        BufferedReader reader = new BufferedReader(fr);
        String linea;
        while ((linea=reader.readLine())!=null) {
            System.out.println(linea);
        }
        reader.close();
    }
}
