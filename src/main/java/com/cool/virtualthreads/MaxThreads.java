package com.cool.virtualthreads;

import java.util.ArrayList;
import java.util.List;

public class MaxThreads {

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
