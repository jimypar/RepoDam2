package com.elenajif.vehiculosHibernateElena.gui;

import com.github.lgooddatepicker.components.DateTimePicker;

import javax.swing.*;

/**
 * Created by DAM on 04/02/2022.
 */
public class Vista {
    private JFrame frame;
    private JPanel panel1;

    JTextField txtId;
    JTextField txtMatricula;
    JTextField txtMarca;
    JTextField txtModelo;
    DateTimePicker dateTimePicker;
    JTextField txtPropietario;

    JButton altaButton;
    JButton modificarButton;
    JButton borrarButton;
    JButton listarCochesButton;
    JButton listarPropietariosButton;

    JList listCoches;
    JList listPropietarios;
    JList listCochesPropietario;

    DefaultListModel dlm;
    DefaultListModel dlmPropietarios;
    DefaultListModel dlmCochesPropietarios;

    JMenuItem conexionItem;
    JMenuItem salirItem;

    public Vista() {
        frame = new JFrame("Vista");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        crearMenu();
        crearModelos();
    }

    private void crearModelos() {
        dlm = new DefaultListModel();
        listCoches.setModel(dlm);
        dlmPropietarios = new DefaultListModel();
        listPropietarios.setModel(dlmPropietarios);
        dlmCochesPropietarios = new DefaultListModel();
        listCochesPropietario.setModel(dlmCochesPropietarios);
    }

    private void crearMenu() {
        JMenuBar barra = new JMenuBar();
        JMenu menu = new JMenu("Archivo");

        conexionItem = new JMenuItem("Conectar");
        conexionItem.setActionCommand("Conectar");

        salirItem = new JMenuItem("Salir");
        salirItem.setActionCommand("Salir");

        menu.add(conexionItem);
        menu.add(salirItem);
        barra.add(menu);
        frame.setJMenuBar(barra);
    }
}
