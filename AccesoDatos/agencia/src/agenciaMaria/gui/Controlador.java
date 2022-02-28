package agenciaMaria.gui;

import agenciaMaria.base.Transporte;
import agenciaMaria.base.Cliente;
import agenciaMaria.base.Vuelo;
import agenciaMaria.util.Util;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Clase que implementa metodos y se crea la vista y el modelo
 */
public class Controlador implements ActionListener, KeyListener, ListSelectionListener {
    //Atributos
    private Modelo modelo;
    private Vista vista;

    /**
     * Creacion del constructor
     * @param modelo variable de la clase modelo
     * @param vista variable de la clase vista
     */
    public Controlador(Modelo modelo, Vista vista) {
        this.vista = vista;
        this.modelo = modelo;

        addActionListeners(this);
        addKeyListeners(this);
        addListSelectionListeners(this);

        try {
            modelo.conectar();
            vista.itemConectar.setText("Desconectar");
            vista.setTitle("Agencia Maria - <CONECTADO>");
            setBotonesActivados(true);
            listarclientes();
            listarvuelos();
            listartransportes();
        } catch (Exception ex) {
            Util.mostrarMensajeError("Imposible establecer conexión con el servidor.");
        }
    }

    /**
     * Metodo que implementa los listeners
     * @param listener variable llamada asi
     */
    private void addActionListeners(ActionListener listener){
        vista.btnAddVuelo.addActionListener(listener);
        vista.btnModVuelo.addActionListener(listener);
        vista.btnDelVuelo.addActionListener(listener);
        vista.btnAddCliente.addActionListener(listener);
        vista.btnModCliente.addActionListener(listener);
        vista.btnDelCliente.addActionListener(listener);
        vista.btnAddTransporte.addActionListener(listener);
        vista.btnModTransporte.addActionListener(listener);
        vista.btnDelTransporte.addActionListener(listener);

        vista.itemConectar.addActionListener(listener);
        vista.itemSalir.addActionListener(listener);
    }

    /**
     * metodo que llama a las listas
     * @param listener variable llamada asi
     */
    private void addListSelectionListeners(ListSelectionListener listener){
        vista.listVuelo.addListSelectionListener(listener);
        vista.listCliente.addListSelectionListener(listener);
        vista.listtransportes.addListSelectionListener(listener);
    }

    /**
     * Metodo que llama a los txt de buscar
     * @param listener variable llamada asi
     */
    private void addKeyListeners(KeyListener listener){
        vista.txtBuscarVuelo.addKeyListener(listener);
        vista.txtBuscarCliente.addKeyListener(listener);
        vista.txtBuscarTransporte.addKeyListener(listener);
    }

