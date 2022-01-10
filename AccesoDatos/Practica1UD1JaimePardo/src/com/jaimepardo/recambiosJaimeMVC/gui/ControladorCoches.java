package com.jaimepardo.recambiosJaimeMVC.gui;

import com.jaimepardo.recambiosJaimeMVC.base.*;
import com.jaimepardo.recambiosJaimeMVC.enums.RecambiosCombustion;
import com.jaimepardo.recambiosJaimeMVC.enums.RecambiosElectricos;
import com.jaimepardo.recambiosJaimeMVC.enums.RecambiosHibridos;
import com.jaimepardo.recambiosJaimeMVC.util.Util;
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
 * Clase de Controlador
 * Created by Jaime Pardo 8/11/21
 */
public class ControladorCoches implements ActionListener, ListSelectionListener, WindowListener {
    private Ventana vista;
    private ModeloCoches modelo;
    private File ultimaRutaExportada;

    /**
     * Inicializa la vista y el modelo usados en esta clase
     * Carga los datos de configuracion de la ultima ruta
     * Inicia los listeners
     *
     * @param vista Ventana gui de la aplicacion
     * @param modelo Modelo de coches de la aplicacion
     */
    public ControladorCoches(Ventana vista, ModeloCoches modelo) {
        this.vista = vista;
        this.modelo = modelo;
        try {
            cargarDatosConfiguracion();
        } catch (IOException e) {
            System.out.println("No existe el fichero de configuracion" + e.getMessage());
        }
        //listeners
        addActionListener(this);
        addListSelectionListener(this);
        addWindowListener(this);
    }


