package Ejercicio4;

import java.util.ArrayList;

public class ContarPalabra extends Thread{

    private String palabraBuscar;
    private ArrayList<String> palabras;
    private int numPalabras;

    //Constructor que recibe el array de palabras y una palabra que tiene que buscar
    public ContarPalabra(ArrayList palabras,String palabra){
        this.palabras = palabras;
        this.palabraBuscar = palabra;
    }

    //Metodo que recorre el array de palabras y busca las similares a la pasada
    public void run(){

        for (int i = 0; i<palabras.size();i++){
            if (palabras.get(i).equals(palabraBuscar)
                    || palabras.get(i).equals(palabraBuscar+",")
                    || palabras.get(i).equals(palabraBuscar+".")
                    || palabras.get(i).equals(palabraBuscar+":")
                    || palabras.get(i).equals(palabraBuscar+"),")
                    || palabras.get(i).equals(palabraBuscar+";")
                    || palabras.get(i).equals(palabraBuscar+"'")){
                this.numPalabras++;
            }
        }

    }

    //Getter del numero de palabras
    public int getNumPalabras() {
        return numPalabras;
    }
}

