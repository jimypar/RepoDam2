
/**
 *  @author Teru
 *
 *  Clase cliente, que representa a un cliente que lleva un conjunto de productos
 *  que ha comprado en un supermercado
 */
public class Cliente {

    private String nombre;  //nombre (id) del cliente
    private int[] carroCompra;  //array que representa el carro de la compra del cliente

    /**
     * Constructor de la clase Cliente. Crea un cliente con un nombre y un carro
     * de la compra
     *
     * @param cliente_1: String que representa el nombre o identificador que se le va
     *                 a asignar a un cliente.
     *
     * @param carroCompra array de enteros que representa el carro de un cliente. para simular
     *                    el paso de productos en la caja se simula realizando un sleep de cada
     *                    numereo entero que contenga el array
     */
    Cliente(String cliente_1, int[] carroCompra) {
        this.nombre = nombre;
        this.carroCompra = carroCompra;
    }

    /**
     * Método get que retorna el nombre o identificador del cliente
     *
     * @return String que representa el nombre o identificador del cliente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que asigna un nombre o identificador al cliente
     *
     * @param nombre String que representa el nombre o identificador del cliente
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    /**
     * Método get que retorna el carro de la compra
     *
     * @return int[]. Retorna el array de números enteros que representa
     * el carro de la compra del cliente
     */
    public int[] getCarroCompra() {
        return carroCompra;
    }

    /**
     * Método set que asigna un nuevo carro de la compra al cliente
     *
     * @param carroCompra int[]. Array de numeros enteros que representa
     *                    el carro de la compra de un cliente
     */
    public void setCarroCompra(int[] carroCompra) {
        this.carroCompra = carroCompra;
    }




}