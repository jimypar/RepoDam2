
/**
 * @author Teru
 *
 * Clase cajera que simula el funcionamiento de una cajera de un supermercado y utiliza Threads.
 * Cojerá los productos y los pasará por la linea de caja. Para simular este proceso realizará
 * un sleep del producto (número entero)
 *
 */
public class CajeraThread extends Thread{

    private String nombre;
    private Cliente cliente;
    private long initialTime;

    /**
     * Constructor de la clase Cajera al cual se le pasan tres parametros
     * @param nombre String que representa el nombre o identificador que tendrá la cajera
     * @param cliente objeto de la clase cliente, que representa al cliente que va a atender
     *                la cajera
     * @param initialTime long que representa el tiempo inicial de ejecución de la aplicación
     */
    CajeraThread(String nombre, Cliente cliente, long initialTime) {
        this.cliente = cliente;
        this.nombre = nombre;
        this.initialTime = initialTime;
    }


    /**
     * Método que inicia la ejecución del Thread
     */
    @Override
    public void run() {
        /* Se muestra un mensaje indicando que comienza a anternder a un cliente */
        System.out.println("La cajera " + this.nombre + " COMIENZA A PROCESAR LA COMPRA DEL CLIENTE "
                + this.cliente.getNombre() + " EN EL TIEMPO: "
                + (System.currentTimeMillis() - this.initialTime) / 1000
                + "seg");

        /* bucle que recorre todos los objetos del carro de la compra */
        for (int i = 0; i < this.cliente.getCarroCompra().length; i++) {
            this.esperarXsegundos(cliente.getCarroCompra()[i]);
            /* Se informa de que la cajera esta pasando un producto por el lector */
            System.out.println("Procesado el producto " + (i + 1)
                    + " del cliente " + this.cliente.getNombre() + "->Tiempo: "
                    + (System.currentTimeMillis() - this.initialTime) / 1000
                    + "seg");
        }

        /* Se informa de que la cajera ha terminado de atender a un cliente */
        System.out.println("La cajera " + this.nombre + " HA TERMINADO DE PROCESAR "
                + this.cliente.getNombre() + " EN EL TIEMPO: "
                + (System.currentTimeMillis() - this.initialTime) / 1000
                + "seg");
    }

    /**
     * Metodo que simula que la cajera pasa el producto por el lector de codigode barras.
     * Para ello realiza un sleep del numero de que representa el producto
     *
     * @param segundos int que representa los segundos que tardará en pasar la cajera
     *                 el producto por el lector
     */
    private void esperarXsegundos(int segundos) {
        try {
            Thread.sleep(segundos * 1000); //duerme los segundos indicados
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}

