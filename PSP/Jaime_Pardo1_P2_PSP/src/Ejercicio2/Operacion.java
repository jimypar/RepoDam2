package Ejercicio2;

import jdk.nashorn.internal.runtime.regexp.joni.ScanEnvironment;

import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Scanner;

public class Operacion extends Observable {

    private float num1;
    private float num2;
    private static Operacion operacion;

    //Singleton de la clase
    public static Operacion getSingletonInstance() {
        if (operacion == null){
            operacion = new Operacion();
        }
        else {
            System.out.println("No se puede crear el objeto");
        }

        return operacion;


    }

    //Se piden dos numeros y se comprueba que son validos
    public void introducirNum(){

        Scanner scan = new Scanner(System.in);
        boolean error = false;

        do {
            try {
                System.out.print("Introduce numero 1: ");
                this.num1 = scan.nextInt();
                System.out.print("Introduce numero 2: ");
                this.num2 = scan.nextInt();
                error = false;
            } catch (InputMismatchException e) {
                setChanged();
                notifyObservers("Numero no valido");
                scan.next();
                error = true;
            }
        }while (error);

    }

    //Menu para seleccionar la operacion
    public void operacion(){

        Scanner scan = new Scanner(System.in);

        int menu = 0;
        boolean error = false;
        do {
            try {
                System.out.println("Introduce la operacion:");
                System.out.println("1.Suma");
                System.out.println("2.Resta");
                System.out.println("3.Multiplicacion");
                System.out.println("4.Raiz cuadrada");
                System.out.println("5.Potencia");
                menu = scan.nextInt();
                error = false;
            } catch (InputMismatchException e) {
                setChanged();
                notifyObservers("Numero no valido");
                scan.next();
                error = true;
            }
            switch (menu){

                case 1:
                    suma();
                    error = false;
                    break;
                case 2:
                    resta();
                    error = false;
                    break;
                case 3:
                    multiplicacion();
                    error = false;
                    break;
                case 4:
                    raiz();
                    error = false;
                    break;
                case 5:
                    potencia();
                    error = false;
                    break;
                default:
                    System.out.println("Numero no valido");
                    error = true;
                    break;
            }
        }while (error);

    }

    //Operacion suma notifica con el resultado
    public void suma() {
        System.out.println("Resultado de la suma:");
        float suma = this.num1 + this.num2;
        setChanged();
        notifyObservers(suma);
    }

    //Operacion resta notifica con el resultado
    public void resta() {
        System.out.println("Resultado de la resta:");
        float resta = this.num1 - this.num2;
        setChanged();
        notifyObservers(resta);
    }

    //Operacion multiplicacion notifica con el resultado
    public void multiplicacion() {
        System.out.println("Resultado de la multiplicación:");
        float multiplicacion = this.num1 * this.num2;
        setChanged();
        notifyObservers(multiplicacion);
    }

    //Operacion potencia notifica con el resultado
    public void potencia() {
        System.out.println("Resultado de la potencia:");
        float potencia = (float) Math.pow(this.num1, this.num2);
        setChanged();
        notifyObservers(potencia);
    }

    //Operacion raiz hace las dos raices de los dos numeros
    public void raiz(){

        raizCuadradaNumero1();
        raizCuadradaNumero2();

    }

    //Operacion raiz del primero numero notifica con el resultado
    public void raizCuadradaNumero1() {
        System.out.println("Resultado de la raíz cuadrada de "+num2+":");
        float raizCuadradaNumero1 = (float) Math.sqrt(this.num1);
        if(this.num1 < 0) {
            setChanged();
            notifyObservers("Error numero negativo");
        }else {
            setChanged();
            notifyObservers(raizCuadradaNumero1);
        }
    }

    //Operacion raiz del segundo numero notifica con el resultado
    public void raizCuadradaNumero2() {
        System.out.println("Resultado de la raíz cuadrada de "+num2+":");
        float raizCuadradaNumero2 = (float) Math.sqrt(this.num2);
        if(this.num2 < 0) {
            setChanged();
            notifyObservers("Error numero negativo");
        }else {
            setChanged();
            notifyObservers(raizCuadradaNumero2);
        }
    }



}
