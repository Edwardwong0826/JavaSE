package com.cool.scopedvalues.scopedvalue;

import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.ThreadFactory;

import com.cool.scopedvalues.*;
import com.cool.scopedvalues.user.User;

/*
 * Scoped Values can be accessed from Child threads started
 * from StructuredTaskScope fork() method. This is because 
 * threads started from StructuredTaskScope block are guaranteed
 * to complete before the try-with-resources block ends and THUS
 * remains within the scope of the ScopedValue.
 */
public class ScopedValueTaskScopePlay {
    
    public static final ScopedValue<User> user = ScopedValue.newInstance();
    
    public static void main(String[] args) throws Exception {
        ScopedValue
            .where(user, new User("sally"))
            .call(ScopedValueTaskScopePlay::invokeTaskScope);
    }

    // When we use structured task scope form inside the dynamic scope
    // All child tasks which were submitted using the fork method will have automatic access to the scope values of the parent
    // The subtasks executed using fork are part of the dynamic scope as well
    private static String invokeTaskScope() throws Exception {

        //The only way to share scope values across thread is using the structure scope class
        
        ThreadFactory factory = Thread.ofVirtual().name("test-",0).factory();
        try (var scope = new StructuredTaskScope<String>("test-scope", factory)) {

            scope.fork(() -> {
                                
                User reqUser = user.orElse(new User("anonymous"));
                print("invokeTaskScope - user " + reqUser);
                
                // set the Id for the user
                reqUser.setId("bob");
                
                return "done";
            });
            
            scope.join();
        }

        // When exit from the try final block, notice we set user to bob at above StructuredTaskScope block
        // Tt will print bob, because is the same object shared between child thread and parent thread
        // Even the scope values variable itself is immutable, but the value object may be mutable
        // Beware there is implications for synchronization
        // If we allow the user in this case can be mutable then the user class has to be thread safe if access by many threads
        // if don't need the value to be mutable, then can use Java record for user
        User reqUser = user.orElse(new User("anonymous"));
        print("invokeTaskScope - user " + reqUser);
        return "done";
    }

    public static void print(String m) {
        System.out.printf("[%s] %s\n", Thread.currentThread().getName(), m);
    }

}
