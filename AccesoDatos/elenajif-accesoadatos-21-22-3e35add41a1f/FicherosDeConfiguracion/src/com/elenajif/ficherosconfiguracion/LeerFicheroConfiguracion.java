package com.elenajif.ficherosconfiguracion;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by DAM on 08/10/2021.
 */
public class LeerFicheroConfiguracion {
    public static void main(String[] args) {
        Properties configuracion = new Properties();
        try {
            configuracion.load(new FileInputStream("configuracion.conf"));
            String usuario=configuracion.getProperty("user");
            String password=configuracion.getProperty("password");
            String servidor=configuracion.getProperty("server");
            int puerto=Integer.valueOf(configuracion.getProperty("port"));

            System.out.println("usuario "+usuario);
            System.out.println("password "+password);
            System.out.println("servidor "+servidor);
            System.out.println("puerto "+puerto);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
