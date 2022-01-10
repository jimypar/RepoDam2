package com.jaimepardo.recambiosJaimeMVC.gui;

import com.jaimepardo.recambiosJaimeMVC.base.Coche;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;

/**
 * Clase ventana
 * Created by Jaime Pardo 8/11/21
 */
public class Ventana {
    private JPanel panel;
    public JRadioButton combustionRadioButton;
    public JRadioButton electricoRadioButton;
    public JRadioButton hibridoRadioButton;
    public JTextField matriculaTxt;
    public JTextField marcaTxt;
    public JTextField modeloTxt;
    public JLabel potencia;
    public JTextField potenciaTxt;
    public JButton nuevoBtn;
    public JButton exportarBtn;
    public JButton importarBtn;
    public JList list;
    public DatePicker fechaAltaDPicker;
    public JLabel recambio;
    public JComboBox cbRecambios;
    public JButton btnClose;
    public JTextArea potencia2Txt;
    public JLabel potencia2;
    public JButton borrarBtn;

    public JFrame frame;

    public DefaultListModel<Coche> dlmCoches;

    /**
     * Clase que da valores a la ventana
     */
    public Ventana() {
        frame = new JFrame("Recambios Jaime Pardo");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        iniciarComponentes();
    }

    /**
     * Iniciar Componentes
     */
    private void iniciarComponentes() {

        dlmCoches=new DefaultListModel<Coche>();
        list.setModel(dlmCoches);


    }


}
