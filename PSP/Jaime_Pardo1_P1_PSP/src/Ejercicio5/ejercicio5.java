package Ejercicio5;

public class ejercicio5 {

    public static void main(String[] args) throws InterruptedException {

        //Numero de pares e impares totales
        int pares = 0;
        int impares = 0;

        //Bucle que lanza los 5 dados 15000 veces
        for (int i = 0;i<15000;i++){

            //Se crean los hilos de los 5 dados
            LanzarDado dado1 = new LanzarDado();
            LanzarDado dado2 = new LanzarDado();
            LanzarDado dado3 = new LanzarDado();
            LanzarDado dado4 = new LanzarDado();
            LanzarDado dado5 = new LanzarDado();

            //Se inician los 5 dados a la vez
                dado1.start();
                dado2.start();
                dado3.start();
                dado4.start();
                dado5.start();

            //Bucle que espera a que los 5 dados terminen
                while (dado1.isAlive() || dado2.isAlive() || dado3.isAlive() || dado4.isAlive() || dado5.isAlive()) { }

            //Comprobar si los resultados son pares o impares y sumarlos al total
                if (dado1.esPar()) {
                    pares++;
                } else {
                    impares++;
                }
                if (dado2.esPar()) {
                    pares++;
                } else {
                    impares++;
                }
                if (dado3.esPar()) {
                    pares++;
                } else {
                    impares++;
                }
                if (dado4.esPar()) {
                    pares++;
                } else {
                    impares++;
                }
                if (dado5.esPar()) {
                    pares++;
                } else {
                    impares++;
                }
    }

        //Muestra los resultados de los numeros
        System.out.println("Pares: "+pares);
        System.out.println("Impares: "+impares);

        //Muestra el resultado final
        if (pares>impares){
            System.out.println("HA SALIDO PAR");
        }else{
            System.out.println("HA SALIDO IMPAR");
        }




    }

}
