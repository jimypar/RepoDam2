package com.example.infoamigos21.util;

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

    public Amigo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreApellidos() {
        return nombreApellidos;
    }

    public void setNombreApellidos(String nombreApellidos) {
        this.nombreApellidos = nombreApellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public String getTlfMovil() {
        return tlfMovil;
    }

    public void setTlfMovil(String tlfMovil) {
        this.tlfMovil = tlfMovil;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public float getDeudas() {
        return deudas;
    }

    public void setDeudas(float deudas) {
        this.deudas = deudas;
    }

    public Intent putExtraAmigo(Intent i){
        i.putExtra("NOMBRE", this.getNombreApellidos());
        i.putExtra("MAIL",this.getEmail());
        i.putExtra("FIJO",this.getTlf());
        i.putExtra("MOVIL", this.getTlfMovil());
        i.putExtra("DEUDA",this.getDeudas());
        return i;
    }
}
