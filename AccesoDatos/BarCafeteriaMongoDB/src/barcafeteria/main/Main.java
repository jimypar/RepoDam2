package barcafeteria.main;

import barcafeteria.gui.Controlador;
import barcafeteria.gui.Modelo;
import barcafeteria.gui.Vista;

public class Main {
    public static void main(String[] args) {
        new Controlador(new Modelo(), new Vista());
    }
}
