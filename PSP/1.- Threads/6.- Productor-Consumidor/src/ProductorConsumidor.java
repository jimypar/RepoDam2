/**
 * @author Teru
 *
 * Clase que muestra el problema clásico de sincronización del Productor-Consumidor.
 * Hay un productor que produce productos y los deja en un espacio común en donde
 * varios consumidores pueden cogerlos para consumirlos.
 *
 */
public class ProductorConsumidor {


    private static Contenedor contenedor; //Lugar donde se depositan los productos
    private static Thread productor; //Hilo del productor
    private static Thread [] consumidores; //Array de Threads  de los consumidores
    private static final int CANTIDADCONSUMIDORES = 5; //Comsumidores


    public static void main(String[] args) {
        //Se crea el contenedor para introducir los productos
        contenedor = new Contenedor();
        //Se crea el productor
        productor = new Thread(new Productor(contenedor, 1));
        //Se crean los consumidores
        consumidores = new Thread[CANTIDADCONSUMIDORES];

        //Se arrancan los consumidores
        for(int i = 0; i < CANTIDADCONSUMIDORES; i++){
            consumidores[i] = new Thread(new Consumidor(contenedor, i));
            consumidores[i].start();
        }
        //Se arrancan los productores
        productor.start();
    }

}

