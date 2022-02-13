package com.elenajif.registrohomicidios;

import com.elenajif.registrohomicidios.mvc.Controlador;
import com.elenajif.registrohomicidios.mvc.Modelo;
import com.elenajif.registrohomicidios.mvc.Vista;

/**
 * Created by PROFESOR on 17/01/2019.
 */
public class Principal {
    public static void main(String[] args) {
        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(vista, modelo);
    }
}
