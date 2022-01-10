package com.jaimepardo.editormvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener {

    private Vista vista;
    private Modelo modelo;

    public Controlador(Vista vista, Modelo modelo) {
        this.modelo=modelo;
        this.vista=vista;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String accion=e.getActionCommand();

        switch (accion){

            case"abrir":
                break;
            case"guardar":
                break;

        }

    }
}
