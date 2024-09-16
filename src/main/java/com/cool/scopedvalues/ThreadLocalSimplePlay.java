package com.cool.scopedvalues;

import com.cool.scopedvalues.user.User;
import com.cool.scopedvalues.user.UserHandler;

/**
 * Simple example with a Single Thread. Demonstrates use of Thread Local
 * as an implicit parameter in whole method stack
 */
public class ThreadLocalSimplePlay {

    // Thread local variable will be associated with thread, actually is the ThreadLocal Map associated with the
    public static ThreadLocal<User> user = new ThreadLocal<User>();

    // we can also set the initializer supplies a value for the user when the get method is called in the supplier
    //public static final ThreadLocal<User> user = ThreadLocal.withInitial(() -> new User("anonymous"));



    public static void main(String[] args) {

        // if the ThreadLocal variable did not set the value, it will return null
        // when main thread call get, it will simply go to thread local map of thread one (main thread) and return the user object associated with that thread
        print("User => " + user.get());

        // Main thread sets the user 
        user.set(new User("anonymous"));
        print("User => " + user.get());
        
        handleUser();
    }

    private static void handleUser() {
        
        UserHandler handler = new UserHandler();
        handler.handle();
    }

    public static void print(String m) {
        System.out.printf("[%s] %s\n", Thread.currentThread().getName(), m);
    }

}

