package Ejercicio1;

public class ejercicio1 {

    public static void main(String[] args) throws InterruptedException {

        //Creo dos hilos y les paso a cada uno el mensaje Si o No
        Hilo si = new Hilo("SI");
        Hilo no = new Hilo("NO");
        //Inicio el hilo si y espera a que termine con join para iniciar el no
        si.start();
        si.join();
        no.start();


    }

    static class Hilo extends Thread{

        private String mensaje;

        //Constructor clase Hilo
        public Hilo(String sino){
            this.mensaje=sino;
        }

        //Metodo que muestra el mensaje 10 veces
        public void run(){

            for (int i = 0;i<10;i++){
                System.out.print(mensaje+" ");
            }

        }

    }

}
