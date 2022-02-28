package agenciaMaria.base;

import org.bson.types.ObjectId;

/**
 * Clase llamada vuelo con mongo
 */
public class Vuelo {

    //Atributos
    private ObjectId id;
    private String nombre;
    private int numPerson;
    private float precio;

    /**
     * Creacion del constructor
     * @param nombre variable que hace referencia al nombre
     * @param numPerson variable que hace referencia al numero de personas que contratan ese vuelo
     * @param precio variable que hace referencia al precio
     */
    public Vuelo(String nombre, int numPerson, float precio) {
        this.nombre = nombre;
        this.numPerson = numPerson;
        this.precio = precio;
    }

    /**
     * Constructor vacio
     */
    public Vuelo() {

    }

    //Getter y setters, para poder se implementados desde otra clase
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getnumPerson() {
        return numPerson;
    }

    public void setnumPerson(int numPerson) {
        this.numPerson = numPerson;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    /**
     * Metodo toString
     * @return
     */
    @Override
    public String toString() {
        return nombre + " - " + numPerson + "- " + precio + " €";
    }
}
