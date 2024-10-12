package com.life;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


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

public class ConcurrentCallable implements Callable<String> {
    // Callable takes a generic argument
    // ReentrantLock allow using lock and unlock , trylock like synchronized

    private String name;
    public int count=10;

    // Callable interface defines task can be submitted to ExecutorService
    // it can return result and can throw a checked exception
    // ExecutorService executor used for fire and forget, can't return value
    // ExecutorService submit can get the return value

    // Future can get back from ExecutorService with submitted task Callable result

    // java.util.concurrent.Future
    // the Future<T> interface is used to obtain results from Callable call()
    // future.get() is blocking method , will need to await it finish only return, can use to enforce to sequential order
    // this allow to work with asynchronous


    public ConcurrentCallable()
    {
        super();
    }

    public ConcurrentCallable(String name)
    {
        this.name = name;
    }


    public void increment()
    {
        // synchronized enforce each thread enter this block of codes get a lock or monitor to execute,
        // other thread need to wait the thread that get the lock to finished and release lock only
        // can get the lock to inside, this can ensure the order be properly
        System.out.println((++count) +" ");

    }


    @Override
    public String call() throws Exception {
        return this.name;
    }

    public static void main(String[] args){

        ExecutorService es = Executors.newFixedThreadPool(4);
        ConcurrentCallable task = new ConcurrentCallable("thread return value : Alpha");
        ConcurrentCallable task1 = new ConcurrentCallable("thread return value : Beta");

        // In order to know the result of the ExecutorService task is completed or not, we use Future<?>
        // to determine the result, as ExecutorService.submit(Callable<?> xxx) object return this
        Future<String> f1 = es.submit(task);
        Future<String> f2 = es.submit(()->"Thread return value : Beta");

        // there is another CompletableFuture<Integer> f = CompletableFuture.supplyAsync(new MySupplier(), exec);
        // this is non blocking like asynchronous , f.thenApply(xxxx()); like call back


        try{
            es.shutdown();
            es.awaitTermination(5, TimeUnit.SECONDS);

            String result1 = f1.get();
            System.out.println(result1);
            String result2 = f2.get();
            System.out.println(result2);


        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

}
