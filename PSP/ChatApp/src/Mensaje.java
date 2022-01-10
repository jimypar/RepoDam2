import java.io.*;

public class Mensaje implements Serializable {

    static final int CONECTADOS = 0, MENSAJE = 1, SALIR = 2;
    private int tipo;
    private String mensaje;

    Mensaje(int tipo, String mensaje) {
        this.tipo = tipo;
        this.mensaje = mensaje;
    }

    int getTipo() {
        return tipo;
    }
    String getMensaje() {
        return mensaje;
    }
}

