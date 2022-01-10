package Ejercicio1;

public class ejercicio1_2 {

    public static void main(String[] args) throws InterruptedException {

        //Creo dos hilos y les paso a cada uno el mensaje Si o No
        Hilo si = new Hilo("SI");
        Hilo no = new Hilo("NO");
        //Los dos hilos se ejecutan a la vez
        si.start();
        no.start();


    }

    static class Hilo extends Thread{

        private String mensaje;

        //Constructor clase Hilo
        public Hilo(String sino){
            this.mensaje=sino;
        }

        //Metodo que muestra el mensaje 10 veces y usa el syncronized
        //Cada for se ejecuta un mensaje y con wait()
        // espera a que haya un notify para vover a mostrar el mensaje
        public void run(){

            synchronized (getClass()){

                for (int i = 0;i<10;i++){
                    System.out.print(mensaje+" ");
                    System.out.flush();
                    getClass().notifyAll();
                    try {
                        getClass().wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }



        }

    }

}
