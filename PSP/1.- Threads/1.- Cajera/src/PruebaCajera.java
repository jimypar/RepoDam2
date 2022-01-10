
/**
 * @author Teru
 *
 * Este ejemplo simula el funcionamiento de una una cola de un supermercado
 * que es atendida por 4 cajas. En este caso se pretende simular el funcionamiento
 * de un procesador con un único hilo de ejecución.
 *
 * En este caso se presentan cuatro clintes a su caja. El cliente contiene un array
 * de numeros enteros. Para simular el paso de productos por la caja se realiza un sleep
 * del numero que se inspecciona en el array.
 *
 * De esta manera se puede comprobar la diferencia de tiempo que costaría atender a los
 * clientes si se utiliza 1 caja (procesador monoHilo) o con cuatro cajas (procesador
 * multiHilo)
 *
 */
public class PruebaCajera {
    public static void main(String[] args) {
        /* Se crean los cuatro clientes que se van a utilzar en el ejemplo */
        Cliente cliente1 = new Cliente("Cliente 1", new int[] { 2, 2, 1, 5, 2, 3 });
        Cliente cliente2 = new Cliente("Cliente 2", new int[] { 1, 3, 5, 1, 1 });
        Cliente cliente3 = new Cliente("Cliente 3", new int[] { 4, 5, 1, 2, 7, 3 });
        Cliente cliente4 = new Cliente("Cliente 4", new int[] { 3, 6, 1, 2, 4 });

        /* Se crea la cajera que atenderá a los clientes */
        Cajera cajera1 = new Cajera("Cajera 1");

        // Se toma el tiempo inicial de referencia
        long initialTime = System.currentTimeMillis();
        //System.out.println(initialTime);

        /* La cajera atiende los cuatro clientes */
        cajera1.procesarCompra(cliente1, initialTime);
        cajera1.procesarCompra(cliente2, initialTime);
        cajera1.procesarCompra(cliente3, initialTime);
        cajera1.procesarCompra(cliente4, initialTime);
    }
}