    /**
     * Metodo de carga de datos de configuracion de la ultima ruta
     *
     * @throws IOException En caso de que no exista la ruta
     */
    private void cargarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.load(new FileReader("recambios.conf"));
        ultimaRutaExportada = new File(configuracion.getProperty("ultimaRutaExportada"));
    }

    /**
     * Actualiza la ruta
     *
     * @param ultimaRutaExportada Ultima ruta de exportacion
     */
    private void actualizarDatosConfiguracion(File ultimaRutaExportada) {
        this.ultimaRutaExportada = ultimaRutaExportada;
    }


    /**
     *  Guarda los datos de configuracion en el archivo recambios.conf
     *
     * @throws IOException En caso de no encontrar la ruta
     */
    private void guardarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.setProperty("ultimaRutaExportada", ultimaRutaExportada.getAbsolutePath());

        configuracion.store(new PrintWriter("recambios.conf"),
                "Datos configuracion vehiculos");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "Nuevo":
                if (hayCamposVacios()) {
                    Util.mensajeError("Hay campos vacios");
                    break;
                }

                if (modelo.existeMatricula(vista.matriculaTxt.getText())) {
                    Util.mensajeError("Ya existe un coche con esta matricula\n" +
                            vista.matriculaTxt.getText());
                    break;
                }
                if (vista.combustionRadioButton.isSelected()) {
                    if (esNumValido(vista.potenciaTxt.getText())) {
                        modelo.altaCombustion(vista.matriculaTxt.getText(),
                                vista.marcaTxt.getText(),
                                vista.modeloTxt.getText(),
                                vista.fechaAltaDPicker.getDate(),
                                Integer.parseInt(vista.potenciaTxt.getText()),
                                (RecambiosCombustion) vista.cbRecambios.getSelectedItem());
                        limpiarCampos();
                    }else {
                        Util.mensajeError("Introduce numero valido en potencia");
                    }
                } else if (vista.electricoRadioButton.isSelected()){
                    if (esNumValido(vista.potenciaTxt.getText())) {
                        modelo.altaElectrico(vista.matriculaTxt.getText(),
                                vista.marcaTxt.getText(),
                                vista.modeloTxt.getText(),
                                vista.fechaAltaDPicker.getDate(),
                                Integer.parseInt(vista.potenciaTxt.getText()),
                                (RecambiosElectricos) vista.cbRecambios.getSelectedItem());
                        limpiarCampos();
                    }else {
                        Util.mensajeError("Introduce numero valido en potencia");
                    }
                } else if (vista.hibridoRadioButton.isSelected()){
                    if (esNumValido(vista.potenciaTxt.getText()) && esNumValido(vista.potencia2Txt.getText())) {
                    modelo.altaHibrido(vista.matriculaTxt.getText(),
                            vista.marcaTxt.getText(),
                            vista.modeloTxt.getText(),
                            vista.fechaAltaDPicker.getDate(),
                            Integer.parseInt(vista.potenciaTxt.getText()),
                            Integer.parseInt(vista.potencia2Txt.getText()),
                            (RecambiosHibridos) vista.cbRecambios.getSelectedItem());
                    limpiarCampos();
                    }else {
                        Util.mensajeError("Introduce numero valido en potencia");
                    }
                }


                refrescar();
                break;
            case "Importar":
                JFileChooser selectorFichero =
                        Util.crearSelectorFichero(ultimaRutaExportada,
                                "Archivos.XML","xml");
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
            case "Borrar":
                try {
                    modelo.bajaCoche((Coche) vista.list.getSelectedValue());
                }catch (NullPointerException n){
                    Util.mensajeError("Ningun coche seleccionado");
                }
                refrescar();
                break;
            case "Combustion":
                vista.recambio.setText("Recambio Combustion");
                vista.potencia.setText("CV");
                vista.potencia2.setVisible(false);
                vista.potencia2Txt.setVisible(false);
                vista.cbRecambios.setModel(new DefaultComboBoxModel<>(RecambiosCombustion.values()));
                break;
            case "Electrico":
                vista.recambio.setText("Recambio Electrico");
                vista.potencia.setText("kW");
                vista.potencia2.setVisible(false);
                vista.potencia2Txt.setVisible(false);
                vista.cbRecambios.setModel(new DefaultComboBoxModel<>(RecambiosElectricos.values()));
                break;
            case "Hibrido":
                vista.recambio.setText("Recambio Hibrido");
                vista.potencia.setText("CV");
                vista.potencia2.setVisible(true);
                vista.potencia2Txt.setVisible(true);
                vista.cbRecambios.setModel(new DefaultComboBoxModel<>(RecambiosHibridos.values()));
                break;
            case "Cerrar":
                int resp = Util.mensajeConfirmacion("¿Desea salir?", "Salir");
                if (resp == JOptionPane.OK_OPTION) {
                    try {
                        guardarDatosConfiguracion();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    System.exit(0);
                }
                break;
        }
    }


    /**
     * Comprueba que es un numero positivo
     *
     * @param text Campo donde hay un numero
     * @return Devuelve true si es valido el numero
     */
    private boolean esNumValido(String text) {
        int num = 0;
        try {
            num = Integer.parseInt(text);
        } catch (NumberFormatException e){
        }

        if (num>0){
            return true;
        }else {
            return false;
        }

    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
        //solo ejecuto el codigo si el valor se está ajustando
        if (e.getValueIsAdjusting()) {
            Coche cocheSeleccionado = (Coche) vista.list.getSelectedValue();
            vista.matriculaTxt.setText(cocheSeleccionado.getMatricula());
            vista.marcaTxt.setText(cocheSeleccionado.getMarca());
            vista.modeloTxt.setText(cocheSeleccionado.getModelo());
            vista.fechaAltaDPicker.setDate(cocheSeleccionado.getFechaAlta());

            if (cocheSeleccionado instanceof Combustion) {
                vista.combustionRadioButton.doClick();
                vista.potenciaTxt.setText(String.valueOf(((Combustion) cocheSeleccionado).getCaballos()));
                vista.cbRecambios.setSelectedItem(((Combustion) cocheSeleccionado).getRecambio());
            } else if (cocheSeleccionado instanceof Electrico){
                vista.electricoRadioButton.doClick();
                vista.potenciaTxt.setText(String.valueOf(((Electrico) cocheSeleccionado).getKiloVatios()));
                vista.cbRecambios.setSelectedItem(((Electrico) cocheSeleccionado).getRecambio());
            } else if (cocheSeleccionado instanceof Hibrido){
                vista.hibridoRadioButton.doClick();
                vista.potenciaTxt.setText(String.valueOf(((Hibrido) cocheSeleccionado).getCaballos()));
                vista.potencia2Txt.setText(String.valueOf(((Hibrido) cocheSeleccionado).getKiloVatios()));
                vista.cbRecambios.setSelectedItem(((Hibrido) cocheSeleccionado).getRecambio());
            }
        }

    }

    //listener de los botones
    private void addActionListener(ActionListener listener) {
        vista.nuevoBtn.addActionListener(listener);
        vista.combustionRadioButton.addActionListener(listener);
        vista.electricoRadioButton.addActionListener(listener);
        vista.hibridoRadioButton.addActionListener(listener);
        vista.exportarBtn.addActionListener(listener);
        vista.importarBtn.addActionListener(listener);
        vista.btnClose.addActionListener(listener);
        vista.borrarBtn.addActionListener(listener);
    }

    //listener de frame
    private void addWindowListener(WindowListener listener) {
        vista.frame.addWindowListener(listener);
    }

    //listener de la lista
    private void addListSelectionListener(ListSelectionListener listener) {
        vista.list.addListSelectionListener(listener);
    }

    //limpiar campos
    private void limpiarCampos() {
        vista.marcaTxt.setText(null);
        vista.matriculaTxt.setText(null);
        vista.modeloTxt.setText(null);
        vista.potenciaTxt.setText(null);
        vista.potencia2Txt.setText(null);
        vista.fechaAltaDPicker.setText(null);
        vista.matriculaTxt.requestFocus();
    }

    /**
     *
     * Comprueba si hay campos vacios
     *
     * @return true si hay algun campo vacio
     */
    private boolean hayCamposVacios() {
        if (vista.hibridoRadioButton.isSelected()){
            if (vista.marcaTxt.getText().isEmpty() ||
                    vista.matriculaTxt.getText().isEmpty() ||
                    vista.modeloTxt.getText().isEmpty() ||
                    vista.potenciaTxt.getText().isEmpty() ||
                    vista.potencia2Txt.getText().isEmpty() ||
                    vista.fechaAltaDPicker.getText().isEmpty()) {
                return true;
            }
        } else{
            if (vista.marcaTxt.getText().isEmpty() ||
                    vista.matriculaTxt.getText().isEmpty() ||
                    vista.modeloTxt.getText().isEmpty() ||
                    vista.potenciaTxt.getText().isEmpty() ||
                    vista.fechaAltaDPicker.getText().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Carga los datos en la lista cada vez
     */
    public void refrescar() {
        vista.dlmCoches.clear();
        for (Coche unCoche : modelo.getListaCoches()) {
            vista.dlmCoches.addElement(unCoche);
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        int resp = Util.mensajeConfirmacion("¿Desea salir?", "Salir");
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
