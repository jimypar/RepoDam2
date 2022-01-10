package com.example.util_idades.suscripciones.util;

import android.content.Intent;
import android.graphics.Bitmap;

import java.time.LocalDateTime;

public class Suscripcion {

    private long id;

    private String nombreSuscripcion;
    private LocalDateTime caducidad;
    private Bitmap foto;


    public Suscripcion() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreSuscripcion() {
        return nombreSuscripcion;
    }

    public void setNombreSuscripcion(String nombreSuscripcion) {
        this.nombreSuscripcion = nombreSuscripcion;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public LocalDateTime getCaducidad() {return caducidad;}

    public void setCaducidad(LocalDateTime caducidad) { this.caducidad = caducidad;}

}
