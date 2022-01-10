/**
 * @author Teru
 *
 * Clase cajera que simula el funcionamiento de una cajera de un supermercado y utiliza Threads.
 * Cojerá los productos y los pasará por la linea de caja. Para simular este proceso realizará
 * un sleep del producto (número entero)
 *
 */
public class Cajera {

    private String nombre;

    /**
     * Constructor de la clase que asigna un nombre o identificador a una cajera
     * @param nombre String que representa el nombre o identificador que tendrá
     *               una cajera
     */
    Cajera(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método simulará que la cajera atiende a uno de sus clientes
     *
     * @param cliente objeto de la clase cliente que representa al cliente que atenderá
     *               la cajera
     * @param timeStamp int que representa los segundos que tardará en pasar la cajera
     *      *        el producto por el lector
     */
    public void procesarCompra(Cliente cliente, long timeStamp) {
        /* Se muestra un mensaje indicando que comienza a anternder a un cliente */
        System.out.println("La cajera " + this.nombre +
                " COMIENZA A PROCESAR LA COMPRA DEL CLIENTE " + cliente.getNombre() +
                " EN EL TIEMPO: " + (System.currentTimeMillis() - timeStamp) / 1000	+
                "seg");

        /* bucle que recorre todos los objetos del carro de la compra */
        for (int i = 0; i < cliente.getCarroCompra().length; i++) {
            this.esperarXsegundos(cliente.getCarroCompra()[i]);
            System.out.println("Procesado el producto " + (i + 1) +
                    " ->Tiempo: " + (System.currentTimeMillis() - timeStamp) / 1000 +
                    "seg");
        }

        /* Se informa de que la cajera ha terminado de atender a un cliente */
        System.out.println("La cajera " + this.nombre + " HA TERMINADO DE PROCESAR " +
                cliente.getNombre() + " EN EL TIEMPO: " +
                (System.currentTimeMillis() - timeStamp) / 1000 + "seg");
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

