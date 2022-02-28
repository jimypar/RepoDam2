package agenciaMaria.gui;

import agenciaMaria.base.Transporte;
import agenciaMaria.base.Cliente;
import agenciaMaria.base.Vuelo;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que crea la vista del modelo
 */
public class Modelo {

    // atributos
    private MongoClient clienteM;
    private MongoCollection<Document> clientes;
    private MongoCollection<Document> vuelos;
    private MongoCollection<Document> transportes;

    /**
     * Metodo que conecta con mongo
     */
    public void conectar() {
        clienteM = new MongoClient();
        String DATABASE = "AgenciaMaria";
        MongoDatabase db = clienteM.getDatabase(DATABASE);

        String COLECCION_CLIENTES = "clientes";
        clientes = db.getCollection(COLECCION_CLIENTES);
        String COLECCION_VUELOS = "vuelos";
        vuelos = db.getCollection(COLECCION_VUELOS);
        String COLECCION_TRANSPORTES = "transportes";
        transportes = db.getCollection(COLECCION_TRANSPORTES);
    }

    /**
     * Metodo que desconecta con mongo
     */
    public void desconectar() {
        clienteM.close();
        clienteM = null;
    }

    // Creacion de getter de mongo
    public MongoClient getCliente() {
        return clienteM;
    }

    /**
     * Metodo que lista los vuelos
     * @return
     */
    public ArrayList<Vuelo> getVuelo() {
        ArrayList<Vuelo> lista = new ArrayList<>();

        for (Document document : vuelos.find()) {
            lista.add(documentToVuelo(document));
        }
        return lista;
    }

    /**
     * Metodo que lista los vuelos los compara
     * @param comparador variable llamada asi
     * @return
     */
    public ArrayList<Vuelo> getVuelo(String comparador) {
        ArrayList<Vuelo> lista = new ArrayList<>();
        Document query = new Document();
        List<Document> listaCriterios = new ArrayList<>();

        listaCriterios.add(new Document("nombre", new Document("$regex", "/*" + comparador + "/*")));
        query.append("$or", listaCriterios);

        for (Document document : vuelos.find(query)) {
            lista.add(documentToVuelo(document));
        }

        return lista;
    }

    /**
     * Metodo que lista los clientes
     * @return
     */
    public ArrayList<Cliente> getClientes() {
        ArrayList<Cliente> lista = new ArrayList<>();

        for (Document document : clientes.find()) {
            lista.add(documentToCliente(document));
        }
        return lista;
    }

    /**
     * Metodo que lista los clinetes y los compara
     * @param comparador variable llamada asi
     * @return
     */
    public ArrayList<Cliente> getClientes(String comparador) {
        ArrayList<Cliente> lista = new ArrayList<>();
        Document query = new Document();
        List<Document> listaCriterios = new ArrayList<>();

        listaCriterios.add(new Document("nombre", new Document("$regex", "/*" + comparador + "/*")));
        listaCriterios.add(new Document("apellidos", new Document("$regex", "/*" + comparador + "/*")));
        query.append("$or", listaCriterios);

        for (Document document : clientes.find(query)) {
            lista.add(documentToCliente(document));
        }

        return lista;
    }

    /**
     * Metodo que lista el transporte
     * @return
     */
    public ArrayList<Transporte> gettransportes() {
        ArrayList<Transporte> lista = new ArrayList<>();

        for (Document document : transportes.find()) {
            lista.add(documentTotransporte(document));
        }
        return lista;
    }

    /**
     * Metodo que lista y compara el transporte
     * @param comparador varibale llamada asi
     * @return
     */
    public ArrayList<Transporte> gettransportes(String comparador) {
        ArrayList<Transporte> lista = new ArrayList<>();
        Document query = new Document();
        List<Document> listaCriterios = new ArrayList<>();

        listaCriterios.add(new Document("transporte", new Document("$regex", "/*" + comparador + "/*")));
        query.append("$or", listaCriterios);

        for (Document document : transportes.find(query)) {
            lista.add(documentTotransporte(document));
        }

        return lista;
    }

