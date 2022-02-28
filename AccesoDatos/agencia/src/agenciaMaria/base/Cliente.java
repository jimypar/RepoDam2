package agenciaMaria.base;

import org.bson.types.ObjectId;

import java.time.LocalDate;

/**
 * Creacion de la clase cliente con mongo
 */
public class Cliente {

    // Atributos
    private ObjectId id;
    private String nombre;
    private String apellidos;
    private LocalDate nacimiento;

    /**
     * Generar constructor de la clase
     * @param nombre variable que hace referencia al nombre
     * @param apellidos variable que hace referencia al apellido
     * @param nacimiento variable que hace referencia a la fecha de nacimiento
     */
    public Cliente(String nombre, String apellidos, LocalDate nacimiento) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nacimiento = nacimiento;
    }

    /**
     * Constructor vacio
     */
    public Cliente() {

    }

    // Generar getters y setters, para poder se implementados desde otra clase
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

    /**
     * Metodo toString
     * @return
     */
    @Override
    public String toString() {
        return nombre + " " + apellidos;
    }
}
