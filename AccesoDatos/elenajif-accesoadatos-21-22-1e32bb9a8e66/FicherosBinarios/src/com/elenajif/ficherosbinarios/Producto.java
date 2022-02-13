package com.elenajif.ficherosbinarios;

import java.io.Serializable;

/**
 * Created by DAM on 14/10/2021.
 */

public class Producto implements Serializable{
    private static final long serialVersionUID=1L;
    private String nombre;
    private float precio;

    public Producto(String nombre, float precio) {
        this.nombre = nombre;
        this.precio = precio;
    }


    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
