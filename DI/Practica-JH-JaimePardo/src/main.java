import gui.Conexion_Pardo;
import gui.Controlador;
import gui.Vista;

public class main {

    public static void main(String[] args) {
        Vista vista = new Vista();
        Conexion_Pardo conexionPardo = new Conexion_Pardo();
        Controlador controlador = new Controlador(vista, conexionPardo);
    }

}
