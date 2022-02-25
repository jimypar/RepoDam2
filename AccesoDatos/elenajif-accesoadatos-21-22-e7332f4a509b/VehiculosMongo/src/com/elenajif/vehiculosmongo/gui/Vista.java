package com.elenajif.vehiculosmongo.gui;

import com.elenajif.vehiculosmongo.base.Coche;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;

/**
 * Created by DAM on 18/02/2022.
 */
public class Vista {
    private JPanel panel1;
    JTextField txtMarca;
    JTextField txtMatricula;
    JTextField txtModelo;
    DatePicker datePicker;
    JTextField txtBuscar;

    JButton nuevoBtn;
    JButton modificarBtn;
    JButton borrarBtn;

    JList<Coche> list1;
    DefaultListModel<Coche> dlm;

    public Vista() {
        JFrame frame = new JFrame("Vista");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        inicializar();
    }

    private void inicializar() {
        dlm=new DefaultListModel<>();
        list1.setModel(dlm);

        datePicker.getComponentToggleCalendarButton().setText("Cal");
    }
}
