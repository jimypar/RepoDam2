package Ejercicio3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ejercicio3 {

    public static void main(String[] args) throws InterruptedException {

        //Iniciar scanner
        Scanner scanner = new Scanner(System.in);

        double a = 0;
        double b = 0;
        double c = 0;

        //Se piden tres numeros por terminal y se comprueba que sean entre 1 y 10
        boolean error = false;
        do {
            try {
                System.out.println("Introduce tres numeros:(1 a 10)");
                System.out.print("- ");
                a = scanner.nextDouble();
                System.out.print("- ");
                b = scanner.nextDouble();
                System.out.print("- ");
                c = scanner.nextDouble();

                if (a>10 || a<1){
                    error = true;
                }else if(b>10 || b<1){
                    error = true;
                }else if(c>10 || c<1){
                    error = true;
                }else {
                    error = false;
                }

            } catch (InputMismatchException e) {
                error = true;
                scanner.next();
            }
        }while (error);


        //Se crean dos hilos con los tres numeros, uno por cada ecuacion
        Hilo1 h1 = new Hilo1(a,b,c);
        Hilo2 h2 = new Hilo2(a,b,c);

        //Se inicia una ecuacion y despues la otra con join()
        h1.start();
        h1.join();
        h2.start();
        h2.join();

        //Se crea el hilo del resultado enviando los dos resultados de las ecuaciones
        Hilo3 h3 = new Hilo3(h1.getResultado(),h2.getResultado());

        //Se inicia el hilo
        h3.start();
        h3.join();

        //Muestra el resultado del hilo3
        System.out.println("El resultado es: "+h3.getResultado());


    }

}

class Hilo1 extends Thread{

    private double x,y,z, resultado;

    //Constructor que recoge los tres numeros
    public Hilo1(double x,double y,double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //Metodo que da el resultado de la ecuacion, rodeado de dos sleep de 2 segundos
    public void run(){

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.resultado = Math.pow((x+y),2) - ((4*x)+(3*y)-(2*z));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Getter del resultado de la ecuacion
        public double getResultado() {

        return this.resultado;

        }

    }

class Hilo2 extends Thread{

    private double x,y,z,resultado;

    //Constructor que recoge los tres numeros
    public Hilo2(double x,double y,double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //Metodo que da el resultado de la ecuacion, rodeado de dos sleep de 2 segundos
    public void run(){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.resultado = Math.pow(((5 * z) - (7 * y) + (Math.pow(x,2))), 3) - Math.pow(((4 * x) + (3 * y) - (8 * z)),2);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }
    //Getter del resultado de la ecuacion
    public double getResultado() {

        return this.resultado;

    }
}

class Hilo3 extends Thread{

    private double result1,result2,resultado;

    //Constructor que recibe los dos resultados de las ecuaciones
    public Hilo3(double result1,double result2){
        this.result1 = result1;
        this.result2 = result2;
    }

    //Metodo que realiza la suma de los parametros
    public void run(){
        this.resultado = this.result1 + this.result2;
    }

    //Getter del resultado final
    public double getResultado() {
        return resultado;
    }
}



