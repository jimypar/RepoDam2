package com.practicaUD4.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.practicaUD4.base.Fruta;
import com.practicaUD4.base.Pescado;

import javax.swing.*;

/**
 * Clase Vista
 * @author
 */
public class Vista {
    //Campos
    private JPanel panel1;
    //Fruta
    JTextField txtNombre;
    JTextField txtPesoNeto;
    JTextField txtBuscar;
    DatePicker dateCaducidad;
    JList listFrutas;
    JButton btnNuevo;
    JButton btnModificar;
    JButton btnEliminar;
    JTextField txtMarca;
    //Pescado
    private JTabbedPane tabbedPane1;
    JTextField txtNombrePescado;
    JTextField txtMarcaPescado;
    JTextField txtBuscarPescado;
    JTextField txtPesoPescado;
    JList listPescado;
    DatePicker dateCaducidadPescado;
    JButton btnNuevoPescado;
    JButton btnModificarPescado;
    JButton btnEliminarPescado;

    DefaultListModel<Fruta> dlmFrutas;
    DefaultListModel<Pescado> dlmPescado;

    /**
     * Constructor de Vista()
     */
    public Vista() {
        JFrame frame = new JFrame("Vista");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        inicializar();
    }

    /**
     * inicializar(),inicializar modelos
     */
    private void inicializar(){
        dlmFrutas = new DefaultListModel<Fruta>();
        listFrutas.setModel(dlmFrutas);

        dlmPescado = new DefaultListModel<Pescado>();
        listPescado.setModel(dlmPescado);

        dateCaducidad.getComponentToggleCalendarButton().setText("Calendario");
        dateCaducidadPescado.getComponentToggleCalendarButton().setText("Calendario");
    }
}
