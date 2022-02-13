package com.elenajif.colegio;

import com.elenajif.colegio.mvc.Controlador;
import com.elenajif.colegio.mvc.Modelo;
import com.elenajif.colegio.mvc.Vista;

public class Principal {
    public static void main(String[] args) {
        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(vista, modelo);
    }
}
