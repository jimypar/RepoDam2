
import java.util.ArrayList;

/**
 * Clase macedonia que contiene un ArrayList que representa la lista de ingredientes
 * y un precio
 *
 * @author Teru
 */
public class Macedonia {

    //ArrayList que contendrá frutas
    private ArrayList macedonia = new ArrayList();
    private int precio;

    /**
     * Constructor que añade frutas a la macedonia
     *
     */
    public Macedonia(){
        macedonia.add("Melocoton");
        macedonia.add("Piña");
        macedonia.add("Kiwi");
        macedonia.add("Zumo de naranja");

        //Precio de la macedonia
        this.precio = 5;
    }


    /**
     * Metodo que retorna los ingredientes y el precio de la macedonia
     *
     * @return String que representa los ingredientes y el precio de la
     * macedonia
     */
    public String toString() {
        return "Ingredientes => "+dameString(this.macedonia)+"Precio => "+this.precio;
    }


    /**
     * Método que recorre el array de ingredientes de la macedonia
     *
     * @param lista ArrayList que representa la lista de ingredientes de la macedonia
     * @return String que representa los ingrediente de la macedonia
     */
    private String dameString(ArrayList lista) {

        String cadena = "";
        //Se recorre la lista
        for (Object valor : lista) {
            //Y se concatenan los ingredientes
            cadena = cadena +" "+valor+", ";
        }
        return cadena;
    }





}
