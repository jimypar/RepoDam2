package com.jaimepardo.recambiosJaimeMVC.base;

import com.jaimepardo.recambiosJaimeMVC.enums.RecambiosHibridos;

import java.time.LocalDate;

/**
 * Clase Hibrido
 * Created by Jaime Pardo 8/11/21
 */
public class Hibrido extends Coche {

    private int caballos;
    private int kiloVatios;
    private RecambiosHibridos recambio;

    public Hibrido() {
        super();
    }

    /**
     * Constructor de la clase Hibrido
     *
     * @param matricula matricula del vehiculo
     * @param marca marca del vehiculo
     * @param modelo modelo del vehiculo
     * @param fechaAlta fecha de alta del vehiculo
     * @param caballos caballos del vehiculo
     * @param kiloVatios kilovatios del vehiculo
     * @param recambio recambio del vehiculo
     */
    public Hibrido(String matricula, String marca, String modelo, LocalDate fechaAlta, int caballos, int kiloVatios, RecambiosHibridos recambio) {
        super(matricula, marca, modelo, fechaAlta);
        this.caballos = caballos;
        this.kiloVatios = kiloVatios;
        this.recambio = recambio;
    }

    public int getCaballos() {
        return caballos;
    }

    public void setCaballos(int caballos) {
        this.caballos = caballos;
    }

    public int getKiloVatios() {
        return kiloVatios;
    }

    public void setKiloVatios(int kiloVatios) {
        this.kiloVatios = kiloVatios;
    }

    public RecambiosHibridos getRecambio() {
        return recambio;
    }

    public void setRecambio(RecambiosHibridos recambio) {
        this.recambio = recambio;
    }

    /**
     * @return String de los datos del coche
     */
    @Override
    public String toString() {
        return " Hibrido ["
                +" Matricula: " + getMatricula()
                +"| Marca: " + getMarca()
                +"| Modelo: " + getModelo()
                +"| CV: " + getCaballos()
                +"| kW: " + getKiloVatios()
                +"| Recambio: " + getRecambio().toString()+
                ']';

    }
}
