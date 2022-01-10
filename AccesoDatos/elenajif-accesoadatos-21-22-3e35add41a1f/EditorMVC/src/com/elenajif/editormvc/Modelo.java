package com.elenajif.editormvc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by DAM on 26/10/2021.
 */
public class Modelo {
    public void guardarTexto(File fichero, String texto) throws FileNotFoundException {
        PrintWriter escritor=new PrintWriter(fichero);
        escritor.println(texto);
        escritor.close();

    }

    public String cargarTexto(File fichero) throws FileNotFoundException {
        Scanner lector = new Scanner(fichero);
        StringBuilder textoLeido=new StringBuilder();

        while (lector.hasNextLine()) {
            textoLeido.append(lector.nextLine());
        }
        lector.close();
        return textoLeido.toString();
    }
}
