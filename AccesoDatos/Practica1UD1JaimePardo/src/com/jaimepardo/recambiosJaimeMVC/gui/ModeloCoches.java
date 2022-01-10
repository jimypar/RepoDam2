package com.jaimepardo.recambiosJaimeMVC.gui;

import com.jaimepardo.recambiosJaimeMVC.base.Combustion;
import com.jaimepardo.recambiosJaimeMVC.base.Electrico;
import com.jaimepardo.recambiosJaimeMVC.base.Coche;

import com.jaimepardo.recambiosJaimeMVC.base.Hibrido;
import com.jaimepardo.recambiosJaimeMVC.enums.RecambiosCombustion;
import com.jaimepardo.recambiosJaimeMVC.enums.RecambiosElectricos;
import com.jaimepardo.recambiosJaimeMVC.enums.RecambiosHibridos;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Clase de Modelo
 * Created by Jaime Pardo 8/11/21
 */
public class ModeloCoches {
    private ArrayList<Coche> listaCoches;

    /**
     * Constructor de la clase
     * Inicializa la lista de coches
     */
    public ModeloCoches() {
        listaCoches = new ArrayList<Coche>();
    }

    /**
     * Getter de la lista de coches
     *
     * @return Array de coches
     */
    public ArrayList<Coche> getListaCoches() {
        return listaCoches;
    }


    /**
     * Crear objeto coche de combustion con datos
     * y añadirlo al ArrayList
     *
     * @param matricula matricula del vehiculo
     * @param marca marca del vehiculo
     * @param modelo modelo del vehiculo
     * @param fechaAlta fecha de alta del vehiculo
     * @param cv caballos del vehiculo
     * @param recambio recambio del vehiculo
     */
    public void altaCombustion(String matricula, String marca, String modelo,
                               LocalDate fechaAlta, int cv, RecambiosCombustion recambio) {
        Combustion nuevoCombustion = new Combustion(matricula, marca, modelo,
                fechaAlta, cv, recambio);
        listaCoches.add(nuevoCombustion);
    }

    /**
     * Crear objeto coche electrico con datos
     * y añadirlo al ArrayList
     *
     * @param matricula matricula del vehiculo
     * @param marca marca del vehiculo
     * @param modelo modelo del vehiculo
     * @param fechaAlta fecha de alta del vehiculo
     * @param kw kilovatios del vehiculo
     * @param recambio recambio del vehiculo
     */
    public void altaElectrico(String matricula, String marca, String modelo,
                              LocalDate fechaAlta, int kw, RecambiosElectricos recambio) {
        Electrico nuevoElectrico = new Electrico(matricula, marca, modelo,
                fechaAlta, kw, recambio);
        listaCoches.add(nuevoElectrico);
    }

    /**
     * Crear objeto coche hibrido con datos
     * y añadirlo al ArrayList
     *
     * @param matricula matricula del vehiculo
     * @param marca marca del vehiculo
     * @param modelo modelo del vehiculo
     * @param fechaAlta fecha de alta del vehiculo
     * @param cv caballos del vehiculo
     * @param kw kilovatios del vehiculo
     * @param recambio recambio del vehiculo
     */
    public void altaHibrido(String matricula, String marca, String modelo,
                              LocalDate fechaAlta, int cv, int kw, RecambiosHibridos recambio) {
        Hibrido nuevoHibrido = new Hibrido(matricula, marca, modelo,
                fechaAlta, cv, kw, recambio);
        listaCoches.add(nuevoHibrido);
    }


    /**
     * Localiza coche por matricula y lo elimina del arrayList
     *
     * @param coche Coche que deseas borrar
     */
    public void bajaCoche(Coche coche){
        for (int i = 0; i<listaCoches.size();i++)
            if (listaCoches.get(i).getMatricula().equals(coche.getMatricula())){
                listaCoches.remove(listaCoches.get(i));
            }
        }


