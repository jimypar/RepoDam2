package com.elenajif.vehiculosmongo;

import com.elenajif.vehiculosmongo.gui.Controlador;
import com.elenajif.vehiculosmongo.gui.Modelo;
import com.elenajif.vehiculosmongo.gui.Vista;

/**
 * Created by DAM on 18/02/2022.
 */
public class Principal {
    public static void main(String[] args) {
        Vista vista=new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(vista,modelo);
    }
}
