package com.elenajif.vehiculosmongo.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by DAM on 18/02/2022.
 */
public class Util {
    public static String formatearFecha(LocalDate fechaMatriculacion) {
        DateTimeFormatter formateador =DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return formateador.format(fechaMatriculacion);
    }

    public static LocalDate parsearFecha(String fecha) {
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(fecha,formateador);
    }
}
