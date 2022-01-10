package com.jaimepardo.recambiosJaimeMVC.base;

import java.time.LocalDate;


/**
 *  Clase padre de Coches
 *
 */
public abstract class Coche {
    private String matricula;
    private String marca;
    private String modelo;
    private LocalDate fechaAlta;

    public Coche() {

    }

    /**
     * Constructor de la clase Coche
     *
     * @param matricula matricula del vehiculo
     * @param marca marca del vehiculo
     * @param modelo modelo del vehiculo
     * @param fechaAlta fecha de alta del vehiculo
     */
    public Coche(String matricula, String marca, String modelo, LocalDate fechaAlta) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.fechaAlta = fechaAlta;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
}
