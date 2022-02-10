package main;

import gui.Controlador;
import gui.Conexión_Pardo;
import gui.Vista;

/**
 * Created by DAM on 13/12/2021.
 */
public class Principal {
    public static void main(String[] args) {
        Vista vista = new Vista();
        Conexión_Pardo conexiónPardo = new Conexión_Pardo();
        Controlador controlador = new Controlador(vista, conexiónPardo);
    }
}
