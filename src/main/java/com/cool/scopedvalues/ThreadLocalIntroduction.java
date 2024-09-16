package com.cool.scopedvalues;

public class ThreadLocalIntroduction {

    // Thread local is to allow variable can be visible to the entire call stack for a thread, but no to other threads
    // Like a global, but global only for a thread, to achieved thread safe

    // One good thing about Thread local, if we create a child thread the thread local from the parent are not automatically carried over to the child
    // The child thread will never access the parent thread local, but this advantage also disadvantage

    // If child thread want to have visibility to the parent thread local, JDk provides the class called Inheritable ThreadLocal with the ability
    // when child thread get created, the inheritable ThreadLocal will get copy over the child
    // by default the child value will identical to the parent value, child and parent values would be pointing to the same object
    // and this has synchronization implications

    // One of the use case is in Spring Security, the user who is accessing the application is stored in a thread local map variable

    // Disadvantage
    // Better to remove ThreadLocal variable once done using as might cause memory leak issue or affect after business logic
    // because ThreadLocal remain in thread local map until the thread dead or removed explicit using remove method
    // If we are using with Platform thread
    // This is not a problem for virtual threads as much because virtual thread normally to be used and discarded after that and never be pool in thread pool.

    // Possible memory issues for large number for Virtual Threads and performance problem when copy the map from parent thread to child thread


    // Thread locals can use in platform threads as well as virtual thread
    // There is a problem we use thread local with virtual thread
    // JDK introduce new scope value to address this problem
}
