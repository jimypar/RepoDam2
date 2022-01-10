import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Teru
 */
class Observador implements Observer {

    /*
    Metodo que cuando se produce una actualizción en el observado
    recibe la actualización y la muestra por pantalla
    */
    @Override
    public void update(Observable o, Object o1) {
        //Se notifica la actualización
        System.out.println("Nueva Actualizacion: "+o+" -> "+o1);
    }
}
