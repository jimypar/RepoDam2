package barcafeteria.base;

import org.bson.types.ObjectId;

import java.time.LocalDate;

public class Empleado {
    private ObjectId id;
    private String nombre;
    private String apellidos;
    private LocalDate nacimiento;

    public Empleado(String nombre, String apellidos, LocalDate nacimiento) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nacimiento = nacimiento;
    }

    public Empleado() {

    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(LocalDate nacimiento) {
        this.nacimiento = nacimiento;
    }

    @Override
    public String toString() {
        return nombre + " " + apellidos;
    }
}
