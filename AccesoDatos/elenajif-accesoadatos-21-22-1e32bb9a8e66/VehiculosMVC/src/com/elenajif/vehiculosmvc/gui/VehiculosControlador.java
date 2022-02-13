package com.elenajif.vehiculosmvc.gui;

import com.elenajif.vehiculosmvc.base.Coche;
import com.elenajif.vehiculosmvc.base.Moto;
import com.elenajif.vehiculosmvc.base.Vehiculo;
import com.elenajif.vehiculosmvc.util.Util;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
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

    public VehiculosControlador(Ventana vista, VehiculosModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        try {
            cargarDatosConfiguracion();
        } catch (IOException e) {
            System.out.println("No existe el fichero de configuracion" + e.getMessage());
        }
        //añadimos listener
        addActionListener(this);
        addListSelectionListener(this);
        addWindowListener(this);
    }

    private void cargarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.load(new FileReader("vehiculos.conf"));
        ultimaRutaExportada = new File(configuracion.getProperty("ultimaRutaExportada"));
    }

    private void actualizarDatosConfiguracion(File ultimaRutaExportada) {
        this.ultimaRutaExportada = ultimaRutaExportada;
    }

    private void guardarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.setProperty("ultimaRutaExportada", ultimaRutaExportada.getAbsolutePath());

        configuracion.store(new PrintWriter("vehiculos.conf"),
                "Datos configuracion vehiculos");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "Nuevo":
                if (hayCamposVacios()) {
                    Util.mensajeError("Los siguientes campos no pueden estar vacios \n" +
                            "Matricula \n Marca \n Modelo \n Fecha matriculación\n" +
                            vista.kmsPlazasTxt.getText());
                    break;
                }

                if (modelo.existeMatricula(vista.matriculaTxt.getText())) {
                    Util.mensajeError("Ya existe un vehiculo con esta matricula\n" +
                            vista.kmsPlazasTxt.getText());
                    break;
                }
                if (vista.cocheRadioButton.isSelected()) {
                    modelo.altaCoche(vista.matriculaTxt.getText(),
                            vista.marcaTxt.getText(),
                            vista.modeloTxt.getText(),
                            vista.fechaMatriculacionDPicker.getDate(),
                            Integer.parseInt(vista.kmsPlazasTxt.getText()));
                } else {
                    modelo.altaMoto(vista.matriculaTxt.getText(),
                            vista.marcaTxt.getText(),
                            vista.modeloTxt.getText(),
                            vista.fechaMatriculacionDPicker.getDate(),
                            Double.parseDouble(vista.kmsPlazasTxt.getText()));
                }
                limpiarCampos();
                refrescar();
                break;
            case "Importar":
                JFileChooser selectorFichero =
                        Util.crearSelectorFichero(ultimaRutaExportada,
                                "Archivos XML","xml");
                int opt=selectorFichero.showOpenDialog(null);
                if (opt==JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.importarXML(selectorFichero.getSelectedFile());
                    } catch (ParserConfigurationException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (SAXException e1) {
                        e1.printStackTrace();
                    }
                    refrescar();
                }
                break;
            case "Exportar":
                JFileChooser selectorFichero2=
                        Util.crearSelectorFichero(ultimaRutaExportada,"Archivos XML","xml");
                int opt2=selectorFichero2.showSaveDialog(null);
                if (opt2==JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.exportarXML(selectorFichero2.getSelectedFile());
                        actualizarDatosConfiguracion(selectorFichero2.getSelectedFile());
                    } catch (ParserConfigurationException e1) {
                        e1.printStackTrace();
                    } catch (TransformerException e1) {
                        e1.printStackTrace();
                    }
                }
                break;
            case "Moto":
                vista.plazasKmsLbl.setText("Kms");
                break;
            case "Coche":
                vista.plazasKmsLbl.setText("N Plazas");
                break;
        }
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
        //solo ejecuto el codigo si el valor se está ajustando
        if (e.getValueIsAdjusting()) {
            Vehiculo vehiculoSeleccionado= (Vehiculo) vista.list1.getSelectedValue();
            vista.matriculaTxt.setText(vehiculoSeleccionado.getMatricula());
            vista.marcaTxt.setText(vehiculoSeleccionado.getMarca());
            vista.modeloTxt.setText(vehiculoSeleccionado.getModelo());
            vista.fechaMatriculacionDPicker.setDate(vehiculoSeleccionado.getFechaMatriculacion());

            if (vehiculoSeleccionado instanceof Coche) {
                vista.cocheRadioButton.doClick();
                vista.kmsPlazasTxt.setText(String.valueOf(((Coche) vehiculoSeleccionado).getNumPlazas()));
            } else {
                vista.motoRadioButton.doClick();
                vista.kmsPlazasTxt.setText(String.valueOf(((Moto) vehiculoSeleccionado).getKms()));
            }
        }

    }

    //listener de los radioButton y botones
    private void addActionListener(ActionListener listener) {
        vista.cocheRadioButton.addActionListener(listener);
        vista.motoRadioButton.addActionListener(listener);
        vista.exportarBtn.addActionListener(listener);
        vista.importarBtn.addActionListener(listener);
        vista.nuevoBtn.addActionListener(listener);
    }

    //listener del frame
    private void addWindowListener(WindowListener listener) {
        vista.frame.addWindowListener(listener);
    }

    //listener de la lista
    private void addListSelectionListener(ListSelectionListener listener) {
        vista.list1.addListSelectionListener(listener);
    }

    //limpiar campos
    private void limpiarCampos() {
        vista.kmsPlazasTxt.setText(null);
        vista.marcaTxt.setText(null);
        vista.matriculaTxt.setText(null);
        vista.modeloTxt.setText(null);
        vista.fechaMatriculacionDPicker.setText(null);
        vista.matriculaTxt.requestFocus();
    }

    //comprobar campos vacios
    private boolean hayCamposVacios() {
        if (vista.kmsPlazasTxt.getText().isEmpty() ||
                vista.marcaTxt.getText().isEmpty() ||
                vista.matriculaTxt.getText().isEmpty() ||
                vista.modeloTxt.getText().isEmpty() ||
                vista.fechaMatriculacionDPicker.getText().isEmpty()) {
            return true;
        }
        return false;
    }

    //cargar datos en la lista
    public void refrescar() {
        vista.dlmVehiculo.clear();
        for (Vehiculo unVehiculo : modelo.obtenerVehiculos()) {
            vista.dlmVehiculo.addElement(unVehiculo);
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        int resp = Util.mensajeConfirmacion("¿Desea cerrar la ventana?", "Salir");
        if (resp == JOptionPane.OK_OPTION) {
            try {
                guardarDatosConfiguracion();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.exit(0);
        }
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
    public void windowOpened(WindowEvent e) {

    }
}
