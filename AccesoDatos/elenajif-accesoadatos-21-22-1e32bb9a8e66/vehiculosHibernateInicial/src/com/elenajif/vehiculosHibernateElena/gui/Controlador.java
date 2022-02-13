package com.elenajif.vehiculosHibernateElena.gui;

import com.elenajif.vehiculosHibernateElena.Coche;
import com.elenajif.vehiculosHibernateElena.Propietario;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DAM on 04/02/2022.
 */
public class Controlador implements ActionListener,ListSelectionListener{

    private Vista vista;
    private Modelo modelo;

    public Controlador(Vista vista, Modelo modelo)  {
        this.vista=vista;
        this.modelo=modelo;

        addActionListeners(this);
        addListSelectionListener(this);
    }

    private void addActionListeners(ActionListener listener) {
        vista.conexionItem.addActionListener(listener);
        vista.salirItem.addActionListener(listener);
        vista.altaButton.addActionListener(listener);
        vista.borrarButton.addActionListener(listener);
        vista.modificarButton.addActionListener(listener);
        vista.listarCochesButton.addActionListener(listener);
        vista.listarPropietariosButton.addActionListener(listener);
    }


    private void addListSelectionListener(ListSelectionListener listener) {
        vista.listCoches.addListSelectionListener(listener);
        vista.listPropietarios.addListSelectionListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case "Salir":
                modelo.desconectar();
                System.exit(0);
                break;
            case "Conectar":
                vista.conexionItem.setEnabled(false);
                modelo.conectar();
                break;
            case "Alta":
                Coche nuevoCoche = new Coche();
                nuevoCoche.setMatricula(vista.txtMatricula.getText());
                nuevoCoche.setMarca(vista.txtMarca.getText());
                nuevoCoche.setModelo(vista.txtModelo.getText());
                nuevoCoche.setFechaMatriculacion(Timestamp.valueOf(vista.dateTimePicker.getDateTimePermissive()));
                modelo.altaCoche(nuevoCoche);
                break;
            case "Listar":
                listarCoches(modelo.getCoches());
                break;
            case "Modificar":
                Coche cocheSeleccionado=(Coche)vista.listCoches.getSelectedValue();
                cocheSeleccionado.setMatricula(vista.txtMatricula.getText());
                cocheSeleccionado.setMarca(vista.txtMarca.getText());
                cocheSeleccionado.setModelo(vista.txtModelo.getText());
                cocheSeleccionado.setFechaMatriculacion(Timestamp.valueOf(vista.dateTimePicker.getDateTimePermissive()));
                cocheSeleccionado.setPropietario((Propietario)vista.listPropietarios.getSelectedValue());
                modelo.modificar(cocheSeleccionado);
                break;
            case "Borrar":
                Coche cocheBorrado=(Coche)vista.listCoches.getSelectedValue();
                modelo.borrar(cocheBorrado);
                break;
            case "listarPropietarios":
                listarPropietarios(modelo.getPropietarios());
                break;
        }
        listarCoches(modelo.getCoches());

    }

    private void listarPropietarios(ArrayList<Propietario> propietarios) {
        vista.dlmPropietarios.clear();
        for (Propietario propietario:propietarios) {
            vista.dlmPropietarios.addElement(propietario);
        }
    }

    private void listarCoches(ArrayList<Coche> lista) {
        vista.dlm.clear();
        for (Coche unCoche:lista) {
            vista.dlm.addElement(unCoche);
        }
    }

    private void listarCochesPropietarios(List<Coche> lista) {
        vista.dlmCochesPropietarios.clear();
        for (Coche unCoche:lista) {
            vista.dlmCochesPropietarios.addElement(unCoche);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getValueIsAdjusting()){
            if(e.getSource() == vista.listCoches) {
                Coche cocheSeleccion = (Coche) vista.listCoches.getSelectedValue();
                vista.txtId.setText(String.valueOf(cocheSeleccion.getId()));
                vista.txtMarca.setText(cocheSeleccion.getMarca());
                vista.txtMatricula.setText(cocheSeleccion.getMatricula());
                vista.txtModelo.setText(cocheSeleccion.getModelo());
                vista.dateTimePicker.setDateTimePermissive(cocheSeleccion.getFechaMatriculacion().toLocalDateTime());
                if (cocheSeleccion.getPropietario() != null) {
                    vista.txtPropietario.setText(cocheSeleccion.getPropietario().toString());
                } else {
                    vista.txtPropietario.setText("");
                }
            }else{
                if(e.getSource() == vista.listPropietarios){
                    Propietario propietarioSeleccionado = (Propietario)vista.listPropietarios.getSelectedValue();
                    listarCochesPropietarios(modelo.getCochesPropietario(propietarioSeleccionado));
                }
            }
        }
    }
}
