/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package executorservicefutureexample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author Teru
 */
public class ExecutorServiceFutureExample {

     public static void main(String[] args) throws Exception {

       ExecutorService executor = Executors.newFixedThreadPool(7);
       Future<String> future = executor.submit(() -> doLongWork("hi with future 1"));
       //future = executor.submit(() -> doLongWork("hi with future 2"));
       //future = executor.submit(() -> doLongWork("hi with future 3"));
       System.out.println(future.get());

   }

   private static String doLongWork(String msg) {
       System.out.println("Running " + msg);
       try {
           Thread.sleep(100);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       System.out.println("Done " + msg);
       return "done " + msg;
   }
    
}