    /**
     * Metodo que guarda el objeto de mongo
     * @param obj
     */
    public void guardarObjeto(Object obj) {
        if (obj instanceof Vuelo) {
            vuelos.insertOne(objectToDocument(obj));

        } else if (obj instanceof Cliente) {
            clientes.insertOne(objectToDocument(obj));

        } else if (obj instanceof Transporte) {
            transportes.insertOne(objectToDocument(obj));
        }
    }

    /**
     * Metodo que modifica el objeto de mongo
     * @param obj
     */
    public void modificarObjeto(Object obj) {
        if (obj instanceof Vuelo) {
            Vuelo vuelo = (Vuelo) obj;
            vuelos.replaceOne(new Document("_id", vuelo.getId()), objectToDocument(vuelo));

        } else if (obj instanceof Cliente) {
            Cliente cliente = (Cliente) obj;
            clientes.replaceOne(new Document("_id", cliente.getId()), objectToDocument(cliente));

        } else if (obj instanceof Transporte) {
            Transporte transporte = (Transporte) obj;
            transportes.replaceOne(new Document("_id", transporte.getId()), objectToDocument(transporte));
        }
    }

    /**
     * Metodo que elimina el objeto de mongo
     * @param obj
     */
    public void eliminarObjeto(Object obj) {
        if (obj instanceof Vuelo) {
            Vuelo vuelo = (Vuelo) obj;
            vuelos.deleteOne(objectToDocument(vuelo));

        } else if (obj instanceof Cliente) {
            Cliente cliente = (Cliente) obj;
            clientes.deleteOne(objectToDocument(cliente));

        } else if (obj instanceof Transporte) {
            Transporte transporte = (Transporte) obj;
            transportes.deleteOne(objectToDocument(transporte));
        }
    }

    /**
     * Metodo que crea un documento con vuelo
     * @param dc
     * @return
     */
    public Vuelo documentToVuelo(Document dc) {
        Vuelo vuelo = new Vuelo();

        vuelo.setId(dc.getObjectId("_id"));
        vuelo.setNombre(dc.getString("nombre"));
        vuelo.setnumPerson(dc.getInteger("numPerson"));
        vuelo.setPrecio((Float.parseFloat(String.valueOf(dc.getDouble("precio")))));
        return vuelo;
    }

    /**
     * metodo wque crea un documento con cliente
     * @param dc
     * @return
     */
    public Cliente documentToCliente(Document dc) {
        Cliente cliente = new Cliente();

        cliente.setId(dc.getObjectId("_id"));
        cliente.setNombre(dc.getString("nombre"));
        cliente.setApellidos(dc.getString("apellidos"));
        cliente.setNacimiento(LocalDate.parse(dc.getString("nacimiento")));
        return cliente;
    }

    /**
     * metodo que crea un documento con transporte
     * @param dc
     * @return
     */
    public Transporte documentTotransporte(Document dc) {
        Transporte transporte = new Transporte();

        transporte.setId(dc.getObjectId("_id"));
        transporte.settransporte(dc.getString("transporte"));
        return transporte;
    }

    /**
     * Metood que crea los objetos con los documentos de todas las clases
     * @param obj
     * @return
     */
    public Document objectToDocument(Object obj) {
        Document dc = new Document();

        if (obj instanceof Vuelo) {
            Vuelo vuelo = (Vuelo) obj;

            dc.append("nombre", vuelo.getNombre());
            dc.append("numPerson", vuelo.getnumPerson());
            dc.append("precio", vuelo.getPrecio());
        } else if (obj instanceof Cliente) {
            Cliente cliente = (Cliente) obj;

            dc.append("nombre", cliente.getNombre());
            dc.append("apellidos", cliente.getApellidos());
            dc.append("nacimiento", cliente.getNacimiento().toString());

        } else if (obj instanceof Transporte) {
            Transporte transporte = (Transporte) obj;

            dc.append("transporte", transporte.gettransporte());
        } else {
            return null;
        }
        return dc;
    }
}
