package com.life;

import java.util.logging.Level;
import java.util.logging.Logger;

// extends Thread class or implements Runnable interface to begin concurrency
public class ConcurrentThreadAndRunnable extends Thread implements Runnable {

    // OCP Java SE 11 no longer focus on create thread by extends Thread and implements Runnable
    // use java.util.concurrent.ExecutorService to executed task, is better
    // no need to create and manage threads
    // task might be executed in parallel
    // task can be Runnable or concurrent.callable

    // both Thread class and Runnable interface has run method
    @Override
    public void run(){
        System.out.println("From a thread!!!!!!!!");
        try{
            Thread.sleep(100); // sleep to polling
        } catch (InterruptedException e) {
            Logger.getLogger(ConcurrentThreadAndRunnable.class.getName()).log(Level.SEVERE, (String) null);
        }
    }


    public static void main(String[] args){

        Runtime rt = Runtime.getRuntime();
        System.out.println("Avail CPU " + rt.availableProcessors());


        // order of thread execution order does not guaranteed by Java, the result might vary until runtime
        // in general if not using executors manage the thread, prefer use Runnable over Thread

        //Two way to execute task for Thread and Runnable
        // if xxxx class extends Thread class, just new xxxx().start() to execute task
        new ConcurrentThreadAndRunnable().start();

        // passed in the Runnable object to Thread constructor to start execute task where define in run()
        new Thread(new ConcurrentThreadAndRunnable()).start();





    }

}
