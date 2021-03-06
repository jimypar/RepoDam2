package gui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created by DAM on 13/12/2021.
 */
public class Controlador implements ActionListener, ItemListener, ListSelectionListener, WindowListener {
    private Modelo modelo;
    private Vista vista;
    boolean refrescar;

    public Controlador(Vista vista, Modelo modelo) {
        this.modelo = modelo;
        this.vista = vista;
        modelo.conectar();
        setOptions();
        addActionListeners(this);
        addItemListeners(this);
        addWindowListeners(this);
        refrescarTodo();
    }

    private void refrescarTodo() {
        refrescarAutores();
        refrescarEditorial();
        refrescarLibros();
        refrescar = false;
    }

    private void addActionListeners(ActionListener listener) {
        vista.btnLibrosAnadir.addActionListener(listener);
        vista.btnAutoresAnadir.addActionListener(listener);
        vista.btnEditorialesAnadir.addActionListener(listener);
        vista.btnLibrosEliminar.addActionListener(listener);
        vista.btnAutoresEliminar.addActionListener(listener);
        vista.btnEditorialesEliminar.addActionListener(listener);
        vista.btnLibrosModificar.addActionListener(listener);
        vista.btnAutoresModificar.addActionListener(listener);
        vista.btnEditorialesModificar.addActionListener(listener);
        vista.optionDialog.btnOpcionesGuardar.addActionListener(listener);
        vista.itemOpciones.addActionListener(listener);
        vista.itemSalir.addActionListener(listener);
        vista.btnValidate.addActionListener(listener);
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
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()
                && !((ListSelectionModel) e.getSource()).isSelectionEmpty()) {
            if (e.getSource().equals(vista.editorialesTabla.getSelectionModel())) {
                int row = vista.editorialesTabla.getSelectedRow();
                vista.txtNombreEditorial.setText(String.valueOf(vista.editorialesTabla.getValueAt(row, 1)));
                vista.txtEmail.setText(String.valueOf(vista.editorialesTabla.getValueAt(row, 2)));
                vista.txtTelefono.setText(String.valueOf(vista.editorialesTabla.getValueAt(row, 3)));
                vista.comboTipoEditorial.setSelectedItem(String.valueOf(vista.editorialesTabla.getValueAt(row, 4)));
                vista.txtWeb.setText(String.valueOf(vista.editorialesTabla.getValueAt(row, 5)));
            } else if (e.getSource().equals(vista.autoresTabla.getSelectionModel())) {
                int row = vista.autoresTabla.getSelectedRow();
                vista.txtNombre.setText(String.valueOf(vista.autoresTabla.getValueAt(row, 1)));
                vista.txtApellidos.setText(String.valueOf(vista.autoresTabla.getValueAt(row, 2)));
                vista.fechaNacimiento.setDate((Date.valueOf(String.valueOf(vista.autoresTabla.getValueAt(row, 3)))).toLocalDate());
                vista.txtPais.setText(String.valueOf(vista.autoresTabla.getValueAt(row, 4)));
            } else if (e.getSource().equals(vista.librosTabla.getSelectionModel())) {
                int row = vista.librosTabla.getSelectedRow();
                vista.txtTitulo.setText(String.valueOf(vista.librosTabla.getValueAt(row, 1)));
                vista.comboAutor.setSelectedItem(String.valueOf(vista.librosTabla.getValueAt(row, 5)));
                vista.comboEditorial.setSelectedItem(String.valueOf(vista.librosTabla.getValueAt(row, 3)));
                vista.comboGenero.setSelectedItem(String.valueOf(vista.librosTabla.getValueAt(row, 4)));
                vista.fecha.setDate((Date.valueOf(String.valueOf(vista.librosTabla.getValueAt(row, 7)))).toLocalDate());
                vista.txtIsbn.setText(String.valueOf(vista.librosTabla.getValueAt(row, 2)));
                vista.txtPrecioLibro.setText(String.valueOf(vista.librosTabla.getValueAt(row, 6)));
            } else if (e.getValueIsAdjusting()
                    && ((ListSelectionModel) e.getSource()).isSelectionEmpty() && !refrescar) {
                if (e.getSource().equals(vista.editorialesTabla.getSelectionModel())) {
                    borrarCamposEditoriales();
                } else if (e.getSource().equals(vista.autoresTabla.getSelectionModel())) {
                    borrarCamposAutores();
                } else if (e.getSource().equals(vista.librosTabla.getSelectionModel())) {
                    borrarCamposLibros();
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Opciones":
                break;
            case "Desconectar":
                break;
            case "Salir":
                break;
            case "abrirOpciones":
                break;
            case "guardarOpciones":
                break;
            case "anadirLibro":
                break;
            case "modificarLibro":
                break;
            case "eliminarLibro":
                break;
            case "anadirAutor":
                break;
            case "modificarAutor":
                break;
            case "eliminarAutor":
                break;
            case "anadirEditorial":
                break;
            case "modificarEditorial":
                break;
            case "eliminarEditorial":
                break;
        }
    }

    private void refrescarEditorial() {
        try {
            vista.editorialesTabla.setModel(construirTableModelEditoriales(modelo.consultarEditorial()));
            vista.comboEditorial.removeAllItems();
            for(int i = 0; i < vista.dtmEditoriales.getRowCount(); i++) {
                vista.comboEditorial.addItem(vista.dtmEditoriales.getValueAt(i, 0)+" - "+
                        vista.dtmEditoriales.getValueAt(i, 1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DefaultTableModel construirTableModelEditoriales(ResultSet rs)
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
        vista.dtmEditoriales.setDataVector(data, columnNames);
        return vista.dtmEditoriales;
    }

    private void refrescarAutores() {
        try {
            vista.autoresTabla.setModel(construirTableModeloAutores(modelo.consultarAutor()));
            vista.comboAutor.removeAllItems();
            for(int i = 0; i < vista.dtmAutores.getRowCount(); i++) {
                vista.comboAutor.addItem(vista.dtmAutores.getValueAt(i, 0)+" - "+
                        vista.dtmAutores.getValueAt(i, 2)+", "+vista.dtmAutores.getValueAt(i, 1));
            }
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

    /**
     * Actualiza los libros que se ven en la lista y los comboboxes
     */
    private void refrescarLibros() {
        try {
            vista.librosTabla.setModel(construirTableModelLibros(modelo.consultarLibros()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DefaultTableModel construirTableModelLibros(ResultSet rs)
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
        vista.dtmLibros.setDataVector(data, columnNames);
        return vista.dtmLibros;

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

    private void borrarCamposLibros() {
        vista.comboEditorial.setSelectedIndex(-1);
        vista.comboAutor.setSelectedIndex(-1);
        vista.txtTitulo.setText("");
        vista.txtIsbn.setText("");
        vista.comboGenero.setSelectedIndex(-1);
        vista.txtPrecioLibro.setText("");
        vista.fecha.setText("");
    }

    private void borrarCamposAutores() {
        vista.txtNombre.setText("");
        vista.txtApellidos.setText("");
        vista.txtPais.setText("");
        vista.fechaNacimiento.setText("");
    }

    private void borrarCamposEditoriales() {
        vista.txtNombreEditorial.setText("");
        vista.txtEmail.setText("");
        vista.txtTelefono.setText("");
        vista.comboTipoEditorial.setSelectedIndex(-1);
        vista.txtWeb.setText("");
    }

    private boolean comprobarLibroVacio() {
        return vista.txtTitulo.getText().isEmpty() ||
                vista.txtPrecioLibro.getText().isEmpty() ||
                vista.txtIsbn.getText().isEmpty() ||
                vista.comboGenero.getSelectedIndex() == -1 ||
                vista.comboAutor.getSelectedIndex() == -1 ||
                vista.comboEditorial.getSelectedIndex() == -1 ||
                vista.fecha.getText().isEmpty();
    }

    private boolean comprobarAutorVacio() {
        return vista.txtApellidos.getText().isEmpty() ||
                vista.txtNombre.getText().isEmpty() ||
                vista.txtPais.getText().isEmpty() ||
                vista.fechaNacimiento.getText().isEmpty();
    }

    private boolean comprobarEditorialVacia() {
        return vista.txtNombreEditorial.getText().isEmpty() ||
                vista.txtEmail.getText().isEmpty() ||
                vista.txtTelefono.getText().isEmpty() ||
                vista.comboTipoEditorial.getSelectedIndex() == -1 ||
                vista.txtWeb.getText().isEmpty();
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