    /**
     * Metodo que hace que funcione todos los botones y conecte con mongo
     * @param e variable llamada asi
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "conexion":
                try {
                    if (modelo.getCliente() == null) {
                        modelo.conectar();
                        vista.itemConectar.setText("Desconectar");
                        vista.setTitle("Agencia Maria - <CONECTADO>");
                        setBotonesActivados(true);
                        listarclientes();
                        listarvuelos();
                        listartransportes();
                    } else {
                        modelo.desconectar();
                        vista.itemConectar.setText("Conectar");
                        vista.setTitle("Agencia Maria - <SIN CONEXION>");
                        setBotonesActivados(false);
                        vista.dlmclientes.clear();
                        vista.dlmvuelos.clear();
                        vista.dlmtransportes.clear();
                        limpiarCamposVuelo();
                        limpiarCamposCliente();
                        limpiarCampostransporte();
                    }
                } catch (Exception ex) {
                    Util.mostrarMensajeError("Imposible establecer conexión con el servidor.");
                }
                break;

            case "salir":
                modelo.desconectar();
                System.exit(0);
                break;

            case "addVuelo":
                if (comprobarCamposVuelo()) {
                    modelo.guardarObjeto(new Vuelo(vista.txtNombreVuelo.getText(),
                            Integer.parseInt(vista.txtNumeroPersonas.getText()),
                            Float.parseFloat(vista.txtPrecioVuelo.getText())));
                    limpiarCamposVuelo();
                } else {
                    Util.mostrarMensajeError("No ha sido posible insertar el producto en la base de datos.\n" +
                            "Compruebe que los campos contengan el tipo de dato requerido.");
                }
                listarvuelos();
                break;

            case "modVuelo":
                if (vista.listVuelo.getSelectedValue() != null) {
                    if (comprobarCamposVuelo()) {
                        Vuelo vuelo = vista.listVuelo.getSelectedValue();
                        vuelo.setNombre(vista.txtNombreVuelo.getText());
                        vuelo.setnumPerson(Integer.parseInt(vista.txtNumeroPersonas.getText()));
                        vuelo.setPrecio(Float.parseFloat(vista.txtPrecioVuelo.getText()));
                        modelo.modificarObjeto(vuelo);
                        limpiarCamposVuelo();
                    } else {
                        Util.mostrarMensajeError("No ha sido posible modificar el producto en la base de datos.\n" +
                                "Compruebe que los campos contengan el tipo de dato requerido.");
                    }
                    listarvuelos();
                } else {
                    Util.mostrarMensajeError("No hay ningún elemento seleccionado.");
                }
                break;

            case "delVuelo":
                if (vista.listVuelo.getSelectedValue() != null) {
                    modelo.eliminarObjeto(vista.listVuelo.getSelectedValue());
                    listarvuelos();
                    limpiarCamposVuelo();
                } else {
                    Util.mostrarMensajeError("No hay ningún elemento seleccionado.");
                }
                break;

            case "addCliente":
                if (comprobarCamposCliente()) {
                    modelo.guardarObjeto(new Cliente(vista.txtNombreCliente.getText(),
                            vista.txtApellidosCliente.getText(),
                            vista.dateNacimientoCliente.getDate()));
                    limpiarCamposCliente();
                } else {
                    Util.mostrarMensajeError("No ha sido posible insertar el empleado en la base de datos.\n" +
                            "Compruebe que los campos contengan el tipo de dato requerido.");
                }
                listarclientes();
                break;

            case "modCliente":
                if (vista.listCliente.getSelectedValue() != null) {
                    if (comprobarCamposCliente()) {
                        Cliente cliente = vista.listCliente.getSelectedValue();
                        cliente.setNombre(vista.txtNombreCliente.getText());
                        cliente.setApellidos(vista.txtApellidosCliente.getText());
                        cliente.setNacimiento(vista.dateNacimientoCliente.getDate());
                        modelo.modificarObjeto(cliente);
                        limpiarCamposCliente();
                    } else {
                        Util.mostrarMensajeError("No ha sido posible modificar el empleado en la base de datos.\n" +
                                "Compruebe que los campos contengan el tipo de dato requerido.");
                    }
                    listarclientes();
                } else {
                    Util.mostrarMensajeError("No hay ningún elemento seleccionado.");
                }
                break;

            case "delCliente":
                if (vista.listCliente.getSelectedValue() != null) {
                    modelo.eliminarObjeto(vista.listCliente.getSelectedValue());
                    listarclientes();
                    limpiarCamposCliente();
                } else {
                    Util.mostrarMensajeError("No hay ningún elemento seleccionado.");
                }
                break;

            case "addtransporte":
                if (comprobarCampostransporte()) {
                    modelo.guardarObjeto(new Transporte(vista.txtTransporte.getText()));
                    limpiarCampostransporte();
                } else {
                    Util.mostrarMensajeError("No ha sido posible insertar el transporte en la base de datos.\n" +
                            "Compruebe que los campos contengan el tipo de dato requerido.");
                }
                listartransportes();
                break;

            case "modtransporte":
                if (vista.listtransportes.getSelectedValue() != null) {
                    if (comprobarCampostransporte()) {
                        Transporte transporte = vista.listtransportes.getSelectedValue();
                        transporte.settransporte(vista.txtTransporte.getText());
                        modelo.modificarObjeto(transporte);
                        limpiarCampostransporte();
                    } else {
                        Util.mostrarMensajeError("No ha sido posible modificar el transporte en la base de datos.\n" +
                                "Compruebe que los campos contengan el tipo de dato requerido.");
                    }
                    listartransportes();
                } else {
                    Util.mostrarMensajeError("No hay ningún elemento seleccionado.");
                }
                break;

            case "deltransporte":
                if (vista.listtransportes.getSelectedValue() != null) {
                    modelo.eliminarObjeto(vista.listtransportes.getSelectedValue());
                    listartransportes();
                    limpiarCampostransporte();
                    break;
                } else {
                    Util.mostrarMensajeError("No hay ningún elemento seleccionado.");
                }
        }
    }

    /**
     * Metodo que lista las listas y mira si es igual que lo que hay introducido
     * @param e variable llamada asi
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == vista.txtBuscarVuelo) {
            listarvuelosBusqueda(modelo.getVuelo(vista.txtBuscarVuelo.getText()));
            if (vista.txtBuscarVuelo.getText().isEmpty()) {
                vista.dlmvuelosBusqueda.clear();
            }
        } else if (e.getSource() == vista.txtBuscarCliente) {
            listarclientesBusqueda(modelo.getClientes(vista.txtBuscarCliente.getText()));
            if (vista.txtBuscarCliente.getText().isEmpty()) {
                vista.dlmclientesBusqueda.clear();
            }
        } else if (e.getSource() == vista.txtBuscarTransporte) {
            listartransportesBusqueda(modelo.gettransportes(vista.txtBuscarTransporte.getText()));
            if (vista.txtBuscarTransporte.getText().isEmpty()) {
                vista.dlmtransportesBusqueda.clear();
            }
        }
    }

    /**
     * Comprueba los cambios
     * @param e variable llamada asi
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == vista.listVuelo) {
            if (vista.listVuelo.getSelectedValue() != null) {
                Vuelo vuelo = vista.listVuelo.getSelectedValue();
                vista.txtNombreVuelo.setText(vuelo.getNombre());
                vista.txtNumeroPersonas.setText(String.valueOf(vuelo.getnumPerson()));
                vista.txtPrecioVuelo.setText(String.valueOf(vuelo.getPrecio()));
            }
        } else if (e.getSource() == vista.listCliente) {
            if (vista.listCliente.getSelectedValue() != null) {
                Cliente cliente = vista.listCliente.getSelectedValue();
                vista.txtNombreCliente.setText(cliente.getNombre());
                vista.txtApellidosCliente.setText(cliente.getApellidos());
                vista.dateNacimientoCliente.setDate(cliente.getNacimiento());
            }
        } else if (e.getSource() == vista.listtransportes) {
            if (vista.listtransportes.getSelectedValue() != null) {
                Transporte transporte = vista.listtransportes.getSelectedValue();
                vista.txtTransporte.setText(transporte.gettransporte());
            }
        }
    }

    /**
     * Mira si los campos de la clase vuelo estan vacios
     * @return
     */
    private boolean comprobarCamposVuelo() {
        return !vista.txtNombreVuelo.getText().isEmpty() &&
                !vista.txtNumeroPersonas.getText().isEmpty() &&
                !vista.txtPrecioVuelo.getText().isEmpty() &&
                comprobarInt(vista.txtNumeroPersonas.getText()) &&
                comprobarFloat(vista.txtPrecioVuelo.getText());
    }

