import com.jaimepardo.cochecomboboxxml.base.Coche;
import com.sun.java.browser.plugin2.DOM;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class Vista {
    //creado por mi
    private JFrame frame;
    //creado graficamente
    private JPanel panel1;
    private JTextField marcaTxt;
    private JTextField modeloTxt;
    private JComboBox comboBox;
    private JButton altaCocheBtn;
    private JButton mostrarCocheBtn;
    private JLabel lblCoche;

    //elementos creados por mi
    private LinkedList<Coche> lista;
    private DefaultComboBoxModel<Coche> dcbm;

    //generar un main y cambiarlo a constructor
    public Vista() {
        frame = new JFrame("Vista");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        crearMenu();

        //centrar
        frame.setLocationRelativeTo(null);

        //inicializar lista LinkedList
        lista=new LinkedList<>();
        //inicializar comboBox
        dcbm= new DefaultComboBoxModel<>();
        //aplicar a modelo combobox
        comboBox.setModel(dcbm);


        //Listener boton alta coche
        altaCocheBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //dar de alta el coche y refrescar
                altaCoche(marcaTxt.getText(),modeloTxt.getText());
                refrescarComboBox();
            }
        });
        //Listener boton mostar coches
        mostrarCocheBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coche seleccionado = (Coche) dcbm.getSelectedItem();
                lblCoche.setText(seleccionado.toString());

            }
        });

        //listener comboBox
        comboBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode()==KeyEvent.VK_DELETE){
                    lista.remove(dcbm.getSelectedItem());
                    refrescarComboBox();
                }
            }
        });
    }

    // Menu para importar y exportar XML
    private void crearMenu() {
        JMenuBar barra = new JMenuBar();
        JMenu menu = new JMenu("Archivo");
        JMenuItem itemExportarXML = new JMenuItem("Exportar XML");
        JMenuItem itemImportarXML = new JMenuItem("Importar XML");

        //listener importarXML
        itemExportarXML.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser selectorArchivo = new JFileChooser();
                int opcionSeleccionada = selectorArchivo.showSaveDialog(null);

                if (opcionSeleccionada==JFileChooser.APPROVE_OPTION){
                    File fichero = selectorArchivo.getSelectedFile();
                    exportarXML(fichero);
                }
            }
        });

        itemImportarXML.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser selectorArchivo = new JFileChooser();
                int opcionSeleccionada = selectorArchivo.showOpenDialog(null);

                File fichero = selectorArchivo.getSelectedFile();
                importarXML(fichero);
                refrescarComboBox();
            }
        });

        //Añadir a la frame
        menu.add(itemExportarXML);
        menu.add(itemImportarXML);
        barra.add(menu);
        frame.setJMenuBar(barra);
    }

    private void altaCoche(String marca, String modelo){
        lista.add(new Coche(marca,modelo));
    }

    private void refrescarComboBox(){
        dcbm.removeAllElements();
        for (Coche coche:lista){
            dcbm.addElement(coche);
        }
    }

    private void exportarXML(File fichero){
        //cuadro de dialogo SAVE
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            DOMImplementation dom = builder.getDOMImplementation();

            Document document = dom.createDocument(null,"xml",null);

            Element raiz = document.createElement("coches");
            document.getDocumentElement().appendChild(raiz);

            Element nodoCoche;
            Element nodoDatos;
            Text dato;

            for (Coche coche: lista) {

                nodoCoche = document.createElement("coche");
                raiz.appendChild(nodoCoche);

                nodoDatos = document.createElement("marca");
                nodoCoche.appendChild(nodoDatos);

                dato = document.createTextNode(coche.getMarca());
                nodoDatos.appendChild(dato);

                nodoDatos= document.createElement("modelo");
                nodoCoche.appendChild(nodoDatos);

                dato = document.createTextNode(coche.getModelo());
                nodoDatos.appendChild(dato);

            }

            Source src = new DOMSource(document);
            Result rs = new StreamResult(fichero);

            Transformer transformer = null;
            transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(src,rs);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

    private void importarXML(File fichero){
        //cuadro de dialogo OPEN

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(fichero);

            //Recorrer cada nodo coche
            NodeList coches = document.getElementsByTagName("coche");
            for (int i = 0; i<coches.getLength();i++){
                Node coche = coches.item(i);
                Element elemento = (Element) coche;
                //Obtengo los campos marca y modelo
                String marca = elemento.getElementsByTagName("marca").item(0).getChildNodes().item(0).getNodeName();
                String modelo = elemento.getElementsByTagName("modelo").item(0).getChildNodes().item(0).getNodeName();
                altaCoche(marca,modelo);
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Main llamando al constructor
    public static void main(String[] args) {
        Vista vista = new Vista();
    }
}
