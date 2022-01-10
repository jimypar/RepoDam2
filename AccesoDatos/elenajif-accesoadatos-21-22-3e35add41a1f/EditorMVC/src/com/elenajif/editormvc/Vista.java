package com.elenajif.editormvc;

import javax.swing.*;

/**
 * Created by DAM on 26/10/2021.
 */
public class Vista {
    private JPanel panel1;
    JTextArea textArea;
    private JFrame frame;
    JMenuItem itemGuardar;
    JMenuItem itemAbrir;

    public Vista() {
        frame = new JFrame("EditorMVC");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        crearBarraMenu();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void crearBarraMenu() {
        JMenuBar barra=new JMenuBar();

        JMenu menu = new JMenu("Archivo");

        itemGuardar=new JMenuItem("Guardar");
        itemAbrir=new JMenuItem("Abrir");

        itemGuardar.setActionCommand("guardar");
        itemAbrir.setActionCommand("abrir");

        menu.add(itemAbrir);
        menu.add(itemGuardar);
        barra.add(menu);
        frame.setJMenuBar(barra);
    }
}
