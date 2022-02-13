package com.elenajif.ficherosxml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;

/**
 * Created by DAM on 08/10/2021.
 */
public class LeerFicherosXML {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento =builder.parse(new File("productos.xml"));

        NodeList productos=documento.getElementsByTagName("producto");
        for (int i=0;i<productos.getLength();i++) {
            Node producto=productos.item(i);
            Element elemento= (Element) producto;

            System.out.println(elemento.getElementsByTagName("nombre").item(0).getChildNodes().item(0).getNodeValue());
            System.out.println(elemento.getElementsByTagName("precio").item(0).getChildNodes().item(0).getNodeValue());
        }
    }
}
