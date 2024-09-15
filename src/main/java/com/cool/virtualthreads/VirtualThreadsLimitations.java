package com.cool.virtualthreads;

public class VirtualThreadsLimitations {

    // Virtual Threads use Monitor / synchronised will cause the underlying platform thread pinned down
    // try to use Lock class form java.util.concurrent
    // refer below blog for more detail explain
    // https://mikemybytes.com/2024/02/28/curiosities-of-java-virtual-threads-pinning-with-synchronized/

    // Blocking with native frames on Stack (JNI) - native method that call C language codes

    // Control memory per stack
    // platform thread use fixed memory set by JYM, for virtual threads is allocated as we use, so memory increase use when we create more
    // - Reduce Thread Locals
    // - No deep recursions

}
