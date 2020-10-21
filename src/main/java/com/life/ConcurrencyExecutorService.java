package com.life;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ConcurrencyExecutorService {

    // Callable interface defines task can be submitted to ExecutorService
    // it can return result and throws a checked exception
    // ExecutorService execute used for fire and forget, doesn't return result and value and only allow Runnable
    // ExecutorService submit can get the return value to determine if the task is completed

    public static void future()
    {
        // Future is the interface instance, return by any Executor that return Future
        // isDone() - return true if the task was completed, threw an exception or cancelled
        // isCancelled() - return true if task was cancelled before completed normally
        // cancel(boolean,mayInterruptRunning)
        // get()
        // get(long timeout, TimeUnit unit )
        System.out.println("future----------------------------");
        ExecutorService es = Executors.newSingleThreadExecutor();

        Future<?> fs = es.submit(()-> 41);
        Callable<String> c = ()-> "result";

        try
        {
            System.out.println(fs.isDone());
            System.out.println(fs.get());

            //invokeAll() is synchronous, means only that entire task is complete only moved next lines
            //invokeAny() same synchronous, but just wait only either one task complete to moved next
            List<Future<String>> list = es.invokeAll(List.of(c,c,c));
            for(Future<String> future : list){
                System.out.println(future.get());
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        es.shutdown();
    }

    public static void Scheduled()
    {
        System.out.println("Scheduled-------------------------");
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        Runnable task1 = () -> System.out.println("5");
        Runnable task2 = () -> System.out.println("10");
        Callable<Integer> task3 = () -> 10;

        ses.schedule(task1,1,TimeUnit.SECONDS);
        ses.schedule(task3,5,TimeUnit.SECONDS);
        // this will submit new task and run specific intervals fot the time set, will not wait previous task
        // if system submit tasks that over to memory, might cause program to crash it
        ses.scheduleAtFixedRate(task1,1,5,TimeUnit.SECONDS);
        // FixedDelay will wait every task to finish and wait delay time by set only will create new task to continue
        ses.scheduleWithFixedDelay(task2,0,2,TimeUnit.SECONDS);


    }

    public static void ConcurrencyWithPools()
    {

        // cachedThread create thread as request come in, reuse the thread it created, don't dead immediately finishing their task
        // if the thread doesn't not anything in 60 seconds, then it will de-allocated and thrown away
        // fixedThread pool contains fixed number of threads, also reuse and queue up work until a thread is available
        ExecutorService es = Executors.newCachedThreadPool();
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(4);

        Runnable task1 = () -> System.out.println("5");
        Runnable task2 = () -> System.out.println("10");

        System.out.println( Runtime.getRuntime().availableProcessors());

        es.submit(new ConcurrentThreadAndRunnable());
        ses.schedule(task1,1,TimeUnit.SECONDS);
        ses.scheduleWithFixedDelay(task2,0,800,TimeUnit.MILLISECONDS);

        es.shutdown();

    }


    public static void main(String[] args)
    {

        // the result is Begin End, only 0 1 2, this is because main have one thread running without bothering
        // the ExecutorService single thread
        System.out.println();
        ExecutorService s = Executors.newSingleThreadExecutor();
        System.out.println("Begin");
        s.execute(()-> System.out.println("Printing something"));
        s.execute(()-> {for(int i=0;i<3; i++) System.out.println("Value " + i); });
        System.out.println("End");

        // shutdown() does not means stop any tasks and terminate, it rejects new coming task
        // finished all submitted task only go to terminate, isShutdown() and isTerminated() will be check
        //s.shutdownNow() - this stop all running task and discards submitted task not start yet
        s.shutdown();

        future();
        //Scheduled();
        ConcurrencyWithPools();

    }

}