    /**
     * Comprueba si la matricula pasada existe ya
     *
     * @param matricula Matricula para comprobar
     * @return true si la matricula ya existe
     */
    public boolean existeMatricula(String matricula) {
        for (Coche unCoche : listaCoches) {
            if (unCoche.getMatricula().equals(matricula)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Exporta los datos a un fichero XML
     *
     * @param fichero fichero seleccionado para guardar el xml
     * @throws ParserConfigurationException
     * @throws TransformerException
     */
    public void exportarXML(File fichero) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document documento = dom.createDocument(null, "xml", null);

        //nodo raiz - la primera etiqueta que contiene las demas
        Element raiz = documento.createElement("Coches");
        documento.getDocumentElement().appendChild(raiz);

        Element nodoCoches = null;
        Element nodoDatos = null;
        Text texto = null;

        for (Coche unCoche : listaCoches) {
            //añado dentro de la etiqueta raiz Coches el tipo de coche
            if (unCoche instanceof Combustion) {
                nodoCoches = documento.createElement("Combustion");
            } else if (unCoche instanceof Electrico){
                nodoCoches = documento.createElement("Electrico");
            } else if (unCoche instanceof Hibrido){
                nodoCoches = documento.createElement("Hibrido");
            }
            raiz.appendChild(nodoCoches);

            //dentro de la etiqueta coche las etiquetas inferiores

            nodoDatos=documento.createElement("Matricula");
            nodoCoches.appendChild(nodoDatos);
            texto=documento.createTextNode(unCoche.getMatricula());
            nodoDatos.appendChild(texto);

            nodoDatos=documento.createElement("Marca");
            nodoCoches.appendChild(nodoDatos);
            texto=documento.createTextNode(unCoche.getMarca());
            nodoDatos.appendChild(texto);

            nodoDatos=documento.createElement("Modelo");
            nodoCoches.appendChild(nodoDatos);
            texto=documento.createTextNode(unCoche.getModelo());
            nodoDatos.appendChild(texto);

            nodoDatos=documento.createElement("Fecha-Alta");
            nodoCoches.appendChild(nodoDatos);
            texto=documento.createTextNode(unCoche.getFechaAlta().toString());
            nodoDatos.appendChild(texto);



            //como hay un dato que depende del tipo de coche
            //tengo que controlar el tipo de objeto
            if (unCoche instanceof Combustion) {
                nodoDatos=documento.createElement("Caballos");
                nodoCoches.appendChild(nodoDatos);
                texto=documento.createTextNode(String.valueOf(((Combustion) unCoche).getCaballos()));
                nodoDatos.appendChild(texto);

                nodoDatos=documento.createElement("Recambio");
                nodoCoches.appendChild(nodoDatos);
                texto=documento.createTextNode(String.valueOf(((Combustion) unCoche).getRecambio().toString()));
                nodoDatos.appendChild(texto);
            } else if (unCoche instanceof Electrico){
                nodoDatos=documento.createElement("KiloVatios");
                nodoCoches.appendChild(nodoDatos);
                texto=documento.createTextNode(String.valueOf(((Electrico) unCoche).getKiloVatios()));
                nodoDatos.appendChild(texto);

                nodoDatos=documento.createElement("Recambio");
                nodoCoches.appendChild(nodoDatos);
                texto=documento.createTextNode(String.valueOf(((Electrico) unCoche).getRecambio().toString()));
                nodoDatos.appendChild(texto);
            } else if (unCoche instanceof Hibrido){
                nodoDatos=documento.createElement("Caballos");
                nodoCoches.appendChild(nodoDatos);
                texto=documento.createTextNode(String.valueOf(((Hibrido) unCoche).getCaballos()));
                nodoDatos.appendChild(texto);

                nodoDatos=documento.createElement("KiloVatios");
                nodoCoches.appendChild(nodoDatos);
                texto=documento.createTextNode(String.valueOf(((Hibrido) unCoche).getKiloVatios()));
                nodoDatos.appendChild(texto);

                nodoDatos=documento.createElement("Recambio");
                nodoCoches.appendChild(nodoDatos);
                texto=documento.createTextNode(String.valueOf(((Hibrido) unCoche).getRecambio().toString()));
                nodoDatos.appendChild(texto);
            }

            //guarda los datos en fichero
            Source source = new DOMSource(documento);
            Result resultado=new StreamResult(fichero);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source,resultado);

        }
    }

    /**
     * Importa los datos de un archivo XML a un ArrayList
     *
     * @param fichero Fichero que contiene los datos en XML
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public void importarXML(File fichero) throws ParserConfigurationException, IOException, SAXException {
        listaCoches =new ArrayList<Coche>();
        Combustion nuevoCombustion =null;
        Electrico nuevoElectrico =null;
        Hibrido nuevoHibrido =null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder=factory.newDocumentBuilder();
        Document documento=builder.parse(fichero);

        NodeList listaElementos = documento.getElementsByTagName("*");

        for (int i=0;i<listaElementos.getLength();i++) {
            Element nodoCoche = (Element) listaElementos.item(i);

            if (nodoCoche.getTagName().equals("Combustion")) {
                nuevoCombustion =new Combustion();
                nuevoCombustion.setMatricula(nodoCoche.getChildNodes().item(0).getTextContent());
                nuevoCombustion.setMarca(nodoCoche.getChildNodes().item(1).getTextContent());
                nuevoCombustion.setModelo(nodoCoche.getChildNodes().item(2).getTextContent());
                nuevoCombustion.setFechaAlta(LocalDate.parse(nodoCoche.getChildNodes().item(3).getTextContent()));
                nuevoCombustion.setCaballos(Integer.parseInt(nodoCoche.getChildNodes().item(4).getTextContent()));
                nuevoCombustion.setRecambio(FormatearEnumCombustion(nodoCoche.getChildNodes().item(5).getTextContent()));
                listaCoches.add(nuevoCombustion);
            }else if (nodoCoche.getTagName().equals("Electrico")) {
                nuevoElectrico =new Electrico();
                nuevoElectrico.setMatricula(nodoCoche.getChildNodes().item(0).getTextContent());
                nuevoElectrico.setMarca(nodoCoche.getChildNodes().item(1).getTextContent());
                nuevoElectrico.setModelo(nodoCoche.getChildNodes().item(2).getTextContent());
                nuevoElectrico.setFechaAlta(LocalDate.parse(nodoCoche.getChildNodes().item(3).getTextContent()));
                nuevoElectrico.setKiloVatios(Integer.parseInt(nodoCoche.getChildNodes().item(4).getTextContent()));
                nuevoElectrico.setRecambio(FormatearEnumElectrico(nodoCoche.getChildNodes().item(5).getTextContent()));
                listaCoches.add(nuevoElectrico);
            }else if (nodoCoche.getTagName().equals("Hibrido")) {
                nuevoHibrido =new Hibrido();
                nuevoHibrido.setMatricula(nodoCoche.getChildNodes().item(0).getTextContent());
                nuevoHibrido.setMarca(nodoCoche.getChildNodes().item(1).getTextContent());
                nuevoHibrido.setModelo(nodoCoche.getChildNodes().item(2).getTextContent());
                nuevoHibrido.setFechaAlta(LocalDate.parse(nodoCoche.getChildNodes().item(3).getTextContent()));
                nuevoHibrido.setCaballos(Integer.parseInt(nodoCoche.getChildNodes().item(4).getTextContent()));
                nuevoHibrido.setKiloVatios(Integer.parseInt(nodoCoche.getChildNodes().item(5).getTextContent()));
                nuevoHibrido.setRecambio(FormatearEnumHibrido(nodoCoche.getChildNodes().item(6).getTextContent()));
                listaCoches.add(nuevoHibrido);
            }


        }

    }


    /**
     * Transforma el texto de XML a una opcion del enum de combustion
     *
     * @param textContent String del valor del enumerable
     * @return devuelve el enumerable de ese valor
     */
    private RecambiosCombustion FormatearEnumCombustion(String textContent) {

        for (RecambiosCombustion recambio: RecambiosCombustion.values()) {
            if (recambio.toString().equals(textContent)) {
                return recambio;
            }
        }
        return RecambiosCombustion.Otro;
    }

    /**
     * Transforma el texto de XML a una opcion del enum de electrico
     *
     * @param textContent String del valor del enumerable
     * @return devuelve el enumerable de ese valor
     */
    private RecambiosElectricos FormatearEnumElectrico(String textContent) {

        for (RecambiosElectricos recambio: RecambiosElectricos.values()) {
            if (recambio.toString().equals(textContent)) {
                return recambio;
            }
        }
        return RecambiosElectricos.Otro;
    }

    /**
     * Transforma el texto de XML a una opcion del enum de hibrido
     *
     * @param textContent String del valor del enumerable
     * @return devuelve el enumerable de ese valor
     */
    private RecambiosHibridos FormatearEnumHibrido(String textContent) {

        for (RecambiosHibridos recambio: RecambiosHibridos.values()) {
            if (recambio.toString().equals(textContent)) {
                return recambio;
            }
        }
        return RecambiosHibridos.Otro;

    }

}
