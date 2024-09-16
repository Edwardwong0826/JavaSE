package com.cool.scopedvalues;

import com.cool.scopedvalues.user.User;

/*
 * Thread locals can be created with an initializer 
 *  - Initial value would be determined by running the supplier
 *  - Supplier is called when calling get()
 *  - After remove() if you call get() => supplier will be called
 *  
 */
public class ThreadLocalInitializerPlay {

    // the initializer supplies a value for the user when the get method is called in the supplier
    public static final ThreadLocal<User> user = ThreadLocal.withInitial(() -> new User("anonymous"));
    
    public static void main(String[] args) throws InterruptedException {

        // Because we initial thread local user with supplier, so if get is called by the thread, it will print the user id that we supply
        // instead of default null or empty
        print("User => " + user.get());

        // Main thread sets the user 
        user.set(new User("main"));


        // when main thread call get, it will simply go to thread local map of thread one (main thread) and return the user object associated with that thread
        print("Modified User => " + user.get());

        // Start a Child Thread for "bob"
        Thread thread = Thread.ofPlatform().start(() -> {
            Thread.currentThread().setName("bob-thread");


            // when bob thread call get, it will simply go to thread local map of thread two (bob thread) and return the user object associated with that thread
            print("User => " + user.get());

            user.set(new User("bob"));
            print("Modified User => " + user.get());
            
        });
        
        thread.join();
        
        print("User => " + user.get());
       
    }
    
    private static void print(String m) {
        System.out.printf("[%s] %s\n", Thread.currentThread().getName(), m);
    }
}
