package com.life;

import java.util.logging.Level;
import java.util.logging.Logger;


// Actually in Java there is only one way to create thread, which is Thread.start()
// Every way to create thread like Runnable, Callable, ExecutorService, ForkJoin, CompletableFuture, Timer, parallelStream class,
// In the source code ultimately will call Thread.start() to create the thread
// https://mp.weixin.qq.com/s/NspUsyhEmKnJ-4OprRFp9g - how actually Java create the thread

// Runnable and Callable more precise to said is the way to create thread body (in this case Runnable and Callable refer to an interface that can run the task)
// thread body only contains run() method , when the thread get launch, it's run() method logic will execute by the thread
// Therefore, thread is the container used to executed thread body define task - run() method, when we create a thread, we need to designate one thread body for it,
// allow when thread launch to execute corresponding task. Thread and thread body relationship is like Class and Object.
// we can think of Thread body is multithreaded task

// new Runnable(...);
// new Callable(...);
// Above is not really create the thread, is to creates two ‘multithreaded tasks’ that can be offered to threads for execution.

// Another question is how task and thread come with a binding relationship ? we can search below method in Thread class to read the source code to see
// This constructor in Thread class will initialize a thread (which is platform thread).
// public Thread(Runnable xxx) {
//    xxx
// }

// Summary, thread is the container to execute thread body, and thread body is a runnable task

// Extends Thread class or implements Runnable interface to begin concurrency
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


        // order of thread execution order does not guarantee by Java, the result might vary until runtime
        // in general if not using executors manage the thread, prefer use Runnable over Thread

        // Two way to execute task for Thread and Runnable
        // if xxxx class extends Thread class, just new xxxx().start() to execute task
        new ConcurrentThreadAndRunnable().start();

        // passed in the Runnable object to Thread constructor to start execute task where define in run()
        new Thread(new ConcurrentThreadAndRunnable()).start();

    }

}
