package com.cool.scopedvalues.scopedvalue;

public class ScopedValueIntroduction {

    // Scoped values introduce in Java 21 and are share per thread variables from parent to child
    // But immutability to the thread local map which is not like Inheritable Thread Local need to copying the thread local map
    // So not required copying map from parent threads child threads, the value can be shared with child thread in a much more efficient way

    // Even within the thread, scoped value can only be accessed inside the "dynamic" scope of a method call
    // More like bounded to particular value

    // We can think Scoped values is the thread local removed the unconstrained mutability and removed unconstrained scope version

    // When child thread is started from dynamic scope or a task is submitted using executor service from one of the dynamic scope method
    // will other thread have access to the scoped value which was bound in the parent thread ? Most cases is no unless we use the
    // structured task scope form inside the dynamic scope
    // So the only way to share scope values across thread is using the structure scope class

    // It solved the problem of unconstrained mutability and problem of unbounded lifetime
}