    /**
     * Mira si los campos de la clase cliente estan vacios
     * @return
     */
    private boolean comprobarCamposCliente() {
        return !vista.txtNombreCliente.getText().isEmpty() &&
                !vista.txtApellidosCliente.getText().isEmpty() &&
                !vista.dateNacimientoCliente.getText().isEmpty();
    }

    /**
     * Mira si los campos de la clase transporte estan vacios
     * @return
     */
    private boolean comprobarCampostransporte() {
        return !vista.txtTransporte.getText().isEmpty();
    }

    /**
     * Limpia los campos de vuelo
     */
    private void limpiarCamposVuelo() {
        vista.txtNombreVuelo.setText("");
        vista.txtNumeroPersonas.setText("");
        vista.txtPrecioVuelo.setText("");
        vista.txtBuscarVuelo.setText("");
    }

    /**
     * Limpia los campos de cliente
     */
    private void limpiarCamposCliente() {
        vista.txtNombreCliente.setText("");
        vista.txtApellidosCliente.setText("");
        vista.dateNacimientoCliente.clear();
        vista.txtBuscarCliente.setText("");
    }

    /**
     * Limpia los campos de transporte
     */
    private void limpiarCampostransporte() {
        vista.txtTransporte.setText("");
        vista.txtBuscarTransporte.setText("");
    }

