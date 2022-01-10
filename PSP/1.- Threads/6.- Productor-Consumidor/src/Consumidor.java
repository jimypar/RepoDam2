/**
 * * @author Teru
 *
 * Clase Consumidor que representa a un consumidor que consume productos producidos
 * por un productor. Estos productos son dejados por el productor en el contenedor,
 * por lo que el consumidor obtendrá de ahí los productos que consuma
 *
 *
 */
public class Consumidor extends Thread{
    private final Contenedor contenedor;
    private final int idconsumidor;

    /**
     * Constructor de la clase. Crea un consumidor que contiene el contenedor del
     * que obtiene los productos y un nombre o identificador
     *
     * @param contenedor Objeto de la clase Contenedor que es común a los
     *                   consumidores y el productor. Será de ahí donde el consumidor
     *                   obtenga los productos que va a consumir
     *
     * @param idconsumidor String que representa el Identificador o nombre del
     *                     consumidor
     */
    public Consumidor(Contenedor contenedor, int idconsumidor){
        this.contenedor = contenedor;
        this.idconsumidor = idconsumidor;
    }

    /**
     * Implementación del hilo en el que se crea un bucle infinito en el que el
     * consumidor consume productos del contenedor
     */
    public void run()
    {
        while(Boolean.TRUE){
            System.out.println("El consumidor " + idconsumidor + " consume: " + contenedor.get());
        }
    }
}
