package com.jaimepardo.propiedades;

import java.io.File;

public class PropiedadesSistema {

    public static void main(String[] args) {

        System.out.println("caracter separacion rutas");
        System.out.println(File.separator);

        System.out.println("Carpeta personal usuario");

        System.out.println(System.getProperty("user.home"));
        System.out.println("Ruta en la que se encuentra el usuario");
        System.out.println(System.getProperty("user.dir"));


    }

}
