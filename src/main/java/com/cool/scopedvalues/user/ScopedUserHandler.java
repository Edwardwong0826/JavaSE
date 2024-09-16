package com.cool.scopedvalues.user;

import com.cool.scopedvalues.*;
import com.cool.scopedvalues.scopedvalue.ScopedValuePlay;

public class ScopedUserHandler {

    public boolean handle() {
                
        boolean bound = ScopedValuePlay.user.isBound();
        print("handle - user is Bound => " + bound);

        if (bound) {

            // return the value of the scope value if bound in the current thread otherwise will throw exception
            User requestUser = ScopedValuePlay.user.get();
            print("handle - User => " + requestUser);
            
            // handle user 'requestUser'
        }
        
        return bound;
    }

    public static void print(String m) {
        ScopedValuePlay.print(m);
    }

}
