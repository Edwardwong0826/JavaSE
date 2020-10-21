package com.feature;

public class testAutoCloseable implements AutoCloseable {
    @Override
    public void close() throws Exception {
        System.out.println("D");

    }
}
