package com.jaimepardo.recambiosbbddmvc;

import com.github.lgooddatepicker.components.DateTimePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Vista {
    private JPanel panel1;
    JTextField txtMatricula;
    JTextField txtMarca;
    JTextField txtPotencia;
    JTable table1;
    JButton listarBtn;
    JButton insertarBtn;
    JButton eliminarBtn;
     DateTimePicker dateTimePicker;
     DefaultTableModel dtm;

    public Vista() {
        JFrame frame = new JFrame("Vista");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        iniciarTabla();
    }

    private void iniciarTabla(){
        dtm = new DefaultTableModel();
        table1.setModel(dtm);

        Object[] cabeceras = {"id", "matricula", "marca", "potencia", "fecha alta"};

        dtm.setColumnIdentifiers(cabeceras);
    }

}
