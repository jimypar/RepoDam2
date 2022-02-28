package agenciaMaria.main;

import agenciaMaria.gui.Controlador;
import agenciaMaria.gui.Modelo;
import agenciaMaria.gui.Vista;

/**
 * Clase donde arranca el programa
 */
public class Main {
    /**
     * Metodo que añade al controlador
     * @param args
     */
    public static void main(String[] args) {
        new Controlador(new Modelo(), new Vista());
    }
}
