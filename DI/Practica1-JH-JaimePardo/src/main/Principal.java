package main;

import gui.Controlador;
import gui.Conexion_Pardo;
import gui.Vista;

/**
 * Created by DAM on 13/12/2021.
 */
public class Principal {
    public static void main(String[] args) {
        Vista vista = new Vista();
        Conexion_Pardo conexionPardo = new Conexion_Pardo();
        Controlador controlador = new Controlador(vista, conexionPardo);
    }
}
