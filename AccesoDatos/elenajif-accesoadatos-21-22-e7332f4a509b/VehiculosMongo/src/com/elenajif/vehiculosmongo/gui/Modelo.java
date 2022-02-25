package com.elenajif.vehiculosmongo.gui;

import com.elenajif.vehiculosmongo.base.Coche;
import com.elenajif.vehiculosmongo.util.Util;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by DAM on 18/02/2022.
 */
public class Modelo {
    private final static String COLECCION_COCHES = "Coches";
    private final static String DATABASE="Vehiculos";

    private MongoClient client;
    private MongoDatabase baseDatos;
    private MongoCollection coleccionCoches;

    public void conectar() {
        client=new MongoClient();
        baseDatos=client.getDatabase(DATABASE);
        coleccionCoches=baseDatos.getCollection(COLECCION_COCHES);
    }

    public void desconectar() {
        client.close();
    }

    public void guardarCoche(Coche unCoche) {
        coleccionCoches.insertOne(cocheToDocument(unCoche));

    }

    public void modificarCoche(Coche unCoche) {
        coleccionCoches.replaceOne(new Document("_id",unCoche.getId()),
                cocheToDocument(unCoche));
    }

    public void borrarCoche(Coche unCoche) {
        coleccionCoches.deleteOne(cocheToDocument(unCoche));
    }


    public List<Coche> getCoches() {
        ArrayList<Coche> lista = new ArrayList<>();
        Iterator<Document> it = coleccionCoches.find().iterator();
        while (it.hasNext()) {
            lista.add(documentToCoche(it.next()));
        }
        return lista;
    }

    public List<Coche> getCoches(String text) {
        ArrayList<Coche> lista = new ArrayList<>();
        Document query = new Document();
        List<Document> listaCriterios = new ArrayList<>();
        listaCriterios.add(new Document("matricula",new Document("$regex","/*"+text+"/*")));
        listaCriterios.add(new Document("marca",new Document("$regex","/*"+text+"/*")));
        query.append("$or",listaCriterios);
        Iterator<Document> iterator =coleccionCoches.find(query).iterator();
        while (iterator.hasNext()) {
            lista.add(documentToCoche(iterator.next()));
        }
        return lista;
    }


    //En mongo tenemos Document
    //En java tenemos objetos (Coches)
    public Document cocheToDocument(Coche unCoche) {
        Document documento = new Document();
        documento.append("marca",unCoche.getMarca());
        documento.append("modelo",unCoche.getModelo());
        documento.append("matricula",unCoche.getMatricula());
        documento.append("fechaMatriculacion", Util.formatearFecha(unCoche.getFechaMatriculacion()));
        return documento;
    }

    public Coche documentToCoche(Document document) {
        Coche unCoche = new Coche();
        unCoche.setId(document.getObjectId("_id"));
        unCoche.setMarca(document.getString("marca"));
        unCoche.setModelo(document.getString("modelo"));
        unCoche.setMatricula(document.getString("matricula"));
        unCoche.setFechaMatriculacion(Util.parsearFecha(document.getString("fechaMatriculacion")));
        return unCoche;
    }


}
