package com.elenajif.vehiculosmvc;

import com.elenajif.vehiculosmvc.gui.VehiculosControlador;
import com.elenajif.vehiculosmvc.gui.VehiculosModelo;
import com.elenajif.vehiculosmvc.gui.Ventana;

/**
 * Created by DAM on 28/10/2021.
 */
public class Principal {
    public static void main(String[] args) {
        Ventana vista= new Ventana();
        VehiculosModelo modelo=new VehiculosModelo();
        VehiculosControlador controlador = new VehiculosControlador(vista,modelo);
    }
}
