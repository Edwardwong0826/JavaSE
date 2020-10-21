package com.feature;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;

public class testCloseable implements Closeable {
    @Override
    public void close() throws IOException {
        System.out.println("w");
        throw new FileNotFoundException("hang");
    }
}
