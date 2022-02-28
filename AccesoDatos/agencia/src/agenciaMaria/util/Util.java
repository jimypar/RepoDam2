package agenciaMaria.util;

import javax.swing.*;

/**
 * Clase que sirve para madnar los mensaje de error
 */
public class Util {
    /**
     * Muestra el mensaje de error deseado en una ventana emergente.
     *
     * @param mensaje Representa el texto deseado para el mensaje de error.
     */
    public static void mostrarMensajeError(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
