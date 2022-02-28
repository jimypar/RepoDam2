package barcafeteria.base;

import org.bson.types.ObjectId;

public class Producto {
    private ObjectId id;
    private String nombre;
    private int grados;
    private float precio;

    public Producto(String nombre, int grados, float precio) {
        this.nombre = nombre;
        this.grados = grados;
        this.precio = precio;
    }

    public Producto () {

    }

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

    public int getGrados() {
        return grados;
    }

    public void setGrados(int grados) {
        this.grados = grados;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return nombre + " - " + grados + "% - " + precio + " €";
    }
}
