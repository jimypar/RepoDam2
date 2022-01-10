package Ejercicio5;

public class LanzarDado extends Thread{

    private boolean esPar;
    private int num;

    public LanzarDado(){
        this.esPar = false;
        this.num = 0;
    }

    //Metodo que genera un numero entero entre 1 y 6 simulando un dado y cambia el booleano si es par
    public void run(){

        int resultado = (int) Math.floor(Math.random()*6+1);
        this.num=resultado;
        this.esPar= resultado % 2 == 0;

    }

    //Getter del booleano esPar
    public boolean esPar() {
        return esPar;
    }
}
