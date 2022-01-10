package com.danielberges.estructuradatos;

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
        //INDICA EN BASE A QUE ATRIBUTOS SE IGUALA EL OBJETO
        if(objeto == null) {
            return false;
        }
        Producto producto = (Producto) objeto;
        if(this.getNombre().equals(producto.getNombre())) {
            return true;
        }
        return false;
    }

    public int hasCode() {
        //RETORNA UN IDENTIFICADOR UNICO DEL OBJETO
        return this.getNombre().hashCode();
    }

    @Override
    public int compareTo(Object objeto) {
        Producto producto = (Producto) objeto;
        String nombreObjeto = producto.getNombre().toLowerCase();
        String nombreThis = this.getNombre().toLowerCase();
        return (nombreThis.compareTo(nombreObjeto));
    }

}
