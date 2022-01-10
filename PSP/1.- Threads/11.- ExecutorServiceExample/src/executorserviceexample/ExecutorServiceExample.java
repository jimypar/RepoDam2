/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package executorserviceexample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Teru
 */
public class ExecutorServiceExample {

    
    public static void main(String[] args) {

       ExecutorService executor = Executors.newFixedThreadPool(10);
       executor.execute(() -> doLongWork("hi 1"));
       executor.execute(() -> doLongWork("hi 2"));
       executor.execute(() -> doLongWork("hi 3"));
   }

   private static void doLongWork(String hola) {
       System.out.println("Running " + hola);
       try {
           Thread.sleep(2000);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       System.out.println("Done " + hola);
   }
   
    
    
    
}
