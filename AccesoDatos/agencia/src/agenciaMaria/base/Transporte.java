package agenciaMaria.base;

import org.bson.types.ObjectId;

/**
 * Clase llamada transporte con mongo
 */
public class Transporte {

    //Atributos
    private ObjectId id;
    private String transporte;

    /**
     * Creacion del constructor
     * @param transporte variable llamada asi
     */
    public Transporte(String transporte) {
        this.transporte = transporte;
    }

    /**
     * Creacion de un constructor vacio
     */
    public Transporte() {

    }

    // Generar getters y setters, para poder se implementados desde otra clase
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String gettransporte() {
        return transporte;
    }

    public void settransporte(String transporte) {
        this.transporte = transporte;
    }

    /**
     * metodo ToString
     * @return
     */
    @Override
    public String toString() {
        return transporte;
    }
}