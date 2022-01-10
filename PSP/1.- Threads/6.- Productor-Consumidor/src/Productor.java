import java.util.Random;

/**
 * Clase Productor que representa al Productor del problema
 * clásico de sincronización del Productor-Consumidor
 *
 * Se encargará de producir productor y dejarlos en un contenedor
 * para que los consumidores puedan consumirlos
 *
 * @author Teru
 */
public class Productor extends Thread{

    private final Random aleatorio;  //Clase para obtener números aleatorios
    private final Contenedor contenedor;  //Contenedor de objetos
    private final int idproductor;  //id del productor
    private final int TIEMPOESPERA = 1500;

    /**
     * Constructor de la clase que asigna un contenedor y un identificador del
     * Productor
     *
     * @param contenedor Contenedor común a los consumidores y el productor
     * @param idproductor Identificador del productor
     */
    public Productor(Contenedor contenedor, int idproductor){
        this.contenedor = contenedor;
        this.idproductor = idproductor;
        aleatorio = new Random();
    }

    /**
     * Implementación del hilo del Productor
     */
    public void run()
    {
        while(Boolean.TRUE){
            int poner = aleatorio.nextInt(300);//Se obtiene un nuemro aleatorio
            contenedor.put(poner); //Se ponen los productos
            /* Se notifica los productos que ha introducido el productor */
            System.out.println("El productor " + idproductor + " pone: " + poner);
            try{
                Thread.sleep(TIEMPOESPERA); //Se duerme el hilo
            }
            catch (InterruptedException e){
                System.err.println("Productor " + idproductor + ": Error en run -> " + e.getMessage());
            }
        }
    }
}