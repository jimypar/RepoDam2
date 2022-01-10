/**
 * Clase de prueba Biblioteca. Implementa el patron de diseño Singleton
 * para que solo se pueda crear una unica instancia de la clase
 */
public class Biblioteca {

    //Instancia privada y estatica de la clase
    private static Biblioteca instance;
    //Nombre que se asignará a la biblioteca
    private String nombre;

    /**
     * Constructor vacio. En este caso se realiza privado para que no se pueda
     * acceder desde fuera de la clase
     */
    private Biblioteca(){

    }


    /**
     * Método que crea y retorna una unica instancia de la clase biblioteca
     *
     * @return objeto de la clase biblioteca
     */
    public static Biblioteca getInstance(){
        //Si el objeto es nulo se crea una instancia nueva
        if (instance == null){
            //Se crea una biblioteca
            instance = new Biblioteca();
        }
        //Se retorna la instancia de biblioteca
        return instance;
    }

    /**
     * Método que asigna un nombre a una biblioteca
     *
     * @param nombre String que representa el nombre que se asignará a la biblioteca
     */
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    /**
     * Método que obtiene el nombre asignado a una biblioteca
     *
     * @return String que representa el nombre que se ha asignado a la
     * biblioteca.
     */
    public String getNombre(){
        return this.nombre;
    }

    /**
     * Método de prueba de la clase
     */
    public static void main(String[] args){
        //Se obtiene una instancia de una biblioteca
        Biblioteca b = Biblioteca.getInstance();
        //Se asigna un nombre a la biblioteca
        b.setNombre("MiBliblioteca");
        System.out.println("Creada biblioteca con nombre "+b.getNombre());

        //¿Se crea otra instancia de biblioteca?
        Biblioteca b2 = Biblioteca.getInstance();
    }


}
