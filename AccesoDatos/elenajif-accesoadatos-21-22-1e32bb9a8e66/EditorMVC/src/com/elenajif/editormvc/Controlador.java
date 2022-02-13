package com.elenajif.editormvc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by DAM on 26/10/2021.
 */
public class Controlador implements ActionListener {
    private Vista vista;
    private Modelo modelo;

    public Controlador(Vista vista, Modelo modelo) {
        this.modelo = modelo;
        this.vista = vista;

        asociarListeners(this);
    }

    private void asociarListeners(ActionListener listener) {
        vista.itemAbrir.addActionListener(listener);
        vista.itemGuardar.addActionListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String accion = e.getActionCommand();

        switch (accion) {
            case "abrir":
            {
                JFileChooser selectorFichero=new JFileChooser();
                int opcion=selectorFichero.showOpenDialog(null);
                if (opcion==JFileChooser.APPROVE_OPTION) {
                    File fichero=selectorFichero.getSelectedFile();
                    try {
                        vista.textArea.setText(modelo.cargarTexto(fichero));
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                }
            }
                break;
            case "guardar":
            {
                JFileChooser selectorFichero=new JFileChooser();
                int opcion=selectorFichero.showSaveDialog(null);
                if (opcion==JFileChooser.APPROVE_OPTION) {
                    File ficheroSeleccionado=selectorFichero.getSelectedFile();
                    try {
                        modelo.guardarTexto(ficheroSeleccionado,vista.textArea.getText());
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                }
            }
                break;
        }
    }
}
