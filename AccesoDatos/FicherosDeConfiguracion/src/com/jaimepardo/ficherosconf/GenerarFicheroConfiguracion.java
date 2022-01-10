package com.jaimepardo.ficherosconf;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class GenerarFicheroConfiguracion {

    public static void main(String[] args) {

        Properties configuracion = new Properties();

        String usuario = "usuario1";
        String password= "12345";
        String servidor = "localhost";
        int puerto = 3307;

        configuracion.setProperty("user", usuario);
        configuracion.setProperty("password", password);
        configuracion.setProperty("server", servidor);
        configuracion.setProperty("port",String.valueOf(puerto));

        try {
            configuracion.store(new FileOutputStream("configuracion.conf"),"Fichero de configuracion");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
