package com.cool.virtualthreads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class MaxThreads {

    // Virtual threads implemented in the JVM and need to be scheduled on platform thread
    // The scheduling is done using a ForkJoinPool, a more sophisticated executor service, got a pool of platform threads
    // The ForkJoinPool is used to schedule the continuation of the virtual threads on the platform threads
    // And virtual threads does not need any thread pool
    // By using virtual threads, the JVM automatically switches from a blocking to non-blocking io call
    // We can think virtual thread is a Java object with no stack of its own associated with it and that's why creating was so fast
    // But always a continuation object associated with that which is associated with a stack
    // Continuation object will stored code pointer and method stack frames

    private static void handleUserRequest(){

        // From the log we can see virtual thread with is associated with the regular java thread
        // Which come from the ForJoinPool as a worker, and this corresponds 1 to 1 OS thread
        // So then virtual threads a lot than the platform / OS  thread
        // platform threads are used as carrier threads to run the virtual threads
        // Staring Thread VirtualThread[#10031]/runnable@ForkJoinPool-1-worker-11
        // Ending Thread VirtualThread[#10031]/runnable@ForkJoinPool-1-worker-13
        System.out.println("Staring Thread " + Thread.currentThread());

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Ending Thread " + Thread.currentThread());
    }
    public static void main(String[] args) throws InterruptedException {

        // Main thread is a regular java thread
        System.out.println("Staring main");

        List<Thread> threads = new ArrayList<>();

        for(int i = 0 ;i < 10000; i++){
            threads.add(startThread());
        }

        // Join on the threads
        for(Thread thread : threads){
            // Join means wait this thread terminate
            thread.join();
        }

        System.out.println("Ending main");

    }

    // Virtual thread is a demon thread
    // Below way of staring thread need to enable Java 21 preview in project setting and modules
    private static Thread startThread() {
        //new Thread(() -> handleUserRequest()).start();
        return Thread.startVirtualThread(()->handleUserRequest());
    }
}
