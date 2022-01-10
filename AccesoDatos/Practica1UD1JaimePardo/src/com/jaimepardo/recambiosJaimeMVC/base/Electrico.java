package com.jaimepardo.recambiosJaimeMVC.base;

import com.jaimepardo.recambiosJaimeMVC.enums.RecambiosElectricos;

import java.time.LocalDate;

/**
 * Clase Electrico
 * Created by Jaime Pardo 8/11/21
 */
public class Electrico extends Coche {

    private int kiloVatios;
    private RecambiosElectricos recambio;

    public Electrico() {
        super();
    }

    /**
     * Constructor de la clase Electrico
     *
     * @param matricula matricula del vehiculo
     * @param marca marca del vehiculo
     * @param modelo modelo del vehiculo
     * @param fechaAlta fecha de alta del vehiculo
     * @param kiloVatios kilovatios del vehiculo
     * @param recambio recambio del vehiculo
     */
    public Electrico(String matricula, String marca, String modelo, LocalDate fechaAlta, int kiloVatios, RecambiosElectricos recambio) {
        super(matricula, marca, modelo, fechaAlta);
        this.kiloVatios = kiloVatios;
        this.recambio = recambio;
    }

    public int getKiloVatios() {
        return kiloVatios;
    }

    public void setKiloVatios(int kiloVatios) {
        this.kiloVatios = kiloVatios;
    }

    public RecambiosElectricos getRecambio() {
        return recambio;
    }

    public void setRecambio(RecambiosElectricos recambio) {
        this.recambio = recambio;
    }

    /**
     * @return String de los datos del coche
     */
    @Override
    public String toString() {
        return " Electrico ["
                +" Matricula: " + getMatricula()
                +"| Marca: " + getMarca()
                +"| Modelo: " + getModelo()
                +"| kW: " + getKiloVatios()
                +"| Recambio: " + getRecambio().toString()+
                ']';
    }
}
