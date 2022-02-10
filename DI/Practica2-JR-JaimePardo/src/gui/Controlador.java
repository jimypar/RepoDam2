package gui;

import util.Util;

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
    private Conexión_Pardo conexiónPardo;
    private Vista vista;
    boolean refrescar;

    public Controlador(Vista vista, Conexión_Pardo conexiónPardo) {
        this.conexiónPardo = conexiónPardo;
        this.vista = vista;
        conexiónPardo.conectar();
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
                        conexiónPardo.insertarAutor(vista.txtDni.getText(),
                                vista.txtNombre.getText(),
                                vista.txtApellidos.getText(),
                                vista.txtDireccion.getText(),
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
                JasperReport.generar("Clientes_PardoOrdenados");
                break;
        }
    }



    private void refrescarAutores() {
        try {
            vista.autoresTabla.setModel(construirTableModeloAutores(conexiónPardo.consultarAutor()));
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
        vista.txtTelefono.setText("");
    }

    private boolean comprobarAutorVacio() {
        return vista.txtApellidos.getText().isEmpty() ||
                vista.txtNombre.getText().isEmpty() ||
                vista.txtDireccion.getText().isEmpty() ||
                vista.txtTelefono.getText().isEmpty()||
                vista.txtCiudad.getText().isEmpty()||
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
