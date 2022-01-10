package com.jaimepardo.recambiosbbddmvc;

import java.sql.SQLException;

public class Principal {
    public static void main(String[] args) throws SQLException {
        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(vista, modelo);
    }
}
