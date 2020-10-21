package com.life;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentThreadSafe{

    private static int count = 0;
    private static AtomicInteger atomicInt = new AtomicInteger(0);
    private AtomicBoolean atomicBoolean = new AtomicBoolean(true);
    private AtomicLong atomicLong = new AtomicLong(1L);

    // synchronized can on the method or block {} and any object instance
    // synchronized blocks other thread access while one thread obtain the lock and using it, it was atomic operation
    // only when the thread is done and release the lock, other thread can get the lock
    // public synchronized increment() - either two way on method or on block is same
    public void increment()
    {
        synchronized(this)
        {
            System.out.println((++count) + "");
        }

    }

    public void decrement()
    {
        synchronized(this)
        {
            System.out.println((--count) + "");
        }
    }

    public synchronized int getCount() { return count; }

    private void remove() {
        System.out.println("Removing");
    }

    private void clean() {
        System.out.println("Cleaning");
    }

    private void add() {
        System.out.println("Adding");
    }

    public static void synchronizedTest()
    {
        System.out.println("synchronizedTest-----------------------");
        ExecutorService fs = Executors.newFixedThreadPool(10);
        ExecutorService fs2 = Executors.newFixedThreadPool(10);

        ConcurrentCallable callable = new ConcurrentCallable();
        ConcurrentThreadSafe ces = new ConcurrentThreadSafe();

        System.out.println();
        for (int i = 0; i < 10; i++)
        {
            // with or without this doesn't matter, synchronized the creation of threads but not the execution
            // of the threads, thread will created one at time, they may all execute and perform work at same time
            fs.submit(ces::increment); // () -> callable.increment()
        }

        for(int j=0; j < 10; j++)
        {
            // why put in second loop? put in the same seems race condition, the value not update correctly
            fs.submit(ces::decrement); // () -> ces.decrement()

        }

        Lock lock = new ReentrantLock();
        if(lock.tryLock())

            try
            {

                for (int k = 0; k < 10; k++)
                {

                    fs2.submit(callable::increment);

                }

            } finally
            {
                // lock() how many times,then unlock() how many else will throw exception as duplicate lock requests
                lock.unlock();
                System.out.println("Updated values: " + callable.count);
            }

        else
        {
            System.out.println("Cloud not acquire lock!!!");
        }


        fs.shutdown();
        fs2.shutdown();
    }

    public static void CopyOnWriteCollections()
    {
        System.out.println("CopyOnWrite----------------------------");
        //  creates a new underlying structure anytime the list is modified
        List<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(1,2,3));

        for(Integer num: list)
        {
            System.out.println(num + " ");
            list.add(1); // if we don't use ConcurrentCollection, it will throw ConcurrentModificationException
        }

        System.out.println("List size: " + list.size());

    }

    public static void CyclicBarriers()
    {
        // if use thread pool, make sure thread pool larger or equal to CyclicBarrier value
        // else will hang , result dead lock

        System.out.println("CyclicBarriers-------------------------");
        ExecutorService es = Executors.newFixedThreadPool(4);
        ConcurrentThreadSafe ces = new ConcurrentThreadSafe();
        CyclicBarrier c1 = new CyclicBarrier(4);
        CyclicBarrier c2 = new CyclicBarrier(4, () -> System.out.println("Finished!"));

        for (int i = 0; i < 4; i++) {
            es.submit(() -> ces.performTask(c1, c2));
        }

        es.shutdown();

    }

    public void performTask(CyclicBarrier c1, CyclicBarrier c2)
    {

        // CyclicBarrier can enforce all the thread at certain point to wait and must wait all
        // thread finish to release the barrier before continue next execution

        try
        {
            remove(); // CyclicBarrier take constructor of value to indicating the number of thread to wait
            c1.await(); // only all thread called await(), the barrier is released to continue next task
            clean();
            c2.await();
            add();
        } catch (InterruptedException | BrokenBarrierException ignored)
        {

        }

    }
    public static void main(String[] args)
    {

        synchronizedTest();
        CopyOnWriteCollections();
        CyclicBarriers();

    }

}
