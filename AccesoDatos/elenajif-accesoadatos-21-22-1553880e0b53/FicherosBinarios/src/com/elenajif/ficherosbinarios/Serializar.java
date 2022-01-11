package com.elenajif.ficherosbinarios;

import java.io.*;

/**
 * Created by DAM on 14/10/2021.
 */
public class Serializar {
    public void escribirObjeto(Object objeto) {
        FileOutputStream fichero=null;
        ObjectOutputStream serializador=null;

        try {
            fichero=new FileOutputStream("archivo.dat");
            serializador= new ObjectOutputStream(fichero);

            serializador.writeObject(objeto);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(serializador!=null) {
                try {
                    serializador.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public Object leerObjeto() {
        FileInputStream fichero=null;
        ObjectInputStream serializador=null;
        Object objeto=null;

        try {
            fichero=new FileInputStream("archivo.dat");
            serializador= new ObjectInputStream(fichero);

            objeto=serializador.readObject();

        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el fichero especificado");
        } catch (IOException e) {
            System.out.println("Error de entrada de datos");
        } catch (ClassNotFoundException e) {
            System.out.println("Tipo de objeto desconocido");
        } finally {
            if (serializador!=null) {
                try {
                    serializador.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return objeto;
    }
}
