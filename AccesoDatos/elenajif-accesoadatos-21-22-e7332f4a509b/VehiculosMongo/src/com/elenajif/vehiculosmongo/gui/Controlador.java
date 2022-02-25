package com.elenajif.vehiculosmongo.gui;

import com.elenajif.vehiculosmongo.base.Coche;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

/**
 * Created by DAM on 18/02/2022.
 */
public class Controlador implements ActionListener, KeyListener, ListSelectionListener {
    Vista vista;
    Modelo modelo;

    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        inicializar();
    }

    private void inicializar() {
        addActionListener(this);
        addKeyListener(this);
        addListSelectionListener(this);
        modelo.conectar();
        listarCoches(modelo.getCoches());
    }

    private void addActionListener(ActionListener listener) {
        vista.nuevoBtn.addActionListener(listener);
        vista.modificarBtn.addActionListener(listener);
        vista.borrarBtn.addActionListener(listener);
    }

    private void addListSelectionListener(ListSelectionListener listener) {
        vista.list1.addListSelectionListener(listener);
    }

    private void addKeyListener(KeyListener listener) {
        vista.txtBuscar.addKeyListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        Coche unCoche;
        switch (comando) {
            case "Nuevo":
                unCoche=new Coche();
                modificarCocheFromCampos(unCoche);
                modelo.guardarCoche(unCoche);
                listarCoches(modelo.getCoches());
                break;
            case "Modificar":
                unCoche=vista.list1.getSelectedValue();
                modificarCocheFromCampos(unCoche);
                modelo.modificarCoche(unCoche);
                listarCoches(modelo.getCoches());
                break;
            case "Borrar":
                unCoche=vista.list1.getSelectedValue();
                modelo.borrarCoche(unCoche);
                listarCoches(modelo.getCoches());
                break;
        }
    }

    private void listarCoches(List<Coche> lista) {
        vista.dlm.clear();
        for (Coche coche: lista) {
            vista.dlm.addElement(coche);
        }

    }

    private void modificarCocheFromCampos(Coche unCoche) {
        unCoche.setMarca(vista.txtMarca.getText());
        unCoche.setModelo(vista.txtModelo.getText());
        unCoche.setMatricula(vista.txtMatricula.getText());
        unCoche.setFechaMatriculacion(vista.datePicker.getDate());
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            Coche unCoche = vista.list1.getSelectedValue();
            vista.txtModelo.setText(unCoche.getModelo());
            vista.txtMarca.setText(unCoche.getMarca());
            vista.txtMatricula.setText(unCoche.getMatricula());
            vista.datePicker.setDate(unCoche.getFechaMatriculacion());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == vista.txtBuscar) {
            listarCoches(modelo.getCoches(vista.txtBuscar.getText()));
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }


}
