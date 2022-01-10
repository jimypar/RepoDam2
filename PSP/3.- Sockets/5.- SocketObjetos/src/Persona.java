import java.io.Serializable;

/**
 * Clase persona, que implementa Serializable para poser ser enviada
 * por socket. Contendrá un nombre, dos apellidos y una edad
 *
 * @author Teru
 */
public class Persona implements Serializable {
    private String nombre;
    private String apellido1;
    private String apellido2;
    private int edad;

    /**
     * Constructor de la clase. Crea una persona con nombre, dos apellidos y una edad
     *
     * @param nombre String que representa el nombre de una persona
     * @param apellido1 String que representa el primer apellido de una persona
     * @param apellido2 String que repsenta el sengu apellido de una persona
     * @param edad int que repsenta la edad de una persona
     */
    public Persona(String nombre, String apellido1, String apellido2, int edad){
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.edad = edad;
    }

    /**
     * Método set que asigna un nuevo nombre a una persona
     *
     * @param nombre String que representa el nuevo nombre a asignar a una persona
     */
    protected void setNombre(String nombre){
        this.nombre = nombre;
    }

    /**
     * Método set que asigna un nuevo primer apellido a una persona
     *
     * @param apellido1 String que representa el nuevo primer apellido a
     *                  asignar a una persona
     */
    protected void setApellido1(String apellido1){
        this.apellido1 = apellido1;
    }

    /**
     * Método set que asigna un nuevo segundo apellido a una persona
     *
     * @param apellido2 String que representa el nuevo segundo apellido a
     *      *                  asignar a una persona
     */
    protected void setApellido2(String apellido2){
        this.apellido2 = apellido2;
    }


    /**
     * Método set que asigna una nueva edad a una persona
     *
     * @param edad int que represnta la nueva edad a asignar a una persona
     */
    protected void setEdad(int edad){
        this.edad = edad;
    }


    /**
     * Método que crea un String que retorna los datos del persona
     *
     * @return String que representa los datos de una persona
     */
    public String toString (){
        String mensaje="La persona se llama "+this.nombre+" "+this.apellido1+" "+this.apellido2+" con "+this.edad+" años";
        return mensaje;
    }


}
