package com.elenajif.vehiculosmvc.base;

import java.time.LocalDate;

/**
 * Created by DAM on 28/10/2021.
 */
public class Moto extends Vehiculo{

    private double kms;

    public Moto() {
        super();
    }

    public Moto(String matricula, String marca, String modelo, LocalDate fechaMatriculacion, double kms) {
        super(matricula, marca, modelo, fechaMatriculacion);
        this.kms = kms;
    }

    public double getKms() {
        return kms;
    }

    public void setKms(double kms) {
        this.kms = kms;
    }

    @Override
    public String toString() {
        return "Moto{"
                +"matricula=" + getMatricula()
                +"marca=" + getMarca()
                +"modelo=" + getModelo()+
                '}';
    }
}
