package gui;

import base.enums.Marcas;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Vista extends JFrame {
    private final static String TITULOFRAME="Aplicación Recambios Jaime Pardo";
    private JTabbedPane tabbedPane;
    private JPanel panel1;
    private JPanel JPanelCoche;
    private JPanel JPanelCliente;
    private JPanel JPanelMecanico;

    //Coches
    JRadioButton hibridoRadioButton;
    JRadioButton electricoRadioButton;
    JRadioButton combustionRadioButton;
    JTextField txtMatricula;
    JTextField txtMarca;
    DatePicker fecha;
    JButton addRecambio;
    JButton delRecambio;
    JButton addMecanico;
    JButton delMecanico;
    JComboBox comboCliente;
    ArrayList<JLabel> cbtRecambio;
    JLabel txtRecambio1;
    JLabel txtRecambio2;
    JLabel txtRecambio3;
    JLabel txtRecambio4;
    JLabel txtRecambio5;
    ArrayList<JComboBox> cbRecambio;
    JComboBox comboRecambio1;
    JComboBox comboRecambio2;
    JComboBox comboRecambio3;
    JComboBox comboRecambio4;
    JComboBox comboRecambio5;
    JButton btnCocheAnadir;
    JButton btnCocheModificar;
    JButton btnCocheEliminar;
    JTable cochesTabla;


    //CLIENTES au
    JTextField txtDni;
    JTextField txtEmail;
    JTextField txtNombre;
    JTextField txtApellidos;
    JTextField txtTelefono;
    JTable clienteTabla;
    JButton btnClienteEliminar;
    JButton btnClienteAnadir;
    JButton btnClienteModificar;

    //MECANICOS ed
    JTextField txtNombreMecanico;
    JTextField txtApellidoMecanico;
    JTextField txtTelefonoMecanico;
    JTable mecanicoTabla;
    JButton btnMecanicoEliminar;
    JButton btnMecanicoAnadir;
    JButton btnMecanicoModificar;
    ArrayList<JLabel> cbtMecanico;
    JLabel txtMecanico1;
    JLabel txtMecanico2;
    JLabel txtMecanico3;
    JLabel txtMecanico4;
    JLabel txtMecanico5;
    ArrayList<JComboBox> cbMecanico;
    JComboBox comboMecanico1;
    JComboBox comboMecanico2;
    JComboBox comboMecanico3;
    JComboBox comboMecanico4;
    JComboBox comboMecanico5;
    JComboBox comboMarca;


    //BUSQUEDA
    JLabel etiquetaEstado;

    //DEFAULT TABLE MODEL
    DefaultTableModel dtmCoches;
    DefaultTableModel dtmClientes;
    DefaultTableModel dtmMecanicos;

    //BARRA MENU
    JMenuItem itemOpciones;
    JMenuItem itemDesconectar;
    JMenuItem itemSalir;

    //CUADRO DIALOGO
    OptionDialog optionDialog;
    JDialog adminPasswordDialog;
    JButton btnValidate;
    JPasswordField adminPassword;

    public Vista() {
        super(TITULOFRAME);
        initFrame();
    }

    public void initFrame() {
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setSize(new Dimension(this.getWidth(),this.getHeight()));
        this.setLocationRelativeTo(this);
        //creo cuadro de dialogo
        optionDialog=new OptionDialog(this);
        setMenu();
        setAdminDialog();
        setComboBox();
        setEnumComboBox();
        setTableModels();

    }



    private void setTableModels() {
        this.dtmCoches=new DefaultTableModel();
        this.cochesTabla.setModel(dtmCoches);
        this.dtmMecanicos=new DefaultTableModel();
        this.mecanicoTabla.setModel(dtmMecanicos);
        this.dtmClientes=new DefaultTableModel();
        this.clienteTabla.setModel(dtmClientes);
    }

    private void setMenu() {
        JMenuBar mbBar = new JMenuBar();
        JMenu menu = new JMenu("Archivo");
        //por cada item que tenga funcionalidad tiene un ActionCommand
        itemOpciones= new JMenuItem("Opciones");
        itemOpciones.setActionCommand("Opciones");
        itemDesconectar= new JMenuItem("Desconectar");
        itemDesconectar.setActionCommand("Desconectar");
        itemSalir=new JMenuItem("Salir");
        itemSalir.setActionCommand("Salir");
        menu.add(itemOpciones);
        menu.add(itemDesconectar);
        menu.add(itemSalir);
        mbBar.add(menu);
        //centrar en horizontal
        mbBar.add(Box.createHorizontalGlue());
        this.setJMenuBar(mbBar);
    }

    private void setEnumComboBox() {
        for (Marcas m: Marcas.values()) {
            comboMarca.addItem(m.getValor());
        }
        comboMarca.setSelectedIndex(-1);
    }

    private void setAdminDialog() {
        btnValidate= new JButton("Validar");
        btnValidate.setActionCommand("abrirOpciones");
        adminPassword= new JPasswordField();
        adminPassword.setPreferredSize(new Dimension(100,26));
        Object[] options = new Object[] {adminPassword,btnValidate};
        JOptionPane jop =new JOptionPane("Introduce la contraseña",
                JOptionPane.WARNING_MESSAGE,JOptionPane.YES_NO_OPTION,null,options);
        adminPasswordDialog=new JDialog(this,"Opciones",true);
        adminPasswordDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        adminPasswordDialog.setContentPane(jop);
        adminPasswordDialog.pack();
        adminPasswordDialog.setLocationRelativeTo(this);
    }

    private void setComboBox() {

        cbtRecambio = new ArrayList<>();
        cbtRecambio.add(txtRecambio1);
        cbtRecambio.add(txtRecambio2);
        cbtRecambio.add(txtRecambio3);
        cbtRecambio.add(txtRecambio4);
        cbtRecambio.add(txtRecambio5);
        cbRecambio = new ArrayList<>();
        cbRecambio.add(comboRecambio1);
        cbRecambio.add(comboRecambio2);
        cbRecambio.add(comboRecambio3);
        cbRecambio.add(comboRecambio4);
        cbRecambio.add(comboRecambio5);

        cbtMecanico = new ArrayList<>();
        cbtMecanico.add(txtMecanico1);
        cbtMecanico.add(txtMecanico2);
        cbtMecanico.add(txtMecanico3);
        cbtMecanico.add(txtMecanico4);
        cbtMecanico.add(txtMecanico5);
        cbMecanico = new ArrayList<>();
        cbMecanico.add(comboMecanico1);
        cbMecanico.add(comboMecanico2);
        cbMecanico.add(comboMecanico3);
        cbMecanico.add(comboMecanico4);
        cbMecanico.add(comboMecanico5);

    }

}