    /**
     * metodo que comprueba si es un campo int (numerico)
     * @param txt variable llamada asi
     * @return
     */
    private boolean comprobarInt(String txt) {
        try {
            Integer.parseInt(txt);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * Metodo que comprueba si es un campo float (numerico decimal)
     * @param txt variable llamada asi
     * @return
     */
    private boolean comprobarFloat(String txt) {
        try {
            Float.parseFloat(txt);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * Metodo que lista los clientes
     */
    private void listarclientes() {
        vista.dlmclientes.clear();
        for (Cliente cliente : modelo.getClientes()) {
            vista.dlmclientes.addElement(cliente);
        }
    }

    /**
     * Metodo que lista los vuelos
     */
    private void listarvuelos() {
        vista.dlmvuelos.clear();
        for (Vuelo vuelo : modelo.getVuelo()) {
            vista.dlmvuelos.addElement(vuelo);
        }
    }

    /**
     * Metodo que lista los transportes
     */
    private void listartransportes() {
        vista.dlmtransportes.clear();
        for (Transporte transporte : modelo.gettransportes()) {
            vista.dlmtransportes.addElement(transporte);
        }
    }

    /**
     *  Metodo qye lista la clase cliente y busca si hay alguno con ese nombre
     * @param lista
     */
    private void listarclientesBusqueda(ArrayList<Cliente> lista) {
        vista.dlmclientesBusqueda.clear();
        for (Cliente cliente : lista) {
            vista.dlmclientesBusqueda.addElement(cliente);
        }
    }

    /**
     * Metodo qye lista la clase vuelo y busca si hay alguno con ese nombre
     * @param lista
     */
    private void listarvuelosBusqueda(ArrayList<Vuelo> lista) {
        vista.dlmvuelosBusqueda.clear();
        for (Vuelo vuelo : lista) {
            vista.dlmvuelosBusqueda.addElement(vuelo);
        }
    }

    /**
     * Metodo qye lista la clase transporte y busca si hay alguno con ese nombre
     * @param lista
     */
    private void listartransportesBusqueda(ArrayList<Transporte> lista) {
        vista.dlmtransportesBusqueda.clear();
        for (Transporte transporte : lista) {
            vista.dlmtransportesBusqueda.addElement(transporte);
        }
    }

    /**
     * Activa los botones cuando se conecta con mongo
     * @param activados variable llamada asi
     */
    private void setBotonesActivados(boolean activados) {
        vista.btnAddVuelo.setEnabled(activados);
        vista.btnModVuelo.setEnabled(activados);
        vista.btnDelVuelo.setEnabled(activados);
        vista.btnAddCliente.setEnabled(activados);
        vista.btnModCliente.setEnabled(activados);
        vista.btnDelCliente.setEnabled(activados);
        vista.btnAddTransporte.setEnabled(activados);
        vista.btnModTransporte.setEnabled(activados);
        vista.btnDelTransporte.setEnabled(activados);
    }

    // Métodos innecesarios, pero si que tenemos que implementar para que funcione
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {}
}
