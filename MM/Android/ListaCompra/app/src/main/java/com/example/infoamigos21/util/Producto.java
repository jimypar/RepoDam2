package com.example.infoamigos21.util;

import android.content.Intent;
import android.graphics.Bitmap;

public class Producto {

    private long id;



    private String nombreProducto;
    private float precio;
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

    public float getPrecio() {return precio;}

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Intent putExtraAmigo(Intent i){
        i.putExtra("NOMBRE", this.getNombreProducto());
        i.putExtra("PRECIO",this.getPrecio());
        return i;
    }
}
