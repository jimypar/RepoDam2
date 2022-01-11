package gui;

import util.Util;

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
    static int mecanicos;
    static int recambios;

    public Controlador(Vista vista, Modelo modelo) {
        this.modelo = modelo;
        this.vista = vista;
        mecanicos = 1;
        recambios = 1;
        modelo.conectar();
        setOptions();
        addActionListeners(this);
        addItemListeners(this);
        addWindowListeners(this);
        refrescarTodo();
    }

    private void refrescarTodo() {
        refrescarMecanico();
        refrescarCliente();
        refrescarCoche();
        refrescar = false;
    }

    private void addActionListeners(ActionListener listener) {
        vista.btnCocheAnadir.addActionListener(listener);
        vista.btnCocheAnadir.setActionCommand("btnCocheAnadir");
        vista.btnClienteAnadir.addActionListener(listener);
        vista.btnClienteAnadir.setActionCommand("btnClienteAnadir");
        vista.btnMecanicoAnadir.addActionListener(listener);
        vista.btnMecanicoAnadir.setActionCommand("btnMecanicoAnadir");
        vista.btnCocheEliminar.addActionListener(listener);
        vista.btnCocheEliminar.setActionCommand("btnCocheEliminar");
        vista.btnClienteEliminar.addActionListener(listener);
        vista.btnClienteEliminar.setActionCommand("btnClienteEliminar");
        vista.btnMecanicoEliminar.addActionListener(listener);
        vista.btnMecanicoEliminar.setActionCommand("btnMecanicoEliminar");
        vista.btnCocheModificar.addActionListener(listener);
        vista.btnCocheModificar.setActionCommand("btnCocheModificar");
        vista.btnClienteModificar.addActionListener(listener);
        vista.btnClienteModificar.setActionCommand("btnClienteModificar");
        vista.btnMecanicoModificar.addActionListener(listener);
        vista.btnMecanicoModificar.setActionCommand("btnMecanicoModificar");
        vista.optionDialog.btnOpcionesGuardar.addActionListener(listener);
        vista.itemOpciones.addActionListener(listener);
        vista.itemSalir.addActionListener(listener);
        vista.btnValidate.addActionListener(listener);
        vista.addMecanico.addActionListener(listener);
        vista.addMecanico.setActionCommand("addMecanico");
        vista.addRecambio.addActionListener(listener);
        vista.addRecambio.setActionCommand("addRecambio");
        vista.delMecanico.addActionListener(listener);
        vista.delMecanico.setActionCommand("delMecanico");
        vista.delRecambio.addActionListener(listener);
        vista.delRecambio.setActionCommand("delRecambio");
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
            if (e.getSource().equals(vista.mecanicoTabla.getSelectionModel())) {
                int row = vista.mecanicoTabla.getSelectedRow();
                vista.txtNombreMecanico.setText(String.valueOf(vista.mecanicoTabla.getValueAt(row, 1)));
                vista.txtApellidoMecanico.setText(String.valueOf(vista.mecanicoTabla.getValueAt(row, 2)));
                vista.txtTelefonoMecanico.setText(String.valueOf(vista.mecanicoTabla.getValueAt(row, 3)));
                vista.comboMecanico1.setSelectedItem(String.valueOf(vista.mecanicoTabla.getValueAt(row, 4)));
            } else if (e.getSource().equals(vista.clienteTabla.getSelectionModel())) {
                int row = vista.clienteTabla.getSelectedRow();
                vista.txtDni.setText(String.valueOf(vista.clienteTabla.getValueAt(row, 1)));
                vista.txtNombre.setText(String.valueOf(vista.clienteTabla.getValueAt(row, 2)));
                vista.txtApellidos.setText(String.valueOf(vista.clienteTabla.getValueAt(row, 3)));
                vista.txtEmail.setText(String.valueOf(vista.clienteTabla.getValueAt(row, 4)));
                vista.txtTelefono.setText(String.valueOf(vista.clienteTabla.getValueAt(row, 5)));
            } else if (e.getSource().equals(vista.cochesTabla.getSelectionModel())) {
                int row = vista.cochesTabla.getSelectedRow();
                vista.txtMatricula.setText(String.valueOf(vista.cochesTabla.getValueAt(row, 1)));
                vista.txtMarca.setText(String.valueOf(vista.cochesTabla.getValueAt(row, 2)));
                vista.fecha.setDate((Date.valueOf(String.valueOf(vista.clienteTabla.getValueAt(row, 3)))).toLocalDate());
                vista.comboMecanico1.setSelectedItem(String.valueOf(vista.cochesTabla.getValueAt(row, 4)));
                vista.comboCliente.setSelectedItem(String.valueOf(vista.cochesTabla.getValueAt(row, 5)));
                vista.comboRecambio1.setSelectedItem(String.valueOf(vista.cochesTabla.getValueAt(row, 6)));

            } else if (e.getValueIsAdjusting()
                    && ((ListSelectionModel) e.getSource()).isSelectionEmpty() && !refrescar) {
                if (e.getSource().equals(vista.mecanicoTabla.getSelectionModel())) {
                    borrarCamposMecanico();
                } else if (e.getSource().equals(vista.clienteTabla.getSelectionModel())) {
                    borrarCamposCliente();
                } else if (e.getSource().equals(vista.cochesTabla.getSelectionModel())) {
                    borrarcamposCoche();
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Opciones":
                vista.adminPasswordDialog.setVisible(true);
                break;
            case "Desconectar":
                modelo.desconectar();
                break;
            case "Salir":
                System.exit(0);
                break;
            case "abrirOpciones":
                if (String.valueOf(vista.adminPassword.getPassword()).equals(modelo.getAdminPassword())) {
                    vista.adminPassword.setText("");
                    vista.adminPasswordDialog.dispose();
                    vista.optionDialog.setVisible(true);
                } else {
                    Util.showErrorAlert("La contraseña introducida no es correcta");
                }
                break;
            case "guardarOpciones":
                modelo.setPropValues(vista.optionDialog.tfIP.getText(),
                        vista.optionDialog.tfUser.getText(),
                        String.valueOf(vista.optionDialog.pfPass.getPassword()),
                        String.valueOf(vista.optionDialog.pfAdmin.getPassword()));
                vista.optionDialog.dispose();
                vista.dispose();
                new Controlador(new Vista(),new Modelo());
                break;
            case "btnCocheAnadir":
                try {
                    if (comprobarCocheVacio()) {
                        Util.showErrorAlert("Rellena todos los campos");
                        vista.cochesTabla.clearSelection();
                    } else if (modelo.cocheMatriculaYaExiste(vista.txtMatricula.getText())) {
                        Util.showErrorAlert("Ese coche ya existe");
                        vista.cochesTabla.clearSelection();
                    } else {
                        modelo.insertarCoche(
                                tipoCoche(),
                                vista.txtMatricula.getText(),
                                String.valueOf(vista.comboMarca.getSelectedItem()),
                                vista.fecha.getDate(),
                                String.valueOf(vista.comboCliente.getSelectedItem()));
                    }
                } catch (NumberFormatException nfe) {
                    Util.showErrorAlert("Introduce numeros en los campos que lo requieran");
                    vista.cochesTabla.clearSelection();
                }
                borrarcamposCoche();
                refrescarCoche();
                break;
            case "btnCocheModificar":
                try {
                    if (comprobarCocheVacio()) {
                        Util.showErrorAlert("Rellena todos los campos");
                        vista.cochesTabla.clearSelection();
                    } else {
                        modelo.modificarCoche(
                                tipoCoche(),
                                vista.txtMatricula.getText(),
                                String.valueOf(vista.comboMarca.getSelectedItem()),
                                vista.fecha.getDate(),
                                String.valueOf(vista.comboCliente.getSelectedItem()),
                                Integer.parseInt((String) vista.cochesTabla.getValueAt(vista.cochesTabla.getSelectedRow(), 0))
                        );
                    }
                } catch (NumberFormatException nfe) {
                    Util.showErrorAlert("Introduce numeros en los campos que lo requieran");
                    vista.cochesTabla.clearSelection();
                }
                borrarcamposCoche();
                refrescarCoche();
                break;
            case "btnCocheEliminar":
                modelo.borrarCoche(Integer.parseInt((String) vista.cochesTabla.getValueAt(vista.cochesTabla.getSelectedRow(), 0)));
                borrarcamposCoche();
                refrescarCoche();
                break;
            case "btnMecanicoAnadir":
                System.out.println("add");
                try {
                    if (comprobarMecanicoVacio()) {
                        Util.showErrorAlert("Rellena todos los campos");
                        vista.mecanicoTabla.clearSelection();
                    } else {
                        modelo.insertarMecanico(vista.txtNombreMecanico.getText(),
                                vista.txtApellidoMecanico.getText(),
                                vista.txtTelefonoMecanico.getText());
                        refrescarMecanico();
                    }
                } catch (NumberFormatException nfe) {
                    Util.showErrorAlert("Introduce números en los campos que lo requieren");
                    vista.mecanicoTabla.clearSelection();
                }
                borrarCamposMecanico();
                break;
            case "btnMecanicoModificar":
                try {
                    if (comprobarMecanicoVacio()) {
                        Util.showErrorAlert("Rellena todos los campos");
                        vista.mecanicoTabla.clearSelection();
                    } else {
                        modelo.modificarMecanico(vista.txtNombre.getText(),
                                vista.txtApellidos.getText(),
                                vista.txtTelefonoMecanico.getText(),
                                Integer.parseInt((String) vista.mecanicoTabla.getValueAt(vista.mecanicoTabla.getSelectedRow(), 0)));
                        refrescarMecanico();
                    }
                } catch (NumberFormatException nfe) {
                    Util.showErrorAlert("Introduce números en los campos que lo requieren");
                    vista.mecanicoTabla.clearSelection();
                }
                borrarCamposMecanico();
                break;
            case "btnMecanicoEliminar":
                modelo.borrarMecanico(Integer.parseInt((String) vista.mecanicoTabla.getValueAt(vista.mecanicoTabla.getSelectedRow(), 0)));
                borrarCamposMecanico();
                refrescarMecanico();
                break;
            case "btnClienteAnadir":
                try {
                    if (comprobarClienteVacio()) {
                        Util.showErrorAlert("Rellena todos los campos");
                        vista.clienteTabla.clearSelection();
                    } else if (modelo.dniClienteYaExiste(vista.txtDni.getText())) {
                        Util.showErrorAlert("Ese nombre ya existe.\nIntroduce una editorial diferente.");
                        vista.clienteTabla.clearSelection();
                    } else {
                        modelo.insertarCliente(vista.txtDni.getText(), vista.txtNombre.getText(),
                                vista.txtApellidos.getText(),
                                vista.txtEmail.getText(),
                                vista.txtTelefono.getText());
                        refrescarCliente();
                    }
                } catch (NumberFormatException nfe) {
                    Util.showErrorAlert("Introduce números en los campos que lo requieren");
                    vista.clienteTabla.clearSelection();
                }
                borrarCamposCliente();
                break;
            case "btnClienteModificar":
                try {
                    if (comprobarClienteVacio()) {
                        Util.showErrorAlert("Rellena todos los campos");
                        vista.clienteTabla.clearSelection();
                    } else {
                        modelo.modificarCliente(vista.txtDni.getText(), vista.txtNombre.getText(),
                                vista.txtApellidos.getText(),
                                vista.txtEmail.getText(),
                                vista.txtTelefono.getText(),
                                Integer.parseInt((String) vista.clienteTabla.getValueAt(vista.clienteTabla.getSelectedRow(), 0)));
                        refrescarCliente();
                    }
                } catch (NumberFormatException nfe) {
                    Util.showErrorAlert("Introduce números en los campos que lo requieren");
                    vista.clienteTabla.clearSelection();
                }
                borrarCamposCliente();
                break;
            case "btnClienteEliminar":
                modelo.borrarCliente(Integer.parseInt((String) vista.clienteTabla.getValueAt(vista.clienteTabla.getSelectedRow(), 0)));
                borrarCamposCliente();
                refrescarCliente();
                break;
            case "addRecambio":
                addRecambio();
                break;
            case "delRecambio":
                delRecambio();
                break;
            case "addMecanico":
                addMecanico();
                break;
            case "delMecanico":
                delMecanico();
                break;
            case "Combustion":
                refrescarRecambios(1);
                break;
            case "Electrico":
                refrescarRecambios(2);
                break;
            case "Hibrido":
                refrescarRecambios(3);
                break;
        }
    }

    private String tipoCoche() {

        if (vista.combustionRadioButton.isSelected()){
            return "Combustion";
        }
        else if (vista.electricoRadioButton.isSelected()){
            return "Electrico";
        }
        else if (vista.hibridoRadioButton.isSelected()){
            return "Hibrido";
        }

        return "error";

    }

    private void refrescarCliente() {
        try {
            vista.clienteTabla.setModel(construirTableModelCliente(modelo.consultarCliente()));
            vista.comboCliente.removeAllItems();
            for(int i = 0; i < vista.dtmClientes.getRowCount(); i++) {
                vista.comboCliente.addItem(vista.dtmClientes.getValueAt(i, 0)+" - "+
                        vista.dtmClientes.getValueAt(i, 1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DefaultTableModel construirTableModelMecanico(ResultSet rs)
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
        vista.dtmMecanicos.setDataVector(data, columnNames);
        return vista.dtmMecanicos;
    }

    private void refrescarMecanico() {
        try {
            vista.mecanicoTabla.setModel(construirTableModelMecanico(modelo.consultarMecanico()));

           for (int i = 0; i < vista.cbMecanico.size();i++ ){
               vista.cbMecanico.get(i).removeAllItems();
               for(int x = 0; x < vista.dtmMecanicos.getRowCount(); x++) {
                   vista.cbMecanico.get(i).addItem(vista.dtmMecanicos.getValueAt(x, 0)+" - "+
                           vista.dtmMecanicos.getValueAt(x, 2)+", "+vista.dtmMecanicos.getValueAt(x, 1));
               }
           }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DefaultTableModel construirTableModelCliente(ResultSet rs)
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
        vista.dtmClientes.setDataVector(data, columnNames);
        return vista.dtmClientes;
    }

    /**
     * Actualiza los libros que se ven en la lista y los comboboxes
     */
    private void refrescarCoche() {
        try {
            vista.cochesTabla.setModel(construirTableModelLibros(modelo.consultarCoche()));
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
        vista.dtmCoches.setDataVector(data, columnNames);
        return vista.dtmCoches;

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

    private void borrarcamposCoche() {
        vista.comboCliente.setSelectedIndex(-1);
        vista.comboMecanico1.setSelectedIndex(-1);
        vista.comboRecambio1.setSelectedIndex(-1);
        vista.txtMatricula.setText("");
        vista.txtMarca.setText("");
        vista.fecha.setText("");
    }

    private void borrarCamposCliente() {
        vista.txtNombre.setText("");
        vista.txtDni.setText("");
        vista.txtApellidos.setText("");
        vista.txtEmail.setText("");
        vista.txtTelefono.setText("");
    }

    private void borrarCamposMecanico() {
        vista.txtNombreMecanico.setText("");
        vista.txtApellidoMecanico.setText("");
        vista.txtTelefonoMecanico.setText("");
    }

    private boolean comprobarCocheVacio() {
        return vista.txtMatricula.getText().isEmpty() ||
                vista.txtMarca.getText().isEmpty() ||
                vista.comboRecambio1.getSelectedIndex() == -1 ||
                vista.comboMecanico1.getSelectedIndex() == -1 ||
                vista.comboCliente.getSelectedIndex() == -1 ||
                vista.fecha.getText().isEmpty();
    }

    private boolean comprobarClienteVacio() {
        return vista.txtDni.getText().isEmpty() ||
                vista.txtNombre.getText().isEmpty() ||
                vista.txtApellidos.getText().isEmpty() ||
                vista.txtTelefono.getText().isEmpty() ||
                vista.txtEmail.getText().isEmpty();
    }

    private boolean comprobarMecanicoVacio() {
        return vista.txtNombreMecanico.getText().isEmpty() ||
                vista.txtApellidoMecanico.getText().isEmpty() ||
                vista.txtTelefonoMecanico.getText().isEmpty();
    }


    private void refrescarRecambios(int i) {

        switch (i){
            case 1:

                break;
        }

    }


    private void delMecanico() {

        for (int i=vista.cbMecanico.size()-1;i>=0;i--){

            if (vista.cbMecanico.get(i).isVisible() && i!=0){
                vista.cbMecanico.get(i).setVisible(false);
                vista.cbMecanico.get(i).setSelectedIndex(-1);
                vista.cbtMecanico.get(i).setVisible(false);
                mecanicos--;
                return;
            }
        }
    }

    private void addMecanico() {

        for (int i=0;i<vista.cbMecanico.size();i++){

            if (!vista.cbMecanico.get(i).isVisible() && i!=0){
                vista.cbMecanico.get(i).setVisible(true);
                vista.cbtMecanico.get(i).setVisible(true);
                mecanicos++;
                return;
            }
        }

    }

    private void delRecambio() {

        for (int i=vista.cbRecambio.size()-1;i>=0;i--){

            if (vista.cbRecambio.get(i).isVisible() && i!=0){
                vista.cbRecambio.get(i).setVisible(false);
                vista.cbRecambio.get(i).setSelectedIndex(-1);
                vista.cbtRecambio.get(i).setVisible(false);
                recambios--;
                return;
            }
        }

    }

    private void addRecambio() {

        for (int i=0;i<vista.cbRecambio.size();i++){

            if (!vista.cbRecambio.get(i).isVisible() && i!=0){
                vista.cbRecambio.get(i).setVisible(true);
                vista.cbtRecambio.get(i).setVisible(true);
                recambios++;
                return;
            }
        }

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
