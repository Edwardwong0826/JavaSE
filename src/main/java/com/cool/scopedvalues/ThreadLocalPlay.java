package com.cool.scopedvalues;

import com.cool.scopedvalues.user.User;

/*
 * Main and Child Thread can set different User object in Threadlocal.
 * Demonstrates how Thread Locals work when multiple threads are in 
 * play.
 */
public class ThreadLocalPlay {

    // Thread local variable will be associated with thread
    public static final ThreadLocal<User> user = new ThreadLocal<User>();

    // we can also set the initializer supplies a value for the user when the get method is called in the supplier
    //public static final ThreadLocal<User> user = ThreadLocal.withInitial(() -> new User("anonymous"));
    
    public static void main(String[] args) throws InterruptedException {

        // if the ThreadLocal variable did not set the value, it will return null
        print("User => " + user.get());

        // Main thread sets the user 
        user.set(new User("main"));

        // when main thread call get, it will simply go to thread local map of thread one (main thread) and return the user object associated with that thread
        print("Modified User => " + user.get());

        // Start a Child Thread for "bob"

        Thread thread = Thread.ofVirtual().start(() -> {
            // At this time there is two thread there is running parallel, the main thread and the bob thread
            Thread.currentThread().setName("bob-thread");

            // this user.get is executed by bob thread so get will return null because nothing was set earlier in the bob thread
            print("User => " + user.get());
            user.set(new User("bob")); // when set is called on bob thread, thread local user gets associated with user bob
            print("Modified User => " + user.get()); // so here it will print bob
            
        });
        
        thread.join();
        
        print("User => " + user.get());
       
    }
    
    private static void print(String m) {
        System.out.printf("[%s] %s\n", Thread.currentThread().getName(), m);
    }

}
