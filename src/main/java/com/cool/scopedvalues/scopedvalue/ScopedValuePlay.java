package com.cool.scopedvalues.scopedvalue;

import com.cool.scopedvalues.*;
import com.cool.scopedvalues.user.ScopedUserHandler;
import com.cool.scopedvalues.user.User;
import com.sun.net.httpserver.Request;

import java.lang.foreign.MemorySegment;

/*
 * Advantages
 *   - Scoped Value only available for use within the dynamic scope of the method 
 *      - during the bounded period of execution of a method
 *      - bound during start of scope and unbounded during end of scope (even exception)
 *   - Rebinding allowed but cannot modify Scoped Value
 *   - No cleanup required. automatically handled
 */
public class ScopedValuePlay {
    
    public static final ScopedValue<User> user = ScopedValue.newInstance();
    // We can also declare many scoped values we wanted
    // public static final ScopedValue<Request> request = ScopedValue.newInstance();
    
    public static void main(String[] args) throws Exception {

        print("user is Bound => " + user.isBound());

        User bob = new User("bob");
        // Here we are binding the User object value to Scoped value which the handleUser method can access
        boolean result = ScopedValue.callWhere(user, bob, ScopedValuePlay::handleUser);
                        
        // boolean result = handleUser();
        print("Result => " + result);
        print("user is Bound => " + user.isBound()); // here will print false, because at this line no longer in the dynamic scope of user binding

        // noted method like set or remove is not available for scoped value

    }
    
    private static boolean handleUser() {
        ScopedUserHandler handler = new ScopedUserHandler();
        return handler.handle();
    }

    public static void print(String m) {
        System.out.printf("[%s] %s\n", Thread.currentThread().getName(), m);
    }
    
}
