package com.elenajif.registrohomicidios.mvc;

import com.elenajif.registrohomicidios.base.Homicida;
import com.elenajif.registrohomicidios.base.Victima;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;

public class Vista {
    JTabbedPane tabbedPane1;
    private JPanel panel1;
    JFrame frame;
    JTextField nombreTxt;
    JRadioButton hombreRB;
    JRadioButton mujerRadioButton;
    JTextField causaMuerteTxt;
    JComboBox<Homicida> cbHomicidaVictima;
    JList<Victima> listVictimas;
    JButton eliminarVictimaBtn;
    JButton altaVictimaBtn;
    JButton modificarVictimaBtn;
    JTextField apodoTxt;
    JTextField armaTxt;
    JTextField tiempoCarcelTxt;
    JCheckBox asesinoSerieCBox;
    JList<Victima> listVictimasHomicida;
    JButton modificarHomicidaBtn;
    JButton altaHomicidaBtn;
    JButton eliminarHomicidaBtn;
    JList<Homicida> listHomicidas;
    DatePicker datePicker;
    JButton mostrarGraficoBtn;
    DefaultListModel<Homicida> dlmHocimidas;
    DefaultListModel<Victima> dlmVictimas;
    DefaultListModel<Victima> dlmVictimasHomicida;
    DefaultComboBoxModel<Homicida> dcbmHomicidaVictima;
    JRadioButtonMenuItem mysqlItem;
    JRadioButtonMenuItem postgreItem;

    public Vista() {
        frame = new JFrame("Vista");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        iniciarModelos();
        iniciarMenu();
        frame.pack();
        frame.setLocationRelativeTo(null);

    }

    private void iniciarModelos() {
        dlmHocimidas = new DefaultListModel<Homicida>();
        dlmVictimas = new DefaultListModel<Victima>();
        dlmVictimasHomicida = new DefaultListModel<Victima>();
        dcbmHomicidaVictima = new DefaultComboBoxModel<Homicida>();
        listHomicidas.setModel(dlmHocimidas);
        listVictimas.setModel(dlmVictimas);
        listVictimasHomicida.setModel(dlmVictimasHomicida);
        cbHomicidaVictima.setModel(dcbmHomicidaVictima);
    }

    private void iniciarMenu(){
        JMenuBar barra = new JMenuBar();
        JMenu menu = new JMenu("Conexion");
        mysqlItem = new JRadioButtonMenuItem("Conectar a MySql");
        mysqlItem.setSelected(true);
        mysqlItem.setActionCommand("mysql");
        postgreItem = new JRadioButtonMenuItem("Conectar a PostgreSql");
        postgreItem.setActionCommand("postgre");
        menu.add(mysqlItem);
        menu.add(postgreItem);
        barra.add(menu);
        frame.setJMenuBar(barra);

        ButtonGroup grupoConexion = new ButtonGroup();
        grupoConexion.add(mysqlItem);
        grupoConexion.add(postgreItem);
    }

}
