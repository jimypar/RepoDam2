
import java.util.ArrayList;

/**
 * Clase que contiene dos arrayList, uno de entradas y otro de salidas contables
 * con el que se puede hacer un balance
 *
 * @author Teru
 */
public class Balance {

    //arrayList de entradas
    private ArrayList<Integer> entradas = new ArrayList<Integer>();
    //ArrayList de salidas
    private ArrayList<Integer> salidas = new ArrayList<Integer>();

    /**
     * Constructor que añade unas entradas y unas salidas predefinidas
     */
    public Balance(){
        //Se añaden las entradas
        entradas.add(89);
        entradas.add(47);
        entradas.add(123);
        entradas.add(56);
        entradas.add(71);

        //Se añaden las salidas
        salidas.add(99);
        salidas.add(25);
        salidas.add(17);
        salidas.add(115);
        salidas.add(84);
    }


    /**
     * Método que retorna en forma de String los datos contables que hay
     * almacenados en las entradas y las salidas.
     *
     * @return String que representa las estradas y salidas contables del balance
     */
    public String toString() {
        //Se obtienen las entradas
        String entradas = dameString(this.entradas);
        //Se obtienen las salidas
        String salidas = dameString(this.salidas);
        //Se retorna el String
        return "Entrada => "+entradas+"\n"+"Salidas => "+salidas;
    }

    /**
     * método que partiendo de un arrayList (entrada o salida) obtiene los
     * datos que hay en el en forma de String
     *
     * @param lista ArraList que será el de entradas o salidas contables
     * @return String que representa las entradas o salidas contables
     * segun la lista que se ha introducido
     */
    private String dameString(ArrayList lista) {

        String cadena = "";
        //Se recorre la lista
        for (Object valor : lista) {
            //Se concatenan los valores
            cadena = cadena +" "+valor;
        }
        return cadena;
    }

}
