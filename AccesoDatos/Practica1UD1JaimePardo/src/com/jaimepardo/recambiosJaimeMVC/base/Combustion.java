package com.jaimepardo.recambiosJaimeMVC.base;

import com.jaimepardo.recambiosJaimeMVC.enums.RecambiosCombustion;

import java.time.LocalDate;

/**
 * Clase Combustion
 * Created by Jaime Pardo 8/11/21
 */
public class Combustion extends Coche {

    private int caballos;
    private RecambiosCombustion recambio;

    public Combustion() {
        super();
    }

    /**
     * Constructor de la clase Combustion
     *
     * @param matricula matricula del vehiculo
     * @param marca marca del vehiculo
     * @param modelo modelo del vehiculo
     * @param fechaAlta fecha de alta del vehiculo
     * @param caballos caballos del vehiculo
     * @param recambio recambio del vehiculo
     */
    public Combustion(String matricula, String marca, String modelo, LocalDate fechaAlta, int caballos, RecambiosCombustion recambio) {
        super(matricula, marca, modelo, fechaAlta);
        this.caballos = caballos;
        this.recambio = recambio;
    }

    public int getCaballos() {
        return caballos;
    }

    public void setCaballos(int caballos) {
        this.caballos = caballos;
    }

    public RecambiosCombustion getRecambio() {
        return recambio;
    }

    public void setRecambio(RecambiosCombustion recambio) {
        this.recambio = recambio;
    }

    /**
     * @return String de los datos del coche
     */
    @Override
    public String toString() {
        return " Combustion ["
                +" Matricula: " + getMatricula()
                +"| Marca: " + getMarca()
                +"| Modelo: " + getModelo()
                +"| CV: " + getCaballos()
                +"| Recambio: " + getRecambio().toString()+
                ']';
    }
}
