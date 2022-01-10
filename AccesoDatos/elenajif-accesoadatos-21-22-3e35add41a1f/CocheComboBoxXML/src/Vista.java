import com.elenajif.cochecomboboxxml.base.Coche;

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

/**
 * Created by DAM on 21/10/2021.
 */
public class Vista {
    //creo yo un JFrame
    private JFrame frame;
    //creado al poner elementos gráficos
    private JPanel panel1;
    private JTextField marcaTxt;
    private JTextField modeloTxt;
    private JComboBox comboBox;
    private JLabel lblCoche;
    private JButton altaCocheBtn;
    private JButton mostrarCocheBtn;

    //elementos añadidos por mi
    private LinkedList<Coche> lista;
    private DefaultComboBoxModel<Coche> dcbm;

    //generar un main y cambiarlo a constructor
    public Vista() {
        frame = new JFrame("Vista");
        //quitar new Vista(). de setContentPane
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //llamo a crear menu
        crearMenu();

        //centrar
        frame.setLocationRelativeTo(null);

        //inicializo lista LinkedList
        lista = new LinkedList<>();
        //inicializo comboxModel
        dcbm = new DefaultComboBoxModel<>();
        //aplico modelo al comboBox
        comboBox.setModel(dcbm);

        //boton alta coche (listener)
        altaCocheBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //dar de alta coche
                altaCoche(marcaTxt.getText(), modeloTxt.getText());
                //refrescar cambios
                refrescarComboBox();
            }
        });

        //boton mostrar coche (listener)
        mostrarCocheBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //muestro el coche seleccionado en la label o campo de texto
                Coche seleccionado = (Coche) dcbm.getSelectedItem();
                lblCoche.setText(seleccionado.toString());
            }
        });

        //listener comboBox
        comboBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    lista.remove(dcbm.getSelectedItem());
                    refrescarComboBox();
                }
            }
        });
    }

    private void altaCoche(String marca, String modelo) {
        lista.add(new Coche(marca, modelo));
    }

    private void refrescarComboBox() {
        dcbm.removeAllElements();
        for (Coche coche : lista) {
            dcbm.addElement(coche);
        }
    }

    //menu para importar y exportar XML
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

                if (opcionSeleccionada == JFileChooser.APPROVE_OPTION) {
                    File fichero=selectorArchivo.getSelectedFile();
                    exportarXML(fichero);
                }
            }
        });

        //listener exportarXML
        itemImportarXML.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser selectorArchivo = new JFileChooser();
                int opcionSeleccionada=selectorArchivo.showOpenDialog(null);

                File fichero=selectorArchivo.getSelectedFile();
                importarXML(fichero);
                refrescarComboBox();
            }
        });

        //para mostrarlo en mi aplicación grafica
        menu.add(itemExportarXML);
        menu.add(itemImportarXML);
        barra.add(menu);
        frame.setJMenuBar(barra);
    }

    private void exportarXML(File fichero) {
        //cuadro de dialogo Save
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder =null;

        try {
            builder=factory.newDocumentBuilder();
            DOMImplementation dom=builder.getDOMImplementation();

            //creo documento que represente un XML
            Document documento =
                    dom.createDocument(null,"xml",null);

            //creo el nodo raiz coches y lo añado al documento
            Element raiz = documento.createElement("coches");
            documento.getDocumentElement().appendChild(raiz);

            Element nodoCoche;
            Element nodoDatos;
            Text dato;

            //por cada coche de la lista, creo un nodo coche
            for (Coche coche: lista) {
                //creo un nodo coche y lo añado al nodo razi (coches)
                nodoCoche=documento.createElement("coche");
                raiz.appendChild(nodoCoche);

                //a cada coche le añado los nodos marca y modelo
                //nodo marca
                nodoDatos=documento.createElement("marca");
                nodoCoche.appendChild(nodoDatos);

                //a cada nodo le añado el dato
                //datos marca
                dato=documento.createTextNode(coche.getMarca());
                nodoDatos.appendChild(dato);

                //a cada coche le añado los nodos marca y modelo
                //nodo modelo
                nodoDatos=documento.createElement("modelo");
                nodoCoche.appendChild(nodoDatos);

                //a cada nodo le añado el dato
                //datos modelo
                dato=documento.createTextNode(coche.getModelo());
                nodoDatos.appendChild(dato);
            }

            //transformo el documento anterio a un fichero de texto plano
            Source src=new DOMSource(documento);
            Result result = new StreamResult(fichero);

            Transformer transformer=null;
            transformer= TransformerFactory.newInstance().newTransformer();
            transformer.transform(src, result);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private void importarXML(File fichero) {
        //cuadro de dialogo Open
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento =builder.parse(fichero);

            //recorro cada uno de los nodos coche para obtener sus campos
            NodeList coches = documento.getElementsByTagName("coche");
            for (int i=0;i<coches.getLength();i++) {
                Node coche = coches.item(i);
                Element elemento = (Element) coche;

                //obtengo los campos marca y modelo
                String marca=elemento.getElementsByTagName("marca").item(0)
                                .getChildNodes().item(0).getNodeValue();
                String modelo=elemento.getElementsByTagName("modelo").item(0)
                        .getChildNodes().item(0).getNodeValue();

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

    //main llamando al constructor
    public static void main(String[] args) {
        Vista vista = new Vista();
    }


}
