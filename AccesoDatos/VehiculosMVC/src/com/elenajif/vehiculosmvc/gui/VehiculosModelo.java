package com.elenajif.vehiculosmvc.gui;

import com.elenajif.vehiculosmvc.base.Coche;
import com.elenajif.vehiculosmvc.base.Moto;
import com.elenajif.vehiculosmvc.base.Vehiculo;
import com.sun.java.browser.plugin2.DOM;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by DAM on 28/10/2021.
 */
public class VehiculosModelo {
    private ArrayList<Vehiculo>listaVehiculos;

    public VehiculosModelo(){
        listaVehiculos = new ArrayList<>();
    }

    public ArrayList<Vehiculo>obtenerVehiculos(){
        return listaVehiculos;
    }

    public void altaCoche(String matricula, String marca, String modelo, LocalDate fechaMatriculacion, int numPlazas){
        Coche nuevoCoche = new Coche(matricula,marca,modelo,fechaMatriculacion,numPlazas);
        listaVehiculos.add(nuevoCoche);
    }

    public void altaMoto(String matricula, String marca, String modelo, LocalDate fechaMatriculacion, double kms){
        Moto nuevoMoto = new Moto(matricula,marca,modelo,fechaMatriculacion,kms);
        listaVehiculos.add(nuevoMoto);
    }

    public boolean existeMatricula(String matricula){
        for (Vehiculo unVehiculo:listaVehiculos){
            if (unVehiculo.getMatricula().equals(matricula)){
                return true;
            }
        }
        return true;
    }

    public void exportarXML(File fichero) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document document = dom.createDocument(null,"xml",null);

        Element raiz = document.createElement("Vehiculos");
        document.getDocumentElement().appendChild(raiz);
        Element nodoVehiculo = null;
        Element nodoDatos = null;
        Text texto = null;

        for (Vehiculo unVehiculo:listaVehiculos){
            if (unVehiculo instanceof Coche){
                nodoVehiculo=document.createElement("Moto");
            }
            raiz.appendChild(nodoVehiculo);

            nodoDatos=document.createElement("matricula");
            nodoVehiculo.appendChild(nodoDatos);
            texto=document.createTextNode(unVehiculo.getMatricula());
            nodoDatos.appendChild(texto);

            nodoDatos=document.createElement("modelo");
            nodoVehiculo.appendChild(nodoDatos);
            texto=document.createTextNode(unVehiculo.getModelo());
            nodoDatos.appendChild(texto);

            nodoDatos=document.createElement("marca");
            nodoVehiculo.appendChild(nodoDatos);
            texto=document.createTextNode(unVehiculo.getMarca());
            nodoDatos.appendChild(texto);

            nodoDatos=document.createElement("fecha-matriculacion");
            nodoVehiculo.appendChild(nodoDatos);
            texto=document.createTextNode(unVehiculo.getFechaMatriculacion().toString());
            nodoDatos.appendChild(texto);

            if (unVehiculo instanceof Coche){
                nodoDatos=document.createElement("numero-plazas");
                nodoVehiculo.appendChild(nodoDatos);
                texto=document.createTextNode(String.valueOf(((Coche) unVehiculo).getNumPlazas()));
                nodoDatos.appendChild(texto);
            } else {
                nodoDatos=document.createElement("kms");
                nodoVehiculo.appendChild(nodoDatos);
                texto=document.createTextNode(String.valueOf(((Moto) unVehiculo).getKms()));
                nodoDatos.appendChild(texto);
            }

        }

        Source source = new DOMSource(document);
        Result result = new StreamResult(fichero);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source,result);

    }

    public void importarXML(File fichero) throws ParserConfigurationException, IOException, SAXException {
        listaVehiculos = new ArrayList<Vehiculo>();
        Coche nuevoCoche=null;
        Moto nuevoMoto=null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(fichero);

        NodeList listaElementos = document.getElementsByTagName("*");

        for (int i = 0;i<listaElementos.getLength();i++){

            Element nodoVehiculo = (Element) listaElementos.item(i);

            if (nodoVehiculo.getTagName().equals("Coche")){
                nuevoCoche = new Coche();
                nuevoCoche.setMatricula(nodoVehiculo.getChildNodes().item(0).getTextContent());
                nuevoCoche.setMarca(nodoVehiculo.getChildNodes().item(1).getTextContent());
                nuevoCoche.setModelo(nodoVehiculo.getChildNodes().item(2).getTextContent());
                nuevoCoche.setFechaMatriculacion(LocalDate.parse(nodoVehiculo.getChildNodes().item(3).getTextContent()));
                nuevoCoche.setNumPlazas(Integer.parseInt(nodoVehiculo.getChildNodes().item(4).getTextContent()));
                listaVehiculos.add(nuevoCoche);
            }else {
                if (nodoVehiculo.getTagName().equals("Moto")){
                    nuevoMoto = new Moto();
                    nuevoMoto.setMatricula(nodoVehiculo.getChildNodes().item(0).getTextContent());
                    nuevoMoto.setMarca(nodoVehiculo.getChildNodes().item(1).getTextContent());
                    nuevoMoto.setModelo(nodoVehiculo.getChildNodes().item(2).getTextContent());
                    nuevoMoto.setFechaMatriculacion(LocalDate.parse(nodoVehiculo.getChildNodes().item(3).getTextContent()));
                    nuevoMoto.setKms(Double.parseDouble(nodoVehiculo.getChildNodes().item(4).getTextContent()));
                    listaVehiculos.add(nuevoMoto);
                }
            }

        }
    }

}
