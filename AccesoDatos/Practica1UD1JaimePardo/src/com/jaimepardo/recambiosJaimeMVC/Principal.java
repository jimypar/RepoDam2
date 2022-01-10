package com.jaimepardo.recambiosJaimeMVC;

import com.jaimepardo.recambiosJaimeMVC.gui.ControladorCoches;
import com.jaimepardo.recambiosJaimeMVC.gui.ModeloCoches;
import com.jaimepardo.recambiosJaimeMVC.gui.Ventana;

/**
 * Clase principal
 * Created by Jaime Pardo 8/11/21
 */
public class Principal {
    public static void main(String[] args) {
        Ventana vista= new Ventana();
        ModeloCoches modelo=new ModeloCoches();
        ControladorCoches controlador = new ControladorCoches(vista,modelo);
    }
}
