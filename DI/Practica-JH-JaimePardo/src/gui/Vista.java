package gui;

import com.github.lgooddatepicker.components.DatePicker;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.net.URL;

public class Vista extends JFrame {
    private final static String TITULOFRAME="Aplicación Varias Tablas";
    private JTabbedPane tabbedPane;
    private JPanel panel1;
     JPanel JPanelClientes;
     JPanel JPanelInformes;

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
    JTextField txtCiuda;
    JTextField txtCP;
    JButton btnInformeCP;
    JButton btnInformeCPbuscar;
    JButton graph1;
    JButton graph2;
    JButton graph3;

    //BUSQUEDA
    JLabel etiquetaEstado;

    //DEFAULT TABLE MODEL
    DefaultTableModel dtmAutores;

    //BARRA MENU
    JMenuItem itemAyuda;
    JMenuItem itemSalir;


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
        setMenu();
        ponLaAyuda();
    }

    private void setTableModels() {
        this.dtmAutores=new DefaultTableModel();
        this.autoresTabla.setModel(dtmAutores);
    }

    private void setMenu() {
        JMenuBar mbBar = new JMenuBar();
        JMenu menu = new JMenu("Opciones");
        //por cada item que tenga funcionalidad tiene un ActionCommand
        itemAyuda= new JMenuItem("Ayuda");
        itemAyuda.setActionCommand("Ayuda");
        itemSalir=new JMenuItem("Salir");
        itemSalir.setActionCommand("Salir");
        menu.add(itemAyuda);
        menu.add(itemSalir);
        mbBar.add(menu);
        //centrar en horizontal
        mbBar.add(Box.createHorizontalGlue());
        this.setJMenuBar(mbBar);
    }

    private void ponLaAyuda() {
        try {
            // Carga el fichero de ayuda
            File fichero = new File("src\\help\\help_set.hs");
            //File fichero = new File("src"+File.separator+"pruebajavahelp\\help\\help_set.hs");
            URL hsURL = fichero.toURI().toURL();

            // Crea el HelpSet y el HelpBroker
            HelpSet helpset = new HelpSet(getClass().getClassLoader(), hsURL);
            HelpBroker hb = helpset.createHelpBroker();

            // Pone ayuda a item de menu al pulsarlo y a F1 en ventana
            // manual e instalar.
            hb.enableHelpOnButton(itemAyuda, "aplicacion", helpset);
            //si pulsamos F1
            hb.enableHelpKey(JPanelClientes, "clientes",
                    helpset);
            hb.enableHelpKey(JPanelInformes, "informes",
                    helpset);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
