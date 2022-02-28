package barcafeteria.gui;

import barcafeteria.base.Departamento;
import barcafeteria.base.Empleado;
import barcafeteria.base.Producto;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;

public class Vista extends JFrame {
    private JPanel panelPrincipal;

    // Productos
    JTextField txtNombreProducto;
    JTextField txtGradosProducto;
    JTextField txtPrecioProducto;

    JList<Producto> listProductos;

    JButton btnAddProducto;
    JButton btnModProducto;
    JButton btnDelProducto;

    JTextField txtBuscarProducto;
    JList<Producto> listBusquedaProducto;

    // Empleados
    JTextField txtNombreEmpleado;
    JTextField txtApellidosEmpleado;
    DatePicker dateNacimientoEmpleado;

    JList<Empleado> listEmpleados;

    JButton btnAddEmpleado;
    JButton btnModEmpleado;
    JButton btnDelEmpleado;

    JTextField txtBuscarEmpleado;
    JList<Empleado> listBusquedaEmpleado;

    // Departamentos
    JTextField txtDepartamento;

    JList<Departamento> listDepartamentos;

    JButton btnAddDepartamento;
    JButton btnModDepartamento;
    JButton btnDelDepartamento;

    JTextField txtBuscarDepartamento;
    JList<Departamento> listBusquedaDepartamento;

    // Modelos
    DefaultListModel<Producto> dlmProductos;
    DefaultListModel<Empleado> dlmEmpleados;
    DefaultListModel<Departamento> dlmDepartamentos;
    DefaultListModel<Producto> dlmProductosBusqueda;
    DefaultListModel<Empleado> dlmEmpleadosBusqueda;
    DefaultListModel<Departamento> dlmDepartamentosBusqueda;

    // Menu
    JMenuItem itemConectar;
    JMenuItem itemSalir;

    public Vista() {
        setTitle("Bar Manolo - <SIN CONEXION>");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 650));
        setResizable(false);
        pack();
        setVisible(true);

        inicializarModelos();
        inicializarMenu();
    }

    private void inicializarModelos() {
        dlmProductos = new DefaultListModel<>();
        listProductos.setModel(dlmProductos);
        dlmEmpleados = new DefaultListModel<>();
        listEmpleados.setModel(dlmEmpleados);
        dlmDepartamentos = new DefaultListModel<>();
        listDepartamentos.setModel(dlmDepartamentos);
        dlmProductosBusqueda = new DefaultListModel<>();
        listBusquedaProducto.setModel(dlmProductosBusqueda);
        dlmEmpleadosBusqueda = new DefaultListModel<>();
        listBusquedaEmpleado.setModel(dlmEmpleadosBusqueda);
        dlmDepartamentosBusqueda = new DefaultListModel<>();
        listBusquedaDepartamento.setModel(dlmDepartamentosBusqueda);
    }

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
