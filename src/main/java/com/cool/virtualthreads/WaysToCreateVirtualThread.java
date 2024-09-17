package com.cool.virtualthreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class WaysToCreateVirtualThread {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Starting Main");

        useStaticThreadMethod();

        useVirtualThreadBuilder();

        useThreadFactory();

        useVirtualThreadExecutorService();

        System.out.println("Ending Main");
    }
    
    private static void useVirtualThreadExecutorService() {
        // 4. Using the Virtual Thread Executor Service
        // Create a Virtual Thread ExecutorService
        // Note the try with resource will make sure all virtual threads are terminated
        // For virtual threads not really interested the upper limit to the thread pool
        // Because are cheap and efficient to create but more on using futures and
        // Completable futures and creating the thread pipelines

        // If want to create virtual thread with username using factory can do below
        // By default virtual thread does not come have a name
         ThreadFactory factory = Thread.ofVirtual().name("userthread",0).factory();
        //try(ExecutorService executorService = Executors.newThreadPerTaskExecutor(factory)){

        // When there is a task submit to the VirtualTaskPerTaskExecutor executors, A virtual threads object is created and
        // The request task is associated with the object, the task is wrapped in a continuation object and associated with the virtual threads
        // for JVM run this continuation object need to schedule on a platform thread

        // Virtual threads implemented in the JVM and need to be scheduled on platform thread
        // The scheduling is done using a ForkJoinPool, a more sophisticated executor service, got a pool of platform threads
        // The ForkJoinPool is used to schedule the continuation of the virtual threads on the platform threads
        // And virtual threads does not need any thread pool
        // By using virtual threads, the JVM automatically switches from a blocking to non-blocking io call
        // We can think virtual thread is a Java object with no stack of its own associated with it and that's why creating was so fast
        // But always a continuation object associated with that which is associated with a stack
        // Continuation object will stored code pointer and method stack frames
        try(ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()){

            // Submit two tasks to the Executor service
            executorService.submit(WaysToCreateVirtualThread::handleUserRequest);
            executorService.submit(WaysToCreateVirtualThread::handleUserRequest);
        }

        // Control reaches here once the two virtual thread completes
    }

    private static void useThreadFactory() throws InterruptedException {
        // 3. Using the Thread Factory
        // Create a Thread factory
        // this is thread safe, we can get the thread factory object out of builder
        ThreadFactory factory = Thread.ofVirtual().name("userthreads", 0).factory();

        // Start two virtual threads using the builder
        Thread thread1 = factory.newThread(WaysToCreateVirtualThread::handleUserRequest);
        thread1.start();

        Thread thread2 = factory.newThread(WaysToCreateVirtualThread::handleUserRequest);
        thread2.start();

        // Make sure the thread terminates
        // so that the main thread block till all threads complete to terminate
        thread1.join();
        thread2.join();

        // Control reaches here once the two virtual thread completes
    }

    private static void useVirtualThreadBuilder() throws InterruptedException {
        // 2.Using the Virtual Thread Builder
        // Create a Virtual Builder with name and initial index
        // builder is not a thread safe way, multiple threads cannot use the same builder object
        // to create threads
        Thread.Builder.OfVirtual builder = Thread.ofVirtual().name("username",0);

        // Start two virtual threads using the builder
        Thread start1 = builder.start(WaysToCreateVirtualThread::handleUserRequest);
        Thread start2 = builder.start(WaysToCreateVirtualThread::handleUserRequest);

        // Make sure the thread terminates
        start1.join();
        start2.join();

        // Control reaches here once the two virtual thread completes
    }

    private static void useStaticThreadMethod() throws InterruptedException {
        // 1. Using a static Thread method
        // Start a new Virtual Thread. No name is associated with the thread
        Thread thread = Thread.startVirtualThread(()->handleUserRequest());

        // Make sure the thread terminates
        thread.join();

        // Control reaches here once the virtual thread completes
    }

    private static void handleUserRequest(){

        System.out.println("Staring Thread " + Thread.currentThread());

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Ending Thread " + Thread.currentThread());
    }
}
