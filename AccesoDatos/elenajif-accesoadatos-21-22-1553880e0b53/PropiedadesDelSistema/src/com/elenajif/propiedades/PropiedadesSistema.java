package com.elenajif.propiedades;

import java.io.File;

/**
 * Created by DAM on 07/10/2021.
 */
public class PropiedadesSistema {
    //psvm intro
    public static void main(String[] args) {
        //sout
        System.out.println("caracter separacion rutas");
        System.out.println(File.separator);
        //ALT+intro para importar la clase
        System.out.println("Carpeta personal usuario");
        System.out.println(System.getProperty("user.home"));
        System.out.println("Ruta en la que se encuentra el usuario");
        System.out.println(System.getProperty("user.dir"));
    }
}
