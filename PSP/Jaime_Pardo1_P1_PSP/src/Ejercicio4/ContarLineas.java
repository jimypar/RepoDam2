package Ejercicio4;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaNamespaceSupport;

import javax.annotation.processing.Filer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ContarLineas extends Thread{

    private int numLineas=0;

    public ContarLineas(){
    }

    //Metodo que cuenta las lineas que hay en el fichero con el scanner
    public void run(){

        FileReader fichero = null;
        try {
            fichero = new FileReader("Contitucion1812.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner scan = new Scanner(fichero);

            while (scan.hasNextLine()){
                scan.nextLine();
                numLineas++;
            }


    }

    //Getter del numero de lineas
    public int getNumLineas() {
        return this.numLineas;
    }
}
