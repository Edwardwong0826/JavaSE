package com.life;

@java.lang.FunctionalInterface
public interface FunctionalInterface {
    // functional interface require the class only have one single abstract method, prior to java 11,
    // we can put the default and private method at interface and still consider functional inetrface

    public abstract void doIt(int inInt);

    public default void doThat()
    {

    }

    public static void doItBe()
    {

    }
}
