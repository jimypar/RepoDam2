package com.example.infoamigos.util;

import android.content.Intent;
import android.graphics.Bitmap;

public class Amigo {

    private long id;

    private String nombreApellidos;
    private String email;
    private String tlf;
    private String tlfMovil;
    private Bitmap foto;
    private float deudas;

    public long getId() {
        return id;
    }

    public String getNombreApellidos() {
        return nombreApellidos;
    }

    public String getEmail() {
        return email;
    }

    public String getTlf() {
        return tlf;
    }

    public String getTlfMovil() {
        return tlfMovil;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public float getDeudas() {
        return deudas;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNombreApellidos(String nombreApellidos) {
        this.nombreApellidos = nombreApellidos;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public void setTlfMovil(String tlfMovil) {
        this.tlfMovil = tlfMovil;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public void setDeudas(float deudas) {
        this.deudas = deudas;
    }

    public Intent putExtraAmigo(Intent i){
        i.putExtra("NOMBRE",this.getNombreApellidos());
        i.putExtra("MAIL",this.getEmail());
        i.putExtra("FIJO",this.getTlf());
        i.putExtra("MOVIL",this.getTlfMovil());
        i.putExtra("DEUDA",this.getDeudas());
        return i;
    }
}
