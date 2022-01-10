package com.elenajif.ficherosconfiguracion;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by DAM on 08/10/2021.
 */
public class GenerarFicheroConfiguracion {
    public static void main(String[] args) {
        Properties configuracion = new Properties();
        //alt+intro para importar
        String usuario = "usuario1";
        String password = "12345";
        String servidor = "localhost";
        int puerto = 3304;
        try {
            configuracion.setProperty("user", usuario);
            configuracion.setProperty("password", password);
            configuracion.setProperty("server", servidor);
            configuracion.setProperty("port", String.valueOf(puerto));

            configuracion.store(new FileOutputStream("configuracion.conf"),
                    "Fichero de configuración");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
