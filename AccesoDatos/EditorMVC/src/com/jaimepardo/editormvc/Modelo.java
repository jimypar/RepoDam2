package com.jaimepardo.editormvc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Modelo {

    public void guardarTexto(File fichero, String texto) throws FileNotFoundException {
        PrintWriter p = new PrintWriter(fichero);
        p.println(texto);
    }

    public String cargarTexto(File fichero) throws FileNotFoundException {
        Scanner lector = new Scanner(fichero);
        StringBuilder textoLeido = new StringBuilder();

        while (lector.hasNextLine()){
            textoLeido.append(lector.nextLine());
        }
        lector.close();

        return textoLeido.toString();
    }
}
