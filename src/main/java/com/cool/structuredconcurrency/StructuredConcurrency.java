package com.cool.structuredconcurrency;

public class StructuredConcurrency {

    // Structured Concurrency is to allow thread which are started from within a block of code , are guaranteed to terminate by the time the block exits
    // can be used in regular / platform thread or virtual thread
    // it required doing a lot of thread cancellation on child threads at appropriate times, the best way to do the thread terminate or cancel is using interrupt

    // StructuredTaskScope - under the hood , uses a thread factory to run the task, which is virtual thread factory, but can choose the factory in constructor
    // Subtask - subtask is similar to future, but it was use by the structured Concurrency with virtual thread
}
