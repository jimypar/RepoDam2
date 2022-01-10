package com.elenajif.vehiculosmvc.gui;

import com.elenajif.vehiculosmvc.base.Vehiculo;
import com.elenajif.vehiculosmvc.util.Util;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.Properties;

/**
 * Created by DAM on 28/10/2021.
 */
public class VehiculosControlador implements ActionListener, ListSelectionListener, WindowListener {
    private Ventana vista;
    private VehiculosModelo modelo;
    private File ultimaRutaExportada;

    public VehiculosControlador(Ventana vista, VehiculosModelo modelo) throws IOException {
        this.vista=vista;
        this.modelo=modelo;
        try {
            cargarDatosConfiguracion();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private void cargarDatosConfiguracion() throws IOException {
        Properties config = new Properties();
        config.load(new FileReader("vehiculos.conf"));
        ultimaRutaExportada = new File(config.getProperty("ultimaRutaExportada"));
    }

    private void actualizarDatosConfiguracion(File ultimaRutaExportada){
        this.ultimaRutaExportada = ultimaRutaExportada;
    }

    private void guardarDatosConfiguracion() throws FileNotFoundException {
        Properties config = new Properties();
        config.setProperty("ultimaRutaExportada",ultimaRutaExportada.getAbsolutePath());
        config.setProperty(String.valueOf(new PrintWriter("vehiculos.conf")),"Datos configuracion");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String actionCommand = e.getActionCommand();

        switch (actionCommand){
            case "Nuevo":
                break;
            case "Importar":
                break;
            case "Eexportar":
                break;
            case "Moto":
                break;
            case "Coche":
                break;
        }

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }


    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    private void addActionListener(ActionListener listener){
        vista.cocheRadioButton.addActionListener(listener);
        vista.motoRadioButton.addActionListener(listener);
        vista.exportarBtn.addActionListener(listener);
        vista.importarBtn.addActionListener(listener);
        vista.nuevoBtn.addActionListener(listener);
    }

    private void addWindowListener(WindowListener listener){
        vista.frame.addWindowListener(listener);
    }

    private void addListSelectionListener(ListSelectionListener listener){
        vista.list1.addListSelectionListener(listener);
    }

    private void limpiarCampos(){
        vista.kmsPlazasTxt.setText(null);
        vista.marcaTxt.setText(null);
        vista.matriculaTxt.setText(null);
        vista.modeloTxt.setText(null);
        vista.fechaMatriculacionDPicker.setText(null);
        vista.matriculaTxt.requestFocus();
    }

    private boolean hayCamposVacios(){
        if (vista.kmsPlazasTxt.getText().isEmpty() ||
            vista.marcaTxt.getText().isEmpty() ||
            vista.matriculaTxt.getText().isEmpty() ||
            vista.modeloTxt.getText().isEmpty() ||
            vista.fechaMatriculacionDPicker.getText().isEmpty()){
            return true;
        }
        return false;
    }

    public void refrescar(){
        vista.dlmVehiculo.clear();
        for (Vehiculo vehiculo: modelo.obtenerVehiculos()) {
            vista.dlmVehiculo.addElement(vehiculo);
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {

        int resp = Util.mensajeConfirmacion("Desea cerrar la ventana?", "Salir");
        if (resp== JOptionPane.OK_OPTION){
            try{
                guardarDatosConfiguracion();
            } catch (IOException e1){
                e1.printStackTrace();
            }
        }

    }

}
