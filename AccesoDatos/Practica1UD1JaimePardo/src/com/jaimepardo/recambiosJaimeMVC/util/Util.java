package com.jaimepardo.recambiosJaimeMVC.util;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
 * Clase Util
 * Created by Jaime Pardo 8/11/21
 */
public class Util {

    /**
     * Crea una pantalla de mensaje de error
     *
     * @param mensaje Mensaje mostrado en el error
     */
    public static void mensajeError(String mensaje) {
        JOptionPane.showMessageDialog(null,
                mensaje,"Error",JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Crea una pantalla de mensaje de confirmacion
     *
     * @param mensaje Mensaje de confirmacion
     * @param titulo Titulo de la ventana
     * @return Int Devuelve de JOptionPane un int
     */
    public static int mensajeConfirmacion(String mensaje, String titulo) {
        return JOptionPane.showConfirmDialog(null,mensaje,
                titulo,JOptionPane.YES_NO_OPTION);
    }

    /**
     * Abre una ventana de selector de Ficheros
     *
     * @param rutaDefecto Ruta que quieras establecer por defecto
     * @param tipoArchivos Tipo de los archivos
     * @param extension Extension de archivos a mostrar
     * @return Devuelve el selector de ficheros en formato JFileChooser
     */
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
