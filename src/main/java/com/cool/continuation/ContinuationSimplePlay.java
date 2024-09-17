package com.cool.continuation;

import jdk.internal.vm.Continuation;
import jdk.internal.vm.ContinuationScope;

import java.time.Duration;

// Continuation related classes have been moved inside this
// internal package but we are making this visible by exposing
// the package --add-exports java.base/jdk.internal.vm=ALL-UNNAMED

// in intellij idea need to go to edit configuration enable Add VM options and copy --add-exports java.base/jdk.internal.vm=ALL-UNNAMED to the VM option
public class ContinuationSimplePlay {

    private static final ContinuationScope SCOPE1 = new ContinuationScope("scope1");


    private static void method1() {
        System.out.println("method1 : enter");

        // When the continuation goes back to previous yield point, and it was able to recreate the local variables example
        // This part variable got increment at the end
        // At the point where yield was called, the entire stack for that method, example method1 stack is stored in continuation object
        // And because continuation object also holds the code pointer, so it know exactly where to start from
        // So when call continuation run again, it already has the stack associated and recreate the stack for the method1,

        int part = 0;

        // When we yield, the control will go back to the location where we had called run for that particular scope
        part++;
        System.out.println("method1 : execute part " + part);
        Continuation.yield(SCOPE1); // First time continuation run execute until this yield, the control go back line 46

        part++;
        System.out.println("method1 : execute part " + part);
        Continuation.yield(SCOPE1); // Second time continuation run execute until this yield, the control go back line 47

        part++;
        System.out.println("method1 : execute part " + part);
        Continuation.yield(SCOPE1); // Third time continuation run execute until this yield, the control go back line 48

        System.out.println("Method1 Part value: " + part);
        System.out.println("method1 : exit"); // Fourth time continuation run will execute rest of code because there is no yield
    }

    public static void main(String[] args) throws Exception {

        System.out.println("main : enter");

        // Delimited continuations it's limited to the runnable object that is passed as the argument
        // When the continuation run, when yield continuation run again it will goes back to the scope continuation, in this case scope1
        // The continuation is associated with scope
        Continuation c = new Continuation(SCOPE1, () -> method1());


        c.run(); // When first time continuation run, it will execute method1 until there is a yield which is line 25
        c.run(); // Second time when continuation run, goes back to method1 line 26 or line 27
        c.run(); // Third time when continuation run, goes back to method1 line 30 or line 31
        c.run(); // Fourth time when continuation run, goes back to method line 34 or 35

        // loop version equal with above
//        while (!c.isDone()) {
//            c.run();
//
//            System.out.println(">> main : scope1 loop");
//
//            Thread.sleep(Duration.ofSeconds(3));
//        }


        System.out.println("main : exit");

    }

}
