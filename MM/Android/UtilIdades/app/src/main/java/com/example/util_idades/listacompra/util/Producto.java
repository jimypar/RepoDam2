package com.example.util_idades.listacompra.util;

import android.graphics.Bitmap;

public class Producto {

    private long id;



    private String nombreProducto;
    private int cantidad;
    private int comprado=0;
    private Bitmap foto;


    public Producto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public int getCantidad() {return cantidad;}

    public void setCantidad(int cantidad) { this.cantidad = cantidad;}

    public int getComprado() {
        return comprado;
    }

    public void setComprado(int comprado) {
        this.comprado = comprado;
    }
}
