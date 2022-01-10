/**
 * Clase de prueba que permite probar las clases MyObservable y MyObserver,
 * que siguen el patrón de diseño Observer
 *
 * @author Teru
 */
public class PruebaObserver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException{
        //Se crea un observable
        MyObservable myobservable = new MyObservable();

        //Se crea un observador
        MyObserver myobserver = new MyObserver();

        //Se añade un observador
        myobservable.addObserver(myobserver);

        //Se crea un Thread y se lanza a traves de el el observador
        Thread thread = new Thread(myobservable);
        thread.start();

        Thread.sleep(2000);
        thread.stop();
        System.out.println("Finishing!");
    }

}
