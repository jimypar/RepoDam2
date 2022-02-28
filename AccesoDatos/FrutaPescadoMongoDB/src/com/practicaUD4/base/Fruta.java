package com.practicaUD4.base;

import org.bson.types.ObjectId;

import java.time.LocalDate;
/**
 * @author
 * Clase Fruta
 */
public class Fruta {
    //Campos
    private ObjectId id;
    private String nombre;
    private String marca;
    private double pesoNeto;
    private LocalDate fechaCaducidad;
    /**
     * Constructor Fruta() vacio
     */
    public Fruta() {

    }
    /**
     * Get getId de ObjectId
     * @return id
     */
    public ObjectId getId() {
        return id;
    }
    /**
     * Set setId()
     * @param id de ObjectId
     */
    public void setId(ObjectId id) {
        this.id = id;
    }
    /**
     * Get getNombre() de tipo String
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Set setNombre()
     * @param nombre de tipo String
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Get getPesoNeto() de tipo double
     * @return pesoNeto
     */
    public double getPesoNeto() {
        return pesoNeto;
    }
    /**
     * Set setPesoNeto()
     * @param pesoNeto de tipo double
     */
    public void setPesoNeto(double pesoNeto) {
        this.pesoNeto = pesoNeto;
    }
    /**
     * Get getFechaCaducidad() de LocalDate
     * @return fechaCaducidad
     */
    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }
    /**
     * Set setFechaCaducidad()
     * @param fechaCaducidad de tipo LocalDate
     */
    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }
    /**
     * Get getMarca() de tipo String
     * @return marca
     */
    public String getMarca() {
        return marca;
    }
    /**
     * Set setMarca()
     * @param marca de tipo String
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }
    /**
     * Método toString() de tipo String
     * @return nombre
     * @return marca
     */
    @Override
    public String toString() {
        return nombre + ": " + marca ;
    }
}
