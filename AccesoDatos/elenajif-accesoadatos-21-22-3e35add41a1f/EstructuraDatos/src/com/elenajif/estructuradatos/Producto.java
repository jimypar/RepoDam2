package com.elenajif.estructuradatos;

/**
 * Created by DAM on 15/10/2021.
 */
public class Producto implements Comparable {

    private String nombre;
    private int cantidad;

    public Producto(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }

    public boolean equals(Object objeto) {
        //indica en base a que atributos se iguala el objeto
        if (objeto == null) {
            return false;
        }
        Producto producto = (Producto) objeto;
        if (this.getNombre().equals(producto.getNombre())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        //retorna un identificador unico del objeto
        return this.getNombre().hashCode();
    }

    @Override
    public int compareTo(Object objeto) {
        //indica en base a que atributos se compara el objeto
        // devuelve +1 si this es > que objeto
        // devuelve -1 si this es < que objeto
        // devuelve 0 si son iguales
        Producto producto = (Producto) objeto;
        String nombreObjeto = producto.getNombre().toLowerCase();
        String nombreThis = this.getNombre().toLowerCase();
        return (nombreThis.compareTo(nombreObjeto));
    }
}
