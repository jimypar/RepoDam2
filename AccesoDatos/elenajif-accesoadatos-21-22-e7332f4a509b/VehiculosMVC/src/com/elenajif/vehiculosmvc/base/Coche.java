package com.elenajif.vehiculosmvc.base;

import java.time.LocalDate;

/**
 * Created by DAM on 28/10/2021.
 */
public class Coche extends Vehiculo{
    private int numPlazas;

    public Coche() {
        super();
    }

    public Coche(String matricula, String marca, String modelo, LocalDate fechaMatriculacion, int numPlazas) {
        super(matricula, marca, modelo, fechaMatriculacion);
        this.numPlazas = numPlazas;
    }

    public int getNumPlazas() {
        return numPlazas;
    }

    public void setNumPlazas(int numPlazas) {
        this.numPlazas = numPlazas;
    }

    @Override
    public String toString() {
        return "Coche{"
                +"matricula=" + getMatricula()
                +"marca=" + getMarca()
                +"modelo=" + getModelo() +
                '}';
    }
}
