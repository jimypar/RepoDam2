package com.elenajif.vehiculosHibernateElena;

import com.elenajif.vehiculosHibernateElena.gui.Controlador;
import com.elenajif.vehiculosHibernateElena.gui.Modelo;
import com.elenajif.vehiculosHibernateElena.gui.Vista;

public class Principal {
    public static void main(String[] args) {
        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador=new Controlador(vista,modelo);
    }
}