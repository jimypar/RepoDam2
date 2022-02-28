package com.practicaUD4.gui;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.practicaUD4.base.Pescado;
import com.practicaUD4.base.Fruta;
import com.practicaUD4.util.Util;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Clase Modelo
 * @author
 */
public class Modelo {

    //Campos
    private final static String COLECCION_FRUTAS = "Frutas";
    private final static String COLECCION_PESCADOS = "Pescados";
    private final static String DATABASE = "Productos";

    private MongoClient client;
    private MongoDatabase baseDatos;
    private MongoCollection coleccionFrutas;
    private MongoCollection coleccionPescados;

    /**
     * Método conectar(), conectas con la base de datos
     */
    public void conectar() {
        client = new MongoClient();
        baseDatos = client.getDatabase(DATABASE);
        coleccionFrutas = baseDatos.getCollection(COLECCION_FRUTAS);
        coleccionPescados=baseDatos.getCollection(COLECCION_PESCADOS);
    }

    /**
     * Método desconectar(), para desconectar la base de datos
     */
    public void desconectar(){
        client.close();

    }

    /**
     * Método guardarFruta(), para guardar frutas en la base de datos
     * @param unaFruta de tipo Fruta
     */
    public void guardarFruta(Fruta unaFruta) {
        coleccionFrutas.insertOne(frutaToDocument(unaFruta));

    }

    /**
     * Método guardarPescado(), para guardar pescado en la base de datos
     * @param unPescado de tipo Pescado
     */
    public void guardarPescado(Pescado unPescado) {
        coleccionPescados.insertOne(pescadoToDocument(unPescado));

    }

    /**
     * Método getFrutas(), lista frutas
     * @return lista
     */
    public List<Fruta> getFrutas(){
        ArrayList<Fruta> lista = new ArrayList<>();

        Iterator<Document> it = coleccionFrutas.find().iterator();
        while(it.hasNext()){
            lista.add(documentToFruta(it.next()));
        }
        return lista;
    }

    /**
     * Método getPescados(), lista pescados
     * @return lista
     */
    public List<Pescado> getPescados(){
        ArrayList<Pescado> lista = new ArrayList<>();

        Iterator<Document> it = coleccionPescados.find().iterator();
        while(it.hasNext()){
            lista.add(documentToPescado(it.next()));
        }
        return lista;
    }


    /**
     * Método getFrutas(),listo las frutas atendiendo a 2 criterios basados en expresiones regulares.
     * @param text de tipo String
     * @return lista
     */
    public List<Fruta> getFrutas(String text) {
        ArrayList<Fruta> lista = new ArrayList<>();

        Document query = new Document();
        List<Document> listaCriterios = new ArrayList<>();
        listaCriterios.add(new Document("nombre", new Document("$regex", "/*"+text+"/*")));
        listaCriterios.add(new Document("marca", new Document("$regex", "/*"+text+"/*")));
        query.append("$or", listaCriterios);

        Iterator<Document> iterator = coleccionFrutas.find(query).iterator();
        while(iterator.hasNext()){
            lista.add(documentToFruta(iterator.next()));
        }

        return lista;
    }
    /**
     * Método getPescado(),listo los pescados atendiendo a 2 criterios basados en expresiones regulares.
     * @param text de tipo String
     * @return lista
     */
    public List<Pescado> getPescado(String text) {
        ArrayList<Pescado> lista = new ArrayList<>();

        Document query = new Document();
        List<Document> listaCriterios = new ArrayList<>();
        listaCriterios.add(new Document("nombre", new Document("$regex", "/*"+text+"/*")));
        listaCriterios.add(new Document("marca", new Document("$regex", "/*"+text+"/*")));
        query.append("$or", listaCriterios);

        Iterator<Document> iterator = coleccionPescados.find(query).iterator();
        while(iterator.hasNext()){
            lista.add(documentToPescado(iterator.next()));
        }

        return lista;
    }

    /**
     * Método frutaToDocument() de tipo Document
     * @param unaFruta de tipo Fruta
     * @return documento
     */
    public Document frutaToDocument(Fruta unaFruta){
        Document documento = new Document();
        documento.append("nombre", unaFruta.getNombre());
        documento.append("marca", unaFruta.getMarca());
        documento.append("peso", unaFruta.getPesoNeto());
        documento.append("fechaCaducidad", Util.formatearFecha(unaFruta.getFechaCaducidad()));
        return documento;
    }
    /**
     * Método pescadoToDocument() de tipo Document
     * @param unPescado de tipo Pescado
     * @return documento
     */
    public Document pescadoToDocument(Pescado unPescado){
        Document documento = new Document();
        documento.append("nombre", unPescado.getNombre());
        documento.append("marca", unPescado.getMarca());
        documento.append("peso", unPescado.getPesoNeto());
        documento.append("fechaCaducidad", Util.formatearFecha(unPescado.getFechaCaducidad()));
        return documento;
    }

    /**
     * Método documentToFruta(), registro de las filas de fruta
     * @param document de tipo Document
     * @return unaFruta
     */
    public Fruta documentToFruta(Document document){
        Fruta unaFruta = new Fruta();
        unaFruta.setId(document.getObjectId("_id"));
        unaFruta.setNombre(document.getString("nombre"));
        unaFruta.setMarca(document.getString("marca"));
        unaFruta.setPesoNeto(document.getDouble("peso"));
        unaFruta.setFechaCaducidad(Util.parsearFecha(document.getString("fechaCaducidad")));
        return unaFruta;
    }
    /**
     * Método documentToPescado(), registro de las filas de pescado
     * @param document de tipo Document
     * @return unPescado
     */
    public Pescado documentToPescado(Document document){
        Pescado unPescado = new Pescado();
        unPescado.setId(document.getObjectId("_id"));
        unPescado.setNombre(document.getString("nombre"));
        unPescado.setMarca(document.getString("marca"));
        unPescado.setPesoNeto(document.getDouble("peso"));
        unPescado.setFechaCaducidad(Util.parsearFecha(document.getString("fechaCaducidad")));
        return unPescado;
    }

    /**
     * Método modificarFruta(), sirve para modificar frutas
     * @param unaFruta de Fruta
     */
    public void modificarFruta(Fruta unaFruta) {
        coleccionFrutas.replaceOne(new Document("_id", unaFruta.getId()), frutaToDocument(unaFruta));
    }

    /**
     * Método modificarPescado(), sirve para modificar pescados
     * @param unPescado de Pescado
     */
    public void modificarPescado(Pescado unPescado) {
        coleccionPescados.replaceOne(new Document("_id", unPescado.getId()), pescadoToDocument(unPescado));
    }

    /**
     * Método borrarFruta(), para borrar una fruta
     * @param unaFruta de Fruta
     */
    public void borrarFruta(Fruta unaFruta) {
        coleccionFrutas.deleteOne(frutaToDocument(unaFruta));
    }
    /**
     * Método borrarPescado(), para borrar una pescado
     * @param unPescado de Pescado
     */
    public void borrarPescado(Pescado unPescado) {
        coleccionPescados.deleteOne(pescadoToDocument(unPescado));

    }
}
