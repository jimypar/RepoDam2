package com.elenajif.vehiculosmvc.gui;

import com.elenajif.vehiculosmvc.base.Vehiculo;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;

/**
 * Created by DAM on 28/10/2021.
 */
public class Ventana {
    private JPanel panel1;
    public JRadioButton cocheRadioButton;
    public JRadioButton motoRadioButton;
    public JTextField matriculaTxt;
    public JTextField marcaTxt;
    public JTextField modeloTxt;
    public JTextField kmsPlazasTxt;
    public JButton nuevoBtn;
    public JButton exportarBtn;
    public JButton importarBtn;
    public JList list1;
    public DatePicker fechaMatriculacionDPicker;
    public JLabel plazasKmsLbl;

    public JFrame frame;

    public DefaultListModel<Vehiculo> dlmVehiculo;

    public Ventana() {
        frame = new JFrame("VehiculosMVC");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        dlmVehiculo=new DefaultListModel<Vehiculo>();
        list1.setModel(dlmVehiculo);
    }


}
