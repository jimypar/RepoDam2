package gui;

import util.Util;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created by DAM on 13/12/2021.
 */
public class Controlador implements ActionListener, ItemListener, WindowListener {
    private Conexion_Pardo conexionPardo;
    private Vista vista;
    boolean refrescar;

    public Controlador(Vista vista, Conexion_Pardo conexionPardo) {
        this.conexionPardo = conexionPardo;
        this.vista = vista;
        conexionPardo.conectar();
        setOptions();
        addActionListeners(this);
        addItemListeners(this);
        addWindowListeners(this);
        refrescarTodo();
    }

    private void refrescarTodo() {
        refrescarAutores();
        refrescar = false;
    }

    private void addActionListeners(ActionListener listener) {
        vista.anadirCliente.addActionListener(listener);
        vista.anadirCliente.setActionCommand("anadir");
        vista.btnInforme.addActionListener(listener);
        vista.btnInforme.setActionCommand("informe");
        vista.btnInformeCiudad.addActionListener(listener);
        vista.btnInformeCiudad.setActionCommand("informeCiudad");
        vista.btnInformeOrdenado.addActionListener(listener);
        vista.btnInformeOrdenado.setActionCommand("informeOrdenado");
        vista.btnInformeBuscar.addActionListener(listener);
        vista.btnInformeBuscar.setActionCommand("informeBuscar");
        vista.btnInformeCP.addActionListener(listener);
        vista.btnInformeCP.setActionCommand("informeCP");
        vista.btnInformeCPbuscar.addActionListener(listener);
        vista.btnInformeCPbuscar.setActionCommand("informeCPBuscar");
        vista.graph1.addActionListener(listener);
        vista.graph1.setActionCommand("grafico1");
        vista.graph2.addActionListener(listener);
        vista.graph2.setActionCommand("grafico2");
        vista.graph3.addActionListener(listener);
        vista.graph3.setActionCommand("grafico3");
    }

    private void addWindowListeners(WindowListener listener) {
        vista.addWindowListener(listener);
    }

    private void addItemListeners(Controlador controlador) {
    }

    /**
     * Muestra los atributos de un objeto seleccionado y los borra una vez se deselecciona
     *
     * @param e Evento producido en una lista
     */


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "anadir": {
                try {
                    if (comprobarAutorVacio()) {
                        Util.showErrorAlert("Rellena todos los campos");
                        vista.autoresTabla.clearSelection();
                    } else {
                        conexionPardo.insertarAutor(vista.txtDni.getText(),
                                vista.txtNombre.getText(),
                                vista.txtApellidos.getText(),
                                vista.txtDireccion.getText(),
                                Integer.parseInt(vista.txtCP.getText()),
                                vista.txtCiudad.getText(),
                                Integer.parseInt(vista.txtTelefono.getText()));
                        refrescarAutores();
                    }
                } catch (NumberFormatException nfe) {
                    Util.showErrorAlert("Introduce números en los campos que lo requieren");
                    vista.autoresTabla.clearSelection();
                }
                borrarCamposAutores();
            }
            break;
            case "informe":
                JasperReport.generar("Clientes_Pardo");
                break;
            case "informeCiudad":
                JasperReport.generar("Clientes_PardoCiudad");
                break;
            case "informeOrdenado":
                JasperReport.generar("ClientesOrdenados_Pardo");
                break;
            case "informeBuscar":
                JTextField ciudad = new JTextField(10);
                String[] options = new String[]{"OK", "Cancel"};
                int option = JOptionPane.showOptionDialog(null, ciudad, "Introduce ciudad",
                        JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options, options[1]);
                if(option == 0)
                { JasperReport.generarBusquedaNombre(ciudad.getText());
                }
                break;
            case "informeCP":
                JasperReport.generar("ClientesCodigoPostal_Pardo");
                break;
            case "informeCPBuscar":
                JTextField codigoPostal = new JTextField(10);
                String[] options2 = new String[]{"OK", "Cancel"};
                int option2 = JOptionPane.showOptionDialog(null, codigoPostal, "Introduce un codigo postal",
                        JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options2, options2[1]);
                if(option2 == 0)
                { JasperReport.generarBusquedaCP(codigoPostal.getText());
                }
                break;
            case "grafico1":
                JasperReport.generar("Grafico1");
                break;
            case "grafico2":
                JasperReport.generar("Grafico2");
                break;
            case "grafico3":
                JasperReport.generar("Grafico3");
                break;
        }
    }



    private void refrescarAutores() {
        try {
            vista.autoresTabla.setModel(construirTableModeloAutores(conexionPardo.consultarAutor()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DefaultTableModel construirTableModeloAutores(ResultSet rs)
            throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        // names of columns
        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }
        // data of the table
        Vector<Vector<Object>> data = new Vector<>();
        setDataVector(rs, columnCount, data);
        vista.dtmAutores.setDataVector(data, columnNames);
        return vista.dtmAutores;
    }

    private void setDataVector(ResultSet rs, int columnCount, Vector<Vector<Object>> data) throws SQLException {
        while (rs.next()) {
            Vector<Object> vector = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }
    }

    private void setOptions() {
    }


    private void borrarCamposAutores() {
        vista.txtDni.setText("");
        vista.txtNombre.setText("");
        vista.txtApellidos.setText("");
        vista.txtDireccion.setText("");
        vista.txtCiudad.setText("");
        vista.txtCP.setText("");
        vista.txtTelefono.setText("");
    }

    private boolean comprobarAutorVacio() {
        return vista.txtApellidos.getText().isEmpty() ||
                vista.txtNombre.getText().isEmpty() ||
                vista.txtDireccion.getText().isEmpty() ||
                vista.txtTelefono.getText().isEmpty()||
                vista.txtCiudad.getText().isEmpty()||
                vista.txtCP.getText().isEmpty() ||
                vista.txtDni.getText().isEmpty();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

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
}
