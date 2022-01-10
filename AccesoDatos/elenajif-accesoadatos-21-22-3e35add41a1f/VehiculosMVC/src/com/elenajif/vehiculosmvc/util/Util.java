package com.elenajif.vehiculosmvc.util;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
 * Created by DAM on 28/10/2021.
 */
public class Util {
    public static void mensajeError(String mensaje) {
        JOptionPane.showMessageDialog(null,
                mensaje,"Error",JOptionPane.ERROR_MESSAGE);
    }

    public static int mensajeConfirmacion(String mensaje, String titulo) {
        return JOptionPane.showConfirmDialog(null,mensaje,
                titulo,JOptionPane.YES_NO_OPTION);
    }

    public static JFileChooser crearSelectorFichero(File rutaDefecto,
                                                    String tipoArchivos,
                                                    String extension) {
        JFileChooser selectorFichero=new JFileChooser();
        if (rutaDefecto!=null) {
            selectorFichero.setCurrentDirectory(rutaDefecto);
        }
        if (extension!=null) {
            FileNameExtensionFilter filtro = new
                    FileNameExtensionFilter(tipoArchivos,extension);
            selectorFichero.setFileFilter(filtro);
        }
        return selectorFichero;
    }
}
