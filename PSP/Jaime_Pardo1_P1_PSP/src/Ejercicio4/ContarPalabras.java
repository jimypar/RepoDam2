package Ejercicio4;

import com.sun.org.apache.bcel.internal.generic.ARETURN;

import java.util.ArrayList;

public class ContarPalabras extends Thread{

    private ArrayList<String> palabras;
    private int numPalabras;

    //Constructor que recibe el array de palabras
    public ContarPalabras(ArrayList palabras){
        this.palabras = palabras;
    }

    //Metodo que cuenta la longitud del ArrayList que equivale al nuemero de palabras
    public void run(){

        this.numPalabras = palabras.size();

    }

    //Getter del numero de palabras
    public int getNumPalabras() {
        return numPalabras;
    }
}
