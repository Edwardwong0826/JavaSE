package com.cool.scopedvalues.scopedvalue;

import com.cool.scopedvalues.*;
import com.cool.scopedvalues.user.User;

/*
 * Rebinding of Scoped Value is allowed
 */
public class ScopedValueRebindPlay {
    
    public static final ScopedValue<User> user = ScopedValue.newInstance();
    
    public static void main(String[] args) throws Exception {

        print("user is Bound => " + user.isBound());

        User bob = new User("bob");
        // runWhere method is used where we are not interested in the return value of the method with no return
        ScopedValue.runWhere(user, bob, ScopedValueRebindPlay::handleUser);

        // Bind the 'user' to bob within the scope of Supplier method handleUser
        //ScopedValue.getWhere(user, bob, ScopedValueRebindPlay::handleUser);
                        
        print("user is Bound => " + user.isBound());
    }
    
    private static void handleUser() {
        
        print("handleUser - " + user.get());

        // Within the dynamic scope of handleUser we are rebinding the scope values user to another user called anonymous
        // The moment when this new dynamic scope ends, the old binding would take into effect
        ScopedValue.runWhere(user, new User("anonymous"), 
                ScopedValueRebindPlay::callAsAnonymous);  

        // So here will print bob user id
        print("handleUser - " + user.get());
    }
    
    private static void callAsAnonymous() {
        // This will print anonymous from scoped value
        print("callAsAnonymous - " + user.get());

   }

    public static void print(String m) {
        System.out.printf("[%s] %s\n", Thread.currentThread().getName(), m);
    }
    
}
