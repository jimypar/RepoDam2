package gui;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Vista extends JFrame {
    private final static String TITULOFRAME="Aplicación Varias Tablas";
    private JTabbedPane tabbedPane;
    private JPanel panel1;
    private JPanel JPanelAutor;

    //AUTORES
    JTextField txtNombre;
    JTextField txtApellidos;
    DatePicker fechaNacimiento;
    JTextField txtDireccion;
    JTable autoresTabla;
    JButton anadirCliente;
    JTextField txtCiudad;
    JTextField txtTelefono;
    JTextField txtDni;
    JButton btnInforme;
    JButton btnInformeOrdenado;
    JButton btnInformeCiudad;
    JButton btnInformeBuscar;
    private JPanel JPanelInformes;
    JTextField txtCiuda;

    //BUSQUEDA
    JLabel etiquetaEstado;

    //DEFAULT TABLE MODEL
    DefaultTableModel dtmAutores;


    public Vista() {
        super(TITULOFRAME);
        initFrame();
    }

    public void initFrame() {
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setSize(new Dimension(this.getWidth()+200,this.getHeight()+100));
        this.setLocationRelativeTo(this);
        setTableModels();
    }

    private void setTableModels() {
        this.dtmAutores=new DefaultTableModel();
        this.autoresTabla.setModel(dtmAutores);
    }


}
