/**
 * Clase de prueba Usuario, que contiene un nombre, dos apellidos y la edad
 * del usuario
 *
 * @author Teru
 */
public class Usuario {
    private String nombre;
    private String apellido1;
    private String apellido2;
    private int edad;

    /**
     * Constructor de la clase. Construye un usuario con un nombre, dos apellidos
     * y la edad
     *
     * @param nombre String que representa el nombre del usuario
     * @param apellido1 String que representa el primer apellido del usuario
     * @param apellido2 String que representa el segundo apellido del usuario
     * @param edad ins que representa la edad del usuario
     */
    public Usuario(String nombre, String apellido1, String apellido2, int edad) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.edad = edad;
    }


    /**
     * Método toString que transforma los datos de un usuario a String, para poder
     * verlos de forma sencilla.
     *
     * @return String que representa el nombre, apellidos y edad del usuario
     */
    public String toString() {
        return nombre.concat(" "). concat(apellido1). concat(apellido2). concat(" con "+edad+" años.");
    }

}