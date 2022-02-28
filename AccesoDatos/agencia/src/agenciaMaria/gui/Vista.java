package agenciaMaria.gui;

import agenciaMaria.base.Transporte;
import agenciaMaria.base.Cliente;
import agenciaMaria.base.Vuelo;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;

/**
 * Clase donde se crea todo lo necesario para la vista
 */
public class Vista extends JFrame {
    private JPanel panelPrincipal;

    // vuelos
    JTextField txtNombreVuelo;
    JTextField txtNumeroPersonas;
    JTextField txtPrecioVuelo;

    JList<Vuelo> listVuelo;

    JButton btnAddVuelo;
    JButton btnModVuelo;
    JButton btnDelVuelo;

    JTextField txtBuscarVuelo;
    JList<Vuelo> listBusquedaVuelo;

    // cliente
    JTextField txtNombreCliente;
    JTextField txtApellidosCliente;
    DatePicker dateNacimientoCliente;

    JButton btnAddCliente;
    JButton btnModCliente;
    JButton btnDelCliente;

    JTextField txtBuscarCliente;
    JList<Cliente> listBusquedaCliente;

    JList<Cliente> listCliente;

    // transportes
    JTextField txtTransporte;

    JList<Transporte> listtransportes;

    JButton btnAddTransporte;
    JButton btnModTransporte;
    JButton btnDelTransporte;

    JTextField txtBuscarTransporte;
    JList<Transporte> listBusquedaTransporte;


    // Modelos
    DefaultListModel<Vuelo> dlmvuelos;
    DefaultListModel<Cliente> dlmclientes;
    DefaultListModel<Transporte> dlmtransportes;
    DefaultListModel<Vuelo> dlmvuelosBusqueda;
    DefaultListModel<Cliente> dlmclientesBusqueda;
    DefaultListModel<Transporte> dlmtransportesBusqueda;

    // Menu
    JMenuItem itemConectar;
    JMenuItem itemSalir;

    /**
     * Creacion del constructor
     */
    public Vista() {
        setTitle("Agencia Maria - <SIN CONEXION>");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 650));
        setResizable(false);
        pack();
        setVisible(true);

        inicializarModelos();
        inicializarMenu();
    }

    /**
     * Metodo que inicializa los modelos y las listas
     */
    private void inicializarModelos() {
        dlmvuelos = new DefaultListModel<>();
        listVuelo.setModel(dlmvuelos);

        dlmclientes = new DefaultListModel<>();
        listCliente.setModel(dlmclientes);

        dlmtransportes = new DefaultListModel<>();
        listtransportes.setModel(dlmtransportes);

        dlmvuelosBusqueda = new DefaultListModel<>();
        listBusquedaVuelo.setModel(dlmvuelosBusqueda);

        dlmclientesBusqueda = new DefaultListModel<>();
        listBusquedaCliente.setModel(dlmclientesBusqueda);

        dlmtransportesBusqueda = new DefaultListModel<>();
        listBusquedaTransporte.setModel(dlmtransportesBusqueda);
    }

    /**
     * Metodo que inicializa el menu
     */
    private void inicializarMenu() {
        itemConectar = new JMenuItem("Conectar");
        itemConectar.setActionCommand("conexion");
        itemSalir = new JMenuItem("Salir");
        itemSalir.setActionCommand("salir");

        JMenu menuArchivo = new JMenu("Archivo");
        menuArchivo.add(itemConectar);
        menuArchivo.add(itemSalir);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuArchivo);

        setJMenuBar(menuBar);
    }
}
