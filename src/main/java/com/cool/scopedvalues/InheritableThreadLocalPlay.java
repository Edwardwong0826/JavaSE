package com.cool.scopedvalues;

import com.cool.scopedvalues.user.User;

/*
 * - Child thread will see the thread local values of the Parent. 
 * - Thread local map is automatically copied when the child thread is created
 * - No deep copy unless childValue() method is used.
 */
public class InheritableThreadLocalPlay {

    // user class must be written in thread safe manner, its method need to be appropriately synchronized
    public static final InheritableThreadLocal<User> user = new InheritableThreadLocal<>();
//    {
//        
//        @Override
//        protected User initialValue() { 
//           return new User("anonymous"); 
//        }
//
//        @Override
//        protected User childValue(User parentValue) { 
//           return new User(parentValue.getId()); 
//        }
//    };
    
    public static void main(String[] args) throws InterruptedException {
                
        print("User => " + user.get());

        // Main thread sets the user 
        user.set(new User("main"));
        print("Modified User => " + user.get());

        // Start a Child Thread for "bob"
        Thread thread = Thread.ofVirtual().start(() -> {

            Thread.currentThread().setName("bob-thread");


            // Beware that thread local get will print main
            // because this is the InheritableThreadLocal, parent thread local value are copy over to child thread local value
            print("User => " + user.get()); // so this will print main user id
            
            user.get().setId("bobby");
            print("Modified User => " + user.get());
            
        });
        
        thread.join();

        // this will print bobby user id, because the bobby thread change the exact the same user object both main and bob thread were point to
        print("User => " + user.get());
       
    }
    
    private static void print(String m) {
        System.out.printf("[%s] %s\n", Thread.currentThread().getName(), m);
    }

}
