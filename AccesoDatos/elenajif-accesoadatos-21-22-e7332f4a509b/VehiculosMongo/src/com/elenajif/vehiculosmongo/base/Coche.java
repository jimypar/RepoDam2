package com.elenajif.vehiculosmongo.base;

import org.bson.types.ObjectId;

import java.time.LocalDate;

/**
 * Created by DAM on 18/02/2022.
 */
public class Coche {
    //atributos
    //Comenzamos con atributo id de la clase ObjectId
    //id de de tipo org.bson.ObjectId
    private ObjectId id;
    private String marca;
    private String modelo;
    private String matricula;
    private LocalDate fechaMatriculacion;

    public Coche() {

    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDate getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    public void setFechaMatriculacion(LocalDate fechamMatriculacion) {
        this.fechaMatriculacion = fechamMatriculacion;
    }


    @Override
    public String toString() {
        return matricula +" : "+marca +" : "+modelo+" : "+fechaMatriculacion;
    }


}
